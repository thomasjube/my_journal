package com.tjube.service;

import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjube.dao.CategoryDao;
import com.tjube.model.Account;
import com.tjube.model.CategoryTask;

@Service
@Transactional
public class CategoryServiceImpl
	implements CategoryService
{
	@Autowired
	CategoryDao categoryDao = null;

	@Override
	public CategoryTask createCategory(String description, Account account)
	{
		return categoryDao.createCategory(description, account);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateCategory(CategoryTask categoryTask, String description)
	{
		categoryDao.updateCategory(categoryTask, description);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeCategory(CategoryTask categoryTask)
	{
		categoryDao.removeCategory(categoryTask);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public CategoryTask retrieveCategoryTask(UUID uuid)
	{
		return categoryDao.retrieveCategoryTask(uuid);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<CategoryTask> retrieveAllCategoryTasksByAccount(Account account)
	{
		return categoryDao.retrieveAllCategoryTasksByAccount(account);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<CategoryTask> retrieveSystemCategoryTasks()
	{
		return categoryDao.retrieveSystemCategoryTasks();
	}

	//---------------------------------------------------------------------------------------------------------------------
}
