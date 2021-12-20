package primitives;

//import Primitives.Point3D;
//import Primitives.Vector;

public class Vector 
{
private Point3D head;
	
	// ***************** Constructors ********************** // 
	public Vector(Point3D head) 
	{
		this.head = new Point3D(head);
	}
	public Vector(double a,double b,double c) 
	{
		this.head=new Point3D(a,b,c);
	}
	
	public Vector(Vector v) 
	{
		this.head = new Point3D(v.head);
	}
	public Vector()//empty_ctor
	{
		this.head = new Point3D();
	}
	
	 public Vector(Point3D p1, Point3D p2) 
	    {
	        Point3D newP=new Point3D(p1);
	        newP.subtract(new Vector(p2));
	        this.head = new Point3D(newP);
	    }
	    
	// ***************** Getters/Setters ********************** // 
	public Point3D getHead() 
	{
		return head;
	}

	public void setHead(Point3D head)
	{
		
		this.head = new Point3D(head);
	}
	// ***************** Administration  ******************** // 
	@Override
	public String toString()
	{
		return "Vector " + head.toString();
	}
	@Override
	public boolean equals(Object obj) 
	{
		//if (this == obj)
			//return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector))//important
			return false;
		Vector other = (Vector) obj;
		if (head == null) 
		{
			if (other.head != null)
				return false;
		} 
		else 
			if (!head.equals(other.head))
			return false;
		return true;
	}
	public void add (Vector vector )
	{
		this.getHead().add(vector);	
	}
	
	public void subtract (Vector vector )
	{
		this.getHead().subtract(vector);	
	}
	
	public void scale(double scalingFactor)//מכפלת הוקטור עם סקלר
	{
		this.getHead().getX().setCoordinate(getHead().getX().getCoordinate()*scalingFactor);
		this.getHead().getY().setCoordinate(getHead().getY().getCoordinate()*scalingFactor);
		this.getHead().getZ().setCoordinate(getHead().getZ().getCoordinate()*scalingFactor);
	}
	
	public double length()
	{
		return Math.sqrt((Math.pow(this.getHead().getX().getCoordinate(), 2))+(Math.pow(this.getHead().getY().getCoordinate() ,2)+(Math.pow(this.getHead().getZ().getCoordinate(),2))));
	}

	public void normalize()//נירמול
	{
		double len= this.length();
		if(len == 0)
		return;
		Coordinate x= new Coordinate(this.head.getX().getCoordinate()/len);
		this.head.setX(x);
		Coordinate y=new Coordinate((this.head.getY().getCoordinate())/len);
		 this.head.setY(y);
		 Coordinate z=new Coordinate((this.head.getZ().getCoordinate())/len);
		 this.head.setZ(z);	
		
	}

	public Vector crossProduct (Vector vec)//מכפלה סקלרית בין וקטורים מחזיר וקטור
	{
		Vector v=new Vector(vec);
		v.head.setX(new Coordinate(vec.head.getY().mult(this.head.getZ())-(vec.head.getZ().mult(this.head.getY()))));
		v.head.setY(new Coordinate((this.head.getX().mult(vec.head.getZ()))-(vec.head.getX().mult(this.head.getZ()))));
		v.head.setZ(new Coordinate(vec.head.getX().mult(this.head.getY())-(vec.head.getY().mult(this.head.getX()))));
		return v;
	}
	public double dotProduct(Vector vec)//מכפלה בין וקטורים מחזיר את ערך המכפלה שלהם בדאבל
	{
		return this.head.getX().mult(vec.head.getX())+this.head.getY().mult(vec.head.getY())+this.head.getZ().mult(vec.head.getZ());
	}
}
