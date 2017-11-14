package com.smarthome.admin.service.impl;

import java.util.Set;

import com.smarthome.admin.entity.Authority;
import com.smarthome.admin.mapper.AuthorityMapper;
import com.smarthome.admin.service.AuthorityService;
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
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements AuthorityService {

	@Override
	public Set<String> findPermissionSet(Integer roleId) {
		// TODO Auto-generated method stub
		return baseMapper.selectPermissionSetByRoleId(roleId);
	}
}
