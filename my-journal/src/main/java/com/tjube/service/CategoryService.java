package com.tjube.service;

import java.util.Collection;
import java.util.UUID;

import com.tjube.model.Account;
import com.tjube.model.CategoryTask;

public interface CategoryService
{

	CategoryTask createCategory(String description, Account account);

	void updateCategory(CategoryTask categoryTask, String description);

	void removeCategory(CategoryTask categoryTask);

	CategoryTask retrieveCategoryTask(UUID uuid);

	Collection<CategoryTask> retrieveAllCategoryTasksByAccount(Account account);

	Collection<CategoryTask> retrieveSystemCategoryTasks();

}
