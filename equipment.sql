/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.21-log : Database - db_equipment
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_equipment` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_equipment`;

/*Table structure for table `t_department` */

DROP TABLE IF EXISTS `t_department`;

CREATE TABLE `t_department` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `dept_number` varchar(11) DEFAULT NULL COMMENT '部门编号',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `dept_introduce` varchar(200) DEFAULT NULL COMMENT '部门简介',
  `create_time` decimal(20,0) DEFAULT NULL COMMENT '创建时间',
  `update_time` decimal(20,0) DEFAULT NULL COMMENT '更新时间',
  `is_del` int(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `t_department` */

insert  into `t_department`(`id`,`dept_number`,`dept_name`,`dept_introduce`,`create_time`,`update_time`,`is_del`) values 
(1,'1001','管理1部','管理人员部门一分部',1611059019459,1611059019459,0),
(2,'2001','用户1部','普通用户部门一分部',1611059019459,1611059019459,0),
(3,'3001','维修1部','维修人员部门一分部',1611059019459,1611059019459,0),
(4,'1002','管理2部','管理人员部门二分部',1611733034325,1611733034325,0),
(5,'2002','用户2部','普通用户部门二分部',1611735762068,1611752781837,1),
(6,'2002','用户2部','11',1611755649176,1611755649176,1),
(7,'2002','用户2部','用户部门二分部。',1612010253273,1612010253273,0);

/*Table structure for table `t_equipment` */

DROP TABLE IF EXISTS `t_equipment`;

CREATE TABLE `t_equipment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `equip_number` varchar(20) DEFAULT NULL COMMENT '设备编号',
  `equip_type_number` varchar(20) DEFAULT NULL COMMENT '设备类型编号',
  `equip_name` varchar(20) DEFAULT NULL COMMENT '设备名称',
  `equip_summary` varchar(200) DEFAULT NULL COMMENT '设备概述',
  `equip_state` int(1) unsigned NOT NULL DEFAULT '0' COMMENT '设备状态 0 使用中 1 维修中 2 已报废',
  `create_time` decimal(20,0) DEFAULT NULL COMMENT '创建时间',
  `update_time` decimal(20,0) DEFAULT NULL COMMENT '更新时间',
  `is_del` int(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_equipment` */

insert  into `t_equipment`(`id`,`equip_number`,`equip_type_number`,`equip_name`,`equip_summary`,`equip_state`,`create_time`,`update_time`,`is_del`) values 
(1,'0001','001','发动机','康明斯柴油发动机组。',0,1611799347922,1612100162784,0),
(2,'0002','001','发动机','康明斯汽油发动机组。',0,1611799347922,1612101129210,0),
(3,'0003','002','破碎机','11111',0,1611823809583,1611823809583,1),
(4,'0004','004','搅拌机','LUM立式搅拌机。',0,1611824017887,1611824017887,1),
(5,'0003','004','搅拌机','LUM立式搅拌机。',0,1611826791793,1612168334202,0);

/*Table structure for table `t_equipment_type` */

DROP TABLE IF EXISTS `t_equipment_type`;

CREATE TABLE `t_equipment_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `equip_type_number` varchar(20) DEFAULT NULL COMMENT '设备类型编号',
  `equip_type_name` varchar(20) DEFAULT NULL COMMENT '设备类型名称',
  `equip_type_summary` varchar(200) DEFAULT NULL COMMENT '设备类型概述',
  `create_time` decimal(20,0) DEFAULT NULL COMMENT '创建时间',
  `update_time` decimal(20,0) DEFAULT NULL COMMENT '更新时间',
  `is_del` int(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `t_equipment_type` */

insert  into `t_equipment_type`(`id`,`equip_type_number`,`equip_type_name`,`equip_type_summary`,`create_time`,`update_time`,`is_del`) values 
(1,'001','康明斯系列','康明斯公司的冠名系列，主打发电机产品。',1611814438334,1611814438334,0),
(2,'002','HPT系列','高压试验（High-Pressure Test）的简称。',1611814438334,1611814438334,0),
(3,'003','VSI6X系列','采用全新四口叶轮设计结构和特殊密封结构系列。',1611814438334,1611814438334,0),
(4,'004','LUM立式','世邦集团结合多年磨机生产经验,以立式磨为基础，衍生出的系列。',1611814438334,1611814438334,0),
(5,'005','111','22222',1611906290992,1611906362395,1),
(6,'005','3333','333333',1611907705065,1611907705065,0);

/*Table structure for table `t_password_visible` */

DROP TABLE IF EXISTS `t_password_visible`;

CREATE TABLE `t_password_visible` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password_visible` varchar(50) DEFAULT NULL COMMENT '用户的可见密码 用户表加密不可见',
  `create_time` decimal(20,0) DEFAULT NULL COMMENT '创建时间',
  `update_time` decimal(20,0) DEFAULT NULL COMMENT '更新时间',
  `is_del` int(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `t_password_visible` */

insert  into `t_password_visible`(`id`,`user_name`,`password_visible`,`create_time`,`update_time`,`is_del`) values 
(1,'admin','123456',1611655086414,1611885110067,0),
(2,'zhangsan','123456',1611655295929,1611655295929,0),
(3,'lisi','123456',1611712483797,1611712483797,0),
(4,'wangwu','123456',1611712528480,1611712528480,0),
(5,'maliu','123456',1611712565681,1611712565681,0),
(6,'tianqi','123456',1611712595747,1611712595747,0),
(7,'user1','123456',1611712621612,1611712621612,0),
(8,'user2','456789',1611712649187,1611824103075,0),
(9,'chenq','19980519',1611712681896,1611712681896,1),
(10,'wufeng','789789',1611712705074,1611712705074,0),
(11,'tom','456456',1611712732245,1611712732245,0);

/*Table structure for table `t_position_apply` */

DROP TABLE IF EXISTS `t_position_apply`;

CREATE TABLE `t_position_apply` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `apply_number` varchar(50) DEFAULT NULL COMMENT '申请编号',
  `apply_type` int(1) DEFAULT NULL COMMENT '申请类型 0 申请管理员 2 申请维修员',
  `user_number` varchar(20) DEFAULT NULL COMMENT '申请人编号',
  `dept_number` varchar(20) DEFAULT NULL COMMENT '申请所在部门编号',
  `apply_reason` varchar(200) DEFAULT NULL COMMENT '申请理由',
  `apply_state` int(1) DEFAULT NULL COMMENT '申请状态 0 审批中 1 审批通过 2 审批驳回',
  `approver_name` varchar(20) DEFAULT NULL COMMENT '审批人',
  `approval_opinion` varchar(200) DEFAULT NULL COMMENT '审批意见',
  `create_time` decimal(20,0) DEFAULT NULL COMMENT '创建时间（申请时间）',
  `update_time` decimal(20,0) DEFAULT NULL COMMENT '更新时间（审批时间）',
  `is_del` int(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `t_position_apply` */

insert  into `t_position_apply`(`id`,`apply_number`,`apply_type`,`user_number`,`dept_number`,`apply_reason`,`apply_state`,`approver_name`,`approval_opinion`,`create_time`,`update_time`,`is_del`) values 
(1,'10020001',0,'2001001','1002','斯柯达斯柯达',1,'admin','予以通过',1612491396496,1612491440039,0),
(2,'10010001',0,'3001001','1001','111',1,'admin','予以通过',1612491732200,1612511746358,0),
(3,'10010002',0,'2001002','1001','撒打算发发发发',2,'zhangsan','理由不足',1612511893899,1612513718816,0),
(4,'10020002',0,'3001003','1002','asda',0,NULL,NULL,1612512763640,NULL,0);

/*Table structure for table `t_repair` */

DROP TABLE IF EXISTS `t_repair`;

CREATE TABLE `t_repair` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `repair_number` varchar(20) DEFAULT NULL COMMENT '维修编号',
  `equip_number` varchar(20) DEFAULT NULL COMMENT '设备编号',
  `reporter_number` varchar(20) DEFAULT NULL COMMENT '报修人编号',
  `report_time` decimal(20,0) DEFAULT NULL COMMENT '报修时间(创建时间)',
  `repairer_number` varchar(20) DEFAULT NULL COMMENT '维修人编号',
  `repair_time` decimal(20,0) DEFAULT NULL COMMENT '维修时间(更新时间)',
  `repair_state` int(1) unsigned NOT NULL DEFAULT '1' COMMENT '维修状态 0 维修完成 1 维修中 2 报废处理',
  `is_del` int(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_repair` */

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_type` int(1) DEFAULT NULL COMMENT '角色编号，角色类型',
  `role_auth` varchar(50) DEFAULT NULL COMMENT 'security权限认证角色名',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `create_time` decimal(20,0) DEFAULT NULL COMMENT '创建时间',
  `update_time` decimal(20,0) DEFAULT NULL COMMENT '更新时间',
  `is_del` int(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`role_type`,`role_auth`,`role_name`,`create_time`,`update_time`,`is_del`) values 
(1,0,'ROLE_ADMIN','管理员',1611058985202,1611058985202,0),
(2,1,'ROLE_USER','普通用户',1611058985202,1611058985202,0),
(3,2,'ROLE_REPAIR','维修员',1611058985202,1611058985202,0);

/*Table structure for table `t_role_dept` */

DROP TABLE IF EXISTS `t_role_dept`;

CREATE TABLE `t_role_dept` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_type` int(1) DEFAULT NULL COMMENT '角色编号，角色类型 0为管理员 1为普通用户 2为维修员',
  `dept_number` varchar(11) DEFAULT NULL COMMENT '部门编号',
  `create_time` decimal(20,0) DEFAULT NULL COMMENT '创建时间',
  `update_time` decimal(20,0) DEFAULT NULL COMMENT '更新时间',
  `is_del` int(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `t_role_dept` */

insert  into `t_role_dept`(`id`,`role_type`,`dept_number`,`create_time`,`update_time`,`is_del`) values 
(1,0,'1001',1611654916147,1611654916147,0),
(2,1,'2001',1611654916147,1611654916147,0),
(3,2,'3001',1611654916147,1611654916147,0),
(4,0,'1002',1611654916147,1611654916147,0),
(5,1,'2002',1611735762060,1611752781826,1),
(6,1,'2002',1611755649168,1611755649168,1),
(7,1,'2002',1612010253266,1612010253266,0);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `dept_number` varchar(11) DEFAULT NULL COMMENT '所在部门编号',
  `user_number` varchar(50) DEFAULT NULL COMMENT '用户编号',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `true_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `role_type` int(1) DEFAULT NULL COMMENT '角色编号，角色类型 0为管理员 1为普通用户 2为维修员',
  `create_time` decimal(20,0) DEFAULT NULL COMMENT '创建时间',
  `update_time` decimal(20,0) DEFAULT NULL COMMENT '更新时间',
  `is_del` int(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 0为存在 1为逻辑已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`dept_number`,`user_number`,`user_name`,`password`,`true_name`,`role_type`,`create_time`,`update_time`,`is_del`) values 
(1,'1001','1001001','admin','$2a$10$Xt2X3HwdydlLsyItNtG0I.JBn9e9z2DZLm1fipdALnQc9BvxSFQQK','管理员',0,1611655086533,1611885110076,0),
(2,'1002','2001001','zhangsan','$2a$10$iSDMfDLKvfkdUF55HwQv5uGga9O4yADGztdQj/6Q9.YgW8xCnbW5a','张三',0,1611655296036,1612491440047,0),
(3,'1001','3001001','lisi','$2a$10$XfDWBl0LdnzU1Bx/e2xS6udl1Hu6BuhkxmekGNw.bZ768N0vzL0IK','李四',0,1611712483912,1612511746438,0),
(4,'1001','1001002','wangwu','$2a$10$uzgPkN2RbHnkKMT6c6u40u/iVxIQCwMlDCxD3pFz0DFkybPP5SZmS','王五',0,1611712528588,1611712528588,0),
(5,'2001','2001002','maliu','$2a$10$JydHrKxM3aMqi9MHUOmmreogiHM1a61Rc27lNum3Io941B4PZwJOm','马六',1,1611712565786,1611712565786,0),
(6,'3001','3001002','tianqi','$2a$10$5enDpxacqULv/Avc014W.OQACMOqRl0SF8geRxgPsjGouK/HOV7XK','田七',2,1611712595854,1611712595854,0),
(7,'2001','2001003','user1','$2a$10$HSxYWzd0ETllonUzWcRmX.2pHG/NLF3yL1UJf841qyjv6arz0OReC','用户1',1,1611712621725,1611712621725,0),
(8,'2001','2001004','user2','$2a$10$BAp50chDgYwW6tyjG87cbeZfiwwwsIuAVpoLo41sp3zuaix8XTs86','游客',1,1611712649296,1611824103085,0),
(9,'1001','1001003','chenq','$2a$10$Y2BT64L5YQpB2sj6zpPyQ.IaAICH4Bedqj2p6qg/VrN2nnMgI0mVK','cq',0,1611712682004,1611712682004,1),
(10,'3001','3001003','wufeng','$2a$10$zx5AX70/fIJgtDvYDtZRBeUz9sot6mv3y.L/wVZU1P20QtQHKv9IO','吴峰',2,1611712705180,1611712705180,0),
(11,'3001','3001004','tom','$2a$10$8KHKZAaEwOUkeALarG8JAOHnqMxSarVKlzuqAybW2dzbpuFv7xsJG','汤姆',2,1611712732352,1611712732352,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
