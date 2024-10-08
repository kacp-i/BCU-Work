public class Rectangle {
	Vector v1;
	Vector v2;
	
	Rectangle(Vector v1, Vector v2){
		this.v1 = v1;
		this.v2 = v2;
	}
	
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

	public double width() {
		return this.v2.x - this.v1.x;
	}
	
	public double height() {
		return this.v2.y - this.v1.y;
	}
	
	public double area() {
		return width() * height();
	}
	
	public Vector center() {
		return this.v1.add(this.v2).scale(0.5); 
	}
	
	public void showRectangle() {
		System.out.println("Vector v1:\n");
		v1.showVector();
		System.out.println("Vector v2:\n");
		v2.showVector();
	}
	
	public boolean contains(Vector point) {
		return ((this.v1.x <= point.x && point.x <= this.v2.x) && (this.v1.y <= point.y && point.y <= this.v2.y));
	}
}
