/*
 Navicat Premium Data Transfer

 Source Server         : 服务器
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 192.168.1.23:3306
 Source Schema         : qwk

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 12/07/2023 11:02:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent` int(0) NOT NULL COMMENT '父级',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `zip_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮编',
  `CREATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '地址管理 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, 0, '测试', '132', NULL, NULL, NULL, NULL);
INSERT INTO `address` VALUES (2, 0, '测试2', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `address` VALUES (3, 1, '测试-1', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for asset_details
-- ----------------------------
DROP TABLE IF EXISTS `asset_details`;
CREATE TABLE `asset_details`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `user_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户手机号',
  `type` int(0) NULL DEFAULT NULL COMMENT '类型',
  `add_or_subtract` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '添加或者相减(0:减;1:加)',
  `records` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '总额变化记录',
  `balance` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '余额',
  `notes` varchar(3072) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `CREATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '资产详情 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of asset_details
-- ----------------------------
INSERT INTO `asset_details` VALUES (1, 1, '11111111111', 1, '1', '100', '1000', NULL, NULL, '2023-06-07 15:18:29', NULL, '2023-06-30 00:00:00');
INSERT INTO `asset_details` VALUES (2, 1, '11111111', 2, '0', '100', '1000', NULL, NULL, '2023-06-29 15:18:42', NULL, '2023-06-30 00:00:00');
INSERT INTO `asset_details` VALUES (3, 1, '1111111111', 3, '1', '500', '1000', NULL, NULL, '2023-06-26 15:18:49', NULL, '2023-06-30 00:00:00');
INSERT INTO `asset_details` VALUES (4, 100, '11111111', 1, '1', '100', '1000', NULL, NULL, '2023-07-03 15:18:56', NULL, '2023-06-24 00:00:00');
INSERT INTO `asset_details` VALUES (5, 100, '111111111', 2, '1', '100', '1000', NULL, NULL, '2023-07-01 15:19:01', NULL, '2023-06-30 00:00:00');
INSERT INTO `asset_details` VALUES (6, 100, '111111111111', 3, '1', '1000', '1000', NULL, NULL, '2023-07-04 15:19:06', NULL, '2023-06-30 00:00:00');
INSERT INTO `asset_details` VALUES (7, 1, '111111', 1, '0', '100', '900', '积分兑换商品', 'admin', '2023-07-11 12:08:28', NULL, NULL);
INSERT INTO `asset_details` VALUES (8, 1, '111111', 1, '0', '100', '800', '积分兑换商品', 'admin', '2023-07-11 12:12:24', NULL, NULL);
INSERT INTO `asset_details` VALUES (9, 1, '111111', 1, '0', '100', '700', '积分兑换商品', 'admin', '2023-07-11 12:18:14', NULL, NULL);
INSERT INTO `asset_details` VALUES (10, 1, '18571940502', 1, '1', '100', '800', '商品兑换积分', 'admin', '2023-07-11 19:01:33', NULL, NULL);
INSERT INTO `asset_details` VALUES (11, 1, '18571940502', 1, '1', '100', '900', '商品兑换积分', 'admin', '2023-07-11 19:02:15', NULL, NULL);

-- ----------------------------
-- Table structure for exchange_ratio
-- ----------------------------
DROP TABLE IF EXISTS `exchange_ratio`;
CREATE TABLE `exchange_ratio`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `goods_proportion` int(0) NOT NULL COMMENT '商品占比',
  `integral_proportion` int(0) NOT NULL COMMENT '积分占比',
  `applicable` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '适用商品',
  `enable_or_not` int(0) NULL DEFAULT 0 COMMENT '是否启用(0:停用,1:启用)',
  `CREATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '兑换比例 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exchange_ratio
-- ----------------------------
INSERT INTO `exchange_ratio` VALUES (1, 1, 1, '0', 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for game_user
-- ----------------------------
DROP TABLE IF EXISTS `game_user`;
CREATE TABLE `game_user`  (
  `id` bigint(0) NOT NULL COMMENT 'id',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `image` varchar(3072) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` int(0) NULL DEFAULT NULL COMMENT '性别',
  `id_card` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号',
  `wechat` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联微信',
  `CREATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '游戏用户 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '代码生成业务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES (1, 'address', '地址管理 ', '', '', 'Address', 'tree', 'com.ruoyi.qwk', 'qwk', 'address', '地址管理 ', 'cjf', '0', '/', '{\"treeCode\":\"id\",\"treeName\":\"name\",\"treeParentCode\":\"parent\",\"parentMenuId\":1}', 'admin', '2023-06-26 10:46:09', '', '2023-06-26 10:54:05', NULL);
INSERT INTO `gen_table` VALUES (3, 'homepage_config', '首页配置 ', NULL, NULL, 'HomepageConfig', 'crud', 'com.ruoyi.qwk', 'qwk', 'config', '首页配置 ', 'cjf', '0', '/', '{}', 'admin', '2023-06-26 15:47:05', '', '2023-06-26 15:49:01', NULL);
INSERT INTO `gen_table` VALUES (6, 'product_specifications', '商品规格 ', NULL, NULL, 'ProductSpecifications', 'crud', 'com.ruoyi.qwk', 'qwk', 'specifications', '商品规格 ', 'cjf', '0', '/', '{}', 'admin', '2023-06-28 10:27:30', '', '2023-06-28 10:29:39', NULL);
INSERT INTO `gen_table` VALUES (8, 'exchange_ratio', '兑换比例 ', NULL, NULL, 'ExchangeRatio', 'crud', 'com.ruoyi.qwk', 'qwk', 'ratio', '积分兑换比例设置', 'cjf', '0', '/', '{\"parentMenuId\":2022}', 'admin', '2023-06-28 16:40:18', '', '2023-06-28 16:49:49', NULL);
INSERT INTO `gen_table` VALUES (9, 'warehouse', '仓库 ', NULL, NULL, 'Warehouse', 'crud', 'com.ruoyi.qwk', 'qwk', 'warehouse', '仓库 ', 'cjf', '0', '/', '{\"parentMenuId\":2022}', 'admin', '2023-06-29 17:24:46', '', '2023-06-29 17:26:00', NULL);
INSERT INTO `gen_table` VALUES (13, 'user_assets', '用户资产 ', NULL, NULL, 'UserAssets', 'crud', 'com.ruoyi.qwk', 'qwk', 'assets', '用户资产 ', 'cjf', '0', '/', '{\"parentMenuId\":2022}', 'admin', '2023-06-29 20:17:14', '', '2023-06-29 20:18:09', NULL);
INSERT INTO `gen_table` VALUES (14, 'money_ratio', '充值档位及比例 ', NULL, NULL, 'MoneyRatio', 'crud', 'com.ruoyi.qwk', 'qwk', 'moneyRatio', '充值档位及比例 ', 'cjf', '0', '/', '{\"parentMenuId\":\"2047\"}', 'admin', '2023-06-30 15:16:43', '', '2023-06-30 15:37:51', NULL);
INSERT INTO `gen_table` VALUES (17, 'logistics', '物流 ', NULL, NULL, 'Logistics', 'crud', 'com.ruoyi.qwk', 'qwk', 'logistics', '物流 ', 'cjf', '0', '/', '{}', 'admin', '2023-06-30 17:09:35', '', '2023-06-30 17:55:16', NULL);
INSERT INTO `gen_table` VALUES (23, 'goods', '商品 ', NULL, NULL, 'Goods', 'crud', 'com.ruoyi.qwk', 'qwk', 'goods', '商品 ', 'cjf', '0', '/', '{}', 'admin', '2023-07-03 15:52:34', '', '2023-07-03 16:50:24', NULL);
INSERT INTO `gen_table` VALUES (24, 'take_delivery_address', '收货地址', NULL, NULL, 'TakeDeliveryAddress', 'crud', 'com.ruoyi.qwk', 'qwk', 'takeDeliveryAddress', 'takeDeliveryAddress', 'cjf', '0', '/', '{\"parentMenuId\":3}', 'admin', '2023-07-03 18:52:36', '', '2023-07-03 18:54:49', NULL);
INSERT INTO `gen_table` VALUES (25, 'recharge_bill', '充值流水 ', NULL, NULL, 'RechargeBill', 'crud', 'com.ruoyi.qwk', 'qwk', 'bill', '充值流水 ', 'cjf', '0', '/', NULL, 'admin', '2023-07-04 10:30:32', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (26, 'asset_details', '资产详情 ', NULL, NULL, 'AssetDetails', 'crud', 'com.ruoyi.qwk', 'qwk', 'details', '资产详情 ', 'cjf', '0', '/', '{}', 'admin', '2023-07-04 14:38:23', '', '2023-07-04 15:06:04', NULL);
INSERT INTO `gen_table` VALUES (30, 'points_details', '仓库商品积分详情 ', NULL, NULL, 'PointsDetails', 'crud', 'com.ruoyi.qwk', 'qwk', 'pointsDetails', '仓库商品积分详情 ', 'cjf', '0', '/', '{}', 'admin', '2023-07-10 18:31:53', '', '2023-07-10 18:35:57', NULL);
INSERT INTO `gen_table` VALUES (31, 'orders', '商品订单 ', NULL, NULL, 'Orders', 'crud', 'com.ruoyi.qwk', 'qwk', 'orders', '商品订单 ', 'cjf', '0', '/', NULL, 'admin', '2023-07-11 09:44:54', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (33, 'warehouse_details', '仓库详情 ', NULL, NULL, 'WarehouseDetails', 'crud', 'com.ruoyi.qwk', 'qwk', 'details', '仓库详情 ', 'cjf', '0', '/', NULL, 'admin', '2023-07-11 15:59:41', '', NULL, NULL);

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 435 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (1, '1', 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-06-26 10:46:09', '', '2023-06-26 10:54:05');
INSERT INTO `gen_table_column` VALUES (2, '1', 'parent', '父级', 'int', 'Long', 'parent', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-06-26 10:46:09', '', '2023-06-26 10:54:06');
INSERT INTO `gen_table_column` VALUES (3, '1', 'name', '名称', 'varchar(64)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-06-26 10:46:09', '', '2023-06-26 10:54:06');
INSERT INTO `gen_table_column` VALUES (4, '1', 'zip_code', '邮编', 'varchar(32)', 'String', 'zipCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-06-26 10:46:09', '', '2023-06-26 10:54:06');
INSERT INTO `gen_table_column` VALUES (5, '1', 'CREATED_BY', '创建人', 'varchar(32)', 'String', 'createdBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-06-26 10:46:09', '', '2023-06-26 10:54:06');
INSERT INTO `gen_table_column` VALUES (6, '1', 'CREATED_TIME', '创建时间', 'datetime', 'Date', 'createdTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 6, 'admin', '2023-06-26 10:46:09', '', '2023-06-26 10:54:06');
INSERT INTO `gen_table_column` VALUES (7, '1', 'UPDATED_BY', '更新人', 'varchar(32)', 'String', 'updatedBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-06-26 10:46:09', '', '2023-06-26 10:54:06');
INSERT INTO `gen_table_column` VALUES (8, '1', 'UPDATED_TIME', '更新时间', 'datetime', 'Date', 'updatedTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 8, 'admin', '2023-06-26 10:46:09', '', '2023-06-26 10:54:06');
INSERT INTO `gen_table_column` VALUES (22, '3', 'id', 'id id唯一标识', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-06-26 15:47:05', '', '2023-06-26 15:49:01');
INSERT INTO `gen_table_column` VALUES (23, '3', 'sort', '排序', 'int', 'Long', 'sort', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-06-26 15:47:05', '', '2023-06-26 15:49:01');
INSERT INTO `gen_table_column` VALUES (24, '3', 'classify', '分类', 'int', 'Long', 'classify', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', 'wtb_home_sort', 3, 'admin', '2023-06-26 15:47:05', '', '2023-06-26 15:49:01');
INSERT INTO `gen_table_column` VALUES (25, '3', 'img_url', '图片', 'varchar(32)', 'String', 'imgUrl', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2023-06-26 15:47:05', '', '2023-06-26 15:49:01');
INSERT INTO `gen_table_column` VALUES (26, '3', 'wide', '宽', 'varchar(32)', 'String', 'wide', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-06-26 15:47:05', '', '2023-06-26 15:49:01');
INSERT INTO `gen_table_column` VALUES (27, '3', 'high', '高', 'varchar(32)', 'String', 'high', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-06-26 15:47:05', '', '2023-06-26 15:49:01');
INSERT INTO `gen_table_column` VALUES (28, '3', 'Link_Address', '链接地址', 'varchar(32)', 'String', 'linkAddress', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 7, 'admin', '2023-06-26 15:47:05', '', '2023-06-26 15:49:01');
INSERT INTO `gen_table_column` VALUES (29, '3', 'Creation_time', '加入时间', 'datetime', 'Date', 'creationTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 8, 'admin', '2023-06-26 15:47:05', '', '2023-06-26 15:49:01');
INSERT INTO `gen_table_column` VALUES (30, '3', 'state', '状态', 'int', 'Long', 'state', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', 'wtb_on_off', 9, 'admin', '2023-06-26 15:47:05', '', '2023-06-26 15:49:01');
INSERT INTO `gen_table_column` VALUES (31, '3', 'Display_Status', '显示状态', 'int', 'Long', 'displayStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', 'wtb_on_off', 10, 'admin', '2023-06-26 15:47:05', '', '2023-06-26 15:49:01');
INSERT INTO `gen_table_column` VALUES (32, '3', 'Display_Location', '显示位置', 'int', 'Long', 'displayLocation', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', 'wtb_display_location', 11, 'admin', '2023-06-26 15:47:05', '', '2023-06-26 15:49:01');
INSERT INTO `gen_table_column` VALUES (33, '3', 'UPDATED_BY', '更新人', 'varchar(64)', 'String', 'updatedBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2023-06-26 15:47:05', '', '2023-06-26 15:49:01');
INSERT INTO `gen_table_column` VALUES (34, '3', 'UPDATED_TIME', '更新时间', 'datetime', 'Date', 'updatedTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 13, 'admin', '2023-06-26 15:47:05', '', '2023-06-26 15:49:01');
INSERT INTO `gen_table_column` VALUES (73, '6', 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, '1', 'EQ', 'input', '', 1, 'admin', '2023-06-28 10:27:30', '', '2023-06-28 10:29:39');
INSERT INTO `gen_table_column` VALUES (74, '6', 'name', '规格名称', 'varchar(32)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2023-06-28 10:27:30', '', '2023-06-28 10:29:39');
INSERT INTO `gen_table_column` VALUES (75, '6', 'value', '规格值', 'varchar(3072)', 'String', 'value', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 3, 'admin', '2023-06-28 10:27:30', '', '2023-06-28 10:29:39');
INSERT INTO `gen_table_column` VALUES (76, '6', 'CREATED_TIME', '创建时间', 'datetime', 'Date', 'createdTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 4, 'admin', '2023-06-28 10:27:30', '', '2023-06-28 10:29:39');
INSERT INTO `gen_table_column` VALUES (77, '6', 'UPDATED_BY', '更新人', 'varchar(32)', 'String', 'updatedBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-06-28 10:27:30', '', '2023-06-28 10:29:39');
INSERT INTO `gen_table_column` VALUES (78, '6', 'UPDATED_TIME', '更新时间', 'datetime', 'Date', 'updatedTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 6, 'admin', '2023-06-28 10:27:31', '', '2023-06-28 10:29:39');
INSERT INTO `gen_table_column` VALUES (99, '8', 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, '1', 'EQ', 'input', '', 1, 'admin', '2023-06-28 16:40:19', '', '2023-06-28 16:49:49');
INSERT INTO `gen_table_column` VALUES (100, '8', 'goods_proportion', '商品占比', 'int', 'Long', 'goodsProportion', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-06-28 16:40:19', '', '2023-06-28 16:49:49');
INSERT INTO `gen_table_column` VALUES (101, '8', 'integral_proportion', '积分占比', 'int', 'Long', 'integralProportion', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-06-28 16:40:19', '', '2023-06-28 16:49:49');
INSERT INTO `gen_table_column` VALUES (102, '8', 'applicable', '适用商品', 'varchar(32)', 'String', 'applicable', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', 'wtb_applicable', 4, 'admin', '2023-06-28 16:40:19', '', '2023-06-28 16:49:49');
INSERT INTO `gen_table_column` VALUES (103, '8', 'enable_or_not', '是否启用', 'int', 'Long', 'enableOrNot', '0', '0', '0', '0', '0', '1', '1', 'EQ', 'radio', 'sys_yes_no', 5, 'admin', '2023-06-28 16:40:19', '', '2023-06-28 16:49:49');
INSERT INTO `gen_table_column` VALUES (104, '8', 'CREATED_BY', '创建人', 'varchar(32)', 'String', 'createdBy', '0', '0', NULL, '0', '0', '0', '1', 'EQ', 'input', '', 6, 'admin', '2023-06-28 16:40:19', '', '2023-06-28 16:49:49');
INSERT INTO `gen_table_column` VALUES (105, '8', 'CREATED_TIME', '创建时间', 'datetime', 'Date', 'createdTime', '0', '0', NULL, '0', '0', '0', '1', 'BETWEEN', 'datetime', '', 7, 'admin', '2023-06-28 16:40:19', '', '2023-06-28 16:49:49');
INSERT INTO `gen_table_column` VALUES (106, '8', 'UPDATED_BY', '更新人', 'varchar(32)', 'String', 'updatedBy', '0', '0', NULL, '0', '0', '0', '1', 'EQ', 'input', '', 8, 'admin', '2023-06-28 16:40:19', '', '2023-06-28 16:49:49');
INSERT INTO `gen_table_column` VALUES (107, '8', 'UPDATED_TIME', '更新时间', 'datetime', 'Date', 'updatedTime', '0', '0', NULL, '0', '0', '1', '1', 'BETWEEN', 'datetime', '', 9, 'admin', '2023-06-28 16:40:19', '', '2023-06-28 16:49:49');
INSERT INTO `gen_table_column` VALUES (108, '9', 'id', '唯一标识', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-06-29 17:24:46', '', '2023-06-29 17:26:00');
INSERT INTO `gen_table_column` VALUES (109, '9', 'user_id', '用户id', 'int', 'Long', 'userId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-06-29 17:24:46', '', '2023-06-29 17:26:00');
INSERT INTO `gen_table_column` VALUES (110, '9', 'nick_name', '用户昵称', 'varchar(128)', 'String', 'nickName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-06-29 17:24:47', '', '2023-06-29 17:26:00');
INSERT INTO `gen_table_column` VALUES (111, '9', 'user_phone', '用户手机号', 'varchar(32)', 'String', 'userPhone', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2023-06-29 17:24:47', '', '2023-06-29 17:26:00');
INSERT INTO `gen_table_column` VALUES (112, '9', 'quantity', '商品数量', 'int', 'Long', 'quantity', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-06-29 17:24:47', '', '2023-06-29 17:26:00');
INSERT INTO `gen_table_column` VALUES (113, '9', 'total_price', '商品总价', 'varchar(128)', 'String', 'totalPrice', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-06-29 17:24:47', '', '2023-06-29 17:26:00');
INSERT INTO `gen_table_column` VALUES (114, '9', 'UPDATED_BY', '更新人', 'varchar(32)', 'String', 'updatedBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-06-29 17:24:47', '', '2023-06-29 17:26:00');
INSERT INTO `gen_table_column` VALUES (115, '9', 'UPDATED_TIME', '更新时间', 'datetime', 'Date', 'updatedTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 8, 'admin', '2023-06-29 17:24:47', '', '2023-06-29 17:26:00');
INSERT INTO `gen_table_column` VALUES (147, '13', 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-06-29 20:17:14', '', '2023-06-29 20:18:09');
INSERT INTO `gen_table_column` VALUES (148, '13', 'user_id', '用户id', 'int', 'Long', 'userId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-06-29 20:17:14', '', '2023-06-29 20:18:09');
INSERT INTO `gen_table_column` VALUES (149, '13', 'user_phone', '用户手机号', 'varchar(32)', 'String', 'userPhone', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-06-29 20:17:14', '', '2023-06-29 20:18:09');
INSERT INTO `gen_table_column` VALUES (150, '13', 'nick_name', '用户昵称', 'varchar(32)', 'String', 'nickName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2023-06-29 20:17:14', '', '2023-06-29 20:18:09');
INSERT INTO `gen_table_column` VALUES (151, '13', 'type', '资产类型', 'int', 'Long', 'type', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', 'wtb_asset_type', 5, 'admin', '2023-06-29 20:17:14', '', '2023-06-29 20:18:09');
INSERT INTO `gen_table_column` VALUES (152, '13', 'total', '当前总额', 'varchar(64)', 'String', 'total', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-06-29 20:17:14', '', '2023-06-29 20:18:09');
INSERT INTO `gen_table_column` VALUES (153, '13', 'CREATED_BY', '创建人', 'varchar(32)', 'String', 'createdBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-06-29 20:17:14', '', '2023-06-29 20:18:09');
INSERT INTO `gen_table_column` VALUES (154, '13', 'CREATED_TIME', '创建时间', 'datetime', 'Date', 'createdTime', '0', '0', NULL, '1', '1', '1', '1', 'BETWEEN', 'datetime', '', 8, 'admin', '2023-06-29 20:17:14', '', '2023-06-29 20:18:09');
INSERT INTO `gen_table_column` VALUES (155, '13', 'UPDATED_BY', '更新人', 'varchar(32)', 'String', 'updatedBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-06-29 20:17:14', '', '2023-06-29 20:18:09');
INSERT INTO `gen_table_column` VALUES (156, '13', 'UPDATED_TIME', '更新时间', 'datetime', 'Date', 'updatedTime', '0', '0', NULL, '1', '1', '1', '1', 'BETWEEN', 'datetime', '', 10, 'admin', '2023-06-29 20:17:14', '', '2023-06-29 20:18:09');
INSERT INTO `gen_table_column` VALUES (157, '14', 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-06-30 15:16:43', '', '2023-06-30 15:37:51');
INSERT INTO `gen_table_column` VALUES (158, '14', 'type', '充值类型', 'int', 'Long', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', 'wtb_asset_type', 2, 'admin', '2023-06-30 15:16:43', '', '2023-06-30 15:37:51');
INSERT INTO `gen_table_column` VALUES (159, '14', 'gear_position', '充值档位', 'varchar(3072)', 'String', 'gearPosition', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 3, 'admin', '2023-06-30 15:16:43', '', '2023-06-30 15:37:51');
INSERT INTO `gen_table_column` VALUES (160, '14', 'money_proportion', '现金占比', 'varchar(64)', 'String', 'moneyProportion', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-06-30 15:16:43', '', '2023-06-30 15:37:51');
INSERT INTO `gen_table_column` VALUES (161, '14', 'unreal_proportion', '虚拟占比', 'varchar(64)', 'String', 'unrealProportion', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-06-30 15:16:43', '', '2023-06-30 15:37:51');
INSERT INTO `gen_table_column` VALUES (162, '14', 'CREATED_BY', '创建人', 'varchar(32)', 'String', 'createdBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-06-30 15:16:43', '', '2023-06-30 15:37:51');
INSERT INTO `gen_table_column` VALUES (163, '14', 'CREATED_TIME', '创建时间', 'datetime', 'Date', 'createdTime', '0', '0', NULL, '1', '1', '1', '1', 'BETWEEN', 'datetime', '', 7, 'admin', '2023-06-30 15:16:43', '', '2023-06-30 15:37:51');
INSERT INTO `gen_table_column` VALUES (164, '14', 'UPDATED_BY', '更新人', 'varchar(32)', 'String', 'updatedBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-06-30 15:16:43', '', '2023-06-30 15:37:51');
INSERT INTO `gen_table_column` VALUES (165, '14', 'UPDATED_TIME', '更新时间', 'datetime', 'Date', 'updatedTime', '0', '0', NULL, '1', '1', '1', '1', 'BETWEEN', 'datetime', '', 9, 'admin', '2023-06-30 15:16:43', '', '2023-06-30 15:37:51');
INSERT INTO `gen_table_column` VALUES (191, '17', 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-06-30 17:09:36', '', '2023-06-30 17:55:16');
INSERT INTO `gen_table_column` VALUES (192, '17', 'order_number', '订单编号', 'varchar(64)', 'String', 'orderNumber', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2023-06-30 17:09:36', '', '2023-06-30 17:55:16');
INSERT INTO `gen_table_column` VALUES (193, '17', 'goods_name', '商品名称', 'varchar(64)', 'String', 'goodsName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-06-30 17:09:36', '', '2023-06-30 17:55:16');
INSERT INTO `gen_table_column` VALUES (194, '17', 'goods_image', '商品图片', 'varchar(1024)', 'String', 'goodsImage', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'imageUpload', '', 4, 'admin', '2023-06-30 17:09:36', '', '2023-06-30 17:55:16');
INSERT INTO `gen_table_column` VALUES (195, '17', 'consignee_name', '收货人姓名', 'varchar(64)', 'String', 'consigneeName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 5, 'admin', '2023-06-30 17:09:36', '', '2023-06-30 17:55:16');
INSERT INTO `gen_table_column` VALUES (196, '17', 'consignee_phone', '收货人手机号', 'varchar(64)', 'String', 'consigneePhone', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 6, 'admin', '2023-06-30 17:09:36', '', '2023-06-30 17:55:16');
INSERT INTO `gen_table_column` VALUES (197, '17', 'tracking_number', '快递单号', 'varchar(64)', 'String', 'trackingNumber', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 7, 'admin', '2023-06-30 17:09:36', '', '2023-06-30 17:55:16');
INSERT INTO `gen_table_column` VALUES (198, '17', 'express_company', '快递公司', 'varchar(64)', 'String', 'expressCompany', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 8, 'admin', '2023-06-30 17:09:36', '', '2023-06-30 17:55:16');
INSERT INTO `gen_table_column` VALUES (199, '17', 'consignee_Address', '收货地址', 'varchar(128)', 'String', 'consigneeAddress', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 9, 'admin', '2023-06-30 17:09:36', '', '2023-06-30 17:55:16');
INSERT INTO `gen_table_column` VALUES (200, '17', 'CREATED_BY', '创建人', 'varchar(32)', 'String', 'createdBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-06-30 17:09:36', '', '2023-06-30 17:55:17');
INSERT INTO `gen_table_column` VALUES (201, '17', 'CREATED_TIME', '创建时间', 'datetime', 'Date', 'createdTime', '0', '0', NULL, '1', '1', '1', '1', 'BETWEEN', 'datetime', '', 11, 'admin', '2023-06-30 17:09:36', '', '2023-06-30 17:55:17');
INSERT INTO `gen_table_column` VALUES (202, '17', 'UPDATED_BY', '更新人', 'varchar(32)', 'String', 'updatedBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2023-06-30 17:09:36', '', '2023-06-30 17:55:17');
INSERT INTO `gen_table_column` VALUES (203, '17', 'UPDATED_TIME', '更新时间', 'datetime', 'Date', 'updatedTime', '0', '0', NULL, '1', '1', '1', '1', 'BETWEEN', 'datetime', '', 13, 'admin', '2023-06-30 17:09:36', '', '2023-06-30 17:55:17');
INSERT INTO `gen_table_column` VALUES (293, '23', 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-03 15:52:34', '', '2023-07-03 16:50:24');
INSERT INTO `gen_table_column` VALUES (294, '23', 'type', '商品类型', 'int', 'Long', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 2, 'admin', '2023-07-03 15:52:34', '', '2023-07-03 16:50:24');
INSERT INTO `gen_table_column` VALUES (295, '23', 'name', '商品名称', 'varchar(64)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-07-03 15:52:34', '', '2023-07-03 16:50:24');
INSERT INTO `gen_table_column` VALUES (296, '23', 'image', '商品图片', 'varchar(1024)', 'String', 'image', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'imageUpload', '', 4, 'admin', '2023-07-03 15:52:34', '', '2023-07-03 16:50:24');
INSERT INTO `gen_table_column` VALUES (297, '23', 'number', '商品货号', 'varchar(64)', 'String', 'number', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-07-03 15:52:34', '', '2023-07-03 16:50:24');
INSERT INTO `gen_table_column` VALUES (298, '23', 'supplier', '供应商', 'varchar(128)', 'String', 'supplier', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-07-03 15:52:34', '', '2023-07-03 16:50:24');
INSERT INTO `gen_table_column` VALUES (299, '23', 'cost_price', '成本价', 'varchar(32)', 'String', 'costPrice', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-07-03 15:52:34', '', '2023-07-03 16:50:24');
INSERT INTO `gen_table_column` VALUES (300, '23', 'platform_price', '平台价', 'varchar(32)', 'String', 'platformPrice', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-07-03 15:52:34', '', '2023-07-03 16:50:24');
INSERT INTO `gen_table_column` VALUES (301, '23', 'postage', '邮费', 'varchar(50)', 'String', 'postage', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-07-03 15:52:34', '', '2023-07-03 16:50:24');
INSERT INTO `gen_table_column` VALUES (302, '23', 'sold_quantity', '已售数量', 'varchar(32)', 'String', 'soldQuantity', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-07-03 15:52:34', '', '2023-07-03 16:50:24');
INSERT INTO `gen_table_column` VALUES (303, '23', 'inventory', '库存', 'varchar(32)', 'String', 'inventory', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-07-03 15:52:34', '', '2023-07-03 16:50:24');
INSERT INTO `gen_table_column` VALUES (304, '23', 'listing_time', '上架时间', 'datetime', 'Date', 'listingTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 12, 'admin', '2023-07-03 15:52:34', '', '2023-07-03 16:50:24');
INSERT INTO `gen_table_column` VALUES (305, '23', 'off_shelf_time', '下架时间', 'datetime', 'Date', 'offShelfTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 13, 'admin', '2023-07-03 15:52:34', '', '2023-07-03 16:50:24');
INSERT INTO `gen_table_column` VALUES (306, '23', 'status', '商品状态', 'varchar(32)', 'String', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 14, 'admin', '2023-07-03 15:52:34', '', '2023-07-03 16:50:24');
INSERT INTO `gen_table_column` VALUES (307, '23', 'sort', '排序', 'int', 'Long', 'sort', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2023-07-03 15:52:34', '', '2023-07-03 16:50:24');
INSERT INTO `gen_table_column` VALUES (308, '23', 'p_s_id', '商品规格', 'json', 'String', 'pSId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', 'wtb_integral_status', 16, 'admin', '2023-07-03 15:52:34', '', '2023-07-03 16:50:24');
INSERT INTO `gen_table_column` VALUES (309, '23', 'describe', '商品描述', 'text', 'String', 'describe', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 17, 'admin', '2023-07-03 15:52:34', '', '2023-07-03 16:50:24');
INSERT INTO `gen_table_column` VALUES (310, '23', 'CREATED_TIME', '创建时间', 'datetime', 'Date', 'createdTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 18, 'admin', '2023-07-03 15:52:34', '', '2023-07-03 16:50:24');
INSERT INTO `gen_table_column` VALUES (311, '23', 'UPDATED_BY', '更新人', 'varchar(32)', 'String', 'updatedBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 19, 'admin', '2023-07-03 15:52:34', '', '2023-07-03 16:50:24');
INSERT INTO `gen_table_column` VALUES (312, '23', 'UPDATED_TIME', '更新时间', 'datetime', 'Date', 'updatedTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 20, 'admin', '2023-07-03 15:52:34', '', '2023-07-03 16:50:24');
INSERT INTO `gen_table_column` VALUES (313, '24', 'id', '主键id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-03 18:52:36', '', '2023-07-03 18:54:49');
INSERT INTO `gen_table_column` VALUES (314, '24', 'user_id', '用户id', 'int', 'Long', 'userId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-07-03 18:52:36', '', '2023-07-03 18:54:49');
INSERT INTO `gen_table_column` VALUES (315, '24', 'consignee', '收货人姓名', 'varchar(255)', 'String', 'consignee', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-07-03 18:52:36', '', '2023-07-03 18:54:49');
INSERT INTO `gen_table_column` VALUES (316, '24', 'phone', '收货人手机号', 'varchar(255)', 'String', 'phone', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2023-07-03 18:52:36', '', '2023-07-03 18:54:49');
INSERT INTO `gen_table_column` VALUES (317, '24', 'area', '收货人地区', 'varchar(255)', 'String', 'area', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 5, 'admin', '2023-07-03 18:52:36', '', '2023-07-03 18:54:49');
INSERT INTO `gen_table_column` VALUES (318, '24', 'address', '收货人详细地址', 'varchar(500)', 'String', 'address', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'textarea', '', 6, 'admin', '2023-07-03 18:52:36', '', '2023-07-03 18:54:49');
INSERT INTO `gen_table_column` VALUES (319, '24', 'zip_code', '邮编', 'varchar(255)', 'String', 'zipCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-07-03 18:52:36', '', '2023-07-03 18:54:49');
INSERT INTO `gen_table_column` VALUES (320, '24', 'is_default', '是否默认收货地址', 'int', 'Long', 'isDefault', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', 'sys_yes_no', 8, 'admin', '2023-07-03 18:52:36', '', '2023-07-03 18:54:49');
INSERT INTO `gen_table_column` VALUES (321, '25', 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-04 10:30:32', '', NULL);
INSERT INTO `gen_table_column` VALUES (322, '25', 'order_number', '订单编号', 'varchar(64)', 'String', 'orderNumber', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-07-04 10:30:32', '', NULL);
INSERT INTO `gen_table_column` VALUES (323, '25', 'user_id', '用户id', 'int', 'Long', 'userId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-07-04 10:30:32', '', NULL);
INSERT INTO `gen_table_column` VALUES (324, '25', 'user_phone', '用户手机号', 'varchar(32)', 'String', 'userPhone', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-07-04 10:30:32', '', NULL);
INSERT INTO `gen_table_column` VALUES (325, '25', 'type', '充值类型', 'int', 'Long', 'type', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 5, 'admin', '2023-07-04 10:30:32', '', NULL);
INSERT INTO `gen_table_column` VALUES (326, '25', 'account', '充值账号', 'varchar(32)', 'String', 'account', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-07-04 10:30:32', '', NULL);
INSERT INTO `gen_table_column` VALUES (327, '25', 'Recharge_grade', '充值档位', 'varchar(32)', 'String', 'rechargeGrade', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-07-04 10:30:32', '', NULL);
INSERT INTO `gen_table_column` VALUES (328, '25', 'payment_amount', '支付金额', 'varchar(255)', 'String', 'paymentAmount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-07-04 10:30:32', '', NULL);
INSERT INTO `gen_table_column` VALUES (329, '25', 'payment_method', '支付方式', 'varchar(32)', 'String', 'paymentMethod', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-07-04 10:30:32', '', NULL);
INSERT INTO `gen_table_column` VALUES (330, '25', 'payment_time', '支付时间', 'datetime', 'Date', 'paymentTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 10, 'admin', '2023-07-04 10:30:32', '', NULL);
INSERT INTO `gen_table_column` VALUES (331, '25', 'CREATED_BY', '创建人', 'varchar(32)', 'String', 'createdBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-07-04 10:30:32', '', NULL);
INSERT INTO `gen_table_column` VALUES (332, '25', 'CREATED_TIME', '创建时间', 'datetime', 'Date', 'createdTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 12, 'admin', '2023-07-04 10:30:32', '', NULL);
INSERT INTO `gen_table_column` VALUES (333, '25', 'UPDATED_BY', '更新人', 'varchar(32)', 'String', 'updatedBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2023-07-04 10:30:32', '', NULL);
INSERT INTO `gen_table_column` VALUES (334, '25', 'UPDATED_TIME', '更新时间', 'datetime', 'Date', 'updatedTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 14, 'admin', '2023-07-04 10:30:32', '', NULL);
INSERT INTO `gen_table_column` VALUES (335, '26', 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-04 14:38:23', '', '2023-07-04 15:06:04');
INSERT INTO `gen_table_column` VALUES (336, '26', 'user_id', '用户id', 'int', 'Long', 'userId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-07-04 14:38:23', '', '2023-07-04 15:06:04');
INSERT INTO `gen_table_column` VALUES (337, '26', 'user_phone', '用户手机号', 'varchar(32)', 'String', 'userPhone', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-07-04 14:38:23', '', '2023-07-04 15:06:04');
INSERT INTO `gen_table_column` VALUES (338, '26', 'type', '类型', 'int', 'Long', 'type', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', 'wtb_asset_type', 4, 'admin', '2023-07-04 14:38:23', '', '2023-07-04 15:06:04');
INSERT INTO `gen_table_column` VALUES (339, '26', 'add_or_subtract', '添加或者相减(0:减;1:加)', 'char(1)', 'String', 'addOrSubtract', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 5, 'admin', '2023-07-04 14:38:23', '', '2023-07-04 15:06:04');
INSERT INTO `gen_table_column` VALUES (340, '26', 'records', '总额变化记录', 'varchar(64)', 'String', 'records', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-07-04 14:38:23', '', '2023-07-04 15:06:04');
INSERT INTO `gen_table_column` VALUES (341, '26', 'balance', '余额', 'varchar(64)', 'String', 'balance', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-07-04 14:38:23', '', '2023-07-04 15:06:04');
INSERT INTO `gen_table_column` VALUES (342, '26', 'notes', '备注', 'varchar(3072)', 'String', 'notes', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 8, 'admin', '2023-07-04 14:38:23', '', '2023-07-04 15:06:04');
INSERT INTO `gen_table_column` VALUES (343, '26', 'CREATED_BY', '创建人', 'varchar(32)', 'String', 'createdBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-07-04 14:38:23', '', '2023-07-04 15:06:04');
INSERT INTO `gen_table_column` VALUES (344, '26', 'CREATED_TIME', '创建时间', 'datetime', 'Date', 'createdTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 10, 'admin', '2023-07-04 14:38:23', '', '2023-07-04 15:06:04');
INSERT INTO `gen_table_column` VALUES (345, '26', 'UPDATED_BY', '更新人', 'varchar(32)', 'String', 'updatedBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-07-04 14:38:23', '', '2023-07-04 15:06:04');
INSERT INTO `gen_table_column` VALUES (346, '26', 'UPDATED_TIME', '更新时间', 'datetime', 'Date', 'updatedTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 12, 'admin', '2023-07-04 14:38:23', '', '2023-07-04 15:06:04');
INSERT INTO `gen_table_column` VALUES (378, '30', 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-10 18:31:53', '', '2023-07-10 18:35:57');
INSERT INTO `gen_table_column` VALUES (379, '30', 'w_d_id', '仓库详情id', 'int', 'Long', 'wDId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-07-10 18:31:53', '', '2023-07-10 18:35:57');
INSERT INTO `gen_table_column` VALUES (380, '30', 'goods_id', '商品id', 'int', 'Long', 'goodsId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-07-10 18:31:53', '', '2023-07-10 18:35:57');
INSERT INTO `gen_table_column` VALUES (381, '30', 'goods_name', '商品名称', 'varchar(128)', 'String', 'goodsName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2023-07-10 18:31:53', '', '2023-07-10 18:35:57');
INSERT INTO `gen_table_column` VALUES (382, '30', 'user_phone', '用户手机号', 'varchar(32)', 'String', 'userPhone', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 5, 'admin', '2023-07-10 18:31:53', '', '2023-07-10 18:35:57');
INSERT INTO `gen_table_column` VALUES (383, '30', 'redeem_points', '兑换积分', 'varchar(128)', 'String', 'redeemPoints', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-07-10 18:31:53', '', '2023-07-10 18:35:57');
INSERT INTO `gen_table_column` VALUES (384, '30', 'transfer', '转赠账号', 'varchar(32)', 'String', 'transfer', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-07-10 18:31:53', '', '2023-07-10 18:35:57');
INSERT INTO `gen_table_column` VALUES (385, '30', 'logistics', '物流详情', 'varchar(1024)', 'String', 'logistics', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 8, 'admin', '2023-07-10 18:31:53', '', '2023-07-10 18:35:57');
INSERT INTO `gen_table_column` VALUES (386, '30', 'CREATED_TIME', '创建时间', 'datetime', 'Date', 'createdTime', '0', '0', NULL, '1', '1', '1', '1', 'BETWEEN', 'datetime', '', 9, 'admin', '2023-07-10 18:31:53', '', '2023-07-10 18:35:57');
INSERT INTO `gen_table_column` VALUES (387, '30', 'UPDATED_BY', '更新人', 'varchar(32)', 'String', 'updatedBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-07-10 18:31:53', '', '2023-07-10 18:35:57');
INSERT INTO `gen_table_column` VALUES (388, '30', 'UPDATED_TIME', '更新时间', 'datetime', 'Date', 'updatedTime', '0', '0', NULL, '1', '1', '1', '1', 'BETWEEN', 'datetime', '', 11, 'admin', '2023-07-10 18:31:53', '', '2023-07-10 18:35:57');
INSERT INTO `gen_table_column` VALUES (389, '31', 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-11 09:44:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (390, '31', 'order_number', '订单编号', 'varchar(64)', 'String', 'orderNumber', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-07-11 09:44:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (391, '31', 'user_id', '用户id', 'int', 'Long', 'userId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-07-11 09:44:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (392, '31', 'user_phone', '用户手机号', 'varchar(64)', 'String', 'userPhone', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-07-11 09:44:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (393, '31', 'nick_name', '用户昵称', 'varchar(32)', 'String', 'nickName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 5, 'admin', '2023-07-11 09:44:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (394, '31', 'goods_name', '商品名称', 'varchar(64)', 'String', 'goodsName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 6, 'admin', '2023-07-11 09:44:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (395, '31', 'goods_image', '商品图片', 'varchar(1024)', 'String', 'goodsImage', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'imageUpload', '', 7, 'admin', '2023-07-11 09:44:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (396, '31', 'goods_type', '商品类型', 'int', 'Long', 'goodsType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 8, 'admin', '2023-07-11 09:44:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (397, '31', 'price', '单价', 'varchar(64)', 'String', 'price', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-07-11 09:44:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (398, '31', 'quantity', '商品数量', 'int', 'Long', 'quantity', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-07-11 09:44:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (399, '31', 't_d_a_id', '收货地址id', 'int', 'Long', 'tDAId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-07-11 09:44:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (400, '31', 'costs', '运费', 'varchar(64)', 'String', 'costs', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2023-07-11 09:44:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (401, '31', 'integral', '消耗积分', 'varchar(64)', 'String', 'integral', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2023-07-11 09:44:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (402, '31', 'payment_amount', '支付金额', 'varchar(64)', 'String', 'paymentAmount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2023-07-11 09:44:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (403, '31', 'payment_time', '支付时间', 'datetime', 'Date', 'paymentTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 15, 'admin', '2023-07-11 09:44:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (404, '31', 'payment_method', '支付方式', 'int', 'Long', 'paymentMethod', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2023-07-11 09:44:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (405, '31', 'status', '订单状态', 'int', 'Long', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 17, 'admin', '2023-07-11 09:44:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (406, '31', 'CREATED_BY', '创建人', 'varchar(32)', 'String', 'createdBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 18, 'admin', '2023-07-11 09:44:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (407, '31', 'CREATED_TIME', '创建时间', 'datetime', 'Date', 'createdTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 19, 'admin', '2023-07-11 09:44:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (408, '31', 'UPDATED_BY', '更新人', 'varchar(32)', 'String', 'updatedBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 20, 'admin', '2023-07-11 09:44:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (409, '31', 'UPDATED_TIME', '更新时间', 'datetime', 'Date', 'updatedTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 21, 'admin', '2023-07-11 09:44:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (422, '33', 'id', 'id', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-11 15:59:41', '', NULL);
INSERT INTO `gen_table_column` VALUES (423, '33', 'user_id', '用户id', 'int', 'Long', 'userId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-07-11 15:59:41', '', NULL);
INSERT INTO `gen_table_column` VALUES (424, '33', 'wid', '仓库id', 'int', 'Long', 'wid', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-07-11 15:59:41', '', NULL);
INSERT INTO `gen_table_column` VALUES (425, '33', 'goods_id', '商品id', 'int', 'Long', 'goodsId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 4, 'admin', '2023-07-11 15:59:41', '', NULL);
INSERT INTO `gen_table_column` VALUES (426, '33', 'goods_image', '商品图片', 'varchar(1024)', 'String', 'goodsImage', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'imageUpload', '', 5, 'admin', '2023-07-11 15:59:41', '', NULL);
INSERT INTO `gen_table_column` VALUES (427, '33', 'goods_name', '商品名称', 'varchar(32)', 'String', 'goodsName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 6, 'admin', '2023-07-11 15:59:41', '', NULL);
INSERT INTO `gen_table_column` VALUES (428, '33', 'source', '商品来源', 'varchar(1024)', 'String', 'source', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 7, 'admin', '2023-07-11 15:59:41', '', NULL);
INSERT INTO `gen_table_column` VALUES (429, '33', 'quantity', '商品数量', 'int', 'Long', 'quantity', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-07-11 15:59:41', '', NULL);
INSERT INTO `gen_table_column` VALUES (430, '33', 'price', '商品价格', 'varchar(32)', 'String', 'price', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-07-11 15:59:41', '', NULL);
INSERT INTO `gen_table_column` VALUES (431, '33', 'CREATED_TIME', '获得时间', 'datetime', 'Date', 'createdTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 10, 'admin', '2023-07-11 15:59:41', '', NULL);
INSERT INTO `gen_table_column` VALUES (432, '33', 'state', '状态 0:未做任何操作,可兑换积分或转赠;1:已兑换积分;2:已转赠;3:已兑换实物.', 'int', 'Long', 'state', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-07-11 15:59:41', '', NULL);
INSERT INTO `gen_table_column` VALUES (433, '33', 'UPDATED_BY', '更新人', 'varchar(32)', 'String', 'updatedBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2023-07-11 15:59:41', '', NULL);
INSERT INTO `gen_table_column` VALUES (434, '33', 'UPDATED_TIME', '更新时间', 'datetime', 'Date', 'updatedTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 13, 'admin', '2023-07-11 15:59:41', '', NULL);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` int(0) NOT NULL COMMENT '商品类型',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `image` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品图片',
  `number` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品货号',
  `supplier` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '供应商',
  `cost_price` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '成本价',
  `platform_price` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '平台价',
  `postage` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮费',
  `sold_quantity` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '已售数量',
  `inventory` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '库存',
  `listing_time` datetime(0) NULL DEFAULT NULL COMMENT '上架时间',
  `off_shelf_time` datetime(0) NULL DEFAULT NULL COMMENT '下架时间',
  `status` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品状态',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `p_s_id` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品规格',
  `describe` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '商品描述',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, 1, '周黑鸭年货大礼包', '/profile/upload/2023/06/28/1_20230628102218A001.jpg', '123312', '周黑鸭', '60', '100', NULL, '1', '9', '2023-06-28 16:12:21', '2023-06-28 16:12:29', '1', 1, '1', '测试', NULL, 'admin', '2023-06-28 16:12:29');
INSERT INTO `goods` VALUES (2, 1, '测试商品', '/profile/upload/2023/07/03/1_20230703140331A011.jpg,/profile/upload/2023/07/03/login-logo_20230703140336A012.png,/profile/upload/2023/07/03/2_20230703140341A013.jpg', '123', '123', '12', '13', NULL, '1', '1', NULL, NULL, '1', 2, '1', '<p>1321313<img src=\"/dev-api/profile/upload/2023/07/03/2_20230703140413A014.jpg\"></p>', NULL, 'admin', '2023-07-03 16:33:44');
INSERT INTO `goods` VALUES (3, 1, '测试', '/profile/upload/2023/07/03/2_20230703160717A008.jpg', '123', '测试', '80', '100', NULL, '3', '100', NULL, NULL, '1', 3, '1,2', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for homepage_config
-- ----------------------------
DROP TABLE IF EXISTS `homepage_config`;
CREATE TABLE `homepage_config`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id id唯一标识',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序 数值越大排序越靠前',
  `classify` int(0) NOT NULL COMMENT '分类',
  `img_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片',
  `wide` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '宽',
  `high` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '高',
  `Link_Address` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '链接地址',
  `Creation_time` datetime(0) NULL DEFAULT NULL COMMENT '加入时间',
  `state` int(0) NULL DEFAULT NULL COMMENT '状态',
  `Display_Status` int(0) NULL DEFAULT NULL COMMENT '显示状态',
  `Display_Location` int(0) NULL DEFAULT NULL COMMENT '显示位置',
  `UPDATED_BY` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '首页配置 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of homepage_config
-- ----------------------------
INSERT INTO `homepage_config` VALUES (1, 1, 1, '/profile/upload/2023/06/27/1_20230627180029A002.jpg', '1', '1', '1111', '2023-06-26 00:00:00', 1, NULL, 1, 'admin', '2023-06-29 16:26:47');
INSERT INTO `homepage_config` VALUES (2, 2, 1, '/profile/upload/2023/06/26/2_20230626180134A008.jpg', '1', '1', '1', '2023-06-26 00:00:00', 1, NULL, 1, NULL, NULL);
INSERT INTO `homepage_config` VALUES (3, 1, 2, '/profile/upload/2023/06/29/login-logo_20230629155921A001.png', '15', '16', '11111111', '2023-06-29 15:59:29', 1, NULL, 2, NULL, NULL);
INSERT INTO `homepage_config` VALUES (4, 1, 3, '/profile/upload/2023/06/29/1_20230629155948A002.jpg', '11', '12', '1111111111111', '2023-06-29 00:00:00', 1, NULL, 3, 'admin', '2023-06-29 16:00:36');
INSERT INTO `homepage_config` VALUES (5, 2, 3, '/profile/upload/2023/06/29/2_20230629160047A003.jpg', '12', '12', '122222', '2023-06-29 16:01:00', 1, NULL, 3, NULL, NULL);
INSERT INTO `homepage_config` VALUES (6, 3, 3, '/profile/upload/2023/06/29/2_20230629160114A004.jpg', '12', '12', '12222222', '2023-06-29 16:01:19', 1, NULL, 3, NULL, NULL);
INSERT INTO `homepage_config` VALUES (7, 4, 3, '/profile/upload/2023/06/29/login-logo_20230629160127A005.png', NULL, NULL, '111111111111', '2023-06-29 16:01:37', 1, NULL, 3, NULL, NULL);
INSERT INTO `homepage_config` VALUES (8, 5, 3, '/profile/upload/2023/06/29/2_20230629160145A006.jpg', '11', '11', '111111111', '2023-06-29 16:01:57', 1, NULL, 3, NULL, NULL);
INSERT INTO `homepage_config` VALUES (9, 3, 1, '/profile/upload/2023/06/29/login-logo_20230629160229A007.png', '12', '12', '1111111111', '2023-06-29 16:02:34', 1, NULL, 1, NULL, NULL);
INSERT INTO `homepage_config` VALUES (10, 4, 1, '/profile/upload/2023/06/29/1_20230629160253A008.jpg', '12', '12', '111111111', '2023-06-29 16:02:58', 1, NULL, 1, NULL, NULL);
INSERT INTO `homepage_config` VALUES (11, 5, 1, '/profile/upload/2023/06/29/1_20230629160312A009.jpg', '11', '11', '1111111', '2023-06-29 16:03:19', 1, NULL, 1, NULL, NULL);
INSERT INTO `homepage_config` VALUES (12, 2, 2, '/profile/upload/2023/06/29/1_20230629162513A001.jpg', '12', '12', '122222', '2023-06-29 16:25:18', 1, NULL, 2, NULL, NULL);
INSERT INTO `homepage_config` VALUES (13, 3, 2, '/profile/upload/2023/06/29/1_20230629162546A002.jpg', '12', '12', '111111111', '2023-06-29 00:00:00', 1, NULL, 2, 'admin', '2023-06-29 16:25:50');
INSERT INTO `homepage_config` VALUES (14, 4, 2, '/profile/upload/2023/06/29/2_20230629162602A003.jpg', '12', '12', '12222222', '2023-06-29 16:26:07', 1, NULL, 2, NULL, NULL);

-- ----------------------------
-- Table structure for logistics
-- ----------------------------
DROP TABLE IF EXISTS `logistics`;
CREATE TABLE `logistics`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_number` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单编号',
  `goods_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名称',
  `goods_image` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品图片',
  `consignee_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人姓名',
  `consignee_phone` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人手机号',
  `tracking_number` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '快递单号',
  `express_company` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '快递公司',
  `consignee_Address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货地址',
  `CREATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `order_number`(`order_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '物流 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of logistics
-- ----------------------------
INSERT INTO `logistics` VALUES (1, '123132132', '测试', '/profile/upload/2023/07/06/1_20230706092027A001.jpg', '测试', '11111111111', '111111111', '顺丰', '湖北省武汉市江汉区万松街道125号', NULL, '2023-07-06 00:00:00', NULL, NULL);

-- ----------------------------
-- Table structure for money_ratio
-- ----------------------------
DROP TABLE IF EXISTS `money_ratio`;
CREATE TABLE `money_ratio`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` int(0) NOT NULL COMMENT '充值类型',
  `gear_position` varchar(3072) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '充值档位',
  `money_proportion` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '现金占比',
  `unreal_proportion` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '虚拟占比',
  `CREATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '充值档位及比例 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of money_ratio
-- ----------------------------
INSERT INTO `money_ratio` VALUES (1, 1, '10,30,50,100,150,200,300', '1', '10', NULL, NULL, NULL, NULL);
INSERT INTO `money_ratio` VALUES (2, 2, '10,20,30,50,100,150,200', '1', '10', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_number` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单编号',
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `user_phone` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `nick_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `goods_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名称',
  `goods_image` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品图片',
  `goods_type` int(0) NULL DEFAULT NULL COMMENT '商品类型',
  `price` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单价',
  `quantity` int(0) NULL DEFAULT NULL COMMENT '商品数量',
  `t_d_a_id` int(0) NULL DEFAULT NULL COMMENT '收货地址id',
  `costs` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '运费',
  `integral` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '消耗积分',
  `payment_amount` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '支付金额',
  `payment_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `payment_method` int(0) NULL DEFAULT NULL COMMENT '支付方式',
  `status` int(0) NULL DEFAULT NULL COMMENT '订单状态',
  `CREATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `orders_number`(`order_number`) USING BTREE COMMENT '唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品订单 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (2, '37005429872', 1, '111111', '若依', '测试', '/profile/upload/2023/07/04/1_20230704104813A001.jpg', 1, '100', 1, 1, NULL, '100', NULL, '2023-07-11 00:00:00', 1, 1, 'admin', '2023-07-11 12:08:28', NULL, NULL);
INSERT INTO `orders` VALUES (3, '23089935825', 1, '111111', '若依', '测试', '/profile/upload/2023/07/04/1_20230704104813A001.jpg', 1, '100', 1, 1, NULL, '100', NULL, '2023-07-11 00:00:00', 1, 1, 'admin', '2023-07-11 12:12:24', NULL, NULL);
INSERT INTO `orders` VALUES (4, '38960009036', 1, '111111', '若依', '测试', '/profile/upload/2023/07/04/1_20230704104813A001.jpg', 1, '100', 1, 1, NULL, '100', NULL, '2023-07-11 00:00:00', 1, 1, 'admin', '2023-07-11 12:18:15', NULL, NULL);

-- ----------------------------
-- Table structure for points_details
-- ----------------------------
DROP TABLE IF EXISTS `points_details`;
CREATE TABLE `points_details`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `w_d_id` int(0) NOT NULL COMMENT '仓库详情id',
  `goods_id` int(0) NULL DEFAULT NULL COMMENT '商品id',
  `goods_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名称',
  `user_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `redeem_points` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '兑换积分',
  `transfer` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '转赠账号',
  `logistics` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '物流详情',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '仓库商品积分详情 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for product_specifications
-- ----------------------------
DROP TABLE IF EXISTS `product_specifications`;
CREATE TABLE `product_specifications`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '规格名称',
  `value` varchar(3072) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '规格值',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品规格 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_specifications
-- ----------------------------
INSERT INTO `product_specifications` VALUES (1, '码数', '大码,小码,中码', NULL, NULL, NULL);
INSERT INTO `product_specifications` VALUES (2, '颜色', '蓝色,绿色,红色', NULL, NULL, NULL);
INSERT INTO `product_specifications` VALUES (3, '测试规格', '1,2,3,4', NULL, NULL, NULL);
INSERT INTO `product_specifications` VALUES (4, '测试', '1,2', NULL, NULL, NULL);
INSERT INTO `product_specifications` VALUES (5, '测试3', '1,2,3', NULL, NULL, NULL);
INSERT INTO `product_specifications` VALUES (6, '测试4', '1,3,4', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for recharge_bill
-- ----------------------------
DROP TABLE IF EXISTS `recharge_bill`;
CREATE TABLE `recharge_bill`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_number` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单编号',
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `user_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `type` int(0) NULL DEFAULT NULL COMMENT '充值类型',
  `account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '充值账号',
  `Recharge_grade` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '充值档位',
  `payment_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '支付金额',
  `payment_method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '支付方式',
  `payment_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `CREATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '充值流水 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recharge_bill
-- ----------------------------
INSERT INTO `recharge_bill` VALUES (1, '123', 1, '111111', 1, '1', '100', '10', '微信', '2023-06-30 16:39:48', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for take_delivery_address
-- ----------------------------
DROP TABLE IF EXISTS `take_delivery_address`;
CREATE TABLE `take_delivery_address`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `consignee` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货人姓名',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货人手机号',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货人地区',
  `address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货人详细地址',
  `zip_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮编',
  `is_default` int(0) NULL DEFAULT NULL COMMENT '是否默认收货地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of take_delivery_address
-- ----------------------------
INSERT INTO `take_delivery_address` VALUES (1, 1, '张三', '111111111', '湖北省,武汉市,江汉区', '湖北省武汉市**区**小区36栋3单元', '420000', 1);

-- ----------------------------
-- Table structure for user_assets
-- ----------------------------
DROP TABLE IF EXISTS `user_assets`;
CREATE TABLE `user_assets`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `user_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户手机号',
  `nick_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `type` int(0) NULL DEFAULT NULL COMMENT '资产类型',
  `total` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '当前总额',
  `CREATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户资产 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_assets
-- ----------------------------
INSERT INTO `user_assets` VALUES (1, 1, '1111111', '若依', 1, '900', NULL, NULL, 'admin', '2023-07-11 19:02:15');
INSERT INTO `user_assets` VALUES (2, 1, '1111111', '若依', 2, '1000', NULL, NULL, NULL, '2023-06-30 10:03:11');
INSERT INTO `user_assets` VALUES (3, 1, '1111111', '若依', 3, '1000', NULL, NULL, NULL, '2023-06-30 10:03:11');
INSERT INTO `user_assets` VALUES (4, 100, '1111111', '游戏用户1', 3, '1000', NULL, NULL, NULL, '2023-06-30 10:03:11');
INSERT INTO `user_assets` VALUES (5, 100, '1111111', '游戏用户1', 2, '1000', NULL, NULL, NULL, '2023-06-30 10:03:11');
INSERT INTO `user_assets` VALUES (6, 100, '1111111', '游戏用户1', 1, '1000', NULL, NULL, NULL, '2023-06-30 10:03:11');

-- ----------------------------
-- Table structure for user_friends
-- ----------------------------
DROP TABLE IF EXISTS `user_friends`;
CREATE TABLE `user_friends`  (
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `friends_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '好友id',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户好友 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `nick_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `user_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `quantity` int(0) NULL DEFAULT NULL COMMENT '商品数量',
  `total_price` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品总价',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '仓库 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO `warehouse` VALUES (1, 100, '游戏用户1', '11111111111', 1, '10000', NULL, '2023-06-28 00:00:00');

-- ----------------------------
-- Table structure for warehouse_details
-- ----------------------------
DROP TABLE IF EXISTS `warehouse_details`;
CREATE TABLE `warehouse_details`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `wid` int(0) NOT NULL COMMENT '仓库id',
  `goods_id` int(0) NOT NULL COMMENT '商品id',
  `goods_image` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品图片',
  `goods_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `source` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品来源',
  `quantity` int(0) NULL DEFAULT NULL COMMENT '商品数量',
  `price` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品价格',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '获得时间',
  `state` int(0) NULL DEFAULT NULL COMMENT '状态 0:未做任何操作,可兑换积分或转赠;1:已兑换积分;2:已转赠;3:已兑换实物.',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`, `goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '仓库详情 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of warehouse_details
-- ----------------------------
INSERT INTO `warehouse_details` VALUES (1, 1, 1, 1, '', '测试', 'XXX游戏获得', 1, '100', NULL, 1, NULL, NULL);
INSERT INTO `warehouse_details` VALUES (2, 1, 1, 2, '', '测试2', '....获得', 1, '100', NULL, 0, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
