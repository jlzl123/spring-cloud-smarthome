package com.smarthome.admin.service.impl;

import java.util.Set;
import java.util.Set;

import com.smarthome.admin.entity.Role;
import com.smarthome.admin.entity.User;
import com.smarthome.admin.mapper.UserMapper;
import com.smarthome.admin.service.AuthorityService;
import com.smarthome.admin.service.RoleService;
import com.smarthome.admin.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liushihua
 * @since 2017-10-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private AuthorityService authorityService;
	
	public User findUserByUserName(String username){
		User user = baseMapper.selectUserByName(username);
		Set<Role> roles=roleService.findRoleSetByUserId(user.getUserId());
		for(Role role : roles){
			Set<String> permissions = authorityService.findPermissionSet(role.getRoleId());
			role.setPermissionSet(permissions);
		}
		user.setRoleSet(roles);
		return user;
	}
	
}
