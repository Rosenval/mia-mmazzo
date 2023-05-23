package com.main;

import com.dao.CategoryDAO;
import com.dao.CategoryDAOImpl;
import com.entity.Category;

public class Main {

	public static void main(String[] args) {
		CategoryDAO cd = new CategoryDAOImpl();

		// Read
		cd.readAllCategories().forEach(System.out::println);
		System.out.println();

		// Create
		Category newCategory = new Category("favoloso");
		Category newCategory2 = new Category("favoloso2");
		cd.createCategory(newCategory);
		cd.createCategory(newCategory2);
		System.out.println();

		// Update
		Category updateCategory = new Category(18, "modificato");
		cd.updateCategory(updateCategory);
		System.out.println();

		// Delete
		System.out.println("Remove:");
		cd.deleteCategory(17);
	}

}
