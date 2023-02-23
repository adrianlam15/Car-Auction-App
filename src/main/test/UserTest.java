package test;

import model.Car;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    User user1;
    User user2;
    User user3;

    @BeforeEach
    public void setUp() {
        user1 = new User();
        user2 = new User();
        user3 = new User();
        user1.createUser("user1", "password1", "password1");
        user2.createUser("user2", "password2", "passwordINVALID");
        user3.createUser("user3", "password3", "password3");
    }

    @Test
    public void testCreateUser() {
        assertTrue(user1.createUser("user1", "password1", "password1"));
        assertFalse(user2.createUser("user2", "password2", "passwordINVALID"));
        assertTrue(user3.createUser("user3", "password3", "password3"));
    }

    @Test
    public void testLogin() {
        assertTrue(user1.login("user1", "password1"));
        assertFalse(user3.login("user3", "FAIL"));
    }

    @Test
    public void testCreateCar() {
        Car user2Car = new Car();
        user2Car.setMake("Honda");
        user2Car.setModel("Civic");
        user2Car.setColour("Red");
        user2Car.setTransmission("Manual");
        user2Car.setDriveType("Front Wheel Drive");
        user2Car.setCondition("Used");
        user2Car.setYear(2010);
        user2Car.setPrice(10000);
        user2Car.setMileage(100000);
        user2Car.setId(1);
        user2Car.setDescription("This is a test car");
        user2.createCar(user2Car);
        assertEquals(0, user1.getCars().size());
        assertEquals(1, user2.getCars().size());
    }

    @Test
    void testDeleteCar() {
        Car user2Car = new Car();
        user2Car.setId(0);
        Car user2Car2 = new Car();
        user2Car2.setId(1);
        user2.createCar(user2Car);
        user2.createCar(user2Car2);
        
        assertFalse(user1.deleteCar(0));
        assertTrue(user2.deleteCar(0));
        assertEquals(1, user2Car2.getId());
    }

    @Test
    void testEditCar() {
        // TODO: implement this method
    }

    @Test
    void testPlaceBid() {
        // TODO: implement this method
    }

    @Test
    void testGetBids() {
        // STUB
    }

    /*
    public ArrayList<Car> getBids() {
        //return biddedCars.getCars();
    }*/

    @Test
    void testGetCars() {
        Car user2Car = new Car();
        assertEquals(0, user1.getCars().size());
        assertEquals(1, user2.getCars().size());
    }
}
