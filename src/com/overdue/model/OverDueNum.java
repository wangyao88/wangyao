package com.overdue.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 逾期数量模型
 * @author lixinxing
 * @date 2015-12-04
 */
@Entity
@Table(name="t_over_due_num")
public class OverDueNum extends OverDue{

	private static final long serialVersionUID = 8318452165617133068L;

}
