package com.trigl.config;

import java.io.InputStream;
import java.util.Properties;

/**
 * 加载配置信息类
 * @author 白鑫
 *	2016年7月6日 下午7:15:43
 */
public class WebConfigLoader {

	private WebConfigLoader() {
	}

	private static Config config;

	static {
		try {
			config = loadConfig();
		} catch (Exception ex) {
			// quit the system
			ex.printStackTrace();
			System.exit(-1);
		}
	}

	public static Config getConfig() {
		return config;
	}

	private static Config loadConfig() throws Exception {
		InputStream in = null;
		try {
			in = WebConfigLoader.class.getClassLoader().getResourceAsStream("config.properties");
			Properties p = new Properties();
			p.load(in);
			Config con = new Config();
			for (Object k : p.keySet()) {
				String key = (String) k;
				con.setConfig(key, p.getProperty(key));
			}
			return con;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				in.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(-1);
			}
		}
	}
}
