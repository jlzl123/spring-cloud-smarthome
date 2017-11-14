package com.msok;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jasig.cas.authentication.Credential;
import org.jasig.cas.web.flow.AuthenticationViaFormAction;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.util.StringUtils;
import org.springframework.webflow.execution.RequestContext;

import com.google.code.kaptcha.Constants;

/**
 * @ClassName:AuthenticationViaCaptchaFormAction.java
 * @Description:  重写表单验证，新增验证码校验  
 * @author gaoguangjin
 * @Date 2015-9-21 下午4:46:34
 */
public class AuthenticationViaCaptchaFormAction extends AuthenticationViaFormAction {
	
	public final String validateCaptcha(final RequestContext context, final Credential credential, final MessageContext messageContext) {
		final HttpServletRequest request = WebUtils.getHttpServletRequest(context);
		
		HttpSession session = request.getSession();
		// 获取验证码
		String rand = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
		
		UsernamePasswordCaptchaCredential upc = (UsernamePasswordCaptchaCredential) credential;
		String captcha = upc.getCaptcha();
		
		if (!StringUtils.hasText(rand) || !StringUtils.hasText(captcha)) {
			messageContext.addMessage(new MessageBuilder().error().code("error.authentication.captcha.bad").build());
			return "error";
		}
		if (captcha.equalsIgnoreCase(rand)) {
			return "success";
		}
		messageContext.addMessage(new MessageBuilder().error().code("error.authentication.captcha.bad").build());
		return "error";
	}
}