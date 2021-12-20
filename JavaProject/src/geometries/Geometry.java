package geometries;

import java.awt.Color;
import java.util.ArrayList;

import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import primitives.Ray;
public abstract class Geometry 
{
		private Color emmission;
		private Material material;
		ArrayList<Point3D> intersections = new ArrayList<Point3D>();
		//***************** Constructors ********************** //
		
		public Geometry(Color emmission1, Material material1)
		{
			//super();
			this.emmission = new Color(emmission1.getRed(),emmission1.getGreen(),emmission1.getBlue());
			this.material = new Material(material1);
		}
		public Geometry(Geometry g) //copy c-tor only for the material
		{
			//super();
			this.emmission = new Color (g.getEmmission().getRed(),g.getEmmission().getGreen(),g.getEmmission().getBlue());
			this.material = new Material(g.getMaterial());
		}

		public Geometry() //empty c-tor
		{
			super();
			this.emmission = Color.black;
			this.material = new Material();
		}
		public Geometry(Color emmission) //c-tor only for the emmission
		{
			super();
			if (emmission == null) 
	        {
	            this.emmission = new Color(255,255,255);
	        }
			else 
			{
	            this.emmission =emmission;
	        }	
			this.material=new Material();
			
	     }

		// ***************** Getters/Setters ********************** // 

		public Material getMaterial()
		{
			return material;
		}
		public void setMaterial(Material material)
		{
			this.material = new Material( material);
		}
	public Color getEmmission() 
		{
			if(emmission!=null)
		   		return  new Color(emmission.getRGB());//אם יש צבע תחזיר את מה שיש
			else
				
			return this.emmission= new Color(225,225,225);
		}	
		public void setEmmission(Color emmission)
		{
			if (emmission == null) 
	        {
	            this.emmission = new Color(255,255,255);
	        }
			else 
			{
	            this.emmission =new Color(emmission.getRGB());
	        }
		}

		// ***************** Administration  ******************** // 
		 public abstract Vector getNormal (Point3D p);
		 
		
		 public abstract ArrayList<Point3D> findIntersections (Ray r);


}