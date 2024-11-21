package bcu.funnypackage.phonebook.main;

import bcu.funnypackage.phonebook.model.*;

public class HelpCommand implements Command {
	public HelpCommand(String[] parts) throws InvalidCommandException{
		if (parts.length != 1) {
			throw new InvalidCommandException();
		}
	}
	
	@Override
	public void execute(PhoneBook phonebook) throws NotPresentException{
		System.out.println(Main.HELP_MESSAGE);
	}
}
