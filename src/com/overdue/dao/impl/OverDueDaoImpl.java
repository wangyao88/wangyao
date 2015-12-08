package com.overdue.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.overdue.dao.OverDueDao;
import com.overdue.model.OverDue;

@Repository("overDueDaoImpl")
public class OverDueDaoImpl extends HibernateDaoSupport implements OverDueDao{

	@Autowired  
    public void setSessionFactoryOverride(SessionFactory sessionFactory){  
        super.setSessionFactory(sessionFactory);  
    }

	public void addOverDue(OverDue overDue) throws Exception{
		this.getHibernateTemplate().save(overDue);
	}

	@SuppressWarnings("unchecked")
	public List<OverDue> getOverDueDataByDate(String hql,String values) {
		return (List<OverDue>) this.getHibernateTemplate().find(hql, values);
	}

}
