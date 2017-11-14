package com.smarthome;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.smarthome.admin.entity.Role;
import com.smarthome.admin.entity.User;
import com.smarthome.admin.service.AuthorityService;
import com.smarthome.admin.service.RoleService;
import com.smarthome.admin.service.UserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestMybatisPlus {

	@Autowired
	private UserService userService;
	@Autowired 
	private RoleService roleService;
	@Autowired
	private AuthorityService authorityService;
	
//	@Test
	public void test(){
		List<User> users=userService.selectList(new EntityWrapper<User>());
		for(User user:users){
			System.out.println(user.toString());
		}
		
		User user=userService.findUserByUserName("绠＄悊鍛�");
		System.out.println(user.toString());
		
		System.out.println("++++++++++++++++++++++++++++++");
		
		User u = userService.findUserByUserName("zzq");
		System.out.println(u.toString());
		for(Role role : u.getRoleSet()){
			System.out.println(role.getRoleName());
			for(String permission : role.getPermissionSet()){
				System.out.println(permission);
			}
		}
	}
	
//	@Test
	public void testRoleService(){
		Set<Role> roles=roleService.findRoleSetByUserId(1);
		for(Role role:roles){
			System.out.println(role.toString());
		}
	}
	
	@Test
	public void testAuthorityService(){
		Set<String> authoritNameSet =authorityService.findPermissionSet(11);
		System.out.println("*****************"+authoritNameSet.size());
		for(String authority : authoritNameSet){
			System.out.println(authority);
		}
	}
}
