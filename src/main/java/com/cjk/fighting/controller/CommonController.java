package com.cjk.fighting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CommonController {

	@RequestMapping("/unauthorizedUrl")
	public String login()
	{
		return "common/unauthorizedUrl";
	}
}
