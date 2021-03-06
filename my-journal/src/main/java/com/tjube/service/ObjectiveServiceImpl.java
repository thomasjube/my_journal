package com.tjube.service;

import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjube.dao.ObjectiveDao;
import com.tjube.model.Account;
import com.tjube.model.Objective;
import com.tjube.model.enums.TaskStateEvent;

@Service
@Transactional
public class ObjectiveServiceImpl
	implements ObjectiveService
{
	@Autowired
	ObjectiveDao objectiveDao = null;

	@Override
	public Objective createObjective(Account account, String name, String description, Objective masterObjective)
	{
		return objectiveDao.createObjective(account, name, description, masterObjective);
	}

	@Override
	public void updateObjective(Objective objective, String name, String description, Objective masterObjective)
	{
		objectiveDao.updateObjective(objective, name, description, masterObjective);
	}

	@Override
	public void updateState(Objective objective, TaskStateEvent state)
	{
		objectiveDao.updateState(objective, state);
	}

	@Override
	public void removeObjective(Objective objective)
	{
		objectiveDao.removeObjective(objective);
	}

	@Override
	public Objective retrieveObjective(UUID masterObjectiveUuid)
	{
		return objectiveDao.retrieveObjective(masterObjectiveUuid);
	}

	@Override
	public Collection<Objective> retrieveMasterObjectives(Account account)
	{
		return objectiveDao.retrieveMasterObjectives(account);
	}

	@Override
	public Collection<Objective> retrieveObjectives(Account account, Collection<TaskStateEvent> states)
	{
		return objectiveDao.retrieveObjectives(account, states);
	}

}
