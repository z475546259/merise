package net.merise.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.BillingType;
import net.merise.platform.orm.base.EstateBuildingDetail;
import net.merise.platform.orm.base.EstateHouseDetail;
import net.merise.platform.orm.base.EstateInfoDetail;
import net.merise.platform.orm.mapper.AnnouncementDao;
import net.merise.platform.orm.mapper.AppointServiceDAO;
import net.merise.platform.orm.mapper.BillDAO;
import net.merise.platform.orm.mapper.BillingTypeDAO;
import net.merise.platform.orm.mapper.ChargeStandardDAO;
import net.merise.platform.orm.mapper.EstateBuildingDetailDAO;
import net.merise.platform.orm.mapper.EstateCodeEetailDAO;
import net.merise.platform.orm.mapper.EstateHouseDetailDAO;
import net.merise.platform.orm.mapper.EstateInfoDetailDAO;
import net.merise.platform.orm.mapper.OwnerBillDAO;
import net.merise.platform.orm.mapper.OwnerDAO;
import net.merise.platform.service.EstateInfoDetailService;

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
