package com.yicunyipin.service;

import java.util.List;

import com.yicunyipin.entity.Picture;
import com.yicunyipin.entity.PageBean;



public interface PictureService {

	//public List<Picture> findPictureList(Picture s_Picture,PageBean pageBean);
	
	//public Long getPictureCount(Picture s_Picture);
	public List<Picture> findPictureList(Picture s_Picture,PageBean pageBean);
	public void savePicture(Picture Picture);
	public Long getPictureCount(Picture s_Picture);
	//public Picture getPictureById(int PictureId);
	/**
	 * 删除商品
	 * @param Picture
	 */
	public void deletePicture(Picture Picture);
	/**
	 * 设置商品为精品
	 * @param PictureId
	 */
	public void setPictureWithHot(int PictureId);
	
	/**
	 * 设置商品为新品
	 * @param PictureId
	 */
	public void setPictureWithNews(int PictureId);
	//public void delete(Picture Picture);

}
