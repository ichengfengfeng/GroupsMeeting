
package com.icss.meeting.entity;

public final class TurnPagePara {
	public int CurrentPageNo=1;         		//当前要显示的页码-----输入项-----------------页号从1开始
    public int OnePageCount=8;       	    	//每页要显示的记录数(默认值为8)---输入项
	public int RecordAllCount;        	    	//记录总数			----返回值
    public int PageAllCount;          			//共有多少页     		----返回值
}
