package ZodiacApp;

public class ageChecker {
	public static void main(String[] args) {
		// Exercise 2
		
		double age = 19; // sets the age
		
		if (age >= 68) { // any age above or equal to 68 is a pensioner
			System.out.println("\nYou're a pensioner");
		} 
		else if (age >= 18) { // any age above 18 (but below 68) is an adult
			System.out.println("\nYou're an adult");
		}
		else if (age >= 12 && age < 18) { // any age above or equal to 12 and lower than 18 is a young adult
			System.out.println("\nYou'e a young adult");
		}
		else if (age >= 5 && age < 12) { // any age above or equal to 5 and lower than 12 is a child
			System.out.println("\nYou're a child");
		}
		else if (age >= 3 && age < 5) { // any age above or equal to 3 and lower than 5 is a toddler
			System.out.println("\nYou're a toddler");
		}
		else if (age < 0) { // any age below 0 (negative) is invalid
			System.out.println("\nYour age is invalid");
		}
		else { // any other age (anything below 3) is an infant
			System.out.println("\nYou're an infant ");
		}	
	}
}
