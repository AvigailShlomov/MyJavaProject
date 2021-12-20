package elements;

import java.awt.Color;

import primitives.Point3D;
import primitives.Vector;

public class PointLight  extends Light
{
	private Point3D position;
	private double kc;//מקדמי דעיכה
	private double kl;
	private double kq;
	//***************** Constructors ********************** //
	public PointLight(Color color, Point3D position, double kc, double kl, double kq) //regular constructor
	{
		super(color);
		this.setPosition(new Point3D(position)); 
		this.setKc(kc);
		this.setKl(kl); 
		this.setKq(kq);
	}
	public PointLight(PointLight pl)//copy c-tor
	{
		super(pl.getColor());
		this.position = new Point3D(pl.getPosition());
		this.kc = pl.kc;
		this.kl = pl.kl;
		this.kq = pl.kq;
	}
	public PointLight() //empty c-tor
	{
		super(225,225,225);
		this.position = new Point3D();
		this.kc = 1;
		this.kl = 1;
		this.kq = 1;
	}
	public PointLight(Light l) 
	{
		super(l);
		this.position = new Point3D();
		kc = 1;
		kl = 1;
		kq = 1;
	}

	public PointLight(int r, int g, int b) 
	{
		super(r, g, b);
		this.position = new Point3D();
		kc = 1;
		kl = 1;
		kq = 1;
	}
	// ***************** Getters/Setters ********************** //

	public Point3D getPosition()
	{
		return new Point3D(position);
	}
	public void setPosition(Point3D positionn)
	{
		this.position =  new Point3D(positionn);
	}
	public double getKc() {
		return kc;
	}
	public void setKc(double Kc)
	{
		this.kc = Kc;
	}
	public double getKl() {
		return kl;
	}
	public void setKl(double Kl)
	{
		this.kl = Kl;
	}
	public double getKq() {
		return kq;
	}
	public void setKq(double Kq) 
	{
		this.kq = Kq;
	}
	// ***************** Administration  ******************** // 

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (obj instanceof PointLight )
			return false;
		PointLight other = (PointLight) obj;
		if (Double.doubleToLongBits(kc) != Double.doubleToLongBits(other.kc))
			return false;
		if (Double.doubleToLongBits(kl) != Double.doubleToLongBits(other.kl))
			return false;
		if (Double.doubleToLongBits(kq) != Double.doubleToLongBits(other.kq))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}
	
	@Override
	public String toString() 
	{
		return "pointLight [position=" + position + ", kc=" + kc + ", kl=" + kl + ", kq=" + kq + "]";
	}
	
	 //Abstract//
	@Override
	public Vector getL(Point3D point) //שתפקידה להחזיר ווקטור שיוצא ממקור האור אל הנקודה שמקבלת כפרמטר פונקציה
	{
		Vector vector= new Vector(position,point);
		vector.normalize();// is this needed
		return vector;
	}


	@Override
	public Color getIntensity(Point3D point)//לחישוב צבע האור של הפוניט
	{
		Point3D temp=new Point3D(this.position);//is this a problem
		double d=temp.distance(point);
		double y=(double)(1/(this.kc+this.kl*d+this.kq*d*d));//עם זה נחלק כל צבע בצבע שלנו הנתון
		if(y>1)
			y=1;
		
		int red=(int)(this.getColor().getRed()*y);
		//if(red>255)
			//red=255;
		
		int green=(int) (this.getColor().getGreen()*y);
		//if(green>255)
			//green=255;
		
		int blue=(int)(this.getColor().getBlue()*y);
		//if(blue>255)
			//blue=255;
//System.out.println("point light intensuty"+red+","+green+","+blue);
		
		return  new Color(red,green,blue);
	}





}
