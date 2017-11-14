package com.msok;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jasig.services.persondir.IPersonAttributes;
import org.jasig.services.persondir.support.AttributeNamedPersonImpl;
import org.jasig.services.persondir.support.StubPersonAttributeDao;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @ClassName:UserStubPersonAttributeDao.java
 * @Description:    用来定义我们需要返回给客户端相关信息的接口
 * @author gaoguangjin
 * @Date 2015-9-21 下午4:11:34
 */
public class UserStubPersonAttributeDao extends StubPersonAttributeDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * UsernamePasswordCaptchaCredential getId() 返回的name
	 */
	public IPersonAttributes getPerson(String uid) {
		Map<String, List<Object>> attributes = new HashMap<String, List<Object>>();
		attributes.put("id", Collections.singletonList((Object) uid));
		attributes.put("name", Collections.singletonList((Object) "高广金gaoguangjin"));
		return new AttributeNamedPersonImpl(attributes);
	}
}