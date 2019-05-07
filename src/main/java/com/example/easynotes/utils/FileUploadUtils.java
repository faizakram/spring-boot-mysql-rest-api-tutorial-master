package com.example.easynotes.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * Utility for uploading files
 */
@Component
public class FileUploadUtils {
	private static String FILE_STORAGE_PATH = "E:/Files/";

	public String uploadMediaFile(byte[] file, String fileName) {
		try {
			File dir = new File(FILE_STORAGE_PATH);
			if (!dir.exists())
				dir.mkdirs();
			Files.write(Paths.get(dir.getAbsolutePath() + File.separator + fileName), file);
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void uploadOnFileSystem(Map<String, MultipartFile> maps) throws IOException {
		for (Map.Entry<String, MultipartFile> entry : maps.entrySet()) {
			uploadMediaFile(entry.getValue().getBytes(), entry.getKey());
	    }
		
	}

}
