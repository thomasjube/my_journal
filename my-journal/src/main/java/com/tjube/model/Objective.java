package com.tjube.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.CascadeType;
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
import com.tjube.model.enums.TaskStateEvent;

@NamedQueries({
		@NamedQuery(name = Objective.QN.RETRIEVE_OBJECTIVE_WITH_UUID,
				query = "SELECT item from Objective item where item.uuid=:uuid"),
		@NamedQuery(name = Objective.QN.RETRIEVE_OBJECTIVE_BY_MOTHLY_TASK,
				query = "SELECT item from Objective item where item.monthlyTask=:monthlyTask"),
		@NamedQuery(name = Objective.QN.RETRIEVE_OBJECTIVE_BY_ACCOUNT,
				query = "SELECT item from Objective item where item.account=:account"),
		@NamedQuery(name = Objective.QN.RETRIEVE_MASTER_OBJECTIVE_BY_ACCOUNT,
		query = "SELECT item from Objective item where item.account=:account and item.masterObjective is null")})
@Entity
@Table(name = "OBJECTIVE")
public class Objective
	implements Serializable
{
	private static final long serialVersionUID = -9145257705716573234L;

	//---------------------------------------------------------------------------------------------------------------------

	public static class QN
	{
		public static final String RETRIEVE_OBJECTIVE_WITH_UUID = "Objective.retrieveObjectiveByUuid";
		public static final String RETRIEVE_OBJECTIVE_BY_MOTHLY_TASK = "Objective.retrieveObjectiveByMonthlyTask";
		public static final String RETRIEVE_OBJECTIVE_BY_ACCOUNT = "Objective.retrieveObjectiveByAccount";
		public static final String RETRIEVE_MASTER_OBJECTIVE_BY_ACCOUNT = "Objective.retrieveMasterObjectiveByAccount";

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
	private Account account = null;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private MonthlyTask monthlyTask = null;

	@Column(name = "name", nullable = false)
	private String name = null;
	
	@Column(name = "description", nullable = true)
	private String description = null;

	@Enumerated(EnumType.STRING)
	@Column(name = "state", nullable = false)
	private TaskStateEvent state = TaskStateEvent.TO_DO;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Objective masterObjective = null;
	
	@OneToMany(mappedBy = "masterObjective", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Objective> subObjectives = new ArrayList<>();

	//---------------------------------------------------------------------------------------------------------------------

	public Objective()
	{
		// Default Constructor
	}

	public Objective(Account account,String name, String description, Objective masterObjective)
	{
		this.uuid = UUID.randomUUID();

		this.account = account;
		this.name = name;
		this.description = description;
		this.masterObjective = masterObjective;
	}
	
	public Objective(Account account, MonthlyTask monthlyTask, String description)
	{
		this.uuid = UUID.randomUUID();

		this.account = account;
		this.monthlyTask = monthlyTask;

		this.description = description;
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

	public Account getAccount()
	{
		return account;
	}

	public void setAccount(Account account)
	{
		this.account = account;
	}
	
	//---------------------------------------------------------------------------------------------------------------------

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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

	public MonthlyTask getMonthlyTask()
	{
		return monthlyTask;
	}

	public void setMonthlyTask(MonthlyTask monthlyTask)
	{
		this.monthlyTask = monthlyTask;
	}
	//---------------------------------------------------------------------------------------------------------------------

	public TaskStateEvent getState()
	{
		return state;
	}

	public void setState(TaskStateEvent state)
	{
		this.state = state;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public Objective getMasterObjective() {
		return masterObjective;
	}
	
	public void setMasterObjective(Objective masterObjective) {
		this.masterObjective = masterObjective;
	}
	
	//---------------------------------------------------------------------------------------------------------------------

	public Collection<Objective> getSubObjectives() {
		return subObjectives;
	}
	
	public void setSubObjectives(Collection<Objective> subObjectives) {
		this.subObjectives = subObjectives;
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

		if (!(obj instanceof Objective))
			return false;

		return getId() == ((Objective) obj).getId();
	}

	//---------------------------------------------------------------------------------------------------------------------

}