package net.merise.platform.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.merise.platform.orm.base.EstateBuildingDetail;
import net.merise.platform.orm.base.EstateCodeDetail;
import net.merise.platform.orm.base.EstateInfoDetail;
import net.merise.platform.service.EstateBuildingDetailService;
import net.merise.platform.service.EstateCodeEetailService;
import net.merise.platform.service.EstateInfoDetailService;
import net.merise.platform.utils.Coder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * 楼盘信息管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/estateBuildingDetail")
@Transactional
public class EstateBuildingDetailController {

	@Autowired
	private EstateBuildingDetailService estateBuildingDetailService;
	
	@Autowired
	private EstateInfoDetailService estateService;
	

	@Autowired
	private EstateCodeEetailService estateCodeEetailService;
	
	@RequestMapping("/index")
	public @ResponseBody Map<String, Object> findAll(int page, int rows,String searchValue) {
		searchValue=searchValue==null?"":searchValue;
		// 处理分页查询开始记录
		int m = (page - 1) * rows;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("m", m);
		dataMap.put("n", rows);
		dataMap.put("building_name", searchValue);
		// 返回数据
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", estateBuildingDetailService.findCount(dataMap));
		resultMap.put("rows", estateBuildingDetailService.findDataByPage(dataMap));
		return resultMap;
	}
	
	@RequestMapping("/findEstateAll")
	public @ResponseBody List<EstateInfoDetail> findEstateAll(){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("estate_name","");
		List<EstateInfoDetail> list=estateService.findAllData(dataMap);//查询所有的物业
		return list;
	}

