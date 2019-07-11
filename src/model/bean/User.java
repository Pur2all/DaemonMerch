package model.bean;

import java.util.ArrayList;

public class User implements Cloneable
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
		return creditCards.get(index).clone();
	}
	
	public BillingAddress getBillingAddress(int index)
	{
		return billingAddresses.get(index).clone();
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
		type=aUserType;
	}
	
	public int getNumberOfCreditCard()
	{
		return creditCards.size();
	}
	
	public void addCreditCard(CreditCard aCreditCard)
	{
		creditCards.add(aCreditCard.clone());
	}
	
	public void removeCreditCard(int index)
	{
		creditCards.remove(index);
	}
	
	public int getNumberOfBillingAddresses()
	{
		return billingAddresses.size();
	}
	
	public void addBillingAddress(BillingAddress aBillingAddress)
	{
		billingAddresses.add(aBillingAddress.clone());
	}
	
	public void setBillingAddress(int index, BillingAddress aBillingAddress)
	{
		billingAddresses.set(index, aBillingAddress.clone());
	}
	
	public void removeBillingAddress(int index)
	{
		billingAddresses.remove(index);
	}
	
	public String toString()
	{
		return getClass().getName() + "[name= " + name + ", surname= " + surname + ", username= " + username + ", password= " + password +
				", birthday= " + birthday + ", id= " + id + ", type= " + type + ", creditCards= " + creditCards + ", billingAddresses= " + 
				billingAddresses + "]";
	}
	
	public boolean equals(Object obj)
	{
		if(obj==null)
			return false;
		if(getClass()!=obj.getClass())
			return false;
		
		User otherUser=(User) obj;
		
		if(creditCards.size()!=otherUser.creditCards.size() || billingAddresses.size()!=otherUser.billingAddresses.size())
			return false;
		else
		{
			for(int i=0; i<creditCards.size(); i++)
				if(creditCards.get(i)!=otherUser.getCreditCard(i))
					return false;
			for(int i=0; i<billingAddresses.size(); i++)
				if(billingAddresses.get(i)!=otherUser.billingAddresses.get(i))
					return false;
		}
		
		return name.equals(otherUser.name) && surname.equals(otherUser.surname) && username.equals(otherUser.username) && 
				password.equals(otherUser.password) && birthday.equals(otherUser.birthday) && id.equals(otherUser.id) &&
				type==otherUser.type;
	}
	
	public User clone()
	{
		try
		{
			User clone=(User) super.clone();
			
			clone.creditCards=new ArrayList<CreditCard>();
			clone.billingAddresses=new ArrayList<BillingAddress>();
			for(int i=0; i<creditCards.size(); i++)
				clone.creditCards.add(i, creditCards.get(i).clone());
			for(int i=0; i<billingAddresses.size(); i++)
				clone.billingAddresses.add(i, billingAddresses.get(i).clone());
			
			return clone;
		}
		catch(CloneNotSupportedException exception)
		{
			exception.printStackTrace();
		}
		
		return null;
	}
}
