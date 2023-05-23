package com.dao;

import java.util.List;

import com.entity.Category;

public interface CategoryDAO {
	public void createCategory(Category category);

	public void updateCategory(Category category);

	public void deleteCategory(int id);

	public List<Category> readAllCategories();
}
