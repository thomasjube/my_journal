package com.tjube.controller.app.form;

import java.time.Month;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.tjube.model.Journal;
import com.tjube.model.MonthlyTask;
import com.tjube.model.enums.TaskUnit;

public class MonthlyTaskAddForm
{
	private UUID uuid;

	@NotNull
	private UUID journalUuid;

	@NotNull
	private Month month;

	@NotEmpty
	private String description;
	
	private boolean pro;
	
	private UUID categoryTaskUuid = null;
	
	private UUID objectiveUuid = null;
	
	private UUID wishUuid = null;
	

	public MonthlyTaskAddForm()
	{
		// Default Constructor
	}

	public MonthlyTaskAddForm(Journal journal,Month month)
	{
		this.journalUuid = journal.getUuid();
		this.month = month;
	}

	public MonthlyTaskAddForm(MonthlyTask monthlyTask)
	{
		uuid = monthlyTask.getUuid();
		description = monthlyTask.getDescription();
		pro = monthlyTask.isProfessional();
		objectiveUuid = monthlyTask.getObjective() != null ? monthlyTask.getObjective().getUuid() : null;
		wishUuid = monthlyTask.getWish() != null ? monthlyTask.getWish().getUuid() : null;
		journalUuid = monthlyTask.getJournal().getUuid();
		month = monthlyTask.getMonth();
	}

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}

	public UUID getJournalUuid() {
		return journalUuid;
	}

	public void setJournalUuid(UUID journalUuid) {
		this.journalUuid = journalUuid;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPro() {
		return pro;
	}

	public void setPro(boolean pro) {
		this.pro = pro;
	}

	public UUID getObjectiveUuid() {
		return objectiveUuid;
	}
	
	public void setObjectiveUuid(UUID objectiveUuid) {
		this.objectiveUuid = objectiveUuid;
	}
	
	public UUID getWishUuid() {
		return wishUuid;
	}
	
	public void setWishUuid(UUID wishUuid) {
		this.wishUuid = wishUuid;
	}

	public UUID getCategoryTaskUuid() {
		return categoryTaskUuid;
	}
	
	public void setCategoryTaskUuid(UUID categoryTaskUuid) {
		this.categoryTaskUuid = categoryTaskUuid;
	}

}
