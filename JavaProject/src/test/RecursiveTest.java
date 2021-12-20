package test;

import java.awt.Color;

import org.junit.Test;

import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class RecursiveTest {
 
	 
	@Test
	public void recursiveTest(){
		
		Scene scene = new Scene();
		scene.setScreenDistance(300);
		Material m=new Material();
		Sphere sphere = new Sphere(new Color(0, 0, 100),m,new Point3D(0.0, 0.0, -1000),500);
	
		m.setnShininess(20);
		m.setKt(0.5);
		sphere.setMaterial(m);
		scene.addGeometry(sphere);
		Material m2=new Material();
		Sphere sphere2 = new Sphere(new Color(100, 20, 20),m2, new Point3D(0.0, 0.0, -1000),250);
		
		m2.setnShininess(20);
		m2.setKt(0);
		sphere2.setMaterial(m2);
		scene.addGeometry(sphere2);
		
		scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -150)//- - -=> + + -
					   , 0.1, 0.00001, 0.000005,new Vector(2, 2, -3)));
		ImageWriter imageWriter = new ImageWriter("Recursive Test", 500, 500, 500, 500);
		
		Render render = new Render( scene,imageWriter);
		
		render.renderImage();
		
	}		
	@Test
	public void recursiveTest2(){
		
		Scene scene = new Scene();
		scene.setScreenDistance(300);
		Material m=new Material();
		Sphere sphere = new Sphere(new Color(0, 0, 100),m, new Point3D(-550, -500, -1000),300);
		m.setnShininess(20);
		m.setKt(0.5);
		sphere.setMaterial(m);
		scene.addGeometry(sphere);
		Material m2=new Material();
		Sphere sphere2 = new Sphere(new Color(100, 20, 20),m2, new Point3D(-550, -500, -1000),150);
		
		m2.setnShininess(20);
		m2.setKt(0);
		sphere2.setMaterial(m2);
		scene.addGeometry(sphere2);
		 
		Triangle triangle = new Triangle(new Color(20, 20, 20),new Point3D(  1500, -1500, -1500),
				 						 new Point3D( -1500,  1500, -1500),
				 						 new Point3D(  200,  200, -375));
		
		Triangle triangle2 = new Triangle( new Color(20, 20, 20),new Point3D(  1500, -1500, -1500),
										  new Point3D( -1500,  1500, -1500),
										  new Point3D( -1500, -1500, -1500));
		Material m3=new Material();
		Material m4=new Material();
		m3.setKr(1);
		m4.setKr(0.5);
		triangle.setMaterial(m3);
		triangle2.setMaterial(m4);
		scene.addGeometry(triangle);
		scene.addGeometry(triangle2);

		scene.addLight(new SpotLight(new Color(255, 100, 100),  new Point3D(200, 200, -150) 
				 , 0, 0.00001, 0.000005  ,new Vector(-2, -2, -3)));
		ImageWriter imageWriter = new ImageWriter("Recursive Test 222", 500, 500, 500, 500);
		
		Render render = new Render(scene,imageWriter );
		
		render.renderImage();
		
	}

	@Test
	public void recursiveTest3()
	{
		
		Scene scene = new Scene();
		scene.setScreenDistance(300);
		Material m=new Material();
		Sphere sphere = new Sphere(new Color(0, 0, 100),m, new Point3D(0, 0, -1000),300.);
		m.setnShininess(20);
		m.setKt(0.5);
		sphere.setMaterial(m);
		scene.addGeometry(sphere);
		Material m1=new Material();
		Sphere sphere2 = new Sphere(new Color(100, 20, 20),m1, new Point3D(0, 0, -1000),150.);
		
		m1.setnShininess(20);
		m1.setKt(0);
		sphere2.setMaterial(m1);
		scene.addGeometry(sphere2);
		
		Triangle triangle = new Triangle(new Color(20, 20, 20),new Point3D(  2000, -1000, -1500),
				 						 new Point3D( -1000,  2000, -1500),
				 						 new Point3D(  700,  700, -375)
				 						);
		
		Triangle triangle2 = new Triangle( new Color(20, 20, 20),new Point3D(  2000, -1000, -1500),
										  new Point3D( -1000,  2000, -1500),
										  new Point3D( -1000, -1000, -1500)
										 );
		
		Material m2=new Material();
		m2.setKr(1);
		triangle.setMaterial(m2);

		Material m3=new Material();
		m3.setKr(0.5);
		triangle2.setMaterial(m3);
		
		
		scene.addGeometry(triangle);
		scene.addGeometry(triangle2);

		scene.addLight(new SpotLight(new Color(255, 100, 100),  new Point3D(200, 200, -150), 
				   0, 0.00001, 0.000005 ,new Vector(-2, -2, -3)));
	
		ImageWriter imageWriter = new ImageWriter("Recursive Test 3", 500, 500, 500, 500);
		
		Render render = new Render(scene,imageWriter );
		
		render.renderImage();
		
	}
	@Test
	public void RecursiveTest4(){
		
		Scene scene = new Scene();

		
		 Sphere sphere = new Sphere( new Color(100, 50, 0), new Material(1, 1, 2,0.8,0), new Point3D(500.0, -500.0, -1000),500.);
		  scene.addGeometry(sphere);
		  Sphere sphere2 = new Sphere(new Color(0, 50, 100),  new Material(1, 1, 20,0,0), new Point3D(-1000.0, -2000.0, -1000),500);
          scene.addGeometry(sphere2);
          Sphere sphere3 = new Sphere(new Color(100, 0, 0), new Material(1, 1, 2,0.8,0) ,new Point3D(-500.0, 500.0, -1000), 200.);
          
              scene.addGeometry(sphere3);
              Triangle triangle = new Triangle(new Color(0,0,0),new Material(1,1,1,0,1),new Point3D(  3500,  3500, -2000),
						 new Point3D( -3500, -3500, -1000),
						 new Point3D(  3500, -3500, -2000)
						 );

Triangle triangle2 = new Triangle(new Color(0,0,0),new Material(1,1,1,0,1),new Point3D(  3500,  3500, -2000),
						  new Point3D( -3500,  3500, -1000),
						  new Point3D( -3500, -3500, -1000)
	 						 );

scene.addGeometry(triangle);
scene.addGeometry(triangle2);

scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -100) 
	   , 0, 0.000001, 0.0000005,new Vector(new Point3D(-2, -2, -3))));


		ImageWriter imageWriter = new ImageWriter("Recursive test 4", 500, 500, 500, 500);
		
		Render render = new Render( scene,imageWriter);
		
		render.renderImage();
		
	}
	}