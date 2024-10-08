import static java.lang.Math.PI;

public class Circle {
	double center;
	double radius;
	
	Circle(double center, double radius){
		this.center = center;
		this.radius = radius;
	}

	public double getCenter() {
		return center;
	}
	public void setCenter(double center) {
		this.center = center;
	}

	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public double diameter() {
		return 2 * this.radius;
	}
	
	public double area() {
		return Math.PI  * (this.radius * this.radius);
	}
	
	public boolean contains(Circle point) {
		
	}
}
