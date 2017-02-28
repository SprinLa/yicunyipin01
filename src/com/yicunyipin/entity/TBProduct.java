package com.yicunyipin.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tb_product")
public class TBProduct {
	private int id;
	private TBUser user;//会员厂家ID，外键，关联user表的id
	private ProductBigType bigType;//产品类型大类ID，外键，关联bigType表的id
	private ProductSmallType smallType;//产品类型小类ID，外键，关联smallType表的id
	private int type;//产品属性,1精品，2-新品
	private String address;//产地
	private String productInfo;//产品信息描述
	private String productAddressInfo;//产地信息描述
	private int verified;//0未审核，1审核过，2审核不通过
	private String verifiedReason;//审核未通过的原因
	private Date addTime;//创建时间
	private float price;//价格
	private String productPic;//产品特写单张照
	private String packagePic;//产品最小包装的展示图片
	private String productAddressPic;//图片1（产地物理地点标志信息  ）
	private String orderNum;//订单数
	
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
	
	@ManyToOne
	@JoinColumn(name="user_id")
	public TBUser getUser() {
		return user;
	}
	public void setUser(TBUser user) {
		this.user = user;
	}
	@ManyToOne
	@JoinColumn(name="big_type")
	public ProductBigType getBigType() {
		return bigType;
	}
	public void setBigType(ProductBigType bigType) {
		this.bigType = bigType;
	}
	@ManyToOne
	@JoinColumn(name="small_type")
	public ProductSmallType getSmallType() {
		return smallType;
	}
	public void setSmallType(ProductSmallType smallType) {
		this.smallType = smallType;
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
	@Column(name="product_info",columnDefinition="TEXT")
	public String getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	@Column(name="product_address_info",columnDefinition="TEXT")
	public String getProductAddressInfo() {
		return productAddressInfo;
	}
	public void setProductAddressInfo(String productAddressInfo) {
		this.productAddressInfo = productAddressInfo;
	}
	public int getVerified() {
		return verified;
	}
	public void setVerified(int verified) {
		this.verified = verified;
	}
	@Column(name="verified_reason",columnDefinition="TEXT")
	public String getVerifiedReason() {
		return verifiedReason;
	}
	public void setVerifiedReason(String verifiedReason) {
		this.verifiedReason = verifiedReason;
	}
	@Column(name="add_time")
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Column(name="product_pic",length=255)
	public String getProductPic() {
		return productPic;
	}
	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}
	@Column(name="package_pic",length=255)
	public String getPackagePic() {
		return packagePic;
	}
	public void setPackagePic(String packagePic) {
		this.packagePic = packagePic;
	}
	@Column(name="product_address_pic",length=255)
	public String getProductAddressPic() {
		return productAddressPic;
	}
	public void setProductAddressPic(String productAddressPic) {
		this.productAddressPic = productAddressPic;
	}
	@OneToMany(mappedBy="product",fetch=FetchType.LAZY)
	public List<TBOrder> getOrderList() {
		return orderList;
	}
	@OneToMany(mappedBy="product",fetch=FetchType.LAZY)
	public void setOrderList(List<TBOrder> orderList) {
		this.orderList = orderList;
	}

	@Column(name="order_num")
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	@Override
	public String toString() {
		return "TBProduct [id=" + id + ", user=" + user + ", bigType="
				+ bigType + ", smallType=" + smallType + ", type=" + type
				+ ", address=" + address + ", productInfo=" + productInfo
				+ ", productAddressInfo=" + productAddressInfo + ", verified="
				+ verified + ", verifiedReason=" + verifiedReason
				+ ", addTime=" + addTime + ", price=" + price + ", productPic="
				+ productPic + ", packagePic=" + packagePic
				+ ", productAddressPic=" + productAddressPic + "]";
	}
	
	
	

}
