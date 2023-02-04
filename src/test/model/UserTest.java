package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    Bid bid1;
    Bid bid2;
    Car car1;
    Car car2;
    Listing listing1;
    Listing listing2;
    User user1;
    User user2;

    @BeforeEach
    void setUp(){
        user1 = new User("user1", "password1");
        user2 = new User("user2", "password2");
        car1 = new Car("Toyota", "Supra", "Excellent", "Silver", "6-Speed Manual",
                "RWD", 10000, 1998);
        car2 = new Car("Nissan", "R-34 GTR", "Fair", "Blue", "6-Speed Manual",
                "AWD", 10000, 1998);
        listing1 = new Listing("listing1", "description1", 1.0, 0, car1);
        listing2 = new Listing("listing2", "description2", 2.0, 1, car2);
        }

    @Test
    void constructorTest() {
        assertEquals("user1", user1.getUsername());
        assertEquals("password1", user1.getPassword());
        assertEquals("user2", user2.getUsername());
        assertEquals("password2", user2.getPassword());

        /*assertEquals(0, user1.getSavedListings().size());
        assertEquals(0, user2.getSavedListings().size());
        assertEquals(0, user1.getCurrentListings().size());
        assertEquals(0, user2.getCurrentListings().size());*/
    }

    @Test
    void getUsernameTest() {
        assertEquals("user1", user1.getUsername());
        assertEquals("user2", user2.getUsername());
    }

    @Test
    void getPasswordTest() {
        assertEquals("password1", user1.getPassword());
        assertEquals("password2", user2.getPassword());
    }

    @Test
    void makeBidTest() {
        user1.makeBid(60000, listing1);
        user2.makeBid(100000, listing2);
        assertEquals(1, user1.getBids().size());
        assertEquals(1, user2.getBids().size());
    }

    @Test
    void getBidsTest() {
        user1.makeBid(60000, listing1);
        user2.makeBid(100000, listing2);
    }

    @Test
    void removeBidTest() {
        user1.makeBid(60000, listing1);
        user1.makeBid(100000, listing2);
        user1.removeBid(listing1);
        assertFalse(user1.inList(listing1));
        assertTrue(user1.inList(listing2));
    }
}
