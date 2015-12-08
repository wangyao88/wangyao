package com.overdue.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.overdue.common.utils.IDUtils;
import com.overdue.dao.OverDueDao;
import com.overdue.model.OverDue;
import com.overdue.service.OverDueService;

@Service("overDueServiceImpl")
public class OverDueServiceImpl implements OverDueService{
	
	@Autowired
	@Qualifier("overDueDaoImpl")
	private OverDueDao overDueDaoImpl;

	public String getOverDueByDate(String date, String type, String param) {
		if(date == null || date.equals("")){
			date = getNowDate();
		}
		if(type.equals("num")){
			return getOverDueNumByDate(date,param);
		}else if(type.equals("size")){
			return getOverDueSizeByDate(date,param);
		}
		return null;
	}

	private String getOverDueSizeByDate(String date, String param) {
		String hql = "from OverDueSize o where o.date like ? order by o.date asc";
		List<OverDue> datas = this.getOverDueData(hql,date);
		return congurateData(datas,param,"大小(M)");
	}

	private String getOverDueNumByDate(String date, String param) {
		String hql = "from OverDueNum o where o.date like ? order by o.date asc";
		List<OverDue> datas = this.getOverDueData(hql,date);
		return congurateData(datas,param,"数量");
	}
	
	private String congurateData(List<OverDue> datas, String param, String name) {
		StringBuffer temp = new StringBuffer();
//		 {
//             "series": [
//                 {
//	                    "name": "wqqw",
//                      "point": [
//                         {"name": "Jan", "y": 21,"color":"#F41111"},
//                         {"name": "Feb", "y": 23,"color":"#BF0ECC"},
//                         {"name": "Mar", "y": 10,"color":"#5D0ECC"},
//                         {"name": "Apr", "y": 34,"color":"#0E8FCC"},
//                         {"name": "May", "y": 45,"color":"#ACCC0E"}
//                     ]
//                 }
//             ]
//         }
		temp.append("{")
		    .append("      series: [")
		    .append("         {")
		    .append("              name: '"+name+"',")
		    .append("              point: [");
		for(OverDue o : datas){
			if(param.equals("creditorInfo")){
				temp.append("                 {name: '"+o.getDate()+"', y: "+o.getCreditorInfo()+"},");
			}else if(param.equals("bidsInfo")){
				temp.append("                 {name: '"+o.getDate()+"', y: "+o.getBidsInfo()+"},");
			}else if(param.equals("orderInfo")){
				temp.append("                 {name: '"+o.getDate()+"', y: "+o.getOrderInfo()+"},");
			}else if(param.equals("contactInfo")){
				temp.append("                 {name: '"+o.getDate()+"', y: "+o.getContactInfo()+"},");
			}else if(param.equals("entryUuid")){
				temp.append("                 {name: '"+o.getDate()+"', y: "+o.getEntryUuid()+"},");
			}else if(param.equals("repayFlow")){
				temp.append("                 {name: '"+o.getDate()+"', y: "+o.getRepayFlow()+"},");
			}else if(param.equals("phoneList")){
				temp.append("                 {name: '"+o.getDate()+"', y: "+o.getPhoneList()+"},");
			}else if(param.equals("rpcPayIn")){
				temp.append("                 {name: '"+o.getDate()+"', y: "+o.getRpcPayIn()+"},");
			}else if(param.equals("rpcPayOut")){
				temp.append("                 {name: '"+o.getDate()+"', y: "+o.getRpcPayOut()+"},");
			}
		}
		StringBuffer result = new StringBuffer();
		if(datas.size() > 0){
			result.append(temp.toString().substring(0, temp.toString().length()-1));
		}else{
			result.append(temp.toString());
		}
		result.append("               ]")
	          .append("         }")
	          .append("    ]")
	          .append("}");
		return result.toString();
	}

	private List<OverDue> getOverDueData(String hql, String date) {
		List<OverDue> datas = this.overDueDaoImpl.getOverDueDataByDate(hql,"%"+date+"%");
		if(datas == null || datas.isEmpty()){
			return new ArrayList<OverDue>();
		}
		return datas;
	}


	private String getNowDate() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		return sdf.format(now);
	}

	public String addOverDue(OverDue overDue) {
		try {
			overDue.setId(IDUtils.getUUID());
			this.overDueDaoImpl.addOverDue(overDue);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	public List<OverDue> getInitOverDueNumData() {
		String date = getCurrentDate();
		String hql = "from OverDueNum o where o.date like ? order by o.date asc";
		return this.overDueDaoImpl.getOverDueDataByDate(hql,"%"+date+"%");
	}
	
	private String getCurrentDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		return sdf.format(new Date());
	}

	public String getDataList(String date, String type) {
		if(type != null && type.equals("num")){
			return this.getNumDataList(date);
		}
		
		if(type != null && type.equals("size")){
			return this.getSizeDataList(date);
		}
		return null;
	}

	private String getNumDataList(String date) {
		String hql = "from OverDueNum o where o.date like ? order by o.date asc";
		return this.congurateDataList(hql,date);
	}
	
	private String getSizeDataList(String date) {
		String hql = "from OverDueSize o where o.date like ? order by o.date asc";
		return this.congurateDataList(hql,date);
	}
	
	private String congurateDataList(String hql,String date){
		List<OverDue> datas = this.overDueDaoImpl.getOverDueDataByDate(hql,"%"+date+"%");
		if(datas == null || datas.isEmpty()){
			return null;
		}
		StringBuffer temp = new StringBuffer();
		for(OverDue o : datas){
			temp.append("<tr>")
			    .append("<td>"+o.getDate()+"</td>")
			    .append("<td>"+o.getCreditorInfo()+"</td>")
			    .append("<td>"+o.getBidsInfo()+"</td>")
			    .append("<td>"+o.getOrderInfo()+"</td>")
			    .append("<td>"+o.getContactInfo()+"</td>")
			    .append("<td>"+o.getEntryUuid()+"</td>")
			    .append("<td>"+o.getRepayFlow()+"</td>")
			    .append("<td>"+o.getPhoneList()+"</td>")
			    .append("<td>"+o.getRpcPayIn()+"</td>")
			    .append("<td>"+o.getRpcPayOut()+"</td>")
			    .append("</tr>");
		}
		return temp.toString();
	}

}
