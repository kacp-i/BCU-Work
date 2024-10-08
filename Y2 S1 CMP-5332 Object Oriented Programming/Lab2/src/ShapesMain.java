public class ShapesMain {

	public static void main(String[] args) {
		Vector samp = new Vector(2,4);
		Vector samp2 = new Vector(6,12);
		Vector samp3 = new Vector(4,8);
		samp.add(samp2);
		samp.showVector();
		
		Rectangle shape = new Rectangle(samp,samp2);
		System.out.println(shape.contains(samp3));
	}

}
