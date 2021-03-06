package com.tjube.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MonthlyStats
{

	private Integer allMonthlyTasks = 0;

	private Integer finishMonthlyTasks = 0;

	private BigDecimal allbudget = BigDecimal.ZERO;

	private BigDecimal usedBudget = BigDecimal.ZERO;

	public MonthlyStats()
	{
		// TODO Auto-generated constructor stub
	}

	public Integer getAllMonthlyTasks()
	{
		return allMonthlyTasks;
	}

	public void setAllMonthlyTasks(Integer allMonthlyTasks)
	{
		this.allMonthlyTasks = allMonthlyTasks;
	}

	public Integer getFinishMonthlyTasks()
	{
		return finishMonthlyTasks;
	}

	public void setFinishMonthlyTasks(Integer finishMonthlyTasks)
	{
		this.finishMonthlyTasks = finishMonthlyTasks;
	}

	public BigDecimal getAllbudget()
	{
		return allbudget;
	}

	public void setAllbudget(BigDecimal allbudget)
	{
		this.allbudget = allbudget;
	}

	public BigDecimal getUsedBudget()
	{
		return usedBudget;
	}

	public void setUsedBudget(BigDecimal usedBudget)
	{
		this.usedBudget = usedBudget;
	}

	public BigDecimal getAllbudgetPercent()
	{
		if (this.allbudget.setScale(0) == BigDecimal.ZERO || this.allbudget == null)
			return BigDecimal.ZERO;
		else 
			return this.usedBudget.multiply(new BigDecimal(100)).divide(this.allbudget,0,
					RoundingMode.HALF_UP);
	}

	public Integer getAllMonthlyTasksPercent()
	{
		if (this.allMonthlyTasks == 0)
			return 0;
		else
			return this.finishMonthlyTasks * 100 / this.allMonthlyTasks;
	}

}
