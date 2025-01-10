package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.UpdateBooking;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * class to deal with updating bookings via the GUI
 */

public class UpdateBookingWindow extends JFrame implements ActionListener {
    private MainWindow mw;
    private JTextField bookingIdText = new JTextField();
    private JTextField newFlightIdText = new JTextField();
    
    private JButton updateBtn = new JButton("Update");
    private JButton cancelBtn = new JButton("Cancel");
    
    public UpdateBookingWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }
    
    private void initialize() {
        setTitle("Update Booking");
        setSize(350, 150);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 2));
        topPanel.add(new JLabel("Booking ID:"));
        topPanel.add(bookingIdText);
        topPanel.add(new JLabel("New Flight ID:"));
        topPanel.add(newFlightIdText);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(updateBtn);
        bottomPanel.add(cancelBtn);
        
        updateBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        
        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);
        setVisible(true);
    }
    
    
    /** 
     * method to deal with events that are possible on the update bookings window
     * @param ae action to update a booking, or cancel the update
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateBtn) {
            updateBooking();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }
    }
    
    private void updateBooking() {
        try {
            int bookingId = Integer.parseInt(bookingIdText.getText());
            int newFlightId = Integer.parseInt(newFlightIdText.getText());
            
            Command updateBooking = new UpdateBooking(bookingId, newFlightId);
            updateBooking.execute(mw.getFlightBookingSystem());
            mw.displayBookings();
            this.setVisible(false);
        } catch (FlightBookingSystemException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
