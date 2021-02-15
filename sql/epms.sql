/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50646
 Source Host           : localhost:3306
 Source Schema         : epms

 Target Server Type    : MySQL
 Target Server Version : 50646
 File Encoding         : 65001

 Date: 31/05/2020 13:29:44
*/

create database IF NOT EXISTS epms character set utf8;
set global character_set_database=utf8;
set global character_set_server=utf8;
USE epms ;

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- ----------------------------
-- Table structure for tb_schedule
-- ----------------------------
DROP TABLE IF EXISTS `tb_schedule`;
CREATE TABLE `tb_schedule`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PERSON_ID` varchar(20) NOT NULL,
  `PERSON_NAME` varchar(20) NOT NULL,
  `SCHEDULE_START` datetime NOT NULL,
  `SCHEDULE_END` datetime NOT NULL,
  `SCHEDULE_PLACE` varchar(20) NOT NULL,
  `SCHEDULE_REASON` varchar(255) NOT NULL,
  `EXPLAINS` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7;

-- ----------------------------
-- Records of tb_schedule
-- ----------------------------
INSERT INTO `tb_schedule` VALUES (1, 1, '张三', '2020-05-29 00:00:00','2020-11-29 00:00:00', '大连', '张三出差事由','备注');
