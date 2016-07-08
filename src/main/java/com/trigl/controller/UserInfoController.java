package com.trigl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.trigl.view.AMSView;

/**
 * 登录用户Controller
 * 
 * @author 白鑫 2016年7月8日 上午11:30:09
 */
@Controller
@RequestMapping("/user")
public class UserInfoController extends AbstractController {

	@RequestMapping("/tologin")
	public ModelAndView tologin() {
		
		logger.info("————进入用户登录页面————");
		ModelAndView mv = new AMSView("index");
		return mv;
	}
}
