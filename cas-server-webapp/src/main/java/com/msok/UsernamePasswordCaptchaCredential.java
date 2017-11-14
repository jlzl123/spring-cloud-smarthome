package com.msok;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jasig.cas.authentication.UsernamePasswordCredential;

/**
 * @ClassName:UsernamePasswordCaptchaCredential.java
 * @Description:   定义我们登录页上输入的认证信息的，比如用户名、密码、验证码等，可以理解为用户认证的相关凭据。 
 * @author gaoguangjin
 * @Date 2015-9-21 下午4:12:17
 */
public class UsernamePasswordCaptchaCredential extends UsernamePasswordCredential {
	private static final long serialVersionUID = 8317889802836113837L;
	
	@NotNull
	@Size(min = 1, message = "required.captcha")
	private String captcha;
	
	public String getCaptcha() {
		return captcha;
	}
	
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
}