package com.tjube.dao;

import java.util.Collection;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tjube.model.Account;
import com.tjube.model.CategoryTask;
import com.tjube.model.JPAUtils;

@Repository
public class CategoryDaoImpl
	implements CategoryDao
{
	@PersistenceContext(unitName = "JpaPersistenceUnit2")
	private EntityManager entityManager = null;

	@Override
	public CategoryTask createCategory(String description, Account account)
	{
		CategoryTask categoryTaskJPA = new CategoryTask(description, account);

		entityManager.persist(categoryTaskJPA);

		return categoryTaskJPA;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateCategory(CategoryTask categoryTask, String description)
	{
		if (!entityManager.contains(categoryTask))
			categoryTask = entityManager.merge(categoryTask);

		categoryTask.setDescription(description);

	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeCategory(CategoryTask categoryTask)
	{
		if (!entityManager.contains(categoryTask))
			categoryTask = entityManager.merge(categoryTask);

		entityManager.remove(categoryTask);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public CategoryTask retrieveCategoryTask(UUID uuid)
	{
		TypedQuery<CategoryTask> query = entityManager
				.createNamedQuery(CategoryTask.QN.RETRIEVE_CATEGORY_TASK_WITH_UUID, CategoryTask.class);

		query.setParameter("uuid", uuid);

		return JPAUtils.getSingleResult(query);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<CategoryTask> retrieveAllCategoryTasksByAccount(Account account)
	{
		TypedQuery<CategoryTask> query = entityManager
				.createNamedQuery(CategoryTask.QN.RETRIEVE_CATEGORY_TASKS_WITH_ACCOUNT, CategoryTask.class);

		query.setParameter("account", account);

		return query.getResultList();
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<CategoryTask> retrieveSystemCategoryTasks()
	{
		TypedQuery<CategoryTask> query = entityManager.createNamedQuery(CategoryTask.QN.RETRIEVE_CATEGORY_TASKS_SYSTEM,
				CategoryTask.class);

		return query.getResultList();
	}

	//---------------------------------------------------------------------------------------------------------------------
}
