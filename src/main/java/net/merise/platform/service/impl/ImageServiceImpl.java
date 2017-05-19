package net.merise.platform.service.impl;

import java.util.List;

import net.merise.platform.orm.base.Image;
import net.merise.platform.orm.mapper.ImageDAO;
import net.merise.platform.service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageDAO imageDao;

	@Override
	public void insert(Image ig) {
		imageDao.insert(ig);
	}

	@Override
	public List<Image> findImageByOwnerId(String image_owner_id,String image_owner_table) {
		return imageDao.findImageByOwnerId(image_owner_id,image_owner_table);
	}

	@Override
	public void deleteBy(String image_owner_id, String image_owner_table,String image_order) {
		imageDao.deleteBy(image_owner_id,image_owner_table,image_order);
	}

	@Override
	public Image findImgByOrder(String image_owner_id,String image_owner_table, String image_order) {
		return imageDao.findImgByOrder(image_owner_id,image_owner_table,image_order);
	}

	@Override
	public void delete(String image_id) {
		imageDao.delete(image_id);
	}
	
}
