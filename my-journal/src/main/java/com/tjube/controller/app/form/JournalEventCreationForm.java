package com.tjube.controller.app.form;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.tjube.model.JournalEvent;

public class JournalEventCreationForm
{
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime time;

	private UUID journalUuid;

	private UUID journalEventUuid;

	private String description;

	private String place;

	private String comments;

	private boolean annually;

	public JournalEventCreationForm()
	{
		// Default Constructor
	}

	public JournalEventCreationForm(UUID journalUuid)
	{
		this.journalUuid = journalUuid;
	}

	public JournalEventCreationForm(JournalEvent journalEvent)
	{
		this.journalUuid = journalEvent.getJournal().getUuid();
		this.journalEventUuid = journalEvent.getUuid();
		this.place = journalEvent.getPlace();
		this.description = journalEvent.getDescription();
		this.comments = journalEvent.getComments();
		this.date = journalEvent.getDate();
		this.time = journalEvent.getTime();
		this.annually = journalEvent.isAnnually();
	}

	public LocalDate getDate()
	{
		return date;
	}

	public void setDate(LocalDate date)
	{
		this.date = date;
	}

	public LocalTime getTime()
	{
		return time;
	}

	public void setTime(LocalTime time)
	{
		this.time = time;
	}

	public UUID getJournalEventUuid()
	{
		return journalEventUuid;
	}

	public void setJournalEventUuid(UUID journalEventUuid)
	{
		this.journalEventUuid = journalEventUuid;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getPlace()
	{
		return place;
	}

	public void setPlace(String place)
	{
		this.place = place;
	}

	public String getComments()
	{
		return comments;
	}

	public void setComments(String comments)
	{
		this.comments = comments;
	}

	public boolean isAnnually()
	{
		return annually;
	}

	public void setAnnually(boolean annually)
	{
		this.annually = annually;
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
