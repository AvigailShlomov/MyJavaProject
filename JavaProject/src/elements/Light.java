package elements;

import java.awt.Color;

//import Elements.Light;
import primitives.Point3D;
import primitives.Vector;

public abstract class Light 
{
	private Color color;
	
	//***************** Constructors ********************** // 
	public Light(Color color)//c-tor for the color
	{
		super();
		if(color!=null)
			this.color = color;
			else
				color=new Color(255, 255, 255);
		
	}
	public Light(int r, int g, int b)//regular constructor
	{
		super();
		this.color=new Color(r, g, b);
	}
	public Light ()//empty c-tor
	{
		super();
		this.color = new Color(255,255,255);
	}
	public Light(Light lt)//copy c-tor
	{
	//	super();
		//this.color = lt.color;
		this.color = new Color (lt.color.getRed(),lt.color.getGreen(),lt.color.getBlue());
	}
	
	// ***************** Getters/Setters ********************** //
	public Color getColor()
	{
		if(color!=null)
			return  new Color(color.getRGB());
			else
				return this.color=new Color(255, 255, 255);
	}
	public void setColor(Color colorr) 
	{
		this.color = colorr;
	} 

	// ***************** Administration  ******************** // 

	@Override
	public String toString() 
	{
		return "Light [color=" + color + "]";
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof Light)
			return false;
		Light other = (Light) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		return true;
	}
	//Abstract//
	public abstract Vector getL(Point3D point);//ש תפקידה להחזיר ווקטור שיוצא ממקור האור אל הנקודה שמקבלת כפרמטר פונקציה
	public abstract Color getIntensity(Point3D point);//לחישוב צבע האור של הפוניט
	
}

