package com.tjube.dao;

import java.util.Collection;
import java.util.UUID;

import com.tjube.model.Account;
import com.tjube.model.CategoryTask;

public interface CategoryDao
{
	CategoryTask createCategory(String description, Account account,boolean tracked);

	void updateCategory(CategoryTask categoryTask, String description);

	void removeCategory(CategoryTask categoryTask);

	CategoryTask retrieveCategoryTask(UUID uuid);

	Collection<CategoryTask> retrieveAllCategoryTasksByAccount(Account account);

	Collection<CategoryTask> retrieveSystemCategoryTasks();
}
