package com.tjube.model;

import java.io.Serializable;
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

@NamedQueries({
		@NamedQuery(name = Objective.QN.RETRIEVE_OBJECTIVE_WITH_UUID,
				query = "SELECT item from Objective item where item.uuid=:uuid"),
		@NamedQuery(name = Objective.QN.RETRIEVE_OBJECTIVE_BY_MOTHLY_TASK,
				query = "SELECT item from Objective item where item.monthlyTask=:monthlyTask") })
@Entity
@Table(name = "OBJECTIVE")
public class Objective
	implements Serializable
{
	private static final long serialVersionUID = -9145257705716573234L;

	//---------------------------------------------------------------------------------------------------------------------

	public static class QN
	{
		public static final String RETRIEVE_OBJECTIVE_WITH_UUID = "Objective.retrieveObjectiveByUuid";
		public static final String RETRIEVE_OBJECTIVE_BY_MOTHLY_TASK = "Objective.retrieveObjectiveByMonthlyTask";

		private QN()
		{

		}
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "uuid", unique = true)
	private UUID uuid;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Journal journal = null;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private MonthlyTask monthlyTask = null;

	@Column(name = "description", nullable = false)
	private String description = null;

	@Enumerated(EnumType.STRING)
	@Column(name = "state", nullable = false)
	private TaskStateEvent state = TaskStateEvent.TO_DO;

	//---------------------------------------------------------------------------------------------------------------------

	public Objective()
	{
		// Default Constructor
	}

	public Objective(Journal journal, MonthlyTask monthlyTask, String description)
	{
		this.uuid = UUID.randomUUID();

		this.journal = journal;
		this.monthlyTask = monthlyTask;

		this.description = description;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public int getId()
	{
		return id;
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

	public Journal getJournal()
	{
		return journal;
	}

	public void setJournal(Journal journal)
	{
		this.journal = journal;
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

	public MonthlyTask getMonthlyTask()
	{
		return monthlyTask;
	}

	public void setMonthlyTask(MonthlyTask monthlyTask)
	{
		this.monthlyTask = monthlyTask;
	}
	//---------------------------------------------------------------------------------------------------------------------

	public TaskStateEvent getState()
	{
		return state;
	}

	public void setState(TaskStateEvent state)
	{
		this.state = state;
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

		if (!(obj instanceof Objective))
			return false;

		return getId() == ((Objective) obj).getId();
	}

	//---------------------------------------------------------------------------------------------------------------------

}