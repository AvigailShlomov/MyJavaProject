package elements;

import java.awt.Color;
import primitives.Point3D;
import primitives.Vector;

public class SpotLight  extends PointLight
{
	 private Vector direction;
//	//***************** Constructors ********************** //
public SpotLight(Color color, Point3D position, double kc, double kl, double kq,Vector direction )
{
	super(color, position, kc, kl, kq);
	this.direction = new Vector(direction);
	this.direction.normalize();
}
public SpotLight(SpotLight s)
{
	super(s.getColor(), s.getPosition(), s.getKc(), s.getKl(), s.getKq());
	this.direction = new Vector(s.direction);
	this.direction.normalize();

}
public SpotLight()
{
	super();
	this.direction = new Vector();

}

// ***************** Getters/Setters ********************** // 
public Vector getDirection()
{
	return new Vector(direction);
}
public void setDirection(Vector direction)
{
	this.direction = new Vector(direction);
}
// ***************** Administration  ******************** // 
@Override
public int hashCode()
{
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((direction == null) ? 0 : direction.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) 
{
	if (this == obj)
		return true;
	if (!super.equals(obj))
		return false;
	if (getClass() != obj.getClass())
		return false;
	SpotLight other = (SpotLight) obj;
	if (direction == null) {
		if (other.direction != null)
			return false;
	} else if (!direction.equals(other.direction))
		return false;
	return true;
}
@Override
public String toString() 
{
	return "spotLight [direction=" + direction + "]";
}

@Override

public Color getIntensity(Point3D point)//לחישוב צבע האור של הפוניט
{
	
	Color pointColor = super.getIntensity(point);
	
	Vector l = getL(point);
	l.normalize();
	
	double dibil = Math.abs(direction.dotProduct(l));

	if (dibil > 1) 
		dibil = 1; 
	
	return new Color((int)(pointColor.getRed()   * dibil),
			 		 (int)(pointColor.getGreen() * dibil),
			 		 (int)(pointColor.getBlue()  * dibil)); 
}

}
	
