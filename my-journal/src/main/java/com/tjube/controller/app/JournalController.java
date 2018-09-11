package com.tjube.controller.app;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
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

import com.tjube.controller.app.form.JournalCreationForm;
import com.tjube.controller.app.form.JournalEventCreationForm;
import com.tjube.controller.security.SecurityContext;
import com.tjube.controller.utils.LoginUtils;
import com.tjube.controller.utils.ModelUtils;
import com.tjube.model.Account;
import com.tjube.model.Journal;
import com.tjube.model.JournalEvent;
import com.tjube.model.MonthlyStats;
import com.tjube.service.JournalService;
import com.tjube.service.TaskService;

@Controller
@RequestMapping("/journal")
@ComponentScan("com.tjube.service")
@Transactional
@Configuration
public class JournalController
{

	@Autowired
	private JournalService journalService;

	@Autowired
	private TaskService taskService;

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "", "/" }, method = { RequestMethod.GET })
	public ModelAndView journalIndex(ModelAndView model)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();

		Collection<Journal> journals = journalService.retrieveJournals(account);
		model.addObject("journals", journals);

		model.setViewName(ModelUtils.MODEL_JOURNAL_LIST);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/show", method = { RequestMethod.GET })
	public ModelAndView journalShow(ModelAndView model, @RequestParam("uuid") UUID journalUuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		if (account == null)
		{
			model.setViewName(ModelUtils.MODEL_LOGIN);
			model.addObject("accountNotFound", true);
			return model;
		}

		Journal journal = journalService.retrieveJournal(journalUuid);
		if (journal == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_JOURNAL);
			return model;
		}

		model.addObject("journal", journal);

		Map<Month, MonthlyStats> mapMonthStats = taskService.getMonthlyStats(journal);
		model.addObject("mapMonthStats", mapMonthStats);

		List<Month> months = new ArrayList<>();
		for (Month month : journal.getMonths())
		{
			months.add(month);
			if (mapMonthStats.get(month) == null)
				mapMonthStats.put(month, new MonthlyStats());
		}
		Collections.sort(months, new Comparator<Month>() {
			@Override
			public int compare(Month o1, Month o2)
			{
				if (o1.ordinal() < o2.ordinal())
					return -1;
				else if (o1.ordinal() > o2.ordinal())
					return 1;
				else
					return -1;
			}
		});

		model.addObject("months", months);

		model.setViewName(ModelUtils.MODEL_JOURNAL_SHOW);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/creation", method = { RequestMethod.GET })
	public ModelAndView journalCreationGet(ModelAndView model)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject("account", account);

		model.addObject("form", new JournalCreationForm());
		model.setViewName(ModelUtils.MODEL_JOURNAL_CREATION);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/creation" }, method = { RequestMethod.POST })
	public ModelAndView journalCreationPost(HttpServletRequest request, ModelAndView model,
			@Valid @ModelAttribute("form") JournalCreationForm form, BindingResult validationResult)
	{
		model.setViewName(ModelUtils.MODEL_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		if (validationResult.hasErrors())
		{
			model.setViewName(ModelUtils.MODEL_JOURNAL_CREATION);
			return model;
		}

		Account account = SecurityContext.getInstance().getCurrentUser();
		if (account == null)
		{
			model.setViewName(ModelUtils.MODEL_LOGIN);
			model.addObject("accountNotFound", true);
			return model;
		}

		journalService.createJournal(account, form.getBeginDate(), form.getEndDate());
		model.setViewName(ModelUtils.REDIRECT_JOURNAL);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/update", method = { RequestMethod.GET })
	public ModelAndView journalUpdateGet(ModelAndView model, @RequestParam("uuid") UUID journalUuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject("account", account);

		Journal journal = journalService.retrieveJournal(journalUuid);
		if (journal == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_JOURNAL);
		}

		model.addObject("form", new JournalCreationForm(journal));
		model.setViewName(ModelUtils.MODEL_JOURNAL_UPDATE);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/update" }, method = { RequestMethod.POST })
	public ModelAndView journalUpdatePost(HttpServletRequest request, ModelAndView model,
			@Valid @ModelAttribute("form") JournalCreationForm form, BindingResult validationResult)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		if (validationResult.hasErrors())
		{
			model.setViewName(ModelUtils.MODEL_JOURNAL_UPDATE);
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
			model.setViewName(ModelUtils.REDIRECT_JOURNAL);
			return model;
		}

		journalService.updateJournal(journal, form.getBeginDate(), form.getEndDate());

		model.setViewName(ModelUtils.REDIRECT_JOURNAL);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/delete", method = { RequestMethod.GET })
	public ModelAndView journalDeleteGet(ModelAndView model, @RequestParam("uuid") UUID journalUuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject("account", account);

		Journal journal = journalService.retrieveJournal(journalUuid);
		if (journal == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_JOURNAL);
		}

		journalService.removeJournal(journal);

		model.setViewName(ModelUtils.REDIRECT_JOURNAL);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// JOURNAL EVENT OPERATIONS
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/event/creation", method = { RequestMethod.GET })
	public ModelAndView journalCreationGet(ModelAndView model, @RequestParam("uuid") UUID journalUuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject("account", account);

		model.addObject("form", new JournalEventCreationForm(journalUuid));
		model.setViewName(ModelUtils.MODEL_JOURNAL_EVENT_CREATION);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/event/creation" }, method = { RequestMethod.POST })
	public ModelAndView journalEventCreationPost(HttpServletRequest request, ModelAndView model,
			@Valid @ModelAttribute("form") JournalEventCreationForm form, BindingResult validationResult)
	{
		model.setViewName(ModelUtils.MODEL_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		if (validationResult.hasErrors())
		{
			model.setViewName(ModelUtils.MODEL_JOURNAL_EVENT_CREATION);
			return model;
		}

		Account account = SecurityContext.getInstance().getCurrentUser();
		if (account == null)
		{
			model.setViewName(ModelUtils.REDIRECT_LOGIN);
			model.addObject("accountNotFound", true);
			return model;
		}

		Journal journal = journalService.retrieveJournal(form.getJournalUuid());
		if (journal == null)
		{
			model.setViewName(ModelUtils.REDIRECT_JOURNAL);
			model.addObject("journalNotFound", true);
			return model;
		}

		journalService.createJournalEvent(journal, form.getDate(), form.getTime(), form.getDescription(),
				form.getPlace(), form.getComments(), form.isAnnually());
		model.setViewName(ModelUtils.REDIRECT_JOURNAL_SHOW + journal.getUuid());
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/event/update", method = { RequestMethod.GET })
	public ModelAndView journalEventUpdateGet(ModelAndView model, @RequestParam("uuid") UUID journalEventUuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject("account", account);

		JournalEvent journalEvent = journalService.retrieveJournalEvent(journalEventUuid);
		if (journalEvent == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_JOURNAL);
		}

		model.addObject("form", new JournalEventCreationForm(journalEvent));
		model.setViewName(ModelUtils.MODEL_JOURNAL_EVENT_UPDATE);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/event/update" }, method = { RequestMethod.POST })
	public ModelAndView journalEventUpdatePost(HttpServletRequest request, ModelAndView model,
			@Valid @ModelAttribute("form") JournalEventCreationForm form, BindingResult validationResult)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		if (validationResult.hasErrors())
		{
			model.setViewName(ModelUtils.MODEL_JOURNAL_EVENT_UPDATE);
			return model;
		}

		Account account = SecurityContext.getInstance().getCurrentUser();
		if (account == null)
		{
			model.setViewName(ModelUtils.MODEL_LOGIN);
			model.addObject("accountNotFound", true);
			return model;
		}

		JournalEvent journalEvent = journalService.retrieveJournalEvent(form.getJournalEventUuid());
		if (journalEvent == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_JOURNAL);
			return model;
		}

		journalService.updateJournalEvent(journalEvent, form.getDate(), form.getTime(), form.getDescription(),
				form.getPlace(), form.getComments(), form.isAnnually());

		model.setViewName(ModelUtils.REDIRECT_JOURNAL_SHOW + journalEvent.getJournal().getUuid());

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/event/delete", method = { RequestMethod.GET })
	public ModelAndView journalEventDeleteGet(ModelAndView model, @RequestParam("uuid") UUID journalEventUuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject("account", account);

		JournalEvent journalEvent = journalService.retrieveJournalEvent(journalEventUuid);
		if (journalEvent == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_JOURNAL);
		}

		journalService.removeJournalEvent(journalEvent);

		model.setViewName(ModelUtils.REDIRECT_JOURNAL_SHOW + journalEvent.getJournal().getUuid());
		return model;
	}
}
