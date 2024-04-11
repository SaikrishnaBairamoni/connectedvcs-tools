/**
 * Created by lewisstet on 2/25/2015.
 * Updated 2/2017 by martzth
 */

/**
 * DEFINE GLOBAL VARIABLES
 */
    var hidden_drag, intersection_sidebar, deleteMode, currentControl, $imgs, itisForm, search_latlon;


/**
 * Define functions that must bind on load
 */

$(document).ready(function() {

    hidden_drag = $('#hidden-drag');
    intersection_sidebar = $('#sidebar');

    /**
     * Purpose: geocomplete for allowing place search
     * @params  address input box
     * @event uses geocomplete from google -> set cookie and move map to location
     */

    $('#address-search').geocomplete().bind("geocode:result", function(event, result){

        var search_lat = result.geometry.location.lat();
        var search_lon = result.geometry.location.lng();

        setCookie("tim_latitude", search_lat, 365);
        setCookie("tim_longitude", search_lon, 365);
        setCookie("tim_zoom", map.getZoom(), 365);

        try {
            var location = new OpenLayers.LonLat(search_lon, search_lat);
            location.transform(new OpenLayers.Projection("EPSG:4326"), map.getProjectionObject());
            map.setCenter(location, 18);
        }
        catch (err) {
            console.log("No vectors to reset view");
        }
    });


    //--- Create Intersection markers
    var intersection_contents = $('#intersection-tab-contents');
    var arrayLength = intersection_features.length;
    for (var i = 0; i < arrayLength; i++) {
        var html = '<div class="col-lg-6">';
        html += '<img id="intersection_img_'+ intersection_features[i].type + intersection_features[i].id +'" class="drag-intersection-img" src="'+ intersection_features[i].img_src +'">';
        html += '<p>' + intersection_features[i].name + '</p>';
        html += '</div>';
        intersection_contents.append(html);
    }
    //--- end intersection


    /**
     * Purpose: allow marker images in sidebar to be dragable onto layer
     * @params  image object
     * @event makes images draggable
     */

    $imgs = intersection_sidebar.find('.drag-intersection-img');
    $imgs.draggable({
        appendTo: 'body', containment: 'body', zIndex: 150000, cursorAt: {left:25, top:50},
        helper: function() {
            var container = $('<div/>');
            var dragged = $(this).clone();
            dragged.attr('class', 'dragged-img');
            container.append(dragged);
            return container;
        },
        start: function(e, ui) {
            hidden_drag.removeClass('hidden');
        },
        stop: function(e) {
            hidden_drag.addClass('hidden');

            // check to see if intersection markers have already been placed
            var id = this.id.substring(this.id.indexOf("TIM"),20);
            var num_features = vectors.features.length;
            for( var i=0; i < num_features; i++) {
                if( id == vectors.features[i].attributes.marker.type ) {
                    console.log("marker already placed");
                    return;
                }
             
            }

            // check to see if intersection markers have already been placed
            var id = this.id.substring(this.id.indexOf("VER"),20);
            var num_features = vectors.features.length;
            for( var i=0; i < num_features; i++) {
                if( id == vectors.features[i].attributes.marker.type ) {
                    console.log("marker already placed");
                    return;
                }

            }

            if( $(this).hasClass('drag-intersection-img') ) {
            	if (currentControl != 'drag'){
            		$('#dragSigns').click();
            	}
                var point = new OpenLayers.Geometry.Point(e.pageX, e.pageY - 50); // subtract 50px because of navbar
                clone(this, point);
            }
        }
    });
    //--- end drag


    /**
     * Purpose: clone marker image onto layer
     * @params  object, point
     * @event places clone of marker image onto map post drag
     */

    function clone(object, point) {
        var lonlat = map.getLonLatFromPixel(point);

        var cloned_feature = new OpenLayers.Feature.Vector(
            new OpenLayers.Geometry.Point(lonlat.lon, lonlat.lat),
            {some:'data'},
            {externalGraphic: object.src, graphicHeight: 50, graphicWidth: 50, graphicYOffset: -50});


        cloned_feature.attributes = {"LonLat": lonlat.transform(toProjection, fromProjection)};
        cloned_feature.attributes.verifiedLat = cloned_feature.attributes.LonLat.lat;
        cloned_feature.attributes.verifiedLon = cloned_feature.attributes.LonLat.lon;
        cloned_feature.attributes.endTime = '';
        cloned_feature.attributes.startTime = '';
        cloned_feature.attributes.packetID = '';
        cloned_feature.attributes.content = '';
        cloned_feature.attributes.speedLimit = '';
        cloned_feature.attributes.elevation = '';
        cloned_feature.attributes.mutcd = '';
        cloned_feature.attributes.infoType = '';
        cloned_feature.attributes.priority = '';
        cloned_feature.attributes.direction = '';
        cloned_feature.attributes.heading = '';

        var intersection_id = parseInt(object.id.match(/(\d+)$/)[0], 10);
        cloned_feature.attributes['marker'] = intersection_features[intersection_id];

        vectors.addFeatures(cloned_feature);
    }


    /**
     * Purpose: uses sheepit form to allow for multiple itis code form w/ select2 combobox
     * @params  DOM form
     * @event creates form and establishes parameters, also allows select2 to have free text added to combobox
     */

    itisForm = $('#itisForm').sheepIt({
        separator: '',
        allowRemoveLast: true,
        allowRemoveCurrent: true,
        allowRemoveAll: true,
        allowAdd: true,
        allowAddN: true,
        maxFormsCount: 13,
        minFormsCount: 0,
        iniFormsCount: 1,
        afterAdd: function(source, newForm) {
            $('.itis_code_list').select2({
                placeholder: 'Select Options, Type Codes or Value (e.g. n30)',
                templateSelection: templateSelection,
                tags: true,
                createTag: function (params) {
                    return {
                        id: params.term,
                        text: params.term,
                        newOption: true
                    }
                },
                templateResult: function (data) {
                    var $result = $("<span></span>");

                    $result.text(data.text);

                    if (data.newOption) {
                        $result.append(" <em>(new)</em>");
                    }

                    return $result;
                }
            });

            loadCodes();

            $('.itis_text_list').attr("placeholder", "Type Text (Limit: " + selected_marker_limit + " chars)");
            $('.itis_text_list').attr("data-parsley-maxlength", selected_marker_limit);

            $('.itis_code_list').on("select2:select", function (evt) {
                var element = evt.params.data.element;
                var $element = $(element);

                $element.detach();
                $(this).append($element);
                $(this).trigger("change");
            });
        }
    });

    function templateSelection(val) {
        return val.id;
    }

});

