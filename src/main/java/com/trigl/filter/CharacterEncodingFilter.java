package com.trigl.filter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.ClassUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 自定义编码过滤器 默认按照配置的编码进行编码 ajax按照utf-8进行编码
 * 超时重定向到登录页面
 * @author 白鑫
 *	2016年7月6日 下午6:56:48
 */
public class CharacterEncodingFilter extends OncePerRequestFilter {
	private String encoding;

	private boolean forceEncoding = false;

	private static final String[] ignoreUrlArray = { "/index", "/user/login", "/user/tologin" };

	// Determine whether the Servlet 2.4
	// HttpServletResponse.setCharacterEncoding(String)
	// method is available, for use in the "doFilterInternal" implementation.
	private final static boolean responseSetCharacterEncodingAvailable = ClassUtils
			.hasMethod(HttpServletResponse.class, "setCharacterEncoding",
					new Class[] { String.class });

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		if (this.forceEncoding && responseSetCharacterEncodingAvailable) {
			response.setCharacterEncoding(this.encoding);
		}
		// ajax请求 session过期时 返回字符串，如果是ajax请求响应头会有，x-requested-with；（如果是ajax请求或者是导出操作，则都进行session超时判断）
		if ((request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")))
		{
			if (request.getSession().getAttribute("user") == null) {
				String requestUrl = request.getRequestURI().substring(
						request.getRequestURI().indexOf("/", 1));
				boolean isMainUrl = false;
				// 如果是忽略url直接返回true
				for (String ignoreUrl : ignoreUrlArray) {
					if (ignoreUrl.equals(requestUrl)) {
						isMainUrl = true;
					}
				}
				if (isMainUrl) {
					filterChain.doFilter(request, response);
				} else {
					redirectLoginPage(request, response);
				}
			} else {
				filterChain.doFilter(request, response);
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}

	/**
	 * 未登录用户重定向到登录页面
	 * 
	 * @param request
	 * @param response
	 */
	public void redirectLoginPage(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head></head>");
			out.println("<body><script>");
			out.println("var wnd=this.window;");
			out.println("while(1){");
			out.println("if(wnd==wnd.parent){");
			out.println("break;");
			out.println("}else");
			out.println("wnd=wnd.parent;");
			out.println("}");
			out.println("wnd.location.href='" + request.getContextPath()
					+ "/index'");
			out.println("</script>");
			out.println("</body></html>");
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public boolean isForceEncoding() {
		return forceEncoding;
	}

	public void setForceEncoding(boolean forceEncoding) {
		this.forceEncoding = forceEncoding;
	}

}
