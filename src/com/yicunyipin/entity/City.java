package com.yicunyipin.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name="t_city")
public class City {
	 	private int cid;  
	    private String cname;  
	    private Province province;
	    private List<Town> townList=new ArrayList<Town>();
	    
	    private List<Product> productList=new ArrayList<Product>();
	    
	    @Id
		@GeneratedValue(generator="_native")
		@GenericGenerator(name="_native",strategy="native")
		public int getCid() {
			return cid;
		}
		public void setCid(int cid) {
			this.cid = cid;
		}
		public String getCname() {
			return cname;
		}
		public void setCname(String cname) {
			this.cname = cname;
		}
		
		@ManyToOne(cascade={CascadeType.PERSIST})
		@JoinColumn(name="provinceId")
		public Province getProvince() {
			return province;
		}
		public void setProvince(Province province) {
			this.province = province;
		}
		
		
		@OneToMany(mappedBy="city",fetch=FetchType.EAGER)
		public List<Town> getTownList() {
			return townList;
		}
		public void setTownList(List<Town> townList) {
			this.townList = townList;
		}
		
		@OneToMany(mappedBy="city")
		public List<Product> getProductList() {
			return productList;
		}
		public void setProductList(List<Product> productList) {
			this.productList = productList;
		}
	    
	  
	  
	   
}
