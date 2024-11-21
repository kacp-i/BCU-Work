package bcu.funnypackage.phonebook.main;

import bcu.funnypackage.phonebook.model.*;

/**
 * @author id124659
 *
 */
public interface Command {
	public void execute(PhoneBook phoneBook) throws AlreadyPresentException, NotPresentException;

}
