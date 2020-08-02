/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : example_data

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2020-08-02 18:18:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for open_playlist
-- ----------------------------
DROP TABLE IF EXISTS `open_playlist`;
CREATE TABLE `open_playlist` (
  `uuid` varchar(50) NOT NULL,
  `duration` float DEFAULT NULL,
  `context` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for open_segment
-- ----------------------------
DROP TABLE IF EXISTS `open_segment`;
CREATE TABLE `open_segment` (
  `uuid` varchar(50) NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `bytes` longblob,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `uuid` varchar(50) NOT NULL,
  `menu_pid` varchar(50) DEFAULT NULL,
  `menu_pids` varchar(50) DEFAULT NULL,
  `is_leaf` varchar(4) DEFAULT NULL,
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(255) DEFAULT NULL COMMENT '路径',
  `icon` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `sort` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `uuid` varchar(50) NOT NULL,
  `org_pid` varchar(50) NOT NULL COMMENT '上级组织的id',
  `org_pids` varchar(50) DEFAULT NULL COMMENT '所有的父节点id',
  `is_leaf` varchar(4) DEFAULT NULL COMMENT '0:不是叶子节点，1:是叶子节点',
  `org_name` varchar(255) DEFAULT NULL COMMENT '组织名',
  `level` varchar(255) DEFAULT NULL COMMENT '组织层级',
  `sort` varchar(255) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `uuid` varchar(50) NOT NULL,
  `role_code` varchar(255) DEFAULT NULL COMMENT '角色代码',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `sort` int(11) DEFAULT NULL COMMENT '角色顺序',
  `create_time` datetime DEFAULT NULL,
  `create_user_uuid` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_uuid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `uuid` varchar(50) NOT NULL,
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色主键',
  `menu_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单主键',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `org_id` varchar(50) DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人主键',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人主键',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `uuid` varchar(50) NOT NULL,
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户主键',
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
