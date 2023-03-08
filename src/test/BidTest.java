import model.Bid;
import model.Car;
import model.Cars;
import model.User;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BidTest {
    Bid b1;
    Bid b2;
    Bid b3;
    Car c1;
    Car c2;
    Car c3;
    User u1;
    User u2;
    User u3;
    User u4;
    User u5;

    @BeforeEach
    void setUp() {
        c1 = new Car();
        c2 = new Car();
        c3 = new Car();
        u1 = new User();
        u2 = new User();
        b1 = new Bid(u1, c1, 1000);
        b2 = new Bid(u2, 2000);
        b3 = new Bid(u2, c2, 3000);
    }

    @Test
    void testConstructor() {
        assertEquals(c1, b1.getCar());
        assertEquals(u1, b1.getUser());
        assertEquals(1000, b1.getBidAmount());

        assertNull(b2.getCar());
        assertEquals(u2, b2.getUser());
        assertEquals(2000, b2.getBidAmount());

        assertEquals(c2, b3.getCar());
        assertEquals(u2, b3.getUser());
        assertEquals(3000, b3.getBidAmount());
    }

    @Test
    void testGetCar() {
        assertEquals(c1, b1.getCar());
        assertNull(b2.getCar());
        assertEquals(c2, b3.getCar());
    }

    @Test
    void testGetBidAmount() {
        assertEquals(1000, b1.getBidAmount());
        assertEquals(2000, b2.getBidAmount());
        assertEquals(3000, b3.getBidAmount());
    }

    @Test
    void testGetBid() {
        assertEquals(c1.getListingCar() + "\nBid: $" + 1000, b1.getBid());
        assertEquals("Bid: $" + 2000, b2.getBid());
        assertEquals(c2.getListingCar() + "\nBid: $" + 3000, b3.getBid());
    }

    @Test
    void testToJson() {
        JSONObject actual = b1.toJson();
        JSONObject expected = new JSONObject();
        expected.put("car", c1.toJson());
        expected.put("bidAmount", 1000);

        assertEquals(expected.getInt("bidAmount"), actual.getInt("bidAmount"));
        assertEquals(expected.getJSONObject("car").getString("make"), actual.getJSONObject("car").getString("make"));}
}