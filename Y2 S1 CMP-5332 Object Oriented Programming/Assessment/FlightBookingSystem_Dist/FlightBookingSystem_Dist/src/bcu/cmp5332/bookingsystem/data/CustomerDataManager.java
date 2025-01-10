package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * class to deal with the data for customers
 */

public class CustomerDataManager implements DataManager {

    private final String RESOURCE = "FlightBookingSystem_Dist/resources/data/customers.txt";
    private final String TEMP_RESOURCE = "FlightBookingSystem_Dist/resources/data/customers_temp.txt";

    
    
    /** 
     * method to load the data stored in the txt file for the customers
     * grabs each line and turns the data stored into properties by splitting the data via the custom separator
     * for each line creates a new customer object into the system
     * @param fbs
     * @throws IOException
     * @throws FlightBookingSystemException throws an error if it is unable to properly parse the customer
     */
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
            	String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -2);
                try {
                    int id = Integer.parseInt(properties[0]);
                    String customerName = properties[1];
                    String customerNumber = properties[2];
                    String customerEmail = properties[3];
                    boolean isDeleted = Boolean.parseBoolean(properties[5]);
                    Customer customer = new Customer(id, customerName, customerNumber, customerEmail);
                    
                    customer.setDeleted(isDeleted);

                    String bookings = properties[6];
                    bookings = bookings.replace("[", "");
                    bookings = bookings.replace("]", "");
                    String[] listBookings = bookings.split(", ");
                    if (bookings.length() != 0) {
                        for (String elem : listBookings) {
                            // grabs flight ID
                            int flightID = Integer.parseInt(elem);
                            customer.addBookingID(flightID);
                        }
                    }

                    fbs.addCustomer(customer);
                } catch (NumberFormatException ex) {
                    throw new FlightBookingSystemException("Unable to parse customer id " + properties[0] + " on line " + line_idx
                        + "\nError: " + ex);
                }
                line_idx++;
            }
    	}
    }

    
    /** 
     * method to save the data on the system into a txt file
     * grabs each customer in the system
     * for each customer, it writes to the txt file all the properties for the customer object
     * @param fbs
     * @throws IOException
     */
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        File originalFile = new File(RESOURCE);
        try (PrintWriter out = new PrintWriter(new FileWriter(originalFile))) {
            for (Customer customer : fbs.getCustomers()) {
                out.print(customer.getId() + SEPARATOR);
                out.print(customer.getCustomerName() + SEPARATOR);
                out.print(customer.getCustomerNumber() + SEPARATOR);
                out.print(customer.getEmail() + SEPARATOR);
                out.print(customer.getBookings().size() + SEPARATOR);
                out.print(customer.isDeleted() + SEPARATOR);
                out.print(customer.getBookingsById() + SEPARATOR);
                out.println();
            }
        }
    }
}
