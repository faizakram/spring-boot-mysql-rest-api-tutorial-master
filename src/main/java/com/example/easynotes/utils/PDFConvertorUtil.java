package com.example.easynotes.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

/**
 * utility class to convert html content to PDF content
 *
 * @author 13354 Created: May 15, 2018
 */
public class PDFConvertorUtil {


	private PDFConvertorUtil() {

	}

	/**
	 * generate PDF stream using html content
	 * 
	 * @param htmlContent
	 * @param command
	 * @param encoding
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static ByteArrayOutputStream generatePDF(String htmlContent, String command, String encoding)
			throws IOException {

		Process wkhtml; // Create uninitialized process

		try (ByteArrayOutputStream convertedStream = new ByteArrayOutputStream()) {
			// read html content
			InputStream inputStream = new ByteArrayInputStream(htmlContent.getBytes(Charset.forName(encoding)));

			wkhtml = Runtime.getRuntime().exec(command); // Start process

			// thread to read html content to set it to Process
			IOUtils.copy(inputStream, wkhtml.getOutputStream());

			wkhtml.getOutputStream().flush();
			wkhtml.getOutputStream().close();

			IOUtils.copy(wkhtml.getInputStream(), convertedStream);

			wkhtml.getInputStream().close();
			wkhtml.destroy();
			inputStream.close();

			return convertedStream;

		} catch (RuntimeException | IOException ex) {
			ex.printStackTrace();
		}

		return null;
	}
	
	
}
