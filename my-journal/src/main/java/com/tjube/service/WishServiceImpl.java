package com.tjube.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjube.dao.WishDao;
import com.tjube.model.Account;
import com.tjube.model.Budget;
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

	@Autowired
	BudgetService budgetService = null;

	@Autowired
	JournalService journalService = null;

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
	public void updateWish(Wish wish, Budget budget)
	{
		wishDao.updateWish(wish, budget);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateWishState(Wish wish, TaskStateEvent state, boolean isFree)
	{
		Budget budget = wish.getBudget();

		if(!isFree)
		{
			if (budget == null)
				budget = budgetService.retrieveBudget(
						journalService.retrieveCurrentJournal(wish.getWishList().getAccount()), LocalDate.now().getMonth(),
						wish.getCategory());

			if (wish.getPrice() != null && budget != null)
			{
				if (wish.getState() != TaskStateEvent.DONE && state == TaskStateEvent.DONE)
				{
					budget.addWish(wish);
					wishDao.updateWish(wish, budget);
				}
				else if (wish.getState() == TaskStateEvent.DONE && state != TaskStateEvent.DONE)
				{
					budget.removeWish(wish);
					wishDao.updateWish(wish, null);
				}
			}
		}
		else
		{
			wishDao.updateWish(wish, null);
		}
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
