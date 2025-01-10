package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.gui.MainWindow;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * class to open the GUI
 */

public class LoadGUI implements Command {

    
    /**
     * method to open the GUI
     * @param flightBookingSystem
     * @throws FlightBookingSystemException
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        MainWindow window = new MainWindow(flightBookingSystem);
        window.setVisible(true);
    }
    
}
