package test;

import static org.junit.Assert.*;

//import java.awt.Color;
import java.util.ArrayList;
import org.junit.Test;
//import geometries.Plane;
//import geometries.Sphere;
import geometries.Triangle;
import primitives.Material;
//import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;



public class TriangleTest 
{

	//@Test
	public void testGetNormal() 
	{
	
		Triangle t= new Triangle(java.awt.Color.black,new Material(), new Point3D(0,0,0),new Point3D(2,5,0) , new Point3D(5,0,3));
		Point3D p=new Point3D(1, 1, 1);
		Vector v=t.getNormal(p);
		//answer
		Vector w=new Vector(new Point3D(2, 5, 0));
	    Vector q=new Vector(new Point3D(3, -5, 3));
	    w.crossProduct(q).normalize();
	    assertEquals(w,v );
		
	}
	@Test
	public void testFindIntersection()
	{
		ArrayList<Point3D> lst=new ArrayList<Point3D>();
		Triangle triangle=new Triangle(java.awt.Color.black,new Material(), new Point3D(100, 100, -200),new Point3D(-100, 100, -200),new Point3D(0, -100, -200));	
		Ray ray1 = new Ray(new Point3D(), new Vector(1/Math.sqrt(6),1/Math.sqrt(6),-1*Math.sqrt(2/3.0)));//we wont use the direction anyway
		lst.addAll(triangle.findIntersections(ray1));
		assertEquals(lst.size(),0);//אין נקודת חיתוך
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
