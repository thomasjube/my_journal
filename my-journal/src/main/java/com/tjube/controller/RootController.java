/**
 *
 * Copyright 2012 © Novigotech - All rights reserved
 *
 */
package com.tjube.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tjube.model.Account;

@Controller
public class RootController
{
	@Resource
	ContentPageService m_contentPageService = null;

	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
	public String redirectToLogin()
	{
		Account acc = SecurityContext.getInstance().getCurrentUser();

		if (acc != null)
		{
			return "redirect:/app";
		}

		return "redirect:/login";
	}
}
