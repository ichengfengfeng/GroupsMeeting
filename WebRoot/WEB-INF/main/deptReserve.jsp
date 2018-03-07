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
    
    <title>所属部门预订信息</title>
    
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
  <p>所属部门订阅记录>></p>
				<div  style="width: 800px;height: 500px;overflow: auto; background-color:F1E1FF ">
     <table border = "1">
     <tr><td>申请时间</td><td>会议室名称</td><td>预订人员</td><td>使用时间</td></tr>
     <c:forEach var ="dmDto" items = "${dmDtos}">
     <tr>
         <td>${dmDto.applytime}</td> <td>${dmDto.rname}</td><td>${dmDto.empname}</td><td>使用时间：${dmDto.begintime}-${dmDto.endtime}</td>
     </tr>
     </c:forEach>
     </table>
     </div>
  </body>
</html>
