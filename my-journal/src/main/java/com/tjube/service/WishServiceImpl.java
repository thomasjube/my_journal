package com.tjube.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjube.dao.WishDao;
import com.tjube.model.Account;
import com.tjube.model.CategoryTask;
import com.tjube.model.DailyTask;
import com.tjube.model.Wish;
import com.tjube.model.WishList;
import com.tjube.model.enums.TaskStateEvent;

@Service
@Transactional
public class WishServiceImpl
	implements WishService
{
	@Autowired
	WishDao wishDao = null;

	//---------------------------------------------------------------------------------------------------------------------
	// WISH LIST OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public WishList createWishList(Account account, String description)
	{
		return wishDao.createWishList(account, description);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateWishList(WishList wishList, String description)
	{
		wishDao.updateWishList(wishList, description);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeWishList(WishList wishList)
	{
		for (Wish wish : wishList.getWishes())
		{
			wishDao.removeWishToWishList(wishList, wish);
			wishDao.removeWish(wish);
		}

		wishDao.removeWishList(wishList);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void addWishToWishList(WishList wishList, Wish wish)
	{
		wishDao.addWishToWishList(wishList, wish);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeWishToWishList(WishList wishList, Wish wish)
	{
		wishDao.removeWishToWishList(wishList, wish);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public WishList retrieveWishList(UUID uuid)
	{
		return wishDao.retrieveWishList(uuid);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<WishList> retrieveWishLists(Account account)
	{
		return wishDao.retrieveWishLists(account);
	}

	//---------------------------------------------------------------------------------------------------------------------
	// WISH OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Wish createWish(WishList wishList, CategoryTask categoryTask, String descritpion, BigDecimal price,
			TaskStateEvent state)
	{
		return wishDao.createWish(wishList, categoryTask, descritpion, price, state);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateWish(Wish wish, CategoryTask categoryTask, String descritpion, BigDecimal price)
	{
		wishDao.updateWish(wish, categoryTask, descritpion, price);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateWishState(Wish wish, TaskStateEvent state)
	{
		wishDao.updateWishState(wish, state);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeWish(Wish wish)
	{
		wishDao.removeWish(wish);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Wish retrieveWish(UUID uuid)
	{
		return wishDao.retrieveWish(uuid);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<Wish> retrieveWishes(WishList wishList)
	{
		return wishDao.retrieveWishes(wishList);
	}

	//---------------------------------------------------------------------------------------------------------------------
}