	@RequestMapping("/save")
	@ResponseBody
	@Transactional
	public void save(EstateBuildingDetail estateBuildingDetail,String estate_id) {
		String building_id=Coder.getSerialCode20();
		estateBuildingDetail.setBuilding_id(building_id);
		estateBuildingDetailService.insert(estateBuildingDetail);
		
		//初始化楼盘下面的编码配置信息
		String[] fwjg={"钢筋混凝土","彩钢结构"};
		EstateCodeDetail estateCodeDetail=null;
		for (int i = 0; i < fwjg.length; i++) {
			estateCodeDetail=new EstateCodeDetail();
			estateCodeDetail.setCode_id(Coder.getSerialCode20());
			estateCodeDetail.setBuilding_id(building_id);
			estateCodeDetail.setCode_name("房屋结构");
			estateCodeDetail.setCode_sequence(i);
			estateCodeDetail.setCode_content(fwjg[i]);
			estateCodeDetail.setCode_remark(fwjg[i]);
			estateCodeEetailService.insert(estateCodeDetail);
		}
		
		String[] fwhx={"单间配套","一室一厅","两室一厅","三室一厅","三室两厅"};
		EstateCodeDetail ecd=null;
		for (int i = 0; i < fwhx.length; i++) {
			ecd=new EstateCodeDetail();
			ecd.setCode_id(Coder.getSerialCode20());
			ecd.setBuilding_id(building_id);
			ecd.setCode_name("房屋户型");
			ecd.setCode_sequence(i);
			ecd.setCode_content(fwhx[i]);
			ecd.setCode_remark(fwhx[i]);
			estateCodeEetailService.insert(ecd);
		}
		
		String[] fwcx={"东","南","西","北"};
		EstateCodeDetail ecd1=null;
		for (int i = 0; i < fwcx.length; i++) {
			ecd1=new EstateCodeDetail();
			ecd1.setCode_id(Coder.getSerialCode20());
			ecd1.setBuilding_id(building_id);
			ecd1.setCode_name("房屋朝向");
			ecd1.setCode_sequence(i);
			ecd1.setCode_content(fwcx[i]);
			ecd1.setCode_remark(fwcx[i]);
			estateCodeEetailService.insert(ecd1);
		}
		
		String[] fwzt={"自留","待售","售出","接房","装修","入驻","空置","出租"};
		EstateCodeDetail ecd2=null;
		for (int i = 0; i < fwzt.length; i++) {
			ecd2=new EstateCodeDetail();
			ecd2.setCode_id(Coder.getSerialCode20());
			ecd2.setBuilding_id(building_id);
			ecd2.setCode_name("房屋状态");
			ecd2.setCode_sequence(i);
			ecd2.setCode_content(fwzt[i]);
			ecd2.setCode_remark(fwzt[i]);
			estateCodeEetailService.insert(ecd2);
		}
		
		/*String[] ckzt={"自留","待售","售出","入驻","空置","出租"};
		EstateCodeDetail ecd3=null;
		for (int i = 0; i < ckzt.length; i++) {
			ecd3=new EstateCodeDetail();
			ecd3.setCode_id(Coder.getSerialCode20());
			ecd3.setBuilding_id(building_id);
			ecd3.setCode_name("车位状态");
			ecd3.setCode_sequence(i);
			ecd3.setCode_content(ckzt[i]);
			ecd3.setCode_remark(ckzt[i]);
			estateCodeEetailService.insert(ecd3);
		}*/
		
		String[] yzfl={"普通用户","Vip","SVip"};
		EstateCodeDetail ecd4=null;
		for (int i = 0; i < yzfl.length; i++) {
			ecd4=new EstateCodeDetail();
			ecd4.setCode_id(Coder.getSerialCode20());
			ecd4.setBuilding_id(building_id);
			ecd4.setCode_name("业主分类");
			ecd4.setCode_sequence(i);
			ecd4.setCode_content(yzfl[i]);
			ecd4.setCode_remark(yzfl[i]);
			estateCodeEetailService.insert(ecd4);
		}
		
		String[] yhsyzt={"自用","出租"};
		EstateCodeDetail ecd5=null;
		for (int i = 0; i < yhsyzt.length; i++) {
			ecd5=new EstateCodeDetail();
			ecd5.setCode_id(Coder.getSerialCode20());
			ecd5.setBuilding_id(building_id);
			ecd5.setCode_name("用户使用状态");
			ecd5.setCode_sequence(i);
			ecd5.setCode_content(yhsyzt[i]);
			ecd5.setCode_remark(yhsyzt[i]);
			estateCodeEetailService.insert(ecd5);
		}
		
		String[] wylx={"住房","商铺"};
		EstateCodeDetail ecd6=null;
		for (int i = 0; i < wylx.length; i++) {
			ecd6=new EstateCodeDetail();
			ecd6.setCode_id(Coder.getSerialCode20());
			ecd6.setBuilding_id(building_id);
			ecd6.setCode_name("物业类型");
			ecd6.setCode_sequence(i);
			ecd6.setCode_content(wylx[i]);
			ecd6.setCode_remark(wylx[i]);
			estateCodeEetailService.insert(ecd6);
		}
		
		String[] cklx={"车库"};
		EstateCodeDetail ecd8=null;
		for (int i = 0; i < cklx.length; i++) {
			ecd8=new EstateCodeDetail();
			ecd8.setCode_id(Coder.getSerialCode20());
			ecd8.setBuilding_id(building_id);
			ecd8.setCode_name("车库类型");
			ecd8.setCode_sequence(i);
			ecd8.setCode_content(cklx[i]);
			ecd8.setCode_remark(cklx[i]);
			estateCodeEetailService.insert(ecd8);
		}
		
		String[] ggsszt={"正常","损坏"};
		EstateCodeDetail ecd7=null;
		for (int i = 0; i < ggsszt.length; i++) {
			ecd7=new EstateCodeDetail();
			ecd7.setCode_id(Coder.getSerialCode20());
			ecd7.setBuilding_id(building_id);
			ecd7.setCode_name("公共设施状态");
			ecd7.setCode_sequence(i);
			ecd7.setCode_content(ggsszt[i]);
			ecd7.setCode_remark(ggsszt[i]);
			estateCodeEetailService.insert(ecd7);
		}
		
		String[] jfgs={"单价*系数","建筑面积*单价*系数","套内面积*单价*系数","(建筑面积-套内面积)*单价*系数"};
		EstateCodeDetail jfgsecd=null;
		for (int i = 0; i < jfgs.length; i++) {
			jfgsecd=new EstateCodeDetail();
			jfgsecd.setCode_id(Coder.getSerialCode20());
			jfgsecd.setBuilding_id(building_id);
			jfgsecd.setCode_name("收费公式");
			jfgsecd.setCode_sequence(i);
			jfgsecd.setCode_content(jfgs[i]);
			jfgsecd.setCode_remark(jfgs[i]);
			estateCodeEetailService.insert(jfgsecd);
		}
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(EstateBuildingDetail estateBuildingDetail,String id) {
		estateBuildingDetail.setBuilding_id(id);
		estateBuildingDetailService.update(estateBuildingDetail);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public JSONObject delete(String id) {
		JSONObject result = new JSONObject();
		try {
			estateBuildingDetailService.delete(id);
			result.put("status", true);
		} catch (Exception e) {
			result.put("message", e.getMessage());
			result.put("status", false);
		}
		return result;
	}
	
	@RequestMapping("/findBuildingByEstateId")
	public @ResponseBody List<EstateBuildingDetail> findBuildingByEstateId(String id){
		return estateBuildingDetailService.findEstateBuildingByEstateId(id);
	}
	
	/**
	 * 查询所有楼盘
	 * @return
	 */
	@RequestMapping("/findBuildingAll")
	@ResponseBody
	public List<EstateBuildingDetail> findBuildingAll(){
		return estateBuildingDetailService.findBuildingAll();
	}
}
