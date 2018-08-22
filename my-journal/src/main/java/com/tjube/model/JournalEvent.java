package com.tjube.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

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
import javax.persistence.Table;

import com.tjube.controller.utils.converter.LocalDateTimeAttributeConverter;
import com.tjube.controller.utils.converter.UUIDAttributeConverter;

@NamedQueries({
		@NamedQuery(name = JournalEvent.QN.RETRIEVE_JOURNAL_EVENT_WITH_UUID,
				query = "SELECT je from JournalEvent je where je.uuid=:uuid"),
		@NamedQuery(name = JournalEvent.QN.RETRIEVE_JOURNAL_EVENTS_WITH_JOURNAL,
				query = "SELECT je from JournalEvent je where je.journal=:journal"),
		@NamedQuery(name = JournalEvent.QN.RETRIEVE_JOURNAL_EVENTS_WITH_JOURNAL_AND_DATETIME,
				query = "SELECT je from JournalEvent je where je.journal=:journal and je.dateTime=:dateTime"), })
@Entity
@Table(name = "JOURNAL_EVENT")
public class JournalEvent
	implements Serializable
{

	private static final long serialVersionUID = -4627677229466253723L;

	//==================================================================================================================================================================================================
	//
	// Query names
	//
	//==================================================================================================================================================================================================

	public static class QN
	{
		public static final String RETRIEVE_JOURNAL_EVENT_WITH_UUID = "JournalEvent.retrieveJournalEventWithUuid";
		public static final String RETRIEVE_JOURNAL_EVENTS_WITH_JOURNAL = "JournalEvent.retrieveJournalEventsWithJournal";
		public static final String RETRIEVE_JOURNAL_EVENTS_WITH_JOURNAL_AND_DATETIME = "JournalEvent.retrieveJournalEventsWithJournalAndDateTime";

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

	@Column
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime dateTime;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "place", nullable = true)
	private String place;

	@Column(name = "comments", nullable = true)
	private String comments;

	@Column(name = "annually", nullable = true)
	private boolean annually;

	@ManyToOne(fetch = FetchType.LAZY)
	private Journal journal;

	//---------------------------------------------------------------------------------------------------------------------

	public JournalEvent()
	{
		// TODO Auto-generated constructor stub
	}

	public JournalEvent(Journal journal, LocalDateTime dateTime, String description, String place, String comments,
			boolean isAnnually)
	{
		this.journal = journal;
		this.dateTime = dateTime;
		this.description = description;
		this.place = place;
		this.comments = comments;
		this.annually = isAnnually;
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

	public LocalDateTime getDateTime()
	{
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime)
	{
		this.dateTime = dateTime;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public String getPlace()
	{
		return place;
	}

	public void setPlace(String place)
	{
		this.place = place;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public String getComments()
	{
		return comments;
	}

	public void setComments(String comments)
	{
		this.comments = comments;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public boolean isAnnually()
	{
		return annually;
	}

	public void setAnnually(boolean annually)
	{
		this.annually = annually;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public Journal getJournal()
	{
		return journal;
	}

	public void setJournal(Journal journal)
	{
		this.journal = journal;
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

		if (!(obj instanceof JournalEvent))
			return false;

		return getId() == ((JournalEvent) obj).getId();
	}
}
