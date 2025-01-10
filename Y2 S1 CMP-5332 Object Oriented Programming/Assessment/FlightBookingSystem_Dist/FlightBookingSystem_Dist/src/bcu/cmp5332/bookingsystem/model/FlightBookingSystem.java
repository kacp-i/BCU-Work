package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.util.*;

/**
 * class to deal with the system
 */

public class FlightBookingSystem {
    
    private final LocalDate systemDate = LocalDate.parse("2024-11-11");
    
    private final Map<Integer, Customer> customers = new TreeMap<>();
    private final Map<Integer, Flight> flights = new TreeMap<>();
    private final Map<Integer, Booking> bookings = new TreeMap<>();

    
    /** 
     * method to return the system date
     * @return returns the system date
     */
    public LocalDate getSystemDate() {
        return systemDate;
    }

    
    /** 
     * method to return a list of flights
     * @return returns a list of flight objects
     */
    public List<Flight> getFlights() {
        List<Flight> out = new ArrayList<>(flights.values());
        return Collections.unmodifiableList(out);
    }

    
    /** 
     * method to return the flight object with the specified ID
     * @param id the ID of the flight to be found
     * @return returns the flight object
     * @throws FlightBookingSystemException throws an error if a flight with the specified ID does not exist
     */
    public Flight getFlightByID(int id) throws FlightBookingSystemException {
        if (!flights.containsKey(id)) {
            throw new FlightBookingSystemException("There is no flight with that ID.");
        }
        return flights.get(id);
    }

    
    /** 
     * method to return the customer object with the specified ID
     * @param id the ID of the customer to be found
     * @return returns the customer object
     * @throws FlightBookingSystemException throws an error if a customer with the specified ID does not exist
     */
    public Customer getCustomerByID(int id) throws FlightBookingSystemException {
        // TODO: implementation here
    	if (!customers.containsKey(id)) {
            throw new FlightBookingSystemException("There is no customer with that ID.");
        }
        return customers.get(id);
    }

    
    /** 
     * method to add a flight to the system
     * checks the new flight object against the system to see if that ID already exists
     * checks the new flight number and departure date against the system to see if that flight already exists
     * stores the new flight into the map of current flights
     * @param flight the new flight object
     * @throws FlightBookingSystemException throws an error if there is a flight with the same flight number and departure date
     * @throws IllegalArgumentExcpetion throws an error if a flight with that ID already exists
     */
    public void addFlight(Flight flight) throws FlightBookingSystemException {
        if (flights.containsKey(flight.getId())) {
            throw new IllegalArgumentException("Duplicate flight ID.");
        }
        for (Flight existing : flights.values()) {
            if (existing.getFlightNumber().equals(flight.getFlightNumber()) 
                && existing.getDepartureDate().isEqual(flight.getDepartureDate())) {
                throw new FlightBookingSystemException("There is a flight with same "
                        + "number and departure date in the system");
            }
        }
        flights.put(flight.getId(), flight);
    }

    
    /** 
     * method to add a customer to the system
     * checks the new customer object against the system to see if that ID already exists
     * checks the new customer name and phone number against the system to see if that customer already exists
     * stores the new customer into the map of current customers
     * @param customer the new flight object
     * @throws FlightBookingSystemException throws an error if there us a customer with the same customer name and number
     * @throws IllegalArgumentExcpetion throws an error if a customer with that ID already exists
     */
    public void addCustomer(Customer customer) throws FlightBookingSystemException {
        // TODO: implementation here
    	if (customers.containsKey(customer.getId())) {
            throw new IllegalArgumentException("Duplicate customer ID.");
        }
        for (Customer existing : customers.values()) {
            if (existing.getCustomerName().equals(customer.getCustomerName()) 
                && existing.getCustomerNumber().equals(customer.getCustomerNumber())) {
                throw new FlightBookingSystemException("There is a customer with same "
                        + "name and phone number in the system");
            }
        }
        customers.put(customer.getId(), customer);
    }

	
    /** 
     * method to return a list of customers
     * @return returns a list of customer objects
     */
    public List<Customer> getCustomers() {
		 List<Customer> out = new ArrayList<>(customers.values());
	     return Collections.unmodifiableList(out);
	}

    
    /** 
     * method to return a list of bookings
     * @return returns a list of booking objects
     */
    public List<Booking> getBookings() {
        List<Booking> out = new ArrayList<>(bookings.values());
        return Collections.unmodifiableList(out);
    }

    
    /** 
     * method to add a booking to the system
     * checks the new booking object against the system to see if that ID already exists
     * checks the new booking values against the system to see if that booking already exists
     * stores the new booking into the map of current bookings
     * @param booking the new booking object
     * @throws FlightBookingSystemException throws an error if there us a booking with the same values
     * @throws IllegalArgumentExcpetion throws an error if a booking with that ID already exists
     */
    public void addBooking(Booking booking) throws FlightBookingSystemException {
    	if (bookings.containsKey(booking.getID())) {
            throw new IllegalArgumentException("Duplicate booking ID.");
        }
        for (Booking existing : bookings.values()) {
            if (existing.getCustomerID() == booking.getCustomerID() 
                && existing.getFlightID() == booking.getFlightID()) {
                throw new FlightBookingSystemException("There is a booking with same customer and flight in the system");
            }
        }
        bookings.put(booking.getID(), booking);
    }

    
    /** 
     * method to return a booking with specified ID
     * @param bookingID the booking ID to be found
     * @return returns the booking object with specified ID
     * @throws FlightBookingSystemException throws an error if there is no booking with that ID
     */
    public Booking getBookingByID(int bookingID) throws FlightBookingSystemException {
        if (!bookings.containsKey(bookingID)) {
            throw new FlightBookingSystemException("There is no booking with that ID.");
        }
        return bookings.get(bookingID);
    }
}
