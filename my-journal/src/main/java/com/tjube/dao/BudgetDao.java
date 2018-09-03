package com.tjube.dao;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Collection;
import java.util.UUID;

import com.tjube.model.Budget;
import com.tjube.model.CategoryTask;
import com.tjube.model.Journal;

public interface BudgetDao
{
	Budget createBudget(String description, BigDecimal budgetTotal, Journal journal, Month month,
			CategoryTask categoryTask);

	void updateBudget(Budget budget, String description, BigDecimal budgetTotal);

	void removeBudget(Budget budget);

	public Budget retrieveBudget(UUID uuid);

	public Collection<Budget> retrieveBudgets(Journal journal, Month month);

	public Budget retrieveBudget(Journal journal, Month month, CategoryTask categoryTask);
}
