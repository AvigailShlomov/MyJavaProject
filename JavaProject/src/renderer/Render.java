package renderer;

import java.awt.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
//import java.util.Vector;

import elements.Light;
import elements.PointLight;
import scene.Scene;
import geometries.*;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
public class Render 
{
	private Scene scene;
	private ImageWriter imageWriter;
	final int RECURSION_LEVEL=2;
	
	//***************** Constructors ********************** //
	public Render(Scene scene, ImageWriter imageWriter) 
	{
		this.scene =new Scene(scene);
//		this.imageWriter =new ImageWriter(imageWriter);
		this.imageWriter =imageWriter;
	}	
	public Render(Render r) 
	{
		this.scene = new Scene(r.scene);
		this.imageWriter = new ImageWriter(r.imageWriter);
	}
	public Render() 
	{
		this.scene = new Scene();
		//this.imageWriter =  new ImageWriter();
	}
	
	// ***************** Getters/Setters ********************** //
	public Scene getScene() 
	{
		return scene;
	}
	public void setScene(Scene scene) 
	{
		this.scene = new Scene(scene);
	}
	public ImageWriter getImageWriter() 
	{
		return imageWriter;
	}
	public void setImageWriter(ImageWriter imageWriter) 
	{
		this.imageWriter = new ImageWriter(imageWriter);
	}
	
	// ******************** Administration  *********************** // 
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Render))
			return false;
		Render other = (Render) obj;
		if (imageWriter == null) 
		{
			if (other.imageWriter != null)
				return false;
		} 
		else 
			if (!imageWriter.equals(other.imageWriter))
			return false;
		if (scene == null) 
		{
			if (other.scene != null)
				return false;
		}
		else
			if (!scene.equals(other.scene))
			return false;
		return true;
	}
	
	@Override
	public String toString()
	{
		return "Render [scene=" + scene + ", imageWriter=" + imageWriter + "]";
	}
	public void printGrid(int interval) 
	{
		for(int i=0;i<this.imageWriter.getHeight();i++)
		{
			for(int j=0;j<this.imageWriter.getWidth();j++)
			{
				if(i%interval==0||j%interval==0)
					this.getImageWriter().writePixel(i, j, 255, 255, 255);//ובמידה ואני באחת מהנקודות הנכונות צבע אותם
			}
		}
//	this.imageWriter.writeToimage();//כתיבת התמונה			
	}


	private Map<Geometry,ArrayList<Point3D>> findClosesntIntersection(Ray ray) {
	
		Iterator<Geometry> geometries = scene.getGeometriesIterator();
		Map<Geometry,ArrayList<Point3D>> intersectionPoints = new HashMap<Geometry,ArrayList<Point3D>>();
	
		while (geometries.hasNext()){
			Geometry geometry = geometries.next();
			ArrayList<Point3D> geometryIntersectionPoints = geometry.findIntersections(ray);
			if (!geometryIntersectionPoints.isEmpty())
				intersectionPoints.put(geometry,geometryIntersectionPoints);
		}
		return intersectionPoints;
	}
	private Entry<Geometry,Point3D> getClosestPoint(Map<Geometry,ArrayList<Point3D>> intersectionPoints) {
	
		double distance = Double.MAX_VALUE;
		Point3D P0 = scene.getCamera().getP0();
		Map<Geometry,Point3D> minDistancePoint = new HashMap<Geometry,Point3D>();
	
		for (Entry<Geometry, ArrayList<Point3D>> entry:intersectionPoints.entrySet())
			for (Point3D point: entry.getValue())
				if (P0.distance(point) < distance)
				{
					minDistancePoint.clear();
					minDistancePoint.put(entry.getKey(), new Point3D(point));
					distance = P0.distance(point); 
				}
		Entry<Geometry, Point3D> entry = minDistancePoint.entrySet().iterator().next();
		return entry;
	}

	public Color calcDiffusiveComp(double kd,Vector normal,Vector light,Color c)//fixed
	{
		normal.normalize();
		light.normalize();
		//light.scale(-1);
		
		double d=/*Math.abs*/(kd*normal.dotProduct(light));//|kd*(normal*light)|
		//System.out.println(d);
//		if(d>1)//בדיקת חיוביות
//		{
//			d=0;
//		}
		//RGB
		double red=c.getRed();
		double green =c.getGreen();
		double blue = c.getBlue();
		
	//	System.out.println(d);
		red=red*d;		
		if(red>255)
			red=255;
		if(red<0)
			red=0;
		
		green=green*d;
		if(green>255)
			green=255;
		if(green<0)
			green=0;
	
		blue=blue*d;
		if(blue>255)
			blue=255;
		if(blue<0)
			blue=0;
	//	System.out.println(red+","+green+","+blue);
		return new Color((int)red,(int)green,(int)blue);
}

