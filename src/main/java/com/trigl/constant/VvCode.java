package com.trigl.constant;

import java.util.HashMap;

/**
 * 请求返回的系统码
 * @author 白鑫
 *	2016年7月11日 上午11:45:13
 */
public class VvCode {
	private final static HashMap<Integer , String> _codeDict ;
	
	public static final int SUCCESS=0;//请求成功
	public static final int INNER_ERROR=401;//内部错误
	public static final int NO_PRI=402;//无此权限
	public static final int WRONG_PASSWORD=403;//用户名或密码错误
	public static final int USER_EXISTS=410;//用户名已存在
	public static final int OBJ_EXISTS=411;//对象已存在
	public static final int OBJ_NOT_EXISTS=413;//对象已存在
	public static final int USER_NOT_EXISTS=412;//用户名不存在
	public static final int NULL_ARGUMENT=701;//传入的参数为null
	public static final int ILLEGAL_ARGUMENT=702;//不合法的参数
	public static final int NOT_WXUSER=801;//不是微信使用
	public static final int REPEAT_DATA=802;
	public static final int NO_MACADDR=888;//mac地址没有绑定
	
	public static final int DEFALUT_ERROR=1;
	static {
		Object[] codeList = new Object[]{
			SUCCESS    ,"请求成功",
			INNER_ERROR,"系统内部错误",
			NULL_ARGUMENT,"传入的参数为空或null",
			ILLEGAL_ARGUMENT,"不合法的参数",
			NO_PRI,"无此权限",
			WRONG_PASSWORD,"用户名或密码错误",
			USER_EXISTS,"用户已存在",
			OBJ_EXISTS,"对象已存在",
			USER_NOT_EXISTS,"用户不存在",
			NOT_WXUSER,"不是微信用户",
			OBJ_NOT_EXISTS,"对象不存在",
			REPEAT_DATA,"重复数据",
			NO_MACADDR,"没有绑定mac地址"
		};
		_codeDict = new HashMap<Integer , String>();
		for(int i = 0 ; i < codeList.length / 2 ; i++ ){
			_codeDict.put(Integer.parseInt(codeList[i*2].toString()),
						codeList[i*2+1].toString());
		}			
	}
	
	public static String GetErrorDesc(int code){
		if(!_codeDict.containsKey(code))
			return "";
		else
			return _codeDict.get(code);
	}
	
	public static String GetErrorDesc(String code){
		return GetErrorDesc(Integer.parseInt(code.trim()));
	}
}
