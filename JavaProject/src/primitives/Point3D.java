package primitives;

public class Point3D extends Point2D
{ 
	private Coordinate z;
	
	// ***************** Constructors ********************** // 
	public Point3D()
	{
		super();
		this.z = new Coordinate();
	}
	public Point3D(Coordinate x, Coordinate y, Coordinate z) 
	{
		super(x, y);
		this.z = new Coordinate(z);
	}
	public Point3D(Point3D p3) 
	{
		super(p3);
		this.z = new Coordinate(p3.z);
	}
	public Point3D(double x, double y, double z)
	{
		super(x, y);
		this.z = new Coordinate(z);
	}
	// ***************** Getters/Setters ********************** // 
	public Coordinate getZ() 
	{
		return z;
	}

	public void setZ(Coordinate z) 
	{
		this.z = new Coordinate(z);
	}
	// ***************** Administration  ******************** // 
	@Override
	public String toString() 
	{
		return super.toString()+" z=" + z + "";
	}

	public double distance (Point3D a)
	{
		double dis=Math.sqrt((Math.pow((this.getX().getCoordinate()-(a.getX().getCoordinate())),2))+Math.pow(this.getY().getCoordinate()-(a.getY().getCoordinate()),2)+Math.pow(this.getZ().getCoordinate()-(a.getZ().getCoordinate()),2));
		return dis;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (obj == null)
			return false;
		if (!(obj instanceof Point3D))
			return false;
		Point3D other = (Point3D) obj;
		if (z == null) 
		{
			if (other.z != null)
				return false;
		}
		else 
			if (this.z.getCoordinate() !=other.z.getCoordinate() && !super.equals(other))
				return false;
		return true;
	}
	public void add(Vector vector)//חיבור בין נקודה לוקטור
	{
		this.getX().add(vector.getHead().getX());
		this.getY().add(vector.getHead().getY());
		this.z.add(vector.getHead().getZ());
	}
	public Point3D addPoint(Point3D p)//חיבור בין נקודות
	{
		//hear we will NOT create a "copy" of this
		this.getX().add(p.getX());
		this.getY().add(p.getY());
		this.z.subtract(p.z);
		return this;
		
	}
	
	public void subtract(Vector vector) //חיסור בין נקודה לוקטור
	{
		this.getX().subtract(vector.getHead().getX());
		this.getY().subtract(vector.getHead().getY());
		this.z.subtract(vector.getHead().getZ());
	}
	
	public Point3D sub (Point3D p)//חיסור בין נקודות
	{
		Point3D copy = new Point3D(this);
		copy.x.subtract(p.getX());
		copy.y.subtract(p.getY());
		copy.z.subtract(p.z);
		return copy;
	}

}
