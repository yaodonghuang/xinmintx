/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50713
 Source Host           : localhost:3306
 Source Schema         : agent

 Target Server Type    : MySQL
 Target Server Version : 50713
 File Encoding         : 65001

 Date: 11/11/2019 20:10:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for merchant
-- ----------------------------
DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NULL DEFAULT NULL COMMENT '订单id',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `idcard` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `recommender` int(11) NULL DEFAULT NULL COMMENT '推荐人(用户表id)',
  `merchant_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户名称',
  `merchant_type` tinyint(1) NULL DEFAULT NULL COMMENT '入驻类型(1,基本商户;2黄金商户)',
  `merchant_table` int(11) NULL DEFAULT NULL COMMENT '桌数',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户地址',
  `region_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区名称',
  `region_code` char(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域代码',
  `merchant_branch_office_id` int(11) NULL DEFAULT NULL COMMENT '商户所属的分公司id',
  `turnover` decimal(10, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '商户营业额',
  `is_review` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否审核（0,否;1,是）',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_user` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `update_user` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `merchant_order_id_fk`(`order_id`) USING BTREE,
  INDEX `merchant_recommender_fk`(`recommender`) USING BTREE,
  INDEX `merchant_merchant_branch_office_id_fk`(`merchant_branch_office_id`) USING BTREE,
  CONSTRAINT `merchant_merchant_branch_office_id_fk` FOREIGN KEY (`merchant_branch_office_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `merchant_order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `merchant_recommender_fk` FOREIGN KEY (`recommender`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商家信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `out_trade_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单交易号',
  `prepay_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方交易标示(微信，支付宝下单返回)',
  `goods_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单描述(商品名称)',
  `total_fee` bigint(20) NULL DEFAULT NULL COMMENT '支付金额(单位分)',
  `pay_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式(1,微信;2支付宝,3,银行卡)',
  `pay_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付状态(0,未支付;1,已支付;2,已退款)',
  `transaction_id` int(11) NULL DEFAULT NULL COMMENT '支付交易号(微信支付宝支付完成后交易流水号)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for region
-- ----------------------------
DROP TABLE IF EXISTS `region`;
CREATE TABLE `region`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `regionCode` char(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '区域代码',
  `province` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '市',
  `district` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区',
  `pyCode` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '拼音码',
  `parent` char(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上级区域代码',
  `level` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '区域等级(1:省,2:市,3:区/县)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_regionCode`(`regionCode`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47476 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '全国省市区（县）基础数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for silver
-- ----------------------------
DROP TABLE IF EXISTS `silver`;
CREATE TABLE `silver`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `cellphone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别(0,未知;1,男;2,女)',
  `recommender` int(11) NULL DEFAULT NULL COMMENT '推荐人(用户表id)',
  `is_review` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否审核（0,否;1,是）',
  `create_user` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人姓名',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `silver_recommender_fk`(`recommender`) USING BTREE,
  INDEX `silver_order_id_fk`(`order_id`) USING BTREE,
  CONSTRAINT `silver_order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `silver_recommender_fk` FOREIGN KEY (`recommender`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '银卡信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for unit_photo
-- ----------------------------
DROP TABLE IF EXISTS `unit_photo`;
CREATE TABLE `unit_photo`  (
  `id` int(11) NOT NULL,
  `company_id` int(11) NOT NULL COMMENT '分公司主键(用户表)/商户表主键(商户表)',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '照片类型(1:身份证正面,2:身份证反面,3:门头照片,4:商户店内照片,5:营业执照)',
  `photo_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '照片地址',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `unit_photo_unit_info_id_fk`(`company_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '照片' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NULL DEFAULT NULL COMMENT '订单id',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `cellphone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `gender` tinyint(1) NULL DEFAULT NULL COMMENT '性别(0,未知;1,男;2,女)',
  `idcard` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `recommender` int(11) NULL DEFAULT NULL COMMENT '推荐人(用户表id)',
  `user_role` tinyint(1) NOT NULL COMMENT '用户角色(1,boss;2,合伙人;3,分公司;4,联合创始人;5,代理;6,员工)',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '账号状态(0:不可用,1:可用,2:未付款)',
  `amount` int(11) NULL DEFAULT NULL COMMENT '代理推荐的商户数量',
  `company_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分公司名称',
  `company_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分公司地址',
  `region_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区名称',
  `region_code` char(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域代码',
  `is_review` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否审核（0,否;1,是）',
  `create_user` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人姓名',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `last_login` timestamp(0) NULL DEFAULT NULL COMMENT '最后登陆时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_cellphone_uindex`(`cellphone`) USING BTREE,
  INDEX `user_order_id_fk`(`order_id`) USING BTREE,
  CONSTRAINT `user_order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
