<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>������Ԥ��</title>
    
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
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/index/index.css" />
  </head>
  
  <body class="min_width">
  <div class="content">
			<!--ͷ�����ⲿ��-->
			<header>
				<div class="logo">
					<p><img src="<%=basePath%>img/logo.png" alt="logo" width="60%" height="30%"/></p>
					<p><img src="<%=basePath%>img/zhongruanguoji.png" alt="logo" width="60%" height="20%"/></p>
				</div>
				<div class="title">
					<h1>������ϵͳ</h1>
				</div>
				<div class="headjsp">
				<jsp:include page="head.jsp"></jsp:include>
				</div>
			</header>
			<div style ="height:150px;width: 850px;background-color: #fff;margin: 0 auto;">
			<div  style = "margin: 0px 20px;padding: 10px 0px;">
 <img src = "<%=basePath%>img/ok.png">&nbsp;&nbsp;<font size = "6">|Ԥ���ɹ�����ȴ�ϵͳȷ�ϡ�</font><br><br><br>
 
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href = "<%=basePath%>user/MainSvl" > <input type ="button" value = "<<ȷ��" style="background-color: #CA8EFF" width="50px" height="50px" ></a>
  </div>
  </div>
  <br>
<div style ="height:300px;width: 800px;background-color: #fff;margin:  30 auto;">
<div  style = "margin: 0px 20px;padding: 10px 0px;">
					<span class="tip_line"></span>
					<span>���ļ�¼</span>
				</div>
   <br><br><br>
    <div align = "center">
     <a href= "<%=basePath%>user/EmpInfoSvl"><input type = "button" value="���˶��ļ�¼��ѯ>"    ></a>
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     <a href= "<%=basePath%>user/EmpInfoSvl"><input type = "button" value="���в��Ż����¼��ѯ>" ></a>
     </div>
  </div>
  </div>
  </body>
</html> 



