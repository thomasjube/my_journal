package com.tjube.controller.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.tjube.model.Account;

public class SecurityContextFilter
	implements Filter
{
	@Override
	public void init(FilterConfig config)
		throws ServletException
	{
		// Do nothing
	}

	@Override
	public void destroy()
	{
		// Do nothing
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException
	{
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		SecurityContext.setup();

		Account account = SessionAttributes.getLoggedAccount(httpRequest);
		SecurityContext.getInstance().setCurrentUser(account);

		chain.doFilter(request, response);
	}
}
