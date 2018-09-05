package com.tjube.dao;

import java.time.Month;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tjube.model.JPAUtils;
import com.tjube.model.Journal;
import com.tjube.model.Tracker;
import com.tjube.model.TrackerState;

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
	public Tracker createTracker(Journal journal, Month month, String name)
	{

		Tracker trackerJPA = new Tracker(journal, month, name);

		entityManager.persist(trackerJPA);

		return trackerJPA;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateTracker(Tracker tracker, String name)
	{

		if (!entityManager.contains(tracker))
			tracker = entityManager.merge(tracker);

		tracker.setName(name);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeTracker(Tracker tracker)
	{

		if (!entityManager.contains(tracker))
			tracker = entityManager.merge(tracker);

		entityManager.remove(tracker);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Tracker retrieveTracker(UUID uuid)
	{

		TypedQuery<Tracker> query = entityManager.createNamedQuery(Tracker.QN.RETRIEVE_TRACKER_WITH_UUID,
				Tracker.class);

		query.setParameter("uuid", uuid);

		return JPAUtils.getSingleResult(query);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<Tracker> retrieveTrackers(Journal journal, Month month)
	{

		TypedQuery<Tracker> query = entityManager.createNamedQuery(Tracker.QN.RETRIEVE_TRACKER_WITH_JOURNAL,
				Tracker.class);

		query.setParameter("journal", journal);
		query.setParameter("month", month);

		return query.getResultList();
	}

	//---------------------------------------------------------------------------------------------------------------------
	// TRACKER STATE OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public TrackerState createTrackerState(Tracker tracker, String name, String color)
	{
		TrackerState trackerStateJPA = new TrackerState(tracker, name, color);

		entityManager.persist(trackerStateJPA);

		return trackerStateJPA;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateTrackerState(TrackerState trackerState, String name, String color)
	{
		if (!entityManager.contains(trackerState))
			trackerState = entityManager.merge(trackerState);

		trackerState.setName(name);
		trackerState.setColor(color);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeTrackerState(TrackerState trackerState)
	{
		if (!entityManager.contains(trackerState))
			trackerState = entityManager.merge(trackerState);

		entityManager.remove(trackerState);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public TrackerState retrieveTrackerState(UUID uuid)
	{
		TypedQuery<TrackerState> query = entityManager
				.createNamedQuery(TrackerState.QN.RETRIEVE_TRACKER_STATE_WITH_UUID, TrackerState.class);

		query.setParameter("uuid", uuid);

		return JPAUtils.getSingleResult(query);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<TrackerState> retrieveTrackerStates(Tracker tracker)
	{
		TypedQuery<TrackerState> query = entityManager
				.createNamedQuery(TrackerState.QN.RETRIEVE_TRACKER_STATE_WITH_TRACKER, TrackerState.class);

		query.setParameter("tracker", tracker);

		return query.getResultList();
	}
}
