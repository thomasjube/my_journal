package com.tjube.service;

import java.util.Collection;
import java.util.UUID;

import com.tjube.model.Account;
import com.tjube.model.Objective;

public interface ObjectiveService
{

	Objective createObjective(Account account, String name, String description, Objective masterObjective);

	Objective retrieveObjective(UUID masterObjectiveUuid);

	Collection<Objective> retrieveObjectives(Account account);

	Collection<Objective> retrieveMasterObjectives(Account account);
}
