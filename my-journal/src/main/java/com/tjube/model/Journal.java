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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tjube.controller.utils.converter.LocalDateAttributeConverter;
import com.tjube.controller.utils.converter.UUIDAttributeConverter;

@NamedQueries({
		@NamedQuery(name = Journal.QN.RETRIEVE_JOURNAL_WITH_UUID, query = "SELECT j from Journal j where j.uuid=:uuid"),
		@NamedQuery(name = Journal.QN.RETRIEVE_JOURNAL_WITH_ACCOUNT,
				query = "SELECT j from Journal j where j.account=:account"),
		@NamedQuery(name = Journal.QN.RETRIEVE_JOURNAL_WITH_ACCOUNT_AND_DATE,
				query = "SELECT j from Journal j where j.account=:account and j.beginDate <=:date and j.endDate >=:date"), })
@Entity
@Table(name = "JOURNAL")
public class Journal
	implements Serializable
{
	private static final long serialVersionUID = -7895428505511560082L;

	//==================================================================================================================================================================================================
	//
	// Query names
	//
	//==================================================================================================================================================================================================

	public static class QN
	{
		public static final String RETRIEVE_JOURNAL_WITH_UUID = "Journal.retrieveJournalWithUuid";
		public static final String RETRIEVE_JOURNAL_WITH_ACCOUNT = "Journal.retrieveJournalWithAccount";
		public static final String RETRIEVE_JOURNAL_WITH_ACCOUNT_AND_DATE = "Journal.retrieveJournalWithAccountAndDate";

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

	@Convert(converter = LocalDateAttributeConverter.class)
	@Column(name = "begin_date", nullable = false)
	private LocalDate beginDate = null;

	@Convert(converter = LocalDateAttributeConverter.class)
	@Column(name = "end_date", nullable = true)
	private LocalDate endDate = null;

	@ManyToOne(fetch = FetchType.LAZY)
	private Account account;

	@OneToMany(mappedBy = "journal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<JournalEvent> journalEvents = new ArrayList<>();

	@OneToMany(mappedBy = "journal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Budget> budgets = new ArrayList<>();

	//---------------------------------------------------------------------------------------------------------------------

	public Journal()
	{
		// Default constructor
	}

	public Journal(Account account, LocalDate beginDate, LocalDate endDate)
	{
		this.uuid = UUID.randomUUID();
		this.account = account;
		this.beginDate = beginDate;
		this.endDate = endDate;
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

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public LocalDate getBeginDate()
	{
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate)
	{
		this.beginDate = beginDate;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public LocalDate getEndDate()
	{
		return endDate;
	}

	public void setEndDate(LocalDate endDate)
	{
		this.endDate = endDate;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public Account getAccount()
	{
		return account;
	}

	public void setAccount(Account account)
	{
		this.account = account;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public Collection<JournalEvent> getJournalEvents()
	{
		return journalEvents;
	}

	public void setJournalEvents(Collection<JournalEvent> journalEvents)
	{
		this.journalEvents = journalEvents;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public Collection<Budget> getBudgets()
	{
		return budgets;
	}

	public void setBudgets(Collection<Budget> budgets)
	{
		this.budgets = budgets;
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

		if (!(obj instanceof Journal))
			return false;

		return getId() == ((Journal) obj).getId();
	}
}
