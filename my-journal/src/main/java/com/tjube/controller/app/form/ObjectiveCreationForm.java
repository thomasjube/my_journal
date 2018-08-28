package com.tjube.controller.app.form;

import javax.validation.constraints.NotBlank;
import com.tjube.model.Objective;

public class ObjectiveCreationForm
{
	@NotBlank
	private String name;
	
	private String description;
	
	private Objective masterObjective;

	public ObjectiveCreationForm()
	{
		// Default Constructor
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Objective getMasterObjective() {
		return masterObjective;
	}

	public void setMasterObjective(Objective masterObjective) {
		this.masterObjective = masterObjective;
	}

	
}
