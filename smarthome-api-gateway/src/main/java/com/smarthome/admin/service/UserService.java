package com.smarthome.admin.service;

import com.smarthome.admin.entity.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liushihua
 * @since 2017-10-24
 */
public interface UserService extends IService<User> {
	
	public User findUserByUserName(String username);
	
}
