package com.tjube.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.tjube.controller.utils.LoginUtils;

@NamedQueries({
		@NamedQuery(name = Account.QN.RETRIEVE_ACCOUNT_WITH_UUID,
				query = "SELECT account from Account account where account.uuid=:uuid"),
		@NamedQuery(name = Account.QN.RETRIEVE_ACCOUNT_WITH_EMAIL_AND_PASSWORD,
				query = "SELECT account from Account account where account.email=:email and account.password=:password and account.valid is TRUE") })
@Entity
@Table(name = "ACCOUNT")
public class Account
	implements Serializable
{
	private static final long serialVersionUID = 7763379993060723463L;

	//==================================================================================================================================================================================================
	//
	// Query names
	//
	//==================================================================================================================================================================================================

	public static class QN
	{
		public static final String RETRIEVE_ACCOUNT_WITH_UUID = "Game.retrieveAccountWithUuid";
		public static final String RETRIEVE_ACCOUNT_WITH_EMAIL_AND_PASSWORD = "Game.retrieveAccountWithEmailAndPassword";

		private QN()
		{

		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "uuid")
	private UUID uuid;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "valid")
	private boolean valid;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "alias")
	private String alias;

	@Column(name = "birth_date")
	private LocalDate birthDate = null;

	public Account()
	{
		// Default Constructor
	}

	public Account(String email, String password, String firstName, String lastName, String alias, LocalDate birthDate)
	{
		this.uuid = UUID.randomUUID();

		this.email = email;
		this.password = LoginUtils.md5Password(password);

		this.firstName = firstName;
		this.lastName = lastName;
		this.alias = alias;
		this.birthDate = birthDate;

	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = LoginUtils.md5Password(password);
	}

	public boolean isValid()
	{
		return valid;
	}

	public void setValid(boolean valid)
	{
		this.valid = valid;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getAlias()
	{
		return alias;
	}

	public void setAlias(String alias)
	{
		this.alias = alias;
	}

	public LocalDate getBirthDate()
	{
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate)
	{
		this.birthDate = birthDate;
	}

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}
}
