package primitives;

public class Ray 
{
	private Point3D poo; 
	private Vector direction;
	
	// ***************** Constructors ********************** // 
	public Ray(Point3D poo, Vector direction) 
	{
		this.poo = new Point3D(poo);
		this.direction = new Vector(direction);
		this.direction.normalize();
	}
	public Ray(Ray r) 
	{
		this.poo =  new Point3D(r.poo);
		this.direction = new Vector(r.direction);
		this.direction.normalize();
	}
	public Ray()
	{
		this.poo = new Point3D();
		this.direction = new Vector();
		this.direction.normalize();
	}
	// ***************** Getters/Setters ********************** // 
	public Point3D getPoo() 
	{
		return  new Point3D(poo);
	}
	public void setPoo(Point3D poo)
	{
		this.poo = new Point3D(poo);
	}
	public Vector getDirection() 
	{
		return new Vector(direction);
	} 
	public void setDirection(Vector direction) 
	{
		this.direction = new Vector(direction);
		this.direction.normalize();
	}
	// ***************** Administration  ******************** // 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		if (poo == null) {
			if (other.poo != null)
				return false;
		} else if (!poo.equals(other.poo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Ray [poo=" + poo + ", direction=" + direction + "]";
	}
	
}
