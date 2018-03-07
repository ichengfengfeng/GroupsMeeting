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
    
    <title>������Ԥ��</title>
    
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
				title:'ְԱ�б�',							
				height:350,
				toolbar: '#tb',
				nowrap: false,
				striped: true,												
				url: null,
				rownumbers:true,
				pagination:true,
				singleSelect:true,				
				pageList:[10],
				loadMsg : 'processing, please wait ��',			
				columns:[[					
					{field:'empname',title:'����',width:50},					
					{field:'empno',title:'Ա�����'},                    								
					{field:'email',title:'����',width:100},					
					{field:'job',title:'��λ����',width:100}
					
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

		   $.messager.confirm('�����ʾ','��ȷ��Ҫ�����', function(r){
				if (r){
					if (editIndex == undefined){return}
					var empno = $('#tt').datagrid('getRows')[editIndex]['empno'];					
					$.ajax({
						  type: "POST",	
						  url: "<%=basePath%>user/AddEmpSessionSvl?empno=" + empno ,						  
						  success: function(msg){						    
						     if(msg == 1){
						        alert("��ӳɹ�");
						     }else{
						        alert(msg);   
						     }      	        
					 	  },
					 	  error: function(msg){
					 	      alert("ϵͳ�쳣������ϵ����Ա");
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
				title:'�λ���Ա�б�',							
				height:350,
				toolbar: '#tb1',
				nowrap: false,
				striped: true,												
				url: null,
				rownumbers:true,
				pagination:true,
				singleSelect:true,				
				pageList:[10],
				loadMsg : 'processing, please wait ��',			
				columns:[[					
					{field:'empname',title:'����',width:50},					
					{field:'empno',title:'Ա�����'},                    								
					{field:'email',title:'����',width:100},					
					{field:'job',title:'��λ����',width:100}
					
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

		   $.messager.confirm('ɾ����ʾ','��ȷ��Ҫɾ����', function(r){
				if (r){
					if (editIndex == undefined){return}
					var empno = $('#joinemps').datagrid('getRows')[editIndex]['empno'];					
					$.ajax({
						  type: "POST",
						  dataType:'TEXT',
						  url: "<%=basePath%>user/RemoveEmpSessionSvl?empno=" + empno ,						  
						  success: function(msg){						    
						     if(msg == 1){
						        alert("ɾ���ɹ�");
						        listJoinEmployee();
						     }else{
						        alert(msg);  
						        listJoinEmployee(); 
						     }      	        
					 	  },
					 	  error: function(msg){
					 	      alert("ϵͳ�쳣������ϵ����Ա");
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
		  			$(sp).html("*����");
		  			$(this).focus();		  		
				}else{
					var sp = $(this).next().next();	
					var fileAccept = $(this).filebox('getValue').split(".")[1];    //��ȡ�ϴ��ļ��ĺ�׺  
					if( fileAccept!="rar" ){  
	    				$(sp).html("ֻ���ϴ�*.rarѹ���ļ���");	    				
					} else{
						$(sp).html("");
						alert("�ļ�����У��OK");
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
			
			<div style ="height:200px;width: 850px;background-color: #fff;margin: 0 auto;">
               <table align = "center" width="900px" height="200px">
                <tr><td rowspan = "7" width="340px" height="200px" align = "center"><img src = "http://10.0.19.155:8080/img/102.png"></td> <td> <h3>${rdto.roomno}</h3></td></tr>
                <tr><td>${rdto.rooname} </td></tr>
                <tr> <td> �ʺ�${spec}�˹�ģ����</td></tr>
                <tr> <td>�������豸: <c:forEach var = "device" items = "${rdto.devices}"> ${device.dname},</c:forEach></td></tr>
                <tr> <td rowspan = "3"> <font color="red"size="2">��������ǰһСʱԤ��</font> <br>
                                        <font color="red"size="2">Ԥ������ʱ��Ϊ10���ӣ���ע�����ȷ��Ԥ����Ϣ </font><br>
                                        <font color="red"size="2"> Ԥ���ɹ��޷��˶�</font>
                      </td>
                </tr>
                </table>
             </div>
  <div class="serch_list">
				<div class="item">
					<span class="tip_line"></span>
					<span>���鶩��</span>
				</div>
				<br>
  <form action = "<%=basePath%>user/ReserveRoomSvl" method = "post" id = "myform"  enctype="multipart/form-data">
  <div  >
              &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;��ʼʱ��:&nbsp;&nbsp;&nbsp;&nbsp;<font size = "2"style="background: #DDDDFF"><c:if test="${idate=='today'}"> ���죺 </c:if><c:if test="${idate=='tomorrow'}">���죺</c:if></font>&nbsp;&nbsp; 
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
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    ����ʱ��:&nbsp;&nbsp;
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
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;��������:
     </div>
     <div align ="center">
     <textarea rows = "1" cols = "40"  name = "theme" id= "theme"></textarea>
     </div>
    </div>
    <div align=center >
    <div align = "left">
      &nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;��Ӳλ���Ա :
    </div>
         <div id="tb1" style="height:auto" >
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="listJoinEmployee()">һ����ѯ</a>		
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">һ��ɾ��</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add', plain:true" onclick="openw()">һ������</a>
			
		</div>
    	<table id="joinemps" width="45%"  ></table>
    	
    </div>		
	
	<div id="w" class="easyui-window" title="Ա����ѯ" data-options="modal:'false',closed:'true'" style="width:500px;height:450px;padding:10px;">
	  <div align=center >
    	  <div>
    		�û�����<input type="text" id="puname" name="puname" /> 
    		      <input type="button" onclick="queryPerson()" value="��ѯ"/>
    	  </div>
    	
    	  <table id="tt" width="400px"  ></table>
    	  <div id="tb" style="height:auto">	
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addit()">���</a>
		  </div>
      </div>	
	</div>
    <br>
            &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
             �����ϴ���&nbsp;
             <input class="easyui-filebox fileinput" name="files" data-options="prompt:'�ϴ�ѹ���ļ�...'"  style="width:300">(*.rar)&nbsp;<span style="color:red">${fmsg}</span><br>
            &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
            ��ϵ��ʽ�� &nbsp; <input id="vv" type= "text" name= "tell">  <br>
            <div align = "center">
            <a href="<%=basePath%>user/CancleReserveSvl" ><input type="button" value = "ȡ&nbsp;&nbsp;&nbsp;&nbsp;��" style="background-color: #CA8EFF"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type = "button" style="background-color: #CA8EFF" value = "��&nbsp;&nbsp;&nbsp;&nbsp;��" onclick="tijiao()"> 
            </div>
    
    
  </form>
 </div>
  </div>
  </body>
</html>
