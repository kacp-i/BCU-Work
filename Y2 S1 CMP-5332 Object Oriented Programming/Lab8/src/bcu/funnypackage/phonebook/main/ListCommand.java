package bcu.funnypackage.phonebook.main;

import bcu.funnypackage.phonebook.model.*;

public class ListCommand implements Command {
	public ListCommand(String[] parts) throws InvalidCommandException{
		if (parts.length != 1) {
			throw new InvalidCommandException();
		}
	}
		
	@Override
	public void execute(PhoneBook phonebook) throws NotPresentException{
		phonebook.getAllNames();
	}
}
