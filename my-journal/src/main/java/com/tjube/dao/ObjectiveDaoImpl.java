package com.tjube.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tjube.model.Account;
import com.tjube.model.Journal;
import com.tjube.model.JournalEvent;
import com.tjube.model.Objective;

@Repository
public class ObjectiveDaoImpl
	implements ObjectiveDao
{
	@PersistenceContext(unitName = "JpaPersistenceUnit2")
	private EntityManager entityManager = null;

	@Override
	public Collection<Objective> retrieveMasterObjectives(Account account) {
		
		TypedQuery<Objective> query = entityManager.createNamedQuery(
				Objective.QN.RETRIEVE_MASTER_OBJECTIVE_BY_ACCOUNT, Objective.class);

		query.setParameter("account", account);
		
		return query.getResultList();
	}

	//---------------------------------------------------------------------------------------------------------------------
	
	@Override
	public Collection<Objective> retrieveObjectives(Account account) {
		
		TypedQuery<Objective> query = entityManager.createNamedQuery(
				Objective.QN.RETRIEVE_OBJECTIVE_BY_ACCOUNT, Objective.class);

		query.setParameter("account", account);
		
		return query.getResultList();
	}
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Objective createObjective(Account account,String name, String description, Objective masterObjective) {
		
		Objective objective = new Objective(account, name,description, masterObjective);

		entityManager.persist(objective);

		return objective;
	}
}
