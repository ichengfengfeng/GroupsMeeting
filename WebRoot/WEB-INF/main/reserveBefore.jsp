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
    
    <title>会议室预订</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/index/index.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/react.css" />
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>	
	<script type="text/javascript">   
    		
    		$(function(){
    		$('#tt').datagrid({
				title:'职员列表',							
				height:350,
				toolbar: '#tb',
				nowrap: false,
				striped: true,												
				url: null,
				rownumbers:true,
				pagination:true,
				singleSelect:true,				
				pageList:[10],
				loadMsg : 'processing, please wait …',			
				columns:[[					
					{field:'empname',title:'姓名',width:50},					
					{field:'empno',title:'员工编号'},                    								
					{field:'email',title:'邮箱',width:100},					
					{field:'job',title:'岗位描述',width:100}
					
				]]					
			});	
			
			$('#tt').datagrid({
					onClickRow: function(index,row){					    				    
					    editIndex = index;		
						$('#tt').datagrid('acceptChanges');
						$('#tt').datagrid('selectRow',index);	
					}
			   });			
    	});     	
    	
    	
    	function queryPerson(){    		   
		   
		    var uname = document.getElementById("puname").value;		    		       
		    var url = '<%=basePath%>user/EmployeeListSvl?empname=' + uname  ;		          
		    $('#tt').datagrid('options').url = url;		    
		    $('#tt').datagrid('reload');	    				
     	}   
     	
     	
     	
     	 
     	function addit(){

		   $.messager.confirm('添加提示','你确定要添加吗？', function(r){
				if (r){
					if (editIndex == undefined){return}
					var empno = $('#tt').datagrid('getRows')[editIndex]['empno'];					
					$.ajax({
						  type: "POST",	
						  url: "<%=basePath%>user/AddEmpSessionSvl?empno=" + empno ,						  
						  success: function(msg){						    
						     if(msg == 1){
						        alert("添加成功");
						     }else{
						        alert(msg);   
						     }      	        
					 	  },
					 	  error: function(msg){
					 	      alert("系统异常，请联系管理员");
					 	  }
					});
										
				}
			});			
		}     
		      	
		function openw(){
		
		$('#w').window('open');
		$('#w').window('vcenter');
		}
    </script>
    <script type="text/javascript">
    $(function(){
    		$('#joinemps').datagrid({
				title:'参会人员列表',							
				height:350,
				toolbar: '#tb1',
				nowrap: false,
				striped: true,												
				url: null,
				rownumbers:true,
				pagination:true,
				singleSelect:true,				
				pageList:[10],
				loadMsg : 'processing, please wait …',			
				columns:[[					
					{field:'empname',title:'姓名',width:50},					
					{field:'empno',title:'员工编号'},                    								
					{field:'email',title:'邮箱',width:100},					
					{field:'job',title:'岗位描述',width:100}
					
				]]					
			});	
			$('#joinemps').datagrid({
					onClickRow: function(index,row){					    				    
					    editIndex = index;		
						$('#tt').datagrid('acceptChanges');
						$('#tt').datagrid('selectRow',index);	
					}
			   });	
		
    	});  
    	
    	
      function listJoinEmployee(){
     	     var url = '<%=basePath%>user/JoinEmpListSvl';	
     	     $('#joinemps').datagrid('options').url = url;		    
		     $('#joinemps').datagrid('reload');	
     	}
     function removeit(){

		   $.messager.confirm('删除提示','你确定要删除吗？', function(r){
				if (r){
					if (editIndex == undefined){return}
					var empno = $('#joinemps').datagrid('getRows')[editIndex]['empno'];					
					$.ajax({
						  type: "POST",
						  dataType:'TEXT',
						  url: "<%=basePath%>user/RemoveEmpSessionSvl?empno=" + empno ,						  
						  success: function(msg){						    
						     if(msg == 1){
						        alert("删除成功");
						        listJoinEmployee();
						     }else{
						        alert(msg);  
						        listJoinEmployee(); 
						     }      	        
					 	  },
					 	  error: function(msg){
					 	      alert("系统异常，请联系管理员");
					 	  }
					});
										
				}
			});	
		}     
     
    
    </script>
    
    <script type="text/javascript">
	    function tijiao(){
	    
	    
	        $(".fileinput").each(function(){
			    var v = $(this).filebox('getValue');			   
				if(v == ''||v==null){	  			
		  			var sp = $(this).next().next();  			
		  			$(sp).html("*必填");
		  			$(this).focus();		  		
				}else{
					var sp = $(this).next().next();	
					var fileAccept = $(this).filebox('getValue').split(".")[1];    //获取上传文件的后缀  
					if( fileAccept!="rar" ){  
	    				$(sp).html("只能上传*.rar压缩文件！");	    				
					} else{
						$(sp).html("");
						alert("文件类型校验OK");
					}		
				}
		     });
		     
		     var myform = document.getElementById("myform");
		     myform.submit();
	    }
	</script>
     
    
	

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
			
			<div style ="height:200px;width: 850px;background-color: #fff;margin: 0 auto;">
               <table align = "center" width="900px" height="200px">
                <tr><td rowspan = "7" width="340px" height="200px" align = "center"><img src = "http://10.0.19.155:8080/img/102.png"></td> <td> <h3>${rdto.roomno}</h3></td></tr>
                <tr><td>${rdto.rooname} </td></tr>
                <tr> <td> 适合${spec}人规模会议</td></tr>
                <tr> <td>会议室设备: <c:forEach var = "device" items = "${rdto.devices}"> ${device.dname},</c:forEach></td></tr>
                <tr> <td rowspan = "3"> <font color="red"size="2">请至少提前一小时预订</font> <br>
                                        <font color="red"size="2">预订处理时间为10分钟，请注意短信确认预订信息 </font><br>
                                        <font color="red"size="2"> 预订成功无法退订</font>
                      </td>
                </tr>
                </table>
             </div>
  <div class="serch_list">
				<div class="item">
					<span class="tip_line"></span>
					<span>会议订阅</span>
				</div>
				<br>
  <form action = "<%=basePath%>user/ReserveRoomSvl" method = "post" id = "myform"  enctype="multipart/form-data">
  <div  >
              &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;开始时间:&nbsp;&nbsp;&nbsp;&nbsp;<font size = "2"style="background: #DDDDFF"><c:if test="${idate=='today'}"> 今天： </c:if><c:if test="${idate=='tomorrow'}">明天：</c:if></font>&nbsp;&nbsp; 
              <select name= "begintime">
              <option value = "08:00:00" selected>08:00:00</option>
              <option value = "09:00:00">09:00:00</option>
              <option value = "10:00:00">10:00:00</option>
              <option value = "11:00:00">11:00:00</option>
              <option value = "12:00:00">12:00:00</option>
              <option value = "13:00:00">13:00:00</option>
              <option value = "14:00:00">14:00:00</option>
              <option value = "15:00:00">15:00:00</option>
              <option value = "16:00:00">16:00:00</option>
              <option value = "17:00:00">17:00:00</option>
              <option value = "18:00:00">18:00:00</option>
              </select> 
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    结束时间:&nbsp;&nbsp;
          <select name= "endtime">
              <option value = "08:00:00" selected>08:00:00</option>
              <option value = "09:00:00">09:00:00</option>
              <option value = "10:00:00">10:00:00</option>
              <option value = "11:00:00">11:00:00</option>
              <option value = "12:00:00">12:00:00</option>
              <option value = "13:00:00">13:00:00</option>
              <option value = "14:00:00">14:00:00</option>
              <option value = "15:00:00">15:00:00</option>
              <option value = "16:00:00">16:00:00</option>
              <option value = "17:00:00">17:00:00</option>
              <option value = "18:00:00">18:00:00</option>
              </select> <font size= "2" color="red">${msg}</font>
         <input type = "hidden" value = "${date}" name="date"><input type = "hidden" value = "${rdto.roomno}" name="roomno"><input type = "hidden" value = "${idate}" name="idate">
         <div>
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;会议主题:
     </div>
     <div align ="center">
     <textarea rows = "1" cols = "40"  name = "theme" id= "theme"></textarea>
     </div>
    </div>
    <div align=center >
    <div align = "left">
      &nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;添加参会人员 :
    </div>
         <div id="tb1" style="height:auto" >
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="listJoinEmployee()">一键查询</a>		
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">一键删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add', plain:true" onclick="openw()">一键导入</a>
			
		</div>
    	<table id="joinemps" width="45%"  ></table>
    	
    </div>		
	
	<div id="w" class="easyui-window" title="员工查询" data-options="modal:'false',closed:'true'" style="width:500px;height:450px;padding:10px;">
	  <div align=center >
    	  <div>
    		用户名：<input type="text" id="puname" name="puname" /> 
    		      <input type="button" onclick="queryPerson()" value="查询"/>
    	  </div>
    	
    	  <table id="tt" width="400px"  ></table>
    	  <div id="tb" style="height:auto">	
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addit()">添加</a>
		  </div>
      </div>	
	</div>
    <br>
            &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
             资料上传：&nbsp;
             <input class="easyui-filebox fileinput" name="files" data-options="prompt:'上传压缩文件...'"  style="width:300">(*.rar)&nbsp;<span style="color:red">${fmsg}</span><br>
            &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
            联系方式： &nbsp; <input id="vv" type= "text" name= "tell">  <br>
            <div align = "center">
            <a href="<%=basePath%>user/CancleReserveSvl" ><input type="button" value = "取&nbsp;&nbsp;&nbsp;&nbsp;消" style="background-color: #CA8EFF"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type = "button" style="background-color: #CA8EFF" value = "提&nbsp;&nbsp;&nbsp;&nbsp;交" onclick="tijiao()"> 
            </div>
    
    
  </form>
 </div>
  </div>
  </body>
</html>
