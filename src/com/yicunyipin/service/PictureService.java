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
	 * ɾ����Ʒ
	 * @param Picture
	 */
	public void deletePicture(Picture Picture);
	/**
	 * ������ƷΪ��Ʒ
	 * @param PictureId
	 */
	public void setPictureWithHot(int PictureId);
	
	/**
	 * ������ƷΪ��Ʒ
	 * @param PictureId
	 */
	public void setPictureWithNews(int PictureId);
	//public void delete(Picture Picture);

}
