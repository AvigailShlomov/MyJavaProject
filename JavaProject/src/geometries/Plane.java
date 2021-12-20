package geometries;

import java.awt.Color;
import java.util.ArrayList;

import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Plane extends Geometry implements FlatGeometry
{
		private Point3D p;
		private Vector u;//normal
		
		//***************** Constructors ********************** // 
		public Plane(Color color, Point3D p, Vector u)
		{
			super(color);
			this.p = new Point3D(p);
			this.u = new Vector(u);
			this.u.normalize();
		}
		public Plane(Color color,Material m, Point3D p, Vector u)
		{
			super(color,m);
			this.p = new Point3D(p);
			this.u = new Vector(u);
			this.u.normalize();
		}
		
		public Plane(Point3D p, Vector u)
		{
			super();
			this.p = new Point3D(p);
			this.u = new Vector(u);
			this.u.normalize();
		}
		public Plane(Plane p) 
		{
			super(p.getEmmission(),p.getMaterial());
			this.p = new Point3D(p.p);
			this.u = new Vector(p.u);
			this.u.normalize();
		}
		public Plane()
		{
			super();
			this.p = new Point3D();
			this.u = new Vector();
			this.u.normalize();
		}
		// ***************** Getters/Setters ********************** // 
		public Point3D getP() 
		{
			return new Point3D(p);
		}
		public void setP(Point3D p) 
		{
			this.p = new Point3D(p);
		}
		public Vector getU() 
		{
			return new Vector(u);
		}
		public void setU(Vector u)
		{
			this.u = new Vector(u);
		}
		// ***************** Administration  ******************** // 
		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Plane))
				return false;
			Plane other = (Plane) obj;
			if (p == null) {
				if (other.p != null)
					return false;
			} else if (!p.equals(other.p))
				return false;
			if (u == null) {
				if (other.u != null)
					return false;
			} else if (!u.equals(other.u))
				return false;
			return true;
		}
	
	
			@Override
		public String toString() {
			return "Plane [p=" + p + ", u=" + u + "]";
		}

			@Override
			public Vector getNormal(Point3D p)
			{
				Vector v=this.getU();
					return v;
			}
			
			public  ArrayList<Point3D> findIntersections(Ray r)
			{
				ArrayList<Point3D> lst= new ArrayList<Point3D>();
				double nv = this.u.dotProduct(r.getDirection());
				if(nv ==0)
					return lst;
				Vector ezer = new Vector(r.getPoo());
				ezer.subtract(new Vector(p));
				ezer.scale(-1/nv);
				double t = ezer.dotProduct(u);
				ezer.setHead(new Point3D(r.getDirection().getHead()));
				ezer.scale(t);
				ezer.add(new Vector(r.getPoo()));
				lst.add(new Point3D(ezer.getHead()));
				return lst;
			}
			
			
}