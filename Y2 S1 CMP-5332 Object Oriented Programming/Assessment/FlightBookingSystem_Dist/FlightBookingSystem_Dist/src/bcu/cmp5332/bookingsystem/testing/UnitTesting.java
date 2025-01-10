package bcu.cmp5332.bookingsystem.testing;

import java.time.LocalDate;

import bcu.cmp5332.bookingsystem.model.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class UnitTesting {
    private Customer customerRef = new Customer(1, "andy", "07122122122", "andy@mail.com");
    LocalDate departureDate = LocalDate.parse("2022-12-17");
    private Flight flightRef = new Flight(1, "BB8002", "Glasgow", "Munich", departureDate, 40, 99);

    
    @Test
    public void testEmail() throws Exception {
        assertEquals("andy@mail.com", customerRef.getEmail());
    }

    @Test
    public void testSeats() throws Exception {
        assertEquals(40, flightRef.getCapacity());
    }

    @Test
    public void testPrice() throws Exception {
        assertEquals(99, flightRef.getPrice());
    }
}
