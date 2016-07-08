package com.trigl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.trigl.view.AMSView;

/**
 * 首页Controller
 */
@Controller
@RequestMapping("/index")
public class IndexController extends AbstractController {
	
	@RequestMapping
	public ModelAndView index(){
		
		logger.info("————进入首页————");
		ModelAndView mv = new AMSView("/ams");
		return mv;
	}
}
