
package test;

import static org.junit.Assert.*;
import org.junit.Test;
import elements.Camera;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class CameraTest
{

	@Test
	public void testRaysConstruction()
	{
		//Camera camera = new Camera(new Point3D(0,0,0), new Vector(0,-1,0),new Vector(0,0,-1),new Vector(1,0,0));
		Camera camera = new Camera(new Point3D(0,0,0), new Vector(0,-1,0),new Vector(0,0,-1),new Vector(1,0,0));

		Ray ray = new Ray(camera.constructRayThroughPixel(3, 3, 2, 2, 100, 150, 150));
		assertEquals("",1/Math.sqrt(6),ray.getDirection().getHead().getX().getCoordinate(),1e-10);
		assertEquals("",1/Math.sqrt(6),ray.getDirection().getHead().getY().getCoordinate(),1e-10);
		assertEquals("",-1*Math.sqrt(2/3.0),ray.getDirection().getHead().getZ().getCoordinate(),1e-10);
	}
}
