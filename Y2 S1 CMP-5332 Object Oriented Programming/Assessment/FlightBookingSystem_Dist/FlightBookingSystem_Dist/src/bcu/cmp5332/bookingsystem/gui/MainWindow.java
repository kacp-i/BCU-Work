package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.DeleteCustomer;
import bcu.cmp5332.bookingsystem.commands.DeleteFlight;
import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;

/**
 * class to deal with running the GUI
 */

public class MainWindow extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu adminMenu;
    private JMenu flightsMenu;
    private JMenu bookingsMenu;
    private JMenu customersMenu;

    private JMenuItem adminExit;
    private JMenuItem flightsView;
    private JMenuItem flightsAdd;
    private JMenuItem flightsDel;
    private JMenuItem bookingsView;
    private JMenuItem bookingsIssue;
    private JMenuItem bookingsUpdate;
    private JMenuItem bookingsCancel;
    private JMenuItem custView;
    private JMenuItem custAdd;
    private JMenuItem custDel;

    private FlightBookingSystem fbs;
    private JTable currentTable;

    public MainWindow(FlightBookingSystem fbs) {
        initialize();
        this.fbs = fbs;
    }

    
    /** 
     * method to grab the system and its data
     * @return FlightBookingSystem returns the system as an object
     */
    public FlightBookingSystem getFlightBookingSystem() {
        return fbs;
    }

    private void initialize() {
        setTitle("Flight Booking System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuBar = new JMenuBar();
        adminMenu = new JMenu("Admin");
        flightsMenu = new JMenu("Flights");
        bookingsMenu = new JMenu("Bookings");
        customersMenu = new JMenu("Customers");

        adminExit = new JMenuItem("Exit");
        flightsView = new JMenuItem("View Flights");
        flightsAdd = new JMenuItem("Add Flight");
        flightsDel = new JMenuItem("Delete Flight");
        bookingsView = new JMenuItem("View Bookings");
        bookingsIssue = new JMenuItem("Issue Booking");
        bookingsUpdate = new JMenuItem("Update Booking");
        bookingsCancel = new JMenuItem("Cancel Booking");
        custView = new JMenuItem("View Customers");
        custAdd = new JMenuItem("Add Customer");
        custDel = new JMenuItem("Delete Customer");

        adminExit.addActionListener(this);
        flightsView.addActionListener(this);
        flightsAdd.addActionListener(this);
        flightsDel.addActionListener(this);
        bookingsView.addActionListener(this);
        bookingsIssue.addActionListener(this);
        bookingsUpdate.addActionListener(this);
        bookingsCancel.addActionListener(this);
        custView.addActionListener(this);
        custAdd.addActionListener(this);
        custDel.addActionListener(this);

        adminMenu.add(adminExit);
        flightsMenu.add(flightsView);
        flightsMenu.add(flightsAdd);
        flightsMenu.add(flightsDel);
        bookingsMenu.add(bookingsView);
        bookingsMenu.add(bookingsIssue);
        bookingsMenu.add(bookingsUpdate);
        bookingsMenu.add(bookingsCancel);
        customersMenu.add(custView);
        customersMenu.add(custAdd);
        customersMenu.add(custDel);

        menuBar.add(adminMenu);
        menuBar.add(flightsMenu);
        menuBar.add(bookingsMenu);
        menuBar.add(customersMenu);
        setJMenuBar(menuBar);
    }

    
    /** 
     * method to check which event is being performed
     * displays an error message if it can not perform the respective event
     * @param ae each action corresponds to its respective button in the window
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == adminExit) {
                handleExit();
            } else if (ae.getSource() == flightsView) {
                displayFlights();
            } else if (ae.getSource() == flightsAdd) {
                new AddFlightWindow(this);
            } else if (ae.getSource() == custView) {
                displayCustomers();
            } else if (ae.getSource() == custAdd) {
                new AddCustomerWindow(this);
            } else if (ae.getSource() == bookingsView) {
                displayBookings();
            } else if (ae.getSource() == bookingsIssue) {
                if (fbs.getCustomers().isEmpty()) {
                    JOptionPane.showMessageDialog(this, 
                        "Please add customers first before creating a booking.", 
                        "No Customers", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (fbs.getFlights().isEmpty()) {
                    JOptionPane.showMessageDialog(this, 
                        "Please add flights first before creating a booking.", 
                        "No Flights", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                new AddBookingWindow(this);
            } else if (ae.getSource() == bookingsUpdate) {
                new UpdateBookingWindow(this);
            } else if (ae.getSource() == bookingsCancel) {
                new CancelBookingWindow(this);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleExit() {
        try {
            FlightBookingSystemData.store(fbs);
            System.exit(0);
        } catch (IOException | FlightBookingSystemException ex) {
            int choice = JOptionPane.showConfirmDialog(this,
                "Error saving data. Exit without saving?",
                "Save Error",
                JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    private void saveState() {
        try {
            FlightBookingSystemData.store(fbs);
            JOptionPane.showMessageDialog(this, 
                "Data saved successfully!", 
                "Save Success", 
                JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error saving data: " + ex.getMessage(), 
                "Save Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    
    /** 
     * method to delete a flight chosen by the user
     * displays an error message if it can not delete the selected flight
     * @param row grabs the row of the flight that is to be deleted
     */
    private void deleteSelectedFlight(int row) {
    try {
        String flightNo = (String)currentTable.getValueAt(row, 0);
        Flight flight = fbs.getFlights().stream()
            .filter(f -> f.getFlightNumber().equals(flightNo))
            .findFirst()
            .orElse(null);
            
        if (flight != null) {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete flight " + flightNo + "?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirm == JOptionPane.YES_OPTION) {
                Command deleteCommand = new DeleteFlight(flight.getId());
                deleteCommand.execute(fbs);
                saveState();
                displayFlights();
            }
        }
    } catch (FlightBookingSystemException ex) {
        JOptionPane.showMessageDialog(
            this,
            ex.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
    }

    
    /** 
     * method to delete the selected customer
     * displays an error message if it can not delete the selected customer
     * @param row grabs the row of the customer that is to be deleted
     */
    private void deleteSelectedCustomer(int row) {
    try {
        int customerId = (int)currentTable.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete customer #" + customerId + "?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            Command deleteCommand = new DeleteCustomer(customerId);
            deleteCommand.execute(fbs);
            saveState();
            displayCustomers();
        }
    } catch (FlightBookingSystemException ex) {
        JOptionPane.showMessageDialog(
            this,
            ex.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
    }

    public void displayFlights() {
        // Filter out deleted and departed flights 
        List<Flight> flightsList = fbs.getFlights().stream()
        .filter(flight -> !flight.isDeleted())
        .filter(flight -> !flight.hasDeparted(fbs.getSystemDate()))
        .collect(Collectors.toList());
            
        String[] columns = new String[]{"Flight No", "Origin", "Destination", "Departure Date", "Capacity", "Passengers", "Current Price"};
        Object[][] data = new Object[flightsList.size()][columns.length];

        for (int i = 0; i < flightsList.size(); i++) {
            Flight flight = flightsList.get(i);
            data[i][0] = flight.getFlightNumber();
            data[i][1] = flight.getOrigin();
            data[i][2] = flight.getDestination();
            data[i][3] = flight.getDepartureDate();
            data[i][4] = flight.getCapacity();
            data[i][5] = flight.getPassengers().size();
            data[i][6] = String.format("$%.2f", flight.calculateCurrentPrice(fbs.getSystemDate()));
        }

        currentTable = new JTable(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.addActionListener(e -> {
            int row = currentTable.getSelectedRow();
            if (row != -1) {
                deleteSelectedFlight(row);
            }
        });
        popupMenu.add(deleteItem);

        currentTable.setComponentPopupMenu(popupMenu);
        currentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setupTableDoubleClick(currentTable);

        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(currentTable));
        this.revalidate();
        }

        public void displayCustomers() {
            // Filter out deleted customers
            List<Customer> customersList = fbs.getCustomers().stream()
                .filter(customer -> !customer.isDeleted())
                .collect(Collectors.toList());
                
            String[] columns = new String[]{"ID", "Name", "Phone", "Email", "Bookings"};
            Object[][] data = new Object[customersList.size()][columns.length];
            
            for (int i = 0; i < customersList.size(); i++) {
                Customer customer = customersList.get(i);
                data[i][0] = customer.getId();
                data[i][1] = customer.getCustomerName();
                data[i][2] = customer.getCustomerNumber();
                data[i][3] = customer.getEmail();
                data[i][4] = customer.getBookings().size();
            }
        
            currentTable = new JTable(data, columns) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem deleteItem = new JMenuItem("Delete");
            deleteItem.addActionListener(e -> {
                int row = currentTable.getSelectedRow();
                if (row != -1) {
                    deleteSelectedCustomer(row);
                }
            });
            popupMenu.add(deleteItem);
            
            currentTable.setComponentPopupMenu(popupMenu);
            currentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            setupTableDoubleClick(currentTable);
        
            this.getContentPane().removeAll();
            this.getContentPane().add(new JScrollPane(currentTable));
            this.revalidate();
        }

    public void displayBookings() {
        List<Booking> bookingsList = fbs.getBookings();
        String[] columns = new String[]{"Booking ID", "Customer ID", "Flight ID", "Status", "Cancellation Fee", "Rebooking Fee", "Price"};
        Object[][] data = new Object[bookingsList.size()][columns.length];

        for (int i = 0; i < bookingsList.size(); i++) {
            Booking booking = bookingsList.get(i);
            data[i][0] = booking.getID();
            data[i][1] = booking.getCustomerID();
            data[i][2] = booking.getFlightID();
            data[i][3] = booking.getStatus() ? "Active" : "Cancelled";
            data[i][4] = String.format("$%.2f", booking.getCancellationFee());
            data[i][5] = String.format("$%.2f", booking.getRebookingFee());
            data[i][6] = String.format("$%.2f", booking.getPrice());
        }

        currentTable = new JTable(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(currentTable));
        this.revalidate();
    }

    
    /** method to generate a table
     * @param table
     */
    private void setupTableDoubleClick(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    if (row != -1) {
                        showDetailsDialog(row);
                    }
                }
            }
        });
    }

   
   /** method to display data for the relative table
    * @param row
    */
   private void showDetailsDialog(int row) {
    if (currentTable == null) return;
    
    try {
        // check if current table is flights or customers based on column count
        if (currentTable.getColumnCount() == 7) {  // for flight table
            String flightNo = (String)currentTable.getValueAt(row, 0);
            Flight flight = fbs.getFlights().stream()
                .filter(f -> f.getFlightNumber().equals(flightNo))
                .findFirst()
                .orElse(null);
            
            if (flight != null) {
                StringBuilder passengerInfo = new StringBuilder();
                passengerInfo.append("Passengers for flight ").append(flightNo).append(":\n\n");
                
                List<Customer> passengers = flight.getPassengers();
                for (Customer passenger : passengers) {
                    passengerInfo.append(String.format("Name: %s\nEmail: %s\nPhone: %s\n\n", 
                        passenger.getCustomerName(), passenger.getEmail(), passenger.getCustomerNumber()));
                }
                JTextArea textArea = new JTextArea(passengerInfo.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(300, 200));

                JOptionPane.showMessageDialog(this, scrollPane, 
                    "Flight Details", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {  // for customer table
            int customerId = (int)currentTable.getValueAt(row, 0);
            Customer customer = fbs.getCustomerByID(customerId);
            
            if (customer != null) {
                StringBuilder bookingInfo = new StringBuilder();
                bookingInfo.append("Bookings for ").append(customer.getCustomerName()).append(":\n\n");
                
                List<Booking> bookings = customer.getBookings();
                for (Booking booking : bookings) {
                    Flight flight = fbs.getFlightByID(booking.getFlightID());
                    bookingInfo.append(String.format("Booking ID: %d\nFlight: %s\nFrom: %s\nTo: %s\nDate: %s\nPrice Paid: $%.2f\nStatus: %s\n\n",
                        booking.getID(),
                        flight.getFlightNumber(),
                        flight.getOrigin(),
                        flight.getDestination(),
                        flight.getDepartureDate(),
                        booking.getPrice(),
                        booking.getStatus() ? "Active" : "Cancelled"));
                }
                JTextArea textArea = new JTextArea(bookingInfo.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(300, 200));

                JOptionPane.showMessageDialog(this, scrollPane, 
                    "Customer Booking Details", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    } catch (FlightBookingSystemException ex) {
        JOptionPane.showMessageDialog(this, 
            "Error displaying details: " + ex.getMessage(), 
            "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
    /** 
     * method to run the window
     * @param args
     */
    public static void main(String[] args) {
        try {
            FlightBookingSystem fbs = FlightBookingSystemData.load();
            MainWindow mainWindow = new MainWindow(fbs);
            mainWindow.setVisible(true);
        } catch (IOException | FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(null, 
                "Error initializing the application: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}