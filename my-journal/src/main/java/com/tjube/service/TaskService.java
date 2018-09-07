package com.tjube.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import com.tjube.model.CategoryTask;
import com.tjube.model.DailyTask;
import com.tjube.model.Journal;
import com.tjube.model.MonthlyStats;
import com.tjube.model.MonthlyTask;
import com.tjube.model.Objective;
import com.tjube.model.Tracker;
import com.tjube.model.TrackerState;
import com.tjube.model.Wish;
import com.tjube.model.enums.TaskStateEvent;

public interface TaskService
{

	//---------------------------------------------------------------------------------------------------------------------
	// MONTHLY TASK OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	MonthlyTask createMonthlyTask(Journal journal, String description, boolean professional, Month month,
			CategoryTask categoryTask, Objective objective, Wish wish);

	void updateMonthlyTask(MonthlyTask monthlyTask, String description, boolean professional, Month month,
			CategoryTask categoryTask, Objective objective, Wish wish);

	void removeMonthlyTask(MonthlyTask monthlyTask);

	MonthlyTask retrieveMonthlyTask(UUID uuid);

	Collection<MonthlyTask> retrieveAllMonthlyTasksByJournal(Journal journal);

	Collection<MonthlyTask> retrieveAllMonthlyTasksByMonth(Journal journal, Month month);

	//---------------------------------------------------------------------------------------------------------------------
	// DAILY OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	DailyTask createDailyTask(Tracker tracker, LocalDate date, TrackerState trackerState);

	void updateDailyTask(DailyTask dailyTask, TrackerState state);

	void removeDailyTask(DailyTask dailyTask);

	DailyTask retrieveDailyTask(UUID uuid);

	Collection<DailyTask> retrieveAllDailyTasksByTracker(Tracker tracker);

	DailyTask retrieveDailyTasksByDay(Tracker tracker, LocalDate localDate);

	Map<Month, MonthlyStats> getMonthlyStats(Journal journal);

	void updateMonthlyTaskState(MonthlyTask monthlyTask, TaskStateEvent state);

}
