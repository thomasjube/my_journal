package com.tjube.model.enums;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public enum TaskStateEvent
{
	TO_DO(true),
	DONE(true),
	POSTPONE(false);

	private boolean forDailyTask = false;

	private TaskStateEvent()
	{
		// Default constructor
	}

	private TaskStateEvent(boolean forDailyTask)
	{
		this.forDailyTask = forDailyTask;
	}

	public boolean isForDailyTask()
	{
		return forDailyTask;
	}

	public static Collection<TaskStateEvent> getStateForDailyTask()
	{
		List<TaskStateEvent> result = new ArrayList<>();

		for (TaskStateEvent taskStateEvent : TaskStateEvent.values())
		{
			if (taskStateEvent.isForDailyTask())
				result.add(taskStateEvent);
		}

		return result;
	}
}
