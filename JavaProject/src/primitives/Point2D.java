package primitives;

public class Point2D
{
	protected Coordinate x;
	protected Coordinate y;
	// ***************** Constructors ********************** // 
	public Point2D()
	{
		this.x = new Coordinate();
		this.y = new Coordinate();
	}
	public Point2D(Coordinate x, Coordinate y) 
	{
		this.x = new Coordinate(x);
		this.y = new Coordinate(y);
	}
	public Point2D(double x, double y) 
	{
		this.x = new Coordinate(x);
		this.y = new Coordinate(y);
	}
	public Point2D(Point2D p) 
	{
		this.x = new Coordinate(p.x);
		this.y = new Coordinate(p.y);
	}
	
	// ***************** Getters/Setters ********************** // 
	public Coordinate getX() 
	{
		return x;
	}
	public void setX(Coordinate x) 
	{
		this.x = new Coordinate(x);
	}
	public Coordinate getY() 
	{
		return y;
	}
	public void setY(Coordinate y) 
	{
		this.y = new Coordinate(y);
	}
	// ***************** Administration  ******************** // 
	@Override
	public String toString() 
	{
		return "x=" + x + " y=" + y + "";
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (obj == null)
			return false;
		if (!(obj instanceof Point2D))
			return false;
		Point2D other = (Point2D) obj;
		if(this.getX().getCoordinate() == other.getX().getCoordinate() &&
				this.getY().getCoordinate() == other.getY().getCoordinate())
			return true;
		return false;
	}
}
