import static java.lang.Math.sqrt;

public class Vector {
	double x;
	double y;
	
	Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}

	public Vector add(Vector other) {
		double newX = this.x + other.x;
		double newY = this.y + other.y;
		
		return new Vector(newX, newY);	
	}
	
	public Vector subtract (Vector other) {
		double newX = this.x - other.x;
		double newY = this.y - other.y;
		
		return new Vector(newX, newY);
	}
	
	public Vector scale (double factor) {
		double newX = this.x * factor;
		double newY = this.y * factor;
		
		return new Vector(newX, newY);
	}
	
	public double length () {
		double squared = (this.x * this.x) + (this.y * this.y);
		return sqrt(squared);
	}
	
	public void showVector() {
		System.out.println("Vector x " + this.x);
		System.out.println("Vector y " + this.y);
		System.out.println("The lenght of the vector is " + length());
	}
}
