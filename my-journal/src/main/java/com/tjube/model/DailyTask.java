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
		@NamedQuery(name = DailyTask.QN.RETRIEVE_DAILY_TASK_WITH_UUID,
				query = "SELECT dt from DailyTask dt where dt.uuid=:uuid"),
		@NamedQuery(name = DailyTask.QN.RETRIEVE_DAILY_TASKS_WITH_JOURNAL,
				query = "SELECT dt from DailyTask dt where dt.journal=:journal"),
		@NamedQuery(name = DailyTask.QN.RETRIEVE_DAILY_TASKS_WITH_MONTHLY_TASK,
				query = "SELECT dt from DailyTask dt where dt.monthlyTask=:monthlyTask"), })
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
		public static final String RETRIEVE_DAILY_TASKS_WITH_JOURNAL = "DailyTask.retrieveDailyTasksWithJournal";
		public static final String RETRIEVE_DAILY_TASKS_WITH_MONTHLY_TASK = "DailyTask.retrieveDailyTasksWithMonthlyTask";

		private QN()
		{

		}
	}

	public DailyTask()
	{
		// TODO Auto-generated constructor stub
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

	@Column(name = "price", nullable = true)
	private Float price;

	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private TaskUnit unit;

	@Column(name = "value", nullable = true)
	private Integer value;

	@ManyToOne(fetch = FetchType.LAZY)
	private Journal journal;

	@ManyToOne(fetch = FetchType.LAZY)
	private CategoryTask categoryTask;

	@ManyToOne(fetch = FetchType.LAZY)
	private MonthlyTask monthlyTask;

	@ManyToOne(fetch = FetchType.LAZY)
	private Wish wish;

	@ManyToOne(fetch = FetchType.LAZY)
	private Objective objective;

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

	public Float getPrice()
	{
		return price;
	}

	public void setPrice(Float price)
	{
		this.price = price;
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

	public CategoryTask getCategoryTask()
	{
		return categoryTask;
	}

	public void setCategoryTask(CategoryTask categoryTask)
	{
		this.categoryTask = categoryTask;
	}

	public MonthlyTask getMonthlyTask()
	{
		return monthlyTask;
	}

	public void setMonthlyTask(MonthlyTask monthlyTask)
	{
		this.monthlyTask = monthlyTask;
	}

	public Wish getWish()
	{
		return wish;
	}

	public void setWish(Wish wish)
	{
		this.wish = wish;
	}

	public Objective getObjective()
	{
		return objective;
	}

	public void setObjective(Objective objective)
	{
		this.objective = objective;
	}

}
