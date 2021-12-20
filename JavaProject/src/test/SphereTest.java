package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import primitives.*;
import geometries.Sphere;

public class SphereTest {

	@Test
	public void testGetNormal()
	{
		Point3D p =new Point3D(1.0,1.0, 1.0);
		Sphere s = new Sphere(java.awt.Color.black,new Material(), new Point3D(2.0, 1.0, 2.0), 5.0);
		Vector v=new Vector(new Point3D(new Coordinate(0.0), new Coordinate(0.0), new Coordinate(0.0)));
		v=s.getNormal(p);
		Vector q=new Vector(new Point3D(new Coordinate(1.0), new Coordinate(0.0), new Coordinate(-1.0)));
		q.normalize();
		assertEquals(q, v);
	}
	@Test
	public void testFindIntersection()
	{
		Sphere sphere = new Sphere(new Point3D(0,0,-400), 200);
		Ray ray1 = new Ray(new Point3D(), new Vector(0,1/Math.sqrt(5),-2/Math.sqrt(5)));
		ArrayList<Point3D> lst = new ArrayList<Point3D>(sphere.findIntersections(ray1));
		assertEquals(2, lst.size());
		assertEquals("",0,lst.get(0).getX().getCoordinate(),1e-10);
		assertEquals("",120,lst.get(0).getY().getCoordinate(),1e-10);
		assertEquals("",-240,lst.get(0).getZ().getCoordinate(),1e-10);
	}
}

