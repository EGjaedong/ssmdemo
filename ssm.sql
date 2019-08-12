/*
Navicat MySQL Data Transfer

Source Server         : dmc
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-08-12 16:54:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `phoneNum` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('1', '张三', '小三', '18888888888', 'zs@163.com');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderNum` varchar(20) NOT NULL,
  `orderTime` timestamp NULL DEFAULT NULL,
  `peopleCount` int(11) DEFAULT NULL,
  `orderDesc` varchar(500) DEFAULT NULL,
  `payType` int(11) DEFAULT NULL,
  `orderStatus` int(11) DEFAULT NULL,
  `productId` int(11) DEFAULT NULL,
  `memberId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `orderNum` (`orderNum`),
  KEY `productId` (`productId`),
  KEY `memberId` (`memberId`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `product` (`id`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`memberId`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', '12345', '2019-06-25 11:51:43', '2', '没什么', '0', '1', '1', '1');
INSERT INTO `orders` VALUES ('2', '54321', '2019-06-25 11:51:43', '2', '没什么', '0', '1', '1', '1');
INSERT INTO `orders` VALUES ('3', '67890', '2019-06-25 11:51:43', '2', '没什么', '0', '1', '2', '1');
INSERT INTO `orders` VALUES ('4', '98765', '2019-06-25 11:51:43', '2', '没什么', '0', '1', '2', '1');
INSERT INTO `orders` VALUES ('5', '11111', '2019-06-25 11:51:43', '2', '没什么', '0', '1', '3', '1');
INSERT INTO `orders` VALUES ('6', '22222', '2019-06-25 11:51:43', '2', '没什么', '0', '1', '3', '1');
INSERT INTO `orders` VALUES ('7', '33333', '2019-06-25 11:51:44', '2', '没什么', '0', '1', '4', '1');
INSERT INTO `orders` VALUES ('8', '44444', '2019-06-25 11:51:44', '2', '没什么', '0', '1', '4', '1');
INSERT INTO `orders` VALUES ('9', '55555', '2019-06-25 11:51:44', '2', '没什么', '0', '1', '5', '1');

-- ----------------------------
-- Table structure for order_traveller
-- ----------------------------
DROP TABLE IF EXISTS `order_traveller`;
CREATE TABLE `order_traveller` (
  `orderId` int(11) NOT NULL,
  `travellerId` int(11) NOT NULL,
  PRIMARY KEY (`orderId`,`travellerId`),
  KEY `travellerId` (`travellerId`),
  CONSTRAINT `order_traveller_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `orders` (`id`),
  CONSTRAINT `order_traveller_ibfk_2` FOREIGN KEY (`travellerId`) REFERENCES `traveller` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_traveller
-- ----------------------------
INSERT INTO `order_traveller` VALUES ('1', '1');
INSERT INTO `order_traveller` VALUES ('2', '1');
INSERT INTO `order_traveller` VALUES ('5', '1');
INSERT INTO `order_traveller` VALUES ('7', '1');
INSERT INTO `order_traveller` VALUES ('3', '2');
INSERT INTO `order_traveller` VALUES ('4', '2');
INSERT INTO `order_traveller` VALUES ('6', '2');
INSERT INTO `order_traveller` VALUES ('8', '2');
INSERT INTO `order_traveller` VALUES ('9', '2');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permissionName` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'user findAll', '/user/findAll.do');
INSERT INTO `permission` VALUES ('2', 'user findById', '/user/findById.do');
INSERT INTO `permission` VALUES ('3', 'role findAll', '/role/findAll.do');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productNum` varchar(50) NOT NULL,
  `productName` varchar(50) DEFAULT NULL,
  `cityName` varchar(50) DEFAULT NULL,
  `DepartureTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `productPrice` decimal(10,0) DEFAULT NULL,
  `productDesc` varchar(500) DEFAULT NULL,
  `productStatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', 'itcast-002', '北京三日游', '北京', '2019-06-24 16:54:43', '1200', '不错的旅行', '1');
INSERT INTO `product` VALUES ('2', 'itcast-003', '上海五日游', '上海', '2019-06-24 16:54:43', '1800', '魔都我来了', '0');
INSERT INTO `product` VALUES ('3', 'itcast-001', '北京三日游', '北京', '2019-06-24 16:54:43', '1200', '不错的旅行', '1');
INSERT INTO `product` VALUES ('4', 'itcast-004', '广州三日游', '广州', '2019-06-25 03:00:00', '1500', '广州是个好地方', '1');
INSERT INTO `product` VALUES ('5', 'itcast-005', '哈尔滨三日游', '哈尔滨', '2019-06-25 03:05:00', '1800', '哈尔滨冰雪大世界', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(50) DEFAULT NULL,
  `roleDesc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'USER', 'vip');
INSERT INTO `role` VALUES ('2', 'ADMIN', 'vip');
INSERT INTO `role` VALUES ('3', 'guest', '测试账户');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `permissionId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`permissionId`,`roleId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`permissionId`) REFERENCES `permission` (`id`),
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1');
INSERT INTO `role_permission` VALUES ('2', '1');
INSERT INTO `role_permission` VALUES ('3', '1');
INSERT INTO `role_permission` VALUES ('1', '2');
INSERT INTO `role_permission` VALUES ('1', '3');

-- ----------------------------
-- Table structure for syslog
-- ----------------------------
DROP TABLE IF EXISTS `syslog`;
CREATE TABLE `syslog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `visitTime` timestamp NULL DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `ip` varchar(30) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `executionTime` int(11) DEFAULT NULL,
  `method` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of syslog
-- ----------------------------
INSERT INTO `syslog` VALUES ('2', '2019-07-18 14:36:19', 'user', '0:0:0:0:0:0:0:1', '/product/findAll.do', '25', '[类名] cn.webdemo.ssm.controller.ProductorController[方法名] findAll');
INSERT INTO `syslog` VALUES ('3', '2019-07-18 14:36:27', 'user', '0:0:0:0:0:0:0:1', '/orders/findAll.do', '112', '[类名] cn.webdemo.ssm.controller.OrdersController[方法名] findAll');
INSERT INTO `syslog` VALUES ('4', '2019-07-18 14:36:52', 'tom', '0:0:0:0:0:0:0:1', '/permission/findAll.do', '10', '[类名] cn.webdemo.ssm.controller.PermissionController[方法名] findAll');
INSERT INTO `syslog` VALUES ('5', '2019-07-18 14:48:28', 'user', '0:0:0:0:0:0:0:1', '/permission/findAll.do', '15', '[类名] cn.webdemo.ssm.controller.PermissionController[方法名] findAll');
INSERT INTO `syslog` VALUES ('6', '2019-07-18 14:48:30', 'user', '0:0:0:0:0:0:0:1', '/sysLog/findAll.do', '11', '[类名] cn.webdemo.ssm.controller.SysLogController[方法名] findAll');
INSERT INTO `syslog` VALUES ('7', '2019-07-18 15:47:13', 'user', '0:0:0:0:0:0:0:1', '/product/findAll.do', '43', '[类名] cn.webdemo.ssm.controller.ProductorController[方法名] findAll');
INSERT INTO `syslog` VALUES ('8', '2019-07-18 16:05:34', 'user', '0:0:0:0:0:0:0:1', '/user/findAll.do', '18', '[类名] cn.webdemo.ssm.controller.UserController[方法名] findAll');
INSERT INTO `syslog` VALUES ('9', '2019-07-31 08:05:56', 'admin', '0:0:0:0:0:0:0:1', '/user/findAll.do', '23', '[类名] cn.webdemo.ssm.controller.UserController[方法名] findAll');
INSERT INTO `syslog` VALUES ('10', '2019-07-31 08:06:05', 'admin', '0:0:0:0:0:0:0:1', '/role/findAll.do', '12', '[类名] cn.webdemo.ssm.controller.RoleController[方法名] findAll');

-- ----------------------------
-- Table structure for traveller
-- ----------------------------
DROP TABLE IF EXISTS `traveller`;
CREATE TABLE `traveller` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `phoneNum` varchar(20) DEFAULT NULL,
  `credentialsType` int(11) DEFAULT NULL,
  `credentialsNum` varchar(50) DEFAULT NULL,
  `travellerType` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of traveller
-- ----------------------------
INSERT INTO `traveller` VALUES ('1', '张龙', '男', '13333333333', '0', '123456789009876543', '0');
INSERT INTO `traveller` VALUES ('2', '张小龙', '男', '15555555555', '0', '987654321123456789', '1');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phoneNum` varchar(20) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '111@qq.com', 'user', '$2a$10$UJpd9W00zzMAtbEhD7Fl6O3Vyj4XJu0Fh1F7pO1CLLWM5mZMgxYeq', '13333333333', '1');
INSERT INTO `users` VALUES ('2', '222@qq.com', 'admin', '$2a$10$FsLHmMGZf6y7keWpYI2u2.6.H35UKFsXSYTdvJ3SFPYHaul.yQH4q', '12222222222', '1');
INSERT INTO `users` VALUES ('3', 'tom@123.com', 'tom', '$2a$10$7bAJEkMMT.cQERChqE6Jce0O/qseud9tfoz/MI.AaUu7N08/QDGl2', '13511111111', '1');

-- ----------------------------
-- Table structure for users_role
-- ----------------------------
DROP TABLE IF EXISTS `users_role`;
CREATE TABLE `users_role` (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `users_role_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`),
  CONSTRAINT `users_role_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users_role
-- ----------------------------
INSERT INTO `users_role` VALUES ('1', '1');
INSERT INTO `users_role` VALUES ('2', '1');
INSERT INTO `users_role` VALUES ('3', '1');
INSERT INTO `users_role` VALUES ('1', '2');
INSERT INTO `users_role` VALUES ('2', '2');
