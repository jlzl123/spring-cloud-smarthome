package com.smarthome.admin.mapper;

import com.smarthome.admin.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liushihua
 * @since 2017-10-24
 */
public interface UserMapper extends BaseMapper<User> {

	public User selectUserByName(String username);
}