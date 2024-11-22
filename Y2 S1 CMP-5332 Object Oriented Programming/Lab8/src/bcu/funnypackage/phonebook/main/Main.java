package bcu.funnypackage.phonebook.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import bcu.funnypackage.phonebook.model.*;

public class Main {
	public static final String HELP_MESSAGE = "Commands:\n" + "    add [name] [phoneNumber]        add a new entry\n"
			+ "    show [name]                     show an entry\n"
			+ "    update [name] [phoneNumber]     update an entry\n"
			+ "    remove [name]                   remove an entry\n"
			+ "    list                            show all names\n"
			+ "    help                            show this help message\n"
			+ "    exit                            exit the program";

	public static void main(String[] args) throws IOException {
		new Main().run();
	}

	private final PhoneBook phoneBook;

	public Main() {
		this.phoneBook = new PhoneBook();
	}

	public Main(PhoneBook phoneBook) {
		this.phoneBook = phoneBook;
	}

	public void run() throws IOException {
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Address book");
		while (true) {
			System.out.print("> ");
			String command = keyboard.readLine();
			if ("exit".equalsIgnoreCase(command)) {
				break;
			}

			try {
				parseAndExecute(command);
			} catch (AlreadyPresentException ex) {
				System.out.println("The entry for " + ex.getName() + " already exists.");
			} catch (NotPresentException ex) {
				System.out.println("The entry for " + ex.getName() + " does not exist.");
			} catch (InvalidCommandException ex) {
				System.out.println("Invalid command (enter 'help' to see the valid commands).");
			}
		}
	}

	public Command parse(String command) throws InvalidCommandException {
		String[] parts = command.split(" ");
		String firstPart = parts[0];

		if ("add".equalsIgnoreCase(firstPart)) {
			Command addCommand = new AddCommand(parts);
			return addCommand;
		} 
		else if ("show".equalsIgnoreCase(firstPart)) {
			Command showCommand = new ShowCommand(parts);
			return showCommand;
		}
		else if ("update".equalsIgnoreCase(firstPart)) {
			Command updateCommand = new UpdateCommand(parts);
			return updateCommand;
		}
		else if ("remove".equalsIgnoreCase(firstPart)) {
			Command removeCommand = new RemoveCommand(parts);
			return removeCommand;
		}
		else if ("list".equalsIgnoreCase(firstPart)) {
			Command listCommand = new ListCommand(parts);
			return listCommand;
		}
		else if ("help".equalsIgnoreCase(firstPart)) {
			Command helpCommand = new HelpCommand(parts);
			return helpCommand;
		} else {
			throw new InvalidCommandException();
		}
	}

	public void parseAndExecute(String command) throws InvalidCommandException, AlreadyPresentException, NotPresentException{
		
		Command cmd = parse(command);
		cmd.execute(this.phoneBook);
	}
}
