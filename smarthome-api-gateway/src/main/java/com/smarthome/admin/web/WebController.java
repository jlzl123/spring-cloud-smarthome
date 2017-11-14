package com.smarthome.admin.web;

import java.security.Principal;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.session.Session;
import org.apache.shiro.web.session.HttpServletSession;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smarthome.config.cas.CasShiroConfiguration;

@Slf4j
@Controller
public class WebController {

	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpSession session,RedirectAttributes redirect){
		Principal  principal = request.getUserPrincipal();
		String username = principal.getName();
		log.info("已登录的用户名:"+username);
		
//		session.setAttribute("username", username);
//		request.setAttribute("username", username);
		redirect.addAttribute("username", username);
		String url=null;
//		url =request.getRequestURL().toString();
		//获取发起该请求的原地址
//		url=request.getHeader("Referer");
		//页面登录 请求中带源页面地址
		url=request.getParameter("source");
		log.info("url:"+url);
		return "redirect:"+url;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		log.info("logout session: "+session.getId());
		return "redirect:"+CasShiroConfiguration.casLogoutUrl;
	}
}
