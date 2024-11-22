package bcu.funnypackage.phonebook.main;

import java.util.List;

import bcu.funnypackage.phonebook.model.*;

public class ListCommand implements Command {
	public ListCommand(String[] parts) throws InvalidCommandException{
		if (parts.length != 1) {
			throw new InvalidCommandException();
		}
	}
		
	@Override
	public void execute(PhoneBook phonebook) throws NotPresentException{
		List<String> names = phonebook.getAllNames();
		if (names.isEmpty()) {
			System.out.println("No entries.");
		} 
		else {
			for (String name : names) {
				System.out.println(name);
			}
		}
	}
}
