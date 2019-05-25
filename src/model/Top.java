package model;

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
}
