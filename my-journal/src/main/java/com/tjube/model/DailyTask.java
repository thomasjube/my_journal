package com.tjube.model;

import java.io.Serializable;
import java.time.LocalDate;
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

import com.tjube.controller.utils.converter.LocalDateAttributeConverter;
import com.tjube.controller.utils.converter.UUIDAttributeConverter;

@NamedQueries({
		@NamedQuery(name = DailyTask.QN.RETRIEVE_DAILY_TASK_WITH_UUID,
				query = "SELECT dt from DailyTask dt where dt.uuid=:uuid"),
		@NamedQuery(name = DailyTask.QN.RETRIEVE_DAILY_TASKS_WITH_TRACKER,
				query = "SELECT dt from DailyTask dt where dt.tracker=:tracker"),
		@NamedQuery(name = DailyTask.QN.RETRIEVE_DAILY_TASKS_WITH_DATE,
				query = "SELECT dt from DailyTask dt where dt.date=:date and dt.tracker=:tracker"), })
@Entity
@Table(name = "DAILY_TASK")
public class DailyTask
	implements Serializable
{
	private static final long serialVersionUID = 5482265096516757424L;

	//==================================================================================================================================================================================================
	//
	// Query names
	//
	//==================================================================================================================================================================================================

	public static class QN
	{
		public static final String RETRIEVE_DAILY_TASK_WITH_UUID = "DailyTask.retrieveDailyTaskWithUuid";
		public static final String RETRIEVE_DAILY_TASKS_WITH_TRACKER = "DailyTask.retrieveDailyTasksWithTracker";
		public static final String RETRIEVE_DAILY_TASKS_WITH_DATE = "DailyTask.retrieveDailyTasksWithDate";

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
	@Column(name = "date", nullable = false)
	private LocalDate date = null;

	@ManyToOne(fetch = FetchType.LAZY)
	private TrackerState state;

	@ManyToOne(fetch = FetchType.LAZY)
	private Tracker tracker;

	//---------------------------------------------------------------------------------------------------------------------

	public DailyTask()
	{
		// Default constructor
	}

	public DailyTask(Tracker tracker, LocalDate date)
	{
		super();
		this.date = date;
		this.tracker = tracker;
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

	public TrackerState getState()
	{
		return state;
	}

	public void setState(TrackerState state)
	{
		this.state = state;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public LocalDate getDate()
	{
		return date;
	}

	public void setDate(LocalDate date)
	{
		this.date = date;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public Tracker getTracker()
	{
		return tracker;
	}

	public void setTracker(Tracker tracker)
	{
		this.tracker = tracker;
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

		if (!(obj instanceof DailyTask))
			return false;

		return getId() == ((DailyTask) obj).getId();
	}
}
