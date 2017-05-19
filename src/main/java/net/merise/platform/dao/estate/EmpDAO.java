package net.merise.platform.dao.estate;

import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.pojo.Emp;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpDAO {

	int findCount(Map<String, Object> dataMap);

	List<Emp> findDataByPage(Map<String, Object> dataMap);

	void insert(Emp emp);

	void update(Emp emp);

	void delete(String id);

	Emp findDataById(String emp_id);

	void updateStatus(@Param("id") String id,@Param("newStatus") String newStatus);

}
