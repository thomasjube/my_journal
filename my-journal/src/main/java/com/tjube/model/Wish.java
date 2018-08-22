package com.tjube.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.tjube.model.enums.TaskStateEvent;

@NamedQueries({
		@NamedQuery(name = Wish.QN.RETRIEVE_WISH_WITH_UUID, query = "SELECT item from Wish item where item.uuid=:uuid"),
		@NamedQuery(name = Wish.QN.RETRIEVE_WISHES_BY_LIST,
				query = "SELECT item from Wish item where item.wishList=:wishList") })
@Entity
@Table(name = "WISH")
public class Wish
	implements Serializable
{

	private static final long serialVersionUID = -405796880322170868L;

	//---------------------------------------------------------------------------------------------------------------------

	public static class QN
	{
		public static final String RETRIEVE_WISH_WITH_UUID = "Wish.retrieveWishByUuid";
		public static final String RETRIEVE_WISHES_BY_LIST = "Wish.retrieveWishesByList";

		private QN()
		{

		}
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "uuid", unique = true)
	private UUID uuid;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private CategoryTask category = null;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private WishList wishList = null;

	@Column(name = "description", nullable = false)
	private String description = null;

	@Column(name = "price", nullable = false, scale = 2)
	private BigDecimal price;

	@Enumerated(EnumType.STRING)
	@Column(name = "state", nullable = false)
	private TaskStateEvent state = TaskStateEvent.TO_DO;

	//---------------------------------------------------------------------------------------------------------------------

	public Wish()
	{
		// Default Constructor
	}

	public Wish(WishList wishList, String description, BigDecimal price, CategoryTask categoryTask)
	{
		this.uuid = UUID.randomUUID();

		this.wishList = wishList;
		this.description = description;
		this.price = price;
		this.category = categoryTask;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public int getId()
	{
		return id;
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

	public WishList getWishList()
	{
		return wishList;
	}

	public void setWishList(WishList wishList)
	{
		this.wishList = wishList;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public BigDecimal getPrice()
	{
		return price;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public TaskStateEvent getState()
	{
		return state;
	}

	public void setState(TaskStateEvent state)
	{
		this.state = state;
	}

	//---------------------------------------------------------------------------------------------------------------------

	public CategoryTask getCategory()
	{
		return category;
	}

	public void setCategory(CategoryTask category)
	{
		this.category = category;
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

		if (!(obj instanceof Wish))
			return false;

		return getId() == ((Wish) obj).getId();
	}

	//---------------------------------------------------------------------------------------------------------------------

}