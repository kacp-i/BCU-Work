package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * class to delete a customer object
 */

public class DeleteCustomer implements Command {
    private final int customerId;
    
    public DeleteCustomer(int customerId) {
        this.customerId = customerId;
    }
    
    
    /** method to disable a customer from the system
     * grabs the ID of the specified customer and sets the status of the customer to inactive
     * @param flightBookingSystem
     * @throws FlightBookingSystemException if the customer has a booking, they can not be removed and an error is thrown
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        Customer customer = flightBookingSystem.getCustomerByID(customerId);
        if (!customer.getBookings().isEmpty()) {
            throw new FlightBookingSystemException("Cannot delete customer with existing bookings");
        }
        customer.setDeleted(true);
        System.out.println("Customer #" + customerId + " has been deleted.");
    }
}
