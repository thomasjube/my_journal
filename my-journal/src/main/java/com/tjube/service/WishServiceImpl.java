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
		return null;
		// return null
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateWishList(WishList wishList, String description)
	{
		// TODO Auto-generated method stub

	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeWishList(WishList wishList)
	{
		// TODO Auto-generated method stub

	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void addWishToWishList(WishList wishList, Wish wish)
	{
		// TODO Auto-generated method stub

	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeWishToWishList(WishList wishList, Wish wish)
	{
		// TODO Auto-generated method stub

	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public WishList retrieveWishList(UUID uuid)
	{
		// TODO Auto-generated method stub
		return null;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<WishList> retrieveWishLists(Account account)
	{
		// TODO Auto-generated method stub
		return null;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// WISH OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Wish createWish(WishList wishList, CategoryTask categoryTask, String descritpion, BigDecimal price,
			TaskStateEvent state)
	{
		// TODO Auto-generated method stub
		return null;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateWish(Wish wish, CategoryTask categoryTask, String descritpion, BigDecimal price)
	{
		// TODO Auto-generated method stub

	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateWishState(Wish wish, TaskStateEvent state)
	{
		// TODO Auto-generated method stub

	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeWish(Wish wish)
	{
		// TODO Auto-generated method stub

	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Wish retrieveWish(UUID uuid)
	{
		// TODO Auto-generated method stub
		return null;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<Wish> retrieveWishes(WishList wishList)
	{
		// TODO Auto-generated method stub
		return null;
	}

	//---------------------------------------------------------------------------------------------------------------------
}
