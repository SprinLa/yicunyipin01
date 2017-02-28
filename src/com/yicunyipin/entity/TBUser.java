package com.yicunyipin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="tb_user")
public class TBUser {
	private int id;
	private String userName;//用户名,登录名称
	private String password;//登录密码
	private String productName;//产品厂家名称
	private String provinceCity;//省+市
	private String memberName;//产品名称 provinceCity+memberName为会员名称
	private String legal;//企业法人
	private String contactName;//会员联系人
	private int type;//申请会员类型  0为普通用户，1为普通会员，2位VIP会员
	private String address;//厂家地址
	private String phoneNum;//联系方式手机号
	private String telNum;//固话
	private String factoryInfo;//厂家信息
	private int verified;//审核类型1-审核通过，2-审核未通过，0-未审核
	private String verifiedReason;//审核未通过的原因
	private String addressPic;//图片1(厂家物理地点标志信息)
	private String awardPic;//图片2证书（企业获奖证书或国家政府颁发的荣誉证书）
	private String licencePic;//（厂家营业执照）扫描件或照片
	private Date memberTime;//成为会员的时间
	
	private List<TBProduct> productList = new ArrayList<TBProduct>();
	private List<TBOrder> orderList = new ArrayList<TBOrder>();
	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native",strategy="native")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="username",length=255,nullable=false)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(nullable=false,length=255)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="product_name",length=255)
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Column(name="province_city",length=255)
	public String getProvinceCity() {
		return provinceCity;
	}
	public void setProvinceCity(String provinceCity) {
		this.provinceCity = provinceCity;
	}
	@Column(name="member_name",length=255)
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getLegal() {
		return legal;
	}
	public void setLegal(String legal) {
		this.legal = legal;
	}
	@Column(name="contact_name",length=255)
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="phone_num",length=255)
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	@Column(name="tel_num",length=255)
	public String getTelNum() {
		return telNum;
	}
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	@Column(name="factory_info",columnDefinition="TEXT")
	public String getFactoryInfo() {
		return factoryInfo;
	}
	public void setFactoryInfo(String factoryInfo) {
		this.factoryInfo = factoryInfo;
	}
	@Column(name="verified_status")
	public int getVerified() {
		return verified;
	}
	public void setVerified(int verified) {
		this.verified = verified;
	}
	@Column(name="verified_reason",length=255)
	public String getVerifiedReason() {
		return verifiedReason;
	}
	public void setVerifiedReason(String verifiedReason) {
		this.verifiedReason = verifiedReason;
	}
	@Column(name="address_pic",length=255)
	public String getAddressPic() {
		return addressPic;
	}
	public void setAddressPic(String addressPic) {
		this.addressPic = addressPic;
	}
	@Column(name="award_pic",length=255)
	public String getAwardPic() {
		return awardPic;
	}
	public void setAwardPic(String awardPic) {
		this.awardPic = awardPic;
	}
	@Column(name="license_pic",length=255)
	public String getLicencePic() {
		return licencePic;
	}
	public void setLicencePic(String licencePic) {
		this.licencePic = licencePic;
	}
	@Column(name="member_time")
	public Date getMemberTime() {
		return memberTime;
	}
	public void setMemberTime(Date memberTime) {
		this.memberTime = memberTime;
	}
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
	public List<TBProduct> getProductList() {
		return productList;
	}
	
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
	public void setProductList(List<TBProduct> productList) {
		this.productList = productList;
	}
	
	@OneToMany(mappedBy="buyerUser",fetch=FetchType.LAZY)
	public List<TBOrder> getOrderList() {
		return orderList;
	}
	@OneToMany(mappedBy="buyerUser",fetch=FetchType.LAZY)
	public void setOrderList(List<TBOrder> orderList) {
		this.orderList = orderList;
	}
	@Override
	public String toString() {
		return "TBUser [id=" + id + ", userName=" + userName + ", password="
				+ password + ", productName=" + productName + ", provinceCity="
				+ provinceCity + ", memberName=" + memberName + ", legal="
				+ legal + ", contactName=" + contactName + ", type=" + type
				+ ", address=" + address + ", phoneNum=" + phoneNum
				+ ", telNum=" + telNum + ", factoryInfo=" + factoryInfo
				+ ", verified=" + verified + ", verifiedReason="
				+ verifiedReason + ", addressPic=" + addressPic + ", awardPic="
				+ awardPic + ", licencePic=" + licencePic + ", memberTime="
				+ memberTime + "]";
	}
	
	
}
