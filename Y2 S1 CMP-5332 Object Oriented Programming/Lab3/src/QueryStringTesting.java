import org.junit.Test;
import static org.junit.Assert.*;

public class QueryStringTesting {
	QueryString test1 = new QueryString("name=Alice&age=19&country=UK");
	QueryString test2 = new QueryString("");
	QueryString test3 = new QueryString("message=Hello%2C+world%21");
	QueryString test4 = new QueryString("message=%5 B+Hello+%40everyone+%3B%29+% 5D");
	QueryString test5 = new QueryString("message=%5B+Hello+%40everyone+%3B%29+%5D");
	
	@Test
	public void testParameterName() {
		// Testing String Query 1
		assertEquals("Alice", test1.getParameter("name"));
		assertEquals("19", test1.getParameter("age"));
		assertEquals("UK", test1.getParameter("country"));
		assertNull(test1.getParameter("address"));
		assertNull("Alice", test1.getParameter("Name"));
		assertNull("", test1.getParameter(""));
		
		// Testing String Query 2
		assertNull(test2.getParameter("name"));
		assertNull(test2.getParameter(""));
		assertNull(test2.getParameter(null));
		
		// Testing String Query 3
		assertEquals("Hello, world!", test3.getParameter("message"));
		assertNull(test3.getParameter("other"));
		
		// Testing String Query 4
		assertEquals("[ Hello @everyone ;) ]", test4.getParameter("message"));
		assertNull(test4.getParameter("other"));
		
		// Testing String Query 5
		assertEquals("[ Hello @everyone ;) ]", test5.getParameter("message"));
		assertNull(test5.getParameter("other"));
	}
	
	@Test
	public void testContainsParameter() {
		// Testing String Query 1
		assertTrue(test1.hasParameter("name"));
		assertTrue(test1.hasParameter("age"));
		assertTrue(test1.hasParameter("country"));
		assertFalse(test1.hasParameter("address"));
		assertFalse(test1.hasParameter("count"));
		assertFalse(test1.hasParameter(""));
		
		// Testing String Query 2
		assertFalse(test2.hasParameter("name"));
		assertFalse(test2.hasParameter(""));
		assertFalse(test2.hasParameter(null));
		
		// Testing String Query 3
		assertTrue(test3.hasParameter("message"));
		assertFalse(test3.hasParameter("other"));
		
		// Testing String Query 4
		assertTrue(test4.hasParameter("message"));
		assertFalse(test4.hasParameter("other"));
		
		// Testing String Query 5
		assertTrue(test5.hasParameter("message"));
		assertFalse(test5.hasParameter("other"));
	}
	
	@Test
	public void testParameterOffset() {
		// Testing String Query 1
		assertEquals(0, test1.getParameterOffset("name"));
		assertEquals(11, test1.getParameterOffset("age"));
		assertEquals(18, test1.getParameterOffset("country"));
		assertEquals(-1, test1.getParameterOffset("address"));
		
		// Testing String Query 2
		assertEquals(-1, test2.getParameterOffset("other"));
		
		// Testing String Query 3
		assertEquals(0, test3.getParameterOffset("message"));
		assertEquals(-1, test3.getParameterOffset("other"));
		
		// Testing String Query 4
		assertEquals(0, test4.getParameterOffset("message"));
		assertEquals(-1, test4.getParameterOffset("other"));
		
		// Testing String Query 5
		assertEquals(0, test5.getParameterOffset("message"));
		assertEquals(-1, test5.getParameterOffset("other"));
	}
	
	@Test
	public void testDecode() {
		// Testing String Query 3
		System.out.println(test3.decode(test3.getParameter("message")));
		
		// Testing String Query 4
		System.out.println(test4.decode(test4.getParameter("message")));
		
		// Testing String Query 5
		System.out.println(test5.decode(test5.getParameter("message")));
	}
}
