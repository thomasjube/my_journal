package com.tjube.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjube.dao.AccountDao;
import com.tjube.model.Account;
import com.tjube.model.exceptions.DuplicatingAccountEmailException;

@Service
@Transactional
public class TaskServiceImpl
	implements AccountService
{
	@Autowired
	AccountDao accountDao = null;

	//---------------------------------------------------------------------------------------------------------------------
	// ACCOUNT OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Account createAccount(String email, String password, String firstName, String lastName, String alias,
			LocalDate birthDate)
		throws DuplicatingAccountEmailException
	{
		Account result = retrieveAccount(email);
		if (result != null)
			throw new DuplicatingAccountEmailException("Account Already exists with email " + email);

		return accountDao.createAccount(email, password, firstName, lastName, alias, birthDate);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateAccount(Account account, String firstName, String lastName, String alias, LocalDate birthDate)
	{
		accountDao.updateAccount(account, firstName, lastName, alias, birthDate);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateAccount(Account account, String password)
	{
		accountDao.updateAccount(account, password);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeAccount(Account account)
	{
		if (account.isValid())
			accountDao.removeAccount(account);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Account retrieveAccount(UUID uuid)
	{
		return accountDao.retrieveAccount(uuid);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Account retrieveAccount(String email)
	{
		return accountDao.retrieveAccount(email);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Account retrieveAccount(String email, String password)
	{
		return accountDao.retrieveAccount(email, password);
	}

	//---------------------------------------------------------------------------------------------------------------------
}
