package com.yicunyipin.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_picture")
public class Picture {
	
	private int picId;
	private String picName;
	private String picType;
	private Product product;
	
	
	
	
	@ManyToOne
	@JoinColumn(name="productId")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Picture() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Picture(String picName, String picType) {
		super();
		this.picName = picName;
		this.picType = picType;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getPicType() {
		return picType;
	}
	public void setPicType(String picType) {
		this.picType = picType;
	}
	
	 @Id 
 	 @GeneratedValue(generator="_native")
 	 @GenericGenerator(name="_native",strategy="native")
	public int getPicId() {
		return picId;
	}
	public void setPicId(int picId) {
		this.picId = picId;
	}
	
	
	
	
	
	
	
	
}
