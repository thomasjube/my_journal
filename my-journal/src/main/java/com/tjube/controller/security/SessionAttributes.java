/**
 *
 * Copyright 2012 © Novigotech - All rights reserved
 *
 */
package com.tjube.controller.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tjube.model.Account;

public class SessionAttributes
{

	//---------------------------------------------------------------------------------------------------------------------
	// Logged user value
	//---------------------------------------------------------------------------------------------------------------------

	public static Account getLoggedAccount(HttpServletRequest request)
	{
		return bindAccount(request, ContextAttributes.LOGGED_ACCOUNT);
	}

	public static Account getLoggedAccount(HttpSession session)
	{
		return bindAccount(session, ContextAttributes.LOGGED_ACCOUNT);
	}

	public static void setLoggedAccount(HttpServletRequest request, Account value)
	{
		request.getSession().setAttribute(ContextAttributes.LOGGED_ACCOUNT.getKey(), value);
		request.setAttribute(ContextAttributes.LOGGED_ACCOUNT.getKey(), value);
	}
	//---------------------------------------------------------------------------------------------------------------------

	public static Account bindAccount(HttpServletRequest request, ContextAttributes key)
	{
		Account result = (Account) request.getAttribute(key.getKey());
		if (result != null)
			return result;

		result = (Account) request.getSession().getAttribute(key.getKey());
		if (result != null)
			request.setAttribute(key.getKey(), result);

		return result;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public static Account bindAccount(HttpSession session, ContextAttributes key)
	{
		return (Account) session.getAttribute(key.getKey());
	}

	//---------------------------------------------------------------------------------------------------------------------
}
