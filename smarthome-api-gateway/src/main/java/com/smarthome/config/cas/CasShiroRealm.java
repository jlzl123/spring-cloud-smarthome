package com.smarthome.config.cas;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.smarthome.admin.entity.Role;
import com.smarthome.admin.entity.User;
import com.smarthome.admin.service.UserService;

@Slf4j
public class CasShiroRealm extends CasRealm {
	
	@Autowired
	private UserService userService;
	
	@PostConstruct
	protected void initProperty(){
		setCasServerUrlPrefix(CasShiroConfiguration.casServerUrlPrefix);
		setCasService(CasShiroConfiguration.shiroServerUrlPerfix+CasShiroConfiguration.casFilterUrlPattern);
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		log.info("*********登录认证---->cas服务器************");
		return super.doGetAuthenticationInfo(token);
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		// TODO Auto-generated method stub
		log.info("###########权限认证############");
		String username=(String)getAvailablePrincipal(principal);
		User user=userService.findUserByUserName(username);
		log.info(user.toString());
		if(user!=null){
		   SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		   Set<String> roleNames = new HashSet<>();
		   for(Role role : user.getRoleSet()){
			   roleNames.add(role.getRoleName());
			   info.setRoles(roleNames);
			   info.setStringPermissions(role.getPermissionSet());
		   }
		   return info;
		}
		log.info("###########权限认证失败T_T############");
		// 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
		return null;
	}
}
