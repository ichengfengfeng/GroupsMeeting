<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>head</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
       <c:if test="${user.role==2}">
                      欢迎用户：${user.uname}
       </c:if>
       <c:if test="${user.role==1}">
                      欢迎管理员：${user.uname}
         <a href= "http://10.0.19.179:8080/Meeting">后台</a>    
       </c:if>
       <br>
                   <a href= "<%=basePath%>user/EmpInfoSvl">个人中心</a><b>|</b>
					<a href = "<%=basePath%>user/LogoutSvl">退出</a>
					<a href= "<%=basePath%>user/MainSvl">返回主页</a>
  </body>
</html>
