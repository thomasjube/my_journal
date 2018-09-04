package com.tjube.controller.app.form;

import java.time.Month;
import java.util.UUID;


import com.tjube.model.Tracker;

public class TrackerCreationForm
{
	private UUID trackerUuid;
	
	private Month month;
	
	private String name;

	private UUID journalUuid;

	public TrackerCreationForm()
	{
		// Default Constructor
	}
	
	public TrackerCreationForm(UUID journalUuid,Month month)
	{
		this.journalUuid = journalUuid;
		this.month = month;
	}

	public TrackerCreationForm(Tracker tracker)
	{
		this.trackerUuid = tracker.getUuid();
		this.name = tracker.getName();
		this.month = tracker.getMonth();
		this.journalUuid = tracker.getJournal().getUuid();
	}

	public UUID getTrackerUuid() {
		return trackerUuid;
	}

	public void setTrackerUuid(UUID trackerUuid) {
		this.trackerUuid = trackerUuid;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
