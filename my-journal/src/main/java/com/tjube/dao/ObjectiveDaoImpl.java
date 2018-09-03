package com.tjube.dao;

import java.util.Collection;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tjube.model.Account;
import com.tjube.model.JPAUtils;
import com.tjube.model.Objective;
import com.tjube.model.enums.TaskStateEvent;

@Repository
public class ObjectiveDaoImpl
	implements ObjectiveDao
{
	@PersistenceContext(unitName = "JpaPersistenceUnit2")
	private EntityManager entityManager = null;

	@Override
	public Objective createObjective(Account account, String name, String description, Objective masterObjective)
	{

		Objective objective = new Objective(account, name, description, masterObjective);

		entityManager.persist(objective);

		return objective;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateObjective(Objective objective, String name, String description, Objective masterObjective)
	{
		
		if(objective != null)
		{
			if (!entityManager.contains(objective))
				objective = entityManager.merge(objective);
	
			objective.setName(name);
			objective.setDescription(description);
			objective.setMasterObjective(masterObjective);
		}
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateState(Objective objective, TaskStateEvent state)
	{
		if(objective != null)
		{
			if (!entityManager.contains(objective))
				objective = entityManager.merge(objective);
	
			objective.setState(state);
		}
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeObjective(Objective objective)
	{
		if(objective != null)
		{
			if (!entityManager.contains(objective))
				objective = entityManager.merge(objective);
		
			entityManager.remove(objective);
		}
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Objective retrieveObjective(UUID masterObjectiveUuid)
	{
		TypedQuery<Objective> query = entityManager.createNamedQuery(Objective.QN.RETRIEVE_OBJECTIVE_WITH_UUID,
				Objective.class);

		query.setParameter("uuid", masterObjectiveUuid);

		return JPAUtils.getSingleResult(query);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<Objective> retrieveMasterObjectives(Account account)
	{

		TypedQuery<Objective> query = entityManager.createNamedQuery(Objective.QN.RETRIEVE_MASTER_OBJECTIVE_BY_ACCOUNT,
				Objective.class);

		query.setParameter("account", account);

		return query.getResultList();
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<Objective> retrieveObjectives(Account account)
	{

		TypedQuery<Objective> query = entityManager.createNamedQuery(Objective.QN.RETRIEVE_OBJECTIVE_BY_ACCOUNT,
				Objective.class);

		query.setParameter("account", account);

		return query.getResultList();
	}

	//---------------------------------------------------------------------------------------------------------------------

}
