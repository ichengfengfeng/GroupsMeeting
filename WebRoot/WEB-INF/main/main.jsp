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
    
    <title>xx会议室</title>
    
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
	<script type="text/javascript">
	 function date(){ 
	   var nowDate = document.getElementById("nowdate");
	   var date = new Date();
	   var year =  date.getFullYear();
	   var month = date.getMonth() + 1;
	   var day = date.getDate();
	   nowDate.innerHTML = year + "年" + month + "月" + day + "日";
	 }
	</script>
	
  </head>
  <body class="min_width" onload="date()">
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
            <!--搜索查询部分-->
             <form action="<%=basePath%>user/MainSvl" method = "post">
			<div class="search_form">
				<div class="item">
					<span class="tip_line"></span>
					<span>筛选</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					今天：<span  id = "nowdate" ></span>
				</div>
				<div class="item">
					<div class="form_item">
						<span>时&nbsp;&nbsp;&nbsp;间：</span>
						<c:if test="${idate=='today'}">
						<input type="radio" name="date"  value="today" checked>今天
                        <input type = "radio" name = "date"  value = "tomorrow">明天&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    </c:if>
					    <c:if test="${idate=='tomorrow'}">
					    <input type="radio" name="date"  value="today" >今天
                        <input type = "radio" name = "date"  value = "tomorrow" checked>明天&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    </c:if>
					    <span>规&nbsp;&nbsp;&nbsp;格：</span>
					    <select  name= "rtype">
					    <option value = "">不限</option>
					    <option value = "10"  <c:if test="${rtype==10}">selected</c:if>>6-10人</option>
					    <option value = "20" <c:if test="${rtype==20}">selected</c:if>>10-20人</option>
					    <option value = "30" <c:if test="${rtype==30}">selected</c:if>>20-30人</option>
					    <option value = "40" <c:if test="${rtype==40}">selected</c:if>>30人以上</option>
					    </select>
					    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    <span>时间段：</span>
							<input type="text" id="start_time" name="begintime" class="start_time" value= "${begintime}"/>
							<span>至</span>
							<input type="text" id="end_time" name="endtime" class="end_time" value= "${endtime}"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type ="submit" size ="60px" value = "搜&nbsp;&nbsp;&nbsp;&nbsp;索" style="background-color: #CA8EFF"> 
					</div>
				</div>
				
			</div>
			 </form>
			<!--会议室预定情况-->
			<div class="serch_list">
				<div class="item">
					<span class="tip_line"></span>
					<span>预定</span>
				</div>
				<div class="item scroll">
				<table border = "1" style='border-collapse: collapse' width="99%">
                         <tr><td rowspan = "2"  align = "center">会议室</td><td rowspan ="2"  align = "center">规格</td><td colspan = "600" align = "center" >预定情况</td><td rowspan = "2">操作</td></tr>
                         <tr><td colspan = "60" align = "center">08-09</td><td colspan = "60" align = "center">09-10</td><td colspan = "60" align = "center">10-11</td><td colspan = "60" align = "center">11-12</td><td colspan = "60" align = "center">12-13</td><td colspan = "60" align = "center">13-14</td><td colspan = "60" align = "center">14-15</td><td colspan = "60"align = "center">15-16</td><td colspan = "60"align = "center">16-17</td><td colspan = "60"align = "center">17-18</td></tr>
                         <c:forEach var = "room" items= "${rooms}">
                          <tr>
                            <td align = "center">${room.roomno}</td><td align = "center">${room.roomsize}人间</td><c:forEach var= "td" items="${room.td}">${td}</c:forEach> <td align = "center"><a href = "<%=basePath%>user/RoomInfoSvl?roomno=${room.roomno}&&date=${date}&&idate=${idate}"><font size="2">预订</font></a></td>
                          </tr>
                          </c:forEach>
               </table>
				</div>
	        </div>
	       
	</div>
	
	
	                    
  </body>
</html>
