package com.tjube.controller.app;

import java.time.LocalDate;
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

import com.tjube.controller.app.form.MonthlyTaskAddForm;
import com.tjube.controller.app.form.WishListForm;
import com.tjube.controller.app.form.WishWishForm;
import com.tjube.controller.security.SecurityContext;
import com.tjube.controller.utils.LoginUtils;
import com.tjube.controller.utils.ModelUtils;
import com.tjube.model.Account;
import com.tjube.model.CategoryTask;
import com.tjube.model.Journal;
import com.tjube.model.MonthlyTask;
import com.tjube.model.Objective;
import com.tjube.model.Wish;
import com.tjube.model.WishList;
import com.tjube.model.enums.TaskStateEvent;
import com.tjube.model.enums.TaskUnit;
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

		model.addObject("unitValues", TaskUnit.values());
		LocalDate beginDate = LocalDate.of(journal.getYear(month).getValue(), month, 1);
		model.addObject("beginDate", beginDate);
		model.addObject("endDate", beginDate.plusMonths(1).minusDays(1));

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
		model.setViewName(
				ModelUtils.REDIRECT_TASK_MONTH_SHOW + "?uuid=" + journal.getUuid() + "&month=" + form.getMonth());
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// WISH LIST - EDIT - GET
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/list/edit", method = { RequestMethod.GET })
	public ModelAndView wishListEditGet(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
			@RequestParam(value = "uuid", required = true) UUID uuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject(ModelUtils.MODEL_ACCOUNT, account);

		WishList wishList = wishService.retrieveWishList(uuid);
		if (wishList == null || !account.equals(wishList.getAccount()))
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_WISH_LIST);
			return model;
		}

		model.addObject("wishList", wishList);
		model.addObject(ModelUtils.MODEL_WISH_LIST_FORM, new WishListForm(wishList));
		model.setViewName(ModelUtils.MODEL_WISH_LIST_EDITION);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// WISH LIST - EDIT - POST
	//---------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = { "/list/edit" }, method = { RequestMethod.POST })
	public ModelAndView wishListEditPost(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
			@Valid @ModelAttribute(ModelUtils.MODEL_WISH_LIST_FORM) WishListForm form, BindingResult validationResult)
	{
		model.setViewName(ModelUtils.MODEL_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject(ModelUtils.MODEL_ACCOUNT, account);

		WishList wishList = wishService.retrieveWishList(form.getUuid());
		if (wishList == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_WISH_LIST);
			return model;
		}

		if (validationResult.hasErrors())
		{
			model.setViewName(ModelUtils.MODEL_WISH_LIST_EDITION);
			model.addObject(ModelUtils.MODEL_WISH_LIST_FORM, form);

			return model;
		}

		wishService.updateWishList(wishList, form.getDescription());
		model.setViewName(ModelUtils.REDIRECT_WISH_LIST_SHOW + wishList.getUuid());
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// WISH LIST - REMOVE
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/list/remove" }, method = { RequestMethod.GET })
	public ModelAndView wishListRemove(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
			@RequestParam(value = "uuid", required = true) UUID uuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		WishList wishList = wishService.retrieveWishList(uuid);
		if (wishList == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_WISH_LIST);
			return model;
		}

		Account account = SecurityContext.getInstance().getCurrentUser();
		if (account.equals(wishList.getAccount()))
			wishService.removeWishList(wishList);

		model.setViewName(ModelUtils.REDIRECT_WISH_LIST);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// WISH LIST - WISH - SHOW
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/list/wish/show" }, method = { RequestMethod.GET })
	public ModelAndView wishListWishShow(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
			@RequestParam(value = "uuid", required = true) UUID uuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();

		Wish wish = wishService.retrieveWish(uuid);
		if (wish == null || !account.equals(wish.getWishList().getAccount()))
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_WISH_LIST);
			return model;
		}

		model.addObject(ModelUtils.MODEL_ACCOUNT, account);
		model.addObject("wish", wish);
		model.setViewName(ModelUtils.MODEL_WISH_WISH_SHOW);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// WISH LIST - WISH - ADD - GET
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/list/wish/add", method = { RequestMethod.GET })
	public ModelAndView wishListAddGet(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
			@RequestParam(value = "uuid", required = true) UUID uuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		WishList wishList = wishService.retrieveWishList(uuid);
		if (wishList == null || !account.equals(wishList.getAccount()))
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_WISH_LIST);
			return model;
		}

		Collection<CategoryTask> categories = categoryService.retrieveAllCategoryTasksByAccount(account);
		model.addObject("categories", categories);

		model.addObject(ModelUtils.MODEL_ACCOUNT, account);
		model.addObject("wishList", wishList);
		model.addObject(ModelUtils.MODEL_WISH_WISH_FORM, new WishWishForm(wishList));
		model.setViewName(ModelUtils.MODEL_WISH_WISH_EDITION);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// WISH LIST - WISH - ADD - POST
	//---------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = { "/list/wish/add" }, method = { RequestMethod.POST })
	public ModelAndView wishListWishAddPost(HttpServletRequest request, HttpServletResponse response,
			ModelAndView model, @Valid @ModelAttribute(ModelUtils.MODEL_WISH_WISH_FORM) WishWishForm form,
			BindingResult validationResult)
	{
		model.setViewName(ModelUtils.MODEL_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject(ModelUtils.MODEL_ACCOUNT, account);

		WishList wishList = wishService.retrieveWishList(form.getWishListUuid());
		CategoryTask categoryTask = categoryService.retrieveCategoryTask(form.getCategoryUuid());

		if (wishList == null || categoryTask == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_WISH_LIST);
			return model;
		}

		if (validationResult.hasErrors())
		{
			model.addObject("wishList", wishList);
			Collection<CategoryTask> categories = categoryService.retrieveAllCategoryTasksByAccount(account);
			model.addObject("categories", categories);
			model.setViewName(ModelUtils.MODEL_WISH_WISH_EDITION);
			model.addObject(ModelUtils.MODEL_WISH_WISH_FORM, form);

			return model;
		}

		wishService.createWish(wishList, categoryTask, form.getDescription(), form.getPrice(), TaskStateEvent.TO_DO);

		model.setViewName(ModelUtils.REDIRECT_WISH_LIST_SHOW + wishList.getUuid());

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// WISH LIST - WISH - EDIT - GET
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/list/wish/edit", method = { RequestMethod.GET })
	public ModelAndView wishListWishEditGet(HttpServletRequest request, HttpServletResponse response,
			ModelAndView model, @RequestParam(value = "uuid", required = true) UUID uuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject(ModelUtils.MODEL_ACCOUNT, account);

		Wish wish = wishService.retrieveWish(uuid);
		if (wish == null || !account.equals(wish.getWishList().getAccount()))
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_WISH_LIST);
			return model;
		}

		model.addObject("wish", wish);
		model.addObject("wishList", wish.getWishList());
		Collection<CategoryTask> categories = categoryService.retrieveAllCategoryTasksByAccount(account);
		model.addObject("categories", categories);
		model.addObject(ModelUtils.MODEL_WISH_WISH_FORM, new WishWishForm(wish));
		model.setViewName(ModelUtils.MODEL_WISH_WISH_EDITION);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// WISH LIST - WISH - EDIT - POST
	//---------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = { "/list/wish/edit" }, method = { RequestMethod.POST })
	public ModelAndView wishListEditPost(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
			@Valid @ModelAttribute(ModelUtils.MODEL_WISH_WISH_FORM) WishWishForm form, BindingResult validationResult)
	{
		model.setViewName(ModelUtils.MODEL_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject(ModelUtils.MODEL_ACCOUNT, account);

		Wish wish = wishService.retrieveWish(form.getUuid());
		CategoryTask categoryTask = categoryService.retrieveCategoryTask(form.getCategoryUuid());

		if (wish == null || categoryTask == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_WISH_LIST);
			return model;
		}

		if (validationResult.hasErrors())
		{
			model.addObject("wish", wish);
			model.addObject("wishList", wish.getWishList());
			model.setViewName(ModelUtils.MODEL_WISH_WISH_EDITION);
			Collection<CategoryTask> categories = categoryService.retrieveAllCategoryTasksByAccount(account);
			model.addObject("categories", categories);
			model.addObject(ModelUtils.MODEL_WISH_WISH_FORM, form);

			return model;
		}

		wishService.updateWish(wish, categoryTask, form.getDescription(), form.getPrice());
		model.setViewName(ModelUtils.REDIRECT_WISH_LIST_SHOW + wish.getWishList().getUuid());
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// WISH LIST - WISH - REMOVE
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/list/wish/remove" }, method = { RequestMethod.GET })
	public ModelAndView wishListWishRemove(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
			@RequestParam(value = "uuid", required = true) UUID uuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Wish wish = wishService.retrieveWish(uuid);
		if (wish == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_WISH_LIST);
			return model;
		}

		Account account = SecurityContext.getInstance().getCurrentUser();
		if (account.equals(wish.getWishList().getAccount()))
			wishService.removeWish(wish);

		model.setViewName(ModelUtils.REDIRECT_WISH_LIST_SHOW + wish.getWishList().getUuid());
		return model;
	}
}
