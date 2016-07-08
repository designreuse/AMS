package com.trigl.view;

import org.springframework.web.servlet.ModelAndView;

import com.trigl.config.Config;
import com.trigl.config.WebConfigLoader;
import com.trigl.util.PathUtil;

/**
 * 自定义View
 * @author 白鑫
 *	2016年7月8日 上午11:09:32
 */
public class AMSView extends ModelAndView {
	
	public AMSView() {
		super();
		setProperties();
	}
	
	public AMSView(String viewname) {
		super(viewname);
		setProperties();
	}

	/**
	 * 初始化路径信息以便前台使用
	 */
	private void setProperties() {
		Config config = WebConfigLoader.getConfig();
		String contextPath = config.getConfig("context");
		String staticcontent = config.getConfig("staticcontent");
		String cdnPath = config.getConfig("cdn.context");
		this.addObject("path", contextPath);
		this.addObject("spath", staticcontent);
		this.addObject("cdnpath", cdnPath);
		this.addObject("httpImg",PathUtil.getDirPath(PathUtil.HTTP_IMG));
	}
}
