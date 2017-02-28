 package com.yicunyipin.entity;


 import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
 
 
 @Entity
 @Table(name="t_newsType")
 public class NewsType
 {
	 
	 
   private int id;
   private String typeName;
   private List<News> newsList=new ArrayList<News>();
   
	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native",strategy="native")
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
@Column(length=50)
public String getTypeName() {
	return typeName;
}
public void setTypeName(String typeName) {
	this.typeName = typeName;
}

@OneToMany(mappedBy="newsType",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@OrderBy(value= "id ASC")
public List<News> getNewsList() {
	return newsList;
}
public void setNewsList(List<News> newsList) {
	this.newsList = newsList;
}
  
   
   
   
  
 }

