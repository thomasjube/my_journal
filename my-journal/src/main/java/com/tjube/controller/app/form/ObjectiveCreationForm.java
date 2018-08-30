package com.tjube.controller.app.form;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

public class ObjectiveCreationForm
{
	@NotBlank
	private String name;

	private String description;

	private UUID masterObjectiveUuid;

	public ObjectiveCreationForm()
	{
		// Default Constructor
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public UUID getMasterObjectiveUuid()
	{
		return masterObjectiveUuid;
	}

	public void setMasterObjectiveUuid(UUID masterObjectiveUUID)
	{
		this.masterObjectiveUuid = masterObjectiveUUID;
	}

}
