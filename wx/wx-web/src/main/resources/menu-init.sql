/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : wx

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-03-11 18:33:06
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '系统管理', null, '0', '0', '1', '2017-03-10 22:19:32', '2017-03-10 22:19:36', '1', '1');
INSERT INTO `menu` VALUES ('2', '菜单管理', '/sys/menu.html', '1', '1', '1', '2017-03-10 22:20:24', '2017-03-10 22:20:27', '1', '2');
INSERT INTO `menu` VALUES ('3', '素材管理', '', '0', '0', '0', '2017-03-11 16:49:26', '2017-03-11 16:49:26', '1', '2');
INSERT INTO `menu` VALUES ('4', '素材列表', '/media/media.html', '1', '3', '0', '2017-03-11 16:50:25', '2017-03-11 16:56:36', '1', '1');
INSERT INTO `menu` VALUES ('5', '图片管理', '/media/imgList.html', '1', '3', '0', '2017-03-11 16:50:51', '2017-03-11 16:56:41', '1', '2');
INSERT INTO `menu` VALUES ('6', '图文素材管理', '/media/articleList.html', '1', '3', '0', '2017-03-11 16:51:45', '2017-03-11 16:57:29', '1', '2');
INSERT INTO `menu` VALUES ('7', '消息管理', '', '0', '0', '0', '2017-03-11 16:52:26', '2017-03-11 16:52:53', '1', '3');
INSERT INTO `menu` VALUES ('8', '群发列表', '/message/index.html', '1', '7', '0', '2017-03-11 16:53:06', '2017-03-11 18:29:39', '1', '1');
INSERT INTO `menu` VALUES ('9', 'OpenId群发', '/message/sendByOpenId.html', '1', '7', '0', '2017-03-11 16:53:33', '2017-03-11 18:24:42', '0', '1');
INSERT INTO `menu` VALUES ('10', '标签群发', '/message/sendByTagId.html', '1', '7', '0', '2017-03-11 16:53:57', '2017-03-11 18:24:46', '0', '2');
INSERT INTO `menu` VALUES ('11', '消息记录', '/message/messageRecordList.html', '1', '7', '0', '2017-03-11 16:54:20', '2017-03-11 16:56:59', '1', '4');
INSERT INTO `menu` VALUES ('12', '账号管理', 'account/index.html', '1', '7', '0', '2017-03-11 16:54:53', '2017-03-11 16:54:56', '0', '4');
INSERT INTO `menu` VALUES ('13', '账号管理', '', '0', '0', '0', '2017-03-11 16:55:08', '2017-03-11 16:55:08', '1', '4');
INSERT INTO `menu` VALUES ('14', '微信号管理', '/account/index.html', '1', '13', '0', '2017-03-11 16:55:26', '2017-03-11 16:57:04', '1', '1');
INSERT INTO `menu` VALUES ('15', '标签管理', '/wxtag/index.html', '1', '13', '0', '2017-03-11 16:56:02', '2017-03-11 16:57:07', '1', '2');
