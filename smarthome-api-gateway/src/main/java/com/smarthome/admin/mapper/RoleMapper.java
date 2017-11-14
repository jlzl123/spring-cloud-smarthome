package com.smarthome.admin.mapper;

import java.util.Set;

import com.smarthome.admin.entity.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liushihua
 * @since 2017-10-24
 */
public interface RoleMapper extends BaseMapper<Role> {

	public Set<Role> selectRoleSetByUserId(Integer userId);
	
}