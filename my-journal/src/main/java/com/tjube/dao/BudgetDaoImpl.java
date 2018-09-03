package com.tjube.dao;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tjube.model.Budget;
import com.tjube.model.CategoryTask;
import com.tjube.model.JPAUtils;
import com.tjube.model.Journal;

@Repository
public class BudgetDaoImpl
	implements BudgetDao
{
	@PersistenceContext(unitName = "JpaPersistenceUnit2")
	private EntityManager entityManager = null;

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Budget createBudget(String description, BigDecimal budgetTotal, Journal journal, Month month,
			CategoryTask categoryTask)
	{
		Budget budgetJPA = new Budget(description, budgetTotal, journal, month, categoryTask);

		entityManager.persist(budgetJPA);

		return budgetJPA;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateBudget(Budget budget, String description, BigDecimal budgetTotal)
	{
		if (!entityManager.contains(budget))
			budget = entityManager.merge(budget);

		budget.setDescription(description);
		budget.setBudgetTotal(budgetTotal);

	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeBudget(Budget budget)
	{
		if (!entityManager.contains(budget))
			budget = entityManager.merge(budget);

		entityManager.remove(budget);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Budget retrieveBudget(UUID uuid)
	{
		TypedQuery<Budget> query = entityManager.createNamedQuery(Budget.QN.RETRIEVE_BUDGET_WITH_UUID, Budget.class);

		query.setParameter("uuid", uuid);

		return JPAUtils.getSingleResult(query);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<Budget> retrieveBudgets(Journal journal, Month month)
	{
		TypedQuery<Budget> query = entityManager.createNamedQuery(Budget.QN.RETRIEVE_BUDGET_BY_MONTH, Budget.class);

		query.setParameter("journal", journal);
		query.setParameter("month", month);

		return query.getResultList();
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Budget retrieveBudget(Journal journal, Month month, CategoryTask categoryTask)
	{
		TypedQuery<Budget> query = entityManager.createNamedQuery(Budget.QN.RETRIEVE_BUDGET_BY_MONTH_AND_CATEGORY,
				Budget.class);

		query.setParameter("journal", journal);
		query.setParameter("month", month);
		query.setParameter("categoryTask", categoryTask);

		return JPAUtils.getSingleResult(query);
	}
}
