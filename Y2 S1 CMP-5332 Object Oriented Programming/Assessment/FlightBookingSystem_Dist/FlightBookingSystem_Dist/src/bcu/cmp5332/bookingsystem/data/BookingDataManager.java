package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * class to deal with the data for bookings
 */

public class BookingDataManager implements DataManager {
    
    private final String RESOURCE = "FlightBookingSystem_Dist/resources/data/bookings.txt";
    private final String TEMP_RESOURCE = "FlightBookingSystem_Dist/resources/data/bookings_temp.txt";

    
    /** 
     * method to load the data stored in the txt file for the bookings
     * grabs each line and turns the data stored into properties by splitting the data via the custom separator
     * for each line creates a new booking object into the system
     * @param fbs
     * @throws IOException
     * @throws FlightBookingSystemException throws an error if it is unable to properly parse the booking
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
                    int customerID = Integer.parseInt(properties[1]);
                    int flightID = Integer.parseInt(properties[2]);
                    boolean status = Boolean.parseBoolean(properties[3]);
                    Booking booking = new Booking(id, customerID, flightID, status);
                    fbs.addBooking(booking);
                } catch (NumberFormatException ex) {
                    throw new FlightBookingSystemException("Unable to parse booking id " + properties[0] + " on line " + line_idx
                        + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    }

    
    /** 
     * method to save the data on the system into a txt file
     * grabs each booking in the system
     * for each booking, it writes to the txt file all the properties for the booking object
     * @param fbs
     * @throws IOException
     */
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        File originalFile = new File(RESOURCE);
        try (PrintWriter out = new PrintWriter(new FileWriter(originalFile))) {
            for (Booking booking : fbs.getBookings()) {
                out.print(booking.getID() + SEPARATOR);
                out.print(booking.getCustomerID() + SEPARATOR);
                out.print(booking.getFlightID() + SEPARATOR);
                out.print(booking.getStatus() + SEPARATOR);
                out.println();
            }
        }
    }
}