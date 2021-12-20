package elements;


import java.util.ArrayList;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Camera
{
	private Point3D P0;//נקודת מרכז ההקרנה של המצלמה(0,0,0)
	private Vector vUp;//  (ציר ה- Y)וקטור בכיוון החיובי של המערכת כלפי מעלה ההמשך שלו בכיוון ההפוך הוא הווקטור שמצביע כלפי מטה
	private Vector vTo;//וקטור בכיוון קדימה מהצלמה
	private Vector vRight;//ציר האיקס וקטור לכיוון צד ימין של המצלמהתהפוך יתן לי את צד ימין
	
	//***************** Constructors ********************** // 
	public Camera(Point3D p0, Vector vUp, Vector vRight,Vector vTo )
	{
		super();
		this.P0 = new Point3D(p0);
		this.vUp = new Vector(vUp);
		this.vTo = new Vector(vTo);
		this.vRight = new Vector(vRight);
	} 
	public Camera(Camera x) 
	{
		super();
		this.P0 = new Point3D(x.P0);
		this.vUp = new Vector(x.vUp);
		this.vTo = new Vector(x.vTo);
		this.vRight = new Vector(x.vRight);
		}
	
	public Camera() 
	{
		this.P0 = new Point3D(0,0,0);
		this.vRight = new Vector(1,0,0);
		this.vTo = new Vector(0,0,-1);
		this.vUp = new Vector(0,1,0);
	}
	// ***************** Getters/Setters ********************** //
	public Point3D getP0() 
	{
		return new Point3D(P0);
	}
	public void setP0(Point3D p0) 
	{
		this.P0 = new Point3D(p0);
	}
	public Vector getvUp()
	{
		return new Vector(vUp);
	}
	public void setvUp(Vector VUp) 
	{
		this.vUp = new Vector(VUp);
	}
	public Vector getvTo() 
	{
		return new Vector(vTo);
	}
	public void setvTo(Vector VTo) 
	{
		this.vTo = new Vector(VTo);
	}
	public Vector getvRight()
	{
		return new Vector(vRight);
	}
	public void setvRight(Vector VRight) 
	{
		this.vRight = new Vector(VRight);
	}
	// ***************** Administration  ******************** // 
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Camera))
			return false;
		Camera other = (Camera) obj;
		if (P0 == null) 
		{
			if (other.P0 != null)
				return false;
		} 
		else 
			if (!P0.equals(other.P0))
				return false;
		if (vRight == null) 
		{
			if (other.vRight != null)
				return false;
		}
		else 
			if (!vRight.equals(other.vRight))
				return false;
		if (vTo == null) 
		{
			if (other.vTo != null)
				return false;
		}
		else 
			if (!vTo.equals(other.vTo))
				return false;
		if (vUp == null) 
		{
			if (other.vUp != null)
				return false;
		} 
		else 
			if (!vUp.equals(other.vUp))
		return false;
		return true;
	}
	
	@Override
	public String toString()
	{
		return " camera [P0=" + P0 + ", vUp=" + vUp + ", vTo=" + vTo + ", vRight=" + vRight + "]";
	}

