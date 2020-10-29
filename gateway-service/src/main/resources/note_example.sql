/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : note_example

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 29/10/2020 16:21:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for open_playlist
-- ----------------------------
DROP TABLE IF EXISTS `open_playlist`;
CREATE TABLE `open_playlist`  (
  `uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `duration` float NULL DEFAULT NULL,
  `context` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of open_playlist
-- ----------------------------

-- ----------------------------
-- Table structure for open_segment
-- ----------------------------
DROP TABLE IF EXISTS `open_segment`;
CREATE TABLE `open_segment`  (
  `uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bytes` longblob NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of open_segment
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `menu_pid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父id',
  `menu_pids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所有父id',
  `is_leaf` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否是叶子节点0:不是叶子节点，1:是叶子节点',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单代码',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标识',
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '层级',
  `sort` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排序',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型 0:目录,1:菜单,2:按钮',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_user_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `update_user_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('0', '-1', NULL, '0', NULL, '...控制台', '#', NULL, '0', '0', NULL, '根节点', '0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES ('1', '0', NULL, '1', 'system', '系统信息', '#', 'el-icon-s-tools', '1', '1', NULL, '系统信息', '0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES ('2', '1', NULL, '1', 'sys-user', '用户管理', '/system/sys-user', 'el-icon-user-solid', '2', '2', NULL, '用户管理', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES ('3', '1', NULL, '1', 'sys-param', '参数管理', '/system/sys-param', 'el-icon-s-grid', '2', '3', NULL, '参数管理', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES ('336716b3677f4234842d99dcd52d62e9', '1', NULL, '1', 'sys-role', '角色管理', '/system/sys-role', 'el-icon-s-check', '2', '5', NULL, '角色管理', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES ('4', '1', NULL, '1', 'sys-menu', '菜单管理', '/system/sys-menu', 'el-icon-menu', '2', '4', NULL, '菜单管理', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES ('b84646d463d7481b9e5e8ddc1e93852f', '1', NULL, '1', 'sys-config', '系统配置', '/system/sys-config', 'el-icon-set-up', '2', '6', NULL, '系统配置', '1', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org`  (
  `uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `org_pid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上级组织的id',
  `org_pids` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所有的父节点id',
  `is_leaf` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0:不是叶子节点，1:是叶子节点',
  `org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织名',
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织层级',
  `sort` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_org
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色代码',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `sort` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色顺序',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_user_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `update_user_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '管理员', '管理员', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES ('2', 'biz1', '业务员1', '业务员1', '2', '2020-10-29 14:14:11', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色主键',
  `menu_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单主键',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('5', '1', '336716b3677f4234842d99dcd52d62e9');
INSERT INTO `sys_role_menu` VALUES ('6', '1', 'b84646d463d7481b9e5e8ddc1e93852f');
INSERT INTO `sys_role_menu` VALUES ('7', '1', '0');
INSERT INTO `sys_role_menu` VALUES ('cd80a7bc288d4169adc19cedaa85cc9c', '2', '2');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `org_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别 1:男，0:女',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `enabled` tinyint(0) NULL DEFAULT NULL COMMENT '账号是否可用 1:可用，0:不可用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人主键',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人主键',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', NULL, NULL, 'admin', '$2a$10$uH2ZHwPMdhTM3KEpRjN2auiFFvMdnRMpA.ra2BNaL75R3h/tNp5Da', '13050571762', NULL, '管理员', '1', NULL, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('2', NULL, NULL, 'ls', '$2a$10$zcDtFrF2ZEd3SfnpfHvXQOVINY8T1VP7ovjWKfZcxdjN1nY6tioN.', '13000000000', NULL, '李四', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户主键',
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '1', '2');
INSERT INTO `sys_user_role` VALUES ('3', '2', '2');

SET FOREIGN_KEY_CHECKS = 1;
