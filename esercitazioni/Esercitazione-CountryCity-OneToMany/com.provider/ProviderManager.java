package com.provider;

import java.util.Properties;

import org.hibernate.jpa.boot.internal.PersistenceXmlParser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ProviderManager {

	private static final String persistenceUnitName = PersistenceXmlParser.locatePersistenceUnits(new Properties())
			.get(0).getName();

	public static EntityManagerFactory getEntityManagerFactory() {
		return Persistence.createEntityManagerFactory(persistenceUnitName);
	}

	public static EntityManager getEntityManager(EntityManagerFactory emf) {
		return emf.createEntityManager();
	}

	public static void beginTransaction(EntityManager em) {
		em.getTransaction().begin();
	}

	public static void commitTransaction(EntityManager em) {
		em.getTransaction().commit();
	}

	public static void rollbackTransaction(EntityManager em) {
		em.getTransaction().rollback();
	}

	public static void closeTransaction(EntityManager em) {
		em.close();
	}

	public static void closeEntityManagerFactory(EntityManagerFactory emf) {
		emf.close();
	}

}
