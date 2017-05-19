/*
Navicat MySQL Data Transfer

Source Server         : ifmtech
Source Server Version : 50627
Source Host           : 192.168.1.12:3306
Source Database       : merise

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2017-03-29 15:30:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for estate_building_detail
-- ----------------------------
DROP TABLE IF EXISTS `estate_building_detail`;
CREATE TABLE `estate_building_detail` (
  `building_id` varchar(50) NOT NULL,
  `estate_id` varchar(50) DEFAULT NULL,
  `building_developer` varchar(500) DEFAULT NULL,
  `building_projectname` varchar(500) DEFAULT NULL,
  `building_name` varchar(200) DEFAULT NULL,
  `building_location` varchar(500) DEFAULT NULL,
  `building_structure` varchar(200) DEFAULT NULL,
  `building_type` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`building_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='楼盘信息表';

-- ----------------------------
-- Table structure for estate_code_detail
-- ----------------------------
DROP TABLE IF EXISTS `estate_code_detail`;
CREATE TABLE `estate_code_detail` (
  `code_id` varchar(50) NOT NULL,
  `building_id` varchar(50) DEFAULT NULL,
  `code_name` varchar(200) DEFAULT NULL,
  `code_content` varchar(200) DEFAULT NULL,
  `code_sequence` int(11) DEFAULT NULL,
  `code_remark` varchar(500) DEFAULT NULL,
  `estate_id` varchar(50) DEFAULT NULL COMMENT '物业编号',
  PRIMARY KEY (`code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='编码配置表，系统常用配置类，例如：（房屋结构：钢筋混凝土、彩钢结构）';

-- ----------------------------
-- Table structure for estate_config
-- ----------------------------
DROP TABLE IF EXISTS `estate_config`;
CREATE TABLE `estate_config` (
  `config_id` varchar(50) NOT NULL,
  `owner_id` varchar(50) DEFAULT NULL,
  `owner_table` varchar(500) DEFAULT NULL,
  `col_name` varchar(500) DEFAULT NULL,
  `col_context` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物业信息配置表';

-- ----------------------------
-- Table structure for estate_house_detail
-- ----------------------------
DROP TABLE IF EXISTS `estate_house_detail`;
CREATE TABLE `estate_house_detail` (
  `house_id` varchar(50) NOT NULL,
  `building_id` varchar(50) DEFAULT NULL,
  `house_dong` varchar(50) DEFAULT NULL,
  `house_units` varchar(50) DEFAULT NULL,
  `house_floor` varchar(50) DEFAULT NULL,
  `house_room` varchar(50) DEFAULT NULL,
  `house_location` varchar(500) DEFAULT NULL,
  `house_type` varchar(50) DEFAULT NULL,
  `house_orientation` varchar(50) DEFAULT NULL,
  `house_build_area` varchar(50) DEFAULT NULL,
  `house_in_area` varchar(50) DEFAULT NULL,
  `house_status` varchar(50) DEFAULT NULL,
  `house_sort` varchar(50) DEFAULT NULL COMMENT '商铺、房屋、车库、公共设施、别墅',
  `dong_structure` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`house_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房屋信息表';

-- ----------------------------
-- Table structure for estate_info_detail
-- ----------------------------
DROP TABLE IF EXISTS `estate_info_detail`;
CREATE TABLE `estate_info_detail` (
  `estate_id` varchar(50) NOT NULL,
  `estate_name` varchar(500) DEFAULT NULL,
  `estate_location` varchar(500) DEFAULT NULL,
  `estate_phone` varchar(500) DEFAULT NULL,
  `estate_head` varchar(500) DEFAULT NULL,
  `estate_head_phone` varchar(500) DEFAULT NULL,
  `estate_remark` varchar(500) DEFAULT NULL,
  `estate_total_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`estate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物业信息表';

-- ----------------------------
-- Table structure for estate_owner_detail
-- ----------------------------
DROP TABLE IF EXISTS `estate_owner_detail`;
CREATE TABLE `estate_owner_detail` (
  `owner_id` varchar(50) NOT NULL,
  `owner_name` varchar(50) DEFAULT NULL,
  `owner_mobile` varchar(500) DEFAULT NULL,
  `owner_idcard` varchar(500) DEFAULT NULL,
  `owner_status` varchar(50) DEFAULT NULL,
  `owner_family` varchar(500) DEFAULT NULL,
  `owner_level` varchar(50) DEFAULT NULL,
  `owner_bank_account` varchar(500) DEFAULT NULL,
  `owner_bank_name` varchar(500) DEFAULT NULL,
  `owner_bank_branch` varchar(500) DEFAULT NULL,
  `owner_remark` varchar(4000) DEFAULT NULL,
  `owner_email` varchar(500) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  `money` int(11) DEFAULT '0',
  PRIMARY KEY (`owner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业主信息表';

-- ----------------------------
-- Table structure for estate_owner_house_detail
-- ----------------------------
DROP TABLE IF EXISTS `estate_owner_house_detail`;
CREATE TABLE `estate_owner_house_detail` (
  `id` varchar(50) NOT NULL,
  `owner_id` varchar(50) DEFAULT NULL,
  `house_id` varchar(50) DEFAULT NULL,
  `garage_start_date` datetime DEFAULT NULL COMMENT '当物业状态为车库时可用',
  `garage_finish_date` datetime DEFAULT NULL,
  `live_name` varchar(500) DEFAULT NULL,
  `live_mobile` varchar(500) DEFAULT NULL,
  `live_idcard` varchar(500) DEFAULT NULL,
  `owner_relation` varchar(500) DEFAULT NULL,
  `living_email` varchar(500) DEFAULT NULL,
  `car_num` varchar(500) DEFAULT NULL,
  `charge_standard` varchar(500) DEFAULT NULL,
  `contract_time` datetime DEFAULT NULL,
  `house_time` datetime DEFAULT NULL,
  `decorate_time` datetime DEFAULT NULL,
  `live_time` datetime DEFAULT NULL,
  `termination_time` datetime DEFAULT NULL,
  `termination_reason` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业主房屋信息表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '用户名称',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `mobile` varchar(20) NOT NULL COMMENT '手机号码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱地址',
  `actived` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否活动',
  `registrationID` varchar(50) DEFAULT NULL,
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastLoginTime` datetime DEFAULT NULL,
  `type` smallint(1) DEFAULT '1' COMMENT '0公司账号，1普通账号',
  `remark` varchar(45) DEFAULT NULL,
  `hxName` varchar(32) DEFAULT NULL,
  `functionCustom` varchar(45) DEFAULT NULL COMMENT '自定义功能模块',
  `nickName` varchar(11) DEFAULT NULL COMMENT '昵称',
  `sign` varchar(255) DEFAULT NULL COMMENT '签名',
  `token` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for t_announcement
-- ----------------------------
DROP TABLE IF EXISTS `t_announcement`;
CREATE TABLE `t_announcement` (
  `id` varchar(50) NOT NULL,
  `type` int(2) DEFAULT NULL COMMENT '公告类型:0 按小区 ,1 按栋',
  `building_id` varchar(50) DEFAULT NULL COMMENT '楼盘编号或房屋编号',
  `house_dong` varchar(50) DEFAULT NULL,
  `title` varchar(500) DEFAULT NULL COMMENT '公告标题',
  `content` varchar(500) DEFAULT NULL COMMENT '公告内容',
  `is_send` int(11) DEFAULT NULL COMMENT '是否定时发送',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `priority` int(11) DEFAULT NULL COMMENT '优先级',
  `status` int(11) DEFAULT NULL COMMENT '发送状态',
  `createtime` datetime DEFAULT NULL COMMENT '公告创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_announcement_log
-- ----------------------------
DROP TABLE IF EXISTS `t_announcement_log`;
CREATE TABLE `t_announcement_log` (
  `id` varchar(50) NOT NULL,
  `title` varchar(500) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_appoint_service
-- ----------------------------
DROP TABLE IF EXISTS `t_appoint_service`;
CREATE TABLE `t_appoint_service` (
  `service_id` varchar(50) NOT NULL,
  `service_picurl` text,
  `service_name` varchar(500) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `charge_standard` varchar(2000) DEFAULT NULL,
  `service_phone` varchar(500) DEFAULT NULL,
  `service_des` varchar(2000) DEFAULT NULL,
  `building_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预约服务表';

-- ----------------------------
-- Table structure for t_billing_type
-- ----------------------------
DROP TABLE IF EXISTS `t_billing_type`;
CREATE TABLE `t_billing_type` (
  `billing_type_id` varchar(50) NOT NULL,
  `building_id` varchar(50) DEFAULT NULL,
  `type_name` varchar(200) DEFAULT NULL,
  `type_remark` varchar(500) DEFAULT NULL,
  `status` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`billing_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='计费类别表';

-- ----------------------------
-- Table structure for t_bill_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_bill_detail`;
CREATE TABLE `t_bill_detail` (
  `bill_id` varchar(50) NOT NULL,
  `bill_name` varchar(50) DEFAULT NULL,
  `bill_type` varchar(50) DEFAULT NULL,
  `bill_time` datetime DEFAULT NULL,
  `bill_number` varchar(50) DEFAULT NULL,
  `employees_id` varchar(50) DEFAULT NULL,
  `bill_starNum` varchar(50) DEFAULT NULL,
  `bill_endNum` varchar(50) DEFAULT NULL,
  `bill_operator` varchar(50) DEFAULT NULL,
  `building_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`bill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='票据记录表';

-- ----------------------------
-- Table structure for t_charge_standard
-- ----------------------------
DROP TABLE IF EXISTS `t_charge_standard`;
CREATE TABLE `t_charge_standard` (
  `standard_id` varchar(50) NOT NULL,
  `billing_type_id` varchar(50) DEFAULT NULL,
  `project_name` varchar(500) DEFAULT NULL,
  `denominated_unit` varchar(500) DEFAULT NULL,
  `standard_price` int(11) DEFAULT NULL,
  `coefficient` double(8,2) DEFAULT NULL,
  `house_dong` varchar(500) DEFAULT NULL,
  `house_units` varchar(500) DEFAULT NULL,
  `start_floor` varchar(500) DEFAULT NULL,
  `end_floor` varchar(500) DEFAULT NULL,
  `premium` double(8,2) DEFAULT NULL,
  `status` varchar(500) DEFAULT NULL,
  `remark` varchar(2000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `charge_unit` varchar(50) DEFAULT NULL,
  `charge_num` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`standard_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收费标准表';

-- ----------------------------
-- Table structure for t_emp
-- ----------------------------
DROP TABLE IF EXISTS `t_emp`;
CREATE TABLE `t_emp` (
  `emp_id` varchar(50) NOT NULL,
  `emp_name` varchar(200) DEFAULT NULL,
  `emp_sex` varchar(200) DEFAULT NULL,
  `emp_dept` varchar(200) DEFAULT NULL,
  `emp_job` varchar(200) DEFAULT NULL,
  `emp_phone` varchar(200) DEFAULT NULL,
  `emp_education` varchar(200) DEFAULT NULL,
  `graduate_school` varchar(200) DEFAULT NULL,
  `emp_time` datetime DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `qq` varchar(200) DEFAULT NULL,
  `id_number` varchar(200) DEFAULT NULL,
  `live_addr` varchar(200) DEFAULT NULL,
  `emp_wechat` varchar(200) DEFAULT NULL,
  `emp_email` varchar(200) DEFAULT NULL,
  `emp_remark` varchar(2000) DEFAULT NULL,
  `building_id` varchar(50) DEFAULT NULL,
  `expect_salary` double(10,2) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应聘人员表';

-- ----------------------------
-- Table structure for t_employees
-- ----------------------------
DROP TABLE IF EXISTS `t_employees`;
CREATE TABLE `t_employees` (
  `employees_id` varchar(50) NOT NULL,
  `employees_name` varchar(200) DEFAULT NULL,
  `employees_sex` varchar(200) DEFAULT NULL,
  `employees_dept` varchar(200) DEFAULT NULL,
  `employees_job` varchar(200) DEFAULT NULL,
  `induction_time` datetime DEFAULT NULL,
  `employees_phone` varchar(200) DEFAULT NULL,
  `employees_education` varchar(200) DEFAULT NULL,
  `graduate_school` varchar(200) DEFAULT NULL,
  `is_positive` varchar(200) DEFAULT NULL,
  `office_status` varchar(200) DEFAULT NULL,
  `departure_time` datetime DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `qq` varchar(200) DEFAULT NULL,
  `id_number` varchar(200) DEFAULT NULL,
  `live_addr` varchar(200) DEFAULT NULL,
  `employees_wechat` varchar(200) DEFAULT NULL,
  `employees_email` varchar(200) DEFAULT NULL,
  `employees_remark` varchar(2000) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  `building_id` varchar(50) DEFAULT NULL,
  `salary` double(10,2) DEFAULT NULL,
  `contract_start` datetime DEFAULT NULL,
  `contract_end` datetime DEFAULT NULL,
  PRIMARY KEY (`employees_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工信息表';

-- ----------------------------
-- Table structure for t_employees_event
-- ----------------------------
DROP TABLE IF EXISTS `t_employees_event`;
CREATE TABLE `t_employees_event` (
  `event_id` varchar(50) NOT NULL,
  `employees_id` varchar(50) DEFAULT NULL,
  `event_name` varchar(500) DEFAULT NULL,
  `event_context` text,
  `event_time` datetime DEFAULT NULL,
  `status` varchar(500) DEFAULT NULL,
  `remark` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工事件明细表';

-- ----------------------------
-- Table structure for t_house_charge_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_house_charge_relation`;
CREATE TABLE `t_house_charge_relation` (
  `relation_id` varchar(50) NOT NULL,
  `house_id` varchar(50) DEFAULT NULL,
  `standard_id` varchar(50) DEFAULT NULL,
  `remark` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`relation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房屋收费项目关系表';

-- ----------------------------
-- Table structure for t_house_number
-- ----------------------------
DROP TABLE IF EXISTS `t_house_number`;
CREATE TABLE `t_house_number` (
  `house_number_id` varchar(50) NOT NULL,
  `house_id` varchar(50) NOT NULL,
  `electricity_number` varchar(500) DEFAULT NULL,
  `water_number` varchar(500) DEFAULT NULL,
  `gas_number` varchar(500) DEFAULT NULL,
  `status` varchar(500) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `nper` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`house_number_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_image
-- ----------------------------
DROP TABLE IF EXISTS `t_image`;
CREATE TABLE `t_image` (
  `image_id` varchar(50) NOT NULL,
  `image_name` varchar(500) DEFAULT NULL,
  `image_remark` varchar(2000) DEFAULT NULL,
  `image_url` text,
  `image_date` datetime DEFAULT NULL,
  `image_owner_table` varchar(500) DEFAULT NULL,
  `image_owner_id` varchar(500) DEFAULT NULL,
  `image_order` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图片表';

-- ----------------------------
-- Table structure for t_message_task
-- ----------------------------
DROP TABLE IF EXISTS `t_message_task`;
CREATE TABLE `t_message_task` (
  `message_id` varchar(50) NOT NULL COMMENT '消息推送编号',
  `message_title` varchar(1000) DEFAULT NULL COMMENT '消息标题',
  `message_content` varchar(4000) DEFAULT NULL COMMENT '消息内容',
  `message_hour` varchar(2) DEFAULT NULL COMMENT '推送时间(小时)',
  `message_app` int(11) DEFAULT '0' COMMENT 'APP推送状态(0不自动,1自动)',
  `message_mail` int(11) DEFAULT '0' COMMENT '邮箱推送状态(0不自动,1自动)',
  `message_sms` int(11) DEFAULT '0' COMMENT '短信推送状态(0不自动,1自动)',
  `message_status` int(11) DEFAULT '0' COMMENT '推送状态(0未推送,1已推送)',
  `building_id` varchar(50) DEFAULT NULL COMMENT '楼盘编号',
  `house_dong` varchar(255) DEFAULT NULL COMMENT '房屋栋号',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建用户编号',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_newspaper_repairs
-- ----------------------------
DROP TABLE IF EXISTS `t_newspaper_repairs`;
CREATE TABLE `t_newspaper_repairs` (
  `repairs_id` varchar(50) NOT NULL,
  `newspaper_id` varchar(50) DEFAULT NULL,
  `employees_id` varchar(50) DEFAULT NULL,
  `repairs_type` varchar(50) DEFAULT NULL,
  `repairs_time` datetime DEFAULT NULL,
  `repairs_status` varchar(500) DEFAULT NULL,
  `repairs_remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`repairs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报事报修处理记录表';

-- ----------------------------
-- Table structure for t_newspaper_report
-- ----------------------------
DROP TABLE IF EXISTS `t_newspaper_report`;
CREATE TABLE `t_newspaper_report` (
  `newspaper_id` varchar(50) NOT NULL,
  `newspaper_type` varchar(50) DEFAULT NULL,
  `report_type` varchar(50) DEFAULT NULL,
  `newspaper_name` varchar(50) DEFAULT NULL,
  `newspaper_content` varchar(500) DEFAULT NULL,
  `newspaper_time` datetime DEFAULT NULL,
  `newspaper_status` varchar(50) DEFAULT NULL,
  `newspaper_remark` varchar(50) DEFAULT NULL,
  `owner_id` varchar(50) DEFAULT NULL,
  `house_id` varchar(50) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`newspaper_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报事记录表';

-- ----------------------------
-- Table structure for t_number_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_number_detail`;
CREATE TABLE `t_number_detail` (
  `detail_id` varchar(50) NOT NULL,
  `house_number_id` varchar(50) NOT NULL,
  `electricity_start` varchar(500) DEFAULT NULL,
  `electricity_end` varchar(500) DEFAULT NULL,
  `water_start` varchar(500) DEFAULT NULL,
  `water_end` varchar(500) DEFAULT NULL,
  `gas_start` varchar(500) DEFAULT NULL,
  `gas_end` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` varchar(500) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_owner_bill
-- ----------------------------
DROP TABLE IF EXISTS `t_owner_bill`;
CREATE TABLE `t_owner_bill` (
  `bill_id` varchar(50) NOT NULL COMMENT '账单编号',
  `house_id` varchar(50) DEFAULT NULL COMMENT '房屋编号',
  `owner_id` varchar(50) DEFAULT NULL COMMENT '业主编号',
  `standard_id` varchar(50) DEFAULT NULL COMMENT '收费标准编号',
  `standard_name` varchar(500) DEFAULT NULL COMMENT '收费标准名字',
  `batch` varchar(50) DEFAULT NULL COMMENT '期数',
  `money` text COMMENT '金额',
  `status` int(11) DEFAULT NULL COMMENT '0未缴费，1已缴费',
  `pay_date` datetime DEFAULT NULL COMMENT '缴费时间',
  `house_number_id` varchar(50) DEFAULT NULL COMMENT '房屋户号编号',
  `expire_date` datetime DEFAULT NULL COMMENT '到期时间',
  `paycost_moneny` varchar(50) DEFAULT NULL,
  `receivable_moneny` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`bill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业主账单表';

-- ----------------------------
-- Table structure for t_paycost
-- ----------------------------
DROP TABLE IF EXISTS `t_paycost`;
CREATE TABLE `t_paycost` (
  `paycost_id` varchar(50) NOT NULL,
  `paycost_project` varchar(500) DEFAULT NULL,
  `paycost_way` varchar(50) DEFAULT NULL,
  `paycost_moneny` varchar(50) DEFAULT NULL,
  `paycost_balance` varchar(50) DEFAULT NULL,
  `paycost_time` datetime DEFAULT NULL,
  `house_id` varchar(50) DEFAULT NULL,
  `owner_id` varchar(50) DEFAULT NULL,
  `bill_id` varchar(50) DEFAULT NULL COMMENT '账单编号',
  `receivable_moneny` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`paycost_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_paycost_item
-- ----------------------------
DROP TABLE IF EXISTS `t_paycost_item`;
CREATE TABLE `t_paycost_item` (
  `id` varchar(50) NOT NULL,
  `should_amount` varchar(50) DEFAULT NULL,
  `paid_amount` varchar(50) DEFAULT NULL,
  `unpaid_amount` varchar(50) DEFAULT NULL,
  `paycost_project` varchar(50) DEFAULT NULL,
  `paycost_time` datetime DEFAULT NULL,
  `paycost_id` varchar(50) DEFAULT NULL,
  `batch` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_public_facilities
-- ----------------------------
DROP TABLE IF EXISTS `t_public_facilities`;
CREATE TABLE `t_public_facilities` (
  `public_facilities_id` varchar(50) NOT NULL,
  `facilities_name` varchar(500) DEFAULT NULL,
  `facilities_count` varchar(500) DEFAULT NULL,
  `facilities_status` varchar(500) DEFAULT NULL,
  `facilities_location` varchar(500) DEFAULT NULL,
  `facilities_type` varchar(500) DEFAULT NULL,
  `facilities_linkman` varchar(500) DEFAULT NULL,
  `facilities_remark` varchar(4000) DEFAULT NULL,
  `building_id` varchar(50) DEFAULT NULL,
  `house_dong` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`public_facilities_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公共设施表';

-- ----------------------------
-- Table structure for t_sms_code
-- ----------------------------
DROP TABLE IF EXISTS `t_sms_code`;
CREATE TABLE `t_sms_code` (
  `sms_code_id` varchar(20) NOT NULL COMMENT '编号',
  `sms_code_mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `sms_code_content` varchar(20) DEFAULT NULL COMMENT '验证码',
  `sms_code_time` text COMMENT '发送时间',
  PRIMARY KEY (`sms_code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_train_info
-- ----------------------------
DROP TABLE IF EXISTS `t_train_info`;
CREATE TABLE `t_train_info` (
  `train_id` varchar(50) NOT NULL,
  `train_name` varchar(50) DEFAULT NULL,
  `train_info_name` varchar(50) DEFAULT NULL,
  `train_content` varchar(500) DEFAULT NULL,
  `train_time` datetime DEFAULT NULL,
  `train_status` varchar(50) DEFAULT NULL,
  `train_remark` varchar(50) DEFAULT NULL,
  `estate_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`train_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='培训信息记录表';

-- ----------------------------
-- Table structure for t_turn
-- ----------------------------
DROP TABLE IF EXISTS `t_turn`;
CREATE TABLE `t_turn` (
  `turn_id` varchar(50) NOT NULL,
  `house_id` varchar(50) DEFAULT NULL,
  `turn_moneny` varchar(50) DEFAULT NULL,
  `dept_no` varchar(50) DEFAULT NULL,
  `over_people` varchar(50) DEFAULT NULL,
  `recipient_perople` varchar(50) DEFAULT NULL,
  `turn_time` datetime DEFAULT NULL,
  PRIMARY KEY (`turn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user_employees
-- ----------------------------
DROP TABLE IF EXISTS `t_user_employees`;
CREATE TABLE `t_user_employees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户编号',
  `employees_id` varchar(50) DEFAULT NULL COMMENT '员工编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user_extend
-- ----------------------------
DROP TABLE IF EXISTS `t_user_extend`;
CREATE TABLE `t_user_extend` (
  `extend_id` varchar(50) NOT NULL COMMENT '扩展编号',
  `user_id` varchar(100) DEFAULT NULL COMMENT '用户编号',
  `client_id` varchar(200) DEFAULT NULL COMMENT '用于推送的客户端唯一标识',
  `owner_id` varchar(200) DEFAULT NULL COMMENT '关联业主编号',
  PRIMARY KEY (`extend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user_opinion
-- ----------------------------
DROP TABLE IF EXISTS `t_user_opinion`;
CREATE TABLE `t_user_opinion` (
  `opinion_id` varchar(20) NOT NULL COMMENT '意见编号',
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户编号',
  `opinion_content` varchar(4000) DEFAULT NULL COMMENT '意见内容',
  `opinion_date` datetime DEFAULT NULL COMMENT '创建时间',
  `opinion_reply` varchar(4000) DEFAULT NULL COMMENT '处理意见',
  `opinion_type` varchar(100) DEFAULT NULL COMMENT '意见分类',
  PRIMARY KEY (`opinion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Procedure structure for num_from_employee
-- ----------------------------
DROP PROCEDURE IF EXISTS `num_from_employee`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `num_from_employee`(IN emp_id INT, OUT count_num INT)
    READS SQL DATA
BEGIN  
              SELECT  COUNT(*)  INTO  count_num  
              FROM  estate_config  
              WHERE  config_id=emp_id ;  
          END
;;
DELIMITER ;
