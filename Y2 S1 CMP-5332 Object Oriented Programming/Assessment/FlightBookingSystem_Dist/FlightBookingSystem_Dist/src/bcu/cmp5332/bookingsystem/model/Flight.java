package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * class to deal with the flight object
 */

public class Flight {
    
    private int id;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private int capacity;
    private int price;
    private boolean isDeleted = false;

    private final List<Integer> passengerIdList = new ArrayList<>();

    private final Set<Customer> passengers;

    /**
     * initialisign the flight object
     * @param id the ID value of the flight
     * @param flightNumber the flight number
     * @param origin the flight origin
     * @param destination the flight destination
     * @param departureDate the flight departure date
     * @param capacity the flight capacity
     * @param price the flight price
     */
    public Flight(int id, String flightNumber, String origin, String destination, LocalDate departureDate, int capacity, int price) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.capacity = capacity;
        this.price = price;
        
        passengers = new HashSet<>();
    }

    
    /** 
     * method to return the ID of the flight
     * @return returns the flight ID
     */
    public int getId() {
        return id;
    }

    
    /**
     * method to set the flight ID
     * @param id the new flight ID
     */
    public void setId(int id) {
        this.id = id;
    }

    
    /** 
     * method to return the flight number
     * @return returns the flight number
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    
    /** 
     * method to set the flight number
     * @param flightNumber the new flight number
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    
    
    /** 
     * method to return the flight origin
     * @return returns the flight origin
     */
    public String getOrigin() {
        return origin;
    }
    
    
    /** 
     * method to set the origin
     * @param origin the new origin
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    
    /** 
     * method to return the destination
     * @return returns the destination
     */
    public String getDestination() {
        return destination;
    }

    
    /** 
     * method to set the destination
     * @param destination the new destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    
    /** 
     * method to return the departure date
     * @return returns the departure date
     */
    public LocalDate getDepartureDate() {
        return departureDate;
    }

    
    /** 
     * method to set the departure date
     * @param departureDate the new departure date
     */
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    
    /** 
     * method to return the passengers as a list
     * @return returns a list of customers
     */
    public List<Customer> getPassengers() {
        return new ArrayList<>(passengers);
    }
	
    
    /** 
     * method to return the details of the flight
     * @return returns the flight ID, flight number, origin, destination and departure date
     */
    public String getDetailsShort() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return "Flight #" + id + " - " + flightNumber + " - " + origin + " to " 
                + destination + " on " + departureDate.format(dtf);
    }

    
    /** 
     * @return String
     */
    public String getDetailsLong() {
        // TODO: implementation here
        return null;
    }
    
    
    /** 
     * method to return the passengers as a set
     * @return returns a set of customers
     */
    public Set<Customer> getPassangers() {
        return passengers;
    }

    
    /** 
     * method to return the passenger ID's as a list
     * @return returns a list of customer ID
     */
    public List<Integer> getPassangersById() {
        return passengerIdList;
    }

    
    /** 
     * method to add a passenger ID to the list of passenger IDs
     * @param id the new customer ID
     */
    public void addPassengerID(int id) {
        passengerIdList.add(id);
    }

    
    /** 
     * method to add a passenger to the list of passenger objects
     * @param passenger the new customer object
     */
    public void addPassenger(Customer passenger) {
        passengers.add(passenger);
    }

    
    /** 
     * method to remove a passenger from the flight
     * @param passenger the customer object
     */
    public void removePassenger(Customer passenger) {
        passengers.remove(passenger);
        passengerIdList.remove(Integer.valueOf(passenger.getId()));
    }

    
    /** 
     * method to return the capacity
     * @return returns the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    
    /** 
     * method to set the capacity
     * @param capacity the new capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    
    /** 
     * method to return the price
     * @return returns the price
     */
    public int getPrice() {
        return price;
    }

    
    /** 
     * method to set the price
     * @param price the new price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    
    /**
     * method to return the status
     * @return returns the status of the flight (true / false)
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    
    /** 
     * method to set the status
     * @param deleted the new status
     */
    public void setDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }

    
    /** 
     * method to check if the flight has departed
     * @param systemDate the current date in the system
     * @return returns if the flight has departed or not (true / false)
     */
    public boolean hasDeparted(LocalDate systemDate) {
        return departureDate.isBefore(systemDate);
    }

    
    /** 
     * method to calculate the current price
     * if the departure date is within the next 7 days, the price is 1.5x the original price
     * if the departure date is in 30 days or less, the price is 1.2x the original price
     * 
     * if the flight is 80% full, the price is 1.3x the original price
     * if the flight is 50% full, the price is 1.15x the original price
     * @param systemDate the current date in the system
     * @return returns the price of the flight
     */
    public double calculateCurrentPrice(LocalDate systemDate) {
    double currentPrice = this.getPrice();
    long daysUntilFlight = ChronoUnit.DAYS.between(systemDate, departureDate);
    double occupancyRate = (double) passengers.size() / capacity;
    
    // Price increases as flight date approaches
    if (daysUntilFlight <= 7) {
        currentPrice *= 1.5;
    } else if (daysUntilFlight <= 30) {
        currentPrice *= 1.2;
    }
    
    // Price increases as flight fills up
    if (occupancyRate >= 0.8) {
        currentPrice *= 1.3;
    } else if (occupancyRate >= 0.5) {
        currentPrice *= 1.15;
    }
    
    return currentPrice;
}
}
