package com.trigl.cache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.trigl.entity.User;

/**
 * 从request中获取session信息
 * @author	白鑫
 * @date	2016年7月7日 上午8:09:25
 */
public class SessionUtil {
	
	/**
	 * 获取登录用户
	 * @param request
	 * @return
	 */
	public static User getUserLogin(HttpServletRequest request){
		return (User)request.getSession().getAttribute("user");
	}
	
	public static User getUserLogin(HttpSession session){
		return (User)session.getAttribute("user");
	}
	
}
