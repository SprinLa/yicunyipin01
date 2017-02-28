package com.yicunyipin.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_town")
public class Town {
	private int tid;  
    private String tname;
    private City city;
    private List<Product> productList=new ArrayList<Product>();
    
    @Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native",strategy="native")
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="cityId")
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	@OneToMany(mappedBy="town")
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}  
  

}
