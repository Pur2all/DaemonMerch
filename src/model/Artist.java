package model;

import java.io.File;

public class Artist implements Cloneable
{
	private String id, name;
	private File logo;
	
	public Artist()
	{
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public File getLogo()
	{
		return logo;
	}
	
	public void setId(String anId)
	{
		id=anId;
	}
	
	public void setName(String aName)
	{
		name=aName;
	}
	
	public void setLogo(File aLogo)
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
		
		return id.equals(otherArtist.id) && name.equals(otherArtist.name) && logo.equals(otherArtist.logo);
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
