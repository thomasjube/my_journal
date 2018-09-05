package com.tjube.controller.app;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tjube.controller.app.form.TrackerCreationForm;
import com.tjube.controller.security.SecurityContext;
import com.tjube.controller.utils.LoginUtils;
import com.tjube.controller.utils.ModelUtils;
import com.tjube.model.Account;
import com.tjube.model.DailyTask;
import com.tjube.model.Journal;
import com.tjube.model.Tracker;
import com.tjube.model.TrackerState;
import com.tjube.service.JournalService;
import com.tjube.service.TaskService;
import com.tjube.service.TrackerService;

@Controller
@RequestMapping("/tracker")
@ComponentScan("com.tjube.service")
@Transactional
@Configuration
public class TrackerController
{

	@Autowired
	private JournalService journalService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private TrackerService trackerService;

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "", "/" }, method = { RequestMethod.GET })
	public ModelAndView trackerIndex(ModelAndView model)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		model.setViewName(ModelUtils.REDIRECT_TRACKER_JOURNAL_LIST);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// TRACKER JOURNAL LIST - HOME
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/journalList" }, method = { RequestMethod.GET })
	public ModelAndView trackerJournalListHome(HttpServletRequest request, HttpServletResponse response,
			ModelAndView model)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();

		Collection<Journal> journals = journalService.retrieveJournals(account);
		model.addObject("journals", journals);

		model.addObject(ModelUtils.MODEL_ACCOUNT, account);
		model.setViewName(ModelUtils.MODEL_GENERAL_JOURNAL_LIST);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// TRACKER MONTH LIST - HOME
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/monthList" }, method = { RequestMethod.GET })
	public ModelAndView trackerMonthListHome(HttpServletRequest request, HttpServletResponse response,
			ModelAndView model, @RequestParam(value = "uuid", required = true) UUID uuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();

		Journal journal = journalService.retrieveJournal(uuid);
		if (journal == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_BUDGET_JOURNAL_LIST);
			return model;
		}

		EnumSet<Month> months = EnumSet.noneOf(Month.class);
		Period period = Period.between(journal.getBeginDate(), journal.getEndDate());
		for (int i = journal.getBeginDate().getMonthValue(); i <= journal.getBeginDate().getMonthValue()
				+ period.getMonths(); i++)
			months.add(Month.of(i));

		model.addObject("months", months);
		model.addObject("journal", journal);
		model.addObject("redirect", "list");

		model.addObject(ModelUtils.MODEL_ACCOUNT, account);
		model.setViewName(ModelUtils.MODEL_GENERAL_MONTH_LIST);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// TRACKER - LIST
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/list" }, method = { RequestMethod.GET })
	public ModelAndView trackerListHome(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
			@RequestParam(value = "uuid", required = true) UUID uuid,
			@RequestParam(value = "month", required = true) Month month)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();

		Journal journal = journalService.retrieveJournal(uuid);
		if (journal == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_BUDGET_JOURNAL_LIST);
			return model;
		}

		model.addObject("uuid", uuid);
		model.addObject("month", month);

		Collection<Tracker> results = trackerService.retrieveTrackers(journal, month);

		model.addObject(ModelUtils.MODEL_ACCOUNT, account);
		model.addObject("trackers", results);
		model.setViewName(ModelUtils.MODEL_TRACKER_LIST);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// TRACKER - SHOW
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/show" }, method = { RequestMethod.GET })
	public ModelAndView trackerListShow(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
			@RequestParam(value = "uuid", required = true) UUID uuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();

		Tracker tracker = trackerService.retrieveTracker(uuid);
		if (tracker == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_TRACKER);
			return model;
		}

		Journal journal = tracker.getJournal();

		model.addObject(ModelUtils.MODEL_ACCOUNT, account);
		model.addObject("tracker", tracker);

		Map<Integer, TrackerState> mapDayState = new HashMap<>();
		for (DailyTask dailyTask : tracker.getDailyTasks())
			mapDayState.put(dailyTask.getDate().getDayOfMonth(), dailyTask.getState());

		model.addObject("mapDayState", mapDayState);

		LocalDate beginDate = LocalDate.of(journal.getYear(tracker.getMonth()).getValue(), tracker.getMonth(), 1);

		model.addObject("beginDate", beginDate);
		model.addObject("endDate", beginDate.plusMonths(1).minusDays(1));

		model.addObject("month", tracker.getMonth());
		model.addObject("year", journal.getYear(tracker.getMonth()));

		model.setViewName(ModelUtils.MODEL_TRACKER_SHOW);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/creation", method = { RequestMethod.GET })
	public ModelAndView trackerCreationGet(ModelAndView model, @RequestParam(value = "uuid", required = true) UUID uuid,
			@RequestParam(value = "month", required = true) Month month)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject("account", account);

		model.addObject("form", new TrackerCreationForm(uuid, month));
		model.setViewName(ModelUtils.MODEL_TRACKER_CREATION);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/creation" }, method = { RequestMethod.POST })
	public ModelAndView trackerCreationPost(HttpServletRequest request, ModelAndView model,
			@Valid @ModelAttribute("form") TrackerCreationForm form, BindingResult validationResult)
	{
		model.setViewName(ModelUtils.MODEL_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		if (validationResult.hasErrors())
		{
			model.setViewName(ModelUtils.MODEL_TRACKER_CREATION);
			return model;
		}

		Account account = SecurityContext.getInstance().getCurrentUser();
		if (account == null)
		{
			model.setViewName(ModelUtils.MODEL_LOGIN);
			model.addObject("accountNotFound", true);
			return model;
		}

		Journal journal = journalService.retrieveJournal(form.getJournalUuid());
		if (journal == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_TRACKER_JOURNAL_LIST);
		}

		Tracker tracker = trackerService.createTracker(journal, form.getMonth(), form.getName());

		int i = 0;
		for (String name : form.getStateName())
		{
			trackerService.createTrackerState(tracker, name, form.getStateColor().get(i));
			i++;
		}

		model.setViewName(ModelUtils.REDIRECT_TRACKER_SHOW + tracker.getUuid());
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/update", method = { RequestMethod.GET })
	public ModelAndView trackerUpdateGet(ModelAndView model, @RequestParam("uuid") UUID trackerUuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject("account", account);

		Tracker tracker = trackerService.retrieveTracker(trackerUuid);
		if (tracker == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_TRACKER);
		}

		model.addObject("form", new TrackerCreationForm(tracker));
		model.setViewName(ModelUtils.MODEL_TRACKER_UPDATE);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/update" }, method = { RequestMethod.POST })
	public ModelAndView trackerUpdatePost(HttpServletRequest request, ModelAndView model,
			@Valid @ModelAttribute("form") TrackerCreationForm form, BindingResult validationResult)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		if (validationResult.hasErrors())
		{
			model.setViewName(ModelUtils.MODEL_TRACKER_UPDATE);
			return model;
		}

		Account account = SecurityContext.getInstance().getCurrentUser();
		if (account == null)
		{
			model.setViewName(ModelUtils.MODEL_LOGIN);
			model.addObject("accountNotFound", true);
			return model;
		}

		Tracker tracker = trackerService.retrieveTracker(form.getTrackerUuid());
		if (tracker == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_TRACKER);
		}

		trackerService.updateTracker(tracker, form.getName());

		model.setViewName(ModelUtils.REDIRECT_TRACKER_LIST + "?uuid=" + tracker.getJournal().getUuid() + "&month="
				+ tracker.getMonth());

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/delete", method = { RequestMethod.GET })
	public ModelAndView journalDeleteGet(ModelAndView model, @RequestParam("uuid") UUID trackerUuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject("account", account);

		Tracker tracker = trackerService.retrieveTracker(trackerUuid);
		if (tracker == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_TRACKER);
		}

		trackerService.removeTracker(tracker);

		model.setViewName(ModelUtils.REDIRECT_TRACKER);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

}
