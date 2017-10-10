package com.cn.fashion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactsController {
	
	@RequestMapping("/contacts")
	private String contacts() {
		
		return "contacts/contacts";
	}

}
