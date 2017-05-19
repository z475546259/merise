package net.merise.platform.service.estate.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.merise.platform.dao.estate.AnnouncementDao;
import net.merise.platform.dao.estate.AppointServiceDAO;
import net.merise.platform.dao.estate.BillDAO;
import net.merise.platform.dao.estate.BillingTypeDAO;
import net.merise.platform.dao.estate.ChargeStandardDAO;
import net.merise.platform.dao.estate.EstateBuildingDetailDAO;
import net.merise.platform.dao.estate.EstateCodeEetailDAO;
import net.merise.platform.dao.estate.EstateHouseDetailDAO;
import net.merise.platform.dao.estate.EstateInfoDetailDAO;
import net.merise.platform.dao.estate.OwnerBillDAO;
import net.merise.platform.dao.estate.OwnerDAO;
import net.merise.platform.dao.estate.pojo.BillingType;
import net.merise.platform.dao.estate.pojo.EstateBuildingDetail;
import net.merise.platform.dao.estate.pojo.EstateHouseDetail;
import net.merise.platform.dao.estate.pojo.EstateInfoDetail;
import net.merise.platform.service.estate.EstateInfoDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EstateInfoDetailServiceImpl implements EstateInfoDetailService {

	@Autowired
	private EstateInfoDetailDAO dao;
	
	@Autowired
	private EstateBuildingDetailDAO estateBuildingDetailDAO;
	
	@Autowired
	private EstateHouseDetailDAO estateHouseDetailDAO;
	
	@Autowired
	private OwnerDAO ownerDAO;
	
	@Autowired
	private EstateCodeEetailDAO estateCodeEetailDAO;
	
	@Autowired
	private AnnouncementDao announcementDao;
	
	@Autowired
	private AppointServiceDAO appointServiceDAO;
	
	@Autowired
	private BillDAO billDAO;
	
	@Autowired
	private BillingTypeDAO billingTypeDAO;
	
	@Autowired
	private ChargeStandardDAO chargeStandardDAO;
	
	@Autowired
	private OwnerBillDAO ownerBillDAO;
	
	@Override
	public void insert(EstateInfoDetail estateInfoDetail) {
		dao.insert(estateInfoDetail);
	}

	@Override
	public void update(EstateInfoDetail estateInfoDetail) {
		dao.update(estateInfoDetail);
	}

	@Override
	public void delete(String id) {
		//获取楼盘
		List<EstateBuildingDetail> list= estateBuildingDetailDAO.findEstateBuildingByEstateId(id);
		for (EstateBuildingDetail estateBuildingDetail : list) {
			//根据楼盘id获取房屋
			List<EstateHouseDetail> eHouseDetails = estateHouseDetailDAO.findEstateHouseDetailByBuildingId(estateBuildingDetail.getBuilding_id());
			for (EstateHouseDetail estateHouseDetail : eHouseDetails) {
				//删除账单数据
				ownerBillDAO.deleteByHouseId(estateHouseDetail.getHouse_id());
				//删除业主房屋信息
				ownerDAO.deleteByHouseId(estateHouseDetail.getHouse_id());
				//删除房屋
				estateHouseDetailDAO.delete(estateHouseDetail.getHouse_id());
			}
			// 删除收费标准数据
			Map<String, Object> temp = new HashMap<>();
			temp.put("building_id", estateBuildingDetail.getBuilding_id());
			List<BillingType> billingTypes = billingTypeDAO.findAllByBuilding(temp);
			for (BillingType billingType : billingTypes) {
				chargeStandardDAO.deleteByBillingTypeId(billingType.getBilling_type_id());
			}
			estateCodeEetailDAO.deleteByBuildingId(estateBuildingDetail.getBuilding_id()); //删除楼盘配置数据
			announcementDao.deleteByBuildingId(estateBuildingDetail.getBuilding_id()); //删除公告数据
			appointServiceDAO.deleteByBuildingId(estateBuildingDetail.getBuilding_id()); //删除服务数据
			billDAO.deleteByBuildingId(estateBuildingDetail.getBuilding_id()); //删除票据数据
			billingTypeDAO.deleteByBuildingId(estateBuildingDetail.getBuilding_id()); // 删除收费类别数据
			estateBuildingDetailDAO.delete(estateBuildingDetail.getBuilding_id()); //删除楼盘数据
		}
		//删除物业
		dao.delete(id);
	}

	@Override
	public EstateInfoDetail findDataById(String id) {
		return dao.findDataById(id);
	}

	@Override
	public List<EstateInfoDetail> findAllData(Map<String, Object> map) {
		return dao.findAllData(map);
	}

	@Override
	public int findCount(Map<String, Object> map) {
		return dao.findCount(map);
	}

	@Override
	public List<EstateInfoDetail> findDataByPage(Map<String, Object> map) {
		return dao.findDataByPage(map);
	}

	@Override
	public List<EstateInfoDetail> findEastateInfoByName(String name) {
		return dao.findEastateInfoByName(name);
	}

}
