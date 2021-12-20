package elements;

import java.awt.Color;

import primitives.*;
public class DirectionalLight extends Light
{

	Vector direction;

	// ***************** Constructors ********************** //

public DirectionalLight(Color color, Vector direction) 
{
	super(color);
	this.direction = new Vector (direction);
}
public DirectionalLight(DirectionalLight d) 
{
	super(d.getColor());
	this.direction = new Vector (d.direction);
}
public DirectionalLight() 
{
	super(new Color(255,255,255));
	this.direction = new Vector ();
}	
// ***************** Getters/Setters ********************** // 
public Vector getDirection() {
	return new Vector(direction);
}
public void setDirection(Vector direction) {
	this.direction = new Vector(direction);
}

// ***************** Administration  ******************** // 

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((direction == null) ? 0 : direction.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	DirectionalLight other = (DirectionalLight) obj;
	if (direction == null) {
		if (other.direction != null)
			return false;
	} else if (!direction.equals(other.direction))
		return false;
	return true;
}
@Override
public String toString() {
	return "DirectionalLight [direction=" + direction + ", color=" + getColor() + "]";
}
//Abstract//
@Override

public Vector getL(Point3D point)//שתפקידה להחזיר ווקטור שיוצא ממקור האור אל הנקודה שמקבלת כפרמטר פונקציה
{
	
	
	return getDirection();
}
@Override
public Color getIntensity(Point3D point)//לחישוב צבע האור של הפוניט
{
	return getColor();
}



}

