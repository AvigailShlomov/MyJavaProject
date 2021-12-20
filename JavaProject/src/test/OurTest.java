package test;

//import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import geometries.Sphere;
import geometries.Triangle;
import primitives.Material;
import primitives.Point3D;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class OurTest {

	//@Test
	public void test() 
	{
		 
		Scene scene = new Scene();
		scene.setScreenDistance(100);
		Material m=new Material();
		
		scene.addGeometry(new Sphere( new Color(255,0,0),m, new Point3D(0.0, -75, -100),25));
		scene.addGeometry(new Sphere( new Color(255,255,0),m, new Point3D(0.0, 0, -100),25));
		scene.addGeometry(new Sphere( new Color(0,102,0),m, new Point3D(0.0, 75, -100),25));
		
		 Triangle triangle = new Triangle(new Color(51,51,51),m,new Point3D( 500, 1000, -500),
				 						 new Point3D(  -500, 1000, -500),
				 						 new Point3D(-500, -1000, -500));
		
		Triangle triangle2 = new Triangle(new Color(51,51,51),m,new Point3D( 500, 1000, -500),
				 			 			  new Point3D(  -500, -1000, -500),
				 			 			  new Point3D( 500,-1000, -500));
		//הוספת המשולשים לרשימה
		scene.addGeometry(triangle);
		scene.addGeometry(triangle2);		
		ImageWriter imageWriter = new ImageWriter("our amazing test", 500, 500, 500, 500);
		
		Render render = new Render(scene,imageWriter);
		
	   render.renderImage();	
		imageWriter.writeToimage();
	
	}
	@Test
	public void test1() 
	{
		 
		Scene scene = new Scene();
		scene.setScreenDistance(100);
		Material m=new Material();
		
		 Triangle triangle = new Triangle(new Color(0,0,255),m,new Point3D( -1000, 1000, -500),
				 						 new Point3D(  100, -1000, -500),
				 						 new Point3D(1000, 1000, -500));
		
		Triangle triangle2 = new Triangle(new Color(51,153,255),m,new Point3D( 500, -1000, -500),
				 			 			  new Point3D(  -500, 1000, -500),
				 			 			  new Point3D( 500,-1000, -500));
		//הוספת המשולשים לרשימה
		scene.addGeometry(triangle);
		scene.addGeometry(triangle2);		
		ImageWriter imageWriter = new ImageWriter("our amazing try", 500, 500, 500, 500);
		
		Render render = new Render(scene,imageWriter);
		
	   render.renderImage();	
		imageWriter.writeToimage();
	
	}
	@Test
	public void test2() 
	{
		 
		Scene scene = new Scene();
		scene.setScreenDistance(100);
		Material m=new Material();
		
		 Triangle triangle = new Triangle(new Color(0,0,255),m,new Point3D( -1000, 500, -500),
				 						 new Point3D(  100, -500, -500),
				 						 new Point3D(1000, 500, -500));
		
		Triangle triangle2 = new Triangle(new Color(51,153,255),m,new Point3D( 1000, -700, 500),
				 			 			  new Point3D(  100, 700, 500),
				 			 			  new Point3D( 1000,-700, 500));

		//הוספת המשולשים לרשימה
		scene.addGeometry(triangle);
		scene.addGeometry(triangle2);		
		ImageWriter imageWriter = new ImageWriter("our amazing", 500, 500, 500, 500);
		
		Render render = new Render(scene,imageWriter);
		
	   render.renderImage();	
		imageWriter.writeToimage();
	
	}
	@Test
	public void test3() 
	{
		 
		Scene scene = new Scene();
		scene.setScreenDistance(100);
		Material m=new Material();
		
		 Triangle triangle = new Triangle(new Color(0,0,255),m,new Point3D( -1000, 700, -500),
				 						 new Point3D(  100, -700, -500),
				 						 new Point3D(1000, 700, -500));
		 Triangle triangle1 = new Triangle(new Color(51,51,51),m,new Point3D( 500, 700, -500),
					 new Point3D(  -500, 700, -500),
					 new Point3D(-500, -700, -500));

Triangle triangle2 = new Triangle(new Color(51,51,51),m,new Point3D( 500, 700, -500),
		 			  new Point3D(  -500, -700, -500),
		 			  new Point3D( 500,-700, -500));


		//הוספת המשולשים לרשימה
		scene.addGeometry(triangle);
		scene.addGeometry(triangle1);
		scene.addGeometry(triangle2);
		ImageWriter imageWriter = new ImageWriter("our amazing home", 500, 500, 500, 500);
		
		Render render = new Render(scene,imageWriter);
		
	   render.renderImage();	
		imageWriter.writeToimage();
	
	}
}
