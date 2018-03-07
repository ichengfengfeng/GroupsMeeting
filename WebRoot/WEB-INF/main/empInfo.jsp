<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>员工个人信息</title>
    
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
  <table >
       <tr><td rowspan = "5" > <img src = "http://10.0.19.155:8080/img/headportrait.png"></td><td align = "left">&nbsp;&nbsp;&nbsp;姓名：${emp.empname}</td></tr>
       <tr><td align = "left">&nbsp;&nbsp;&nbsp;工号：   ${emp.empno}</td></tr>
       <tr><td align = "left">&nbsp;&nbsp;&nbsp;职位：${emp.job}</td></tr>
       <tr><td align = "left">&nbsp;&nbsp;&nbsp;部门：${emp.deptname}</td></tr>
       <tr><td align = "left">&nbsp;&nbsp;&nbsp;邮箱：${emp.email}</td></tr>
  </table>
  </body>
</html>
