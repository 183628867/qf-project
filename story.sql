/*
 Navicat Premium Data Transfer

 Source Server         : 175.24.35.40
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : 175.24.35.40:3366
 Source Schema         : story

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 27/07/2021 09:47:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `browser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  `system_mc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统',
  `insert_dt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 77 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES (87, '1997', '220.184.141.45', 'UNKNOWN', 'UNKNOWN', '2021-07-26 09:41:11');
INSERT INTO `log` VALUES (89, '1997', '220.184.141.45', 'UNKNOWN', 'UNKNOWN', '2021-07-26 10:00:56');
INSERT INTO `log` VALUES (90, '1997', '220.184.141.45', 'UNKNOWN', 'UNKNOWN', '2021-07-26 10:01:15');
INSERT INTO `log` VALUES (91, '1997', '220.184.141.45', 'CHROME9', 'WINDOWS_10', '2021-07-26 10:02:00');
INSERT INTO `log` VALUES (92, '1997', '220.184.141.45', 'UNKNOWN', 'UNKNOWN', '2021-07-26 10:03:38');
INSERT INTO `log` VALUES (93, '1997', '220.184.141.45', 'CHROME9', 'WINDOWS_10', '2021-07-26 10:03:46');
INSERT INTO `log` VALUES (94, '1997', '220.184.141.45', 'CHROME9', 'WINDOWS_10', '2021-07-26 10:03:51');
INSERT INTO `log` VALUES (95, '1997', '220.184.141.45', 'CHROME9', 'WINDOWS_10', '2021-07-26 10:10:36');
INSERT INTO `log` VALUES (96, '1997', '112.10.160.190', 'CHROME_MOBILE', 'ANDROID7', '2021-07-26 14:10:23');
INSERT INTO `log` VALUES (97, '1997', '220.184.141.45', 'CHROME9', 'WINDOWS_10', '2021-07-27 01:17:37');

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `disable` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '0 未禁用 1禁用',
  `insert_dt` datetime(0) NULL DEFAULT NULL COMMENT '插入时间',
  `update_dt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `delete_dt` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `delete_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '0 未删除 1 删除',
  `version` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台登录账号表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES (2, '1997', 'A27A102A904F1421FC046FD3CEDDF759', 'cheng', '0', '1', 'story/web/6f98b63e9ffc490c8b23ffc2bb102961.png', '0', '2020-07-03 09:26:56', '2021-07-23 06:10:39', NULL, '0', '25');
INSERT INTO `manager` VALUES (10, '123', 'A27A102A904F1421FC046FD3CEDDF759', '管理员', '1', '24', NULL, '0', '2021-07-23 04:24:24', '2021-07-26 02:09:02', NULL, '0', '14');

-- ----------------------------
-- Table structure for manager_role_associ
-- ----------------------------
DROP TABLE IF EXISTS `manager_role_associ`;
CREATE TABLE `manager_role_associ`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `manager_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `role_id` int(0) NULL DEFAULT NULL COMMENT '角色id',
  `delete_flag` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager_role_associ
-- ----------------------------
INSERT INTO `manager_role_associ` VALUES (1, 1, 1, '0');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `parent_id` int(0) NULL DEFAULT 0 COMMENT '父级id',
  `menu_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `order` int(0) NULL DEFAULT NULL COMMENT '排序',
  `insert_dt` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `updat_dt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `delete_dt` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `delete_flag` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '标志',
  `version` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 0, '/', '系统管理', 'el-icon-s-tools', 2, '2020-06-12 18:35:19', NULL, NULL, '0', '0');
INSERT INTO `menu` VALUES (2, 1, 'rolesList', '角色管理', 'el-icon-s-custom', 2, '2020-05-09 21:46:31', NULL, NULL, '0', '0');
INSERT INTO `menu` VALUES (3, 1, 'managerList', '账户管理', 'el-icon-user-solid', 1, '2020-05-15 13:37:14', NULL, NULL, '0', '0');
INSERT INTO `menu` VALUES (4, 1, 'menuList', '菜单管理', 'el-icon-menu', 3, '2020-08-15 13:23:31', NULL, NULL, '0', '0');
INSERT INTO `menu` VALUES (43, 0, 'home', '首页', 'el-icon-s-home', 0, '2021-05-21 02:43:29', NULL, NULL, '0', '0');
INSERT INTO `menu` VALUES (44, 0, '/', '日志', 'el-icon-notebook-1', 3, '2021-07-09 04:13:14', NULL, NULL, '0', '0');
INSERT INTO `menu` VALUES (47, 44, 'loginLogList', '登录日志', 'el-icon-tickets', 1, '2021-07-26 11:24:57', NULL, NULL, '0', '0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `insert_dt` datetime(0) NULL DEFAULT NULL COMMENT '插入时间',
  `update_dt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `delete_dt` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `delete_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志 0 未删除 1删除',
  `version` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', '2020-04-14 19:15:31', NULL, NULL, '0', '0');
INSERT INTO `role` VALUES (19, '普通用户', '2021-04-10 09:03:06', NULL, NULL, '0', '0');
INSERT INTO `role` VALUES (24, '管理员', '2021-05-02 09:56:31', NULL, NULL, '0', '0');

-- ----------------------------
-- Table structure for role_menu_associ
-- ----------------------------
DROP TABLE IF EXISTS `role_menu_associ`;
CREATE TABLE `role_menu_associ`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role_id` int(0) NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` int(0) NULL DEFAULT NULL COMMENT '资源路径id',
  `delete_flag` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 336 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '角色资源关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu_associ
-- ----------------------------
INSERT INTO `role_menu_associ` VALUES (291, 19, 30, '0');
INSERT INTO `role_menu_associ` VALUES (292, 19, 32, '0');
INSERT INTO `role_menu_associ` VALUES (293, 24, 2, '0');
INSERT INTO `role_menu_associ` VALUES (294, 24, 42, '0');
INSERT INTO `role_menu_associ` VALUES (295, 24, 30, '0');
INSERT INTO `role_menu_associ` VALUES (296, 24, 32, '0');
INSERT INTO `role_menu_associ` VALUES (297, 24, 1, '0');
INSERT INTO `role_menu_associ` VALUES (336, 1, 43, '0');
INSERT INTO `role_menu_associ` VALUES (337, 1, 1, '0');
INSERT INTO `role_menu_associ` VALUES (338, 1, 3, '0');
INSERT INTO `role_menu_associ` VALUES (339, 1, 2, '0');
INSERT INTO `role_menu_associ` VALUES (340, 1, 4, '0');
INSERT INTO `role_menu_associ` VALUES (341, 1, 44, '0');
INSERT INTO `role_menu_associ` VALUES (342, 1, 47, '0');

SET FOREIGN_KEY_CHECKS = 1;
