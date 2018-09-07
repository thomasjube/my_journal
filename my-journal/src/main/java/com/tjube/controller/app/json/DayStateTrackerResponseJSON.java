package com.tjube.controller.app.json;

import java.util.UUID;

public class DayStateTrackerResponseJSON
{

	private UUID stateUuid;

	private Integer day;

	private String color;

	public DayStateTrackerResponseJSON()
	{
		// TODO Auto-generated constructor stub
	}

	public DayStateTrackerResponseJSON(UUID stateUuid, Integer day, String color)
	{
		this.stateUuid = stateUuid;
		this.day = day;
		this.color = color;
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

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}
}
