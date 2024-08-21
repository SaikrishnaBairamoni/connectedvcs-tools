package gov.usdot.cv.msg.builder.util;

import gov.usdot.cv.rgaencoder.Encoder;
import gov.usdot.cv.rgaencoder.RGAData;
import gov.usdot.cv.mapencoder.ByteArrayObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class J2945Helper {

	private static final Logger logger = LogManager.getLogger(J2945Helper.class);
	private static final String START_CURLY_BRACKET = "{";

	// This constant is used to convert the given LAT/LON to J2945 format
	private static final int LAT_LONG_CONVERSION_FACTOR = 10000000;
	
	public static String getHexString(RGAData message) 
	{

		byte[] bytes = getBytes(message);
		return Hex.encodeHexString(bytes);
	}

	public static byte[] getBytes(RGAData message) {
		Encoder encoder = new Encoder();
		ByteArrayObject encodedMsg = encoder.encode(message);
		return encodedMsg.getMessage();
	}
}