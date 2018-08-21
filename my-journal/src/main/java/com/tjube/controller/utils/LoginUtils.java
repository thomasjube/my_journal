package com.tjube.controller.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.tjube.controller.security.SecurityContext;
import com.tjube.model.Account;

public class LoginUtils
{
	public static String login()
	{
		SecurityContext context = SecurityContext.getInstance();
		if (context == null)
			return ModelUtils.REDIRECT_LOGIN;

		Account acc = SecurityContext.getInstance().getCurrentUser();
		if (acc == null)
			return ModelUtils.REDIRECT_LOGIN;

		return null;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public static String md5Password(String password)
	{
		if (password == null)
			return null;

		try
		{
			MessageDigest digest = MessageDigest.getInstance("MD5");

			digest.update(password.getBytes("utf-8"));

			byte[] md5 = digest.digest();

			BigInteger bi = new BigInteger(1, md5);

			return bi.toString(16);
		}
		catch (UnsupportedEncodingException | NoSuchAlgorithmException e)
		{
			return null;
		}
	}

	//---------------------------------------------------------------------------------------------------------------------

}
