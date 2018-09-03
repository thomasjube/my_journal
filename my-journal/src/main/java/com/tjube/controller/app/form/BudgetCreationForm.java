package com.tjube.controller.app.form;

import java.math.BigDecimal;
import java.time.Month;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.tjube.model.Budget;

public class BudgetCreationForm
{
	@NotBlank
	private String description;

	private UUID journalUuid;

	private Month month;

	private UUID categoryTaskUuid;

	private UUID budgetUuid = null;

	private BigDecimal budgetTotal = BigDecimal.ZERO;

	public BudgetCreationForm()
	{
		// Default Constructor
	}

	public BudgetCreationForm(UUID journalUuid, Month month)
	{
		this.journalUuid = journalUuid;
		this.month = month;
	}

	public BudgetCreationForm(Budget budget)
	{
		this.description = budget.getDescription();
		this.journalUuid = budget.getJournal() != null ? budget.getJournal().getUuid() : null;
		this.month = budget.getMonth();
		this.categoryTaskUuid = budget.getCategoryTask() != null ? budget.getCategoryTask().getUuid() : null;
		this.budgetUuid = budget.getUuid();
		this.budgetTotal = budget.getBudgetTotal();
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public UUID getBudgetUuid()
	{
		return budgetUuid;
	}

	public void setBudgetUuid(UUID budgetUuid)
	{
		this.budgetUuid = budgetUuid;
	}

	public UUID getCategoryTaskUuid()
	{
		return categoryTaskUuid;
	}

	public void setCategoryTaskUuid(UUID categoryTaskUuid)
	{
		this.categoryTaskUuid = categoryTaskUuid;
	}

	public UUID getJournalUuid()
	{
		return journalUuid;
	}

	public void setJournalUuid(UUID journalUuid)
	{
		this.journalUuid = journalUuid;
	}

	public Month getMonth()
	{
		return month;
	}

	public void setMonth(Month month)
	{
		this.month = month;
	}

	public BigDecimal getBudgetTotal()
	{
		return budgetTotal;
	}

	public void setBudgetTotal(BigDecimal budgetTotal)
	{
		this.budgetTotal = budgetTotal;
	}
}
