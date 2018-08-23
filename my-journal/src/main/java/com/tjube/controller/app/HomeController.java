package com.tjube.controller.app;

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
import com.tjube.model.Journal;
import com.tjube.service.JournalService;

@Controller
@RequestMapping("/")
@ComponentScan("com.tjube.service")
@Transactional
@Configuration
public class HomeController
{

	@Autowired
	private JournalService journalService;

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

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject("account", account);

		Journal journal = journalService.retrieveCurrentJournal(account);
		if (journal != null)
		{
			model.addObject("journal", journal);
			model.setViewName(ModelUtils.MODEL_HOME);
		}
		else
			model.setViewName(ModelUtils.REDIRECT_JOURNAL);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

}
