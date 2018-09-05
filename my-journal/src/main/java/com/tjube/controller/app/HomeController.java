package com.tjube.controller.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

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
import com.tjube.model.Journal;
import com.tjube.model.JournalEvent;
import com.tjube.service.JournalService;
import com.tjube.service.TaskService;

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

		//		Collection<DailyTask> dailyTasks = taskService.retrieveAllDailyTasksByDay(tracker, LocalDate.now());
		//		model.addObject("dailyTasks", dailyTasks);

		//TODO : mettre des graphiques pour les trackers du mois en cours + synthèses des trackers du jour avec couleur+ affichers les trackers renseigné si il y en a (dans le calendriers)

		Collection<JournalEvent> journalEvents = journalService.retrieveJournalEventsByDate(account,
				LocalDateTime.now());
		model.addObject("journalEvents", journalEvents);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

}
