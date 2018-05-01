package com.hm.utils;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.OSSException;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.aliyun.openservices.oss.model.PutObjectResult;

public class FileUtil {
	
	/**
     * 删除文件夹下的所有文件
     * @param oldPath
     */
    public static void deleteFile(File oldPath) {
	    if (oldPath.isDirectory()) {
		    File[] files = oldPath.listFiles();
		    for (File file : files) {
		     deleteFile(file);
		    }
	    }
	    oldPath.delete();
    }
    
	/**
	 * 用户上传后自动生成文件名及按时间生成文件路径
	 * @param file 上传的文件
	 * @param filedir 文件跟路径
	 * @param filename 上传的文件名称
	 * @return 返回生成的文件的路径
	 * @throws ClientException 
	 * @throws OSSException 
	 */
	public static String saveFile(File file, String filedir,
			String urlFilePath, String filename) throws OSSException, ClientException {
		String filePath = filedir + File.separator + urlFilePath;
		if (!new File(filePath).exists()) {
			new File(filePath).mkdirs();
		}
		String lstr = Long.toString(System.currentTimeMillis());
		String fileName = lstr.substring(7) + "."
				+ ApplicationUtil.getFileSuffix(filename);
		String key = "261kofj0t3fzifelgntbl4na";
		String secret = "jzi1A++HwGkgVeu0KhxjnVm+qw4=";
		OSSClient client = new OSSClient(key, secret);
		String bucketName = "ehuipic";
		String fn = "";
		if(ApplicationUtil.getFileSuffix(filename).equalsIgnoreCase("jpg")
		   ||ApplicationUtil.getFileSuffix(filename).equalsIgnoreCase("gif")
		   ||ApplicationUtil.getFileSuffix(filename).equalsIgnoreCase("png")
		   ||ApplicationUtil.getFileSuffix(filename).equalsIgnoreCase("jpeg")) {
			
			fn = (urlFilePath+fileName).substring(1);
		}else {
			fn = (urlFilePath+fileName).substring(1);
			bucketName = "etalkfile";
		}
		InputStream content;
		try {
			content = new FileInputStream(file);
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(file.length());
			PutObjectResult result = client.putObject(bucketName, fn, content, meta);
			System.out.println(result.getETag());
			File savedFile = new File(filePath, fileName);
			file.renameTo(savedFile);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		return urlFilePath + fileName;
	}
	
	 public static File downloadFile(String remoteFilePath, String localFilePath) {
        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File f = new File(localFilePath);
        try
        {
            urlfile = new URL("http://file.ehui.net"+remoteFilePath);
            httpUrl = (HttpURLConnection)urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(f));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1)
            {
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
            httpUrl.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                bis.close();
                bos.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
       return f;
    }
	 
	 
	public static String saveFile(File file,
			String urlFilePath, String filename) throws OSSException, ClientException, IOException {
		String lstr = Long.toString(System.currentTimeMillis());
		String fileName = lstr.substring(7) + "."
				+ ApplicationUtil.getFileSuffix(filename);
		String key = "261kofj0t3fzifelgntbl4na";
		String secret = "jzi1A++HwGkgVeu0KhxjnVm+qw4=";
		OSSClient client = new OSSClient(key, secret);
		String bucketName = "ehuipic";
		String fn = "";
		if(ApplicationUtil.getFileSuffix(filename).equalsIgnoreCase("jpg")
		   ||ApplicationUtil.getFileSuffix(filename).equalsIgnoreCase("gif")
		   ||ApplicationUtil.getFileSuffix(filename).equalsIgnoreCase("png")
		   ||ApplicationUtil.getFileSuffix(filename).equalsIgnoreCase("jpeg")) {
			
			fn = (urlFilePath+fileName).substring(1);
		}else {
			bucketName = "ehuifile";
			fn = (urlFilePath+fileName).substring(1);
		}
		InputStream content;
		try {
			content = new FileInputStream(file);
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(file.length());
			PutObjectResult result = client.putObject(bucketName, fn, content, meta);
			System.out.println(result.getETag());
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		return urlFilePath + fileName;
	}
	
	public static String saveDocFile(File file, String filedir,
			String urlFilePath, String filename) throws OSSException, ClientException {
		String filePath = filedir + File.separator + urlFilePath;
		if (!new File(filePath).exists()) {
			new File(filePath).mkdirs();
		}
		String lstr = Long.toString(System.currentTimeMillis());
		String fileName = lstr.substring(7) + "."
				+ ApplicationUtil.getFileSuffix(filename);
		String key = "261kofj0t3fzifelgntbl4na";
		String secret = "jzi1A++HwGkgVeu0KhxjnVm+qw4=";
		OSSClient client = new OSSClient(key, secret);
		String bucketName = "ehuifile";
		String fn = (urlFilePath+fileName).substring(1);
		InputStream content;
		try {
			content = new FileInputStream(file);
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(file.length());
			PutObjectResult result = client.putObject(bucketName, fn, content, meta);
			System.out.println(result.getETag());
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		return urlFilePath + fileName;
	}

	/**
	 * 新建目录
	 * 
	 * @param folderPath
	 *            String 如 c:/fqf
	 * @return boolean
	 */
	public static void newFolder(String folderPath) {
		try {
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.mkdir();
			}
		} catch (Exception e) {
			System.out.println("新建目录操作出错 ");
			e.printStackTrace();
		}
	}

	/**
	 * 新建文件
	 * 
	 * @param filePathAndName 文件路径及名称 如c:/fqf.txt
	 * @param fileContent 文件内容
	 * @return boolean
	 */
	public static void newFile(String filePathAndName, String fileContent) {

		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			FileWriter resultFile = new FileWriter(myFilePath);
			PrintWriter myFile = new PrintWriter(resultFile);
			String strContent = fileContent;
			myFile.println(strContent);
			resultFile.close();

		} catch (Exception e) {
			System.out.println("新建目录操作出错 ");
			e.printStackTrace();

		}

	}

	/**
	 * 删除文件
	 * @param filePathAndName 文件路径及名称 如c:/fqf.txt
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			java.io.File myDelFile = new java.io.File(filePath);
			myDelFile.delete();

		} catch (Exception e) {
			System.out.println("删除文件操作出错 ");
			e.printStackTrace();

		}

	}

	/**
	 * 删除文件夹
	 * 
	 * @param filePathAndName
	 *            String 文件夹路径及名称 如c:/fqf
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹

		} catch (Exception e) {
			System.out.println("删除文件夹操作出错 ");
			e.printStackTrace();

		}

	}

	/**
	 * 删除文件夹里面的所有文件
	 * 
	 * @param path
	 *            String 文件夹路径 如 c:/fqf
	 */
	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/ " + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/ " + tempList[i]);// 再删除空文件夹
			}
		}
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	@SuppressWarnings("unused")
	public static void copyFile(String oldPath, String newPath) {
		InputStream inStream = null;
		FileOutputStream fs = null;
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				inStream = new FileInputStream(oldPath); // 读入原文件
				fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
				if (fs != null) {
					fs.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 */
	public static void copyFolder(String oldPath, String newPath) {

		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/ " + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/ " + file[i], newPath + "/ "
							+ file[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("复制整个文件夹内容操作出错 ");
			e.printStackTrace();

		}

	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/fqf.txt
	 * @param newPath
	 *            String 如：d:/fqf.txt
	 */
	public static void moveFile(String oldPath, String newPath) {
		copyFile(oldPath, newPath);
		delFile(oldPath);

	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/fqf.txt
	 * @param newPath
	 *            String 如：d:/fqf.txt
	 */
	public static void moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);

	}

	public static void createFile(String filename, byte[] bytes) {
		FileOutputStream output;
		try {
			File myFilePath = new File(filename);
			if (!myFilePath.getParentFile().exists()) {
				myFilePath.getParentFile().mkdirs();
			}
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			output = new FileOutputStream(filename);
			output.write(bytes);
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	public static void createFile(String filename, byte[] bytes,String name,String uname) {
		FileOutputStream output;
		try {
			if(name!=null) {
				String key = "261kofj0t3fzifelgntbl4na";
				String secret = "jzi1A++HwGkgVeu0KhxjnVm+qw4=";
				String bucketName = "ehuipic";
				String fn = "";
				if(ApplicationUtil.getFileSuffix(filename).equalsIgnoreCase("jpg")
				   ||ApplicationUtil.getFileSuffix(filename).equalsIgnoreCase("gif")
				   ||ApplicationUtil.getFileSuffix(filename).equalsIgnoreCase("png")
				   ||ApplicationUtil.getFileSuffix(filename).equalsIgnoreCase("jpeg")) {
					
					fn = (uname+name).substring(1);
				}else {
					fn = (uname+name).substring(1);
					bucketName = "ehuifile";
				}
				OSSClient client = new OSSClient(key, secret);
				InputStream content = new ByteArrayInputStream(bytes); 
				ObjectMetadata meta = new ObjectMetadata();
				meta.setContentLength(bytes.length);
				PutObjectResult result = client.putObject(bucketName, fn, content, meta);
				System.out.println("SUCCESS:"+result.getETag());
				//myFilePath.delete();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public static byte[] getBytesFile(String filename) {
		try {
			File file = new File(filename);
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(filename);
				if (fis != null) {
					int len = fis.available();
					byte[] bytes = new byte[len];
					fis.read(bytes);
					return bytes;
				}
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return null;
	}

	public static void download(String urlString, String filename)
			throws Exception {
		URL url = new URL(urlString);
		URLConnection con = url.openConnection();
		InputStream is = con.getInputStream();
		byte[] bs = new byte[1024];
		int len;
		File myFilePath = new File(filename);
		if (!myFilePath.getParentFile().exists()) {
			myFilePath.getParentFile().mkdirs();
		}
		if (!myFilePath.exists()) {
			myFilePath.createNewFile();
		}
		OutputStream os = new FileOutputStream(filename);
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		os.close();
		is.close();
	}

	public static void html2image(String htmlstr, String filePath) {
		// <span style='font-family:SimSun;'></span>
		htmlstr = "<div style='font-family:SimSun;'>" + htmlstr
				+ "<br/><br/><hr></hr><div align='right'>内容来自易会</div></div>";
		// HtmlImageGenerator imageGenerator = new HtmlImageGenerator(440, 20);
		//HtmlImageGenerator imageGenerator = new HtmlImageGenerator(440, 20);
		// HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		// imageGenerator.getSize().setSize(440, 100);
		//imageGenerator.loadHtml(htmlstr);
		//imageGenerator.saveAsImage(filePath);
	}

	/**
	 * html代码转换pdf
	 * 
	 * @param text
	 * @param filePath
	 * @throws DocumentException
	 * @throws IOException
	 */
//	public static void html2PDF(String text, String filePath)
//			throws DocumentException, IOException {
//		try {
//			File file = new File(filePath);
//			if (!file.getParentFile().exists()) {
//				file.getParentFile().mkdirs();
//			}
//			if (!file.exists()) {
//				file.createNewFile();
//			}
//			String outputFile = filePath;
//			OutputStream os = new FileOutputStream(outputFile);
//			ITextRenderer renderer = new ITextRenderer();
//			ITextFontResolver fontResolver = renderer.getFontResolver();
//			StringBuffer html = new StringBuffer();
//			// DOCTYPE 必需写否则类似于 这样的字符解析会出现错误
//			html
//					.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
//			html
//					.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">")
//					.append("<head>")
//					.append(
//							"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />")
//					.append(
//							"<style type=\"text/css\" mce_bogus=\"1\">body {font-family: SimSun;}"
//									+ "" + "</style>").append("</head>")
//					.append("<body>");
//			html.append(text);
//			html.append("</body></html>");
//			renderer.setDocumentFromString(html.toString());
//			renderer.layout();
//			renderer.createPDF(os);
//			os.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
