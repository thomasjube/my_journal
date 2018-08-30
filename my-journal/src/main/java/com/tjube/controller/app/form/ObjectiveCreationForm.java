package com.tjube.controller.app.form;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.tjube.model.Objective;

public class ObjectiveCreationForm
{
	@NotBlank
	private String name;

	private String description;

	private UUID masterObjectiveUuid;

	private UUID objectiveUuid = null;

	public ObjectiveCreationForm()
	{
		// Default Constructor
	}

	public ObjectiveCreationForm(Objective objective)
	{
		this.name = objective.getName();
		this.description = objective.getDescription();
		this.masterObjectiveUuid = objective.getMasterObjective() != null ? objective.getMasterObjective().getUuid()
				: null;
		this.objectiveUuid = objective.getUuid();
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

	public UUID getObjectiveUuid()
	{
		return objectiveUuid;
	}

	public void setObjectiveUuid(UUID objectiveUuid)
	{
		this.objectiveUuid = objectiveUuid;
	}

}
