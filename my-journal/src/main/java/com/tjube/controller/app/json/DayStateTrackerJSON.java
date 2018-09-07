package com.tjube.controller.app.json;

import java.util.UUID;

public class DayStateTrackerJSON
{
	private UUID trackerUuid;

	private UUID stateUuid;

	private Integer day;

	public DayStateTrackerJSON()
	{
		// TODO Auto-generated constructor stub
	}

	public UUID getTrackerUuid()
	{
		return trackerUuid;
	}

	public void setTrackerUuid(UUID trackerUuid)
	{
		this.trackerUuid = trackerUuid;
	}

	public UUID getStateUuid()
	{
		return stateUuid;
	}

	public void setStateUuid(UUID stateUuid)
	{
		this.stateUuid = stateUuid;
	}

	public Integer getDay()
	{
		return day;
	}

	public void setDay(Integer day)
	{
		this.day = day;
	}

}
