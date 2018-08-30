package com.tjube.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tjube.model.Budget;
import com.tjube.model.CategoryTask;
import com.tjube.model.DailyTask;
import com.tjube.model.JPAUtils;
import com.tjube.model.Journal;
import com.tjube.model.MonthlyTask;
import com.tjube.model.Objective;
import com.tjube.model.Wish;
import com.tjube.model.enums.TaskUnit;

@Repository
public class TaskDaoImpl
	implements TaskDao
{
	@PersistenceContext(unitName = "JpaPersistenceUnit2")
	private EntityManager entityManager = null;

	//---------------------------------------------------------------------------------------------------------------------
	// MONTHLY TASK OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public MonthlyTask createMonthlyTask(Journal journal, String description, boolean professional, LocalDate date,
			TaskUnit unit, Integer value)
	{
		MonthlyTask monthlyTaskJPA = new MonthlyTask(journal, description, professional, date, unit, value);

		entityManager.persist(monthlyTaskJPA);

		return monthlyTaskJPA;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateMonthlyTask(MonthlyTask monthlyTask, String description, boolean professional, LocalDate date,
			TaskUnit unit, Integer value)
	{
		if (!entityManager.contains(monthlyTask))
			monthlyTask = entityManager.merge(monthlyTask);

		monthlyTask.setDescription(description);
		monthlyTask.setProfessional(professional);
		monthlyTask.setDate(date);
		monthlyTask.setUnit(unit);
		monthlyTask.setValue(value);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeMonthlyTask(MonthlyTask monthlyTask)
	{
		if (!entityManager.contains(monthlyTask))
			monthlyTask = entityManager.merge(monthlyTask);

		entityManager.remove(monthlyTask);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public MonthlyTask retrieveMonthlyTask(UUID uuid)
	{
		TypedQuery<MonthlyTask> query = entityManager.createNamedQuery(MonthlyTask.QN.RETRIEVE_MONTHLY_TASK_WITH_UUID,
				MonthlyTask.class);

		query.setParameter("uuid", uuid);

		return JPAUtils.getSingleResult(query);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<MonthlyTask> retrieveAllMonthlyTasksByJournal(Journal journal)
	{
		TypedQuery<MonthlyTask> query = entityManager
				.createNamedQuery(MonthlyTask.QN.RETRIEVE_MONTHLY_TASKS_WITH_JOURNAL, MonthlyTask.class);

		query.setParameter("journal", journal);

		return query.getResultList();
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<MonthlyTask> retrieveAllMonthlyTasksByMonth(Journal journal, LocalDate localDate)
	{
		TypedQuery<MonthlyTask> query = entityManager.createNamedQuery(MonthlyTask.QN.RETRIEVE_MONTHLY_TASKS_WITH_DATE,
				MonthlyTask.class);

		query.setParameter("journal", journal);
		query.setParameter("localDate", localDate);

		return query.getResultList();
	}

	//---------------------------------------------------------------------------------------------------------------------
	// DAILY TASK OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public DailyTask createDailyTask(Journal journal, String description, boolean professional, LocalDate date,
			BigDecimal price, TaskUnit unit, Integer value, CategoryTask category, MonthlyTask monthlyTask, Wish wish,
			Budget budget, Objective objective)
	{
		DailyTask dailyTaskJPA = new DailyTask(journal, description, professional, date, price, unit, value, category,
				monthlyTask, wish, budget, objective);

		entityManager.persist(dailyTaskJPA);

		return dailyTaskJPA;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateDailyTask(DailyTask dailyTask, String description, boolean professional, LocalDate date,
			BigDecimal price, TaskUnit unit, Integer value, CategoryTask category, MonthlyTask monthlyTask, Wish wish,
			Budget budget, Objective objective)
	{
		if (!entityManager.contains(dailyTask))
			dailyTask = entityManager.merge(dailyTask);

		dailyTask.setDescription(description);
		dailyTask.setProfessional(professional);
		dailyTask.setDate(date);
		dailyTask.setPrice(price);
		dailyTask.setUnit(unit);
		dailyTask.setValue(value);
		dailyTask.setCategoryTask(category);
		dailyTask.setMonthlyTask(monthlyTask);
		dailyTask.setWish(wish);
		dailyTask.setBudget(budget);
		dailyTask.setObjective(objective);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeDailyTask(DailyTask dailyTask)
	{
		if (!entityManager.contains(dailyTask))
			dailyTask = entityManager.merge(dailyTask);

		entityManager.remove(dailyTask);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public DailyTask retrieveDailyTask(UUID uuid)
	{
		TypedQuery<DailyTask> query = entityManager.createNamedQuery(DailyTask.QN.RETRIEVE_DAILY_TASK_WITH_UUID,
				DailyTask.class);

		query.setParameter("uuid", uuid);

		return JPAUtils.getSingleResult(query);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<DailyTask> retrieveAllDailyTasksByJournal(Journal journal)
	{
		TypedQuery<DailyTask> query = entityManager.createNamedQuery(DailyTask.QN.RETRIEVE_DAILY_TASKS_WITH_JOURNAL,
				DailyTask.class);

		query.setParameter("journal", journal);

		return query.getResultList();
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<DailyTask> retrieveAllDailyTasksByDay(Journal journal, LocalDate localDate)
	{
		TypedQuery<DailyTask> query = entityManager.createNamedQuery(DailyTask.QN.RETRIEVE_DAILY_TASKS_WITH_DATE,
				DailyTask.class);

		query.setParameter("journal", journal);
		query.setParameter("date", localDate);

		return query.getResultList();
	}

	@Override
	public Map<Month, Integer> countDailyTaskByMonth(Journal journal)
	{
		TypedQuery<Object[]> query = entityManager.createNamedQuery(DailyTask.QN.COUNT_DAILY_TASK_BY_MONTH,
				Object[].class);

		query.setParameter("journal", journal);
		//		query.setMaxResults(maxResults);

		Map<Month, Integer> results = new HashMap<>();

		for (Object[] values : query.getResultList())
		{
			Month month = values[0] != null ? Month.valueOf(values[0].toString().toUpperCase().trim()) : null;
			Long count = values[1] != null ? (Long) values[1] : null;

			results.put(month, count.intValue());
		}

		return results;
	}

	//---------------------------------------------------------------------------------------------------------------------
}
