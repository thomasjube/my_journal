package com.tjube.model;

import java.util.List;

import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

public class JPAUtils
{
	public static <T> T getSingleResult(TypedQuery<T> query)
	{
		List<T> results = query.getResultList();
		if (results.size() == 1)
			return results.get(0);

		if (results.isEmpty())
			return null;

		throw new NonUniqueResultException();
	}
}
