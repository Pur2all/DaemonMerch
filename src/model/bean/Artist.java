package model.bean;

public class Artist implements Cloneable
{
	private int id;
	private String name;
	private Image logo;
	private Image[] images;

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

	public Image getLogo()
	{
		return logo;
	}

	public Image[] getImages()
	{
		return images;
	}

	public void setId(int anId)
	{
		id=anId;
	}

	public void setName(String aName)
	{
		name=aName;
	}

	public void setLogo(Image aLogo)
	{
		logo=aLogo;
	}

	public void setImages(Image[] someImages)
	{
		images=someImages;
	}

	public String toString()
	{
		return getClass().getName() + "[id= " + id + ", name= " + name + ", logo=" + logo + "]";
	}

	public boolean equals(Object obj)
	{
		if(obj==null)
			return false;
		if(getClass()!=obj.getClass())
			return false;

		Artist otherArtist=(Artist) obj;

		return id==otherArtist.id && name.equals(otherArtist.name) && logo.equals(otherArtist.logo) && images.equals(otherArtist.images);
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
