package scene;

import java.awt.Color;

import java.util.ArrayList;
import java.util.Iterator;

//import Elements.LightSource;
//import Geometry.Geometries;
//import Scene.List;
import elements.AmbientLight;
//import elements.AmbientLight;
import elements.Camera;
import elements.Light;

import geometries.Geometry;

public class Scene 
{
	 private String sceneName;
	 private Color background;
	// private AmbientLight ambientLight;
	 private ArrayList<Geometry> geometries;
	 private Camera camera;
     private double screenDistance=100;//מרחק המצלמה מהמסך
     private AmbientLight ambientLight ;
     private ArrayList<Light> arrListLight;
   
	//***************** Constructors ********************** // 
   

   public Scene(String sceneName, Color background, ArrayList<Geometry> g, Camera camera,
			double screenDistance, AmbientLight ambientLight, ArrayList<Light> al)
    {
	   setSceneName(sceneName);
	   setBackground(background);
	   this.geometries =  new ArrayList<Geometry>();
	   for(int i=0;i<g.size();i++)
	   {
		   geometries.add(g.get(i));
	   }
	   setCamera(camera);
	   setScreenDistance(screenDistance);
	   this.ambientLight = new AmbientLight(ambientLight);
	   this.arrListLight = new ArrayList<Light>();
	   for(int i=0;i<al.size();i++)
	   {
		   arrListLight.add(al.get(i));
	   }
//		this.sceneName =  new String(sceneName);
//		this.background = background;
//		this.geometries =  new ArrayList<Geometry>(geometries);
//		this.camera = new Camera(camera);
//		this.screenDistance = screenDistance;
//		this.ambientLight = new AmbientLight(ambientLight);
//		this.arrListLight = new ArrayList<Light>(arrListLight);
	}

public Scene(Scene s) 
   {
		this.sceneName = s.sceneName;
	this.background = s.getBackground();
		//this.background = new Color(s.background.getRed(), s.background.getGreen(),s.background.getBlue());
		this.geometries = new ArrayList<Geometry>(); 
		   for(int i=0;i<s.geometries.size();i++)
		   {
			   geometries.add(s.geometries.get(i));
		   }
	//	this.camera = new Camera(s.camera);
		this.camera = new Camera(s.camera);
		setScreenDistance(s.screenDistance);
		this.ambientLight = new AmbientLight(s.ambientLight);
		this.arrListLight = new ArrayList<Light>();
		   for(int i=0;i<s.arrListLight.size();i++)
		   {
			   arrListLight.add(s.arrListLight.get(i));
		   }
	}
   public Scene()
   {
	   super();
	   this.sceneName = "";
		this.background = new Color(0,0,0);
		this.geometries = new ArrayList<Geometry>(); 
		this.camera = new Camera();
		this.screenDistance =100;
		this.ambientLight =new AmbientLight() ;
		this.arrListLight =new ArrayList<Light>();	
   }	

// ***************** Getters/Setters ********************** //
   
    public String getSceneName() 
    {
		return sceneName;
	}
	public void setSceneName(String sceneName) 
	{
		 if (sceneName == null) 
	        {
	            sceneName = "";
	         } 
		 else 
		 {
	           this.sceneName = sceneName;
	      }
	}
	public Color getBackground() 
	{
		return new Color(background.getRGB());
	}
	public void setBackground(Color background) 
	{
		if (background == null) 
        {
            this.background = new Color(255,255,255);
        }
		else 
		{
            this.background =new Color(background.getRGB());
        }
	}

	public ArrayList<Geometry> getGeometries() 
	{
		ArrayList<Geometry> list = new ArrayList<Geometry>();
        for (Iterator<Geometry> iterator = this.geometries.iterator(); iterator.hasNext();) 
        {
        	Geometry next = iterator.next();
            list.add(next);
        }
        return list;
	}
	
	public void setGeometries(ArrayList<Geometry> geometries1)
	{
		if (geometries == null) 
        {
            this.geometries= new ArrayList<Geometry>();
        }
		this.geometries = new ArrayList<Geometry>(geometries1);
	}
	
	public Camera getCamera() 
	{
		return new Camera(camera);
	}
	public void setCamera(Camera camera) 
	{
		this.camera = new Camera(camera);
	}
	public double getScreenDistance() 
	{
		return screenDistance;
	}
	public void setScreenDistance(double screenDistance)
	{
		this.screenDistance = screenDistance;
	}
	   public AmbientLight getAmbientLight() {
			return ambientLight;
		}
		public void setAmbientLight(AmbientLight ambientLight) 
		{
			this.ambientLight = new AmbientLight(ambientLight);
		}
		public ArrayList<Light> getArrListLight()
		{
			ArrayList<Light> list = new ArrayList<Light>();
	        for (Iterator<Light> iterator = this.arrListLight.iterator(); iterator.hasNext();) 
	        {
	        	Light next = iterator.next();
	            list.add(next);
	        }
	        return list;
		}
		public void setArrListLight(ArrayList<Light> l) //copy c-tor
		{
			if (l == null) 
	        {
	            this.arrListLight = new ArrayList<Light>();
	        }
			this.arrListLight = new ArrayList<Light>(l);
		}	
	// ******************** Administration  *********************** //  
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Scene other = (Scene) obj;
		if (background == null) {
			if (other.background != null)
				return false;
		} else if (!background.equals(other.background))
			return false;
		if (camera == null) {
			if (other.camera != null)
				return false;
		} else if (!camera.equals(other.camera))
			return false;
		if (geometries == null) {
			if (other.geometries != null)
				return false;
		} else if (!geometries.equals(other.geometries))
			return false;
		if (sceneName == null) {
			if (other.sceneName != null)
				return false;
		} else if (!sceneName.equals(other.sceneName))
			return false;
		if (Double.doubleToLongBits(screenDistance) != Double.doubleToLongBits(other.screenDistance))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Scene [sceneName=" + sceneName + ", background=" + background 
				+ ", geometries=" + geometries + ", camera=" + camera + ", screenDistance=" + screenDistance + "]";
	} 
	//add function
	public void addGeometry(Geometry x)
	{
		if(x!=null)
		{
		this.geometries.add(x);	
		}
	}
	public void addLight(Light l) 
	{
		this.arrListLight.add(l);
	}
//	public List<Geometries> addGeometry (Geometries g)
//	{
//		this.geometries1.add(g);
//		return this.geometries1;
//		
//	}
	//iterators
	public Iterator<Light> getLightsIterator ()//from the...
	{
		return arrListLight.iterator();
	}
	public Iterator<Geometry> getGeometriesIterator()//from the...
	{
		return geometries.iterator();
	} 

}

