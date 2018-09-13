package com.tjube.controller.app;

import java.util.Collection;
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

import com.tjube.controller.app.form.CategoryCreationForm;
import com.tjube.controller.security.SecurityContext;
import com.tjube.controller.utils.LoginUtils;
import com.tjube.controller.utils.ModelUtils;
import com.tjube.model.Account;
import com.tjube.model.CategoryTask;
import com.tjube.service.CategoryService;
import com.tjube.service.JournalService;
import com.tjube.service.TaskService;

@Controller
@RequestMapping("/category")
@ComponentScan("com.tjube.service")
@Transactional
@Configuration
public class CategoryController
{

	@Autowired
	private JournalService journalService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private TaskService taskService;

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "", "/" }, method = { RequestMethod.GET })
	public ModelAndView categoryIndex(ModelAndView model)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();

		Collection<CategoryTask> categories = categoryService.retrieveAllCategoryTasksByAccount(account);
		model.addObject("categories", categories);

		model.setViewName(ModelUtils.MODEL_CATEGORY_LIST);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/creation", method = { RequestMethod.GET })
	public ModelAndView categoryCreationGet(ModelAndView model)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject("account", account);

		model.addObject("form", new CategoryCreationForm());
		model.setViewName(ModelUtils.MODEL_CATEGORY_CREATION);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/creation" }, method = { RequestMethod.POST })
	public ModelAndView categoryCreationPost(HttpServletRequest request, ModelAndView model,
			@Valid @ModelAttribute("form") CategoryCreationForm form, BindingResult validationResult)
	{
		model.setViewName(ModelUtils.MODEL_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		if (validationResult.hasErrors())
		{
			model.setViewName(ModelUtils.MODEL_CATEGORY_CREATION);
			return model;
		}

		Account account = SecurityContext.getInstance().getCurrentUser();
		if (account == null)
		{
			model.setViewName(ModelUtils.MODEL_LOGIN);
			model.addObject("accountNotFound", true);
			return model;
		}

		categoryService.createCategory(form.getDescription(), account, false);
		model.setViewName(ModelUtils.REDIRECT_CATEGORY_LIST);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/update", method = { RequestMethod.GET })
	public ModelAndView categoryUpdateGet(ModelAndView model, @RequestParam("uuid") UUID categoryUuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject("account", account);

		CategoryTask category = categoryService.retrieveCategoryTask(categoryUuid);
		if (category == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_CATEGORY_LIST);
		}

		model.addObject("form", new CategoryCreationForm(category));
		model.setViewName(ModelUtils.MODEL_CATEGORY_UPDATE);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/update" }, method = { RequestMethod.POST })
	public ModelAndView categoryUpdatePost(HttpServletRequest request, ModelAndView model,
			@Valid @ModelAttribute("form") CategoryCreationForm form, BindingResult validationResult)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		if (validationResult.hasErrors())
		{
			model.setViewName(ModelUtils.MODEL_CATEGORY_UPDATE);
			return model;
		}

		Account account = SecurityContext.getInstance().getCurrentUser();
		if (account == null)
		{
			model.setViewName(ModelUtils.MODEL_LOGIN);
			model.addObject("accountNotFound", true);
			return model;
		}

		CategoryTask category = categoryService.retrieveCategoryTask(form.getCategoryUuid());
		if (category == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_CATEGORY_LIST);
			return model;
		}

		categoryService.updateCategory(category, form.getDescription());

		model.setViewName(ModelUtils.REDIRECT_CATEGORY_LIST);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/delete", method = { RequestMethod.GET })
	public ModelAndView categoryDeleteGet(ModelAndView model, @RequestParam("uuid") UUID categoryUuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject("account", account);

		CategoryTask category = categoryService.retrieveCategoryTask(categoryUuid);
		if (category == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_CATEGORY_LIST);
		}

		categoryService.removeCategory(category);

		model.setViewName(ModelUtils.REDIRECT_CATEGORY_LIST);
		return model;
	}

}
