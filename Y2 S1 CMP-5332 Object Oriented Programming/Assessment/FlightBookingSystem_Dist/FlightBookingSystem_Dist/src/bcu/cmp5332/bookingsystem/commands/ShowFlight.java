package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * class to show a flight's details
 */

public class ShowFlight implements Command {

    int flightID;

    public ShowFlight(int flightID) {
        this.flightID = flightID;
    }

    
    /** method to show a flight's details
     * grabs the relative flight via ID then displays details about it
     * @param flightBookingSystem
     * @throws FlightBookingSystemException
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        Flight flightReference = flightBookingSystem.getFlightByID(flightID);

        System.out.println(flightReference.getDetailsShort());
    }
}