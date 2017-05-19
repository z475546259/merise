package net.merise.platform.service;

import java.util.List;

import net.merise.platform.orm.base.Image;

public interface ImageService {

	void insert(Image ig);

	List<Image> findImageByOwnerId(String image_owner_id,String image_owner_table);

	void deleteBy(String image_owner_id, String image_owner_table, String image_order);

	Image findImgByOrder(String image_owner_id, String image_owner_table, String image_order);

	void delete(String image_id);

}
