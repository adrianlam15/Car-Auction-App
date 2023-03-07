import model.User;
import model.Users;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UsersTest {
    Users users;
    Users users2;
    User user1;


    @BeforeEach
    void setUp() {
        users = new Users();
        users2 = new Users();
        user1 = new User();
        users2.add(user1);
    }

    @Test
    void testConstructor() {
        assertEquals(0, users.getUsers().size());
    }

    @Test
    void testAddUser() {
        User user1 = new User();
        users2.add(user1);
        assertEquals(2, users2.getUsers().size());
    }

    @Test
    void testGetUser() {
        assertEquals(user1, users2.getUser(0));
        try {
            users.getUser(0);
            fail("IndexOutOfBoundsException was not caught");
        } catch (IndexOutOfBoundsException e) {
            // pass
        }
    }

    @Test
    void testRemoveUser() {
        users2.remove(user1);
        assertEquals(0, users2.getUsers().size());
    }

    @Test
    void testGetUsers() {
        assertEquals(0, users.getUsers().size());
        for (User u : users2.getUsers()) {
            assertEquals(user1, u);
            break;
        }
    }
}