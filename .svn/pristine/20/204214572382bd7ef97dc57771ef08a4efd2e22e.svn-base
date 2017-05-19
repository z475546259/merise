package net.merise.platform.orm.mapper;

import java.util.List;

import net.merise.platform.orm.base.Image;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDAO {

	void insert(Image ig);

	List<Image> findImageByOwnerId(@Param("image_owner_id") String image_owner_id,@Param("image_owner_table") String image_owner_table);

	void deleteBy(@Param("image_owner_id") String image_owner_id,@Param("image_owner_table") String image_owner_table,@Param("image_order") String image_order);

	Image findImgByOrder(@Param("image_owner_id") String image_owner_id,@Param("image_owner_table") String image_owner_table,@Param("image_order") String image_order);

	void delete(String image_id);

}
