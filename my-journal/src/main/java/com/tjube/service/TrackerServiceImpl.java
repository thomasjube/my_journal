package com.tjube.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjube.dao.JournalDao;
import com.tjube.dao.TrackerDao;
import com.tjube.model.Account;
import com.tjube.model.Journal;
import com.tjube.model.JournalEvent;
import com.tjube.model.Tracker;

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
	public Tracker createTracker(Journal journal, Month month, String name) {
		return trackerDao.createTracker(journal, month, name);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateTracker(Tracker tracker, String month) {
		trackerDao.updateTracker(tracker, month);
	}
	
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeTracker(Tracker tracker) {
		trackerDao.removeTracker(tracker);
	}
	
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Tracker retrieveTracker(UUID uuid) {
		return trackerDao.retrieveTracker(uuid);
	}
	
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<Tracker> retrieveTrackers(Journal journal, Month month) {
		return trackerDao.retrieveTrackers(journal, month);
	}

	//---------------------------------------------------------------------------------------------------------------------

}
