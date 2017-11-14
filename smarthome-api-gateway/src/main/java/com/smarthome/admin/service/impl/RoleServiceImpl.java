package com.smarthome.admin.service.impl;

import java.util.Set;

import com.smarthome.admin.entity.Role;
import com.smarthome.admin.mapper.RoleMapper;
import com.smarthome.admin.service.RoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	@Override
	public Set<Role> findRoleSetByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return baseMapper.selectRoleSetByUserId(userId);
	}
	
}
