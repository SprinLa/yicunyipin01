package com.yicunyipin.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yicunyipin.dao.BaseDAO;
import com.yicunyipin.entity.BigProvince;
import com.yicunyipin.entity.City;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.Province;
import com.yicunyipin.entity.Town;
import com.yicunyipin.service.ProductAddressService;
import com.yicunyipin.util.StringUtil;



@Service("ProductAddressService")
public class ProductAddressServiceImpl implements ProductAddressService{

	@Resource
	private BaseDAO<BigProvince> baseDAO;
	
	@Resource
	private BaseDAO<Province> baseDAO1;
	@Resource
	private BaseDAO<City> baseDAO2;
	@Resource
	private BaseDAO<Town> baseDAO3;

	@Override
	public List<BigProvince> findAllBigAddressList() {
		return baseDAO.find(" from BigProvince");
	}

	@Override
	public List<Province> findAllProvinceList() {
		return baseDAO1.find(" from Province ");
	}

	@Override
	public List<City> findAllCityList(Province s_province) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from City");
		if(s_province!=null){
			if(s_province!=null){
				hql.append(" and province=?");
				param.add(s_province);
			}
		
		}
		return baseDAO2.find(hql.toString().replaceFirst("and", "where"), param);
		
		//return baseDAO2.find(" from City ");
	}
	
	@Override
	public List<Town> findAllTownList(City s_city) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Town");
		if(s_city!=null){
				hql.append(" and city=?");
				param.add(s_city);
		
		}
		return baseDAO3.find(hql.toString().replaceFirst("and", "where"), param);
		
	}

	@Override
	public Province getProvinceById(int ProId) {
		return baseDAO1.get(Province.class, ProId);
	}

	@Override
	public City getCityById(int CityId) {
		return baseDAO2.get(City.class, CityId);
	}

	@Override
	public List<Province> findProvinceList(Province s_province,PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Province");
		if(s_province!=null){
			if(StringUtil.isNotEmpty(s_province.getPname())){
				hql.append(" and name like ?");
				param.add("%"+s_province.getPname()+"%");
			}
		}
		if(pageBean!=null){
			return baseDAO1.find(hql.toString().replaceFirst("and", "where"),param,pageBean);
		}else{
			return baseDAO1.find(hql.toString().replaceFirst("and", "where"), param);			
		}
	}

	@Override
	public List<City> findCityList(City s_city, PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from City");
		if(s_city!=null){
			if(StringUtil.isNotEmpty(s_city.getCname())){
				hql.append(" and name like ?");
				param.add("%"+s_city.getCname()+"%");
			}
			if(s_city.getProvince()!=null &&s_city.getProvince().getPid()!=-1){
				hql.append(" and province.pid = ?");
				param.add(s_city.getProvince().getPid());
			}
		}
		if(pageBean!=null){
			return baseDAO2.find(hql.toString().replaceFirst("and", "where"),param,pageBean);
		}else{
			return baseDAO2.find(hql.toString().replaceFirst("and", "where"), param);			
		}
	}

	@Override
	public List<Town> findTownList(Town s_town, PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Town");
		if(s_town!=null){
			if(StringUtil.isNotEmpty(s_town.getTname())){
				hql.append(" and name like ?");
				param.add("%"+s_town.getTname()+"%");
			}
			if(s_town.getCity()!=null &&s_town.getCity().getCid()!=-1){
				hql.append(" and city.cid = ?");
				param.add(s_town.getCity().getCid());
			}
		}
		if(pageBean!=null){
			return baseDAO3.find(hql.toString().replaceFirst("and", "where"),param,pageBean);
		}else{
			return baseDAO3.find(hql.toString().replaceFirst("and", "where"), param);			
		}
	}

	@Override
	public Long getProvinceCount(Province s_province) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from Province");
		if(s_province!=null){
			if(StringUtil.isNotEmpty(s_province.getPname())){
				hql.append(" and name like ?");
				param.add("%"+s_province.getPname()+"%");
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}

	@Override
	public Long getCityCount(City s_city) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from City");
		if(s_city!=null){
			if(StringUtil.isNotEmpty(s_city.getCname())){
				hql.append(" and name like ?");
				param.add("%"+s_city.getCname()+"%");
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}

	@Override
	public Long getTownCount(Town s_town) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from Town");
		if(s_town!=null){
			if(StringUtil.isNotEmpty(s_town.getTname())){
				hql.append(" and name like ?");
				param.add("%"+s_town.getTname()+"%");
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}

	@Override
	public void saveProvince(Province province) {
		baseDAO1.merge(province);
		
	}

	@Override
	public void saveCity(City city) {
		baseDAO2.merge(city);
	}

	@Override
	public void saveTown(Town town) {
		baseDAO3.merge(town);
	}

	@Override
	public boolean existCityWithProvinceId(int provinceId) {
		StringBuffer hql=new StringBuffer("from City where province.pid="+provinceId);
		if(baseDAO.find(hql.toString()).size()>0){
			return true;
		}else{
			return false;			
		}
	}

	@Override
	public boolean existTownWithCityId(int cityId) {
		StringBuffer hql=new StringBuffer("from Town where city.cid="+cityId);
		if(baseDAO.find(hql.toString()).size()>0){
			return true;
		}else{
			return false;			
		}
	}

	@Override
	public void delete(Province province) {
		baseDAO1.delete(province);
	}

	@Override
	public void delete(City city) {
		baseDAO2.delete(city);
	}

	@Override
	public void delete(Town town) {
		baseDAO3.delete(town);
	}

	@Override
	public Town getTownById(int townId) {
		return baseDAO3.get(Town.class, townId);
	}

	
	
	
	

	/*@Override
	public List<Town> findAllTownList(City s_city) {
		return baseDAO3.find(" from Town");
	}
*/

	/**/

	
	
	
}


