package com.tjube.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjube.dao.TaskDao;
import com.tjube.model.Budget;
import com.tjube.model.CategoryTask;
import com.tjube.model.DailyTask;
import com.tjube.model.Journal;
import com.tjube.model.MonthlyStats;
import com.tjube.model.MonthlyTask;
import com.tjube.model.Objective;
import com.tjube.model.Wish;
import com.tjube.model.enums.TaskUnit;

@Service
@Transactional
public class TaskServiceImpl
	implements TaskService
{

	@Autowired
	TaskDao taskDao = null;

	//---------------------------------------------------------------------------------------------------------------------
	// MONTHLY OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public MonthlyTask createMonthlyTask(Journal journal, String description, boolean professional, Month month,
			TaskUnit unit, Integer value)
	{

		return taskDao.createMonthlyTask(journal, description, professional, month, unit, value);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateMonthlyTask(MonthlyTask monthlyTask, String description, boolean professional, Month month,
			TaskUnit unit, Integer value)
	{
		taskDao.updateMonthlyTask(monthlyTask, description, professional, month, unit, value);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeMonthlyTask(MonthlyTask monthlyTask)
	{
		taskDao.removeMonthlyTask(monthlyTask);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public MonthlyTask retrieveMonthlyTask(UUID uuid)
	{
		return taskDao.retrieveMonthlyTask(uuid);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<MonthlyTask> retrieveAllMonthlyTasksByJournal(Journal journal)
	{
		return taskDao.retrieveAllMonthlyTasksByJournal(journal);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<MonthlyTask> retrieveAllMonthlyTasksByMonth(Journal journal, Month month)
	{
		return taskDao.retrieveAllMonthlyTasksByMonth(journal, month);
	}

	//---------------------------------------------------------------------------------------------------------------------
	// DAILY OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public DailyTask createDailyTask(Journal journal, String description, boolean professional, LocalDate date,
			BigDecimal price, TaskUnit unit, Integer value, CategoryTask category, MonthlyTask monthlyTask, Wish wish,
			Budget budget, Objective objective)
	{
		return taskDao.createDailyTask(journal, description, professional, date, price, unit, value, category,
				monthlyTask, wish, budget, objective);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateDailyTask(DailyTask dailyTask, String description, boolean professional, LocalDate date,
			BigDecimal price, TaskUnit unit, Integer value, CategoryTask category, MonthlyTask monthlyTask, Wish wish,
			Budget budget, Objective objective)
	{
		taskDao.updateDailyTask(dailyTask, description, professional, date, price, unit, value, category, monthlyTask,
				wish, budget, objective);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeDailyTask(DailyTask dailyTask)
	{
		taskDao.removeDailyTask(dailyTask);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public DailyTask retrieveDailyTask(UUID uuid)
	{
		return taskDao.retrieveDailyTask(uuid);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<DailyTask> retrieveAllDailyTasksByJournal(Journal journal)
	{
		return taskDao.retrieveAllDailyTasksByJournal(journal);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<DailyTask> retrieveAllDailyTasksByDay(Journal journal, LocalDate localDate)
	{
		return taskDao.retrieveAllDailyTasksByDay(journal, localDate);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Map<Month, MonthlyStats> getMonthlyStats(Journal journal)
	{
		return taskDao.getMonthlyStats(journal);
	}

	//---------------------------------------------------------------------------------------------------------------------

}
