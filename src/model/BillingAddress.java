package model;

public class BillingAddress
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
}
