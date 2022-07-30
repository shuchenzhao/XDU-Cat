/*
 Navicat Premium Data Transfer

 Source Server         : ZCWzy's MySQL
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : xducat

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 07/06/2022 22:33:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cat
-- ----------------------------
DROP TABLE IF EXISTS `cat`;
CREATE TABLE `cat`  (
  `id` int(0) NOT NULL,
  `location` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `color` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cat
-- ----------------------------
INSERT INTO `cat` VALUES (1, '图书馆', '黑', '1.jpg');
INSERT INTO `cat` VALUES (2, '图书馆', '玳瑁', '2.jpg');
INSERT INTO `cat` VALUES (3, '未知', '白橘', '3.jpg');
INSERT INTO `cat` VALUES (4, '未知', '狸花', '4.jpg');
INSERT INTO `cat` VALUES (5, '图书馆', '狸花加白', '5.jpg');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `commentid` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `id` int(0) NOT NULL,
  `comment` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`commentid`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  CONSTRAINT `id` FOREIGN KEY (`id`) REFERENCES `cat` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 1, '它经常和那个玳瑁同时出现，但是我一段时间没见过它了', 'ZCWzy', '2022-03-10');
INSERT INTO `comment` VALUES (2, 1, '它很怕人!', '匿名同学', '2022-04-01');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` char(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` char(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`username`, `password`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('super', '123');
INSERT INTO `user` VALUES ('test', '111111');

SET FOREIGN_KEY_CHECKS = 1;
