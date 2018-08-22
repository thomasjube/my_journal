package com.tjube.dao;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tjube.controller.utils.LoginUtils;
import com.tjube.model.Account;
import com.tjube.model.JPAUtils;

@Repository
public class TaskDaoImpl
	implements AccountDao
{
	@PersistenceContext(unitName = "JpaPersistenceUnit2")
	private EntityManager entityManager = null;

	//---------------------------------------------------------------------------------------------------------------------
	// ACCOUNT OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Account createAccount(String email, String password, String firstName, String lastName, String alias,
			LocalDate birthDate)
	{
		Account accountJPA = new Account(email, password, firstName, lastName, alias, birthDate);

		entityManager.persist(accountJPA);

		return accountJPA;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateAccount(Account account, String firstName, String lastName, String alias, LocalDate birthDate)
	{
		if (!entityManager.contains(account))
			account = entityManager.merge(account);

		account.setFirstName(firstName);
		account.setLastName(lastName);
		account.setAlias(alias);
		account.setBirthDate(birthDate);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateAccount(Account account, String password)
	{
		if (!entityManager.contains(account))
			account = entityManager.merge(account);

		account.setPassword(password);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeAccount(Account account)
	{
		if (!entityManager.contains(account))
			account = entityManager.merge(account);

		account.setValid(false);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Account retrieveAccount(UUID uuid)
	{
		TypedQuery<Account> query = entityManager.createNamedQuery(Account.QN.RETRIEVE_ACCOUNT_WITH_UUID,
				Account.class);

		query.setParameter("uuid", uuid);

		return JPAUtils.getSingleResult(query);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Account retrieveAccount(String email)
	{
		TypedQuery<Account> query = entityManager.createNamedQuery(Account.QN.RETRIEVE_ACCOUNT_WITH_EMAIL,
				Account.class);

		query.setParameter("email", email);

		return JPAUtils.getSingleResult(query);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Account retrieveAccount(String email, String password)
	{
		TypedQuery<Account> query = entityManager.createNamedQuery(Account.QN.RETRIEVE_ACCOUNT_WITH_EMAIL_AND_PASSWORD,
				Account.class);

		query.setParameter("email", email);
		query.setParameter("password", LoginUtils.md5Password(password));

		return JPAUtils.getSingleResult(query);
	}

	//---------------------------------------------------------------------------------------------------------------------
}
