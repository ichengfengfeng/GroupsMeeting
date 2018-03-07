package com.icss.meeting.util;

public class JsonUtil {
	
	/**
	 * ���easyui-datagrid��Ҫ��ʽ��json��
	 * @param total  �ܼ�¼��
	 * @param rows   �е�json��
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
