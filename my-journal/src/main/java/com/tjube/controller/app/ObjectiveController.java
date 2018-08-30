package com.tjube.controller.app;

import java.util.Collection;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tjube.controller.app.form.ObjectiveCreationForm;
import com.tjube.controller.app.json.ObjectiveChangeStateJSON;
import com.tjube.controller.security.SecurityContext;
import com.tjube.controller.utils.LoginUtils;
import com.tjube.controller.utils.ModelUtils;
import com.tjube.model.Account;
import com.tjube.model.Objective;
import com.tjube.model.enums.TaskStateEvent;
import com.tjube.service.ObjectiveService;

@RestController
@Controller
@RequestMapping("/objective")
@ComponentScan("com.tjube.service")
@Transactional
@Configuration
public class ObjectiveController
{

	@Autowired
	private ObjectiveService objectiveService;

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "", "/" }, method = { RequestMethod.GET })
	public ModelAndView journalIndex(ModelAndView model)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();

		Collection<Objective> objectives = objectiveService.retrieveObjectives(account);
		model.addObject("objectives", objectives);

		Collection<Objective> masterObjectives = objectiveService.retrieveMasterObjectives(account);
		model.addObject("masterObjectives", masterObjectives);

		model.setViewName(ModelUtils.MODEL_OBJECTIVE_LIST);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/creation", method = { RequestMethod.GET })
	public ModelAndView journalCreationGet(ModelAndView model)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject("account", account);

		Collection<Objective> masterObjectives = objectiveService.retrieveMasterObjectives(account);
		model.addObject("masterObjectives", masterObjectives);

		model.addObject("form", new ObjectiveCreationForm());
		model.setViewName(ModelUtils.MODEL_OBJECTIVE_CREATION);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/creation" }, method = { RequestMethod.POST })
	public ModelAndView journalCreationPost(HttpServletRequest request, ModelAndView model,
			@Valid @ModelAttribute("form") ObjectiveCreationForm form, BindingResult validationResult)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		if (validationResult.hasErrors())
		{
			model.setViewName(ModelUtils.MODEL_OBJECTIVE_CREATION);
			return model;
		}

		Account account = SecurityContext.getInstance().getCurrentUser();
		if (account == null)
		{
			model.setViewName(ModelUtils.MODEL_LOGIN);
			model.addObject("accountNotFound", true);
			return model;
		}

		Objective masterObjective = null;
		if (form.getMasterObjectiveUuid() != null)
			masterObjective = objectiveService.retrieveObjective(form.getMasterObjectiveUuid());

		objectiveService.createObjective(account, form.getName(), form.getDescription(), masterObjective);

		model.setViewName(ModelUtils.REDIRECT_OBJECTIVE);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/update", method = { RequestMethod.GET })
	public ModelAndView objectiveUpdateGet(ModelAndView model, @RequestParam("uuid") UUID objectiveUuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject("account", account);

		Collection<Objective> masterObjectives = objectiveService.retrieveMasterObjectives(account);
		model.addObject("masterObjectives", masterObjectives);

		Objective objective = objectiveService.retrieveObjective(objectiveUuid);
		if (objective == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_OBJECTIVE);
		}

		model.addObject("form", new ObjectiveCreationForm(objective));
		model.setViewName(ModelUtils.MODEL_OBJECTIVE_UPDATE);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = { "/update" }, method = { RequestMethod.POST })
	public ModelAndView objectiveUpdatePost(HttpServletRequest request, ModelAndView model,
			@Valid @ModelAttribute("form") ObjectiveCreationForm form, BindingResult validationResult)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		if (validationResult.hasErrors())
		{
			model.setViewName(ModelUtils.MODEL_OBJECTIVE_UPDATE);
			return model;
		}

		Account account = SecurityContext.getInstance().getCurrentUser();
		if (account == null)
		{
			model.setViewName(ModelUtils.MODEL_LOGIN);
			model.addObject("accountNotFound", true);
			return model;
		}

		Objective masterObjective = null;
		if (form.getMasterObjectiveUuid() != null)
			masterObjective = objectiveService.retrieveObjective(form.getMasterObjectiveUuid());

		Objective objective = objectiveService.retrieveObjective(form.getObjectiveUuid());
		if (objective == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_OBJECTIVE);
			return model;
		}

		objectiveService.updateObjective(objective, form.getName(), form.getDescription(), masterObjective);

		model.setViewName(ModelUtils.REDIRECT_OBJECTIVE);

		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/delete", method = { RequestMethod.GET })
	public ModelAndView objectiveDeleteGet(ModelAndView model, @RequestParam("uuid") UUID objectiveUuid)
	{
		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		String result = LoginUtils.login();
		if (result != null)
			return model;

		Account account = SecurityContext.getInstance().getCurrentUser();
		model.addObject("account", account);

		Objective objective = objectiveService.retrieveObjective(objectiveUuid);
		if (objective == null)
		{
			model.clear();
			model.setViewName(ModelUtils.REDIRECT_OBJECTIVE);
		}

		objectiveService.removeObjective(objective);

		model.setViewName(ModelUtils.REDIRECT_OBJECTIVE);
		return model;
	}

	//---------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/updateState", method = { RequestMethod.POST }, consumes = "application/json")
	public void objectiveStatePatch(HttpServletRequest request, HttpServletResponse response,
			@RequestBody ObjectiveChangeStateJSON form, @RequestParam("uuid") UUID objectiveUuid)
	{
		//		model.setViewName(ModelUtils.REDIRECT_LOGIN);
		//		String result = LoginUtils.login();
		//		if (result != null)
		//			return model;
		//
		//		Account account = SecurityContext.getInstance().getCurrentUser();
		//		model.addObject("account", account);
		//
		Objective objective = objectiveService.retrieveObjective(objectiveUuid);
		if (objective == null)
		{
			return;
		}

		objectiveService.updateState(objective, TaskStateEvent.valueOf(form.getState()));

		//		model.setViewName(ModelUtils.REDIRECT_OBJECTIVE);
		//		return model;
	}
}