//		private Color calcDiffusiveComp(double kd, Vector normal, Vector l, Color lightIntensity) {
//
//			normal.normalize();
//			l.normalize();
//
//			double k = Math.abs(kd * normal.dotProduct(l));
//            int r=(int)(lightIntensity.getRed() * k);
//            int g=(int)(lightIntensity.getGreen() * k);
//            int b=(int)(lightIntensity.getBlue() * k);
//			return new Color (r,g,b);
//		}

	
	private Color calcColor(Geometry geometry, Point3D point,Ray inRay,int level) 
	{
		
		if (level == RECURSION_LEVEL)
		{
		return new Color(0, 0, 0);
	}
		Color ambientLight = scene.getAmbientLight().getIntensity(point);
		Color emissionLight = geometry.getEmmission();
		Color diffuseLight = new Color(0, 0, 0);
		Color specularLight = new Color(0, 0, 0);
		Color reflected = new Color(0, 0, 0);		
		Color refracted = new Color(0, 0, 0);
		Iterator<Light> lights = scene.getLightsIterator();
		while (lights.hasNext())
		{
			Light light = lights.next();
			if (!occluded(light, point, geometry))
			{

			Color lightIntensity = light.getIntensity(point);
			Color lightDiffuse = calcDiffusiveComp(geometry.getMaterial().getKd(),geometry.getNormal(point),light.getL(point), lightIntensity);
			diffuseLight=addColors(diffuseLight,lightDiffuse);
			Color lightSpecular = calcSpecularComp(geometry.getMaterial().getKs(),new Vector(point, scene.getCamera().getP0()),geometry.getNormal(point),light.getL(point),geometry.getMaterial().getnShininess(), lightIntensity);
			specularLight=addColors(specularLight,lightSpecular);
			}
		}		
		// Recursive call for a reflected ray
		Ray reflectedRay = constructReflectedRay(geometry.getNormal(point), point, inRay);
		Map<Geometry, ArrayList<Point3D>> reflectedIntersectionPoints = findClosesntIntersection(reflectedRay);
		if (!reflectedIntersectionPoints.isEmpty()){
			Entry<Geometry, Point3D> reflectedClosesEntry = getClosestPoint(reflectedIntersectionPoints);
			reflected = calcColor(reflectedClosesEntry.getKey(), reflectedClosesEntry.getValue(), reflectedRay, level+1);
			double kr = geometry.getMaterial().getKr();
			reflected = new Color ((int)(reflected.getRed() * kr), (int)(reflected.getGreen() * kr),(int)(reflected.getBlue() * kr));
		}
		// Recursive call for a refracted ray
		Ray refractedRay = constructRefractedRay(geometry, point, inRay);
		Map<Geometry, ArrayList<Point3D>> refractedIntersectionPoints = findClosesntIntersection(refractedRay);
		if (!refractedIntersectionPoints.isEmpty()){
			Entry<Geometry, Point3D> refractedEntry = getClosestPoint(refractedIntersectionPoints);
			refracted = calcColor(refractedEntry.getKey(), refractedEntry.getValue(), refractedRay, level + 1);
			double kt = geometry.getMaterial().getKt();
			refracted = new Color ((int)(refracted.getRed() * kt), (int)(refracted.getGreen() * kt),(int)(refracted.getBlue() * kt));
		}
		Color finalColor = addColors(ambientLight, emissionLight);
		finalColor= addColors(finalColor, diffuseLight);
		finalColor= addColors(finalColor, specularLight);
		finalColor= addColors(finalColor, reflected);
		finalColor= addColors(finalColor, refracted);
		
		return finalColor;
	}
	
	private Color calcColor(Geometry geometry, Point3D point, Ray ray)
	{
	return calcColor(geometry, point, ray, 0);
    }
