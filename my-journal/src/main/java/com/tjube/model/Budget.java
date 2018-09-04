package com.tjube.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tjube.controller.utils.converter.UUIDAttributeConverter;

@NamedQueries({
		@NamedQuery(name = Budget.QN.RETRIEVE_BUDGET_WITH_UUID,
				query = "SELECT item from Budget item where item.uuid=:uuid"),
		@NamedQuery(name = Budget.QN.RETRIEVE_BUDGET_BY_MONTH,
				query = "SELECT item from Budget item where item.journal=:journal AND item.month=:month order by item.month asc"),
		@NamedQuery(name = Budget.QN.RETRIEVE_BUDGET_BY_MONTH_AND_CATEGORY,
				query = "SELECT item from Budget item where item.journal=:journal AND item.month=:month AND item.categoryTask=:categoryTask order by item.month asc"),
		@NamedQuery(name = Budget.QN.RETRIEVE_BUDGETS_BY_JOURNAL,
				query = "SELECT item from Budget item where item.journal=:journal") })
@Entity
@Table(name = "BUDGET")
public class Budget
	implements Serializable
{

	private static final long serialVersionUID = -2131580076008143414L;

	//---------------------------------------------------------------------------------------------------------------------

	public static class QN
	{
		public static final String RETRIEVE_BUDGET_WITH_UUID = "Budget.retrieveBudgetWithUuid";
		public static final String RETRIEVE_BUDGET_BY_MONTH = "Budget.retrieveBudgetByMonth";
		public static final String RETRIEVE_BUDGET_BY_MONTH_AND_CATEGORY = "Budget.retrieveBudgetByMonthAndCategory";
		public static final String RETRIEVE_BUDGETS_BY_JOURNAL = "Budget.retrieveBudgetsByJournal";

		private QN()
		{

		}
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Convert(converter = UUIDAttributeConverter.class)
	@Column(name = "uuid", unique = true, nullable = false)
	private UUID uuid;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Journal journal = null;

	@Enumerated(EnumType.STRING)
	@Column(name = "month", nullable = false)
	private Month month = null;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private CategoryTask categoryTask = null;

	@Column(name = "description", nullable = false)
	private String description = null;

	@Column(name = "budget_total", nullable = false, scale = 2)
	private BigDecimal budgetTotal = BigDecimal.ZERO;

	@Column(name = "budget_balance", nullable = false, scale = 2)
	private BigDecimal budgetBalance = BigDecimal.ZERO;

	@Column(name = "budget_taken", nullable = false, scale = 2)
	private BigDecimal budgetTaken = BigDecimal.ZERO;

	@OneToMany(mappedBy = "budget", fetch = FetchType.LAZY)
	private Collection<Wish> wishes = new ArrayList<>();

	//---------------------------------------------------------------------------------------------------------------------

	public Budget()
	{
		// Default Constructor
	}

	public Budget(String description, BigDecimal budgetTotal, Journal journal, Month month, CategoryTask categoryTask)
	{
		this.uuid = UUID.randomUUID();

		this.description = description;
		this.journal = journal;
		this.month = month;
		this.categoryTask = categoryTask;

		setBudgetTotal(budgetTotal);
	}

	//---------------------------------------------------------------------------------------------------------------------

	public int getId()
	{
		return id;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public Journal getJournal()
	{
		return journal;
	}

	public void setJournal(Journal journal)
	{
		this.journal = journal;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public CategoryTask getCategoryTask()
	{
		return categoryTask;
	}

	public void setCategoryTask(CategoryTask categoryTask)
	{
		this.categoryTask = categoryTask;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public Month getMonth()
	{
		return month;
	}

	public void setMonth(Month month)
	{
		this.month = month;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public BigDecimal getBudgetTotal()
	{
		return budgetTotal;
	}

	public void setBudgetTotal(BigDecimal budgetTotal)
	{
		this.budgetTotal = budgetTotal;
		updateBudget();
	}

	//---------------------------------------------------------------------------------------------------------------------

	public BigDecimal getBudgetBalance()
	{
		return budgetBalance;
	}

	public void setBudgetBalance(BigDecimal budgetBalance)
	{
		this.budgetBalance = budgetBalance;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public BigDecimal getBudgetTaken()
	{
		return budgetTaken;
	}

	public void setBudgetTaken(BigDecimal budgetTaken)
	{
		this.budgetTaken = budgetTaken;
	}

	//---------------------------------------------------------------------------------------------------------------------

	private void updateBudget()
	{
		BigDecimal newBalance = budgetTotal;
		BigDecimal newTaken = BigDecimal.ZERO;

		for (Wish wish : wishes)
		{
			newBalance = newBalance.subtract(wish.getPrice());
			newTaken = newTaken.add(wish.getPrice());
		}

		setBudgetBalance(newBalance);
		setBudgetTaken(newTaken);
	}

	//---------------------------------------------------------------------------------------------------------------------

	public Collection<Wish> getWishes()
	{
		return wishes;
	}

	public void addWish(Wish wish)
	{
		wishes.add(wish);
		updateBudget();
	}

	public void removeWish(Wish wish)
	{
		wishes.remove(wish);
		updateBudget();
	}

	public void clearWishes()
	{
		wishes.clear();
		updateBudget();
	}

	//---------------------------------------------------------------------------------------------------------------------

	public BigDecimal getPercentage()
	{
		BigDecimal result = this.budgetTaken.multiply(new BigDecimal(100)).divide(this.budgetTotal).setScale(0,
				RoundingMode.HALF_UP);
		return result;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public int hashCode()
	{
		return new Long(getId()).hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;

		if (!(obj instanceof Budget))
			return false;

		return getId() == ((Budget) obj).getId();
	}

	//---------------------------------------------------------------------------------------------------------------------

}