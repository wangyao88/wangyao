package com.overdue.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.overdue.model.OverDue;
import com.overdue.model.OverDueNum;
import com.overdue.model.OverDueSize;
import com.overdue.service.OverDueService;

@Controller
@RequestMapping("/overDue")
public class OverDueController {
	
	@Autowired
	@Qualifier("overDueServiceImpl")
	private OverDueService overDueServiceImpl;
	
	@ResponseBody
	@RequestMapping(value="/getOverDueByDate",method=RequestMethod.POST)
	public String getOverDueByDate(HttpServletRequest request,
			                          @RequestParam(required=true,value="date")String date,
			                          @RequestParam(required=true,value="type")String type,
			                          @RequestParam(required=true,value="param")String param){
//		HttpSession session = request.getSession();
//		Object o = session.getAttribute(date+"-"+type+"-"+param);// 201512-size-creditorInfo  result
//		if(o != null){
//			return o.toString();
//		}
		String result = this.overDueServiceImpl.getOverDueByDate(date,type,param);
//		session.setAttribute(date+"-"+type+"-"+param, result);
		return result;
	}
	
	@RequestMapping(value="/gotoChart.do")
	public ModelAndView gotoChart(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("line");
		return mv;
	}
	
	@RequestMapping(value="/gotoAddData.do")
	public ModelAndView gotoAddData(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addData");
		return mv;
	}
	
	@RequestMapping("/addSizeData.do")
	public ModelAndView addSizeData(OverDueSize overDueSize,HttpServletRequest request){
		String result = this.overDueServiceImpl.addOverDue(overDueSize);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dataList");
		return mv;
	}
	
	@RequestMapping("/addNumData.do")
	public ModelAndView addSizeData(OverDueNum overDueNum,HttpServletRequest request){
		String result = this.overDueServiceImpl.addOverDue(overDueNum);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dataList");
		return mv;
	}
	
	@RequestMapping(value="/gotoDataList.do")
	public ModelAndView gotoDataList(HttpServletRequest request){
		List<OverDue> overDueList = this.overDueServiceImpl.getInitOverDueNumData();
		request.setAttribute("overDueList", overDueList);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dataList");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/dataList.do",method=RequestMethod.POST)
	public String dataList(@RequestParam(required=true,value="date")String date,@RequestParam(required=true,value="type")String type){
		String result = this.overDueServiceImpl.getDataList(date,type);
		return result;
	}

}
