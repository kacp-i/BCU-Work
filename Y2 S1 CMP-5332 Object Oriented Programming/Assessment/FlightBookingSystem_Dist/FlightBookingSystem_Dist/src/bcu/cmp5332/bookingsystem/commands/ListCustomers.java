package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.util.List;

/**
 * class to list all customers
 */

public class ListCustomers implements Command {

    
    /** method to show all the customers in the system
     * grabs all the users in the system into a list, and iterates through the list to print out each customer one by one
     * also shows the size of the list
     * @param flightBookingSystem
     * @throws FlightBookingSystemException
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        List<Customer> customers = flightBookingSystem.getCustomers();
        for (Customer customer : customers) {
            System.out.println(customer.getDetails());
        }
        System.out.println(customers.size() + " Customer(s)");
    }
}
