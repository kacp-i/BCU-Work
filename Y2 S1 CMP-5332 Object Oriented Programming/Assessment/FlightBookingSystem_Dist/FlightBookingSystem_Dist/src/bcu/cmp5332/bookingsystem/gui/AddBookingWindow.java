package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.AddBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * class to deal with adding bookings via the GUI
 */

public class AddBookingWindow extends JFrame implements ActionListener {
    
    private MainWindow mw;
    private JComboBox<String> customerComboBox;
    private JComboBox<String> flightComboBox;
    private JLabel priceLabel = new JLabel("Price: ");
    private JButton bookBtn = new JButton("Book");
    private JButton cancelBtn = new JButton("Cancel");
    
    public AddBookingWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }
    
    private void initialize() {
        setTitle("Add a New Booking");
        setSize(400, 200);
        setLocationRelativeTo(mw);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        
        // Get customers
        customerComboBox = new JComboBox<>();
        for (Customer customer : mw.getFlightBookingSystem().getCustomers()) {
            if (!customer.isDeleted()) {
                customerComboBox.addItem(customer.getId() + " - " + customer.getCustomerName());
            }
        }
        
        // Get flights
        flightComboBox = new JComboBox<>();
        for (Flight flight : mw.getFlightBookingSystem().getFlights()) {
            if (!flight.isDeleted() && !flight.hasDeparted(mw.getFlightBookingSystem().getSystemDate())) {
                flightComboBox.addItem(flight.getFlightNumber() + " - " + 
                                  flight.getOrigin() + " to " + 
                                  flight.getDestination() + " (" + 
                                  flight.getDepartureDate() + ")");
            }
        }
        
        formPanel.add(new JLabel("Select Customer:"));
        formPanel.add(customerComboBox);
        formPanel.add(new JLabel("Select Flight:"));
        formPanel.add(flightComboBox);
        formPanel.add(new JLabel("Current Price:"));
        formPanel.add(priceLabel);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bookBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        flightComboBox.addActionListener(e -> updatePrice());
        buttonPanel.add(bookBtn);
        buttonPanel.add(cancelBtn);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        setContentPane(mainPanel);
        setVisible(true);
    }
    
    
    /** 
     * method to deal with events that are possible on the booking window
     * @param ae action to create a booking, or cancel the booking
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == bookBtn) {
            addBooking();
        } else if (ae.getSource() == cancelBtn) {
            dispose();
        }
    }
    
    private void addBooking() {
        try {
            // Get selected customer ID
            String customerSelection = (String) customerComboBox.getSelectedItem();
            int customerId = Integer.parseInt(customerSelection.split(" - ")[0]);
            
            // Get selected flight number
            String flightSelection = (String) flightComboBox.getSelectedItem();
            String flightNumber = flightSelection.split(" - ")[0];
            
            // Find the flight ID from flight number
            Flight selectedFlight = mw.getFlightBookingSystem().getFlights().stream()
                .filter(f -> f.getFlightNumber().equals(flightNumber))
                .findFirst()
                .orElseThrow(() -> new FlightBookingSystemException("Flight not found"));
            
            // Create and execute the booking command
            Command addBooking = new AddBooking(customerId, selectedFlight.getId());
            addBooking.execute(mw.getFlightBookingSystem());
            
            // Show success message
            JOptionPane.showMessageDialog(this, 
                "Booking created successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Refresh the main window and close this window
            mw.displayFlights();
            dispose();
            
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error creating booking: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "An unexpected error occurred: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updatePrice() {
        String flightSelection = (String) flightComboBox.getSelectedItem();
        if (flightSelection != null) {
            String flightNumber = flightSelection.split(" - ")[0];
            Flight selectedFlight = mw.getFlightBookingSystem().getFlights().stream()
                .filter(f -> f.getFlightNumber().equals(flightNumber))
                .findFirst()
                .orElse(null);
                
            if (selectedFlight != null) {
                double currentPrice = selectedFlight.calculateCurrentPrice(
                    mw.getFlightBookingSystem().getSystemDate());
                priceLabel.setText(String.format("Price: $%.2f", currentPrice));
            }
        }
    }
}