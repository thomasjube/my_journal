package com.tjube.model;

import java.io.Serializable;
import java.time.Month;
import java.util.UUID;

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
import javax.persistence.Table;

import com.tjube.controller.utils.converter.UUIDAttributeConverter;
import com.tjube.model.enums.TaskStateEvent;
import com.tjube.model.enums.TaskUnit;

@NamedQueries({
		@NamedQuery(name = MonthlyTask.QN.RETRIEVE_MONTHLY_TASK_WITH_UUID,
				query = "SELECT mt from MonthlyTask mt where mt.uuid=:uuid"),
		@NamedQuery(name = MonthlyTask.QN.RETRIEVE_MONTHLY_TASKS_WITH_JOURNAL,
				query = "SELECT mt from MonthlyTask mt where mt.journal=:journal"),
		@NamedQuery(name = MonthlyTask.QN.RETRIEVE_MONTHLY_TASKS_WITH_DATE,
				query = "SELECT mt from MonthlyTask mt where mt.month=:month and mt.journal=:journal"),
		@NamedQuery(name = MonthlyTask.QN.GET_MONTHLY_STATS_ALL_MONTHLY_TASK,
				query = "SELECT month, count(mt.id) from MonthlyTask mt where mt.journal=:journal group by 1"),
		@NamedQuery(name = MonthlyTask.QN.GET_MONTHLY_STATS_DONE_MONTHLY_TASK,
				query = "SELECT month, count(mt.id) from MonthlyTask mt where mt.state = 'DONE' AND mt.journal=:journal group by 1") })
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
		public static final String GET_MONTHLY_STATS_ALL_MONTHLY_TASK = "MonthlyTask.getMonthlyStatsAllMonthlyTask";
		public static final String GET_MONTHLY_STATS_DONE_MONTHLY_TASK = "MonthlyTask.getMonthlyStatsDoneMonthlyTask";

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

	@Column(name = "description", nullable = false)
	private String description;

	@Column
	@Enumerated(EnumType.STRING)
	private TaskStateEvent state = TaskStateEvent.TO_DO;

	@Column(name = "professional", nullable = true)
	private boolean professional;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Month month = null;

	@ManyToOne(fetch = FetchType.LAZY)
	private Journal journal;

	@ManyToOne(fetch = FetchType.LAZY)
	private CategoryTask categoryTask;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Objective objective;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Wish wish;
	
	//---------------------------------------------------------------------------------------------------------------------

	public MonthlyTask()
	{
		// TODO Auto-generated constructor stub
	}

	public MonthlyTask(Journal journal, String description, boolean professional, Month month,CategoryTask categoryTask,Objective objective, Wish wish)
	{
		this.uuid = UUID.randomUUID();
		this.description = description;
		this.professional = professional;
		this.month = month;
		this.journal = journal;
		this.categoryTask = categoryTask;
		this.objective = objective;
		this.wish = wish;
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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
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

	public boolean isProfessional()
	{
		return professional;
	}

	public void setProfessional(boolean professional)
	{
		this.professional = professional;
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

	public Objective getObjective() {
		return objective;
	}
	
	public void setObjective(Objective objective) {
		this.objective = objective;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public Wish getWish() {
		return wish;
	}
	
	public void setWish(Wish wish) {
		this.wish = wish;
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

	public CategoryTask getCategoryTask() {
		return categoryTask;
	}
	
	public void setCategoryTask(CategoryTask categoryTask) {
		this.categoryTask = categoryTask;
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

		if (!(obj instanceof MonthlyTask))
			return false;

		return getId() == ((MonthlyTask) obj).getId();
	}

}
