package net.merise.platform.web.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.merise.platform.orm.base.AppointService;
import net.merise.platform.orm.base.Image;
import net.merise.platform.service.AppointServiceService;
import net.merise.platform.service.ImageService;
import net.merise.platform.utils.Coder;
import net.merise.platform.utils.UploadImages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 预约服务
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/appointService")
@Transactional
public class AppointServiceController {
	
	@Autowired
	private AppointServiceService appointServiceService;
	
	@Autowired
	private ImageService imageService;
	
	@RequestMapping("/delete")
	public @ResponseBody  String delete(String id,HttpServletRequest request) {
		AppointService old = appointServiceService.findDataById(id);
		String oldservicePic = old.getService_picurl();
		
		String path1 = request.getSession().getServletContext().getRealPath("/");  //上传的路径  
		File f=new File(path1,oldservicePic);
		f.delete();
		
		List<Image> list=imageService.findImageByOwnerId(id,"t_appoint_service");
		for (int i = 0; i < list.size(); i++) {
			Image image = list.get(i);
			File file=new File(path1,image.getImage_url());
			file.delete();
			imageService.delete(image.getImage_id());
		}
		
		appointServiceService.delete(id);
		return "ok";
	}
	
	@RequestMapping("/findImageByOwnerId")
	public @ResponseBody List<Image> findImageByOwnerId(String image_owner_id,String image_owner_table){
		List<Image> list=imageService.findImageByOwnerId(image_owner_id,image_owner_table);
		return list;
	}
	
