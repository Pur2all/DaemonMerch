package model.bean;

public class Top extends Product
{
	private Size size;
	private Category category;
	private PrintType type;
	
	public Size getSize()
	{
		return size;
	}
	
	public Category getCategory()
	{
		return category;
	}
	
	public PrintType getPrintType()
	{
		return type;
	}
	
	public void setSize(Size aSize)
	{
		size=aSize;
	}
	
	public void setCategory(Category aCategory)
	{
		category=aCategory;
	}
	
	public void setPrintType(PrintType aPrintType)
	{
		type=aPrintType;
	}
	
	public String toString()
	{
		return super.toString() + "[size= " + size + ", category= " + category + ", type= " + type + "]";
	}
	
	public boolean equals(Object obj)
	{
		if(!super.equals(obj))
			return false;
		
		Top otherTop=(Top) obj;
		
		return size==otherTop.size && category==otherTop.category && type==otherTop.type;
	}
	
	public Top clone()
	{
		return (Top) super.clone();
	}
}
