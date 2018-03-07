<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="<%=basePath%>css/index/main.css" />
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.6.min.js"></script>
	<script type="text/javascript">
	function send(){
	   var uname = document.getElementById("uname").value;
	   var pwd = document.getElementById("password").value;
	   if(uname == null || uname == ""){
	   var unamespan = document.getElementById("unamespan");
	   unamespan.innerHTML = "用户名不能为空 ";
	   return false;
	   }
	   if(pwd == null || pwd == ""){
	   var pwdspan = document.getElementById("pwdspan");
	   pwdspan.innerHTML = "密码不能为空";
	   return false;
	   }
	   
	   if($("#verifyCode").val()==""){
	   	 verifyspan.innerHTML = "校验码不能为空";
	   	 return false;
	   }
	   
	   $.post("login.do",{uname:$("#uname").val(),password:$("#password").val(),verifyCode:$("#verifyCode").val()},
	   		function callback(data){
	   			if(data.toString()=="ok"){
	   			window.location="<%=basePath%>user/MainSvl";
	   			}else{
	   			$("#enter").attr("disabled",false);
	   			verifyFunc();
	   			$("#message").html("<font face=Arial size=3 color=#ff9900 >"+data+"</font>");
	             alert(data);
	   			}
	   });
	}
	function verifyFunc(){
		$("#verifyCode").val("");
		$("#verifyImage").attr("src","<%=basePath%>createMa.jsp?temp="+new Date());
	}
	</script>
  </head>
  <body>  
      <div class="top">
			<div class="top_p">Meeting Room</div>
		</div>
		<div class="main">
			<div class="m_l">
			</div>
			<div class="m_r">
				<table align="center">
				<tr height="50px"></tr>
  				<tr><td>用户名:</td></tr>
  				<tr><td><input type="text" name = "uname" id="uname" style="height: 25px;"/><span  style="color:red" id="unamespan"></span></td></tr>
  				<tr><td>密码:</td></tr>
  				<tr><td><input type="password" name = "password" id="password" style="height: 25px;"/><span style="color:red" id="pwdspan"></span></td></tr>
  				<tr><td>校验码:</td></tr>
  				<tr><td><input type="text" id="verifyCode" style="height: 25px;"/><img id=verifyImage name=idenimage alt="点击下一个" src="createMa.jsp" onclick="verifyFunc();" ></img><span style="color:red" id="verifyspan"></span></td></tr>
  				<tr height="20px"></tr>
  				<tr><td><input type="button" id="enter" onclick="send()" value="&nbsp;&nbsp;&nbsp;&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;&nbsp;&nbsp;&nbsp;" style="height: 25px;" /></td></tr>
  				<tr><td><span style="color: red">${msg}</span></td></tr>
    			</table>  
			</div>
		</div>
		<div class="foot">
			<tr>
			<td>
			<p>内容维护：ICSS-Beijing| Copyright@2017All Rights 
			Reserved | Meeting3版权所有
			<br/><br/> 电话:010-8888666&nbsp;&nbsp; Email:Meeting3@163.com&nbsp; 
			&nbsp;QQ:110119120</p></td>
	    </tr>
		</div>
      
  </body>
</html>
