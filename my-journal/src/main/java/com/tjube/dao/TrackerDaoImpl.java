package com.tjube.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tjube.model.Account;
import com.tjube.model.JPAUtils;
import com.tjube.model.Journal;
import com.tjube.model.JournalEvent;
import com.tjube.model.Tracker;

@Repository
public class TrackerDaoImpl
	implements TrackerDao
{
	@PersistenceContext(unitName = "JpaPersistenceUnit2")
	private EntityManager entityManager = null;

	//---------------------------------------------------------------------------------------------------------------------
	// JOURNAL OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Tracker createTracker(Journal journal, Month month, String name) {
		
		Tracker trackerJPA = new Tracker(journal,month,name);

		entityManager.persist(trackerJPA);

		return trackerJPA;
	}
	
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateTracker(Tracker tracker, String name) {
		
		if (!entityManager.contains(tracker))
			tracker = entityManager.merge(tracker);

		tracker.setName(name);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeTracker(Tracker tracker) {
		
		if (!entityManager.contains(tracker))
			tracker = entityManager.merge(tracker);

		entityManager.remove(tracker);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Tracker retrieveTracker(UUID uuid) {
		
		TypedQuery<Tracker> query = entityManager.createNamedQuery(Tracker.QN.RETRIEVE_TRACKER_WITH_UUID,
				Tracker.class);

		query.setParameter("uuid", uuid);

		return JPAUtils.getSingleResult(query);
	}

	//---------------------------------------------------------------------------------------------------------------------
	
	@Override
	public Collection<Tracker> retrieveTrackers(Journal journal, Month month) {
		
		TypedQuery<Tracker> query = entityManager.createNamedQuery(Tracker.QN.RETRIEVE_TRACKER_WITH_JOURNAL,
				Tracker.class);

		query.setParameter("journal", journal);
		query.setParameter("month", month);

		return query.getResultList();
	}
	
	//---------------------------------------------------------------------------------------------------------------------



	//---------------------------------------------------------------------------------------------------------------------

}
