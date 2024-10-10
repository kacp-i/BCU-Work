
public class QTest {

	public static void main(String[] args) {
		QueryString test1 = new QueryString("name=Alice&age=19&country=UK");
		QueryString test2 = new QueryString("message=Hello%2C+world%21");
		
		System.out.println(test1.getParameterOffset("age"));
	}

}
