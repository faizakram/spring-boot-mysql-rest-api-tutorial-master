package com.example.easynotes.controller;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.easynotes.utils.DownloadFile;

@RestController
public class DownloadZipFileController {
	
	public static final String MIME_TYPE = "application/octet-stream";
	public static final String FILE_HEADER = "Content-Disposition";
	public static final String DOWNLOAD_FILE_PROPERTY_NAME = "inline; filename=\"%s\"";


	@GetMapping(value = "/download")
	public void showPartner(HttpServletRequest request, HttpServletResponse response) throws Exception {
		File file = new File("C:\\Users\\13615\\Desktop\\Prince\\1.pdf");
		List<DownloadFile> downloadFiles = new ArrayList<>();
		DownloadFile download1 = new DownloadFile();
		download1.setFile(FileUtils.readFileToByteArray(file));
		download1.setFileName("test.pdf");
		downloadFiles.add(download1);
		response.setHeader(FILE_HEADER,
				  String.format(DOWNLOAD_FILE_PROPERTY_NAME, "download.zip"));
		response.setContentType(MIME_TYPE);
		OutputStream output = response.getOutputStream();
		ZipOutputStream zipOutputStream = new ZipOutputStream(output);

		for (DownloadFile download : downloadFiles) {
			createZipEntry(zipOutputStream, download.getFileName(), download.getFile());
		}
		zipOutputStream.close();
		output.flush();
		output.close();

	}

	private void createZipEntry(ZipOutputStream zipOutputStream, String filename, byte[] content) throws Exception {
		ZipEntry entry = new ZipEntry(filename);
		zipOutputStream.putNextEntry(entry);
		zipOutputStream.write(content);
		zipOutputStream.closeEntry();
	}

}
