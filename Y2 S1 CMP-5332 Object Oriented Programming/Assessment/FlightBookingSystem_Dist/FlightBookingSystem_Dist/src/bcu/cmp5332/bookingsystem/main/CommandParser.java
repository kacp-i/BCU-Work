package bcu.cmp5332.bookingsystem.main;

import bcu.cmp5332.bookingsystem.commands.AddBooking;
import bcu.cmp5332.bookingsystem.commands.AddCustomer;
import bcu.cmp5332.bookingsystem.commands.AddFlight;
import bcu.cmp5332.bookingsystem.commands.CancelBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.Help;
import bcu.cmp5332.bookingsystem.commands.ListCustomers;
import bcu.cmp5332.bookingsystem.commands.ListFlights;
import bcu.cmp5332.bookingsystem.commands.LoadGUI;
import bcu.cmp5332.bookingsystem.commands.ShowCustomer;
import bcu.cmp5332.bookingsystem.commands.ShowFlight;
import bcu.cmp5332.bookingsystem.commands.UpdateBooking;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * class to deal with selecting the correct commands
 */

public class CommandParser {    
    
    /** 
     * method to select the correct command based on the data entered by the user
     * the data entered is split respectively based on the data entered (based on spaces)
     * @param line the data entered in the terminal
     * @return Command the respective command is chosen based on the line entered by the user
     * @throws IOException
     * @throws FlightBookingSystemException throws an error if the command is not a valid command
     */
    public static Command parse(String line) throws IOException, FlightBookingSystemException {
        try {
            String[] parts = line.split(" ", 3);
            String cmd = parts[0];

            
            if (cmd.equals("addflight")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Flight Number: ");
                String flightNumber = reader.readLine();
                System.out.print("Origin: ");
                String origin = reader.readLine();
                System.out.print("Destination: ");
                String destination = reader.readLine();
                System.out.print("Capacity: ");
                String capacity = reader.readLine();
                int cap = Integer.parseInt(capacity);
                System.out.print("Price: ");
                String price = reader.readLine();
                int pri = Integer.parseInt(price);

                LocalDate departureDate = parseDateWithAttempts(reader);

                return new AddFlight(flightNumber, origin, destination, departureDate, cap, pri);
            } 
            else if (cmd.equals("addcustomer")) {
                 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                 System.out.print("Customer Name: ");
                 String customerName = reader.readLine();
                 System.out.print("Customer Phone Number: ");
                 String customerNumber = reader.readLine();
                 System.out.print("Customer Email: ");
                 String customerEmail = reader.readLine();

                 return new AddCustomer(customerName, customerNumber, customerEmail);
            } 
            else if (cmd.equals("loadgui")) {
                return new LoadGUI();
            } 
            else if (parts.length == 1) {
                if (line.equals("listflights")) {
                    return new ListFlights();
                }
                else if (line.equals("listcustomers")) {
                   return new ListCustomers();
                }
                else if (line.equals("help")) {
                    return new Help();
                }
            } 
            else if (parts.length == 2) {
                int id = Integer.parseInt(parts[1]);

                if (cmd.equals("showflight")) {
                    return new ShowFlight(id);
                } 
                else if (cmd.equals("showcustomer")) {
                    return new ShowCustomer(id);
                }
            } else if (parts.length == 3) {
                int id1 = Integer.parseInt(parts[1]);
                int id2 = Integer.parseInt(parts[2]);

                if (cmd.equals("addbooking")) {
                    return new AddBooking(id1, id2);
                }
                else if (cmd.equals("editbooking")) {
                    return new UpdateBooking(id1, id2);
                }
                else if (cmd.equals("cancelbooking")) {
                    return new CancelBooking(id1, id2);
                }
            }
        } catch (NumberFormatException ex) {

        }

        throw new FlightBookingSystemException("Invalid command.");
    }
    
    
    /** 
     * method to turn the user input date into a LocalDate data type
     * @param br the date entered by the user
     * @param attempts the number of attempts remaining
     * @return LocalDate returns the date entered by the user as a LocalDate data type
     * @throws IOException
     * @throws FlightBookingSystemException throws an error if the date is in the incorrect format
     */
    private static LocalDate parseDateWithAttempts(BufferedReader br, int attempts) throws IOException, FlightBookingSystemException {
        if (attempts < 1) {
            throw new IllegalArgumentException("Number of attempts should be higher that 0");
        }
        while (attempts > 0) {
            attempts--;
            System.out.print("Departure Date (\"YYYY-MM-DD\" format): ");
            try {
                LocalDate departureDate = LocalDate.parse(br.readLine());
                return departureDate;
            } catch (DateTimeParseException dtpe) {
                System.out.println("Date must be in YYYY-MM-DD format. " + attempts + " attempts remaining...");
            }
        }
        
        throw new FlightBookingSystemException("Incorrect departure date provided. Cannot create flight.");
    }
    
    
    /**
     * method to call the date parsing method
     * @param br the date entered by the user
     * @return LocalDate returns the date entered by the user as a LocalDate data type
     * @throws IOException
     * @throws FlightBookingSystemException throws an error if the date is in the incorrect format
     */
    private static LocalDate parseDateWithAttempts(BufferedReader br) throws IOException, FlightBookingSystemException {
        return parseDateWithAttempts(br, 3);
    }
}
