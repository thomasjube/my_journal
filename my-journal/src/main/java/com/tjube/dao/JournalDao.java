package com.tjube.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

import com.tjube.model.Account;
import com.tjube.model.Journal;
import com.tjube.model.JournalEvent;

public interface JournalDao
{
	//---------------------------------------------------------------------------------------------------------------------
	// JOURNAL OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	Journal createJournal(Account account, LocalDate beginDate, LocalDate endDate);

	void updateJournal(Journal journal, LocalDate beginDate, LocalDate endDate);

	void removeJournal(Journal journal);

	Journal retrieveJournal(UUID uuid);

	Journal retrieveJournal(Account account, LocalDate date);

	Collection<Journal> retrieveJournals(Account account);

	//---------------------------------------------------------------------------------------------------------------------
	// JOURNAL_EVENT OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	JournalEvent createJournalEvent(Journal journal, LocalDateTime dateTime, String description, String place,
			String comments, boolean isAnnually);

	void updateJournalEvent(JournalEvent journalEvent, LocalDateTime dateTime, String description, String place,
			String comments, boolean isAnnually);

	void removeJournalEvent(JournalEvent journalEvent);

	JournalEvent retrieveJournalEvent(UUID uuid);

	Collection<JournalEvent> retrieveJournalEventsByDate(Journal journal, LocalDateTime dateTime);

	Collection<JournalEvent> retrieveAllJournalEvents(Journal journal);
}
