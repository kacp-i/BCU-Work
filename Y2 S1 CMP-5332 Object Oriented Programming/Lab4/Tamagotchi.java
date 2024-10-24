public class Tamagotchi {
	private int tiredness;
	private int hunger;
	private int cleanness;
	private int happiness;
	private String status = "";
	
	// constructor
	public Tamagotchi() {
		tiredness = 0;
		hunger = 0;
		cleanness = 10;
		happiness = 10;
		status = "ok";
	}
	
	
	// getters
	public int getTiredness() {
		return tiredness;
	}
	public int getHunger() {
		return hunger;
	}
	public int getCleanness() {
		return cleanness;
	}
	public int getHappiness() {
		return happiness;
	}
	public String getStatus() {
		return status;
	}


	// setting the mood of the tamagotchi based on specified parameters
	private void conditionCheck() {
		paramCheck();
		
		if (tiredness == 10) {
			status = "asleep";
		}
		else if (tiredness >= 8) {
			status = "tired";
		}
		
		if (hunger >= 7) {
			status = "hungry";
		}
		
		if (cleanness < 3) {
			status = "dirty";
		}
		
		if (happiness >= 8) {
			status = "happy";
		}
		else if (happiness >= 4) {
			status = "ok";
		}
		else {
			status = "sad";
		}
	}
	
	// reseting parameters if they are over 10 or under 0
	private void paramCheck() {
		if (tiredness > 10) {
			tiredness = 10;
		}
		if (tiredness < 0) {
			tiredness = 0;
		}
		
		if (hunger > 10) {
			hunger = 10;
		}
		if (hunger < 0) {
			hunger = 0;
		}
		
		if (cleanness > 10) {
			cleanness = 10;
		}
		if (cleanness < 0) {
			cleanness = 0;
		}
		
		if (happiness > 10) {
			happiness = 10;
		}
		if (happiness < 0) {
			happiness = 0;
		}
	}
	
	
	// action methods
	public void feed() {
		hunger -= 5;
	}
	
	public void walk() {
		happiness += 3;
		tiredness += 2;
	}
	
	public void pet() {
		happiness += 5;
	}
	
	public void clean() {
		cleanness += 10;
	}
	
	
	// get the tamagotchi's current mood
	public void moodCheck() {
		System.out.println("Current mood of your pet: " + status);
	}
	
	
	// method to pass time
	public void passTime() {
		hunger += 1;
		cleanness -= 1;
		
		if (tiredness == 10) {
			tiredness = 0;
		}
		else {
			tiredness += 1;
		}
		
		if (hunger >= 7) {
			happiness -= 1;
		}
		else if (hunger < 3) {
			happiness -= 3;
		}
		
		paramCheck();
	}
	
	// starts the game
	public void startGame() {
		System.out.println("Welcome to this amazing Tamagotchi game :D");
		userChoice();
	}
	
	// allows the user to interact with the pet
	private void userChoice() {
		stats();
		System.out.println("\nYour actions:\n1 - Feed the pet\n2 - Walk the pet\n3 - Pet the pet\n4 - Clean the pet\n5 - Pass time\n");
		String userGuess = System.console().readLine("Your action: ");
		int choice = Integer.valueOf(userGuess);
		
		switch (choice) {
		case 1:
			feed();
			break;
		case 2:
			walk();
			break;
		case 3:
			pet();
			break;
		case 4:
			clean();
			break;
		case 5:
			passTime();
			break;
		default:
			System.out.println("Invalid Input");
		}
		
		conditionCheck();
		moodCheck();
		userChoice();
	}
	
	
	// shows the stats of the pet
	private void stats() {
		System.out.println("Your pet's stats:\nTiredness: " + tiredness + "\nHunger: " + hunger + "\nCleanness: " + cleanness + "\nHappiness: " + happiness);
	}
}
