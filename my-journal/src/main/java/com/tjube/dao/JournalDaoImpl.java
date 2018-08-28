package com.tjube.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

@Repository
public class JournalDaoImpl
	implements JournalDao
{
	@PersistenceContext(unitName = "JpaPersistenceUnit2")
	private EntityManager entityManager = null;

	//---------------------------------------------------------------------------------------------------------------------
	// JOURNAL OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Journal createJournal(Account account, LocalDate beginDate, LocalDate endDate)
	{
		Journal journalJPA = new Journal(account, beginDate, endDate);

		entityManager.persist(journalJPA);

		return journalJPA;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateJournal(Journal journal, LocalDate beginDate, LocalDate endDate)
	{
		if (!entityManager.contains(journal))
			journal = entityManager.merge(journal);

		journal.setBeginDate(beginDate);
		journal.setEndDate(endDate);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeJournal(Journal journal)
	{
		if (!entityManager.contains(journal))
			journal = entityManager.merge(journal);

		entityManager.remove(journal);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Journal retrieveJournal(UUID uuid)
	{
		TypedQuery<Journal> query = entityManager.createNamedQuery(Journal.QN.RETRIEVE_JOURNAL_WITH_UUID,
				Journal.class);

		query.setParameter("uuid", uuid);

		return JPAUtils.getSingleResult(query);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Journal retrieveCurrentJournal(Account account)
	{
		TypedQuery<Journal> query = entityManager.createNamedQuery(Journal.QN.RETRIEVE_JOURNAL_WITH_ACCOUNT_AND_DATE,
				Journal.class);

		query.setParameter("account", account);
		query.setParameter("date", LocalDate.now());

		return JPAUtils.getSingleResult(query);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Journal retrieveJournal(Account account, LocalDate date)
	{
		TypedQuery<Journal> query = entityManager.createNamedQuery(Journal.QN.RETRIEVE_JOURNAL_WITH_ACCOUNT_AND_DATE,
				Journal.class);

		query.setParameter("account", account);
		query.setParameter("date", date);

		return JPAUtils.getSingleResult(query);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<Journal> retrieveJournals(Account account)
	{
		TypedQuery<Journal> query = entityManager.createNamedQuery(Journal.QN.RETRIEVE_JOURNAL_WITH_ACCOUNT,
				Journal.class);

		query.setParameter("account", account);

		return query.getResultList();
	}

	//---------------------------------------------------------------------------------------------------------------------
	// JOURNAL_EVENT OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public JournalEvent createJournalEvent(Journal journal, LocalDateTime dateTime, String description, String place,
			String comments, boolean isAnnually)
	{
		JournalEvent journalEventJPA = new JournalEvent(journal, dateTime, description, place, comments, isAnnually);

		entityManager.persist(journalEventJPA);

		return journalEventJPA;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateJournalEvent(JournalEvent journalEvent, LocalDateTime dateTime, String description, String place,
			String comments, boolean isAnnually)
	{
		if (!entityManager.contains(journalEvent))
			journalEvent = entityManager.merge(journalEvent);

		journalEvent.setDateTime(dateTime);
		journalEvent.setDescription(description);
		journalEvent.setPlace(place);
		journalEvent.setComments(comments);
		journalEvent.setAnnually(isAnnually);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeJournalEvent(JournalEvent journalEvent)
	{
		if (!entityManager.contains(journalEvent))
			journalEvent = entityManager.merge(journalEvent);

		entityManager.remove(journalEvent);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public JournalEvent retrieveJournalEvent(UUID uuid)
	{
		TypedQuery<JournalEvent> query = entityManager
				.createNamedQuery(JournalEvent.QN.RETRIEVE_JOURNAL_EVENT_WITH_UUID, JournalEvent.class);

		query.setParameter("uuid", uuid);

		return JPAUtils.getSingleResult(query);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<JournalEvent> retrieveJournalEventsByDate(Account account, LocalDateTime dateTime)
	{
		TypedQuery<JournalEvent> query = entityManager.createNamedQuery(
				JournalEvent.QN.RETRIEVE_JOURNAL_EVENTS_WITH_ACCOUNT_AND_DATETIME, JournalEvent.class);

		query.setParameter("account", account);
		query.setParameter("dateTimeMin", LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MIDNIGHT));
		query.setParameter("dateTimeMax", LocalDateTime.of(dateTime.toLocalDate().plusDays(1), LocalTime.MIDNIGHT));

		return query.getResultList();
	}
	
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<JournalEvent> retrieveJournalEventsByDate(Journal journal, LocalDateTime dateTime)
	{
		TypedQuery<JournalEvent> query = entityManager.createNamedQuery(
				JournalEvent.QN.RETRIEVE_JOURNAL_EVENTS_WITH_JOURNAL_AND_DATETIME, JournalEvent.class);

		query.setParameter("journal", journal);
		query.setParameter("date", dateTime);
		
		return query.getResultList();
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<JournalEvent> retrieveAllJournalEvents(Journal journal)
	{
		TypedQuery<JournalEvent> query = entityManager
				.createNamedQuery(JournalEvent.QN.RETRIEVE_JOURNAL_EVENTS_WITH_JOURNAL, JournalEvent.class);

		query.setParameter("journal", journal);

		return query.getResultList();
	}

	//---------------------------------------------------------------------------------------------------------------------

}
