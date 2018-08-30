package com.tjube.service;

import java.util.Collection;
import java.util.UUID;

import com.tjube.model.Account;
import com.tjube.model.Objective;
import com.tjube.model.enums.TaskStateEvent;

public interface ObjectiveService
{

	Objective createObjective(Account account, String name, String description, Objective masterObjective);

	void updateObjective(Objective objective, String name, String description, Objective masterObjective);

	void updateState(Objective objective, TaskStateEvent state);

	void removeObjective(Objective objective);

	Objective retrieveObjective(UUID masterObjectiveUuid);

	Collection<Objective> retrieveObjectives(Account account);

	Collection<Objective> retrieveMasterObjectives(Account account);

}
