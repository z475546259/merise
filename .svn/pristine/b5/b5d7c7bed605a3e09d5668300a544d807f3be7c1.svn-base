package net.merise.platform.dao.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.Turn;

import org.springframework.stereotype.Repository;

@Repository
public interface TurnDao {

	List<Turn> findTurnByPage(Map<String, Object> params);

	void saveTrun(Turn turn);

	int findCount(Map<String, Object> params);

}
