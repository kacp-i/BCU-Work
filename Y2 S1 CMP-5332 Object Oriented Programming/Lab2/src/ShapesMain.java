public class ShapesMain {

	public static void main(String[] args) {
		System.out.println("Testing Vector Class:");
		Vector samp = new Vector(2,4);
		Vector samp2 = new Vector(6,12);
		Vector samp3 = new Vector(4,8);
		samp.add(samp2);
		samp.showVector();
		
		System.out.println("\nTesting Rectangle Class:");
		Rectangle sampRectangle = new Rectangle(samp,samp2);
		System.out.println(sampRectangle.width());
		System.out.println(sampRectangle.height());
		System.out.println(sampRectangle.area());
		sampRectangle.center().showVector();
		sampRectangle.showRectangle();
		
		System.out.println("\nTesting Circle Class:");
		Circle sampCircle = new Circle(samp2, 8);
		System.out.println(sampCircle.diameter());
		System.out.println(sampCircle.area());
		System.out.println(sampCircle.contains(samp3));
		sampCircle.boundingBox().showRectangle();
	}

}
