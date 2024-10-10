import org.junit.Test;
import static org.junit.Assert.*;

public class VendingMachineTest {
	// create a new object for each test
	VendingMachine test1 = new VendingMachine();
	VendingMachine test2 = new VendingMachine();
	VendingMachine test3 = new VendingMachine();
	VendingMachine test4 = new VendingMachine();
	VendingMachine test5 = new VendingMachine();
	VendingMachine test6 = new VendingMachine();
	
	@Test
	public void testInitialBalance() {
		assertEquals(0, test1.getBalance());
	}
	
	@Test
	public void testInitialRevenue() {
		assertEquals(0, test2.getRevenue());
	}
	
	@Test
	public void testInsertCoins() {
		test3.insertCoin();
		assertEquals(1, test3.balance);
	}
	
	@Test
	public void testRefund() {
		test4.insertCoin();
		test4.refund();
		assertEquals(0, test4.balance);
	}
	
	@Test
	public void testVendFailure() {
		assertFalse(test5.vendCandyBar());
	}
	
	@Test
	public void testVendSuccess() {
		test6.insertCoin();
		test6.insertCoin();
		test6.insertCoin();
		assertTrue(test6.vendCandyBar());
	}
}
