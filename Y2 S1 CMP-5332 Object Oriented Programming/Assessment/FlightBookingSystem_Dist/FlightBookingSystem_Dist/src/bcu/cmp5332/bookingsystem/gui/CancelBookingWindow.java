package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.CancelBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * class to deal with cancelling bookings via the GUI
 */

public class CancelBookingWindow extends JFrame implements ActionListener {
    private MainWindow mw;
    private JTextField customerIdText = new JTextField();
    private JTextField flightIdText = new JTextField();
    
    private JButton cancelBtn = new JButton("Cancel Booking");
    private JButton closeBtn = new JButton("Close");
    
    public CancelBookingWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }
    
    private void initialize() {
        setTitle("Cancel Booking");
        setSize(350, 150);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 2));
        topPanel.add(new JLabel("Customer ID:"));
        topPanel.add(customerIdText);
        topPanel.add(new JLabel("Flight ID:"));
        topPanel.add(flightIdText);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(cancelBtn);
        bottomPanel.add(closeBtn);
        
        cancelBtn.addActionListener(this);
        closeBtn.addActionListener(this);
        
        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);
        setVisible(true);
    }
    
    
    /** 
     * method to deal with events that are possible on the cancel bookings window
     * @param ae action to cancel a booking, or cancel the cancelation of the booking
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == cancelBtn) {
            cancelBooking();
        } else if (ae.getSource() == closeBtn) {
            this.setVisible(false);
        }
    }
    
    private void cancelBooking() {
        try {
            int customerId = Integer.parseInt(customerIdText.getText());
            int flightId = Integer.parseInt(flightIdText.getText());
            
            Command cancelBooking = new CancelBooking(customerId, flightId);
            cancelBooking.execute(mw.getFlightBookingSystem());
            mw.displayBookings();
            this.setVisible(false);
        } catch (FlightBookingSystemException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
