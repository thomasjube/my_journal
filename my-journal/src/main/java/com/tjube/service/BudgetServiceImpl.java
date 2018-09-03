package com.tjube.service;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjube.dao.BudgetDao;
import com.tjube.model.Budget;
import com.tjube.model.CategoryTask;
import com.tjube.model.Journal;

@Service
@Transactional
public class BudgetServiceImpl
	implements BudgetService
{

	@Autowired
	BudgetDao budgetDao;

	@Override
	public Budget createBudget(String description, BigDecimal budgetTotal, Journal journal, Month month,
			CategoryTask categoryTask)
	{
		return budgetDao.createBudget(description, budgetTotal, journal, month, categoryTask);
	}

	@Override
	public void updateBudget(Budget budget, String description, BigDecimal budgetTotal)
	{
		budgetDao.updateBudget(budget, description, budgetTotal);
	}

	@Override
	public void removeBudget(Budget budget)
	{
		budgetDao.removeBudget(budget);
	}

	@Override
	public Budget retrieveBudget(UUID uuid)
	{
		return budgetDao.retrieveBudget(uuid);
	}

	@Override
	public Collection<Budget> retrieveBudgets(Journal journal, Month month)
	{
		return budgetDao.retrieveBudgets(journal, month);
	}

	@Override
	public Budget retrieveBudget(Journal journal, Month month, CategoryTask categoryTask)
	{
		return budgetDao.retrieveBudget(journal, month, categoryTask);
	}
}
