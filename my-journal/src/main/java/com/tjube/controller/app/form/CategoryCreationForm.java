package com.tjube.controller.app.form;

import java.util.UUID;

import com.tjube.model.CategoryTask;

public class CategoryCreationForm
{

	private String description;

	private UUID categoryUuid;

	public CategoryCreationForm()
	{
		// Default Constructor
	}

	public CategoryCreationForm(CategoryTask category)
	{
		this.description = category.getDescription();
		this.categoryUuid = category.getUuid();
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public UUID getCategoryUuid()
	{
		return categoryUuid;
	}

	public void setCategoryUuid(UUID categoryUuid)
	{
		this.categoryUuid = categoryUuid;
	}

}
