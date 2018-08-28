package com.tjube.service;

import java.util.Collection;

import com.tjube.model.Account;
import com.tjube.model.Objective;

public interface ObjectiveService
{

	Collection<Objective> retrieveObjectives(Account account);

	Collection<Objective> retrieveMasterObjectives(Account account);

	Objective createObjective(Account account, String name,String description, Objective masterObjective);
}
