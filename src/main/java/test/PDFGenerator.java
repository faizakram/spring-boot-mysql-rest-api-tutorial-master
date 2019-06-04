package com.example.project.DmsServerNew.utility;

import java.io.ByteArrayOutputStream;

public class PDFGenerator {

	private static final String PDF_CONVERSION_COMMAND = "C:/Program Files/wkhtmltopdf/bin/wkhtmltopdf --margin-left 0  --margin-right 0 --margin-top 10 --header-html C:/Users/Rahul/Desktop/Template/header.html --footer-html "
			+ "C:/Users/Rahul/Desktop/Template/footer.html --footer-spacing 10 - -";

	/**
	 * 
	 * @param htmlContent
	 * @param footerFileName
	 * @return
	 */
	public static ByteArrayOutputStream generatePDFContentFromHTML(String htmlContent) {
		try {
			return PDFConvertorUtil.generatePDF(htmlContent, PDF_CONVERSION_COMMAND, "UTF-8");
		} catch (Exception e) {
		}
		return null;
	}
}
