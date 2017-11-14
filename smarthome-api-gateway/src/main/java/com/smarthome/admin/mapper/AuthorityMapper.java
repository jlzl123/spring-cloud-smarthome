package com.smarthome.admin.mapper;

import java.util.Set;

import com.smarthome.admin.entity.Authority;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liushihua
 * @since 2017-10-24
 */
public interface AuthorityMapper extends BaseMapper<Authority> {

	public Set<String> selectPermissionSetByRoleId(Integer roleId);
	
}