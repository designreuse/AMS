package com.trigl.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.trigl.domain.JsonResponse;
import com.trigl.domain.JsonResponseFactory;
import com.trigl.entity.User;
import com.trigl.service.UserService;
import com.trigl.view.AMSView;

/**
 * 用户Controller
 * 
 * @author 白鑫 2016年7月8日 上午11:30:09
 */
@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	@Resource
	private UserService userService;
	
	@RequestMapping("/tologin")
	public ModelAndView tologin() {
		
		logger.info("————进入用户登录页面————");
		ModelAndView mv = new AMSView("index");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/login")
	public JsonResponse login(HttpServletRequest req,HttpServletResponse resp, User user){
		
		logger.info("————验证账户密码————");
		JsonResponse jr = JsonResponseFactory.getSuccessJsonResp();
		user.setLoginip(getIpAddr(req));
		try {
			user = userService.login(user);
			if (null != user) {
				// 将登录信息存入session中
				req.getSession().setAttribute("user", user);
			} else {
				jr = JsonResponseFactory.getWrongPwdJsonResp(); 
			}
		} catch (Exception e) {
			jr = JsonResponseFactory.getInnerErrorJsonResp(e);
			logger.error(e.getMessage(),e);
		}
		return jr;
	}
}
