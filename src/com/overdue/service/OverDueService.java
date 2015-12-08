package com.overdue.service;

import java.util.List;

import com.overdue.model.OverDue;

/**
 * 逾期服务层接口
 * @author lixinxing
 * @date 2015-12-04
 */
public interface OverDueService {

	/**
	 * 查询单月逾期数据
	 * @param date 日期
	 * @param type 数量/大小
	 * @param param 那个属性
	 * @return
	 */
	public String getOverDueByDate(String date, String type, String param);

	/**
	 * 保存逾期数据
	 * @param overDueNum
	 * @return
	 */
	public String addOverDue(OverDue overDue);

	/**
	 * 获取本月逾期数量数据
	 * @return 本月逾期数量数据
	 */
	public List<OverDue> getInitOverDueNumData();

	/**
	 * 获取指定月份数据
	 * @param date 日期
	 * @param type 类型
	 * @return 指定月份数据
	 */
	public String getDataList(String date, String type);

}
