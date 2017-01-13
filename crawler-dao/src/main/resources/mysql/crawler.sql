/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50611
Source Host           : localhost:3306
Source Database       : crawler

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2017-01-13 15:32:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_home_page`
-- ----------------------------
DROP TABLE IF EXISTS `t_home_page`;
CREATE TABLE `t_home_page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteName` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `dynamic` tinyint(4) DEFAULT NULL,
  `stop` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_home_page
-- ----------------------------

-- ----------------------------
-- Table structure for `t_image_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_image_info`;
CREATE TABLE `t_image_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `targetId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_image_info
-- ----------------------------

-- ----------------------------
-- Table structure for `t_target_page`
-- ----------------------------
DROP TABLE IF EXISTS `t_target_page`;
CREATE TABLE `t_target_page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `homePageId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_target_page
-- ----------------------------
