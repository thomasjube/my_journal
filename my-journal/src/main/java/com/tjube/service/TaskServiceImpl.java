package com.tjube.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjube.dao.TaskDao;
import com.tjube.model.CategoryTask;
import com.tjube.model.DailyTask;
import com.tjube.model.Journal;
import com.tjube.model.MonthlyStats;
import com.tjube.model.MonthlyTask;
import com.tjube.model.Objective;
import com.tjube.model.Tracker;
import com.tjube.model.Wish;
import com.tjube.model.enums.TaskStateEvent;

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
			CategoryTask categoryTask, Objective objective, Wish wish)
	{

		return taskDao.createMonthlyTask(journal, description, professional, month, categoryTask, objective, wish);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateMonthlyTask(MonthlyTask monthlyTask, String description, boolean professional, Month month,
			CategoryTask categoryTask, Objective objective, Wish wish)
	{
		taskDao.updateMonthlyTask(monthlyTask, description, professional, month, categoryTask, objective, wish);
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
	public DailyTask createDailyTask(Tracker tracker, LocalDate date)
	{
		return taskDao.createDailyTask(tracker, date);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateDailyTask(DailyTask dailyTask, TaskStateEvent state)
	{
		taskDao.updateDailyTask(dailyTask, state);
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
	public Collection<DailyTask> retrieveAllDailyTasksByTracker(Tracker tracker)
	{
		return taskDao.retrieveAllDailyTasksByTracker(tracker);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<DailyTask> retrieveAllDailyTasksByDay(Tracker tracker, LocalDate localDate)
	{
		return taskDao.retrieveAllDailyTasksByDay(tracker, localDate);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Map<Month, MonthlyStats> getMonthlyStats(Journal journal)
	{
		return taskDao.getMonthlyStats(journal);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateMonthlyTaskState(MonthlyTask monthlyTask, TaskStateEvent state)
	{
		taskDao.updateMonthlyTaskState(monthlyTask, state);
	}
}
