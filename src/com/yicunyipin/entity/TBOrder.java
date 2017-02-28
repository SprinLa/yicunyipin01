package com.yicunyipin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="tb_order")
public class TBOrder {
	private int id;
	private TBUser buyerUser;
	private TBProduct product;
	private String buyerName;
	private String buyerPhone;
	private String buyerCompany;
	private String buyerAddress;
	private int orderCount;
	private Date buyerTime;
	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native",strategy="native")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="buyer_id")
	public TBUser getBuyerUser() {
		return buyerUser;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="buyer_id")
	public void setBuyerUser(TBUser buyerUser) {
		this.buyerUser = buyerUser;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product_id")
	public TBProduct getProduct() {
		return product;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product_id")
	public void setProduct(TBProduct product) {
		this.product = product;
	}
	@Column(name="buyer_name",length=50)
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	@Column(name="buyer_phone",length=20)
	public String getBuyerPhone() {
		return buyerPhone;
	}
	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}
	@Column(name="buyer_company",length=100)
	public String getBuyerCompany() {
		return buyerCompany;
	}
	public void setBuyerCompany(String buyerCompany) {
		this.buyerCompany = buyerCompany;
	}
	@Column(name="buyer_address",length=100)
	public String getBuyerAddress() {
		return buyerAddress;
	}
	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}
	@Column(name="order_count")
	public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	@Column(name="buyer_time",length=20)
	public Date getBuyerTime() {
		return buyerTime;
	}
	public void setBuyerTime(Date buyerTime) {
		this.buyerTime = buyerTime;
	}
	
	public String toString() {
		// TODO Auto-generated method stub
		return "{id:"+id+",memberName:"+product.getUser().getMemberName()+",productName:"+product.getUser().getProductName()+",buyerName:"+buyerName+
				",buyerPhone:"+buyerPhone+",buyerCompany:"+buyerCompany+",buyerAddress:"+buyerAddress
				+",buyerTime:"+buyerTime+"}";
	}
	
	
	
	

}
