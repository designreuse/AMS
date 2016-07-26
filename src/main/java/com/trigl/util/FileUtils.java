package com.trigl.util;

import java.io.File;
import java.text.DecimalFormat;

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
	
	public static void main(String[] args) {
		try {
			//System.out.println(getFileSize("C:/Users/hzyq/Desktop/ssf_v420_noad-NNA20075.apk"));
			System.out.println(new File("C:/Users/hzyq/Desktop/ssf_v420_noad-NNA20075.apk").length());
			System.out.println(formetFileSize(getDirSize(new File("C:/Users/hzyq/Desktop/ssf_v420_noad-NNA20075.apk"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
