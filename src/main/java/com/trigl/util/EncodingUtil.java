package com.trigl.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 字符编码工具类
 * @author 白鑫
 *	2016年7月26日 下午7:18:01
 */
public class EncodingUtil {

	/**
	 * 获取指定文件的字符编码类型
	 * @param filePath
	 * @return
	 */
	@SuppressWarnings("resource")
	public static String getFileCharset(String filePath) {
		String charset = "GBK";
		byte[] f3b = new byte[3];
		try {
			boolean check = false;
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(filePath));
			bis.mark(0);
			int read = bis.read(f3b, 0, 3);
			if (read == -1) {
				return charset;
			} else if (f3b[0] == (byte) 0xFF && f3b[1] == (byte) 0xFE) {
				charset = "UTF-16LE";
				check = true;
			} else if (f3b[0] == (byte) 0xEF && f3b[1] == (byte) 0xBB
					&& f3b[2] == (byte) 0xBF) {
				charset = "UTF-8";
				check = true;
			}
			bis.reset();
			if (!check) {
				while ((read = bis.read()) != -1) {
					if (read >= 0xF0)
						break;

					if (0x80 <= read && read <= 0xBF)
						break;// GBK

					if (0xC0 <= read && read <= 0xDF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF)
							continue;
						else
							break;

					} else if (0xE0 <= read && read <= 0xEF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) {
							read = bis.read();
							if (0x80 <= read && read <= 0xBF) {
								charset = "UTF-8";
								break;
							} else
								break;
						} else
							break;
					}
				}
			}
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return charset;
	}
	
	/**
	 * 转换文件字符编码
	 * 步骤是先读取源文件的内容，存放到StringBuffer里面，
	 * 然后删除源文件，再重新new一个与源文件名一样的文件，再以另一种编码形式存放。
	 * @param path 文件绝对路径
	 * @param desCharset 想要转换的编码
	 * @throws Exception
	 */
	public static void convertEncoding (String path,String desCharset) throws Exception {
		File srcFile = new File(path);
		String srcCharset = getFileCharset(path); // 获取文件本来的字符编码
		System.out.println(path + "的字符编码是" + srcCharset + "，要转成的字符编码是" + desCharset);
		if(srcCharset.equalsIgnoreCase(desCharset)) {
			System.out.println("编码相同，无需转换");
		} else {
			System.out.println("编码不同，需要转换");
			InputStream in = new FileInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(in, srcCharset));
			StringBuffer sb = new StringBuffer();
			String s1;
			while((s1 = br.readLine()) != null) {
				String s = URLEncoder.encode(s1, desCharset);
				sb.append(s + "\r\n"); // 一行+回车
			}
			br.close();
			srcFile.delete(); // 删除原来的文件
			// 重新以新编码写入文件并返回值
			File desFile = new File(path); // 重新建原来的文件
			desFile.createNewFile();
			OutputStream out = new FileOutputStream(desFile);
			OutputStreamWriter writer = new OutputStreamWriter(out, desCharset);
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write(URLDecoder.decode(sb.toString(), desCharset));
			bw.flush(); // 刷新到文件中
			bw.close();
		}
	}
	
	public static void main(String[] args) {
		try {
			String charset = getFileCharset("C:/Users/三浪/Desktop/99701200000_KFDB_DSMC_0402_20160304_I_4321_0001.XML");
			System.out.println(charset);
			convertEncoding("C:/Users/三浪/Desktop/1.txt", "utf-16");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
