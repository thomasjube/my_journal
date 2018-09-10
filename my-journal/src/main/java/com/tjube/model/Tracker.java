package com.tjube.model;

import java.io.Serializable;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tjube.controller.utils.converter.UUIDAttributeConverter;

@NamedQueries({
		@NamedQuery(name = Tracker.QN.RETRIEVE_TRACKER_WITH_UUID, query = "SELECT t from Tracker t where t.uuid=:uuid"),
		@NamedQuery(name = Tracker.QN.RETRIEVE_TRACKER_WITH_JOURNAL,
				query = "SELECT t from Tracker t where t.journal=:journal and t.month=:month"), })
@Entity
@Table(name = "TRACKER")
public class Tracker
	implements Serializable
{

	private static final long serialVersionUID = -5480865053070705207L;

	//==================================================================================================================================================================================================
	//
	// Query names
	//
	//==================================================================================================================================================================================================

	public static class QN
	{
		public static final String RETRIEVE_TRACKER_WITH_UUID = "Tracker.retrieveTrackerWithUuid";
		public static final String RETRIEVE_TRACKER_WITH_JOURNAL = "Tracker.retrieveTrackerWithJournal";

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

	@Column(name = "name", nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "month", nullable = false)
	private Month month = null;

	@ManyToOne(fetch = FetchType.LAZY)
	private Journal journal;

	@OneToMany(mappedBy = "tracker", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private Collection<DailyTask> dailyTasks = new ArrayList<>();

	@OneToMany(mappedBy = "tracker", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private Collection<TrackerState> states = new ArrayList<>();

	//---------------------------------------------------------------------------------------------------------------------

	public Tracker()
	{
		// Default constructor
	}

	public Tracker(Journal journal, Month month, String name)
	{
		super();
		this.uuid = UUID.randomUUID();
		this.name = name;
		this.month = month;
		this.journal = journal;
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public Month getMonth()
	{
		return month;
	}

	public void setMonth(Month month)
	{
		this.month = month;
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

	public Collection<DailyTask> getDailyTasks()
	{
		return dailyTasks;
	}

	public void setDailyTasks(Collection<DailyTask> dailyTasks)
	{
		this.dailyTasks = dailyTasks;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public Collection<TrackerState> getStates()
	{
		return states;
	}

	public void setStates(Collection<TrackerState> states)
	{
		this.states = states;
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

		if (!(obj instanceof Tracker))
			return false;

		return getId() == ((Tracker) obj).getId();
	}
}
