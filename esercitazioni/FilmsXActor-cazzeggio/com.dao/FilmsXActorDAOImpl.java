package com.dao;

import java.util.List;

import com.provider.ProviderManager;
import com.vo.FilmsXActorVO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.RollbackException;

public class FilmsXActorDAOImpl implements FilmsXActorDAO {

	private EntityManagerFactory emf;
	private EntityManager em;

	@Override
	public List<FilmsXActorVO> getFilmsXActor() {
		initRoutine();
		List<FilmsXActorVO> total = em.createNamedQuery("Film.FilmsXActor", FilmsXActorVO.class).getResultList();
		closeRoutine();
		return total;
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
