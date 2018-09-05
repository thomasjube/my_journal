package com.tjube.service;

import java.time.Month;
import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjube.dao.TrackerDao;
import com.tjube.model.Journal;
import com.tjube.model.Tracker;
import com.tjube.model.TrackerState;

@Service
@Transactional
public class TrackerServiceImpl
	implements TrackerService
{

	@Autowired
	TrackerDao trackerDao = null;

	//---------------------------------------------------------------------------------------------------------------------
	// TRACKER OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Tracker createTracker(Journal journal, Month month, String name)
	{
		return trackerDao.createTracker(journal, month, name);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateTracker(Tracker tracker, String month)
	{
		trackerDao.updateTracker(tracker, month);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeTracker(Tracker tracker)
	{
		trackerDao.removeTracker(tracker);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Tracker retrieveTracker(UUID uuid)
	{
		return trackerDao.retrieveTracker(uuid);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<Tracker> retrieveTrackers(Journal journal, Month month)
	{
		return trackerDao.retrieveTrackers(journal, month);
	}

	//---------------------------------------------------------------------------------------------------------------------
	// TRACKER STATE OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public TrackerState createTrackerState(Tracker tracker, String name, String color)
	{
		return trackerDao.createTrackerState(tracker, name, color);
	}

	@Override
	public void updateTrackerState(TrackerState trackerState, String name, String color)
	{
		trackerDao.updateTrackerState(trackerState, name, color);
	}

	@Override
	public void removeTrackerState(TrackerState trackerState)
	{
		trackerDao.removeTrackerState(trackerState);
	}

	@Override
	public TrackerState retrieveTrackerState(UUID uuid)
	{
		return trackerDao.retrieveTrackerState(uuid);
	}

	@Override
	public Collection<TrackerState> retrieveTrackerStates(Tracker tracker)
	{
		return trackerDao.retrieveTrackerStates(tracker);
	}

	//---------------------------------------------------------------------------------------------------------------------

}
