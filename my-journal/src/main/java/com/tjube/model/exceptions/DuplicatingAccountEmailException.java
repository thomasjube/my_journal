package com.tjube.model.exceptions;

public class DuplicatingAccountEmailException
	extends Exception
{

	private static final long serialVersionUID = -4615689616370403110L;

	public DuplicatingAccountEmailException()
	{
		super();
	}

	public DuplicatingAccountEmailException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public DuplicatingAccountEmailException(String message)
	{
		super(message);
	}

	public DuplicatingAccountEmailException(Throwable cause)
	{
		super(cause);
	}

}
