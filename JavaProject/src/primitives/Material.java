package primitives;

public class Material 
{

	private double Kd; // Diffusion attenuation coefficient – פיזור האור 
	private double Ks; // Specular attenuation coefficient – החזרת האור
	private double nShininess;//Refraction index
	private double Kr;//reflect//מקדם השתקפות
	private double Kt;//מקדם שקיפות.//tanspernt
	//***************** Constructors ********************** //
	public Material(Material m) {
		super();
		Kd =m.Kd ;
		Ks = m.Ks;
		this.nShininess = m.nShininess;
		Kr = m.Kr;
		Kt = m.Kt;
	}
	public Material() {
		//super();
		Kd = 1.0;
		Ks = 1.0;
		this.nShininess = 1.0;
		Kr = 0;
		Kt = 0;
	}
	
	public Material(double kd, double ks, double nShininess, double kr, double kq) {
		super();
		Kd = kd;
		Ks = ks;
		this.nShininess = nShininess;
		Kr = kr;
		Kt = kq;
	}
	// ***************** Getters/Setters ********************** //
	public double getKd() {
		return Kd;
	}

	public void setKd(double kd) {
		this.Kd = kd;
	}
	public double getKs() {
		return Ks;
	}
	public void setKs(double ks) {
		this.Ks = ks;
	}	
	public double getnShininess() {
		return nShininess;
	}
	public void setnShininess(double nShininess) {
		this.nShininess = nShininess;
	}
	public double getKr() {
		return Kr;
	}
	public void setKr(double kr) {
		this.Kr = kr;
	}
	public double getKt() {
		return Kt;
	}
	public void setKt(double kt) {
		this.Kt = kt;
	}
	// ***************** Administration  ******************** // 
	@Override
	public String toString() {
		return "Material [Kd=" + Kd + ", Ks=" + Ks + ", n=" + nShininess + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(Kd);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(Kr);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(Ks);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(Kt);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(nShininess);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Material other = (Material) obj;
		if (Double.doubleToLongBits(Kd) != Double.doubleToLongBits(other.Kd))
			return false;
		if (Double.doubleToLongBits(Kr) != Double.doubleToLongBits(other.Kr))
			return false;
		if (Double.doubleToLongBits(Ks) != Double.doubleToLongBits(other.Ks))
			return false;
		if (Double.doubleToLongBits(Kt) != Double.doubleToLongBits(other.Kt))
			return false;
		if (Double.doubleToLongBits(nShininess) != Double.doubleToLongBits(other.nShininess))
			return false;
		return true;
	}


	
	
	
}
