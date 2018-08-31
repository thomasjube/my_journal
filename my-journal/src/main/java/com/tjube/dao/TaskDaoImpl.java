package com.tjube.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
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
import com.tjube.model.MonthlyStats;
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
	public MonthlyTask createMonthlyTask(Journal journal, String description, boolean professional, Month month,
			TaskUnit unit, Integer value)
	{
		MonthlyTask monthlyTaskJPA = new MonthlyTask(journal, description, professional, month, unit, value);

		entityManager.persist(monthlyTaskJPA);

		return monthlyTaskJPA;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateMonthlyTask(MonthlyTask monthlyTask, String description, boolean professional, Month month,
			TaskUnit unit, Integer value)
	{
		if (!entityManager.contains(monthlyTask))
			monthlyTask = entityManager.merge(monthlyTask);

		monthlyTask.setDescription(description);
		monthlyTask.setProfessional(professional);
		monthlyTask.setMonth(month);
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
	public Collection<MonthlyTask> retrieveAllMonthlyTasksByMonth(Journal journal, Month month)
	{
		TypedQuery<MonthlyTask> query = entityManager.createNamedQuery(MonthlyTask.QN.RETRIEVE_MONTHLY_TASKS_WITH_DATE,
				MonthlyTask.class);

		query.setParameter("journal", journal);
		query.setParameter("month", month);

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
	public Map<Month, MonthlyStats> getMonthlyStats(Journal journal)
	{
		TypedQuery<Object[]> query = entityManager.createNamedQuery(DailyTask.QN.GET_MONTHLY_STATS_DAILY_TASK,
				Object[].class);
		query.setParameter("journal", journal);

		Map<Month, MonthlyStats> results = new HashMap<>();

		for (Object[] values : query.getResultList())
		{
			Month month = values[0] != null ? Month.valueOf(values[0].toString().toUpperCase().trim()) : null;
			Long count = values[1] != null ? (Long) values[1] : null;

			MonthlyStats ms = new MonthlyStats();

			if (results.get(month) != null)
				ms = results.get(month);

			ms.setAllDailyTasks(count.intValue());

			results.put(month, ms);
		}

		query = entityManager.createNamedQuery(MonthlyTask.QN.GET_MONTHLY_STATS_ALL_MONTHLY_TASK, Object[].class);
		query.setParameter("journal", journal);

		for (Object[] values : query.getResultList())
		{
			Month month = values[0] != null ? Month.valueOf(values[0].toString().toUpperCase().trim()) : null;
			Long count = values[1] != null ? (Long) values[1] : null;

			MonthlyStats ms = new MonthlyStats();

			if (results.get(month) != null)
				ms = results.get(month);

			ms.setAllMonthlyTasks(count.intValue());

			results.put(month, ms);
		}

		query = entityManager.createNamedQuery(MonthlyTask.QN.GET_MONTHLY_STATS_DONE_MONTHLY_TASK, Object[].class);
		query.setParameter("journal", journal);

		for (Object[] values : query.getResultList())
		{
			Month month = values[0] != null ? Month.valueOf(values[0].toString().toUpperCase().trim()) : null;
			Long count = values[1] != null ? (Long) values[1] : null;

			MonthlyStats ms = new MonthlyStats();

			if (results.get(month) != null)
				ms = results.get(month);

			ms.setFinishMonthlyTasks(count.intValue());

			results.put(month, ms);
		}

		Collection<Budget> budgets = journal.getBudgets();
		Map<Month, BigDecimal> budgetTotalByMonth = new HashMap<>();
		Map<Month, BigDecimal> budgetUsedByMonth = new HashMap<>();

		for (Budget budget : budgets)
		{
			if (budgetTotalByMonth.get(budget.getMonth()) != null)
				budgetTotalByMonth.put(budget.getMonth(),
						budget.getBudgetTotal().add(budgetTotalByMonth.get(budget.getMonth())));
			else
				budgetTotalByMonth.put(budget.getMonth(), budget.getBudgetTotal());

			if (budgetUsedByMonth.get(budget.getMonth()) != null)
				budgetUsedByMonth.put(budget.getMonth(),
						budget.getBudgetTaken().add(budgetUsedByMonth.get(budget.getMonth())));
			else
				budgetTotalByMonth.put(budget.getMonth(), budget.getBudgetTaken());
		}

		for (Entry<Month, BigDecimal> budgetMonth : budgetTotalByMonth.entrySet())
		{
			MonthlyStats ms = new MonthlyStats();

			if (results.get(budgetMonth.getKey()) != null)
				ms = results.get(budgetMonth.getKey());

			ms.setAllbudget(budgetMonth.getValue());

			results.put(budgetMonth.getKey(), ms);
		}

		for (Entry<Month, BigDecimal> budgetMonth : budgetUsedByMonth.entrySet())
		{
			MonthlyStats ms = new MonthlyStats();

			if (results.get(budgetMonth.getKey()) != null)
				ms = results.get(budgetMonth.getKey());

			ms.setUsedBudget(budgetMonth.getValue());

			results.put(budgetMonth.getKey(), ms);
		}

		return results;
	}

	//---------------------------------------------------------------------------------------------------------------------
}
