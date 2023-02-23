package test;

import model.Bid;
import model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BidTest {
    Bid b1;
    Bid b2;
    Bid b3;
    Car x1;
    Car x3;

    @BeforeEach
    void setUp() {
        x1 = new Car();
        x3 = new Car();
        b1 = new Bid(x1, 1000);
        b2 = new Bid(2000);
        b3 = new Bid(x3, 3000);
    }

    @Test
    void testConstructor() {
        assertEquals(x1, b1.getCar());
        assertEquals(1000, b1.getBidAmount());

        assertNull(b2.getCar());
        assertEquals(2000, b2.getBidAmount());

        assertEquals(x3, b3.getCar());
        assertEquals(3000, b3.getBidAmount());
    }

    @Test
    void testGetCar() {
        assertEquals(x1, b1.getCar());
        assertNull(b2.getCar());
        assertEquals(x3, b3.getCar());
    }

    @Test
    void testGetBidAmount() {
        assertEquals(1000, b1.getBidAmount());
        assertEquals(2000, b2.getBidAmount());
        assertEquals(3000, b3.getBidAmount());
    }

    @Test
    void testGetBid() {
        assertEquals(x1.getListingCar() + "\nBid: $" + 1000, b1.getBid());
        assertEquals("Bid: $" + 2000, b2.getBid());
        assertEquals(x3.getListingCar() + "\nBid: $" + 3000, b3.getBid());
    }
}
