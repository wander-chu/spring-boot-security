USE `test`;

DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `users` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `u_username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `u_password` varchar(255) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';

CREATE TABLE `roles` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `r_rolename` varchar(255) DEFAULT NULL COMMENT '角色名',
  `r_roleflag` varchar(255) DEFAULT NULL COMMENT '角色标识',
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息';

CREATE TABLE `user_roles` (
  `ur_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ur_user_id` int(11) NOT NULL COMMENT '用户Id',
  `ur_role_id` int(11) NOT NULL COMMENT '角色Id',
  PRIMARY KEY (`ur_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联信息';

-- 用户信息 密码均为“123456”，数据库中存储的密码内容时md5加密的结果
INSERT INTO users(`u_id`, `u_username`, `u_password`) VALUES(1, 'admin', 'd6f119f1ead799bb8f8323101eff1558');
INSERT INTO users(`u_id`, `u_username`, `u_password`) VALUES(2, 'user', 'd6f119f1ead799bb8f8323101eff1558');

-- 角色信息
INSERT INTO roles(`r_id`, `r_rolename`, `r_roleflag`) VALUES(1, '超级管理员', 'ROLE_ADMIN');
INSERT INTO roles(`r_id`, `r_rolename`, `r_roleflag`) VALUES(2, '普通用户', 'ROLE_USER');

-- 用户角色关联信息
INSERT INTO user_roles(`ur_id`, `ur_user_id`, `ur_role_id`) VALUES(1, 1, 1);
INSERT INTO user_roles(`ur_id`, `ur_user_id`, `ur_role_id`) VALUES(2, 1, 2);
INSERT INTO user_roles(`ur_id`, `ur_user_id`, `ur_role_id`) VALUES(3, 2, 2);