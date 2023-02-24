import model.Bid;
import model.Car;
import model.User;
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
    User u1;
    User u2;

    @BeforeEach
    void setUp() {
        x1 = new Car();
        x3 = new Car();
        u1 = new User();
        u2 = new User();
        b1 = new Bid(u1, x1, 1000);
        b2 = new Bid(u2, 2000);
        b3 = new Bid(u2, x3, 3000);
    }

    @Test
    void testConstructor() {
        assertEquals(x1, b1.getCar());
        assertEquals(u1, b1.getUser());
        assertEquals(1000, b1.getBidAmount());

        assertNull(b2.getCar());
        assertEquals(u2, b2.getUser());
        assertEquals(2000, b2.getBidAmount());

        assertEquals(x3, b3.getCar());
        assertEquals(u2, b3.getUser());
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
