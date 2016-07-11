package com.trigl.domain;

import com.trigl.constant.VvCode;


/**
 * JsonResponse工厂类
 * @author 白鑫
 *	2016年7月11日 下午12:02:11
 */
public class JsonResponseFactory {
	
	/**
	 * get request success response
	 * @return JsonResponse code:0
	 */
	public static JsonResponse getSuccessJsonResp(){
		return new JsonResponse(VvCode.SUCCESS,VvCode.GetErrorDesc(VvCode.SUCCESS));
	}
	
	/**
	 * get request inner error response
	 * @return JsonResponse code:401
	 */
	public static JsonResponse getInnerErrorJsonResp(){
		return new JsonResponse(VvCode.INNER_ERROR,VvCode.GetErrorDesc(VvCode.INNER_ERROR));
	}
	
	/**
	 * get request wrong password response
	 * @return JsonResponse code:403
	 */
	public static JsonResponse getWrongPwdJsonResp(){
		return new JsonResponse(VvCode.WRONG_PASSWORD,VvCode.GetErrorDesc(VvCode.WRONG_PASSWORD));
	}
	/**
	 * mac地址不正确
	 * @return
	 */
	public static JsonResponse getWrongMacJsonResp(){
		return new JsonResponse(VvCode.NO_MACADDR,VvCode.GetErrorDesc(VvCode.NO_MACADDR));
	}
	
	/**
	 * get request no pri response
	 * @return JsonResponse code:402
	 */
	public static JsonResponse getNoPRIJsonResp(){
		return new JsonResponse(VvCode.NO_PRI,VvCode.GetErrorDesc(VvCode.NO_PRI));
	}
	
	public static JsonResponse getUserExistJsonResp(){
		return new JsonResponse(VvCode.USER_EXISTS,VvCode.GetErrorDesc(VvCode.USER_EXISTS));
	}
	
	public static JsonResponse getObjExistJsonResp(){
		return new JsonResponse(VvCode.OBJ_EXISTS,VvCode.GetErrorDesc(VvCode.OBJ_EXISTS));
	}
	
	public static JsonResponse getObjNotExistJsonResp(){
		return new JsonResponse(VvCode.OBJ_NOT_EXISTS,VvCode.GetErrorDesc(VvCode.OBJ_NOT_EXISTS));
	}
	
	public static JsonResponse getUserNotExistJsonResp(){
		return new JsonResponse(VvCode.USER_NOT_EXISTS,VvCode.GetErrorDesc(VvCode.USER_NOT_EXISTS));
	}
	
	public static JsonResponse getIllegalArgumentResp(){
		return new JsonResponse(VvCode.ILLEGAL_ARGUMENT,VvCode.GetErrorDesc(VvCode.ILLEGAL_ARGUMENT));
	}

	public static JsonResponse getIllegalArgumentResp(String alterString){
		return new JsonResponse(VvCode.ILLEGAL_ARGUMENT,alterString);
	}
	public static JsonResponse getInnerErrorJsonResp(Exception e) {
		return new JsonResponse(VvCode.INNER_ERROR,VvCode.GetErrorDesc(VvCode.INNER_ERROR),e,e.toString());
	}
	public static JsonResponse getRepeatData(){
		return new JsonResponse(VvCode.REPEAT_DATA,VvCode.GetErrorDesc(VvCode.REPEAT_DATA));
	}
	public static JsonResponse getError(String errorMessage){
		return new JsonResponse(VvCode.DEFALUT_ERROR,errorMessage);
	}
}
