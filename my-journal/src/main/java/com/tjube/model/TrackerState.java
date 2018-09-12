package com.tjube.model;

import java.awt.Color;
import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.tjube.controller.utils.converter.UUIDAttributeConverter;

@NamedQueries({
		@NamedQuery(name = TrackerState.QN.RETRIEVE_TRACKER_STATE_WITH_UUID,
				query = "SELECT ts from TrackerState ts where ts.uuid=:uuid"),
		@NamedQuery(name = TrackerState.QN.RETRIEVE_TRACKER_STATE_WITH_TRACKER,
				query = "SELECT ts from TrackerState ts where ts.tracker=:tracker"), })
@Entity
@Table(name = "TRACKER_STATE")
public class TrackerState
	implements Serializable
{

	private static final long serialVersionUID = -5480865053070705207L;

	//==================================================================================================================================================================================================
	//
	// Query names
	//
	//==================================================================================================================================================================================================

	public static class QN
	{
		public static final String RETRIEVE_TRACKER_STATE_WITH_UUID = "TrackerState.retrieveTrackerStateWithUuid";
		public static final String RETRIEVE_TRACKER_STATE_WITH_TRACKER = "TrackerState.retrieveTrackerStateWithTracker";

		private QN()
		{

		}
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Convert(converter = UUIDAttributeConverter.class)
	@Column(name = "uuid", unique = true, nullable = false)
	private UUID uuid;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "COLOR", nullable = false)
	private String color = null;

	@ManyToOne(fetch = FetchType.LAZY)
	private Tracker tracker;

	//---------------------------------------------------------------------------------------------------------------------

	public TrackerState()
	{
		// Default constructor
	}

	public TrackerState(Tracker tracker, String name, String color)
	{
		super();
		this.uuid = UUID.randomUUID();
		this.name = name;
		this.tracker = tracker;
		this.color = color;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public Tracker getTracker()
	{
		return tracker;
	}

	public void setTracker(Tracker tracker)
	{
		this.tracker = tracker;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public String getColorText()
	{
		return changeColorText(this.color);
	}

	//---------------------------------------------------------------------------------------------------------------------

	private String changeColorText(String color)
	{
		Color c = Color.decode(color);
		double brightness = Math.sqrt(c.getRed() * c.getRed() * 0.241 + c.getGreen() * c.getGreen() * 0.691
				+ c.getBlue() * c.getBlue() * 0.068);
		if (brightness < 130)
			return "#fff";
		else
			return "#000";
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Override
	public int hashCode()
	{
		return new Long(getId()).hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;

		if (!(obj instanceof TrackerState))
			return false;

		return getId() == ((TrackerState) obj).getId();
	}
}
