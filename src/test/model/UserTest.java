package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    User user1;
    User user2;

    @BeforeEach
    void setUp(){
        user1 = new User("user1", "password1");
        user2 = new User("user2", "password2");
    }

    @Test
    void testConstructor() {
        assertEquals("user1", user1.getUsername());
        assertEquals("password1", user1.getPassword());
        assertEquals("user2", user2.getUsername());
        assertEquals("password2", user2.getPassword());

        /*assertEquals(0, user1.getSavedListings().size());
        assertEquals(0, user2.getSavedListings().size());
        assertEquals(0, user1.getCurrentListings().size());
        assertEquals(0, user2.getCurrentListings().size());*/
    }
}
