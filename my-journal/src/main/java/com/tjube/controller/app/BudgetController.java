package com.tjube.controller.app;

import java.time.Month;
import java.time.Period;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tjube.controller.app.form.BudgetCreationForm;
import com.tjube.controller.security.SecurityContext;
import com.tjube.controller.utils.LoginUtils;
import com.tjube.controller.utils.ModelUtils;
import com.tjube.model.Account;
import com.tjube.model.Budget;
import com.tjube.model.CategoryTask;
import com.tjube.model.Journal;
import com.tjube.service.BudgetService;
import com.tjube.service.CategoryService;
import com.tjube.service.JournalService;
import com.tjube.service.WishService;

@Controller
@RequestMapping("/budget")
@ComponentScan("com.tjube.service")
@Transactional
@Configuration
public class BudgetController
{

	@Autowired
	private WishService wishService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private BudgetService budgetService;

	@Autowired
	private JournalService journalService;

	//---------------------------------------------------------------------------------------------------------------------
	// BUDGET - BASE
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "" }, method = { RequestMethod.GET })
	public ModelAndView budgetBase(HttpServletRequest request, HttpServletResponse response, ModelAndView model)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		model.setViewName(ModelUtils.REDIRECT_BUDGET_JOURNAL_LIST);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// BUDGET JOURNAL LIST - HOME
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/journalList" }, method = { RequestMethod.GET })
	public ModelAndView budgetJournalListHome(HttpServletRequest request, HttpServletResponse response,
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
	// BUDGET MONTH LIST - HOME
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/monthList" }, method = { RequestMethod.GET })
	public ModelAndView budgetMonthListHome(HttpServletRequest request, HttpServletResponse response,
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
	// BUDGET - SHOW
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/show" }, method = { RequestMethod.GET })
	public ModelAndView budgetShow(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
			@RequestParam(value = "uuid", required = true) UUID uuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();

		Budget budget = budgetService.retrieveBudget(uuid);
		if (budget == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_BUDGET_JOURNAL_LIST);
			return model;
		}

		model.addObject(ModelUtils.MODEL_ACCOUNT, account);
		model.addObject("budget", budget);
		model.setViewName(ModelUtils.MODEL_BUDGET_SHOW);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// BUDGET - LIST
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/list" }, method = { RequestMethod.GET })
	public ModelAndView budgetListHome(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
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

		Collection<Budget> results = budgetService.retrieveBudgets(journal, month);

		model.addObject(ModelUtils.MODEL_ACCOUNT, account);
		model.addObject("budgets", results);
		model.setViewName(ModelUtils.MODEL_BUDGET_LIST);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// BUDGET - CREATION
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/creation", method = { RequestMethod.GET })
	public ModelAndView budgetCreationGet(ModelAndView model, @RequestParam(value = "uuid", required = true) UUID uuid,
			@RequestParam(value = "month", required = true) Month month)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject("account", account);

		Collection<CategoryTask> categoryTasks = categoryService.retrieveAllCategoryTasksByAccount(account);
		model.addObject("categoryTasks", categoryTasks);

		model.addObject("form", new BudgetCreationForm(uuid, month));
		model.setViewName(ModelUtils.MODEL_BUDGET_CREATION);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/creation" }, method = { RequestMethod.POST })
	public ModelAndView budgetCreationPost(HttpServletRequest request, ModelAndView model,
			@Valid @ModelAttribute("form") BudgetCreationForm form, BindingResult validationResult)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		if (validationResult.hasErrors())
		{
			model.setViewName(ModelUtils.MODEL_BUDGET_CREATION);
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
			model.setViewName(ModelUtils.REDIRECT_BUDGET_LIST);
			model.addObject("journalNotFound", true);
			return model;
		}

		CategoryTask categoryTask = null;
		if (form.getCategoryTaskUuid() != null)
			categoryTask = categoryService.retrieveCategoryTask(form.getCategoryTaskUuid());

		budgetService.createBudget(form.getDescription(), form.getBudgetTotal(), journal, form.getMonth(),
				categoryTask);

		model.setViewName(ModelUtils.REDIRECT_BUDGET_LIST + "?uuid=" + journal.getUuid() + "&month=" + form.getMonth());

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// BUDGET - UPDATE
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/update", method = { RequestMethod.GET })
	public ModelAndView budgetUpdateGet(ModelAndView model, @RequestParam(value = "uuid", required = true) UUID uuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject("account", account);

		Collection<CategoryTask> categoryTasks = categoryService.retrieveAllCategoryTasksByAccount(account);
		model.addObject("categoryTasks", categoryTasks);

		Budget budget = budgetService.retrieveBudget(uuid);
		if (budget == null)
		{
			model.setViewName(ModelUtils.REDIRECT_BUDGET_LIST);
			model.addObject("budgetNotFound", true);
			return model;
		}

		model.addObject("form", new BudgetCreationForm(budget));
		model.setViewName(ModelUtils.MODEL_BUDGET_UPDATE);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/update" }, method = { RequestMethod.POST })
	public ModelAndView budgetUpdatePost(HttpServletRequest request, ModelAndView model,
			@Valid @ModelAttribute("form") BudgetCreationForm form, BindingResult validationResult)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		if (validationResult.hasErrors())
		{
			model.setViewName(ModelUtils.MODEL_BUDGET_UPDATE);
			return model;
		}

		Account account = SecurityContext.getInstance().getCurrentUser();
		if (account == null)
		{
			model.setViewName(ModelUtils.MODEL_LOGIN);
			model.addObject("accountNotFound", true);
			return model;
		}

		Budget budget = budgetService.retrieveBudget(form.getBudgetUuid());
		if (budget == null)
		{
			model.setViewName(ModelUtils.REDIRECT_BUDGET_LIST);
			model.addObject("budgetNotFound", true);
			return model;
		}

		budgetService.updateBudget(budget, form.getDescription(), form.getBudgetTotal());

		model.setViewName(ModelUtils.REDIRECT_BUDGET_LIST + "?uuid=" + budget.getJournal().getUuid() + "&month="
				+ budget.getMonth());

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/delete", method = { RequestMethod.GET })
	public ModelAndView objectiveDeleteGet(ModelAndView model, @RequestParam("uuid") UUID budgetUuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject("account", account);

		Budget budget = budgetService.retrieveBudget(budgetUuid);
		if (budget == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_BUDGET_JOURNAL_LIST);
		}

		budgetService.removeBudget(budget);

		model.setViewName(ModelUtils.REDIRECT_BUDGET_LIST + "?uuid=" + budget.getJournal().getUuid() + "&month="
				+ budget.getMonth());
		return model;
	}
}
