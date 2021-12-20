package geometries;

import java.awt.Color;
import java.util.ArrayList;

import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Sphere extends Geometry
{

		private Point3D center;
		private double radius; 

		// ***************** Constructors ********************** // 

		public Sphere(Color emmission1, Material material1, Point3D center, double radius)
		{
			super(emmission1, material1); 
			this.center = new Point3D(center);
			this.radius = radius;
		}
		public Sphere(Point3D center, double radius) 
		{
			super();
			this.center = new Point3D(center);
			this.radius = radius;
		}
		public Sphere(Sphere s) //copy Constructor
		{
			super();
			this.center = new Point3D(s.getCenter());
			this.radius = s.radius;
		}
		public Sphere()//empty Constructor
		{
			super();
			this.center = new Point3D(center);
			this.radius = 0;
		}
		// ***************** Getters/Setters ********************** // 
		public Point3D getCenter()
		{
			return new Point3D(center);
		}

		public void setCenter(Point3D center) //חייב להחזיר עצם חדש אנו לא רוצים שמשהו חלילה יקרה למקור
		{
			this.center = new Point3D(center);
		}
		public double getRadius() 
		{
			return radius;
		}

		public void setRadius(double radius) 
		{
			this.radius = radius;
		}
		// ***************** Administration  ******************** // 
		@Override
		public String toString() 
		{
			return "Sphere [center=" + center + ", radius=" + radius + "]";
		}

		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Sphere))
				return false;
			Sphere other = (Sphere) obj;
			if (center == null) {
				if (other.center != null)
					return false;
			} else if (!center.equals(other.center))
				return false;
			if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
				return false;
			return true;
		}

		// ***************** Operations ******************** // 
		
		public Vector getNormal(Point3D p)//נורמל למעגל בעזרת נקודה במרחב 
		{
				Vector o= new Vector(p.sub(center));
				 o.normalize();
				return o;		
		}
		
		public  ArrayList<Point3D> findIntersections (Ray r)
		{
			ArrayList<Point3D> lst = new ArrayList<Point3D>(2);
			Vector l = new Vector(this.center);//L=O-P0
			l.subtract(new Vector(r.getPoo()));//L=O-P0
			double tm = l.dotProduct(r.getDirection());
			double d = Math.pow(l.length(), 2) - Math.pow(tm, 2);//WITHOUT SQRT SO WE WONT HAVE A SQRT ON A NEGETIVE NUMBER
			if(d<0)
			{
				return lst;//empty list
			}
			d = Math.sqrt(d);
			if(d>this.radius)
			{
				return lst;
			}
			double th = Math.sqrt(Math.pow(radius,2) - Math.pow(d, 2));
			double t1 = tm-th;//-
			if(t1>0)
			{
				Vector ezer1 = new Vector(r.getDirection());
				ezer1.scale(t1);//v*t1
				ezer1.add(new Vector(r.getPoo()));//v*t1+p0
				lst.add(ezer1.getHead());//נקודת החתוך שלנו
			}
			double t2 = tm+th;//+
			if(t2>0)
			{
				Vector ezer2 = new Vector(r.getDirection());
				ezer2.scale(t2);//v*t2
				ezer2.add(new Vector(r.getPoo()));//v*t2+p0
				lst.add(ezer2.getHead());//נקודת החתוך שלנו
			}
			return lst;
		}

		////////////////////////////////////////////////////////////
//		@Override
//		public ArrayList<Point3D> findIntersections(Ray ray) {
//
//			ArrayList<Point3D> intersectionPoints= new ArrayList<Point3D>(2);
//			
//			Vector L = new Vector(ray.getPoint(), this.get_p());
//			double tm = L.dotProduct(ray.getVector());
//			double d = Math.sqrt(Math.pow(L.length(), 2) - Math.pow(tm, 2));
//			
//			if (d > this.getRadiuos())
//				return intersectionPoints;
//			
//			double th = Math.sqrt(Math.pow(this.getRadiuos(), 2) - Math.pow(d, 2));
//			
//			double t1 = tm - th;
//			double t2 = tm + th;
//			
//			if (t1 >= 0){
//				Vector V = ray.getVector();
//				V.scale(t1);
//				Point3D P1 = ray.getPoint();
//				P1.add(V.getHead());
//				intersectionPoints.add(P1);
//			}
//			
//			if (t2 >= 0){
//				Vector V = ray.getVector();
//				V.scale(t2);
//				Point3D P2 = ray.getPoint();
//				P2.add(V.getHead());
//				intersectionPoints.add(P2);
//			}
//			
//			return intersectionPoints;
//		}

}

