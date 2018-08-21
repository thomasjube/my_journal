package com.tjube.controller.app;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tjube.controller.app.form.LoginForm;
import com.tjube.controller.security.SecurityContext;
import com.tjube.controller.security.SessionAttributes;
import com.tjube.controller.utils.LoginUtils;
import com.tjube.controller.utils.ModelUtils;
import com.tjube.model.Account;
import com.tjube.service.AccountService;

@Controller
@RequestMapping(value = { "/login" })
public class LoginController
{
	@Autowired
	AccountService accountService = null;

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "" }, method = { RequestMethod.GET })
	public ModelAndView journlaLoginGet(HttpServletRequest request, ModelAndView model)
	{
		model.setViewName(ModelUtils.REDIRECT_HOME);
		String result = LoginUtils.login();
		if (result == null)
			return model;

		model.setViewName(ModelUtils.MODEL_LOGIN);
		model.addObject("loginForm", new LoginForm());
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "" }, method = { RequestMethod.POST })
	public ModelAndView journalLoginPost(HttpServletRequest request, ModelAndView model,
			@Valid @ModelAttribute("loginForm") LoginForm form, BindingResult validationResult)
	{
		model.setViewName(ModelUtils.REDIRECT_HOME);
		String result = LoginUtils.login();
		if (result == null)
			return model;

		if (validationResult.hasErrors())
		{
			model.setViewName(ModelUtils.MODEL_LOGIN);
			return model;
		}

		Account account = accountService.retrieveAccount(form.getEmail(), form.getPassword());
		if (account == null)
		{
			model.setViewName(ModelUtils.MODEL_LOGIN);
			model.addObject("accountNotFound", true);
			return model;
		}

		SecurityContext.getInstance().setCurrentUser(account);
		SessionAttributes.setLoggedAccount(request, account);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/logout" }, method = { RequestMethod.GET })
	public ModelAndView journlaLoginLogoutGet(HttpServletRequest request, ModelAndView model)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		SecurityContext.cleanup();
		SessionAttributes.setLoggedAccount(request, null);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

}
