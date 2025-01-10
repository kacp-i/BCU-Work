package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * class to deal with the data for flights
 */

public class FlightDataManager implements DataManager {
    
    private final String RESOURCE = "FlightBookingSystem_Dist/resources/data/flights.txt";
    private final String TEMP_RESOURCE = "FlightBookingSystem_Dist/resources/data/flights_temp.txt";
    
    
    /** 
     * method to load the data stored in the txt file for the flights
     * grabs each line and turns the data stored into properties by splitting the data via the custom separator
     * for each line creates a new flight object into the system
     * @param fbs
     * @throws IOException
     * @throws FlightBookingSystemException throws an error if it is unable to properly parse the flight
     */
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                    int id = Integer.parseInt(properties[0]);
                    String flightNumber = properties[1];
                    String origin = properties[2];
                    String destination = properties[3];
                    LocalDate departureDate = LocalDate.parse(properties[4]);
                    int capacity = Integer.parseInt(properties[5]);
                    int price = Integer.parseInt(properties[6]);
                    boolean isDeleted = Boolean.parseBoolean(properties[7]);
                    Flight flight = new Flight(id, flightNumber, origin, destination, departureDate, capacity, price);         
                    
                    flight.setDeleted(isDeleted);

                    String passengers = properties[8];
                    passengers = passengers.replace("[", "");
                    passengers = passengers.replace("]", "");
                    String[] listPassengers = passengers.split(", ");
                    if (passengers.length() != 0) {
                        for (String elem : listPassengers) {
                            // grabs passenger ID
                            int passengerID = Integer.parseInt(elem);
                            flight.addPassengerID(passengerID);
                        }
                    }

                    fbs.addFlight(flight);
                } catch (NumberFormatException ex) {
                    throw new FlightBookingSystemException("Unable to parse flight id " + properties[0] + " on line " + line_idx
                        + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    }
    
    
    /** 
     * method to save the data on the system into a txt file
     * grabs each flight in the system
     * for each flight, it writes to the txt file all the properties for the flight object
     * @param fbs
     * @throws IOException
     */
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        File originalFile = new File(RESOURCE);
        try (PrintWriter out = new PrintWriter(new FileWriter(originalFile))) {
            for (Flight flight : fbs.getFlights()) {
                out.print(flight.getId() + SEPARATOR);
                out.print(flight.getFlightNumber() + SEPARATOR);
                out.print(flight.getOrigin() + SEPARATOR);
                out.print(flight.getDestination() + SEPARATOR);
                out.print(flight.getDepartureDate() + SEPARATOR);
                out.print(flight.getCapacity() + SEPARATOR);
                out.print(flight.getPrice() + SEPARATOR);
                out.print(flight.isDeleted() + SEPARATOR);
                out.print(flight.getPassangersById() + SEPARATOR);
                out.println();
            }
        }
    }
}
