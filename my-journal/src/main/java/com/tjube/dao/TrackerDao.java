package com.tjube.dao;

import java.time.Month;
import java.util.Collection;
import java.util.UUID;

import com.tjube.model.Journal;
import com.tjube.model.Tracker;

public interface TrackerDao
{
	//---------------------------------------------------------------------------------------------------------------------
	// TRACKER OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	Tracker createTracker(Journal journal,Month month,String name);

	void updateTracker(Tracker Tracker, String month);

	void removeTracker(Tracker Tracker);

	Tracker retrieveTracker(UUID uuid);

	Collection<Tracker> retrieveTrackers(Journal journal,Month month);

}
