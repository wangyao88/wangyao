package com.overdue.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 逾期大小模型
 * @author lixinxing
 * @date 2015-12-04
 */
@Entity
@Table(name="t_over_due_size")
public class OverDueSize extends OverDue{
	
	private static final long serialVersionUID = 6081197026064470988L;
	
	@Transient
	private double totalSize;

	public double getTotalSize() {
		totalSize = this.getBidsInfo() + this.getContactInfo() + this.getCreditorInfo() + this.getEntryUuid() + this.getOrderInfo() + this.getPhoneList() + this.getRepayFlow() + this.getRpcPayOut();
		return totalSize;
	}

	public void setTotalSize(double totalSize) {
		this.totalSize = totalSize;
	}

}
