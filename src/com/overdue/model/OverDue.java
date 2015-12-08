package com.overdue.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 逾期父类模型
 * @author wangyao
 * @date 2015-12-04
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class OverDue implements Serializable{
	
	private static final long serialVersionUID = -3188497921394662786L;

	@Id
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
	@Column(name="od_id", nullable=false,length=32)
	private String id;
	
	@Column(name="od_creditor_info", nullable=false)
	private double creditorInfo;//债权人信息
	
	@Column(name="od_bids_info", nullable=false)
	private double bidsInfo;//账户信息
	
	@Column(name="od_order_info", nullable=false)
	private double orderInfo;//订单信息
	
	@Column(name="od_contact_info", nullable=false)
	private double contactInfo;//联系人信息
	
	@Column(name="od_entry_uuid", nullable=false)
	private double entryUuid;//债务人信息
	
	@Column(name="od_repay_flow", nullable=false)
	private double repayFlow;//还账流水
	
	@Column(name="od_phone_list", nullable=false)
	private double phoneList;//联系人通讯录
	
	@Column(name="od_rpc_pay_in", nullable=false)
	private double rpcPayIn;//RRC_PAY.in
	
	@Column(name="od_rpc_pay_out", nullable=false)
	private double rpcPayOut;//RPC_PAY.out
	
	@Column(name="od_date", nullable=false,length=8)
	private String date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getCreditorInfo() {
		return creditorInfo;
	}

	public void setCreditorInfo(double creditorInfo) {
		this.creditorInfo = creditorInfo;
	}

	public double getBidsInfo() {
		return bidsInfo;
	}

	public void setBidsInfo(double bidsInfo) {
		this.bidsInfo = bidsInfo;
	}

	public double getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(double orderInfo) {
		this.orderInfo = orderInfo;
	}

	public double getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(double contactInfo) {
		this.contactInfo = contactInfo;
	}

	public double getEntryUuid() {
		return entryUuid;
	}

	public void setEntryUuid(double entryUuid) {
		this.entryUuid = entryUuid;
	}

	public double getRepayFlow() {
		return repayFlow;
	}

	public void setRepayFlow(double repayFlow) {
		this.repayFlow = repayFlow;
	}

	public double getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(double phoneList) {
		this.phoneList = phoneList;
	}

	public double getRpcPayIn() {
		return rpcPayIn;
	}

	public void setRpcPayIn(double rpcPayIn) {
		this.rpcPayIn = rpcPayIn;
	}

	public double getRpcPayOut() {
		return rpcPayOut;
	}

	public void setRpcPayOut(double rpcPayOut) {
		this.rpcPayOut = rpcPayOut;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
