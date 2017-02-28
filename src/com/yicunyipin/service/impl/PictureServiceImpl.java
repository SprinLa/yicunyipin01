package com.yicunyipin.service.impl;


import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yicunyipin.dao.BaseDAO;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.Picture;
import com.yicunyipin.service.PictureService;
import com.yicunyipin.util.StringUtil;



@Service("pictureService")
public class PictureServiceImpl implements PictureService{

	@Resource
	private BaseDAO<Picture> baseDAO;
	
	/*@Override
	public List<Picture> findPictureList(Picture s_Picture, PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Picture");

		if(s_Picture!=null){
			根据类型查找
		   if(s_Picture.getPictureType()!=null){
			hql.append(" and PictureType.id=?");
			param.add(s_Picture.getPictureType().getId());
			System.out.println(""+s_Picture.getPictureType().getId());
		   }
		}
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return null;
		}
	}

	@Override
	public Picture getPictureById(int PictureId) {
		return baseDAO.get(Picture.class, PictureId);
	}

	//新闻数量
	@Override
	public Long getPictureCount(Picture s_Picture) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from Picture");
		if(s_Picture!=null){
			if(s_Picture.getPictureType()!=null){
				hql.append(" and PictureType.id=?");
				param.add(s_Picture.getPictureType().getId());
			}
			
			if(StringUtil.isNotEmpty(s_Picture.getName())){
				hql.append(" and name like ?");
				param.add("%"+s_Picture.getName()+"%");
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}*/

	@Override
	public void savePicture(Picture Picture) {
		baseDAO.merge(Picture);
	}

	@Override
	public List<Picture> findPictureList(Picture s_picture, PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Picture");
		if(s_picture!=null){
			/*if(s_picture.getBigType()!=null){
				hql.append(" and bigType.id=?");
				param.add(s_picture.getBigType().getId());
			}
			if(s_picture.getSmallType()!=null){
				hql.append(" and smallType.id=?");
				param.add(s_picture.getSmallType().getId());
			}
			if(s_picture.getProvince()!=null){
				hql.append(" and province.pid=?");
				param.add(s_picture.getProvince().getPid());
			}
			if(s_picture.getCity()!=null){
				hql.append(" and city.cid=?");
				param.add(s_picture.getCity().getCid());
			}
			if(s_picture.getUser()!=null){
				hql.append(" and user.id=?");
				param.add(s_picture.getUser().getId());
			}
			
			if(StringUtil.isNotEmpty(s_picture.getName())){
				hql.append(" and name like ?");
				param.add("%"+s_picture.getName()+"%");
			}
			if(s_picture.getNews()==1){
				hql.append(" and news=1 order by publishTime desc");
			} 
			if(s_picture.getHot()==1){
				hql.append(" and hot=1 order by publishTime desc");
			}*/
		}
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return null;
		}
	}

	@Override
	public Long getPictureCount(Picture s_picture) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from Picture");
		if(s_picture!=null){
		/*	if(s_picture.getBigType()!=null){
				hql.append(" and bigType.id=?");
				param.add(s_picture.getBigType().getId());
			}
			if(s_picture.getSmallType()!=null){
				hql.append(" and smallType.id=?");
				param.add(s_picture.getSmallType().getId());
			}
			if(s_picture.getProvince()!=null){
				hql.append(" and province.pid=?");
				param.add(s_picture.getProvince().getPid());
			}
			if(s_picture.getCity()!=null){
				hql.append(" and city.cid=?");
				param.add(s_picture.getCity().getCid());
			}
			if(s_picture.getUser()!=null){
				hql.append(" and user.id=?");
				param.add(s_picture.getUser().getId());
			}
			if(StringUtil.isNotEmpty(s_picture.getName())){
				hql.append(" and name like ?");
				param.add("%"+s_picture.getName()+"%");
			}*/
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}

	@Override
	public void deletePicture(Picture Picture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPictureWithHot(int PictureId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPictureWithNews(int PictureId) {
		// TODO Auto-generated method stub
		
	}

	/*@Override
	public void delete(Picture Picture) {
		// TODO Auto-generated method stub
		baseDAO.delete(Picture);
	}

	@Override
	public boolean existPictureWithPictureTypeId(int PictureTypeId) {
		StringBuffer hql=new StringBuffer("from Picture where PictureType.id="+PictureTypeId);
		if(baseDAO.find(hql.toString()).size()>0){
			return true;
		}else{
			return false;			
		}
	}
*/
	
	
}
