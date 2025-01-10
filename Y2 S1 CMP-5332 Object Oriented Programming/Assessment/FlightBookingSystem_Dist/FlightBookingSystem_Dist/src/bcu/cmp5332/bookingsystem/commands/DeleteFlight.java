package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * class to delete a flight object
 */

public class DeleteFlight implements Command {
    private final int flightId;
    
    public DeleteFlight(int flightId) {
        this.flightId = flightId;
    }
    
    
    /** method to disable a flight from the system
     * grabs the ID of the flight, and sets the status of the flight to disabled
     * @param flightBookingSystem
     * @throws FlightBookingSystemException if the flight has passengers, it can not be removed and an error is thrown
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        Flight flight = flightBookingSystem.getFlightByID(flightId);
        if (!flight.getPassengers().isEmpty()) {
            throw new FlightBookingSystemException("Cannot delete flight with existing passengers");
        }
        flight.setDeleted(true);
        System.out.println("Flight #" + flightId + " has been deleted.");
    }
}
