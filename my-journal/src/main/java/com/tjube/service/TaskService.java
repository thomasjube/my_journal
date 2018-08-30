package com.tjube.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import com.tjube.model.Budget;
import com.tjube.model.CategoryTask;
import com.tjube.model.DailyTask;
import com.tjube.model.Journal;
import com.tjube.model.MonthlyTask;
import com.tjube.model.Objective;
import com.tjube.model.Wish;
import com.tjube.model.enums.TaskUnit;

public interface TaskService
{

	//---------------------------------------------------------------------------------------------------------------------
	// MONTHLY TASK OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	MonthlyTask createMonthlyTask(Journal journal, String description, boolean professional, LocalDate date,
			TaskUnit unit, Integer value);

	void updateMonthlyTask(MonthlyTask monthlyTask, String description, boolean professional, LocalDate date,
			TaskUnit unit, Integer value);

	void removeMonthlyTask(MonthlyTask monthlyTask);

	MonthlyTask retrieveMonthlyTask(UUID uuid);

	Collection<MonthlyTask> retrieveAllMonthlyTasksByJournal(Journal journal);

	Collection<MonthlyTask> retrieveAllMonthlyTasksByMonth(Journal journal, LocalDate localDate);

	//---------------------------------------------------------------------------------------------------------------------
	// DAILY OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	DailyTask createDailyTask(Journal journal, String description, boolean professional, LocalDate date,
			BigDecimal price, TaskUnit unit, Integer value, CategoryTask category, MonthlyTask monthlyTask, Wish wish,
			Budget budget, Objective objective);

	void updateDailyTask(DailyTask dailyTask, String description, boolean professional, LocalDate date,
			BigDecimal price, TaskUnit unit, Integer value, CategoryTask category, MonthlyTask monthlyTask, Wish wish,
			Budget budget, Objective objective);

	void removeDailyTask(DailyTask dailyTask);

	DailyTask retrieveDailyTask(UUID uuid);

	Collection<DailyTask> retrieveAllDailyTasksByJournal(Journal journal);

	Collection<DailyTask> retrieveAllDailyTasksByDay(Journal journal, LocalDate localDate);

	Map<Month, Integer> countDailyTaskByMonth(Journal journal);

}
