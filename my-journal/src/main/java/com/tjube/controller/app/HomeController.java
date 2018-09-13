package com.tjube.controller.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tjube.controller.security.SecurityContext;
import com.tjube.controller.utils.LoginUtils;
import com.tjube.controller.utils.ModelUtils;
import com.tjube.model.Account;
import com.tjube.model.DailyTask;
import com.tjube.model.Journal;
import com.tjube.model.JournalEvent;
import com.tjube.model.Tracker;
import com.tjube.model.TrackerState;
import com.tjube.service.JournalService;
import com.tjube.service.TaskService;
import com.tjube.service.TrackerService;

@Controller
@RequestMapping("/")
@ComponentScan("com.tjube.service")
@Transactional
@Configuration
public class HomeController
{

	@Autowired
	private JournalService journalService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private TrackerService trackerService;

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMM YYYY");

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "", "/" }, method = { RequestMethod.GET })
	public String journalIndex()
	{
		String result = LoginUtils.login();
		if (result != null)
			return result;

		return ModelUtils.REDIRECT_HOME;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "home", method = { RequestMethod.GET })
	public ModelAndView journalHome(ModelAndView model)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject("account", account);

		model.addObject("today", LocalDate.now().format(formatter));

		Journal journal = journalService.retrieveCurrentJournal(account);
		if (journal != null)
		{
			model.addObject("journal", journal);
			model.setViewName(ModelUtils.MODEL_HOME);
		}
		else
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_JOURNAL);
		}

		Collection<Tracker> trackers = trackerService.retrieveTrackers(journal, LocalDate.now().getMonth());
		Collection<DailyTask> dailyTasks = new ArrayList<>();
		Map<UUID, Collection<Integer>> mapTrackerData = new HashMap<>();
		Map<UUID, Collection<String>> mapTrackerColors = new HashMap<>();
		Map<UUID, Collection<String>> mapTrackerLabels = new HashMap<>();

		for (Tracker tracker : trackers)
		{
			DailyTask dailyTask = taskService.retrieveDailyTasksByDay(tracker, LocalDate.now());
			if (dailyTask != null)
				dailyTasks.add(dailyTask);

			Map<TrackerState, Integer> mapStateTotal = new HashMap<>();
			Map<TrackerState, Integer> mapStateCount = new HashMap<>();

			for (TrackerState state : tracker.getStates())
			{
				LocalDate date = LocalDate.of(journal.getYear(tracker.getMonth()).getValue(), tracker.getMonth(), 1);
				mapStateCount.put(state, 0);
				mapStateTotal.put(state, date.lengthOfMonth());
			}

			for (DailyTask task : tracker.getDailyTasks())
			{
				Integer count = mapStateCount.get(task.getState());
				mapStateCount.put(task.getState(), count + 1);
			}

			for (Entry<TrackerState, Integer> entrySet : mapStateCount.entrySet())
			{
				Integer count = mapStateCount.get(entrySet.getKey());
				Integer total = mapStateTotal.get(entrySet.getKey());

				Collection<Integer> datas = mapTrackerData.get(entrySet.getKey().getTracker().getUuid());
				if (datas == null)
					datas = new ArrayList<>();

				datas.add(count * 100 / total);

				Collection<String> colors = mapTrackerColors.get(entrySet.getKey().getTracker().getUuid());
				if (colors == null)
					colors = new ArrayList<>();

				colors.add(entrySet.getKey().getColor());

				Collection<String> labels = mapTrackerLabels.get(entrySet.getKey().getTracker().getUuid());
				if (labels == null)
					labels = new ArrayList<>();

				labels.add(entrySet.getKey().getName());
			}
		}

		model.addObject("mapTrackerData", mapTrackerData);
		model.addObject("mapTrackerColors", mapTrackerColors);
		model.addObject("mapTrackerLabels", mapTrackerLabels);

		model.addObject("trackers", trackers);
		model.addObject("dailyTasks", dailyTasks);

		//TODO : mettre des graphiques pour les trackers du mois en cours + synthèses des trackers du jour avec couleur+ affichers les trackers renseigné si il y en a (dans le calendriers)

		Collection<JournalEvent> journalEvents = journalService.retrieveJournalEventsByDate(account, LocalDate.now());
		model.addObject("journalEvents", journalEvents);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

}
