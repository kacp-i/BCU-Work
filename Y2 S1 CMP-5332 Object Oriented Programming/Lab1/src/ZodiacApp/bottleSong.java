package ZodiacApp;

public class bottleSong {

	public static void main(String[] args) {
		// Exercise 4
		
		String word; // setting an empty var
		
		for(int n = 10; n > 0; n--) { // for loop to count down from 10 (by decrements of 1)
			if (n != 1) { // if n isnt 1, then the var "word" is bottles (plural)
				word = "bottles";
			}
			else { // if n is anything else then var "word" is bottle (singular)
				word = "bottle";
			}
			System.out.println(n + " green " + word + ", hanging on the wall"); // concatinating the string with the variable
			System.out.println(n + " green " + word + ", hanging on the wall");
			System.out.print("And if one green bottle");
			System.out.println(" should accidentally fall");
			
			if (n - 1 != 1) { // checks the condition of n - 1 and applies propriate word to the variable
				word = "bottles";
			}
			else {
				word = "bottle";
			}
			System.out.print("There'll be " + (n-1) + " green " + word); // concatinating the string with the variable
			System.out.println(" hanging on the wall");
			System.out.println();
		}
	}
}
