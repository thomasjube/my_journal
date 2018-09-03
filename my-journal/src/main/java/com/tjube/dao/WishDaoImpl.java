package com.tjube.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tjube.model.Account;
import com.tjube.model.CategoryTask;
import com.tjube.model.JPAUtils;
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
		WishList result = new WishList(account, description);

		entityManager.persist(result);

		return result;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateWishList(WishList wishList, String description)
	{
		if (!entityManager.contains(wishList))
			wishList = entityManager.merge(wishList);

		wishList.setDescription(description);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeWishList(WishList wishList)
	{
		if (!entityManager.contains(wishList))
			wishList = entityManager.merge(wishList);

		entityManager.remove(wishList);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void addWishToWishList(WishList wishList, Wish wish)
	{
		if (!entityManager.contains(wishList))
			wishList = entityManager.merge(wishList);

		wishList.addWish(wish);

	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeWishToWishList(WishList wishList, Wish wish)
	{
		if (!entityManager.contains(wishList))
			wishList = entityManager.merge(wishList);

		wishList.removeWish(wish);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public WishList retrieveWishList(UUID uuid)
	{
		TypedQuery<WishList> query = entityManager.createNamedQuery(WishList.QN.RETRIEVE_WISH_LIST_WITH_UUID,
				WishList.class);

		query.setParameter("uuid", uuid);

		return JPAUtils.getSingleResult(query);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<WishList> retrieveWishLists(Account account)
	{
		TypedQuery<WishList> query = entityManager.createNamedQuery(WishList.QN.RETRIEVE_WISH_LISTS_BY_ACCOUNT,
				WishList.class);

		query.setParameter("account", account);

		return query.getResultList();
	}

	//---------------------------------------------------------------------------------------------------------------------
	// WISH OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Wish createWish(WishList wishList, CategoryTask categoryTask, String description, BigDecimal price,
			TaskStateEvent state)
	{
		Wish result = new Wish(wishList, description, price, categoryTask);

		entityManager.persist(result);

		wishList.addWish(result);

		return result;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateWish(Wish wish, CategoryTask categoryTask, String description, BigDecimal price)
	{
		if (!entityManager.contains(wish))
			wish = entityManager.merge(wish);

		wish.setCategory(categoryTask);
		wish.setDescription(description);
		wish.setPrice(price);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateWishState(Wish wish, TaskStateEvent state)
	{
		if (!entityManager.contains(wish))
			wish = entityManager.merge(wish);

		wish.setState(state);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeWish(Wish wish)
	{
		if (!entityManager.contains(wish))
			wish = entityManager.merge(wish);

		wish.getWishList().removeWish(wish);

		entityManager.remove(wish);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Wish retrieveWish(UUID uuid)
	{
		TypedQuery<Wish> query = entityManager.createNamedQuery(Wish.QN.RETRIEVE_WISH_WITH_UUID, Wish.class);

		query.setParameter("uuid", uuid);

		return JPAUtils.getSingleResult(query);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<Wish> retrieveWishes(WishList wishList)
	{
		TypedQuery<Wish> query = entityManager.createNamedQuery(Wish.QN.RETRIEVE_WISHES_BY_LIST, Wish.class);

		query.setParameter("wishList", wishList);

		return query.getResultList();
	}

	//---------------------------------------------------------------------------------------------------------------------
}
