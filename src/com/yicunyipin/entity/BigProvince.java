package com.yicunyipin.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="t_bigProvince")
public class BigProvince {
	private int id; 
    private String name;   
    private List<Province> provinceList=new ArrayList<Province>();
    
    
    @Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native",strategy="native")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }
    @OneToMany(mappedBy="bigProvince",fetch=FetchType.EAGER)
	public List<Province> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<Province> provinceList) {
		this.provinceList = provinceList;
	}
   
    
    
}
