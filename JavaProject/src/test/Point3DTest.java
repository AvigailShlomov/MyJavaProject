package test;

import static org.junit.Assert.*;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

public class Point3DTest 
{

	@Test
	public void testAdd() 
	{

		Point3D g = new Point3D(1.0, 2.0, 50.5);
		System.out.println(g);
		Vector v= new Vector(new Point3D(1.0, 5.6, 9.9));
		g.add(v);
		System.out.println(g);
		assertEquals(new Point3D(2.0, 7.6, 60.4),g);
	}
	
	@Test
	public void testSubtract() 
	{

		Point3D s1 = new Point3D(7.0, 12.0, 50.5);
		Vector v= new Vector(new Point3D(1.0, 5.6, 9.9));
		s1.subtract(v);
		assertEquals(new Point3D(6.0, 6.4, 40.6),s1);
	}
	

}

