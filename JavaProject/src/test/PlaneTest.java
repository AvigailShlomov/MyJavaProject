package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import geometries.Plane;
import primitives.Coordinate;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class PlaneTest {

	//@Test
	public void testGetNormal()
	{
		Vector w=new Vector(new Point3D(new Coordinate(1.0), new Coordinate(5.0), new Coordinate(1.6)));
		Vector a=new Vector(new Point3D(new Coordinate(1.0), new Coordinate(5.0), new Coordinate(1.6)));
		Plane p= new Plane(java.awt.Color.black,new Material(), new Point3D(0, 0, 1), w);//לבדוק אם צריכ לשים ערכ במשתנה של החומר
		Point3D o=new Point3D(1, 5, 1);
		Vector e=p.getNormal(o);
		assertEquals(e, a);
	}
	@Test
	public void testFindIntersection()
	{
		Plane p = new Plane(new Point3D(0,0,-200), new Vector(0,0,1));
		Ray ray1 = new Ray(new Point3D(), new Vector(1/Math.sqrt(6),1/Math.sqrt(6),-1*Math.sqrt(2/3.0)));
		ArrayList<Point3D> lst = new ArrayList<Point3D>(p.findIntersections(ray1));
		assertEquals(1, lst.size());
		assertEquals("",1/Math.sqrt(6),ray1.getDirection().getHead().getX().getCoordinate(),1e-10);
		assertEquals("",1/Math.sqrt(6),ray1.getDirection().getHead().getY().getCoordinate(),1e-10);
		assertEquals("",-1*Math.sqrt(2/3.0),ray1.getDirection().getHead().getZ().getCoordinate(),1e-10);
	}

}

