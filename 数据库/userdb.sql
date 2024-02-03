/*
Navicat MySQL Data Transfer

Source Server         : Class
Source Server Version : 50647
Source Host           : localhost:3306
Source Database       : userdb

Target Server Type    : MYSQL
Target Server Version : 50647
File Encoding         : 65001

Date: 2024-02-03 15:03:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sign
-- ----------------------------
DROP TABLE IF EXISTS `sign`;
CREATE TABLE `sign` (
  `uId` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `lasttime` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign
-- ----------------------------
INSERT INTO `sign` VALUES ('1', '30', '2024-02-03');
INSERT INTO `sign` VALUES ('2', '13', '2024-02-03');
INSERT INTO `sign` VALUES ('3', '1', '2024-01-30');
INSERT INTO `sign` VALUES ('8', '1', '2024-01-30');
INSERT INTO `sign` VALUES ('9', '0', '2024-01-30');
INSERT INTO `sign` VALUES ('10', '1', '2024-01-30');
INSERT INTO `sign` VALUES ('11', '1', '2024-01-30');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uId` int(11) NOT NULL AUTO_INCREMENT,
  `uName` varchar(20) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `vip` int(11) DEFAULT NULL,
  `vipdate` date DEFAULT NULL,
  PRIMARY KEY (`uId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '小明', '男', '22', '18973160810', '1', '2024-12-29');
INSERT INTO `users` VALUES ('2', '小红', '女', '22', '13263635656', '1', '2025-08-03');
INSERT INTO `users` VALUES ('3', '小丽', '女', '45', '14789899898', '1', '2024-02-29');
INSERT INTO `users` VALUES ('8', '小娟', '女', '19', '18878788787', '1', '2024-03-29');
INSERT INTO `users` VALUES ('9', '小刚', '男', '25', '15565655656', '0', null);
INSERT INTO `users` VALUES ('10', '小雨', '女', '33', '12332322323', '1', '2024-02-29');
INSERT INTO `users` VALUES ('11', '小杰', '男', '18', '13363633636', '1', '2024-08-29');
INSERT INTO `users` VALUES ('12', '小西', '女', '24', '15645455454', '0', null);
