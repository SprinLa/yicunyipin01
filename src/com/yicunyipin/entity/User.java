package com.yicunyipin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_user")
public class User {

	private int id;
//	private int type;//会员类别（企业或个人）
	private String trueName;//会员
	private String userName;//登录名
	private String password;//密码
	private String sex;
	private Date birthday;
	private String dentityCode;//邮政编码
	private String email;//邮箱
	private String mobile;//手机
	private String address;//详细地址
	private String csName;//厂商名称
	private String tel;//固话
	private String dentityName;//身份证或企业名称
	private String dentityPic; //身份证扫描件
	
	private String rank;//会员等级
	private int status=0;// 状态（审核中/待审核/通过/未通过）
	
	
	
	
	
	
	
	
	
	public String getDentityName() {
		return dentityName;
	}
	public void setDentityName(String dentityName) {
		this.dentityName = dentityName;
	}
	private List<Product> productList=new ArrayList<Product>();
	
//	public int getType() {
//		return type;
//	}
//	public void setType(int type) {
//		this.type = type;
//	}
	public String getDentityPic() {
		return dentityPic;
	}
	public void setDentityPic(String dentityPic) {
		this.dentityPic = dentityPic;
	}
	@OneToMany(mappedBy="user")
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public String getCsName() {
		return csName;
	}
	public void setCsName(String csName) {
		this.csName = csName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	private List<Order> orderList=new ArrayList<Order>();
	
	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native",strategy="native")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(length=20)
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	@Column(length=20)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(length=20000)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(length=5)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Column(length=20)
	public String getDentityCode() {
		return dentityCode;
	}
	public void setDentityCode(String dentityCode) {
		this.dentityCode = dentityCode;
	}
	
	@Column(length=20)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(length=20)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Column(length=200)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@OneToMany(targetEntity=Order.class,cascade=CascadeType.ALL)
	@JoinColumn(name="userId")//外键
	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	
	
	
}
