package com.tjube.service;

import java.time.LocalDate;
import java.util.UUID;

import com.tjube.model.Account;
import com.tjube.model.DuplicatingAccountEmailException;

public interface AccountService
{
	//---------------------------------------------------------------------------------------------------------------------
	// ACCOUNT OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	Account createAccount(String email, String password, String firstName, String lastName, String alias,
			LocalDate birthDate)
		throws DuplicatingAccountEmailException;

	void updateAccount(Account account, String firstName, String lastName, String alias, LocalDate birthDate);

	void updateAccount(Account account, String password);

	void removeAccount(Account account);

	Account retrieveAccount(UUID uuid);

	Account retrieveAccount(String email);

	Account retrieveAccount(String email, String password);

	//---------------------------------------------------------------------------------------------------------------------
}
