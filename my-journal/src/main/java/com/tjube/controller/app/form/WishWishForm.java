package com.tjube.controller.app.form;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.tjube.model.Wish;
import com.tjube.model.WishList;

public class WishWishForm
{
	private UUID uuid;

	@NotNull
	private UUID wishListUuid;

	@NotNull
	private UUID categoryUuid;

	@NotEmpty
	private String description;

	@NotNull
	private BigDecimal price = null;

	public WishWishForm()
	{
		// Default Constructor
	}

	public WishWishForm(WishList wishList)
	{
		wishListUuid = wishList.getUuid();
	}

	public WishWishForm(Wish wish)
	{
		uuid = wish.getUuid();
		wishListUuid = wish.getWishList().getUuid();
		categoryUuid = wish.getCategory().getUuid();
		description = wish.getDescription();
		price = wish.getPrice();
	}

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}

	public UUID getWishListUuid()
	{
		return wishListUuid;
	}

	public void setWishListUuid(UUID wishListUuid)
	{
		this.wishListUuid = wishListUuid;
	}

	public UUID getCategoryUuid()
	{
		return categoryUuid;
	}

	public void setCategoryUuid(UUID categoryUuid)
	{
		this.categoryUuid = categoryUuid;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

}
