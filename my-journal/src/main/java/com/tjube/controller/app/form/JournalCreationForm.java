package com.tjube.controller.app.form;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.tjube.model.Journal;

public class JournalCreationForm
{
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate beginDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;

	private UUID journalUuid;

	public JournalCreationForm()
	{
		// Default Constructor
	}

	public JournalCreationForm(Journal journal)
	{
		this.beginDate = journal.getBeginDate();
		this.endDate = journal.getEndDate();
		this.journalUuid = journal.getUuid();
	}

	public LocalDate getBeginDate()
	{
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate)
	{
		this.beginDate = beginDate;
	}

	public LocalDate getEndDate()
	{
		return endDate;
	}

	public void setEndDate(LocalDate endDate)
	{
		this.endDate = endDate;
	}

	public UUID getJournalUuid()
	{
		return journalUuid;
	}

	public void setJournalUuid(UUID journalUuid)
	{
		this.journalUuid = journalUuid;
	}

}
