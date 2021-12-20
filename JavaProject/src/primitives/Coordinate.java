package primitives;

public class Coordinate
{
	private double coordinate;
	
	// ***************** Constructors ********************** // 
	public Coordinate()
	{
		this.coordinate = 0.0;
	}
	public Coordinate(double x)
	{
		this.coordinate = x;
	}
	public Coordinate(Coordinate x)
	{
		this.coordinate = x.coordinate;
	}
	// ***************** Getters/Setters ********************** // 
	
	public double getCoordinate() 
	{
		return coordinate;
	}

	public void setCoordinate(double x)
	{
		this.coordinate = x;
	}

	@Override
	public String toString() {
		return "Coordinate [coordinate=" + coordinate + "]";
	}
	
	public void add (Coordinate c)
	{
		this.coordinate +=c.getCoordinate();
		
	}
	
	public void subtract (Coordinate c)
	{
		this.coordinate-=c.getCoordinate();
		
	}
	public double mult(Coordinate c)
	{
		 return this.coordinate*c.coordinate;
		 
	}
	public double divide (double len)
	{
		return this.coordinate/len;
		
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (Double.doubleToLongBits(coordinate) != Double.doubleToLongBits(other.coordinate))
			return false;
		return true;
	}
	
}
