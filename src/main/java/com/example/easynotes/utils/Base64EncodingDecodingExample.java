package com.example.easynotes.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Base64EncodingDecodingExample {

	public static void main(String[] args) throws IOException
	 {
	     String stringValue = "Hello world";
	    // File file = new File("C:\\Users\\13615\\Desktop\\automobile-automotive-car-112460.jpg");
	     byte[] array = Files.readAllBytes(Paths.get("C:\\Users\\13615\\Desktop\\automobile-automotive-car-112460.jpg"));
	     // Encode this value into Base6 
	     String stringValueBase64Encoded = Base64.getEncoder().encodeToString(array);
	     System.out.println(stringValue  + " when Base64 encoded is: " + stringValueBase64Encoded);
	     
	     // Decode Base64 encoded value
	     /*byte[] byteValueBase64Decoded = Base64.getDecoder().decode(stringValueBase64Encoded);
	     String stringValueBase64Decoded = new String(byteValueBase64Decoded);
	     
	     System.out.println(stringValueBase64Encoded  + " when decoded is: " + stringValueBase64Decoded);*/
	 }
}
