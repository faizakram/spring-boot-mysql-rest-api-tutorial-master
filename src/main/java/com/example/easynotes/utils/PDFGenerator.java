package com.example.easynotes.utils;

import java.io.ByteArrayOutputStream;

public class PDFGenerator {

	private static final String PDF_CONVERSION_COMMAND = "E:/logs/wkhtmltopdf/bin/wkhtmltopdf --margin-left 0  --margin-right 0 --margin-top 10 --header-html E:/Websites/Wildfly-SparksLinkDEVAPI/standalone/SparksLink/mail_templates/header.html --footer-html E:/Websites/Wildfly-SparksLinkDEVAPI/standalone/SparksLink/mail_templates/footer.html --footer-spacing 10 - -";

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
