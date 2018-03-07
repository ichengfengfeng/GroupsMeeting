<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>预订信息浏览</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/react.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/timeList.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/index/index.css" />

  </head>
  
  <body class="min_width">
  <div class="content">
  <!--头部标题部分-->
			<header>
				<div class="logo">
					<p><img src="<%=basePath%>img/logo.png" alt="logo" width="60%" height="30%"/></p>
					<p><img src="<%=basePath%>img/zhongruanguoji.png" alt="logo" width="60%" height="20%"/></p>
				</div>
				<div class="title">
					<h1>会议室系统</h1>
				</div>
				<div class="headjsp">
				<jsp:include page="head.jsp"></jsp:include>
				</div>
			</header>
	 <div style ="height:200px;width: 850px;background-color: #fff;margin: 0 auto;  ">
	   <div  style = "margin: 0px 20px;padding: 10px 0px;">
			
			<jsp:include page="empInfo.jsp"></jsp:include>
	   </div>		
     </div>
     
   <div class="serch_list">
				<div class="item">
					<span class="tip_line"></span>
					<span>预定情况查询</span>
				</div>
				 <div  style = "margin: 0px 20px;padding: 10px 0px;">
				  <div>
				     <a href="<%=basePath%>user/PersonalMeetingSvl"    target="myiframe">个人会议室订阅记录查询>></a><b>|</b>
				     <a href="<%=basePath%>user/DeptMeetingSvl" target="myiframe">部门会议室订阅记录查询>></a>
				  </div>
				  
				  <div >
				  <iframe name="myiframe" width="99%" height="99%" scrolling="no" frameborder="0"></iframe>
				  </div>
				  </div>
  </div>
  </div>
  </body>
</html>
