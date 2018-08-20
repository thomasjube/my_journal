package com.tjube.controller.app;

import javax.transaction.Transactional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tjube.controller.utils.LoginUtils;

@Controller
@RequestMapping("/")
@ComponentScan("com.tjube.service")
@Transactional
@Configuration
public class HomeController
{
	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "" }, method = { RequestMethod.GET })
	public String journalIndex()
	{
		String result = LoginUtils.login();
		if (result != null)
			return result;

		return "redirect:/home";
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "home", method = { RequestMethod.GET })
	public ModelAndView journalHome(ModelAndView model)
	{
		model.setViewName("redirect:/login");
		String result = LoginUtils.login();
		if (result != null)
			return model;

		model.setViewName("home");
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

}
