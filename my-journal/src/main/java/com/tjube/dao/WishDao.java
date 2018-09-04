package com.tjube.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;

import com.tjube.model.Account;
import com.tjube.model.Budget;
import com.tjube.model.CategoryTask;
import com.tjube.model.Wish;
import com.tjube.model.WishList;
import com.tjube.model.enums.TaskStateEvent;

public interface WishDao
{
	//---------------------------------------------------------------------------------------------------------------------
	// WISH LIST OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	WishList createWishList(Account account, String description);

	void updateWishList(WishList wishList, String description);

	void removeWishList(WishList wishList);

	void addWishToWishList(WishList wishList, Wish wish);

	void removeWishToWishList(WishList wishList, Wish wish);

	WishList retrieveWishList(UUID uuid);

	Collection<WishList> retrieveWishLists(Account account);

	//---------------------------------------------------------------------------------------------------------------------
	// WISH OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	Wish createWish(WishList wishList, CategoryTask categoryTask, String description, BigDecimal price,
			TaskStateEvent state);

	void updateWish(Wish wish, CategoryTask categoryTask, String descritpion, BigDecimal price);

	void updateWishState(Wish wish, TaskStateEvent state);

	void updateWish(Wish wish, Budget budget);

	void removeWish(Wish wish);

	Wish retrieveWish(UUID uuid);

	Collection<Wish> retrieveWishes(WishList wishList);

	//---------------------------------------------------------------------------------------------------------------------
}
