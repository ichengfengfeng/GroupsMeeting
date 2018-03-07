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
    
    <title>个人中心</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	function download(){
	
	   $.ajax({
              type: "POST",
              url: "<%=basePath%>user/DownLoadSvl",
              data: "name=John&location=Boston",
              success: function(msg){
              alert( "Data Saved: " + msg );
                }
         }); 
	
	
	
	}
	
	</script>
  </head>
  
  <body >
					<p>个人订阅记录>></p>
				<div  style="width: 800px;height: 500px;overflow: auto; background-color:F1E1FF ">
     <table border = "1">
     <tr><td>申请时间</td><td>会议室名称</td><td>使用时间</td><td>下载文件</td></tr>
     <c:forEach var ="meeting" items = "${meetings}">
     <tr>
          <td>${meeting.applytime}</td> <td>${meeting.roomname}</td><td>使用时间：${meeting.begintime}-${meeting.endtime}</td><td> <a href = "<%=basePath%>user/DownLoadSvl?mno=${meeting.mno}">下载</a></td>
     </tr>
     </c:forEach>
     </table>
     </div>
  </body>
</html>
