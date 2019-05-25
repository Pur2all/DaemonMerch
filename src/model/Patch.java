package model;

public class Patch extends Product
{
	private String material, measures;
	private PatchType type;
	
	public String getMaterial()
	{
		return material;
	}
	
	public String getMeasures()
	{
		return measures;
	}
	
	public PatchType getPatchType()
	{
		return type;
	}
	
	public void setMaterial(String aMaterial)
	{
		material=aMaterial;
	}
	
	public void setMeasures(String aMeasures)
	{
		measures=aMeasures;
	}
	
	public void setPatchType(PatchType aPatchType)
	{
		type=aPatchType;
	}
}
