package com.tjube.controller.app;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tjube.controller.app.form.MonthlyTaskAddForm;
import com.tjube.controller.app.json.ObjectChangeStateJSON;
import com.tjube.controller.security.SecurityContext;
import com.tjube.controller.utils.LoginUtils;
import com.tjube.controller.utils.ModelUtils;
import com.tjube.model.Account;
import com.tjube.model.Budget;
import com.tjube.model.CategoryTask;
import com.tjube.model.Journal;
import com.tjube.model.MonthlyTask;
import com.tjube.model.Objective;
import com.tjube.model.Wish;
import com.tjube.model.WishList;
import com.tjube.model.enums.TaskStateEvent;
import com.tjube.service.BudgetService;
import com.tjube.service.CategoryService;
import com.tjube.service.JournalService;
import com.tjube.service.ObjectiveService;
import com.tjube.service.TaskService;
import com.tjube.service.WishService;

@Controller
@RequestMapping("/tasks")
@ComponentScan("com.tjube.service")
@Transactional
@Configuration
public class TaskController
{
	@Autowired
	private ObjectiveService objectiveService;

	@Autowired
	private WishService wishService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private JournalService journalService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private BudgetService budgetService;

	//---------------------------------------------------------------------------------------------------------------------
	// WISH - BASE
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "", "/" }, method = { RequestMethod.GET })
	public ModelAndView wishBase(HttpServletRequest request, HttpServletResponse response, ModelAndView model)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		model.setViewName(ModelUtils.REDIRECT_TASK_JOURNAL_LIST);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// TASK JOURNAL LIST - HOME
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/journalList" }, method = { RequestMethod.GET })
	public ModelAndView taskJournalListHome(HttpServletRequest request, HttpServletResponse response,
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
	// TASK MONTH LIST - HOME
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/monthList" }, method = { RequestMethod.GET })
	public ModelAndView taskMonthListHome(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
			@RequestParam(value = "uuid", required = true) UUID uuid)
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
			model.setViewName(ModelUtils.REDIRECT_TASK_JOURNAL_LIST);
			return model;
		}

		EnumSet<Month> months = EnumSet.noneOf(Month.class);
		Period period = Period.between(journal.getBeginDate(), journal.getEndDate());
		for (int i = journal.getBeginDate().getMonthValue(); i <= journal.getBeginDate().getMonthValue()
				+ period.getMonths(); i++)
		{
			months.add(Month.of(i));
		}

		model.addObject("months", months);
		model.addObject("journal", journal);
		model.addObject("redirect", "show");

		model.addObject(ModelUtils.MODEL_ACCOUNT, account);
		model.setViewName(ModelUtils.MODEL_GENERAL_MONTH_LIST);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// MONTH - SHOW
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/show" }, method = { RequestMethod.GET })
	public ModelAndView monthShow(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
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
			model.setViewName(ModelUtils.REDIRECT_TASK_JOURNAL_LIST);
			return model;
		}

		//		Map<Integer, Collection<JournalEvent>> mapDayJournalEvent = journalService.retrieveJournalEventsByMonth(journal, month);

		Collection<MonthlyTask> monthlyTasks = taskService.retrieveAllMonthlyTasksByMonth(journal, month);
		model.addObject("monthlyTasks", monthlyTasks);

		LocalDate beginDate = LocalDate.of(journal.getYear(month).getValue(), month, 1);

		model.addObject("beginDate", beginDate);
		model.addObject("endDate", beginDate.plusMonths(1).minusDays(1));

		model.addObject("month", month);
		model.addObject("year", journal.getYear(month));
		model.addObject("journal", journal);
		model.addObject(ModelUtils.MODEL_ACCOUNT, account);
		model.setViewName(ModelUtils.MODEL_TASKS_SHOW);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// MONTHLY TASK - ADD - GET
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/monthly/add", method = { RequestMethod.GET })
	public ModelAndView monthlyTaskAddGet(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
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
			model.setViewName(ModelUtils.REDIRECT_TASK_JOURNAL_LIST);
			return model;
		}

		LocalDate beginDate = LocalDate.of(journal.getYear(month).getValue(), month, 1);
		model.addObject("beginDate", beginDate);
		model.addObject("endDate", beginDate.plusMonths(1).minusDays(1));

		Collection<TaskStateEvent> states = new ArrayList<>();
		states.add(TaskStateEvent.TO_DO);

		Collection<CategoryTask> categoryTasks = categoryService.retrieveAllCategoryTasksByAccount(account);
		Collection<Objective> objectives = objectiveService.retrieveObjectives(account, states);
		Collection<WishList> wishLists = wishService.retrieveWishLists(account);

		model.addObject("categoryTasks", categoryTasks);
		model.addObject("objectives", objectives);
		model.addObject("wishLists", wishLists);

		model.addObject(ModelUtils.MODEL_ACCOUNT, account);
		model.addObject(ModelUtils.MODEL_TASK_MONTHLY_ADD_FORM, new MonthlyTaskAddForm(journal, month));
		model.setViewName(ModelUtils.MODEL_TASKS_MONTH_ADD);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// MONTHLY TASK - ADD - POST
	//---------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = { "/monthly/add" }, method = { RequestMethod.POST })
	public ModelAndView monthlyTaskAddPost(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
			@Valid @ModelAttribute(ModelUtils.MODEL_TASK_MONTHLY_ADD_FORM) MonthlyTaskAddForm form,
			BindingResult validationResult)
	{
		model.setViewName(ModelUtils.MODEL_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject(ModelUtils.MODEL_ACCOUNT, account);

		if (validationResult.hasErrors())
		{
			model.setViewName(ModelUtils.MODEL_TASKS_MONTH_ADD);
			model.addObject(ModelUtils.MODEL_TASK_MONTHLY_ADD_FORM, form);

			return model;
		}

		Journal journal = journalService.retrieveJournal(form.getJournalUuid());
		if (journal == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_TASK_JOURNAL_LIST);
			return model;
		}

		CategoryTask categoryTask = categoryService.retrieveCategoryTask(form.getCategoryTaskUuid());

		Objective objective = objectiveService.retrieveObjective(form.getObjectiveUuid());

		Wish wish = wishService.retrieveWish(form.getWishUuid());

		MonthlyTask monthlyTask = taskService.createMonthlyTask(journal, form.getDescription(), form.isPro(),
				form.getMonth(), categoryTask, objective, wish);

		if (wish != null)
		{
			Budget budget = budgetService.retrieveBudget(journal, form.getMonth(), wish.getCategory());
			wishService.updateWish(wish, budget);
		}

		model.setViewName(
				ModelUtils.REDIRECT_TASK_MONTH_SHOW + "?uuid=" + journal.getUuid() + "&month=" + form.getMonth());
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/updateState", method = { RequestMethod.POST }, consumes = "application/json")
	public void objectiveStatePatch(HttpServletRequest request, HttpServletResponse response,
			@RequestBody ObjectChangeStateJSON form, @RequestParam("uuid") UUID taskUuid)
	{
		MonthlyTask monthlyTask = taskService.retrieveMonthlyTask(taskUuid);
		if (monthlyTask == null)
		{
			return;
		}

		if (!form.getState().equalsIgnoreCase(TaskStateEvent.POSTPONE.toString()))
		{
			if (monthlyTask.getObjective() != null)
				objectiveService.updateState(monthlyTask.getObjective(), TaskStateEvent.valueOf(form.getState()));

			if (monthlyTask.getWish() != null)
				wishService.updateWishState(monthlyTask.getWish(), TaskStateEvent.valueOf(form.getState()),false);
		}
		else
		{
			Month newMonth = null;
			if (monthlyTask.getMonth().ordinal() < 12)
				newMonth = Month.values()[monthlyTask.getMonth().ordinal() + 1];

			if (newMonth != null)
				taskService.createMonthlyTask(monthlyTask.getJournal(), monthlyTask.getDescription(),
						monthlyTask.isProfessional(), newMonth, monthlyTask.getCategoryTask(),
						monthlyTask.getObjective(), monthlyTask.getWish());
		}

		taskService.updateMonthlyTaskState(monthlyTask, TaskStateEvent.valueOf(form.getState()));
	}
}
