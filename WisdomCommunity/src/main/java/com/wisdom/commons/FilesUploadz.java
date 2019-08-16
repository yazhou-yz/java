package com.wisdom.commons;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FilesUploadz {
	public static String filePart(String adminName, MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String physicsPath = "D:\\upload\\" + adminName + "_" + fileName;
		String visitPath = null;
		try {
			file.transferTo(new File(physicsPath));
			visitPath = "http://localhost:8080/upload/" + adminName + "_" + fileName;
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return visitPath;
	}
}
