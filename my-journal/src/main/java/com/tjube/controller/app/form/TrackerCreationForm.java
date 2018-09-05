package com.tjube.controller.app.form;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.tjube.model.Tracker;
import com.tjube.model.TrackerState;

public class TrackerCreationForm
{
	private UUID trackerUuid;

	private Month month;

	private String name;

	private UUID journalUuid;

	private List<String> stateName = new ArrayList<>();
	private List<String> stateColor = new ArrayList<>();

	public TrackerCreationForm()
	{
		// Default Constructor
	}

	public TrackerCreationForm(UUID journalUuid, Month month)
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

		for (TrackerState state : tracker.getStates())
		{
			this.stateName.add(state.getName());
			this.stateColor.add(state.getColor());
		}
	}

	public UUID getTrackerUuid()
	{
		return trackerUuid;
	}

	public void setTrackerUuid(UUID trackerUuid)
	{
		this.trackerUuid = trackerUuid;
	}

	public Month getMonth()
	{
		return month;
	}

	public void setMonth(Month month)
	{
		this.month = month;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
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

	public List<String> getStateColor()
	{
		return stateColor;
	}

	public void setStateColor(List<String> stateColor)
	{
		this.stateColor = stateColor;
	}

	public List<String> getStateName()
	{
		return stateName;
	}

	public void setStateName(List<String> stateName)
	{
		this.stateName = stateName;
	}

}
