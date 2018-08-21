package com.tjube.dao;

import java.time.LocalDate;
import java.util.UUID;

import com.tjube.model.Account;

public interface AccountDao
{
	//---------------------------------------------------------------------------------------------------------------------
	// ACCOUNT OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	Account createAccount(String email, String password, String firstName, String lastName, String alias,
			LocalDate birthDate);

	void updateAccount(Account account, String firstName, String lastName, String alias, LocalDate birthDate);

	void updateAccount(Account account, String password);

	void removeAccount(Account account);

	Account retrieveAccount(UUID uuid);

	Account retrieveAccount(String email);

	Account retrieveAccount(String email, String password);

	//---------------------------------------------------------------------------------------------------------------------
}
