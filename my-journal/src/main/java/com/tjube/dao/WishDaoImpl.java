package com.tjube.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.tjube.model.Account;
import com.tjube.model.CategoryTask;
import com.tjube.model.Wish;
import com.tjube.model.WishList;
import com.tjube.model.enums.TaskStateEvent;

@Repository
public class WishDaoImpl
	implements WishDao
{
	@PersistenceContext(unitName = "JpaPersistenceUnit2")
	private EntityManager entityManager = null;

	//---------------------------------------------------------------------------------------------------------------------
	// WISH LIST OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public WishList createWishList(Account account, String description)
	{
		// TODO Auto-generated method stub
		return null;
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
