public class GuessingGame {
	private int numToGuess;
	private int totalGuessesLeft;
	private boolean gameOver;
	private boolean gameWon;
	
	// constructor
	public GuessingGame() {
		numToGuess = (int) ((Math.random() * 100) + 1); 
		totalGuessesLeft = 10;
		gameOver = false;
		gameWon = false;
	}
	
	// getters
	public int getNumToGuess() {
		return numToGuess;
	}
	public int getGuessesLeft() {
		return totalGuessesLeft;
	}
	public boolean isGameOver() {
		return gameOver;
	}
	public boolean isGameWon() {
		return gameWon;
	}
	
	// methods for functionality
	public int guessTaken() {
		totalGuessesLeft -= 1;
		return totalGuessesLeft;
	}
	
	public void userGuess() {
		while (gameOver == false) {
			String userGuess = System.console().readLine("\nYour guess: ");
			guessTaken();
			System.out.println(checkGuess(Integer.valueOf(userGuess)));
			System.out.println("Your remaining guesses: " + getGuessesLeft());
			checkGameStatus();
		}
	}
	
	public void startGame() {
		System.out.println("Welcome to this amazing guessing game :D\nYou have a total of " + totalGuessesLeft + " guesses for this game\nThe number is from 1 - 100\nGood Luck !");
		userGuess();
	}
	
	public String checkGuess(int guess) {
		String message = "";
		
		if (guess == numToGuess) {
			message = "Your guess is correct";
			gameOver = true;
			gameWon = true;
		}
		else if (guess < numToGuess) {
			message = "Your guess is too low";
		}
		else {
			message = "Your guess is too high";
		}
		
		return message;
	}
	
	public void checkGameStatus() {
		if (totalGuessesLeft <= 0) {
			gameOver = true;
			System.out.println("The number was " + numToGuess);
		}
	}
}
