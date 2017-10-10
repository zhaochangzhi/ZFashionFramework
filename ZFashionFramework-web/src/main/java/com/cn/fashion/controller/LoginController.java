package com.cn.fashion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	/**
	 * 登陆跳转
	 * @return
	 */
	@RequestMapping("/login")
	private String login() {
		
		System.out.println("========== 登录跳转 ==========");
		return "login/login";
	}
	
	/**
	 * 登陆操作
	 * @return
	 */
	@RequestMapping("/dologin")
	private String dologin() {
		
		System.out.println("========== 登录操作 ==========");
		return "main";
	}
	
	/**
	 * 登出
	 * @return
	 */
	@RequestMapping("/logout")
	private String logout() {
		
		return "login/login";
	}
	
	/**
	 * 注册
	 * @return
	 */
	@RequestMapping("/signup")
	private String signUp() {
		
		return "login/signup";
	}
	
	/**
	 * 忘记密码
	 * @return
	 */
	@RequestMapping("/forget")
	private String forget() {
		
		return "login/forget";
	}
	
	
}
