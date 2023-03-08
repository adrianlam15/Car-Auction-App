import model.Car;
import model.Cars;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    User user1;
    User user2;
    User user3;
    Car c1;
    Car c2;
    Car c3;

    HashMap<String, String> userMap;

    @BeforeEach
    void setUp() {
        user1 = new User();
        user2 = new User();
        user3 = new User();
        c1 = new Car();
        c2 = new Car();
        c3 = new Car();
        userMap = new HashMap<>();
        user1.createUser("user1", "password1", "password1");
        user2.createUser("user2", "password2", "passwordINVALID");
        user3.createUser("user3", "password3", "password3");
        userMap.put("user1", "password1");
        userMap.put("user3", "password3");

    }

    @Test
    public void testCreateUser() {
        assertTrue(user1.createUser("user1", "password1", "password1"));
        assertFalse(user2.createUser("user2", "password2", "passwordINVALID"));
        assertTrue(user3.createUser("user3", "password3", "password3"));

        assertEquals("", c1.getMake());
        assertEquals("", c1.getModel());
        assertEquals("", c1.getColour());
        assertEquals("", c1.getTransmission());
        assertEquals("", c1.getDriveType());
        assertEquals("", c1.getCondition());
        assertEquals("", c1.getDescription());
        assertEquals(0, c1.getYear());
        assertEquals(0, c1.getPrice());
        assertEquals(0, c1.getMileage());
        assertEquals(0, c1.getId());
        assertEquals(0, c1.getBids().size());

        assertEquals("", c2.getMake());
        assertEquals("", c2.getModel());
        assertEquals("", c2.getColour());
        assertEquals("", c2.getTransmission());
        assertEquals("", c2.getDriveType());
        assertEquals("", c2.getCondition());
        assertEquals("", c2.getDescription());
        assertEquals(0, c2.getYear());
        assertEquals(0, c2.getPrice());
        assertEquals(0, c2.getMileage());
        assertEquals(0, c2.getId());
        assertEquals(0, c2.getBids().size());

        assertEquals("", c3.getMake());
        assertEquals("", c3.getModel());
        assertEquals("", c3.getColour());
        assertEquals("", c3.getTransmission());
        assertEquals("", c3.getDriveType());
        assertEquals("", c3.getCondition());
        assertEquals("", c3.getDescription());
        assertEquals(0, c3.getYear());
        assertEquals(0, c3.getPrice());
        assertEquals(0, c3.getMileage());
        assertEquals(0, c3.getId());
        assertEquals(0, c3.getBids().size());
    }

    @Test
    public void testLogin() {
        assertTrue(user1.login("user1", "password1", userMap));
        assertFalse(user3.login("user3", "password33", userMap));
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
        assertNull(user1.deleteCar(0));
        user2.deleteCar(0);
        assertEquals(1, user2Car2.getId());
        assertNull(user2.deleteCar(3));
    }

    @Test
    void testEditCar() {
        assertFalse(user1.editCar(0, 1, "Honda"));

        Car user2Car = new Car();
        user2Car.setId(1);
        user2.createCar(user2Car);

        assertTrue(user2.editCar(1, 1, "Honda"));
        Car user2Car2 = new Car();
        user2Car2.setId(2);
        user2.createCar(user2Car2);
        user2Car2.setMake("Honda1");
        user2.editCar(2, 1, "Honda2");
        assertEquals("Honda2", user2Car2.getMake());
    }

    @Test
    void testPlaceBid() {
        Car c1 = new Car();
        c1.setId(1);
        Car c2 = new Car();
        c2.setId(2);
        Car c3 = new Car();
        c3.setId(3);

        Cars cars = new Cars();
        cars.addCar(c1);
        cars.addCar(c2);
        assertTrue(user1.placeBid(1, 10000, cars));
        assertTrue(user1.placeBid(2, 20000, cars));
        assertEquals(2, user1.getBids().size());
    }

    @Test
    void testGetBids() {
        Car c1 = new Car();
        c1.setId(1);
        Car c2 = new Car();
        c2.setId(2);
        Car c3 = new Car();
        c3.setId(3);

        Cars cars = new Cars();
        cars.addCar(c1);
        cars.addCar(c2);
        user1.placeBid(1, 10000, cars);
        user1.placeBid(2, 20000, cars);
        assertEquals(2, user1.getBids().size());
    }

    @Test
    void testGetName() {
        assertEquals("user1", user1.getUsername());
        assertNull(user2.getUsername());
        assertEquals("user3", user3.getUsername());
    }

    @Test
    void testGetCars() {
        Car user3Car = new Car();
        Car user3Car2 = new Car();
        user3.createCar(user3Car);
        user3.createCar(user3Car2);
        assertEquals(0, user1.getCars().size());
        assertEquals(2, user3.getCars().size());
    }

    @Test
    void testGetWonCars() {
        Car c1 = new Car();
        c1.setId(1);
        Car c2 = new Car();
        c2.setId(2);
        Car c3 = new Car();
        c3.setId(3);

        Cars cars = new Cars();
        cars.addCar(c1);
        cars.addCar(c2);
        user1.placeBid(1, 10000, cars);
        user1.placeBid(2, 20000, cars);
        user1.placeBid(3, 30000, cars);
        c1.bid(user1, 10000);
        assertEquals(2, user1.getBids().size());
        assertEquals(0, user1.getWonCars().size());

        user2.placeBid(1, 40000, cars);
        c1.bid(user2, 40000);
        c1.giveToWinner();
        assertEquals(1, user2.getWonCars().size());
    }
}
