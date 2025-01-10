package bcu.cmp5332.bookingsystem.model;

import java.util.ArrayList;
import java.util.List;

/**
 * class to deal with the customer object
 */
public class Customer {
    
    private int id;
    private String name;
    private String phone;
    private String email;
    private boolean isDeleted = false;

    private final List<Integer> bookingIdList = new ArrayList<>();
    
    private final List<Booking> bookings = new ArrayList<>();
    
    /**
     * initialising the customer object
     * @param id the ID value of the customer
     * @param customerName the name of the customer
     * @param customerNumber the phone number of the customer
     * @param email the email of the customer
     */
    public Customer(int id, String customerName, String customerNumber, String email) {
        this.id = id;
        this.name = customerName;
        this.phone = customerNumber;
        this.email = email;
    }
    
    
    /** 
     * method to return the ID of the customer
     * @return returns the customer ID
     */
    public int getId() {
        return id;
    }

    
    /** 
     * method to set the ID of the customer
     * @param id the new ID value
     */
    public void setId(int id) {
        this.id = id;
    }

    
    /** 
     * method to return the customer name
     * @return returns the customer name
     */
    public String getCustomerName() {
        return name;
    }

    
    /** 
     * method to set the customer name
     * @param customerName the new customer name value
     */
    public void setCustomerName(String customerName) {
        this.name = customerName;
    }
    
    
    /** 
     * method to return the customer phone number
     * @return returns the customer's phone number
     */
    public String getCustomerNumber() {
        return phone;
    }
    
    
    /** 
     * method to set the customer phone number
     * @param customerNumber the new phone number
     */
    public void setCustomerNumber(String customerNumber) {
        this.phone = customerNumber;
    }
    
    
    /** 
     * method to return the details of the customer
     * @return returns the ID, name, phone number and email of customer
     */
    public String getDetails() {
        return "Customer #" + id + " Name: " + name + " Number: " + phone + " Email: " + email;
    }
    
    
    /** 
     * method to return bookings
     * @return returns a list of booking objects
     */
    public List<Booking> getBookings() {
        return bookings;
    }

    
    /** 
     * method to return bookings (by ID)
     * @return returns a list of booking ID's
     */
    public List<Integer> getBookingsById() {
        return bookingIdList;
    }

    
    /** 
     * method to add a booking to the list of bookings
     * @param booking the new booking object
     */
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    
    /** 
     * method to add a booking ID to the list of booking IDs
     * @param id the new booking ID
     */
    public void addBookingID(int id) {
        bookingIdList.add(id);
    }

    
    /** 
     * method to return the customer email
     * @return returns the customer email
     */
    public String getEmail() {
        return email;
    }

    
    /** 
     * method to set the customer email
     * @param email the new customer email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    
    /**
     * method to return the status of the booking
     * @return returns the satus of the booking (true / false)
     */
    public boolean isDeleted() {
        return isDeleted;
    }
    
    
    /** 
     * method to set the status of the booking
     * @param deleted the new status
     */
    public void setDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }
}
