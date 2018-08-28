/**
 *
 * Copyright 2012 © Novigotech - All rights reserved
 *
 */
package com.tjube.controller.security;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tjube.model.Account;
import com.tjube.model.JournalEvent;

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
	// Today journalEvents value
	//---------------------------------------------------------------------------------------------------------------------

	public static Collection<JournalEvent> getTodayJournalEvents(HttpServletRequest request)
	{
		return bindJournalEvent(request, ContextAttributes.TODAY_JOURNAL_EVENTS);
	}

	public static Collection<JournalEvent> getTodayJournalEvents(HttpSession session)
	{
		return bindJournalEvent(session, ContextAttributes.TODAY_JOURNAL_EVENTS);
	}

	public static void setTodayJournalEvents(HttpServletRequest request, Collection<JournalEvent> value)
	{
		request.getSession().setAttribute(ContextAttributes.TODAY_JOURNAL_EVENTS.getKey(), value);
		request.setAttribute(ContextAttributes.TODAY_JOURNAL_EVENTS.getKey(), value);
	}
	//---------------------------------------------------------------------------------------------------------------------

	public static Collection<JournalEvent> bindJournalEvent(HttpServletRequest request, ContextAttributes key)
	{
		Collection<JournalEvent> result = (Collection<JournalEvent>) request.getAttribute(key.getKey());
		if (result != null)
			return result;

		result = (Collection<JournalEvent>) request.getSession().getAttribute(key.getKey());
		if (result != null)
			request.setAttribute(key.getKey(), result);

		return result;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public static Collection<JournalEvent> bindJournalEvent(HttpSession session, ContextAttributes key)
	{
		return (Collection<JournalEvent>) session.getAttribute(key.getKey());
	}
	
	//---------------------------------------------------------------------------------------------------------------------
}
