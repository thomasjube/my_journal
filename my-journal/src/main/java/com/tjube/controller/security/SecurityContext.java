/**
 *
 * Copyright 2012 © Novigotech - All rights reserved
 *
 */
package com.tjube.controller.security;

import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.tjube.model.Account;

public class SecurityContext
{
	private static ThreadLocal<SecurityContext> CONTEXT = new ThreadLocal<>();

	private TransactionSynchronizationAdapter m_transactionSynchronizationAdapter = new TransactionSynchronizationAdapter() {
		@Override
		public void afterCompletion(int status)
		{
			switch (status)
			{
				case TransactionSynchronization.STATUS_COMMITTED:
				case TransactionSynchronization.STATUS_ROLLED_BACK:

					break;

				default:
					break;
			}
		}
	};

	//==================================================================================================================================================================================================
	//
	// Static operations
	//
	//==================================================================================================================================================================================================

	public static SecurityContext getInstance()
	{
		return CONTEXT.get();
	}

	//------------------------------------------------------------------------------------------------------------------

	public static SecurityContext setup()
	{
		SecurityContext result = new SecurityContext();
		CONTEXT.set(result);

		return result;
	}

	//------------------------------------------------------------------------------------------------------------------

	public static void cleanup()
	{
		CONTEXT.set(null);
	}

	//==================================================================================================================================================================================================
	//
	// Instance members
	//
	//==================================================================================================================================================================================================

	//------------------------------------------------------------------------------------------------------------------
	// Internal caching tools
	//------------------------------------------------------------------------------------------------------------------

	private enum CachedAclState
	{
		NO_ACL,
		EXISTING;
	};

	// CONTEXT logged user

	private Account m_currentUser = null;

	//==================================================================================================================================================================================================
	//
	// Construction
	//
	//==================================================================================================================================================================================================

	private SecurityContext()
	{
		// Default constructor
	}

	//==================================================================================================================================================================================================
	//
	// Public operations
	//
	//==================================================================================================================================================================================================

	//------------------------------------------------------------------------------------------------------------------

	public void registerTransactionListener()
	{
		if (TransactionSynchronizationManager.isSynchronizationActive())
			TransactionSynchronizationManager.registerSynchronization(m_transactionSynchronizationAdapter);
	}

	//------------------------------------------------------------------------------------------------------------------
	// Login related operations
	//------------------------------------------------------------------------------------------------------------------

	public void setCurrentUser(Account acc)
	{
		m_currentUser = acc;
	}

	//------------------------------------------------------------------------------------------------------------------

	public Account getCurrentUser()
	{
		return m_currentUser;
	}

}