/**
 * Purpose: ??
 * Not used - may need to be @deprecated
 */

function makeDraggable( selector ) {
    selector.draggable({
        appendTo: 'body', containment: 'body', zIndex: 150000, cursorAt: {left:25, top:25},
        revert: function() {
            return 'invalid';
        },
        start: function(e, ui) {
            hidden_drag.removeClass('hidden');
            $('#trash_droppable').toggleClass("hidden");
        },
        stop: function(e) {
            hidden_drag.addClass('hidden');
            $('#trash_droppable').toggleClass("hidden");
        }
    });
}


/**
 * Purpose: toggle marker lock
 * @params  click event
 * @event toggle marker lock
 */

$("button[name='layerControl']").click(function(e) {
	deleteMode = false;
	$("#dragSigns i").removeClass('fa-unlock').addClass('fa-lock')
	$(this).addClass('current').siblings().removeClass('active');
	currentControl = this.value;
	if(!$(this).hasClass('active')){
		if(currentControl === 'drag'){
			$("#dragSigns i").removeClass('fa-lock').addClass('fa-unlock')
		}
		if(currentControl === 'del'){
			deleteMode = true;
		}
		toggleControlsOn(currentControl);
	} else {
		deleteMode = false;
		toggleControlsOn('none');
	}
});


/**
 * Purpose: link to help doc in config
 * @params  click event
 * @event load appropriate help window for a field
 */

$('.fa-question-circle').click(function(){
	var tag = $(this).attr('tag');
	var obj = $.grep(help_notes, function(e){ return e.value === tag; });
	$('#help_modal').modal('show');
	$('#min').html(obj[0].min)
	$('#max').html(obj[0].max)
	$('#units').html(obj[0].units)
	$('#description').html(obj[0].description)
	$('#help_modal h4').html(obj[0].title)
});


/*****************************************
 * ITIS Codes
 * Methods
 *****************************************/

/**
 * Purpose: loads the itis codes from config.js into the combobox
 * @params  -
 * @event loads the itis codes from config.js into the combobox
 */

function loadCodes() {
    $.each(itis_list, function (key, item) {
        $('.itis_code_list').append(new Option(item.text, item.value));
    });
}


/**
 * Purpose: selects text or select2 combobox
 * @params  change event
 * @event disables non-selected option and clears the data
 */

$(document).on('change', '#itisForm input[type=radio]', function() {
    var itisFormSelection = (this.value).split("_");
    clearForm(itisFormSelection[0], itisFormSelection[1]);
});

function clearForm(name, index){
    if (name === "code") {
        console.log("removing text")
        $('#itisForm_' + index + '_itis_text').prop('disabled', true);
        $('#itisForm_' + index + '_itis_codes').prop('disabled', false);
        $('#itisForm_' + index + '_itis_text').val('');
    } else {
        console.log("removing code")
        $('#itisForm_' + index + '_itis_text').prop('disabled', false);
        $('#itisForm_' + index + '_itis_codes').prop('disabled', true);
        $('#itisForm_' + index + '_itis_codes').empty();
        loadCodes();
    }
}


/**
 * Purpose: add/remove/rebuild form
 * @params  change event
 * @event works with sheepit form to add new, delete last row, or rebuild form after close of sidebar and reopen
 */

function addITISForm() {
    itisForm.addForm();
}

function removeITISForm() {
    itisForm.removeAllForms();
}

function rebuildITISForm(contentArray) {
    removeITISForm();
    var results = contentArray.length;
    for (var i = 0; i < results; i++) {
        itisForm.addForm();
        if(contentArray[i].text === ''){
            $('#text_' + i).prop("checked", false);
            $('#code_' + i).prop("checked", true);
            clearForm("code", i)
        } else {
            $('#code_' + i).prop("checked", false);
            $('#text_' + i).prop("checked", true);
            clearForm("text", i)
        }
        $.each(contentArray[i].codes, function(j,obj){
            var found = false;
            itis_list.some(function(el){
                if (el.value == obj) {
                    var str = (el.text).split(")")
                    $(".itis_code_list option[value=" + el.value + "]").remove();
                    $('.itis_code_list').append(new Option("(" + el.value + ")" + str[1] , obj));
                    found = true;
                }
            })
            if (!found) {
                $('.itis_code_list').append(new Option("(" + obj + ") Custom Tag" , obj));
            }
        });
        $("#itisForm_"+ i + "_itis_text").val(contentArray[i].text);
        $("#itisForm_"+ i + "_itis_codes").val(contentArray[i].codes).trigger("change");
    }
    content = [];
}