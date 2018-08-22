package com.tjube.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tjube.controller.utils.converter.UUIDAttributeConverter;

@NamedQueries({
		@NamedQuery(name = WishList.QN.RETRIEVE_WISH_LIST_WITH_UUID,
				query = "SELECT list from WishList list where list.uuid=:uuid"),
		@NamedQuery(name = WishList.QN.RETRIEVE_WISH_LISTS_BY_ACCOUNT,
				query = "SELECT list from WishList list where list.account=:account") })
@Entity
@Table(name = "WISH_LIST")
public class WishList
	implements Serializable
{

	private static final long serialVersionUID = 3030092420918865630L;

	//---------------------------------------------------------------------------------------------------------------------

	public static class QN
	{
		public static final String RETRIEVE_WISH_LIST_WITH_UUID = "WishList.retrieveWishListWithUuid";
		public static final String RETRIEVE_WISH_LISTS_BY_ACCOUNT = "WishList.retrieveWishListsByAccount";

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

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Account account = null;

	@Column(name = "description", nullable = false)
	private String description;

	@OneToMany(mappedBy = "wishList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Wish> wishes = new ArrayList<>();

	//---------------------------------------------------------------------------------------------------------------------

	public WishList()
	{
		// Default Constructor
	}

	public WishList(Account account, String description)
	{
		this.uuid = UUID.randomUUID();

		this.account = account;
		this.description = description;
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

	public Account getAccount()
	{
		return account;
	}

	public void setAccount(Account account)
	{
		this.account = account;
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

	public Collection<Wish> getWishes()
	{
		return new ArrayList<>(wishes);
	}

	public void addWish(Wish wish)
	{
		wishes.add(wish);
	}

	public void removeWish(Wish wish)
	{
		wishes.remove(wish);
	}

	public void clearWishes()
	{
		wishes.clear();
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

		if (!(obj instanceof WishList))
			return false;

		return getId() == ((WishList) obj).getId();
	}

	//---------------------------------------------------------------------------------------------------------------------

}