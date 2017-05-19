package net.merise.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.BillingType;
import net.merise.platform.orm.base.EstateBuildingDetail;
import net.merise.platform.orm.base.EstateHouseDetail;
import net.merise.platform.orm.mapper.AnnouncementDao;
import net.merise.platform.orm.mapper.AppointServiceDAO;
import net.merise.platform.orm.mapper.BillDAO;
import net.merise.platform.orm.mapper.BillingTypeDAO;
import net.merise.platform.orm.mapper.ChargeStandardDAO;
import net.merise.platform.orm.mapper.EstateBuildingDetailDAO;
import net.merise.platform.orm.mapper.EstateCodeEetailDAO;
import net.merise.platform.orm.mapper.EstateHouseDetailDAO;
import net.merise.platform.orm.mapper.OwnerBillDAO;
import net.merise.platform.orm.mapper.OwnerDAO;
import net.merise.platform.service.EstateBuildingDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EstateBuildingDetailServiceImpl implements	EstateBuildingDetailService {

	@Autowired
	private EstateBuildingDetailDAO estateBuildingDetailDao;
	
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
	public int findCount(Map<String, Object> dataMap) {
		return estateBuildingDetailDao.findCount(dataMap);
	}

	@Override
	public List<EstateBuildingDetail> findDataByPage(Map<String, Object> dataMap) {
		return estateBuildingDetailDao.findDataByPage(dataMap);
	}

	@Override
	public void insert(EstateBuildingDetail estateBuildingDetail) {
		estateBuildingDetailDao.insert(estateBuildingDetail);
	}

	@Override
	public void update(EstateBuildingDetail estateBuildingDetail) {
		estateBuildingDetailDao.update(estateBuildingDetail);
	}

	@Override
	public void delete(String id) {
		//根据楼盘id获取房屋
		List<EstateHouseDetail> eHouseDetails = estateHouseDetailDAO.findEstateHouseDetailByBuildingId(id);
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
		temp.put("building_id", id);
		List<BillingType> billingTypes = billingTypeDAO.findAllByBuilding(temp);
		for (BillingType billingType : billingTypes) {
			chargeStandardDAO.deleteByBillingTypeId(billingType.getBilling_type_id());
		}
		estateCodeEetailDAO.deleteByBuildingId(id); //删除楼盘配置数据
		announcementDao.deleteByBuildingId(id); //删除公告数据
		appointServiceDAO.deleteByBuildingId(id); //删除服务数据
		billDAO.deleteByBuildingId(id); //删除票据数据
		billingTypeDAO.deleteByBuildingId(id); // 删除收费类别数据
		estateBuildingDetailDao.delete(id); //删除楼盘数据
	}

	@Override
	public List<EstateBuildingDetail> findAllData(Map<String, Object> dataMap) {
		return estateBuildingDetailDao.findAllData(dataMap);
	}

	@Override
	public List<EstateBuildingDetail> findEstateBuildingByEstateId(String estateId) {
		return estateBuildingDetailDao.findEstateBuildingByEstateId(estateId);
	}

	@Override
	public List<EstateBuildingDetail> findBuildingAll() {
		return estateBuildingDetailDao.findBuildingAll();
	}
	
	
}

