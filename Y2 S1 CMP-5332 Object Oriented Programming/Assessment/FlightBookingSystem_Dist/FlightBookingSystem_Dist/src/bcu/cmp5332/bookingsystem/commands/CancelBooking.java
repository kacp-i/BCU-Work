package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.util.List;

/**
 * class to cancel a booking
 */

public class CancelBooking implements Command {

    private final int customerID;
    private final int flightID;

    public CancelBooking(int customerID, int flightID) {
        this.customerID = customerID;
        this.flightID = flightID;
    }

    
    /** 
     * method to cancel a booking
     * grabs all bookings and looks for the specified booking
     * calculates the cancellation fee
     * updates the status of the relative booking
     * removes the customer from the flight object
     * @param flightBookingSystem
     * @throws FlightBookingSystemException
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        List<Booking> listBookings = flightBookingSystem.getBookings();
    
        for (Booking bookingRef : listBookings) {
            if (bookingRef.getCustomerID() == customerID && bookingRef.getFlightID() == flightID) {
                Flight flight = flightBookingSystem.getFlightByID(flightID);
                
                // Calculate cancellation fee
                double fee = flight.getPrice() * 0.30;
                bookingRef.setCancellationFee(fee);
                
                bookingRef.setStatus(false);
                System.out.println("Booking #" + bookingRef.getID() + " cancelled. Cancellation fee: $" + fee);
                
                // remove from flight object
                Flight flightReference = flightBookingSystem.getFlightByID(flightID);
                Customer customerReference = flightBookingSystem.getCustomerByID(customerID);
                flightReference.removePassenger(customerReference);
            }
        }
    }
}