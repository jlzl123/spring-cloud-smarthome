package com.smarthome.admin.service;

import java.util.Set;

import com.smarthome.admin.entity.Authority;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liushihua
 * @since 2017-10-24
 */
public interface AuthorityService extends IService<Authority> {

	public Set<String> findPermissionSet(Integer roleId);
	
}
