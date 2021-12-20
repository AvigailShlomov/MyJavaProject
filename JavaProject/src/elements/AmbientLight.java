package elements;

import java.awt.Color;

import primitives.Point3D;
import primitives.Vector;

public class AmbientLight extends Light
{
	private double Ka=0.1;
	
	//***************** Constructors ********************** //
	
	public AmbientLight(Color color, double ka)//
	{
		super(color);
		this.Ka = ka;
	}

	public AmbientLight(AmbientLight al)
	{
		super(al.getColor());
		this.Ka = al.Ka;
	}
	public AmbientLight()//empyt ctor
	{
		super();
		this.Ka = 0.1;
	}
	public AmbientLight(int r,int g, int b) 
	{
		super(r,g,b); 
	}	
	
	public AmbientLight(Light l) 
	{
		super(l); 
	}	
	
	// ***************** Getters/Setters ********************** //
	public double getKa() 
	{
		return Ka;
	}

	public void setKa(double ka) 
	{
		this.Ka = ka;
	}
	// ***************** Administration  ******************** // 
	
	@Override
	public String toString() {
		return "AmbientLight [Ka=" + Ka + "]";
	}

	@Override
	public boolean equals(Object obj)//check
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (obj instanceof AmbientLight)
			return false;
		AmbientLight other = (AmbientLight) obj;
		if (Double.doubleToLongBits(Ka) != Double.doubleToLongBits(other.Ka))
			return false;
		return true;
	}
                     //Abstract//
	@Override
	public Vector getL(Point3D point)//ש תפקידה להחזיר ווקטור שיוצא ממקור האור אל הנקודה שמקבלת כפרמטר פונקציה
	{
      Vector v=new Vector(point);
		return v;
	}
	@Override
	public Color getIntensity(Point3D point) //לחישוב צבע האור של האמביאנט
	{
	//	AmbientLight al= new AmbientLight(this);
		int red=(int)(this.getColor().getRed()*this.getKa());
		int green=(int)(this.getColor().getGreen()*this.getKa());
		int blue=(int)(this.getColor().getBlue()*this.getKa());
		//System.out.println("getintensity"+red+","+green+","+blue);
		if(red>255)
			red=255;
//		if(red<0)
//			red=0;

		if(green>255)
			green=255;
//		if(green<0)
//			green=0;

		if(blue>255)
			blue=255;
//		if(blue<0)
//			blue=0;

		Color tmp= new Color(red,green,blue);
		return tmp;
	}
	
	
}

