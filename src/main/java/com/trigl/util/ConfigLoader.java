package com.trigl.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 读取配置文件
 * @author 白鑫
 *	2016年7月26日 下午6:52:25
 */
public class ConfigLoader {

	private final String[] names = new String[]{"cifTxCfg.properties","cif-crm.properties","respCode.properties"};
	private Logger logger = Logger.getLogger(ConfigLoader.class);
	private Properties pro = new Properties();
	private static final ConfigLoader config = new ConfigLoader();

	private ConfigLoader() {
		InputStream in = null;
		try {
			for(int i =0;i<names.length;i++){
				 in = this.getClass().getClassLoader().getResourceAsStream(
						 names[i]);
				 pro.load(in);
			}
		} catch (IOException e) {
			logger.debug(e.getMessage(), e);
			logger.info("------------读取配置文件失败！");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public synchronized static ConfigLoader getInstance() {
		return config;
	}

	public String getValue(String key) {
		String temp = pro.getProperty(key);
		return temp;
	}

	public String getValue(String key, Object[] objects) {
		String temp = pro.getProperty(key);
		return MessageFormat.format(temp, objects);
	}

	public String getValue(String key, String defaultValue) {
		String temp = pro.getProperty(key, defaultValue);
		return temp;
	}

	public static void main(String[] args) {
		// 通过如下方式获取值
		String temp = ConfigLoader.getInstance().getValue("host");
		System.out.println(temp);
	}

}