package gov.usdot.cv.msg.builder.util;

import gov.usdot.cv.mapencoder.Encoder;
import gov.usdot.cv.mapencoder.MapData;
import gov.usdot.cv.mapencoder.ByteArrayObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class J2735Helper {

	private static final Logger logger = LogManager.getLogger(J2735Helper.class);
	private static final String START_CURLY_BRACKET = "{";

	// This constant is used to convert the given LAT/LON to J2735 format
	private static final int LAT_LONG_CONVERSION_FACTOR = 10000000;
	
	public static String getHexString(MapData message) 
	{

		byte[] bytes = getBytes(message);
		return Hex.encodeHexString(bytes);
	}

	public static byte[] getBytes(MapData message) {
		Encoder encoder = new Encoder();
		ByteArrayObject encodedMsg = encoder.encode(message);
		return encodedMsg.getMessage();
	}
	
	public static byte[] getCrc(MapData message) {
		byte[] fullMessage = getBytes(message);
		int checkSum = CrcCccitt.calculateCrcCccitt(fullMessage, 0, fullMessage.length-2);
		ByteBuffer buffer = ByteBuffer.allocate(2).order(ByteOrder.BIG_ENDIAN);
		buffer.putShort((short)checkSum);
		return buffer.array();
	}


	public static int bitWiseOr(int[] nums) {
		int result = 0;
		for (int i=0; i<nums.length; i++) {
			result|= nums[i];
		}
		return result;
	}

	/**
	 * Takes a Lat or Long as a double and converts to an int.
	 * @param point
	 * @return
	 */
	public static int convertGeoCoordinateToInt(double point) {
		double convertedPoint = point * LAT_LONG_CONVERSION_FACTOR;
		return (int)Math.round(convertedPoint);
	}
	
}
