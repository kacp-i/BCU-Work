public class VendingMachineMain {

	public static void main(String[] args) {
		// Test 1
		// creating a vending machine object
		VendingMachine test1 = new VendingMachine();
		
		// testing the initial balance
		System.out.println("Checking the balance: " + test1.getBalance());
		
		
		// Test 2
		// creating a vending machine object
		VendingMachine test2 = new VendingMachine();
		
		// testing the initial revenue
		System.out.println("\nChecking the revenue: " + test2.getRevenue());
		
		
		// Test 3
		// creating a vending machine object
		VendingMachine test3 = new VendingMachine();
		
		// adding coins and checking balance and revenue - revenue shouldnt increase but balance should
		test3.insertCoin();
		test3.insertCoin();
		test3.insertCoin();
		test3.insertCoin();
		System.out.println("\nChecking the balance: " + test3.getBalance());
		System.out.println("Checking the revenue: " + test3.getRevenue());
		
		
		// Test 4
		// creating a vending machine object
		VendingMachine test4 = new VendingMachine();
		
		// if a refund is called, the balance should be reset
		test4.insertCoin();
		System.out.println("\nRefunded " + test4.refund());
		System.out.println("Checking the balance: " + test4.getBalance());
		System.out.println("Checking the revenue: " + test4.getRevenue());
		
		
		// Test 5
		// creating a vending machine object
		VendingMachine test5 = new VendingMachine();
		
		// if there is no balance then a candy bar should not be vended
		System.out.println("\nChecking the balance: " + test5.getBalance());
		System.out.println(test5.vendCandyBar());
		
		
		// Test 6
		// creating a vending machine object
		VendingMachine test6 = new VendingMachine();
		
		// if there is a balance of 3 then a candy bar should be vended
		// then 3 should be removed from balance and 3 should be added to revenue
		test6.insertCoin();
		test6.insertCoin();
		test6.insertCoin();
		System.out.println("\nChecking the balance: " + test6.getBalance());
		System.out.println(test6.vendCandyBar());
		System.out.println("Checking the balance: " + test6.getBalance());
		System.out.println("Checking the revenue: " + test6.getRevenue());
	}

}