	@RequestMapping("/findAppointServiceByBuildingId")
	public @ResponseBody Map<String, Object> findAppointServiceByBuildingId(int page, int rows,String searchValue,String building_id) {
		searchValue=searchValue==null?"":searchValue;
		// 处理分页查询开始记录
		int m = (page - 1) * rows;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("m", m);
		dataMap.put("n", rows);
		dataMap.put("building_id", building_id);
		dataMap.put("service_name", searchValue);
		// 返回数据
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", appointServiceService.findCount(dataMap));
		resultMap.put("rows", appointServiceService.findDataByPage(dataMap));
		return resultMap;
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public void edit(String id,HttpServletRequest request,AppointService appointService,@RequestParam(value="file_upload") MultipartFile image,
			@RequestParam(value="file_upload1",required=false) MultipartFile image1,@RequestParam(value="file_upload2",required=false) MultipartFile image2,
			@RequestParam(value="file_upload3",required=false) MultipartFile image3){
		try {
			String start_time_value = request.getParameter("start_time_value");
			String end_time_value = request.getParameter("end_time_value");
			SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
			
			UploadImages uploadImages = new UploadImages();  
			String path1 = request.getSession().getServletContext().getRealPath("/");  //上传的路径  
			String path2 = "UploadFiles";//保存的文件夹  
			
			AppointService oldservice=appointServiceService.findDataById(id);
			oldservice.setStart_time(sdf.parse(start_time_value));
			oldservice.setEnd_time(sdf.parse(end_time_value));
			oldservice.setCharge_standard(appointService.getCharge_standard());
			oldservice.setService_des(appointService.getService_des());
			oldservice.setService_phone(appointService.getService_phone());
			oldservice.setService_name(appointService.getService_name());
			//没上传，不修改,当size=0的时候，该图片没修改过
			if(image.getSize()!=0){
				String oldservicePic = oldservice.getService_picurl();
				File f=new File(path1,oldservicePic);
				f.delete();
				
				String imgUrl = uploadImages.upLoadImage(request, image, path1, path2);  //项目图片
				oldservice.setService_picurl(imgUrl);
			}
			appointServiceService.update(oldservice);
			
			if(image1.getSize()!=0){
				Image oldimg=imageService.findImgByOrder(id,"t_appoint_service","1");
				if(oldimg!=null){
					String image_url = oldimg.getImage_url();
					File f=new File(path1,image_url);
					f.delete();
					imageService.deleteBy(id,"t_appoint_service","1");
				}
				
				String imgUrl1 = uploadImages.upLoadImage(request, image1, path1, path2);  
				Image ig=new Image();
				ig.setImage_id(Coder.getSerialCode20());
				ig.setImage_name(image1.getOriginalFilename());
				ig.setImage_owner_table("t_appoint_service");
				ig.setImage_owner_id(id);
				ig.setImage_url(imgUrl1);
				ig.setImage_order("1");
				imageService.insert(ig);
			}
			
			if(image2.getSize()!=0){
				Image oldimg=imageService.findImgByOrder(id,"t_appoint_service","2");
				if(oldimg!=null){
					String image_url = oldimg.getImage_url();
					File f=new File(path1,image_url);
					f.delete();
					imageService.deleteBy(id,"t_appoint_service","2");
				}
				
				String imgUrl2 = uploadImages.upLoadImage(request, image2, path1, path2);  
				Image ig1=new Image();
				ig1.setImage_id(Coder.getSerialCode20());
				ig1.setImage_name(image2.getOriginalFilename());
				ig1.setImage_owner_table("t_appoint_service");
				ig1.setImage_owner_id(id);
				ig1.setImage_url(imgUrl2);
				ig1.setImage_order("2");
				imageService.insert(ig1);
			}
			
			if(image3.getSize()!=0){
				Image oldimg=imageService.findImgByOrder(id,"t_appoint_service","3");
				if(oldimg!=null){
					String image_url = oldimg.getImage_url();
					File f=new File(path1,image_url);
					f.delete();
					imageService.deleteBy(id,"t_appoint_service","3");
				}
				
				String imgUrl3 = uploadImages.upLoadImage(request, image3, path1, path2);  
				Image ig2=new Image();
				ig2.setImage_id(Coder.getSerialCode20());
				ig2.setImage_name(image3.getOriginalFilename());
				ig2.setImage_owner_table("t_appoint_service");
				ig2.setImage_owner_id(id);
				ig2.setImage_url(imgUrl3);
				ig2.setImage_order("3");
				imageService.insert(ig2);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public void save(HttpServletRequest request,AppointService appointService,@RequestParam(value="file_upload") MultipartFile image,
			@RequestParam(value="file_upload1",required=false) MultipartFile image1,@RequestParam(value="file_upload2",required=false) MultipartFile image2,
			@RequestParam(value="file_upload3",required=false) MultipartFile image3){
		try {
			String start_time_value = request.getParameter("start_time_value");
			String end_time_value = request.getParameter("end_time_value");
			SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
			
			UploadImages uploadImages = new UploadImages();  
			String path1 = request.getSession().getServletContext().getRealPath("/");  //上传的路径  
			String path2 = "UploadFiles";//保存的文件夹  
			
//			String imgUrl = uploadImages.upLoadImage(request, image, path1, path2);  //项目图片
			String service_id = Coder.getSerialCode20();
			appointService.setService_id(service_id);
			appointService.setStart_time(sdf.parse(start_time_value));
			appointService.setEnd_time(sdf.parse(end_time_value));
			appointService.setService_picurl("");
			appointServiceService.insert(appointService);
			
			if(image1.getSize()!=0){
				String imgUrl1 = uploadImages.upLoadImage(request, image1, path1, path2);  
				Image ig=new Image();
				ig.setImage_id(Coder.getSerialCode20());
				ig.setImage_name(image1.getOriginalFilename());
				ig.setImage_owner_table("t_appoint_service");
				ig.setImage_owner_id(service_id);
				ig.setImage_url(imgUrl1);
				ig.setImage_order("1");
				imageService.insert(ig);
			}
			
			if(image2.getSize()!=0){
				String imgUrl2 = uploadImages.upLoadImage(request, image2, path1, path2);  
				Image ig1=new Image();
				ig1.setImage_id(Coder.getSerialCode20());
				ig1.setImage_name(image2.getOriginalFilename());
				ig1.setImage_owner_table("t_appoint_service");
				ig1.setImage_owner_id(service_id);
				ig1.setImage_url(imgUrl2);
				ig1.setImage_order("2");
				imageService.insert(ig1);
			}
			
			if(image3.getSize()!=0){
				String imgUrl3 = uploadImages.upLoadImage(request, image3, path1, path2);  
				Image ig2=new Image();
				ig2.setImage_id(Coder.getSerialCode20());
				ig2.setImage_name(image3.getOriginalFilename());
				ig2.setImage_owner_table("t_appoint_service");
				ig2.setImage_owner_id(service_id);
				ig2.setImage_url(imgUrl3);
				ig2.setImage_order("3");
				imageService.insert(ig2);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
