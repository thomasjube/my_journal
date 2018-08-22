package com.tjube.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
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
import javax.persistence.Table;

import com.tjube.model.enums.TaskStateEvent;
import com.tjube.model.enums.TaskUnit;

@NamedQueries({
		@NamedQuery(name = MonthlyTask.QN.RETRIEVE_MONTHLY_TASK_WITH_UUID,
				query = "SELECT mt from MonthlyTask mt where mt.uuid=:uuid"),
		@NamedQuery(name = MonthlyTask.QN.RETRIEVE_MONTHLY_TASKS_WITH_JOURNAL,
				query = "SELECT mt from MonthlyTask mt where mt.journal=:journal"),
		@NamedQuery(name = MonthlyTask.QN.RETRIEVE_MONTHLY_TASKS_WITH_DATE,
				query = "SELECT mt from MonthlyTask mt where mt.date=:date"), })
@Entity
@Table(name = "MONTHLY_TASK")
public class MonthlyTask
	implements Serializable
{
	private static final long serialVersionUID = 5365418181134367097L;

	//==================================================================================================================================================================================================
	//
	// Query names
	//
	//==================================================================================================================================================================================================

	public static class QN
	{
		public static final String RETRIEVE_MONTHLY_TASK_WITH_UUID = "MonthlyTask.retrieveMonthlyTaskWithUuid";
		public static final String RETRIEVE_MONTHLY_TASKS_WITH_JOURNAL = "MonthlyTask.retrieveMonthlyTasksWithJournal";
		public static final String RETRIEVE_MONTHLY_TASKS_WITH_DATE = "MonthlyTask.retrieveMonthlyTaskWithDate";

		private QN()
		{

		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "uuid", unique = true)
	private UUID uuid;

	@Column(name = "description", nullable = false)
	private String description;

	@Column
	@Enumerated(EnumType.STRING)
	private TaskStateEvent state;

	@Column(name = "professional", nullable = true)
	private boolean professional;

	@Column(name = "date", nullable = false)
	private LocalDate date = null;

	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private TaskUnit unit;

	@Column(name = "value", nullable = true)
	private Integer value;

	@ManyToOne(fetch = FetchType.LAZY)
	private Journal journal;

	public MonthlyTask()
	{
		// TODO Auto-generated constructor stub
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public TaskStateEvent getState()
	{
		return state;
	}

	public void setState(TaskStateEvent state)
	{
		this.state = state;
	}

	public boolean isProfessional()
	{
		return professional;
	}

	public void setProfessional(boolean professional)
	{
		this.professional = professional;
	}

	public LocalDate getDate()
	{
		return date;
	}

	public void setDate(LocalDate date)
	{
		this.date = date;
	}

	public TaskUnit getUnit()
	{
		return unit;
	}

	public void setUnit(TaskUnit unit)
	{
		this.unit = unit;
	}

	public Integer getValue()
	{
		return value;
	}

	public void setValue(Integer value)
	{
		this.value = value;
	}

	public Journal getJournal()
	{
		return journal;
	}

	public void setJournal(Journal journal)
	{
		this.journal = journal;
	}

}
