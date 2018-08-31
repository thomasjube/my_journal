package com.tjube.controller.app;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.tjube.model.WishList;
import com.tjube.service.BudgetService;
import com.tjube.service.CategoryService;
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

		model.setViewName(ModelUtils.REDIRECT_BUDGET_LIST);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------
	// BUDGET - LIST
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/list" }, method = { RequestMethod.GET })
	public ModelAndView budgetListHome(HttpServletRequest request, HttpServletResponse response, ModelAndView model)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();

		Collection<WishList> results = wishService.retrieveWishLists(account);

		model.addObject(ModelUtils.MODEL_ACCOUNT, account);
		model.addObject("wishLists", results);
		model.setViewName(ModelUtils.MODEL_BUDGET_LIST);

		return model;
	}

}
