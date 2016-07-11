package com.trigl.domain;

import com.trigl.constant.VvCode;

/**
 * 用于平台一般的ajax 返回信息
 * @author 白鑫
 *	2016年7月11日 上午11:43:22
 */
public class JsonResponse {
	/**错误代码*/
	private int code;
	
	/**返回信息显示*/
	private String msg="";
	
	/**需要跳转的页面*/
	private String nextUrl="";
	
	/**存储的数据对象*/
	private Object obj;

	private String exceptionMsg;
	
	private Exception e;
	
	public JsonResponse(){
		
	}
	
	public JsonResponse(int code){
		super();
		this.code=code;
		this.msg=VvCode.GetErrorDesc(code);
	}
	
	public JsonResponse(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public JsonResponse(int code, String msg, String nextUrl) {
		super();
		this.code = code;
		this.msg = msg;
		this.nextUrl = nextUrl;
	}

	public JsonResponse(int code, String msg, Exception e2,
			String exceptionMsg) {
		this.code = code;
		this.msg = msg;
		this.e = e2;
		this.exceptionMsg = exceptionMsg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getNextUrl() {
		return nextUrl;
	}

	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}
	
	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isOk(){
		if(code==VvCode.SUCCESS){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isError(){
		return !isOk();
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	public Exception getE() {
		return e;
	}

	public void setE(Exception e) {
		this.e = e;
	}
	
	
}
