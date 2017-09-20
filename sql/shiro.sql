/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50529
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50529
File Encoding         : 65001

Date: 2017-09-20 15:36:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for roles_resources
-- ----------------------------
DROP TABLE IF EXISTS `roles_resources`;
CREATE TABLE `roles_resources` (
  `re_id` int(11) NOT NULL AUTO_INCREMENT,
  `re_name` varchar(100) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `parent_id` int(10) unsigned zerofill NOT NULL,
  `permission` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`re_id`),
  UNIQUE KEY `idx_roles_permissions` (`re_name`,`permission`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles_resources
-- ----------------------------
INSERT INTO `roles_resources` VALUES ('1', '用户管理', 'menu', 'user.jsp', '0000000000', 'user:*');
INSERT INTO `roles_resources` VALUES ('2', '角色管理', 'menu', 'roles.jsp', '0000000000', 'role:*');
INSERT INTO `roles_resources` VALUES ('3', '菜单管理', 'menu', 'menu.jsp', '0000000000', 'menu:*');
INSERT INTO `roles_resources` VALUES ('4', '数据查看', 'menu', 'data.jsp', '0000000000', 'data:*');
INSERT INTO `roles_resources` VALUES ('5', '用户查询', 'button', null, '0000000001', 'user:select');
INSERT INTO `roles_resources` VALUES ('6', '角色查询', 'button', null, '0000000002', 'role:update');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `u_id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `password_salt` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `idx_users_username` (`username`),
  KEY `FK_users_user_roles` (`role_id`),
  CONSTRAINT `FK_users_user_roles` FOREIGN KEY (`role_id`) REFERENCES `user_roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'admin', '038bdaf98f2037b31f1e75b5b4c9b26e', '1', '');
INSERT INTO `users` VALUES ('2', 'user', '098d2c478e9c11555ce2823231e02ec1', '2', 'dXNlcg==');

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `resource_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `idx_user_roles` (`description`,`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES ('1', 'admin', '超级管理员', '1,2,3,4');
INSERT INTO `user_roles` VALUES ('2', 'user', '普通用户', '5,6');

-- ----------------------------
-- Procedure structure for proc_adder
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_adder`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_adder`(IN a int, IN b int, OUT sum int)
BEGIN
   
    DECLARE c int;
    if a is null then set a = 0; 
    end if;
  
    if b is null then set b = 0;
    end if;

    set sum  = a + b;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for proc_adder1
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_adder1`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_adder1`(IN a int, IN b int, OUT sum int)
BEGIN
   
    DECLARE c int;
    if a is null then set a = 0; 
    end if;
  
    if b is null then set b = 0;
    end if;

    set sum  = a + b;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for proc_adder11
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_adder11`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_adder11`(IN a int, IN b int, OUT sum int)
BEGIN
   
    DECLARE c int;
    if a is null then set a = 0; 
    end if;
  
    if b is null then set b = 0;
    end if;

    set sum  = a + b;
END
;;
DELIMITER ;
