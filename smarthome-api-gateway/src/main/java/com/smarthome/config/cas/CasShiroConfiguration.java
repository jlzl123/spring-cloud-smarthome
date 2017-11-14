package com.smarthome.config.cas;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasSubjectFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
public class CasShiroConfiguration {
	
	public static final String casServerUrlPrefix = "http://127.0.0.1:8080/cas";
	
	public static final String casLoginUrl = casServerUrlPrefix + "/login";
	
	public static final String casLogoutUrl = casServerUrlPrefix + "/logout";
	//cas客户端地址
	public static final String shiroServerUrlPerfix = "http://127.0.0.1:8888";
	// casFilter UrlPattern
    public static final String casFilterUrlPattern = "/cas";
    // 登录地址，后面service是client获取ticket的地址
    public static final String loginUrl = casLoginUrl + "?service=" + shiroServerUrlPerfix + casFilterUrlPattern;
    // 登出地址（casserver启用service跳转功能，需在webapps\cas\WEB-INF\cas.properties文件中启用cas.logout.followServiceRedirects=true）
    public static final String logoutUrl = casLogoutUrl + "?service=" + loginUrl; 
    
    public static final String loginSuccessUrl = "/home";
    
    public static final String unauthorizedUrl = "/error/403.html";
    
    @Bean
    public DefaultWebSecurityManager securityManager(){
    	DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    	securityManager.setRealm(getShiroRelam());
    	securityManager.setCacheManager(getEhCacheManager());
    	securityManager.setSubjectFactory(new CasSubjectFactory());
    	return securityManager;
    }
    
    @Bean
    public CasShiroRealm getShiroRelam(){
    	CasShiroRealm shiroRelam = new CasShiroRealm();
    	shiroRelam.setCasServerUrlPrefix(casServerUrlPrefix);
    	shiroRelam.setCasService(shiroServerUrlPerfix+casFilterUrlPattern);
    	return shiroRelam;
    }
    
    @Bean
    public EhCacheManager getEhCacheManager(){
    	EhCacheManager ehEacheManager = new EhCacheManager();
    	ehEacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
    	return ehEacheManager;
    }
    
	/*
	 * 注册单点登出listener
	 */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ServletListenerRegistrationBean singleSignOutHttpSessionListener(){
    	ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean();
        bean.setListener(new SingleSignOutHttpSessionListener());
        bean.setEnabled(true);
    	return bean;
    }
   
	/*
	 * 注册单点登出filter
	 */
    @Bean
    public FilterRegistrationBean singleSignOutFilter(){
    	FilterRegistrationBean bean = new FilterRegistrationBean();
    	bean.setName("singleSignOutFilter");
    	bean.setFilter(new SingleSignOutFilter());
    	bean.addUrlPatterns("/*");
    	bean.setEnabled(true); 	
    	return bean;
    }
    
	// DelegatingFilterProxy过滤器链
	@Bean
	public FilterRegistrationBean delegatingFilterProxy() {
		FilterRegistrationBean filterRegisterationBean = new FilterRegistrationBean();
		filterRegisterationBean.setFilter(new DelegatingFilterProxy(
				"shiroFilter"));
		filterRegisterationBean.addInitParameter("targetFilterLifecycle",
				"true");
		filterRegisterationBean.setEnabled(true);
		filterRegisterationBean.addUrlPatterns("/*");
		return filterRegisterationBean;
	}
	
	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	
	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisor = new DefaultAdvisorAutoProxyCreator();
		advisor.setProxyTargetClass(true);
		return advisor;
	}
	
	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(
			DefaultSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor auth = new AuthorizationAttributeSourceAdvisor();
		auth.setSecurityManager(securityManager);
		return auth;
	}
	
	@Bean(name="casFilter")
	public CasFilter getCasFilter(){
		CasFilter casFilter = new CasFilter();
		casFilter.setName("casFilter");
		casFilter.setFailureUrl(loginUrl);
		casFilter.setLoginUrl(loginUrl);
		casFilter.setEnabled(true);
		return casFilter;
	}
	
	@Bean(name="shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		
		shiroFilterFactoryBean.setLoginUrl(loginUrl);
		shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
		
		Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
		filters.put("casFilter", getCasFilter());
		
		shiroFilterFactoryBean.setFilters(filters);
		loadShiroFilterChain(shiroFilterFactoryBean);
		
		return shiroFilterFactoryBean;
	}
	
	protected void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean){
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		
		//1.shiro集成cas后，首先添加该规则,自定义的casFilter过滤器
		filterChainDefinitionMap.put(casFilterUrlPattern, "casFilter");
		
        //2.不拦截的请求  
        filterChainDefinitionMap.put("/css/**","anon");  
        filterChainDefinitionMap.put("/js/**","anon");  
//        filterChainDefinitionMap.put("/login", "anon");  
        // 此处将logout页面设置为anon，而不是logout，因为logout被单点处理，而不需要再被shiro的logoutFilter进行拦截  
        filterChainDefinitionMap.put("/logout","anon");  
        filterChainDefinitionMap.put("/error","anon"); 
        
        //3.拦截的请求（从本地数据库获取或者从casserver获取(webservice,http等远程方式)，看你的角色权限配置在哪里）  
//        filterChainDefinitionMap.put("/user", "authc"); //需要登录  
  
        filterChainDefinitionMap.put("/login", "authc");
        //拦截api-gateway请求， 
//        filterChainDefinitionMap.put("/api-gateway/**", "authc"); 
        filterChainDefinitionMap.put("/api-gateway/**", "authc,perms[admin:user:add]"); 
     // 这里为了测试，固定写死的值，也可以从数据库或其他配置中读取
        filterChainDefinitionMap.put("/user/edit/**", "authc,perms[user:edit]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
	}
}
