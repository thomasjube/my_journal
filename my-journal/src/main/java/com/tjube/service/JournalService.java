package com.tjube.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Collection;
import java.util.UUID;

import com.tjube.model.Account;
import com.tjube.model.Journal;
import com.tjube.model.JournalEvent;

public interface JournalService
{
	//---------------------------------------------------------------------------------------------------------------------
	// JOURNAL OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	Journal createJournal(Account account, LocalDate beginDate, LocalDate endDate);

	void updateJournal(Journal journal, LocalDate beginDate, LocalDate endDate);

	void removeJournal(Journal journal);

	Journal retrieveJournal(UUID uuid);

	Journal retrieveJournal(Account account, LocalDate date);

	Journal retrieveCurrentJournal(Account account);

	Collection<Journal> retrieveJournals(Account account);

	//---------------------------------------------------------------------------------------------------------------------
	// JOURNAL_EVENT OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	JournalEvent createJournalEvent(Journal journal, LocalDate date, LocalTime time, String description, String place,
			String comments, boolean isAnnually);

	void updateJournalEvent(JournalEvent journalEvent, LocalDate date, LocalTime time, String description, String place,
			String comments, boolean isAnnually);

	void removeJournalEvent(JournalEvent journalEvent);

	JournalEvent retrieveJournalEvent(UUID uuid);

	Collection<JournalEvent> retrieveJournalEventsByDate(Account account, LocalDate date);

	Collection<JournalEvent> retrieveJournalEventsByDate(Journal journal, LocalDate date);

	Collection<JournalEvent> retrieveJournalEventsByMonth(Journal journal, Month month);

	Collection<JournalEvent> retrieveAllJournalEvents(Journal journal);

}
