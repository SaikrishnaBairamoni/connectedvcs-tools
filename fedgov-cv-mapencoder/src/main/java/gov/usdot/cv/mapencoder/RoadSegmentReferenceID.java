/*
 * Copyright (C) 2023 LEIDOS.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
 
package gov.usdot.cv.mapencoder;

public class RoadSegmentReferenceID {
    private int region;
    private boolean regionExists;

    private int id;
    private boolean idExists;

    public RoadSegmentReferenceID() {
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public boolean isRegionExists() {
        return regionExists;
    }

    public void setRegionExists(boolean regionExists) {
        this.regionExists = regionExists;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIdExists() {
        return idExists;
    }

    public void setIdExists(boolean idExists) {
        this.idExists = idExists;
    }
}
