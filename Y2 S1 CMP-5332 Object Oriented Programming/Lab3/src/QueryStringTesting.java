import org.junit.Test;
import static org.junit.Assert.*;

public class QueryStringTesting {
	QueryString test1 = new QueryString("name=Alice&age=19&country=UK");
	QueryString test2 = new QueryString("");
	QueryString test3 = new QueryString("message=Hello%2C+world%21");
	
	@Test
	public void testParameterName() {
		assertEquals("Alice", test1.getParameter("name"));
		assertEquals("19", test1.getParameter("age"));
		assertEquals("UK", test1.getParameter("country"));
		assertNull(test1.getParameter("address"));
		assertNull(test2.getParameter("name"));
	}
	
	@Test
	public void testContainsParameter() {
		assertTrue(test1.hasParameter("name"));
		assertTrue(test1.hasParameter("age"));
		assertTrue(test1.hasParameter("country"));
		assertFalse(test1.hasParameter("address"));
		assertFalse(test1.hasParameter("count"));
		assertFalse(test2.hasParameter("name"));
	}
	
	@Test
	public void testParameterOffset() {
		assertEquals(0, test1.getParameterOffset("name"));
		assertEquals(11, test1.getParameterOffset("age"));
		assertEquals(18, test1.getParameterOffset("country"));
		assertEquals(-1, test1.getParameterOffset("address"));
	}
	
	@Test
	public void testDecode() {
		
	}
}
