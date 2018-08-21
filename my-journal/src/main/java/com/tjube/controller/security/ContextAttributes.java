package com.tjube.controller.security;

public enum ContextAttributes
{
	LOGGED_ACCOUNT("loggedAccount");

	private String key = null;

	private ContextAttributes(String key)
	{
		this.key = key;
	}

	public String getKey()
	{
		return this.key;
	}
}