//	private Color calcSpecularComp(double ks, Vector v, Vector normal, Vector l, double shininess, Color lightIntensity) {
//
//		v.normalize();
//		normal.normalize();
//		l.normalize();
//
//		normal.scale(-2 * l.dotProduct(normal));
//		l.add(normal);
//		Vector R = new Vector(l);
//		R.normalize();
//		double k=0;
//		if (v.dotProduct(R) > 0) // prevents glowing at edges
//			k = ks * Math.pow(v.dotProduct(R), shininess);
//		 int r=(int)(lightIntensity.getRed() * k);
//         int g=(int)(lightIntensity.getGreen() * k);
//         int b=(int)(lightIntensity.getBlue() * k);
//   		 return new Color (r,g,b);
//		
//	}
	
	 public Color calcSpecularComp(double ks,Vector v,Vector normal,Vector light,double shine,Color il)//fixed
	 {
	     v.normalize();//V
		 normal.normalize();
		 light.normalize();//R

		 double dibil=light.dotProduct(normal);//V*R
		 dibil*=(-2);	
		 normal.scale(dibil);
		 light.add(normal);//שינינו את הערך של light
		 Vector r = new Vector(light);
		 r.normalize();

		 double x=0;
		 double vrn=v.dotProduct(r);
		 if (vrn>0)//בדיקת חיוביות//למניעת זריחה בנקודות קצה בנקודה צפה		 
			 x=ks*Math.pow(vrn, shine);//k=ks*(vrn^shine)
		
		 x=Math.abs(x);//|x|
			int red=il.getRed();
			int green =il.getGreen();
			int blue = il.getBlue();
			
			red=(int)(red*x);
			if (red > 255)
			red = 255;
			green=(int)(green*x);
			if (green > 255)
			green = 255;
			blue=(int)(blue*x);
			if (blue > 255)
			blue = 255;
			return new Color(red,green,blue); 
			//return new Color(0,0,0);
	 }

	// (בנוספ יש מימוש במידה ונשתמש ביותר קרניים לדיוק וחדות )עובר על כל חלק ברנדר שלפניו ומחפש אם הקרן חותכת איפשו את העצם לפניה במידה וכן נקודת החיתוך נרשמת
		public void renderImage()
		{
		
			for (int i = 0; i < imageWriter.getHeight(); i++){
				for (int j = 0; j < imageWriter.getWidth(); j++){
					ArrayList<Ray> listRay = scene.getCamera().constructRayThroughPixel1(
							  imageWriter.getNx(), imageWriter.getNy(), j, i, 
							  scene.getScreenDistance(),
							  imageWriter.getWidth(), imageWriter.getHeight());
					int r=0,g=0,b=0;
					for (Ray ray : listRay)
					{
					Map<Geometry, ArrayList<Point3D>> intersectionPoints = findClosesntIntersection(ray);
					
					if (intersectionPoints.isEmpty()){
						r+=scene.getBackground().getRed();
						g+=scene.getBackground().getGreen();
						b+=scene.getBackground().getBlue();
						
					} 
					else 
					{
						Entry<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
						Color c=calcColor(closestPoint.getKey(),closestPoint.getValue(), ray);
						r+=c.getRed();
						g+=c.getGreen();
						b+=c.getBlue();
					}
					}
					imageWriter.writePixel(j, i, new Color(r,g,b));
				}
			}
			imageWriter.writeToimage();
		}
	
		
	private boolean occluded(Light light, Point3D point, Geometry geometry)//fixed
	{
	
	Vector lightDirection = light.getL(point);
	lightDirection.scale(-1);
	lightDirection.normalize();
	
	Point3D geometryPoint = new Point3D(point);//////////
	Vector epsVector = new Vector(geometry.getNormal(point));
	epsVector.scale(2);
	if (epsVector.dotProduct(geometry.getNormal(geometryPoint))>=0)
		epsVector.scale(-1);
	geometryPoint.add(epsVector);
	
	Ray lightRay = new Ray(geometryPoint, lightDirection); 
	//if(lightDirection.dotProduct(geometry.getNormal(geometryPoint))>0)//
		//epsVector.scale(-1);
	Map<Geometry, ArrayList<Point3D>> intersectionPoints = findClosesntIntersection(lightRay);
	
	//if (geometry instanceof FlatGeometry)
		intersectionPoints.remove(geometry);
	for (Entry<Geometry, ArrayList<Point3D>> entry: intersectionPoints.entrySet())
		if (entry.getKey().getMaterial().getKt() == 0)//kt=שקיפות
			if (!(light instanceof PointLight) || ((PointLight)light).getPosition().distance(point)>((PointLight)light).getPosition().distance(entry.getValue().get(0)))
				return true;
	
	return false;
}
	private Color addColors(Color x,Color y)
	{
		int red=x.getRed()+y.getRed();
		if (red>255)
			red=255;
		int green=x.getGreen()+y.getGreen();
		if (green>255) 
			green=255;
		int blue=x.getBlue()+y.getBlue();
		if (blue>255)
			blue=255;
		return new Color(red,green,blue);
	}	
private Ray constructReflectedRay(Vector normal, Point3D point, Ray inRay) {

Vector l = new Vector(inRay.getDirection());
l.normalize();
normal.scale(-2 * l.dotProduct(normal));
l.add(normal);
Vector R = new Vector(l);
R.normalize();
Point3D newP = new Point3D(point);
newP.add(normal);
Ray reflectedRay = new Ray(newP, R);
return reflectedRay;
}


	private Ray constructRefractedRay(Geometry geometry, Point3D point, Ray inRay)
 {
		
		Vector normal = geometry.getNormal(point);
		normal.scale(-2);
		Point3D newP = new Point3D(point);
		newP.add(normal);
		return new Ray (newP, inRay.getDirection());

		}
	}

