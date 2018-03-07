package com.icss.meeting.util;

public class JsonUtil {
	
	/**
	 * 获得easyui-datagrid需要格式的json串
	 * @param total  总记录数
	 * @param rows   行的json串
	 * @return
	 */
	public static String getDatagridJSON(int total,String rows){
		
		String json = "{\"total\":" + total + ",\"rows\":";
		if(rows != null){
			json += rows;
		}
		json += "}";
		
		return json;
		
	}

}
