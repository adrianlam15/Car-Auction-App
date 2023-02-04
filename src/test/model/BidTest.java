package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BidTest {
    Bid bid1;
    Bid bid2;
    Car car1;
    Car car2;
    User user1;
    User user2;
    Listing l1;
    Listing l2;

    @BeforeEach
    void setUp(){
        car1 = new Car("Toyota", "Supra", "Excellent", "Silver", "6-Speed Manual",
                "RWD", 10000, 1998);
        car2 = new Car("Nissan", "R-34 GTR", "Fair", "Blue", "6-Speed Manual",
                "AWD", 10000, 1998);
        user1 = new User("user1", "password1");
        user2 = new User("user2", "password2");
        l1 = new Listing("listing1", "description1", 1.0, 0, car1);
        l2 = new Listing("listing2", "description2", 2.0, 1, car2);
        bid1 = new Bid(10000, user1, l1);
        bid2 = new Bid(20000, user2, l2);
    }

    @Test
    void constructorTest() {
        assertEquals(10000, bid1.getAmount());
        assertEquals(20000, bid2.getAmount());
        assertEquals(user1, bid1.getBidder());
        assertEquals(user2, bid2.getBidder());
    }

    @Test
    void getAmountTest() {
        assertEquals(10000, bid1.getAmount());
        assertEquals(20000, bid2.getAmount());
    }

    @Test
    void getBidderTest() {
        assertEquals(user1, bid1.getBidder());
        assertEquals(user2, bid2.getBidder());
    }

    @Test
    void getListingTest() {
        assertEquals(l1, bid1.getListing());
        assertEquals(l2, bid2.getListing());
    }
}
