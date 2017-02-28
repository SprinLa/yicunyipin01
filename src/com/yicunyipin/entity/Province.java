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
@Table(name="t_province")
public class Province {
	private int pid; 
    private String pname;   
    private BigProvince bigProvince;
    private List<City> cityList=new ArrayList<City>();
    private List<Product> productList=new ArrayList<Product>();
    
    @Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native",strategy="native")
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {  
        return pname;  
    }  
  
    public void setPname(String pname) {  
        this.pname = pname;  
    }
    @OneToMany(mappedBy="province",fetch=FetchType.EAGER)
	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="BigProvinceId")
	public BigProvince getBigProvince() {
		return bigProvince;
	}

	public void setBigProvince(BigProvince bigProvince) {
		this.bigProvince = bigProvince;
	}
	
	
	@OneToMany(mappedBy="province")
	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
    
    
}
