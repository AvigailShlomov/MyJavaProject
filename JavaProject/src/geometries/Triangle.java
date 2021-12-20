package geometries;

import java.awt.Color;
import java.util.ArrayList;

import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Triangle extends Geometry implements FlatGeometry
{
	 	Point3D p1;
	 	Point3D p2;
	 	Point3D p3;
	 	
	 	// ***************** Constructors ********************** // 
	 	public Triangle(Color emmission1, Point3D p1, Point3D p2, Point3D p3) //בונה המקבלת את כל עכי המשלוש בלי החומר
	 	{
			super(emmission1);
			this.p1 =  new Point3D(p1);
			this.p2 =  new Point3D(p2);
			this.p3 =new Point3D(p3);
	 	}
	 	public Triangle(Color emmission1, Material material1, Point3D p1, Point3D p2, Point3D p3) //בונה המקבלת את כל ערכי המשלוש
	 	{
			super(emmission1, material1);
			this.p1 =  new Point3D(p1);
			this.p2 = new Point3D(p2);
			this.p3 = new Point3D(p3);
		}
		public Triangle(Point3D p1, Point3D p2, Point3D p3)//בונה המקבלת נקודות בלי הסופר
	 	{
	 		super();
	 		this.p1 = new Point3D(p1);
	 		this.p2 = new Point3D(p2);
	 		this.p3 = new Point3D(p3);
	 	}
	 	public Triangle(Triangle T)//בונה המקבלת משולש
	 	{
	 		super(T.getEmmission(),T.getMaterial());
	 		this.p1 = new Point3D(T.p1);
	 		this.p2 = new Point3D(T.p2);
	 		this.p3 = new Point3D(T.p3);
	 	}
		public Triangle() //בנאי ברירת מחדל
		{
			super();
			this.p1 = new Point3D();
			this.p2 = new Point3D();
			this.p3 = new Point3D();
		}
		public Triangle(double x,double y,double z)//בנאי שמקבל דאבל
		{
			this.p1=new Point3D(x, y, z);
		}
	 	// ***************** Getters/Setters ********************** //
	 	//p1
	 	public Point3D getP1() 
	 	{
	 		return new Point3D(p1);
	 	}

	 	public void setP1(Point3D p1)
	 	{
	 		this.p1 = new Point3D(p1);
	 	}
	 	//p2
	 	public Point3D getP2() 
	 	{
	 		return new Point3D(p2);
	 	}

	 	public void setP2(Point3D p2)
	 	{
	 		this.p2 = new Point3D(p2);
	 	}
	 	//p3
	 	public Point3D getP3() 
	 	{
	 		return new Point3D(p3);
	 	}

	 	public void setP3(Point3D p3) 
	 	{
	 		this.p3 = new Point3D(p3);
	 	}
	 	
	 	// ***************** Administration  ******************** // 
	 	@Override
	 	public boolean equals(Object obj) 
	 	{
	 		if (this == obj)
	 			return true;
	 		if (obj == null)
	 			return false;
	 		if (getClass() != obj.getClass())
	 			return false;
	 		Triangle other = (Triangle) obj;
	 		if (p1 == null) {
	 			if (other.p1 != null)
	 				return false;
	 		} else if (!p1.equals(other.p1))
	 			return false;
	 		if (p2 == null) {
	 			if (other.p2 != null)
	 				return false;
	 		} else if (!p2.equals(other.p2))
	 			return false;
	 		if (p3 == null) {
	 			if (other.p3 != null)
	 				return false;
	 		} else if (!p3.equals(other.p3))
	 			return false;
	 		return true;
	 	}

	 	@Override
	 	public String toString() {
	 		return "Triangle [p1=" + p1 + ", p2=" + p2 + ", p3=" + p3 + "]";
	 	}

	
	 	@Override
	 	public Vector getNormal(Point3D p) //מחזיר את הנורמל למשולש
	 	{
//	 		Vector o= new Vector(p2.sub(p1));
//	 		Vector a= new Vector(p3.sub(p1));
//	 		o.crossProduct(a).normalize();
//	 		return o;
	 		Vector vec1 = new Vector(p2);//יצירת וקטור מהנקודה פי2
	 		Vector vec2 = new Vector(p3);//יצירת וקטור מפי 3	
	        vec1.subtract(new Vector(p1));//חיסור בין שתי הקטורים
	        vec2.subtract(new Vector(p1));//חיסור בין שתי הקטורים
	        vec1 = vec1.crossProduct(vec2);
	        vec1.normalize();//נירמול
	        return vec1;
	 		
	 	}

	 	
	 	public  ArrayList<Point3D> findIntersections (Ray r)//מחזיר רשימת נקודות חיתוך למשטח עם הקרן שמתקבלת
	 	{
	 		Triangle trg=new Triangle(this);// המשלוש הנוכחי
	 		ArrayList<Point3D> lst = new ArrayList<Point3D>();//מערך לנקודות חיתןך
	 		ArrayList<Point3D> EmptyList = new ArrayList<Point3D>();//מערך ריק במידה ואין נקודות חיתו
	 		
	 		Vector v1=new Vector(trg.getP1().sub(r.getPoo()));//v1=p1-p0
	 		Vector v2=new Vector(trg.getP2().sub(r.getPoo()));//v2=p2-p0
	 		Vector v3=new Vector(trg.getP3().sub(r.getPoo()));//v3=p3-p0
	 		
	 		Vector n1=new Vector(v1.crossProduct(v2));//n1=v1*v2
	 		n1.normalize();//נירמול הוקטור ממכפלת הוקטורים
	 		
	 		Vector n2=new Vector(v2.crossProduct(v3));//n2=v2*v3
	 		n2.normalize();
	 		
	 		Vector n3=new Vector(v3.crossProduct(v1));//n3=v3*v1
	 		n3.normalize();
	 		
	 		Plane plane =new Plane(p3,n1);
	 		lst.addAll(plane.findIntersections(r));
	 		
	 		if(!lst.isEmpty())
            {
	 		  Point3D p= new Point3D(lst.get(0)); 
	 		  Vector q=new Vector(p.sub(r.getPoo()));//p-p0
	 		  
	 		  if((n1.dotProduct(q)>=0)&&((n2.dotProduct(q)>=0))&&((n3.dotProduct(q)>=0)))//משולשים שעריכהם חיובים
	 			  return lst;
	 		  else 
	 			 if((n1.dotProduct(q)<0)&&((n2.dotProduct(q)<0))&&((n3.dotProduct(q)<0)))//למשולשים שערכיהם שיליליים
		 			  return lst;
	 		    return EmptyList;
            }
	 		else
	 			return EmptyList;
	 		
	 			
	 	
	 	
//		public ArrayList<Point3D> findIntersections (Ray ray)//מחזיר רשימת נקודות חיתוך למשטח עם הקרן שמתקבלת
//		{
//			    Vector v = this.getNormal(new Point3D());
//			    ArrayList<Point3D> intersectionPoints = new ArrayList<Point3D>();
//		        Plane p = new Plane(this.getEmmission(),this.p1,v);
//		        intersectionPoints = p.findIntersections(ray);
//		        if (intersectionPoints.isEmpty())
//		        {
//		            return intersectionPoints; 
//		        }         
//		        Triangle tr1 = new Triangle(this.getEmmission(), ray.getPoo(), p1, p2);
//		        Vector N1 = new Vector();
//		        N1 = tr1.getNormal(new Point3D());
//		        Triangle tr2 = new Triangle( this.getEmmission(), ray.getPoo(), p2, p3);
//		        Vector N2 = new Vector();
//		        N2 = tr2.getNormal(new Point3D());
//		        Triangle tr3 = new Triangle(this.getEmmission(), ray.getPoo(), p3, p1);
//		        Vector N3 = new Vector();
//		        N3 = tr3.getNormal(new Point3D());
//		       
//		        Vector v1 = new Vector(intersectionPoints.get(0));
//	            Vector v2=new Vector(ray.getPoo());
//	            Vector sign=new Vector(v1);
//	            sign.subtract(v2);
//		        if (((sign.dotProduct(N1) > 0) && (sign.dotProduct(N2) > 0) 
//		        		&& (sign.dotProduct(N3) > 0)) || ((sign.dotProduct(N1) < 0) && 
//		        				(sign.dotProduct(N2) < 0) && (sign.dotProduct(N3) < 0))) 
//		        {
//		            return intersectionPoints;
//		        }
//		        intersectionPoints.clear();
//		        return intersectionPoints;
			
		}
	 			
	 		
	}





