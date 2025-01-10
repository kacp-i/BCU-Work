package bcu.cmp5332.bookingsystem.data;



import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * class to deal with the data managers
 */

public class FlightBookingSystemData {
    
    private static final List<DataManager> dataManagers = new ArrayList<>();
    
    // runs only once when the object gets loaded to memory
    static {
        dataManagers.add(new FlightDataManager());
        dataManagers.add(new CustomerDataManager());
        dataManagers.add(new BookingDataManager());
    }
    
    
    /** 
     * method to load all the data from all the data managers
     * @return FlightBookingSystem
     * @throws FlightBookingSystemException
     * @throws IOException
     */
    public static FlightBookingSystem load() throws FlightBookingSystemException, IOException {

        FlightBookingSystem fbs = new FlightBookingSystem();
        for (DataManager dm : dataManagers) {
            dm.loadData(fbs);
        }
        return fbs;
    }

    
    /** 
     * method to save all the data from all the data managers
     * @param fbs
     * @throws IOException
     * @throws FlightBookingSystemException throws an error if it is unable to save the data to the relative txt file
     */
    public static void store(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        try {
            for (DataManager dm : dataManagers) {
                dm.storeData(fbs);
            }
        } catch (IOException e) {
            throw new FlightBookingSystemException("Error saving data: " + e.getMessage());
        }
    }
}
