public class Rectangle {
	Vector v1;
	Vector v2;
	
	// constructor
	Rectangle(Vector v1, Vector v2){
		this.v1 = v1;
		this.v2 = v2;
	}
	
	// getters and setters
	public Vector getV1() {
		return v1;
	}
	public void setV1(Vector v1) {
		this.v1 = v1;
	}
	public Vector getV2() {
		return v2;
	}
	public void setV2(Vector v2) {
		this.v2 = v2;
	}

	// method to calculate the width of the rectangle using vectors
	public double width() {
		return this.v2.x - this.v1.x;
	}
	
	// method to calculate the height of the rectangle using vectors
	public double height() {
		return this.v2.y - this.v1.y;
	}
	
	// method to calculate the area of the rectangle
	public double area() {
		return width() * height();
	}
	
	// method to calculate the center point of the rectangle
	public Vector center() {
		return (this.v1.add(this.v2).scale(0.5)); 
	}
	
	// method to return the attributes of the rectangle
	public void showRectangle() {
		System.out.println("\nVector v1:");
		this.v1.showVector();
		System.out.println("\nVector v2:");
		this.v2.showVector();
	}
	
	// method to check if a specific point (vector) is within the rectangle's area
	public boolean contains(Vector point) {
		return ((this.v1.x <= point.x && point.x <= this.v2.x) && (this.v1.y <= point.y && point.y <= this.v2.y));
	}
}
