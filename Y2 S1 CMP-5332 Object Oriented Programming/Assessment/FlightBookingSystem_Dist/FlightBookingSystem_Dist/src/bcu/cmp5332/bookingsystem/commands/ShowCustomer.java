package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * class to show a customer's details
 */

public class ShowCustomer implements Command {

    int customerID;

    public ShowCustomer(int customerID) {
        this.customerID = customerID;
    }

    
    /** 
     * method to show a customer's details
     * grabs the relative customer via ID then displays details about it
     * @param flightBookingSystem
     * @throws FlightBookingSystemException
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        Customer customerReference = flightBookingSystem.getCustomerByID(customerID);

        System.out.println(customerReference.getDetails());
    }
}