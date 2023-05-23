package com.dao;

import java.util.List;

import com.entity.Category;
import com.provider.ProviderManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.RollbackException;

public class CategoryDAOImpl implements CategoryDAO {

	private EntityManagerFactory emf;
	private EntityManager em;

	@Override
	public void createCategory(Category category) {
		initRoutine();
		em.persist(category);
		closeRoutine();
	}

	@Override
	public void updateCategory(Category category) {
		initRoutine();
		em.merge(category);
		closeRoutine();
	}

	@Override
	public void deleteCategory(int id) {
		initRoutine();
		Category findCategory = em.find(Category.class, id);
		em.remove(findCategory);
		closeRoutine();
	}

	@Override
	public List<Category> readAllCategories() {
		initRoutine();
		List<Category> category = em.createNamedQuery("Category.findAll", Category.class).getResultList();
		closeRoutine();
		return category;
	}

	private void initRoutine() {
		emf = ProviderManager.getEntityManagerFactory();
		em = ProviderManager.getEntityManager(emf);
		ProviderManager.beginTransaction(em);
	}

	private void closeRoutine() {
		try {
			ProviderManager.commitTransaction(em);
		} catch (RollbackException rbe) {
			System.err.println("Transazione fallita: eseguo il rollback.");
			rbe.printStackTrace();
			ProviderManager.rollbackTransaction(em);
		} finally {
			ProviderManager.closeTransaction(em);
			ProviderManager.closeEntityManagerFactory(emf);
		}
	}

}
