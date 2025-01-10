package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * class to create bookings
 */

public class AddBooking implements Command {

    private final int customerID;
    private final int flightID;

    public AddBooking(int customerID, int flightID) {
        this.customerID = customerID;
        this.flightID = flightID;
    }

    
    /** 
     * method to create a booking object, add a customer reference to the relative flight object, add a booking reference to the relative customer object
     * @param flightBookingSystem
     * @throws FlightBookingSystemException checks the capacity before creating a new booking - if this booking would be over the relative capitiy an error is thrown
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        Flight flightReference = flightBookingSystem.getFlightByID(flightID);
        
        // Check capacity before adding
        if (flightReference.getPassengers().size() >= flightReference.getCapacity()) {
            throw new FlightBookingSystemException("Flight is at full capacity. Cannot add more passengers.");
        }
        
        double finalPrice = flightReference.calculateCurrentPrice(flightBookingSystem.getSystemDate());
        Customer customerReference = flightBookingSystem.getCustomerByID(customerID);
        
        int maxId = 0;
        if (flightBookingSystem.getBookings().size() > 0) {
            int lastIndex = flightBookingSystem.getBookings().size() - 1;
            maxId = flightBookingSystem.getBookings().get(lastIndex).getID();
        }
    
        Booking booking = new Booking(++maxId, customerID, flightID, true);
        booking.setPrice(finalPrice);
        flightBookingSystem.addBooking(booking);
        System.out.println("Booking #" + booking.getID() + " added. Price: $" + finalPrice);
    
        customerReference.addBooking(booking);
        customerReference.addBookingID(maxId);
        flightReference.addPassenger(customerReference);
        flightReference.addPassengerID(customerID);
    }
}