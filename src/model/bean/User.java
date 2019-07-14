package model.bean;

public class User implements Cloneable
{
	private String name, surname, username, password, birthday, id;
	private UserType type;
	
	public User()
	{
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
	
	public String toString()
	{
		return getClass().getName() + "[name= " + name + ", surname= " + surname + ", username= " + username + ", password= " + password +
				", birthday= " + birthday + ", id= " + id + ", type= " + type + "]";
	}
	
	public boolean equals(Object obj)
	{
		if(obj==null)
			return false;
		if(getClass()!=obj.getClass())
			return false;
		
		User otherUser=(User) obj;
		
		return name.equals(otherUser.name) && surname.equals(otherUser.surname) && username.equals(otherUser.username) && 
				password.equals(otherUser.password) && birthday.equals(otherUser.birthday) && id.equals(otherUser.id) &&
				type==otherUser.type;
	}
	
	public User clone()
	{
		try
		{
			User clone=(User) super.clone();
			
			return clone;
		}
		catch(CloneNotSupportedException exception)
		{
			exception.printStackTrace();
		}
		
		return null;
	}
}
