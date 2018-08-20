/**
 *
 * Copyright 2012 © Novigotech - All rights reserved
 *
 */
package com.tjube.controller.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjube.controller.security.SecurityContext;
import com.tjube.model.Account;

@Controller
@RequestMapping(value = { "/app" })
public class AppController
{

	//==================================================================================================================================================================================================
	//
	// Request Mappings
	//
	//==================================================================================================================================================================================================

	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Base redirection
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/", "" })
	public String showIndex(HttpSession session, HttpServletRequest request)
	{
		Account acc = SecurityContext.getInstance().getCurrentUser();
		if (acc != null)
		{
			return "redirect:/app/personal";
		}

		return "redirect:/login";
	}

}
