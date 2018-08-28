package com.tjube.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjube.dao.JournalDao;
import com.tjube.model.Account;
import com.tjube.model.Journal;
import com.tjube.model.JournalEvent;

@Service
@Transactional
public class JournalServiceImpl
	implements JournalService
{

	@Autowired
	JournalDao journalDao = null;

	//---------------------------------------------------------------------------------------------------------------------
	// JOURNAL OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Journal createJournal(Account account, LocalDate beginDate, LocalDate endDate)
	{
		return journalDao.createJournal(account, beginDate, endDate);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateJournal(Journal journal, LocalDate beginDate, LocalDate endDate)
	{
		journalDao.updateJournal(journal, beginDate, endDate);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeJournal(Journal journal)
	{
		journalDao.removeJournal(journal);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Journal retrieveJournal(UUID uuid)
	{
		return journalDao.retrieveJournal(uuid);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Journal retrieveCurrentJournal(Account account)
	{
		return journalDao.retrieveCurrentJournal(account);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Journal retrieveJournal(Account account, LocalDate date)
	{
		return journalDao.retrieveJournal(account, date);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<Journal> retrieveJournals(Account account)
	{
		return journalDao.retrieveJournals(account);
	}

	//---------------------------------------------------------------------------------------------------------------------
	// JOURNAL_EVENT OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public JournalEvent createJournalEvent(Journal journal, LocalDateTime dateTime, String description, String place,
			String comments, boolean isAnnually)
	{
		return journalDao.createJournalEvent(journal, dateTime, description, place, comments, isAnnually);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateJournalEvent(JournalEvent journalEvent, LocalDateTime dateTime, String description, String place,
			String comments, boolean isAnnually)
	{
		journalDao.updateJournalEvent(journalEvent, dateTime, description, place, comments, isAnnually);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void removeJournalEvent(JournalEvent journalEvent)
	{
		journalDao.removeJournalEvent(journalEvent);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public JournalEvent retrieveJournalEvent(UUID uuid)
	{
		return journalDao.retrieveJournalEvent(uuid);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<JournalEvent> retrieveJournalEventsByDate(Account account, LocalDateTime dateTime)
	{
		return journalDao.retrieveJournalEventsByDate(account, dateTime);
	}
	
	//---------------------------------------------------------------------------------------------------------------------

		@Override
		public Collection<JournalEvent> retrieveJournalEventsByDate(Journal journal, LocalDateTime dateTime)
		{
			return journalDao.retrieveJournalEventsByDate(journal, dateTime);
		}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<JournalEvent> retrieveAllJournalEvents(Journal journal)
	{
		return journalDao.retrieveAllJournalEvents(journal);
	}

	//---------------------------------------------------------------------------------------------------------------------

}
