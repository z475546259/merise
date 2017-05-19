package net.merise.platform.dao.estate;

import java.util.List;

import net.merise.platform.dao.estate.pojo.Repairs;

import org.springframework.stereotype.Repository;
@Repository
public interface IRepairsDAO {
	public abstract  List<Repairs> incidentRecord(String str);

}
