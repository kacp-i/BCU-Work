import static java.lang.Math.PI;

public class Circle {
	Vector center;
	double radius;
	
	// constructor
	Circle(Vector center, double radius){
		this.center = center;
		this.radius = radius;
	}

	// getters and setters
	public Vector getCenter() {
		return center;
	}
	public void setCenter(Vector center) {
		this.center = center;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	// method to calculate the diameter of the circle
	public double diameter() {
		return 2 * this.radius;
	}
	
	// method to calculate the area of the circle
	public double area() {
		return Math.PI  * (this.radius * this.radius);
	}
	
	// method to check if a point is within the circle's area
	public boolean contains(Vector point) {
		Vector offset = point.subtract(center);
		return offset.length() <= radius;
	}
	
	// method to calculate a rectangle within the circle
	public Rectangle boundingBox() {
		double cx = center.x;
		double cy = center.y;
		Vector p1 = new Vector(cx - radius, cy - radius);
		Vector p2 = new Vector(cx + radius, cy + radius);
		return new Rectangle(p1, p2);
	}
}
