package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/** 
 * class to update a booking
 */

public class UpdateBooking implements Command {

    private final int bookingID;
    private final int newFlightID;

    public UpdateBooking(int bookingID, int newFlightID) {
        this.bookingID = bookingID;
        this.newFlightID = newFlightID;
    }

    
    /** method to update a booking
     * grabs the relative booking via the booking ID
     * grabs the current flight on the booking
     * grabs the new flight ID provided by the user
     * grabs the relative customer via the customer ID in the booking
     * calculates a rebooking fee
     * updates the booking details to conatin the new flight
     * updates the passenger list of the flights 
     * @param flightBookingSystem
     * @throws FlightBookingSystemException
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        Booking booking = flightBookingSystem.getBookingByID(bookingID);
        Flight oldFlight = flightBookingSystem.getFlightByID(booking.getFlightID());
        Flight newFlight = flightBookingSystem.getFlightByID(newFlightID);
        Customer customer = flightBookingSystem.getCustomerByID(booking.getCustomerID());

        // Rebooking fee
        double rebookingFee = oldFlight.getPrice() * 0.20;
        booking.setRebookingFee(rebookingFee);

        // Update booking details
        booking.setFlightID(newFlightID);
        booking.setStatus(true);
        System.out.println("Booking #" + booking.getID() + " updated. Rebooking fee: $" + rebookingFee);

        // Update flight passenger lists
        oldFlight.removePassenger(customer);
        newFlight.addPassenger(customer);
    }
}
