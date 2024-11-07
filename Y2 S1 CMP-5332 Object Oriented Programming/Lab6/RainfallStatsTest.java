import org.junit.Test;
import static org.junit.Assert.*;

public class RainfallStatsTest {
	private final RainfallStats stats = new RainfallStats();
	 
	/*
	 * The count should initially be 0.
	 */
	@Test
	public void testInitialState() {
		assertEquals(0, stats.getCount());
	}
	
	/*
	 * If no measurements have been added, the getMean method
	 * should throw an IllegalStateException.
	 */
	@Test
	public void testMeanOfNoMeasurements() {
		// write unit test
		Exception exception = assertThrows(IllegalStateException.class, () -> stats.getMean());
		
		assertTrue(exception.getMessage().contains("No measurements have been added yet"));
	}
	
	/*
	 * If no measurements have been added, the getMax method
	 * should throw an IllegalStateException.
	 */
	@Test
	public void testMaxOfNoMeasurements() {
		// write unit test
		Exception exception = assertThrows(IllegalStateException.class, () -> stats.getMax());
		
		assertTrue(exception.getMessage().contains("No measurements have been added yet"));
	}
	
	/*
	 * If one measurement is added, the count should be one,
	 * and the mean and max should equal that measurement.
	 */
	@Test
	public void testAddMeasurement() throws InvalidRainfallException {
		stats.addMeasure(4.0);
		assertEquals(1, stats.getCount());
		assertEquals(4.0, stats.getMean(), 0);
		assertEquals(4.0, stats.getMax(), 0);
	}
	
	/*
	 * The addMeasurement method should throw an InvalidRainfallException
	 * if the measurement is negative.
	 */
	@Test
	public void testAddInvalidMeasurement() {
		// write unit test
		Exception exception = assertThrows(InvalidRainfallException.class, () -> stats.addMeasure(-2.0));
		
		assertTrue(exception.getMessage().contains("Measurement must not be negative."));
	}
	
	/*
	 * After three measurements are added, the count should be 3.
	 */
	@Test
	public void testCount() throws InvalidRainfallException {
		stats.addMeasure(3.0);
		stats.addMeasure(5.0);
		stats.addMeasure(4.0);
		assertEquals(3, stats.getCount());
	}
	
	/*
	 * Test the mean of three measurements.
	 */
	@Test
	public void testMean() throws InvalidRainfallException {
		stats.addMeasure(3.0);
		stats.addMeasure(5.0);
		stats.addMeasure(4.0);
		assertEquals(4.0, stats.getMean(), 0);
	}
	
	/*
	 * Test the max of three measurements.
	 */
	@Test
	public void testMax() throws InvalidRainfallException {
		stats.addMeasure(3.0);
		stats.addMeasure(5.0);
		stats.addMeasure(4.0);
		assertEquals(5.0, stats.getMax(), 0);
	}
}