//נק חיתוך של  הקרן היוצאת המצלמה עם העצם שממול	
	public Ray constructRayThroughPixel (int Nx, int Ny, double x, double y,double screenDist, double screenWidth,
		    double screenHeight)
			{
				//	 בונה וומחזירה את הקרן שעוברת במרכז פיקסל נתון
				// Nx מספר הפיקסלים לאורך המסך
				// Ny מספר הפיקסלים לרוחב המסך
				// i קאורדינטה  אנכית של פיקסל מבוקש
				// j קאורדינטה  אופקית של פיקסל מבוקש
				// screenDistance המרחק ממרכז המצלמה למרכז המשטח
				// screenWidth רוחב המסך ביחידות מדידה קבועות
				// screenHeight גובה המסך ביחידות מדידה קבועות
//				    double Rx = screenWidth / Nx;//רוחב המסך חלק מספר הפיקסלים באורך
//				    double Ry = screenHeight / Ny; //גובה המסך חלקי מספר הפיקסלים ברוחב
//				    Point3D Pc = new Point3D(this.P0); //יצירת נקודה שתהיה מרכז ההקרנה
//				    Vector vRtemp = new Vector(vRight);
//				    Vector vUtemp = new Vector(vUp);
//				    Vector vTtemp = new Vector(vTo);
//				    vTtemp.scale(screenDist);//כפל בסקלאר שהוא המרחק ממרכז המצלמה למרכז השמטח
//				    Pc.add(vTtemp);//חיבור נקודות בין מרכז ההקרנה לבין הנכפל בסקלאר
//				    vRtemp.scale((x-(Nx+1)/2.0)*Rx); 
//				    vUtemp.scale((y-(Ny+1)/2.0)*Ry);
//				    vRtemp.add(vUtemp);
//				    Pc.add(vRtemp);
//				    Vector temp=new Vector(this.P0);
//				    Pc.subtract(temp);
//				    Vector rayVector = new Vector(Pc);//וקטור הכיוון
//				    rayVector.normalize();
//				    return new Ray(new Point3D(this.P0), rayVector);
		Vector vto = new Vector(this.vTo);
		Vector vright = new Vector(this.vRight);
		Vector vup = new Vector(this.vUp);
		vto.scale(screenDist);
		Point3D pc = new Point3D(P0);
		pc.addPoint(vto.getHead());//pc
		double rx=screenWidth/Nx;
		double ry=screenHeight/Ny;	
		vright.scale((x-Nx/2.0)*rx+rx/2.0);
		vup.scale((y-Ny/2.0)*ry+ry/2.0);	
		vright.subtract(vup);
		pc.addPoint(vright.getHead());	
		Point3D p=new Point3D(pc);//p is pc after the changes
		Vector vec=new Vector(getP0(),p);
		vec.normalize();	
		return new Ray(new Point3D(p),vec);
			}



	public ArrayList<Ray> constructRayThroughPixel1 (int Nx, int Ny, double x, double y, 
			double screenDist, double screenWidth, double screenHeight)//שיפור 9 הקרניים
	{

		ArrayList<Ray> rays=new ArrayList<Ray>();
	// Calculating the image center
	Vector vToward = new Vector(this.vTo);
	vToward.scale(screenDist);

	// Calculating x-y ratios
	double Rx = screenWidth  / Nx; 
	double Ry = screenHeight / Ny; 

	// Calculating P - the intersection point

	Point3D Pc = new Point3D(this.P0);
	Pc.addPoint(vToward.getHead());
	Vector vRight = new Vector(this.vRight);
	Vector vUp = new Vector(this.vUp);

	vRight.scale(((x - (Nx*0.5)) * Rx + 0.5 * Rx));
	vUp.   scale(((y - (Ny*0.5)) * Ry + 0.5 * Ry));

	vRight.subtract(vUp);

	Pc.addPoint(vRight.getHead());

	Point3D P = new Point3D(Pc);
	// constructing ray between P0 and the intersection point
	double rx=0.25*Rx;
	double ry=0.25*Ry;
	Vector ray = new Vector(P0, P);
	Point3D P2=new Point3D(P.addPoint(new Point3D(rx,ry,0)));
	ray.normalize();
	Vector ray2 = new Vector(P0, P2);
	ray2.normalize();
	Point3D P3=new Point3D(P.addPoint(new Point3D(rx,-ry,0)));
	Vector ray3 = new Vector(P0,P3 );
	ray3.normalize();
	Point3D P4=new Point3D(P.addPoint(new Point3D(-rx,ry,0)));
	Vector ray4 = new Vector(P0,P4);
	ray4.normalize();
	Point3D P5=new Point3D(P.addPoint(new Point3D(-rx,-ry,0)));
	Vector ray5= new Vector(P0, P5);
	ray5.normalize();
	Point3D P6=new Point3D(P.addPoint(new Point3D((0)*Rx,-ry,0)));
	Vector ray6= new Vector(P0, P5);
	ray6.normalize();
	Point3D P7=new Point3D(P.addPoint(new Point3D((0)*Rx,ry,0)));
	Vector ray7= new Vector(P0, P5);
	ray7.normalize();
	Point3D P8=new Point3D(P.addPoint(new Point3D(-rx,(0)*Ry,0)));
	Vector ray8= new Vector(P0, P5);	
	ray8.normalize();
	Point3D P9=new Point3D(P.addPoint(new Point3D(rx,(0)*Ry,0)));
	Vector ray9= new Vector(P0, P5);
	ray9.normalize();
	rays.add(new Ray(P, ray));
//	rays.add(new Ray(P2, ray2));
//	rays.add(new Ray(P3, ray3));
//	rays.add(new Ray(P4, ray4));
//	rays.add(new Ray(P5, ray5));
//	rays.add(new Ray(P6, ray6));
//	rays.add(new Ray(P7, ray7));
//	rays.add(new Ray(P8, ray8));
	//rays.add(new Ray(P9, ray9));
	return rays;
	}
}




