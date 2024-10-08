package ZodiacApp;

public class evaluateExpressions {

	public static void main(String[] args) {
		System.out.println("Arithmetic Operations");
		System.out.println(1+1);
		System.out.println(5 - 2 * 3);
		System.out.println((5 - 2) * 3);
		System.out.println(4.5 + 6.7);
		System.out.println(3 - 2.1);
		System.out.println(6 / 2);
		System.out.println(7 / 2);
		System.out.println(7.0 / 2.0);
		System.out.println(8 % 2);
		System.out.println(9 % 2);
		
		System.out.println("\nComparison Operations");
		System.out.println(1 + 1 == 2);
		System.out.println(1 + 1 != 3);
		System.out.println(1 < 3);
		System.out.println(1 > 3);
		System.out.println(3 <= 3);
		System.out.println(3 >= 1);
		
		System.out.println("\nLogical Operations");
		System.out.println(true && false);
		System.out.println(true || false);
		System.out.println(!false);
		
		System.out.println("\nString Operations");
		System.out.println("Hello," + "world!");
		System.out.println("Catch " + 22);
		System.out.println("A piece of string".length());
		System.out.println("ABCDEFG".charAt(3));
		System.out.println("MMXVIII".toLowerCase());
		System.out.println("Yellow Submarine".startsWith("Yellow"));
		
		System.out.println("\nType Conversions");
		System.out.println((double) 5);
		System.out.println((int) 5.3);
		System.out.println((int) 'a');
		System.out.println((char) 120);
		System.out.println(String.valueOf(1234));
		System.out.println(Integer.parseInt("5678"));
		System.out.println(Double.parseDouble("3.14159"));
		
		System.out.println("\nTypes");
		System.out.println(1 + 1.5); // will print 2.5
		System.out.println("a" + "b"); // will print ab
		System.out.println("1" + 1.5); // will print 11.5
		System.out.println('a' + 'b'); // thought it would print ab but it prints 195 (because it takes the ascii value)
		System.out.println('a' + 1 ); // will print 98
		System.out.println(false || !false); // will print true
		System.out.println("Hello".length()); // will print 5
		
		// System.out.println(1 && 2); // wont run because its using a bool expression on numbers
		// System.out.println("a" - "b"); // wont run because its subtracting two non-numerical values
		// System.out.println(true + false); // wont run because its adding two bool values
		// System.out.println((1 + 2).length()); // wont run because it cant take the length of a non-string value
		
		System.out.println("\nVairables");
		int x;
		x = 5;
		int y = 8;
		x += y;
		System.out.println(x);
		
		x = 11;
		x += 23;
		System.out.println(x);
		
		// x = 6.28; // wont work as you cant change an int into a double
		// x = "Hello" // wont work as you cant change an int into a string
		
		double pi = 3.14159;
		char letter = 's';
		String name = "Ada Lovelance";
		System.out.println(pi);
		System.out.println(letter);
		System.out.println(name);
		
		System.out.println("\nWorking out correct var types");
		int age = 19;
		char grade = 'A';
		double gigaWatts = 1.21;
		boolean isBlue = true;
		String phoneNumber = "555-1234";
		System.out.println(age);
		System.out.println(grade);
		System.out.println(gigaWatts);
		System.out.println(isBlue);
		System.out.println(phoneNumber);
	}

}
