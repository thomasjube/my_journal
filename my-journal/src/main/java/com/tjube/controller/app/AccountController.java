package com.tjube.controller.app;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tjube.controller.app.form.AccountCreationForm;
import com.tjube.controller.security.SecurityContext;
import com.tjube.controller.utils.LoginUtils;
import com.tjube.controller.utils.ModelUtils;
import com.tjube.model.Account;
import com.tjube.model.DuplicatingAccountEmailException;
import com.tjube.service.AccountService;

@Controller
@RequestMapping(value = { "/account" })
public class AccountController
{
	@Autowired
	AccountService accountService = null;

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "", "/" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView journalAccountBase(HttpServletRequest request, ModelAndView model)
	{
		model.setViewName(ModelUtils.REDIRECT_HOME);
		String result = LoginUtils.login();
		if (result == null)
			return model;

		model.setViewName(ModelUtils.MODEL_LOGIN);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/creation" }, method = { RequestMethod.GET })
	public ModelAndView journalAccountCreationGet(HttpServletRequest request, ModelAndView model)
	{
		model.setViewName(ModelUtils.REDIRECT_HOME);
		String result = LoginUtils.login();
		if (result == null)
			return model;

		model.setViewName(ModelUtils.MODEL_CREATION);
		model.addObject("creationForm", new AccountCreationForm());

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/creation" }, method = { RequestMethod.POST })
	public ModelAndView journalAccountPost(HttpServletRequest request, ModelAndView model,
			@Valid @ModelAttribute("creationForm") AccountCreationForm form, BindingResult validationResult)
	{
		model.setViewName(ModelUtils.REDIRECT_HOME);
		String result = LoginUtils.login();
		if (result == null)
			return model;

		if (validationResult.hasErrors())
		{
			model.setViewName(ModelUtils.MODEL_CREATION);
			return model;
		}

		Account account = null;
		LocalDate birthDate = form.getBirthDate() != null ? LocalDate.parse(form.getBirthDate()) : null;
		try
		{
			account = accountService.createAccount(form.getEmail(), form.getPassword(), form.getFirstName(),
					form.getLastName(), form.getAlias(), birthDate);
		}
		catch (DuplicatingAccountEmailException e)
		{
			model.setViewName(ModelUtils.MODEL_CREATION);
			model.addObject("accountEmailAlreadyExist", true);
			return model;
		}

		SecurityContext.setup();
		SecurityContext.getInstance().setCurrentUser(account);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

}
