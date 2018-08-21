package com.tjube.controller.app;

import javax.transaction.Transactional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tjube.controller.utils.LoginUtils;
import com.tjube.controller.utils.ModelUtils;

@Controller
@RequestMapping("/")
@ComponentScan("com.tjube.service")
@Transactional
@Configuration
public class HomeController
{
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

		model.setViewName(ModelUtils.MODEL_HOME);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

}
