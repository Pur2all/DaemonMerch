package model;

import java.io.File;

public class Artist
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
}
