package com.wisdom.commons;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FilesUpload {
	public  static String filePart(MultipartFile file) {
		String fileName=file.getOriginalFilename();
		String physicsPath="D:\\workSpace\\WisdomCommunity\\src\\main\\webapp\\headicon\\"+fileName;
		String visitPath=null;
		try {
			file.transferTo(new File(physicsPath));
			visitPath="http://localhost:8080/WisdomCommunity/headicon/"+fileName;
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return visitPath;
	}
}
