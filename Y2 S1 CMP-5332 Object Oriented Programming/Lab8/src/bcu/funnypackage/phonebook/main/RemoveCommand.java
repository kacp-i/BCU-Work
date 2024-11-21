package bcu.funnypackage.phonebook.main;

import bcu.funnypackage.phonebook.model.*;

public class RemoveCommand implements Command {
	private final String name;
	
	public RemoveCommand(String[] parts) throws InvalidCommandException{
		if (parts.length != 2) {
			throw new InvalidCommandException();
		}
		this.name = parts[1];
	}
	
	@Override
	public void execute(PhoneBook phonebook) throws NotPresentException{
		phonebook.removeEntry(name);
		System.out.println("Entry Removed.");
	}
}
