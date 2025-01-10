package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.AddCustomer;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * class to deal with adding customers via the GUI
 */

public class AddCustomerWindow extends JFrame implements ActionListener {
    private MainWindow mw;
    private JTextField nameText = new JTextField();
    private JTextField phoneText = new JTextField();
    private JTextField emailText = new JTextField();
    
    private JButton addBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");
    
    public AddCustomerWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }
    
    private void initialize() {
        setTitle("Add New Customer");
        setSize(350, 220);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 2));
        topPanel.add(new JLabel("Name:"));
        topPanel.add(nameText);
        topPanel.add(new JLabel("Phone:"));
        topPanel.add(phoneText);
        topPanel.add(new JLabel("Email:"));
        topPanel.add(emailText);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(addBtn);
        bottomPanel.add(cancelBtn);
        
        addBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        
        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);
        setVisible(true);
    }
    
    
    /** 
     * method to deal with events that are possible on the adding customer window
     * @param ae action to create a customer, or cancel the creation
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addBtn) {
            addCustomer();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }
    }
    
    private void addCustomer() {
        try {
            String name = nameText.getText();
            String phone = phoneText.getText();
            String email = emailText.getText();
            
            if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                throw new FlightBookingSystemException("All fields must be filled");
            }
            
            Command addCustomer = new AddCustomer(name, phone, email);
            addCustomer.execute(mw.getFlightBookingSystem());
            mw.displayCustomers();
            this.setVisible(false);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}