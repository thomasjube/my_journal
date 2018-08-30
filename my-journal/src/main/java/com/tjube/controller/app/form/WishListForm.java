package com.tjube.controller.app.form;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;

import com.tjube.model.WishList;

public class WishListForm
{
	private UUID uuid;

	@NotEmpty
	private String description;

	public WishListForm()
	{
		// Default Constructor
	}

	public WishListForm(WishList wishList)
	{
		uuid = wishList.getUuid();
		description = wishList.getDescription();
	}

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

}
