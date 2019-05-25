package model;

import java.util.ArrayList;

public class User
{
	private String name, surname, username, password, birthday, id;
	private UserType type;
	private ArrayList<CreditCard> creditCards;
	private ArrayList<BillingAddress> billingAddresses;
	
	public User()
	{
		creditCards=new ArrayList<CreditCard>();
		billingAddresses=new ArrayList<BillingAddress>();
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getSurname()
	{
		return surname;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getBirthday()
	{
		return birthday;
	}
	
	public String getId()
	{
		return id;
	}
	
	public UserType getUserType()
	{
		return type;
	}
	
	public CreditCard getCreditCard(int index)
	{
		return creditCards.get(index);
	}
	
	public BillingAddress getBillingAddress(int index)
	{
		return billingAddresses.get(index);
	}
	
	public void setName(String aName)
	{
		name=aName;
	}
	
	public void setSurname(String aSurname)
	{
		surname=aSurname;
	}
	
	public void setUsername(String anUsername)
	{
		username=anUsername;
	}
	
	public void setPassword(String aPassword)
	{
		password=aPassword;
	}
	
	public void setBirthday(String aBirthday)
	{
		birthday=aBirthday;
	}
	
	public void setId(String anId)
	{
		id=anId;
	}
	
	public void setUserType(UserType aUserType)
	{
		type=aType;
	}
}
