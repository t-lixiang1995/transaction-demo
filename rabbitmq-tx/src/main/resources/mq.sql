/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : mq

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2019-06-25 17:03:27
*/
create database mq,mq2,mq3
-- ----------------------------
-- 三个数据库依次执行下列语句
-- ----------------------------

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_advertisement`
-- ----------------------------
DROP TABLE IF EXISTS `t_advertisement`;
CREATE TABLE `t_advertisement` (
  `id` bigint(50) NOT NULL COMMENT '主键',
  `shop_id` bigint(50) DEFAULT NULL COMMENT '商品id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='商品详情表';

-- ----------------------------
-- Records of t_advertisement
-- ----------------------------
INSERT INTO `t_advertisement` VALUES ('1', '2500', 'ipad');
INSERT INTO `t_advertisement` VALUES ('1241412', '20002141', '这就是大灌篮');
INSERT INTO `t_advertisement` VALUES ('1561450469273', '2000', '这就是大灌篮');

-- ----------------------------
-- Table structure for `t_balance_flow`
-- ----------------------------
DROP TABLE IF EXISTS `t_balance_flow`;
CREATE TABLE `t_balance_flow` (
  `shop_id` bigint(50) NOT NULL COMMENT '商品id',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `cost` int(20) DEFAULT NULL COMMENT '消费金额',
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='账户流水表';

-- ----------------------------
-- Records of t_balance_flow
-- ----------------------------

-- ----------------------------
-- Table structure for `t_exposure_touch_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_exposure_touch_record`;
CREATE TABLE `t_exposure_touch_record` (
  `shop_id` bigint(50) NOT NULL COMMENT '商品id',
  `user_id` bigint(50) DEFAULT NULL COMMENT '用户id',
  `cost` int(20) DEFAULT NULL COMMENT '消费金额',
  `exposure_time` date DEFAULT NULL COMMENT '曝光时间',
  `ad_id` bigint(50) DEFAULT NULL COMMENT '广告id',
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='账单详情表（曝光流水）';

-- ----------------------------
-- Records of t_exposure_touch_record
-- ----------------------------

-- ----------------------------
-- Table structure for `t_shop_balance`
-- ----------------------------
DROP TABLE IF EXISTS `t_shop_balance`;
CREATE TABLE `t_shop_balance` (
  `shop_id` bigint(50) NOT NULL COMMENT '商品id',
  `balance` int(20) DEFAULT NULL COMMENT '账户余额',
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='账户扣费表';

-- ----------------------------
-- Records of t_shop_balance
-- ----------------------------
INSERT INTO `t_shop_balance` VALUES ('2000', '3500');
