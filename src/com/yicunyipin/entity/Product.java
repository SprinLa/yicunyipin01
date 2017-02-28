package com.yicunyipin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="t_product")
public class Product {

	private int id;
	private String name;
	private int status=0;
	private String csName;//厂商名称
	private Float price;//价格
	private String proPic;//图片
	private String description;//描述
	private String content;//乡村风貌
	private Date publishTime;//上线时间
	private Date midifyTime;//上次修改时间
	private int click;//点击量
	private String tel;
	private String zipcode;
	private ProductBigType bigType;
	private ProductSmallType smallType;
	private Province province;
	private City city;
	private Town town;
	private String address;//手写地址具体到村
	/*private List<OrderProduct> orderProductList=new ArrayList<OrderProduct>();*/
	private User user;
	private List<Picture> pictures =new ArrayList<Picture>();
	private int hot=0; // 是否精品
	//private int news;// 是否新品
	
	
	
	
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	/*public int getNews() {
		return news;
	}
	public void setNews(int news) {
		this.news = news;
	}*/
	public Date getMidifyTime() {
		return midifyTime;
	}
	public void setMidifyTime(Date midifyTime) {
		this.midifyTime = midifyTime;
	}
	public String getCsName() {
		return csName;
	}
	public void setCsName(String csName) {
		this.csName = csName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@ManyToOne
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getClick() {
		return click;
	}
	public void setClick(int click) {
		this.click = click;
	}
	
	
	@OneToMany(cascade = { CascadeType.ALL },mappedBy="product",fetch=FetchType.EAGER)
	public List<Picture> getPictures() {
		return pictures;
	}
	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	public Product() {
		super();
	}
	public Product(String name, Float price, String description, String tel) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.tel = tel;
	}
	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native",strategy="native")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	@Column(length=50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getProPic() {
		return proPic;
	}
	public void setProPic(String proPic) {
		this.proPic = proPic;
	}
	@Column(length=2000)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@ManyToOne
	@JoinColumn(name="bigTypeId")
	public ProductBigType getBigType() {
		return bigType;
	}
	public void setBigType(ProductBigType bigType) {
		this.bigType = bigType;
	}
	
	@ManyToOne
	@JoinColumn(name="smallTypeId")
	public ProductSmallType getSmallType() {
		return smallType;
	}
	public void setSmallType(ProductSmallType smallType) {
		this.smallType = smallType;
	}
	/*@OneToMany
	@JoinColumn(name="productId")
	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}
	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}*/
	
	
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	@ManyToOne
	@JoinColumn(name="provinceId")
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	
	@ManyToOne
	@JoinColumn(name="cityId")
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	@ManyToOne
	@JoinColumn(name="townId")
	public Town getTown() {
		return town;
	}
	public void setTown(Town town) {
		this.town = town;
	}
	
	
	
}
