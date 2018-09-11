package com.tjube.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
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
		Journal journal = journalDao.createJournal(account, beginDate, endDate);

		Integer year = null;
		if ((account.getBirthDate().withYear(beginDate.getYear()).isAfter(beginDate)
				|| account.getBirthDate().withYear(beginDate.getYear()).isEqual(beginDate))
				&& account.getBirthDate().withYear(beginDate.getYear())
						.isBefore(LocalDate.of(beginDate.getYear() + 1, 1, 1)))
			year = beginDate.getYear();
		else if ((account.getBirthDate().withYear(endDate.getYear()).isBefore(endDate)
				|| account.getBirthDate().withYear(endDate.getYear()).isEqual(endDate))
				&& (account.getBirthDate().withYear(endDate.getYear()).isAfter(beginDate)
						|| account.getBirthDate().withYear(endDate.getYear()).isEqual(beginDate)))
			year = endDate.getYear();

		if (year != null)
		{
			LocalDate birthDateYear = account.getBirthDate().withYear(year);
			journalDao.createJournalEvent(journal, birthDateYear, LocalTime.MIDNIGHT,
					"Mon " + Period.between(account.getBirthDate(), birthDateYear).getYears() + "ème anniversaire",
					null, null, true);
		}
		return journal;
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
	public JournalEvent createJournalEvent(Journal journal, LocalDate date, LocalTime time, String description,
			String place, String comments, boolean isAnnually)
	{
		return journalDao.createJournalEvent(journal, date, time, description, place, comments, isAnnually);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public void updateJournalEvent(JournalEvent journalEvent, LocalDate date, LocalTime time, String description,
			String place, String comments, boolean isAnnually)
	{
		journalDao.updateJournalEvent(journalEvent, date, time, description, place, comments, isAnnually);
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
	public Collection<JournalEvent> retrieveJournalEventsByDate(Account account, LocalDate date)
	{
		return journalDao.retrieveJournalEventsByDate(account, date);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<JournalEvent> retrieveJournalEventsByDate(Journal journal, LocalDate date)
	{
		return journalDao.retrieveJournalEventsByDate(journal, date);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<JournalEvent> retrieveJournalEventsByMonth(Journal journal, Month month)
	{
		return journalDao.retrieveJournalEventsByMonth(journal, month);
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public Collection<JournalEvent> retrieveAllJournalEvents(Journal journal)
	{
		return journalDao.retrieveAllJournalEvents(journal);
	}

	//---------------------------------------------------------------------------------------------------------------------

}
