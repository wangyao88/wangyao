package com.overdue.dao;

import java.util.List;

import com.overdue.model.OverDue;

/**
 * 逾期持久层接口
 * @author lixinxing
 * @date 2015-12-04
 */
public interface OverDueDao {
	
	public void addOverDue(OverDue overDue) throws Exception;

	public List<OverDue> getOverDueDataByDate(String hql, String values);

	
}
