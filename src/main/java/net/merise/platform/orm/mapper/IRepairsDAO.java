package net.merise.platform.orm.mapper;

import java.util.List;

import net.merise.platform.orm.base.Repairs;

import org.springframework.stereotype.Repository;
@Repository
public interface IRepairsDAO {
	public abstract  List<Repairs> incidentRecord(String str);

}
