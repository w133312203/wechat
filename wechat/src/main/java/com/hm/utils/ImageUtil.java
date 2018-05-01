package com.hm.utils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.springframework.web.multipart.MultipartFile;


public class ImageUtil {
	
	public static String uploadImage(MultipartFile imageFile) {
		
		String fileName = imageFile.getOriginalFilename();
		int spotIndex = fileName.lastIndexOf(".");
		fileName = System.currentTimeMillis()+fileName.substring(spotIndex);
		Calendar cd = Calendar.getInstance();
		int year = cd.get(Calendar.YEAR);
		String month = "";
		if(cd.get(Calendar.MONTH) + 1<10) {
			month = "0"+(cd.get(Calendar.MONTH) + 1);
		}else {
			month = ""+(cd.get(Calendar.MONTH) + 1);
		}
		String day = "";
		if(cd.get(Calendar.DATE)<10) {
			day= "0"+cd.get(Calendar.DATE);
		}else {
			day = ""+cd.get(Calendar.DATE);
		}
        String dayPath = month+day;
		
		
        String dirPath="/hm/image/"+year+"/"+dayPath+"/";
		File dirFile=new File(dirPath);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		String filePath = dirPath+fileName;
		File file=new File(filePath);
		try {
			imageFile.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return year+"/"+dayPath+"/"+fileName;
	}
	
}
