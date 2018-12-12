-- 初始化用户
INSERT INTO `user` (`id`, `is_admin`, `password`, `realname`, `salt`, `state`, `username`) VALUES ('1', 1, '202cb962ac59075b964b07152d234b70', '管理员', '8d78869f470951332959580424d4bf4f', 0, 'admin');
-- 初始化角色
INSERT INTO `role` (`role_id`,`available`,`role_name`) VALUES (1,0,'admin');
INSERT INTO `role` (`role_id`,`available`,`role_name`) VALUES (2,0,'vip');
INSERT INTO `role` (`role_id`,`available`,`role_name`) VALUES (3,1,'test');

-- 初始化权限
INSERT INTO `permission` (`id`,`available`,`name`,`parent_id`,`parent_ids`,`permission`,`resource_type`,`url`) VALUES (1,0,'用户管理',0,'0/','userInfo:view','menu','userInfo/userList');
INSERT INTO `permission` (`id`,`available`,`name`,`parent_id`,`parent_ids`,`permission`,`resource_type`,`url`) VALUES (2,0,'用户添加',1,'0/1','userInfo:add','button','userInfo/userAdd');
INSERT INTO `permission` (`id`,`available`,`name`,`parent_id`,`parent_ids`,`permission`,`resource_type`,`url`) VALUES (3,0,'用户删除',1,'0/1','userInfo:del','button','userInfo/userDel');

-- 初始角色-权限化中间表
INSERT INTO `role_permission` (`permission_id`,`role_id`) VALUES (1,1);
INSERT INTO `role_permission` (`permission_id`,`role_id`) VALUES (2,1);
INSERT INTO `role_permission` (`permission_id`,`role_id`) VALUES (3,2);

-- 初始角色-用户化中间表
INSERT INTO `user_role` (`role_id`,`uid`) VALUES (1,1);