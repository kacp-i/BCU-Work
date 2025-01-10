package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import java.time.LocalDate;

/**
 * class to deal with the booking object
 */

public class Booking {
    
    private int id;
    private int customerID;
    private int flightID;
    private boolean status;
    private double cancellationFee = 0;
    private double rebookingFee = 0;
    private double price;
    
    /**
     * initialising the booking object
     * @param id the id value of the booking
     * @param customerID the customer ID of the customer creating the booking
     * @param flightID the flight ID of the flight booked
     * @param status the status of the booking (active / inactive)
     */
    public Booking(int id, int customerID, int flightID, boolean status) {
        this.id = id;
        this.customerID = customerID;
        this.flightID = flightID;
        this.status = status;
    }
    
    
    /** 
     * method to return the booking ID
     * @return returns the booking ID
     */
    public int getID() {
        return id;
    }

    
    /**
     * method to return the customer ID in the booking
     * @return returns the customer ID
     */
    public int getCustomerID() {
        return customerID;
    }

    
    /** 
     * method to return the flight ID in the booking
     * @return returns the flight ID
     */
    public int getFlightID() {
        return flightID;
    }

    
    /** 
     * method to return the status of the booking
     * @return returns the status
     */
    public boolean getStatus() {
        return status;
    }

    
    /** 
     * method to change the ID of the booking
     * @param id the new ID of the booking
     */
    public void setID(int id) {
        this.id = id;
    }
    
    /** 
     * method to change the customer ID in the booking
     * @param customerID the new ID of the customer
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    
    /** 
     * method to change the flight ID in the booking
     * @param flightID the new ID of the flight
     */
    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    
    /** 
     * method to change the status of the booking
     * @param newStatus the new status
     */
    public void setStatus(boolean newStatus) {
        this.status = newStatus;
    }

    
    /** 
     * method to return the cancellation fee
     * @return returns the cancellation fee value
     */
    public double getCancellationFee() {
        return cancellationFee;
    }
    
    
    /** 
     * method to set the cancellation fee
     * @param cancellationFee the new cancellation fee
     */
    public void setCancellationFee(double cancellationFee) {
        this.cancellationFee = cancellationFee;
    }
    
    
    /** 
     * method to return the rebooking fee
     * @return returns the rebooking fee value
     */
    public double getRebookingFee() {
        return rebookingFee;
    }
    
    
    /** 
     * method to set the rebooking fee value
     * @param rebookingFee the new rebooking fee
     */
    public void setRebookingFee(double rebookingFee) {
        this.rebookingFee = rebookingFee;
    }

    
    /** 
     * method to return the price of the booking
     * @return returns the booking value
     */
    public double getPrice() {
        return price;
    }
    
    
    /** 
     * methdo to set the price of the booking
     * @param price the new price
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
