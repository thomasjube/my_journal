package com.tjube.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
		@NamedQuery(name = CategoryTask.QN.RETRIEVE_CATEGORY_TASK_WITH_UUID,
				query = "SELECT ct from CategoryTask ct where ct.uuid=:uuid"),
		@NamedQuery(name = CategoryTask.QN.RETRIEVE_CATEGORY_TASKS_WITH_ACCOUNT,
				query = "SELECT ct from CategoryTask ct where ct.account=:account OR ct.account is null"),
		@NamedQuery(name = CategoryTask.QN.RETRIEVE_CATEGORY_TASKS_SYSTEM,
				query = "SELECT ct from CategoryTask ct where ct.account is null"), })
@Entity
@Table(name = "CATEGORY_TASK")
public class CategoryTask
	implements Serializable
{

	private static final long serialVersionUID = 2429636837730813045L;

	//==================================================================================================================================================================================================
	//
	// Query names
	//
	//==================================================================================================================================================================================================

	public static class QN
	{
		public static final String RETRIEVE_CATEGORY_TASK_WITH_UUID = "CategoryTask.retrieveCategoryTaskWithUuid";
		public static final String RETRIEVE_CATEGORY_TASKS_WITH_ACCOUNT = "CategoryTask.retrieveCategoryTasksWithAccount";
		public static final String RETRIEVE_CATEGORY_TASKS_SYSTEM = "CategoryTask.retrieveCategoryTaskSystem";

		private QN()
		{

		}
	}

	public CategoryTask()
	{
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "uuid", unique = true)
	private UUID uuid;

	@Column(name = "description", nullable = false)
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	private Account account;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Account getAccount()
	{
		return account;
	}

	public void setAccount(Account account)
	{
		this.account = account;
	}

}
