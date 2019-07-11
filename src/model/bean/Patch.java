package model.bean;

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
	
	public String toString()
	{
		return super.toString() + "[material= " + material + ", measures= " + measures + ", type= " + type + "]";
	}
	
	public boolean equals(Object obj)
	{
		if(!super.equals(obj))
			return false;
		
		Patch otherPatch=(Patch) obj;
		
		return material.equals(otherPatch.material) && measures.equals(otherPatch.measures) && type==otherPatch.type;
	}
	
	public Patch clone()
	{
		return (Patch) super.clone();
	}
}
