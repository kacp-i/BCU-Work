package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.util.List;
import java.util.stream.Collectors;

/**
 * class to show all flights
 */

public class ListFlights implements Command {

    
    /** method to show all the flights in the system
     * grabs all the flights in the system into a list, and iterates through the list to print out each flight one by one
     * filter is applied to only show active flights
     * also shows the size of the list
     * @param flightBookingSystem
     * @throws FlightBookingSystemException
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        List<Flight> flights = flightBookingSystem.getFlights().stream()
            .filter(flight -> !flight.isDeleted())
            .filter(flight -> !flight.hasDeparted(flightBookingSystem.getSystemDate()))
            .collect(Collectors.toList());
            
        for (Flight flight : flights) {
            System.out.println(flight.getDetailsShort());
        }
        System.out.println(flights.size() + " flight(s)");
    }
}
