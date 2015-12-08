<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.overdue.model.*" %>
<%@ page import="java.util.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<OverDue> datas = new ArrayList<OverDue>();
if(request.getAttribute("overDueList") != null){
	datas = (List<OverDue>)request.getAttribute("overDueList");
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>逾期数据列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="<%=basePath%>My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).ready(function(){
     $(".click").click(function(){
  		$(".tip").fadeIn(200);
    });
  
    $(".tiptop a").click(function(){
	    $(".tip").fadeOut(200);
	});

    $(".sure").click(function(){
        $(".tip").fadeOut(100);
    });

    $(".cancel").click(function(){
  	    $(".tip").fadeOut(100);
    });
});

function dataList(){
    var date = $("#date").val();
    if(date == ""){
       alert('请选择日期');
       return false;
    }
    var type = $("#type").val();
    var url = "/cuishou/overDue/dataList.do?date="+date+"&type="+type;
    $.ajax({
  	  url: url,
  	  type:'POST',
  	  success:function(data,textStatus){
	    	var _element = document.getElementById("tbody");
	    	var _parentElement = _element.parentNode;
	        if(_parentElement){
	               _parentElement.removeChild(_element);
	        }
	        var tbody = "<tbody id='tbody'></tbody>";
    	    $("#dataList").append(tbody);
	        $("#tbody").append(data);
      }
  });
    
    
}
</script>


</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li>首页</li>
    <li>数据概览</a></li>
    <li><a href="/cuishou/overDue/gotoDataList.do" target="rightFrame">数据列表</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li><a href="/cuishou/overDue/gotoAddData.do" target="rightFrame"><span><img src="images/t01.png" /></span>添加</a></li>
        <input class="Wdate" type="text" id="date" name="date" onClick="WdatePicker({dateFmt:'yyyyMM'})">
         <select id="type" name="type" size="1" style="width:100px;">
			<option  value="num" selected>数量
			<option  value="size">大小
		 </select>
        <li><a href="javascript:void(0)" onclick="dataList()"><span><img src="images/t04.png" /></span>查询</a></li>
        </ul>
        
    </div>
    
    
    <table class="tablelist" id="dataList">
    	<thead>
	    	<tr>
		        <th>日期</th>
		        <th>债权人信息更新</th>
		        <th>账户信息</th>
		        <th>订单信息</th>
		        <th>联系人信息</th>
		        <th>债务人信息</th>
		        <th>还款流水</th>
		        <th>联系人通讯录</th>
		        <th>RRC_PAY.in</th>
		        <th>RRC_PAY.out</th>
	        </tr>
        </thead>
        <tbody id="tbody">
           <%for(OverDue o : datas){%>
		             <tr>
		            	<td><%=o.getDate()%></td>
		            	<td><%=o.getCreditorInfo()%></td>
		            	<td><%=o.getBidsInfo()%></td>
		            	<td><%=o.getOrderInfo()%></td>
		            	<td><%=o.getContactInfo()%></td>
		            	<td><%=o.getEntryUuid()%></td>
		            	<td><%=o.getRepayFlow()%></td>
		            	<td><%=o.getPhoneList()%></td>
		            	<td><%=o.getRpcPayIn()%></td>
		            	<td><%=o.getRpcPayOut()%></td>
		             </tr> 
	            <%}%>
        </tbody>
    </table>
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>
