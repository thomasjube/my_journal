package com.tjube.service;

import java.time.Month;
import java.util.Collection;
import java.util.UUID;

import com.tjube.model.Journal;
import com.tjube.model.Tracker;

public interface TrackerService
{
	//---------------------------------------------------------------------------------------------------------------------
	// TRACKER OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	Tracker createTracker(Journal journal,Month month,String name);

	void updateTracker(Tracker tracker, String month);

	void removeTracker(Tracker tracker);

	Tracker retrieveTracker(UUID uuid);

	Collection<Tracker> retrieveTrackers(Journal journal,Month month);

}
