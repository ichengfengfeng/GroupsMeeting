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
    
    <title>��������Ԥ����Ϣ</title>
    
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
  <p>�������Ŷ��ļ�¼>></p>
				<div  style="width: 800px;height: 500px;overflow: auto; background-color:F1E1FF ">
     <table border = "1">
     <tr><td>����ʱ��</td><td>����������</td><td>Ԥ����Ա</td><td>ʹ��ʱ��</td></tr>
     <c:forEach var ="dmDto" items = "${dmDtos}">
     <tr>
         <td>${dmDto.applytime}</td> <td>${dmDto.rname}</td><td>${dmDto.empname}</td><td>ʹ��ʱ�䣺${dmDto.begintime}-${dmDto.endtime}</td>
     </tr>
     </c:forEach>
     </table>
     </div>
  </body>
</html>
