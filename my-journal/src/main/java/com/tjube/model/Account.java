package com.tjube.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tjube.controller.utils.LoginUtils;
import com.tjube.controller.utils.converter.LocalDateAttributeConverter;
import com.tjube.controller.utils.converter.UUIDAttributeConverter;

@NamedQueries({
		@NamedQuery(name = Account.QN.RETRIEVE_ACCOUNT_WITH_UUID, query = "SELECT a from Account a where a.uuid=:uuid"),
		@NamedQuery(name = Account.QN.RETRIEVE_ACCOUNT_WITH_EMAIL,
				query = "SELECT a from Account a where a.email=:email AND a.valid = TRUE"),
		@NamedQuery(name = Account.QN.RETRIEVE_ACCOUNT_WITH_EMAIL_AND_PASSWORD,
				query = "SELECT a from Account a where a.email=:email and a.password=:password and a.valid = TRUE") })
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
		public static final String RETRIEVE_ACCOUNT_WITH_UUID = "Account.retrieveAccountWithUuid";
		public static final String RETRIEVE_ACCOUNT_WITH_EMAIL = "Account.retrieveAccountWithEmail";
		public static final String RETRIEVE_ACCOUNT_WITH_EMAIL_AND_PASSWORD = "Account.retrieveAccountWithEmailAndPassword";

		private QN()
		{

		}
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Convert(converter = UUIDAttributeConverter.class)
	@Column(name = "uuid", unique = true, nullable = false)
	private UUID uuid;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "valid", nullable = false)
	private boolean valid = true;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "alias", nullable = true)
	private String alias;

	@Convert(converter = LocalDateAttributeConverter.class)
	@Column(name = "birth_date", nullable = true)
	private LocalDate birthDate = null;

	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Journal> journals = new ArrayList<>();

	//---------------------------------------------------------------------------------------------------------------------

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

	//---------------------------------------------------------------------------------------------------------------------

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = LoginUtils.md5Password(password);
	}

	//---------------------------------------------------------------------------------------------------------------------

	public boolean isValid()
	{
		return valid;
	}

	public void setValid(boolean valid)
	{
		this.valid = valid;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public String getAlias()
	{
		return alias;
	}

	public void setAlias(String alias)
	{
		this.alias = alias;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public LocalDate getBirthDate()
	{
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate)
	{
		this.birthDate = birthDate;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public Collection<Journal> getJournals()
	{
		return journals;
	}

	public void setJournals(Collection<Journal> journals)
	{
		this.journals = journals;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public int hashCode()
	{
		return new Long(getId()).hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;

		if (!(obj instanceof Account))
			return false;

		return getId() == ((Account) obj).getId();
	}
}
