package com.trigl.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 文件处理工具类
 * @author 白鑫
 *	2016年7月26日 上午11:33:25
 */
public class FileUtils {

	private static final int BUFFER = 1024;
	
	/**
	 * 返回文件/文件夹的大小
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static long getDirSize(File file) throws Exception// 取得文件夹大小
	{
		long size = 0;
		if (file.isDirectory()) {
			File flist[] = file.listFiles();
			for (int i = 0; i < flist.length; i++) {
				if (flist[i].isDirectory()) {
					size = size + getDirSize(flist[i]);
				} else {
					size = size + flist[i].length();
				}
			}
		} else {
			size = file.length();
		}
		return size;
	}
	
	/**
	 * 将文件大小转换为适合的单位
	 * @param fileS
	 * @return
	 */
	public static String formetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / BUFFER) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / (BUFFER * BUFFER)) + "M";
        } else {
            fileSizeString = df.format((double) fileS / (BUFFER * BUFFER * BUFFER)) + "G";
        }
        return fileSizeString;
    }
	
	/**
	 * 复制文件
	 * @param source	被复制文件路径
	 * @param target	复制后文件路径
	 * @throws IOException
	 */
	public static void copyFile(String source, String target) throws IOException {
		try (InputStream in = new FileInputStream(source)) {
			try (OutputStream out = new FileOutputStream(target)) {
				byte[] buffer = new byte[4096]; 
				int bytesToRead;
				// 每次读取4096个字节
				while ((bytesToRead = in.read(buffer)) != -1) {
					out.write(buffer, 0, bytesToRead);
				}
			}
		}
	}
	
	/**
	 * 移动某个目录下面的所有文件到目标目录
	 * @param fromPath 要移动的文件所在目录
	 * @param toPath	目标目录
	 */
	public void fileRemove(String fromPath,String toPath){
		File fromFolder = new File(fromPath);
		// fromFolder文件目录下的子文件
		File[] fromFiles=fromFolder.listFiles();
		if(fromFiles==null){
			return;
		}
		File toFolder = new File(toPath);
		if (!toFolder.exists()){
			toFolder.mkdirs();
		}
		
		for(int i=0;i<fromFiles.length;i++){
			File file=fromFiles[i];
			if (file.isDirectory()){
				fileRemove(file.getPath(), toPath+"\\"+file.getName());
			}
			if(file.isFile()){
				File toFile=new File(toFolder+"\\"+file.getName());
				file.renameTo(toFile); // 移动操作，不要先copy再delete
			}
		}
	}
	
	/**
	 * 移动某个具体的文件到目标目录
	 * @param filePath 具体文件路径（非目录）
	 * @param toPath	目标目录
	 */
	public void removeFile(String filePath, String toPath){
		File file = new File(filePath);
		if(file.isDirectory()) {
			return;
		} else {
			File toFile = new File(toPath + File.separatorChar +file.getName());
			file.renameTo(toFile);
		}
	}
	
	/**
	 * 删除文件或目录（所有的文件）
	 * @param path
	 */
	public static void delFile (String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		
		// 如果是文件直接删除
		if(file.isFile()) {
			file.delete();
			return;
		}
		
		// 如果包含子目录递归删除
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			delFile(files[i].getPath());
		}
		
		file.delete(); // 子目录都删除完成最后删除该目录
	}
	
	/**
	 * 解压文件
	 * @param unZipFile 压缩文件绝对路径
	 * @param desPath 解压目录
	 * @return  解压后文件
	 * @throws IOException
	 */
	public File unZipFile(String unZipFile, String desPath) throws IOException {
		FileOutputStream fileOut = null;
		File file = null; // 解压后文件
		System.out.print("解压前文件路径-->" + unZipFile);
		int fileLength = (int) new File(unZipFile).length();
		System.out.println(" | 文件大小-->[" + fileLength+"]字节");
		ZipFile zipfile = new ZipFile(unZipFile);// 获取该zip文件
		Enumeration e = zipfile.entries();
		byte[] buf = new byte[fileLength*2];
		int readedBytes;
		try {
			// 遍历压缩包中所有压缩文件
			while (e.hasMoreElements()) {
				ZipEntry zipEntry = (ZipEntry) e.nextElement();
				file = new File(desPath  + File.separatorChar +  zipEntry.getName());
				if(file.exists()){
					file.delete();
				}
				if (zipEntry.isDirectory()) {
					file.mkdirs();
				} else {
					if (!file.getParentFile().exists()) {
						file.getParentFile().mkdirs();
					}
					fileOut = new FileOutputStream(file.getPath());
					BufferedInputStream b = new BufferedInputStream(zipfile.getInputStream(zipEntry));
					while ((readedBytes = b.read(buf)) > 0) {
						fileOut.write(buf, 0, readedBytes);
					}
					fileOut.close();
				}
			}
			System.out.print("解压后文件路径-->"+file.getPath());
			int fileLength1 = (int) file.length();
			System.out.println(" | 文件大小-->[" + fileLength1+"]字节");
			return file;
		}catch (Exception ioe) {
			ioe.printStackTrace();
			return null;
		}finally{
			zipfile.close();
			fileOut.close();
		}
	}
}
