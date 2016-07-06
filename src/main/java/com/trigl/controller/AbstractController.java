package com.trigl.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.trigl.config.Config;
import com.trigl.config.WebConfigLoader;

/**
 * 控制层公用的一些方法
 * @author 白鑫
 *	2016年7月6日 下午7:04:04
 */
public abstract class AbstractController {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	protected Config config;
	
	public AbstractController() {
		config = WebConfigLoader.getConfig();
	}

	/**
	 * 重定向
	 * @param response
	 * @param path
	 */
	protected void redirectToPage(HttpServletResponse response, String path) {
		String context = config.getConfig("context");
		try {
			logger.debug("RedirectPath:"+context + path);
			response.sendRedirect(context + path);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 获取request中的ip
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
