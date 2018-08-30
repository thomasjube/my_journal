package com.tjube.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
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

import com.tjube.controller.utils.converter.LocalDateAttributeConverter;
import com.tjube.controller.utils.converter.UUIDAttributeConverter;
import com.tjube.model.enums.TaskStateEvent;
import com.tjube.model.enums.TaskUnit;

@NamedQueries({
		@NamedQuery(name = DailyTask.QN.RETRIEVE_DAILY_TASK_WITH_UUID,
				query = "SELECT dt from DailyTask dt where dt.uuid=:uuid"),
		@NamedQuery(name = DailyTask.QN.RETRIEVE_DAILY_TASKS_WITH_JOURNAL,
				query = "SELECT dt from DailyTask dt where dt.journal=:journal"),
		@NamedQuery(name = DailyTask.QN.RETRIEVE_DAILY_TASKS_WITH_MONTHLY_TASK,
				query = "SELECT dt from DailyTask dt where dt.monthlyTask=:monthlyTask"),
		@NamedQuery(name = DailyTask.QN.RETRIEVE_DAILY_TASKS_WITH_DATE,
				query = "SELECT dt from DailyTask dt where dt.date=:date and dt.journal=:journal"),
		@NamedQuery(name = DailyTask.QN.COUNT_DAILY_TASK_BY_MONTH,
				query = "SELECT to_char(dt.date,'Month') as mon, count(dt.id) from DailyTask dt where dt.journal=:journal group by 1"), })
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
		public static final String RETRIEVE_DAILY_TASKS_WITH_DATE = "DailyTask.retrieveDailyTasksWithDate";
		public static final String COUNT_DAILY_TASK_BY_MONTH = "DailyTask.countDailyTasksByMonth";

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

	@Convert(converter = LocalDateAttributeConverter.class)
	@Column(name = "date", nullable = false)
	private LocalDate date = null;

	@Column(name = "price", nullable = true)
	private BigDecimal price;

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
	private Budget budget;

	@ManyToOne(fetch = FetchType.LAZY)
	private Objective objective;

	//---------------------------------------------------------------------------------------------------------------------

	public DailyTask()
	{
		// Default constructor
	}

	public DailyTask(Journal journal, String description, boolean professional, LocalDate date, BigDecimal price,
			TaskUnit unit, Integer value, CategoryTask categoryTask, MonthlyTask monthlyTask, Wish wish, Budget budget,
			Objective objective)
	{
		super();
		this.uuid = UUID.randomUUID();
		this.description = description;
		this.professional = professional;
		this.date = date;
		this.price = price;
		this.unit = unit;
		this.value = value;
		this.journal = journal;
		this.categoryTask = categoryTask;
		this.monthlyTask = monthlyTask;
		this.wish = wish;
		this.budget = budget;
		this.objective = objective;
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

	public LocalDate getDate()
	{
		return date;
	}

	public void setDate(LocalDate date)
	{
		this.date = date;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public BigDecimal getPrice()
	{
		return price;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public TaskUnit getUnit()
	{
		return unit;
	}

	public void setUnit(TaskUnit unit)
	{
		this.unit = unit;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public Integer getValue()
	{
		return value;
	}

	public void setValue(Integer value)
	{
		this.value = value;
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

	public CategoryTask getCategoryTask()
	{
		return categoryTask;
	}

	public void setCategoryTask(CategoryTask categoryTask)
	{
		this.categoryTask = categoryTask;
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

	public Wish getWish()
	{
		return wish;
	}

	public void setWish(Wish wish)
	{
		this.wish = wish;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public Objective getObjective()
	{
		return objective;
	}

	public void setObjective(Objective objective)
	{
		this.objective = objective;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public Budget getBudget()
	{
		return budget;
	}

	public void setBudget(Budget budget)
	{
		this.budget = budget;
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
