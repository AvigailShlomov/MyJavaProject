package test;

import static org.junit.Assert.*;

import primitives.Vector;
import primitives.Coordinate;
import primitives.Point3D;
import org.junit.Test;

public class VectorTest 
{

	@Test
	public void testAdd() 
	{
		Vector w=new Vector(new Point3D(new Coordinate(1.0), new Coordinate(5.0), new Coordinate(1.6)));
	    Vector v=new Vector(new Point3D(new Coordinate(1.0), new Coordinate(1.0), new Coordinate(1.0)));
	    w.add(v);
	    assertEquals(new Vector(new Point3D(2.0, 6.0, 2.6)),w);
	}
	@Test
	public void testSubtract() 
	{
		Vector w=new Vector(new Point3D(new Coordinate(5.0), new Coordinate(3.0), new Coordinate(1.0)));
	    Vector v=new Vector(new Point3D(new Coordinate(3.0), new Coordinate(1.0), new Coordinate(1.0)));
	    w.subtract(v);
	    assertEquals(new Vector(new Point3D(2.0, 2.0, 0.0)),w);	
	}
	@Test
	public void testScaling()
	{
		double x=5.0;
		Vector v=new Vector(new Point3D(new Coordinate(3.0), new Coordinate(1.0), new Coordinate(1.0)));
		v.scale(x);
		assertEquals(new Vector(new Point3D(15.0, 5.0, 5.0)), v);
	}
	@Test
	public void testDorProduct() 
	{
		Vector x =new Vector(new Point3D(new Coordinate(3.0), new Coordinate(1.0), new Coordinate(1.0)));
		Vector v=new Vector(new Point3D(new Coordinate(3.0), new Coordinate(1.0), new Coordinate(1.0)));
		v.dotProduct(x);
		assertEquals(new Vector(new Point3D(9.0, 1.0, 1.0)), v);
	}
	@Test
	public void testLength()
	{
		Vector x =new Vector(new Point3D(new Coordinate(3.0), new Coordinate(1.0), new Coordinate(1.0)));
		double w=x.length();//11
		assertEquals(Math.sqrt(11),w,0);	
	}

	//@Test
	public void testNormalize()
	{/*
		Vector o = new Vector(new Point3D(new Coordinate(2), new Coordinate(2), new Coordinate(2)));
		o.normalize();
		Vector w = new Vector(new Point3D(new Coordinate(2/Math.sqrt(12)), new Coordinate(2/Math.sqrt(12)), new Coordinate(2/Math.sqrt(12))));
		assertEquals(w,o);*/
		Vector v = new Vector(3.5,-5,10);
		v.normalize();
		assertEquals("", 1, v.length(),1e-10);
		v = new Vector(0,0,0);
		try 
		{
			v.normalize();
			fail("Didn't throw divide by zero exception!");
		} 
		catch (ArithmeticException e) 
		{
			assertTrue(true);
		}

	}
	
	@Test
	public void testCrossProduct()//////////check
	{
		Vector o = new Vector(new Point3D(new Coordinate(2), new Coordinate(2), new Coordinate(2)));
		Vector w = new Vector(new Point3D(new Coordinate(1), new Coordinate(2), new Coordinate(1)));
		o.crossProduct(w);
		Vector r = new Vector(new Point3D(new Coordinate(-2), new Coordinate(0), new Coordinate(2)));
		assertEquals(r, o);
		
		
		
	}
}

