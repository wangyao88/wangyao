<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>趋势图</title>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="<%=basePath%>anyChart/anychart.js"></script>
	<script language="javascript" type="text/javascript" src="<%=basePath%>My97DatePicker/WdatePicker.js"></script>
</head> 
<body>
    <div id="search">
       <input class="Wdate" type="text" id="date" name="date" onClick="WdatePicker({dateFmt:'yyyyMM'})"> <font color=red></font>
       <select id="param" name="param" size="1">
			<option value="creditorInfo" selected>债权人信息
			<option value="bidsInfo">账户信息
			<option value="orderInfo">订单信息
			<option value="contactInfo">联系人信息
			<option value="entryUuid">债务人信息
			<option value="repayFlow">还账流水
			<option value="phoneList">联系人通讯录
			<option value="rpcPayIn">RC_PAY.in
			<option value="rpcPayOut">RPC_PAY.out
		</select>
       <button id="search" onclick="search();">查询</button>
    </div>
    <table width="100%" height="80%">
       <tr>
         <td>
             <div id="numLineContainer">
  
             </div>
         </td>
         <td>
             <div id="sizeLineContainer">
  
             </div>
         </td>
       </tr>
    </table> 
    
    <script type="text/javascript">
    function search(){
        var date = $("#date").val();
        var param = $("#param").val();
        var url = "/cuishou/overDue/getOverDueByDate.do?date="+date+"&type=num&param="+param;
        $.ajax({
        	  url: url,
        	  type:'POST',
        	  success:function(data,textStatus){
	                var obj = eval('(' + data + ')');
	                initNumLineChart(obj);
	           }
        });

        var url1 = "/cuishou/overDue/getOverDueByDate.do?date="+date+"&type=size&param="+param;
        $.ajax({
        	  url: url1,
        	  type:'POST',
        	  success:function(data,textStatus){
	                var obj = eval('(' + data + ')');
	                initSizeLineChart(obj);
	           }
        });
    }

    function initNumLineChart(data){
    	var JSONData = {
    		    "charts": {
    		        "chart": {
        	            "plot_type":"CategorizedVertical",
    					"data_plot_settings" : {
    					   // "enable_3d_mode" : "true",
    					    "default_series_type":"Line",
    					    "line_series":{
    	                        "tooltip_settings":{
    	                              "enabled": "true",
    	                              "format":"大小:{%YValue}{numDecimals:2,trailingZeros:false}"
    	                        },
    	                        "label_settings":{
    	                              "enabled": "true",
    	                              "position":{
                                          "anchor": "Center",
                                          "halign": "Center",
                                          "valign": "Center"
    	                              },
    	                              "format":"{%YValue}{numDecimals:2,trailingZeros:false}",
    	                              "font":{
                                         "size":"14"
    		                          }
    	                        }
    					    }
    					 },
    		            "chart_settings": {
    						"legend":{ 
    							"enabled":"true",
    							"title":{ 
    								"enabled":"false"
    							},
    						},
    		                "title": {
    		                    "text": "数量数据趋势图"
    		                },
    		                "axes":{
    		                    "x_axis": {
    		                	    "enabled":"true",
    		                        "title":{
    		                            "text": ""
    		                         },
    		                         "labels":{
			                             "rotation": "-45"
			                         }
    		                    },
    		                    "y_axis": {
    		                	    "enabled":"true",
    		                        "title":{
    		                            "text": ""
    		                         },
    		                         "labels":{
    		                        	 "format":"{%Value}{numDecimals:2,trailingZeros:false}"
    			                     }
    		                    }
    		   			    }
    		            },
    		            "data": data
    		        }
    		    }
        
    		};

        var chart = new AnyChart('<%=basePath%>anyChart/swf/AnyChart.swf'); 
        chart.width = 600; 
        chart.height = 450; 
        chart.setJSData(JSONData);
        chart.write("numLineContainer"); 
    }

    function initSizeLineChart(data){
    	var JSONData = {
    		    "charts": {
    		        "chart": {
        	            "plot_type":"CategorizedVertical",
    					"data_plot_settings" : {
    					   // "enable_3d_mode" : "true",
    					    "default_series_type":"Line",
    					    "line_series":{
    	                        "tooltip_settings":{
    	                              "enabled": "true",
    	                              "format":"大小:{%YValue}{numDecimals:2,trailingZeros:false}"
    	                        },
    	                        "label_settings":{
    	                              "enabled": "true",
    	                              "position":{
                                          "anchor": "Center",
                                          "halign": "Center",
                                          "valign": "Center"
    	                              },
    	                              "format":"{%YValue}{numDecimals:2,trailingZeros:false}",
    	                              "font":{
                                         "size":"14"
    		                          }
    	                        }
    					    }
    					 },
    		            "chart_settings": {
    						"legend":{ 
    							"enabled":"true",
    							"title":{ 
    								"enabled":"false"
    							},
    						},
    		                "title": {
    		                    "text": "大小数据趋势图"
    		                },
    		                "axes":{
    		                    "x_axis": {
    		                	    "enabled":"true",
    		                        "title":{
    		                            "text": ""
    		                         },
    		                         "labels":{
			                             "rotation": "-45"
			                         }
    		                    },
    		                    "y_axis": {
    		                	    "enabled":"true",
    		                        "title":{
    		                            "text": ""
    		                         },
    		                         "labels":{
    		                        	 "format":"{%Value}{numDecimals:2,trailingZeros:false}"
    			                     }
    		                    }
    		   			    }
    		            },
    		            "data": data
    		        }
    		    }
        
    		};

        var chart = new AnyChart('<%=basePath%>anyChart/swf/AnyChart.swf'); 
        chart.width = 600; 
        chart.height = 450; 
        chart.setJSData(JSONData);
        chart.write("sizeLineContainer"); 
    }
     
    </script> 
</body> 
</html>
