package model.bean;

public class BillingAddress implements Cloneable
{
	private String state, street, city, district, houseNumber;
	
	public BillingAddress()
	{
	}
	
	public String getState()
	{
		return state;
	}
	
	public String getStreet()
	{
		return street;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public String getDistrict()
	{
		return district;
	}
	
	public String getHouseNumber()
	{
		return houseNumber;
	}
	
	public void setState(String aState)
	{
		state=aState;
	}
	
	public void setStreet(String aStreet)
	{
		street=aStreet;
	}
	
	public void setCity(String aCity)
	{
		city=aCity;
	}
	
	public void setDistrict(String aDistrict)
	{
		district=aDistrict;
	}
	
	public void setHouseNumber(String anHouseNumber)
	{
		houseNumber=anHouseNumber;
	}
	
	public String getInfo()
	{
		return state + ", " + district + "\n" + street + " " + houseNumber + ", " + city;
	}
	
	public String toString()
	{
		return getClass().getName() + "[state= " + state + ", street=" + street + ", city=" + city + ", district= " 
				+ district + ", houseNumber= " + houseNumber + "]";
	}
	
	public boolean equals(Object obj)
	{
		if(obj==null)
			return false;
		if(getClass()!=obj.getClass())
			return false;
		
		BillingAddress otherAddress=(BillingAddress) obj;
		
		return state.equals(otherAddress.state) && street.equals(otherAddress.street) && city.equals(otherAddress.city) &&
				district.equals(otherAddress.district) && houseNumber.equals(otherAddress.houseNumber);
	}
	
	public BillingAddress clone()
	{
		try
		{
			BillingAddress clone=(BillingAddress) super.clone();
			
			return clone;
		}
		catch(CloneNotSupportedException exception)
		{
			exception.printStackTrace();
		}
		
		return null;
	}
}
