package com.tjube.controller.app.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class JournalCreationForm
{
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate beginDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;

	public JournalCreationForm()
	{
		// Default Constructor
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

}
