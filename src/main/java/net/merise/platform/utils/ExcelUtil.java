package net.merise.platform.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil<T> {
	private Workbook workbook = null;   

	/** 
	/** 
	 * 读取excel表中的数据. 
	 *  
	 * @param fileDir    文件路径    
	 * @param object   object 
	 * @param headers excel表头信息
	 */ 
	@SuppressWarnings("rawtypes")
	public List readFromExcel(String fileDir,Object object,String[] headers) {  
		try {
			//创建workbook
			File file = new File(fileDir); 
				try {
					workbook = new XSSFWorkbook(new FileInputStream(file));
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}  

			List result = new ArrayList();  
			// 获取该对象的class对象  
			Class class_ = object.getClass();  
			// 获得该类的所有属性  
			Field[] fields = class_.getDeclaredFields();  

			// 读取excel数据  
			// 获得指定的excel表  
			//XSSFSheet sheet = workbook.getSheet(sheetName);)
			int sheetNum = workbook.getNumberOfSheets();
			if(sheetNum>0){
				for(int n=0;n<sheetNum;n++){
					XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(n);
					// 获取表格的总行数  
					int rowCount = sheet.getLastRowNum()+1 ; // 需要加一   
					if (rowCount < 1) {  
						return result;  
					}
					// 获取表头的列数  
					int columnCount = sheet.getRow(0).getLastCellNum(); 
					// 读取表头信息,确定需要用的方法名---set方法  
					// 用于存储方法名  
					String[] methodNames = new String[columnCount]; // 表头列数即为需要的set方法个数  
					// 用于存储属性类型  
					String[] fieldTypes = new String[columnCount];  
					// 获得表头行对象  
					XSSFRow titleRow = sheet.getRow(0);  
					// 遍历  
					for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) { // 遍历表头列  
						String data = titleRow.getCell(columnIndex).toString(); // 某一列的内容  
						String shuxing = fields[columnIndex].getName().toString();
						String Udata = Character.toUpperCase(shuxing.charAt(0))  
								+ shuxing.substring(1, shuxing.length()); // 使其首字母大写 
						methodNames[columnIndex] = "set" + Udata; 
						for (int i = 0; i < headers.length; i++) { // 遍历表头数组  
							if (data.equals(headers[i])) { // 列内容与表头相等  
								fieldTypes[columnIndex] = fields[i].getType().getName(); // 将属性类型放到数组中  
							}  
						} 
					}  
					// 逐行读取数据 从1开始 忽略表头  
					for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) {  
						// 获得行对象  
						XSSFRow row = sheet.getRow(rowIndex);  
						if (row != null) {  
							Object obj = null;  
							// 实例化该泛型类的对象一个对象  
							try {  
								obj = class_.newInstance();  
							} catch (Exception e1) {  
								e1.printStackTrace();  
							}  

							// 获得本行中各单元格中的数据  
							for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {  
								String data = row.getCell(columnIndex).toString();
								// 获取要调用方法的方法名  
								String methodName = methodNames[columnIndex];  
								Method method = null;  
								if(data!=null&&""!=data){
									try { 
										if (fieldTypes[columnIndex].equals("java.lang.String")) {  
											method = class_.getDeclaredMethod(methodName,  String.class); // 设置要执行的方法--set方法参数为String  
											method.invoke(obj, data); // 执行该方法  
										} else if (fieldTypes[columnIndex].equals("int")) {  
											method = class_.getDeclaredMethod(methodName,  int.class); // 设置要执行的方法--set方法参数为int  
											double data_double = Double.parseDouble(data.trim());  
											int data_int = (int) data_double;  
											method.invoke(obj, data_int); // 执行该方法  
										}else if(fieldTypes[columnIndex].equals("java.util.Date")){
											method = class_.getDeclaredMethod(methodName,Date.class); // 设置要执行的方法--set方法参数为Date  
											Date strDate = StringToDate(data);
											method.invoke(obj,strDate);
										}  
									} catch (Exception e) {  
										e.printStackTrace();  
									} 
								}
								
							}  
							result.add(obj);  
						}  
					}
				}
			}
			return result;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			//创建workbook  
			File file = new File(fileDir); 
			try {
				workbook = new HSSFWorkbook(new FileInputStream(file));
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			}  

			List result = new ArrayList();  
			// 获取该对象的class对象  
			Class class_ = object.getClass();  
			// 获得该类的所有属性  
			Field[] fields = class_.getDeclaredFields();  

			// 读取excel数据  
			// 获得指定的excel表  
			//XSSFSheet sheet = workbook.getSheet(sheetName);)
			int sheetNum = workbook.getNumberOfSheets();
			if(sheetNum>0){
				for(int n=0;n<sheetNum;n++){
					HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(n);
					// 获取表格的总行数  
					int rowCount = sheet.getLastRowNum()+1 ; // 需要加一   
					if (rowCount < 1) {  
						return result;  
					} 
					// 获取表头的列数  
					int columnCount = sheet.getRow(1).getLastCellNum(); 
					// 读取表头信息,确定需要用的方法名---set方法  
					// 用于存储方法名  
					String[] methodNames = new String[columnCount]; // 表头列数即为需要的set方法个数  
					// 用于存储属性类型  
					String[] fieldTypes = new String[columnCount];  
					// 获得表头行对象  
					HSSFRow titleRow = sheet.getRow(1);  
					// 遍历  
					for (int columnIndex = 1; columnIndex < columnCount; columnIndex++) { // 遍历表头列  
						String data = titleRow.getCell(columnIndex).toString(); // 某一列的内容  
						String shuxing = fields[columnIndex].getName().toString();
						String Udata = Character.toUpperCase(shuxing.charAt(0))  
								+ shuxing.substring(1, shuxing.length()); // 使其首字母大写 
						methodNames[columnIndex] = "set" + Udata; 
						for (int i = 1; i < headers.length; i++) { // 遍历表头数组  
							if (data.equals(headers[i])) { // 列内容与表头相等  
								fieldTypes[columnIndex] = fields[i].getType().getName(); // 将属性类型放到数组中  
							}  
						} 
					}  
					// 逐行读取数据 从1开始 忽略表头  
					for (int rowIndex = 2; rowIndex < rowCount; rowIndex++) {  
						// 获得行对象  
						HSSFRow row = sheet.getRow(rowIndex);  
						if (row != null) {  
							Object obj = null;  
							// 实例化该泛型类的对象一个对象  
							try {  
								obj = class_.newInstance();  
							} catch (Exception e1) {  
								e1.printStackTrace();  
							}  

							// 获得本行中各单元格中的数据  
							for (int columnIndex = 1; columnIndex < columnCount; columnIndex++) {  
								String data = row.getCell(columnIndex).toString();
								// 获取要调用方法的方法名  
								String methodName = methodNames[columnIndex];  
								Method method = null;  
								if(data!=null&&""!=data){
									try { 
										if (fieldTypes[columnIndex].equals("java.lang.String")) {  
											method = class_.getDeclaredMethod(methodName,  
													String.class); // 设置要执行的方法--set方法参数为String  
											method.invoke(obj, data); // 执行该方法  
										} else if (fieldTypes[columnIndex].equals("int")) {  
											method = class_.getDeclaredMethod(methodName,  
													int.class); // 设置要执行的方法--set方法参数为int  
											double data_double = Double.parseDouble(data.trim());  
											int data_int = (int) data_double;  
											method.invoke(obj, data_int); // 执行该方法  
										}else if(fieldTypes[columnIndex].equals("java.util.Date")){
											method = class_.getDeclaredMethod(methodName,  
													 Date.class); // 设置要执行的方法--set方法参数为Date  
											Date strDate = StringToDate(data);
											method.invoke(obj,strDate);
										}  
									} catch (Exception e) {  
										e.printStackTrace();  
									} 
								}
								
							}  
							result.add(obj);  
						}  
					}
				}
			}
			return result;
			
		}
		
	}  
	
    /** 
     * 删除文件. 
     * @param fileDir  文件路径 
     */ 
    public boolean deleteExcel(String fileDir){  
        boolean flag = false;  
        File file = new File(fileDir);  
        // 判断目录或文件是否存在    
        if (!file.exists()) {  // 不存在返回 false    
            return flag;    
        } else {    
            // 判断是否为文件    
            if (file.isFile()) {  // 为文件时调用删除文件方法    
                file.delete();  
                flag = true;  
            }   
        }  
        return flag;  
    }
    /**
	 * 将字符串转换为Date
	 * @return
	 * @throws ParseException
	 */
	public static Date StringToDate(String strDate) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date=sdf.parse(strDate);
		return date;
	}
	
	
	/* public static void main(String[] args) throws ParseException, IOException {  
	        // 测试自媒体  
	         ExportExcel<Media> ex = new ExportExcel<Media>();  
	         String[] headers = { "编号", "电话", "区域", "年龄", "性别" , "开通时间", "有效期","广告主编号","广告编号"};  
	         List<Media> dataset = new ArrayList<Media>();  
	         
	         Media m = new Media();
	         m.setAd_id(1);
	         m.setMedia_age(18);
	         m.setMedia_area("江北");
	         m.setMedia_id(1);
	         m.setMedia_phone("13678901324");
	         m.setMedia_sex("女");
	         m.setOperationtime(new Date());
	         m.setUser_id(1);
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 		 m.setValiditytime(sdf.parse("2015-11-20"));
	 		 dataset.add(m);
	 		 
	 		 OutputStream out = new FileOutputStream("G://a.xls");  
	         ex.exportExcel("你好",headers, dataset, out);  
	         out.close();  
	         System.out.println("excel导出成功！");  
			 //测试读取excel
			  //读取excel    
			 ExcelUtil<SchoolBackgroundData> ex = new ExcelUtil<SchoolBackgroundData>(); 
			 SchoolBackgroundData backgroundData = new SchoolBackgroundData();
			 String[] headers = { "序号", "学校", "学院", "系", "班级" , "角色", "校级学号", "姓名", "性别", "学生电话", "是否享受资助", "是否享受贫困资助", "缴费情况", "是否流旷", "半工半读"};
			 List list = ex.readFromExcel("G:/导出自媒体信息/15轨道1班注册簿-移动.xlsx", backgroundData,headers);
			 for(int i=0;i<list.size();i++){
				System.out.println(list.get(i).getClass());
				backgroundData =(SchoolBackgroundData) list.get(i);
				System.out.println(backgroundData.getStudent_name());
			 }
	      
	      } */
	
}

