package com.hm.utils;

import java.io.File;

public class FileUploadUtil {
	
	private static final long partSize = 5 * 1024 * 1024L;
	
	/**
     * 断点续传分片数量判断
     */
    public static int getPartCount(File file){
        long fileLength = file.length();
        int partCount = (int) (fileLength / partSize); // 分片个数，不能超过10000
        if (fileLength % partSize != 0) {
            partCount++;
        }
        if (partCount > 10000) {
            throw new RuntimeException("分片不能超过10000，请修改每片大小");
        } else {
            return partCount;
        }
    }
    
    public static void main(String arg0[]) {
    	File file = new File("/Users/magic/Desktop/GA4.mov");
    	Integer partCount = getPartCount(file);

    	for (int i = 0; i < partCount; i++) {
            long startPos = i * partSize;
            long curPartSize = (i + 1 == partCount) ? (file.length() - startPos) : partSize;
            System.out.println("startPos:"+startPos+" i:"+i);
            System.out.println("curPartSize:"+curPartSize);
            //executorService.execute(new PartUploader(file, startPos, curPartSize, i + 1, uploadId));
        }
    }

}
