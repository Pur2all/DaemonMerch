package model;

public class Artist implements Cloneable
{
	private int id;
	private String name;
	private byte[] logo;
	
	public Artist()
	{
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public byte[] getLogo()
	{
		return logo;
	}
	
	public void setId(int anId)
	{
		id=anId;
	}
	
	public void setName(String aName)
	{
		name=aName;
	}
	
	public void setLogo(byte[] aLogo)
	{
		logo=aLogo;
	}
	
	public String toString()
	{
		return getClass().getName() + "[id= " + id + ", name= " + name + "]";
	}
	
	public boolean equals(Object obj)
	{
		if(obj==null)
			return false;
		if(getClass()!=obj.getClass())
			return false;
		
		Artist otherArtist=(Artist) obj;
		
		return id==otherArtist.id && name.equals(otherArtist.name) && logo.equals(otherArtist.logo);
	}
	
	public Artist clone()
	{
		try
		{
			Artist clone=(Artist) super.clone();
			
			return clone;
		}
		catch(CloneNotSupportedException exception)
		{
			exception.printStackTrace();
		}
		
		return null;
	}
}
