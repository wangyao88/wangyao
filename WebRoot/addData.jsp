<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增逾期数据</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<style>
	    td{
	        text-align:center
	    }
	</style>
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.js"></script>
	<script language="javascript" type="text/javascript" src="<%=basePath%>My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	     function add(){
		      var type = $("#type").val();
              var action = "/cuishou/overDue/addSizeData.do";
              if(type == 'num'){
            	  action = "/cuishou/overDue/addNumData.do";
              }

              //序列化表单元素，返回json数据  
              var params = $("#addDataForm").serializeArray();  
                
              $.ajax({  
                  type:"POST",  
                  url:action,  
                  data:params,  
                  success:function(data){  
                      alert("添加数据成功");
                      $("#addDataForm").reset();
                  },  
                  error:function(e) {  
                      alert("出错："+e);  
                  }  
              });  
		 }
	
	</script>

  </head>
  
  <body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li>数据概览</li>
    <li><a href="/cuishou/overDue/gotoAddData.do" target="rightFrame">新增数据</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>数据信息</span></div>
    
    <ul class="forminfo">
	    <form id="addDataForm"  method="post" action="#">
		    <li>
		         <label>数据类型</label>
		         <select id="type" name="type" size="1" style="width:135px;">
					<option  value="num" selected>数量
					<option  value="size">大小
				 </select>
		         <i>不允许空值</i>
		    </li>
		    <li><label>日期</label><input class="Wdate" type="text" id="date" name="date" onClick="WdatePicker({dateFmt:'yyyyMMdd'})"> <font color=red></font><i>不允许空值</i></li>
		    <li><label>债权人信息</label><input name="creditorInfo" type="text" class="dfinput" value="1"/><i>不允许空值</i></li>
		    <li><label>账户信息</label><input name="bidsInfo" type="text" class="dfinput" value="3" /><i>不允许空值</i></li>
		    <li><label>订单信息</label><input name="orderInfo" type="text" class="dfinput"  value="6"/><i>不允许空值</i></li>
		    <li><label>联系人信息</label><input name="contactInfo" type="text" class="dfinput" value="1"/><i>不允许空值</i></li>
		    <li><label>债务人信息</label><input name="entryUuid" type="text" class="dfinput" value="9"/><i>不允许空值</i></li>
		    <li><label>还账流水</label><input name="repayFlow" type="text" class="dfinput" value="7"/><i>不允许空值</i></li>
		    <li><label>联系人通讯录</label><input name="phoneList" type="text" class="dfinput" value="5"/><i>不允许空值</i></li>
		    <li><label>RRC_PAY.in</label><input name="rpcPayIn" type="text" class="dfinput" value="3"/><i>不允许空值</i></li>
		    <li><label>RPC_PAY.out</label><input name="rpcPayOut" type="text" class="dfinput" value="9"/><i>不允许空值</i></li>
		    <li><label>&nbsp;</label><input name="submit" type="button" class="btn" value="确认保存" onClick="add();" /></li>
	    </form>
    </ul>
    
    
    </div>


</body>

</html>
