package com.trigl.controller;

import java.awt.Menu;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页Controller
 */
@Controller
@RequestMapping("/index")
public class IndexController extends AbstractController {
	
	@Resource
	MenuService menuService;
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest req,HttpServletResponse resp, Page<TUserInfo> page,TUserInfo query){
		
		logger.info("————进入首页————");
		ModelAndView mv = new VVView("/acms");
		try {
			List<Menu> firstMenuList = menuService.selectFirstMenu();
			for (Menu menu : firstMenuList) {
				menu.setChildMenus(menuService.selectChildMenu(menu.getMenuid()));
			}
			mv.addObject("firstMenu", firstMenuList);
		} catch (Exception e) {
			logger.error(e);
		}
		return mv;
	}
}
