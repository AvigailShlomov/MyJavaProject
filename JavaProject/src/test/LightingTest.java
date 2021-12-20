package test;

import java.awt.Color;
import org.junit.Test;

import elements.DirectionalLight;
import elements.Light;
import elements.PointLight;

import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Material;
import primitives.Point3D;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;
import primitives.Vector;

	public class LightingTest 
	{
 
		
		@Test 
		public void emmissionTest()//works just fine
		{
			 
			Scene scene = new Scene();
			scene.setScreenDistance(50);
			Material m=new Material();
			
			scene.addGeometry(new Sphere( new Color(255,0,0),m, new Point3D(0.0, 0.0, -149),50));//add sphere to the list
			
			 Triangle triangle = new Triangle(new Color(0,255,0),m,new Point3D( 100, 0, -149),
					 						 new Point3D(  0, 100, -149),
					 						 new Point3D( 100, 100, -149));
			
			Triangle triangle2 = new Triangle(new Color(0,0,255),m,new Point3D( 100, 0, -149),
					 			 			  new Point3D(  0, -100, -149),
					 			 			  new Point3D( 100,-100, -149));
			
			Triangle triangle3 = new Triangle( new Color(255,255,0),m,new Point3D(-100, 0, -149),
					 						  new Point3D(  0, 100, -149),
					 						  new Point3D(-100, 100, -149));
			
			Triangle triangle4 = new Triangle(new Color(255,0,255),m,new Point3D(-100, 0, -149),
					 			 			  new Point3D(  0,  -100, -149),
					 			 			  new Point3D(-100, -100, -149) );
			//הוספת המשולשים לרשימה
			scene.addGeometry(triangle);
			scene.addGeometry(triangle2);
			scene.addGeometry(triangle3);
			scene.addGeometry(triangle4);
			
			ImageWriter imageWriter = new ImageWriter("Emmition testttt", 500, 500, 500, 500);
			
			Render render = new Render(scene,imageWriter);
			
		   render.renderImage();
			render.printGrid(50);
			
			imageWriter.writeToimage();
		}
		//////////////////////////////////////////////////
//POINT TESTS

		@Test
		public void pointLightTest()
		{
			
		Scene scene = new Scene();
			scene.setScreenDistance(100);
			Material m=new Material();
			m.setnShininess(19);
			m.setKd(1); //NOTE: All changes to the material must be done BEFORE the geometry is constructed, because the constructor creates a copy of it
			m.setKs(1);
			Sphere sphere = new Sphere (new Color(0, 0, 100),m, new Point3D(0.0, 0.0, -1000),800);			
			scene.addGeometry(sphere);
			scene.addLight(new PointLight(new Color(255,100,100), new Point3D(200, 200, -100), 
					 1, 0.00001, 0.000000));
			scene.addLight(new SpotLight(new Color(125,0,0), new Point3D(200, -200, -100), 
					 1, 0.00001, 0.000000,new Vector(2,2,-3)));
			ImageWriter imageWriter = new ImageWriter("Point test now", 500, 500, 500, 500);
			
			Render render = new Render(scene, imageWriter);
			
			render.renderImage();
			render.getImageWriter().writeToimage();
			
		}
		
		@Test
		public void pointLightTest2()
		{
			
			Scene scene = new Scene();
			scene.setScreenDistance(100);
			
			Material m=new Material();
			m.setnShininess(20);
			Sphere sphere = new Sphere(new Color(0, 0, 100),m, new Point3D(0.0, 0.0, -1000),500);
			scene.addGeometry(sphere);//הוספת העיגול לרישמה
			
			Triangle triangle = new Triangle(new Color(0,0,0),m,new Point3D(  -3500,  -3500, -2000),
					 						 new Point3D( -3500, 3500, -1000),
					 						 new Point3D(  3500, -3500, -2000)
					 						 );

			Triangle triangle2 = new Triangle(new Color(0,0,0),m,new Point3D(  -3500,  3500, -1000),
					  						  new Point3D( 3500,  3500, -1000),
					  						  new Point3D( 3500, -3500, -2000)
						 					  );
			
			//הוספת המשולשים לרשימה
			scene.addGeometry(triangle);
			scene.addGeometry(triangle2);
			scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(200, 200, -100), 1, 0.000001, 0.0000005));
			ImageWriter imageWriter = new ImageWriter("Point test 2", 500, 500, 500, 500);
			
			Render render = new Render(scene,imageWriter);

			render.renderImage();
			//render.printGrid(50);
		//	imageWriter.writeToimage();
			render.getImageWriter().writeToimage();
			
		}
		


		//////////////////////////////////////////////////////////////////
		//SPOT TESTS
		
		@Test
		public void spotLightTest()// spot test
		{
			Scene scene = new Scene();
			scene.setScreenDistance(100);
			Material m=new Material();
			 m.setnShininess(19);
			m.setKd(1); //NOTE: All changes to the material must be done BEFORE the geometry is constructed, because the constructor creates a copy of it
			m.setKs(1);
			Sphere sphere = new Sphere (new Color(0, 0, 100),m, new Point3D(0.0, 0.0, -1000),800);			
			scene.addGeometry(sphere);
			scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -100), //- - - => + + -
					   0.1, 0.00001, 0.000005,new Vector(2, 2, -3)));
			
			ImageWriter imageWriter = new ImageWriter("spot test", 500, 500, 500, 500);
			
			Render render = new Render(scene, imageWriter);
			
			render.renderImage();
			render.getImageWriter().writeToimage();

		}

		
		@Test
		public void spotLightTest2()//spot test 2
		{
			Scene scene = new Scene();
			//scene.getCamera().setvUp(new Vector(0, 1, 0));
			scene.setScreenDistance(200);
			
			//sphere
			Material m=new Material();
			m.setnShininess(20);		
			Sphere sphere = new Sphere(new Color(0, 0, 100),m, new Point3D(0.0, 0.0,-1000),500);
			scene.addGeometry(sphere);//הוספת מעגל לרשימה
			
			//triangle
			Material m1=new Material();
			
			m1.setnShininess(4);		
			Triangle triangle = new Triangle(new Color (0, 0, 100),m1,new Point3D(125, 225, -260),
											 new Point3D(225, 125, -260),
											 new Point3D(225, 225, -270));
			scene.addGeometry(triangle);//הוספת משלוש לרשימה
			
		    //light
			Light l= new SpotLight(new Color(255, 100, 100), new Point3D(200, 200,-150), 0.1, 0.00001, 0.000005,  new Vector(2.0, 2.0, -3.0));
			scene.addLight(l);//תאורה לסצנה
			
			ImageWriter imageWriter = new ImageWriter("Spot test 2", 500, 500, 500, 500);
			Render render = new Render(scene,imageWriter);
			
			render.renderImage();
			render.getImageWriter().writeToimage();
			
		}


		@Test
		public void spotLightTest3()//spot test 3
		{		
				Scene scene = new Scene();
				
				Triangle triangle = new Triangle( new Color(0,0,0),new Point3D(  3500,  3500, -2000),
						 						 new Point3D( -3500, -3500, -1000),
						 						 new Point3D(  3500, -3500, -2000)
						 						);

				Triangle triangle2 = new Triangle( new Color(0,0,0),new Point3D(  3500,  3500, -2000),
						  						  new Point3D( -3500,  3500, -1000),
						  						  new Point3D( -3500, -3500, -1000)
							 						);
				
				scene.addGeometry(triangle);
				scene.addGeometry(triangle2);
				
				scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -100), 
							   0, 0.00000, 0.000000 ,new Vector(new Point3D(2, 2, -3))));
			
				
				ImageWriter imageWriter = new ImageWriter("Spot test 31", 500, 500, 500, 500);
				
				Render render = new Render(scene,imageWriter );
				
				render.renderImage();
				imageWriter.writeToimage();
		
		}
		@Test
		public void spotLightTest20(){
			
			Scene scene = new Scene();
			scene.setScreenDistance(200);
			Material m=new Material();
			m.setnShininess(20);
			Sphere sphere = new Sphere(new Color(0, 0, 100),m, new Point3D(0.0, 0.0, -1000),500.);
	
			sphere.setMaterial(m);
			scene.addGeometry(sphere);
			
			Triangle triangle = new Triangle( new Color (0, 0, 100),new Point3D(-125, -225, -260),
											 new Point3D(-225, -125, -260),
											 new Point3D(-225, -225, -270)
											);
			
			Material m1=new Material();
			m1.setnShininess(4);
			triangle.setMaterial(m);
			scene.addGeometry(triangle);
			
			scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150),
						   0.1, 0.00001, 0.000005, new Vector(new Point3D(2, 2, -3)) ));
		
			ImageWriter imageWriter = new ImageWriter("Spot test 2-0", 500, 500, 500, 500);
			
			Render render = new Render(scene,imageWriter );
			
			render.renderImage();
			imageWriter.writeToimage();
		}
		@Test
		public void spotLightTest21(){
			
			Scene scene = new Scene();
			scene.setScreenDistance(200);
			Material m=new Material();
			m.setnShininess(20);
			Sphere sphere = new Sphere(new Color(0, 0, 100),m, new Point3D(0.0, 0.0, -1000),500.);

			sphere.setMaterial(m);
			scene.addGeometry(sphere);
			
			Triangle triangle = new Triangle(new Color (0, 0, 100),new Point3D(-75, -175, -260),
											 new Point3D(-175, -75, -260),
											 new Point3D(-175, -175, -270)
											 );
			
			Material m1=new Material();
			m1.setnShininess(4);
			triangle.setMaterial(m);
			scene.addGeometry(triangle);
			
			scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150) ,
						   0.1, 0.00001, 0.000005, new Vector(new Point3D(2, 2, -3))));
		
			ImageWriter imageWriter = new ImageWriter("Spot test 2-1", 500, 500, 500, 500);
			
			Render render = new Render(scene,imageWriter );
			
			render.renderImage();
			imageWriter.writeToimage();
		}

		@Test
		public void spotLightTest23(){
			
			Scene scene = new Scene();
			scene.setScreenDistance(200);
			Material m=new Material();
			m.setnShininess(20);
			Sphere sphere = new Sphere(new Color(0, 0, 100),m, new Point3D(0.0, 0.0, -1000),500);

			//sphere.setMaterial(m);
			scene.addGeometry(sphere);
			
			Triangle triangle = new Triangle(new Color (0, 0, 100),new Point3D(-125, -225, -260),
											 new Point3D(-225, -125, -260),
											 new Point3D(-225, -225, -270)
											 );
			
			Material m1=new Material();
			m1.setnShininess(4);
			triangle.setMaterial(m);
			scene.addGeometry(triangle);
			
			scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -175),
						   0.1, 0.00001, 0.000005, new Vector(new Point3D(2, 2, -3)) ));
		
			ImageWriter imageWriter = new ImageWriter("Spot test 2-3", 500, 500, 500, 500);
			
			Render render = new Render( scene,imageWriter);
			
			render.renderImage();
			imageWriter.writeToimage();
		}
		@Test
		public void spotLightTest24(){
			
			Scene scene = new Scene();
			scene.setScreenDistance(200);
			Material m=new Material();
			m.setnShininess(20);
			Sphere sphere = new Sphere(new Color(0, 0, 100),m, new Point3D(0.0, 0.0, -1000),500.);

			sphere.setMaterial(m);
			scene.addGeometry(sphere);
			
			Triangle triangle = new Triangle(new Color (0, 0, 100),new Point3D(-125, -225, -260),
											 new Point3D(-225, -125, -260),
											 new Point3D(-225, -225, -270)
											 );
			
			Material m1=new Material();
			m1.setnShininess(4);
			triangle.setMaterial(m);
			scene.addGeometry(triangle);
			
			scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -200) ,
						   0.1, 0.00001, 0.000005, new Vector(new Point3D(2, 2, -3))));
		
			ImageWriter imageWriter = new ImageWriter("Spot test 2-4", 500, 500, 500, 500);
			
			Render render = new Render(scene,imageWriter );
			
			render.renderImage();
			imageWriter.writeToimage();
		}
	

		@Test
		public void directionTest(){
			
			Scene scene = new Scene();
			Material m=new Material();
			m.setnShininess(20);
			Sphere sphere = new Sphere(new Color(0, 0, 100),m, new Point3D(0.0, 0.0, -1000),800);
		
			
		//	sphere.setMaterial(m);
			scene.addGeometry(sphere);
			scene.addLight(new DirectionalLight(new Color(255, 100, 100),new Vector(new Point3D(-20, 20, -3))));
		
			ImageWriter imageWriter = new ImageWriter("Direction test", 500, 500, 500, 500);
			
			Render render = new Render(scene,imageWriter );
			
			render.renderImage();
			imageWriter.writeToimage();
		}

	}
