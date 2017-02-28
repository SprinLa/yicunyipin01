package com.yicunyipin.service;

import java.util.List;

import com.yicunyipin.entity.BigProvince;
import com.yicunyipin.entity.City;
import com.yicunyipin.entity.News;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.ProductBigType;
import com.yicunyipin.entity.ProductSmallType;
import com.yicunyipin.entity.Province;
import com.yicunyipin.entity.Town;



public interface ProductAddressService {

	public List<BigProvince> findAllBigAddressList();
	
	public List<Province> findAllProvinceList();
	
	/**
	 * 分页查询省
	 * @param productBigType
	 * @param pageBean
	 * @return
	 */
	public List<Province> findProvinceList(Province s_Province,PageBean pageBean);
	
	
	/**
	 * 分页查询市
	 * @param productBigType
	 * @param pageBean
	 * @return
	 */
	public List<City> findCityList(City s_City,PageBean pageBean);
	
	
	/**
	 * 分页查询市
	 * @param productBigType
	 * @param pageBean
	 * @return
	 */
	
	
	/**
	 * 分页查询县
	 * @param productBigType
	 * @param pageBean
	 * @return
	 */
	public List<Town> findTownList(Town s_Town,PageBean pageBean);
	
	
	public List<City> findAllCityList(Province s_province);
	
	public List<Town> findAllTownList(City s_city);
	
	/**
	 * 查询省数量
	 * @param s_productBigType
	 * @return
	 */
	public Long getProvinceCount(Province s_province);
	
	/**
	 * 查询省数量
	 * @param s_productBigType
	 * @return
	 */
	public Long getCityCount(City s_city);
	
	/**
	 * 查询省数量
	 * @param s_productBigType
	 * @return
	 */
	public Long getTownCount(Town s_town);
	
	public Province getProvinceById(int ProId);
	public City getCityById(int CityId);
	public Town getTownById(int townId);
	
	/**
	 * 保存省
	 * @param productSmallType
	 */
	public void saveProvince(Province province);
	
	/**
	 * 保存市
	 * @param productSmallType
	 */
	public void saveCity(City city);
	
	/**
	 * 保存县
	 * @param productSmallType
	 */
	public void saveTown(Town town);

	public boolean existCityWithProvinceId(int provinceId);
	public boolean existTownWithCityId(int cityId);
	
	public void delete(Province province);
	
	public void delete(City city);
	
	public void delete(Town town);
	
}
