package com.tjube.controller.app;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tjube.controller.app.form.LoginForm;
import com.tjube.controller.utils.LoginUtils;

@Controller
@RequestMapping(value = { "/login" })
public class LoginController
{

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "" }, method = { RequestMethod.GET })
	public ModelAndView journlaLoginGet(HttpServletRequest request, ModelAndView model)
	{
		model.setViewName("redirect:/home");
		String result = LoginUtils.login();
		if (result == null)
			return model;

		model.setViewName("login");
		model.addObject("loginForm", new LoginForm());
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "" }, method = { RequestMethod.POST })
	public ModelAndView journalLoginPost(HttpServletRequest request, ModelAndView model,
			@Valid @ModelAttribute("loginForm") LoginForm form)
	{
		model.setViewName("redirect:/home");
		String result = LoginUtils.login();
		if (result == null)
			return model;

		// TODO TROUVER ACCOUNT
		// SI ACCOUNT -> HOME
		// ELSE LOGIN

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

}
