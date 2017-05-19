package net.merise.platform.dao.estate;

import java.util.List;

import net.merise.platform.dao.estate.pojo.TUserOpinion;

import org.springframework.stereotype.Repository;

@Repository
public interface UserOpinionDAO {

	void insert(TUserOpinion userOpinion);
	
	void update(TUserOpinion userOpinion);
	
	void delete(String opinion_id);
	
	TUserOpinion findDataById(String opinion_id);
	
	List<TUserOpinion> findAllData();
	
	int findCount();
	
}
