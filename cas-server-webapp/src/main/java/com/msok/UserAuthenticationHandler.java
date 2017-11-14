package com.msok;

import java.security.GeneralSecurityException;

import javax.security.auth.login.FailedLoginException;

import org.apache.commons.codec.digest.DigestUtils;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @ClassName:UserAuthenticationHandler.java
 * @Description: 自定义用户名和密码校验  
 * @author gaoguangjin
 * @Date 2015-9-21 下午6:24:39
 */
public class UserAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler {
	
	private JdbcTemplate jdbcTemplate;
	
	@Override
	protected HandlerResult authenticateUsernamePasswordInternal(UsernamePasswordCredential transformedCredential)
			throws GeneralSecurityException, PreventedException {
		String username = transformedCredential.getUsername();
		String password = DigestUtils.shaHex(transformedCredential.getPassword());
		String sql = "select count(1) from t_unite_user where user_id=? and password=?";
		int i;
		try {
			i = jdbcTemplate.queryForObject(sql, new Object[] { username, password }, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			throw new FailedLoginException();
		}
		if (i > 0)
			return createHandlerResult(transformedCredential, new SimplePrincipal(username + "-" + password), null);
		throw new FailedLoginException();
		
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}