package test;

import java.awt.Color;

//import static org.junit.Assert.*;

//import java.io.Reader;

import org.junit.Test;

import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;

public class RenderTest {

	@Test
	public void test() 
	{

			Scene scene = new Scene();
			Sphere sphere = new Sphere( new Point3D(0.0, 0.0, -150),50);
			sphere.setEmmission(new Color(255, 0, 255));
			
			scene.addGeometry(sphere);
			
			Triangle triangle = new Triangle(new Point3D( 100, 0, -149),
					 						 new Point3D(  0, 100, -149),
					 						 new Point3D( 100, 100, -149));
			triangle.setEmmission(new Color(255, 0, 255));
			
			Triangle triangle2 = new Triangle(new Point3D( 100, 0, -149),
					 			 			  new Point3D(  0, -100, -149),
					 			 			  new Point3D( 100,-100, -149));
			triangle2.setEmmission(new Color(0, 0, 255));
			
			Triangle triangle3 = new Triangle(new Point3D(-100, 0, -149),
					 						  new Point3D(  0, 100, -149),
					 						  new Point3D(-100, 100, -149));
			triangle3.setEmmission(new Color(0, 255, 255));
			
			Triangle triangle4 = new Triangle(new Point3D(-100, 0, -149),
					 			 			  new Point3D(  0,  -100, -149),
					 			 			  new Point3D(-100, -100, -149));
			triangle4.setEmmission(new Color(0, 0, 255));
			
			scene.addGeometry(triangle);
			scene.addGeometry(triangle2);
			scene.addGeometry(triangle3);
			scene.addGeometry(triangle4);
			//scene.setSceneName("firstttt");
			//scene.getCamera().setP0(new Point3D(0,0,200));
			scene.setScreenDistance(50);
			ImageWriter imageWriter = new ImageWriter("image111", 500, 500, 500, 500);
			
			Render render = new Render(scene,imageWriter);
			
			render.renderImage();
			render.printGrid(50);
			render.getImageWriter().writeToimage();
			
		
		
		
	}

}

