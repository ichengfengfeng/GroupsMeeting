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
    
    <title>xx������</title>
    
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
	   nowDate.innerHTML = year + "��" + month + "��" + day + "��";
	 }
	</script>
	
  </head>
  <body class="min_width" onload="date()">
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
            <!--������ѯ����-->
             <form action="<%=basePath%>user/MainSvl" method = "post">
			<div class="search_form">
				<div class="item">
					<span class="tip_line"></span>
					<span>ɸѡ</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					���죺<span  id = "nowdate" ></span>
				</div>
				<div class="item">
					<div class="form_item">
						<span>ʱ&nbsp;&nbsp;&nbsp;�䣺</span>
						<c:if test="${idate=='today'}">
						<input type="radio" name="date"  value="today" checked>����
                        <input type = "radio" name = "date"  value = "tomorrow">����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    </c:if>
					    <c:if test="${idate=='tomorrow'}">
					    <input type="radio" name="date"  value="today" >����
                        <input type = "radio" name = "date"  value = "tomorrow" checked>����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    </c:if>
					    <span>��&nbsp;&nbsp;&nbsp;��</span>
					    <select  name= "rtype">
					    <option value = "">����</option>
					    <option value = "10"  <c:if test="${rtype==10}">selected</c:if>>6-10��</option>
					    <option value = "20" <c:if test="${rtype==20}">selected</c:if>>10-20��</option>
					    <option value = "30" <c:if test="${rtype==30}">selected</c:if>>20-30��</option>
					    <option value = "40" <c:if test="${rtype==40}">selected</c:if>>30������</option>
					    </select>
					    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    <span>ʱ��Σ�</span>
							<input type="text" id="start_time" name="begintime" class="start_time" value= "${begintime}"/>
							<span>��</span>
							<input type="text" id="end_time" name="endtime" class="end_time" value= "${endtime}"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type ="submit" size ="60px" value = "��&nbsp;&nbsp;&nbsp;&nbsp;��" style="background-color: #CA8EFF"> 
					</div>
				</div>
				
			</div>
			 </form>
			<!--������Ԥ�����-->
			<div class="serch_list">
				<div class="item">
					<span class="tip_line"></span>
					<span>Ԥ��</span>
				</div>
				<div class="item scroll">
				<table border = "1" style='border-collapse: collapse' width="99%">
                         <tr><td rowspan = "2"  align = "center">������</td><td rowspan ="2"  align = "center">���</td><td colspan = "600" align = "center" >Ԥ�����</td><td rowspan = "2">����</td></tr>
                         <tr><td colspan = "60" align = "center">08-09</td><td colspan = "60" align = "center">09-10</td><td colspan = "60" align = "center">10-11</td><td colspan = "60" align = "center">11-12</td><td colspan = "60" align = "center">12-13</td><td colspan = "60" align = "center">13-14</td><td colspan = "60" align = "center">14-15</td><td colspan = "60"align = "center">15-16</td><td colspan = "60"align = "center">16-17</td><td colspan = "60"align = "center">17-18</td></tr>
                         <c:forEach var = "room" items= "${rooms}">
                          <tr>
                            <td align = "center">${room.roomno}</td><td align = "center">${room.roomsize}�˼�</td><c:forEach var= "td" items="${room.td}">${td}</c:forEach> <td align = "center"><a href = "<%=basePath%>user/RoomInfoSvl?roomno=${room.roomno}&&date=${date}&&idate=${idate}"><font size="2">Ԥ��</font></a></td>
                          </tr>
                          </c:forEach>
               </table>
				</div>
	        </div>
	       
	</div>
	
	
	                    
  </body>
</html>
