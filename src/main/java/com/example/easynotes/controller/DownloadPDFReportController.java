package com.example.easynotes.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.example.easynotes.model.TxnDocument;
import com.example.easynotes.repository.TxnDocumentRepository;
import com.example.easynotes.utils.DownloadFile;
import com.example.easynotes.utils.S3Utility;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
@RequestMapping("download")
public class DownloadPDFReportController {

	public static final String MIME_TYPE = "application/octet-stream";
	public static final String FILE_HEADER = "Content-Disposition";
	public static final String DOWNLOAD_FILE_PROPERTY_NAME = "inline; filename=\"%s\"";
	@Autowired
	private TxnDocumentRepository txnDocumentRepository;
	@Autowired
	private S3Utility s3Utility;

	@GetMapping("pdf-itext")
	public void doGet(@PathVariable Integer id, HttpServletResponse response) throws IOException {
		response.setContentType(MIME_TYPE);
		response.setHeader(FILE_HEADER, String.format(DOWNLOAD_FILE_PROPERTY_NAME, "abc.pdf"));
		try {
			// step 1
			Document document = new Document(PageSize.A4, 20, 20, 20, 50);
			// step 2
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
			List<TxnDocument> txnDocument = txnDocumentRepository.findBy(id);
			Font f = new Font();
			f.setStyle(Font.BOLD);
			f.setSize(14);
			for(TxnDocument txn : txnDocument) {
				Paragraph p = new Paragraph();
				p.setFont(f);
				p.add(txn.getDocreferid().toString());
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				Image image = Image.getInstance("http://localhost:8080/download/img/"+txn.getDocreferid());
				image.setAlignment(Element.ALIGN_CENTER);
				document.add(image);
			}
			// close
			document.close();
		} catch (DocumentException de) {
			throw new IOException(de.getMessage());
		}
	}

	@GetMapping("img")
	public StreamingResponseBody downloadImg(@PathVariable Integer id, HttpServletResponse httpResponse) {
		DownloadFile downloadFile = new DownloadFile();
		TxnDocument txnDocument = txnDocumentRepository.getById(id);
		downloadFile.setFileName(txnDocument.getDocsavefilename());
		downloadFile.setFile(s3Utility.downloadS3Byte(txnDocument.getDocsavefilename()));
		httpResponse.setContentType(MIME_TYPE);
		httpResponse.setHeader(FILE_HEADER, String.format(DOWNLOAD_FILE_PROPERTY_NAME, downloadFile.getFileName()));
		return new StreamingResponseBody() {
			@Override
			public void writeTo(OutputStream outputStream) throws IOException {
				outputStream.write(downloadFile.getFile());
				outputStream.flush();
			}
		};
	}

}
