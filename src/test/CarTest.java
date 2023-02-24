import model.Bid;
import model.Car;
import model.Cars;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.TimerTask;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    Car car1;
    Car car2;
    Car car3;

    @BeforeEach
    void setup() {
        car1 = new Car();
        car2 = new Car();
        car3 = new Car();
    }

    @Test
    void testConstructor() {
        assertEquals("", car1.getMake());
        assertEquals("", car1.getModel());
        assertEquals("", car1.getColour());
        assertEquals("", car1.getTransmission());
        assertEquals("", car1.getDriveType());
        assertEquals("", car1.getCondition());
        assertEquals("", car1.getDescription());
        assertEquals(0, car1.getYear());
        assertEquals(0, car1.getPrice());
        assertEquals(0, car1.getMileage());
        assertEquals(0, car1.getId());
        assertEquals(0, car1.getBids().size());

        assertEquals("", car2.getMake());
        assertEquals("", car2.getModel());
        assertEquals("", car2.getColour());
        assertEquals("", car2.getTransmission());
        assertEquals("", car2.getDriveType());
        assertEquals("", car2.getCondition());
        assertEquals("", car2.getDescription());
        assertEquals(0, car2.getYear());
        assertEquals(0, car2.getPrice());
        assertEquals(0, car2.getMileage());
        assertEquals(0, car2.getId());
        assertEquals(0, car2.getBids().size());

        assertEquals("", car3.getMake());
        assertEquals("", car3.getModel());
        assertEquals("", car3.getColour());
        assertEquals("", car3.getTransmission());
        assertEquals("", car3.getDriveType());
        assertEquals("", car3.getCondition());
        assertEquals("", car3.getDescription());
        assertEquals(0, car3.getYear());
        assertEquals(0, car3.getPrice());
        assertEquals(0, car3.getMileage());
        assertEquals(0, car3.getId());
        assertEquals(0, car3.getBids().size());
    }

    @Test
    void testSetMake() {
        car1.setMake("Honda");
        assertEquals("Honda", car1.getMake());
        car2.setMake("Toyota");
        assertEquals("Toyota", car2.getMake());
        car3.setMake("Ford");
        assertEquals("Ford", car3.getMake());
    }

    @Test
    void testIsExpired() {
        assertFalse(car1.isExpired());
        car2.markExpired();
        assertTrue(car2.isExpired());
        assertFalse(car3.isExpired());
    }

    @Test
    void testSetModel() {
        car1.setModel("Civic");
        assertEquals("Civic", car1.getModel());
        car2.setModel("Corolla");
        assertEquals("Corolla", car2.getModel());
        car3.setModel("F-150");
        assertEquals("F-150", car3.getModel());
    }

    @Test
    void testSetColour() {
        car1.setColour("Red");
        assertEquals("Red", car1.getColour());
        car2.setColour("Blue");
        assertEquals("Blue", car2.getColour());
        car3.setColour("Black");
        assertEquals("Black", car3.getColour());
    }

    @Test
    void testSetTransmission() {
        car1.setTransmission("Automatic");
        assertEquals("Automatic", car1.getTransmission());
        car2.setTransmission("Manual");
        assertEquals("Manual", car2.getTransmission());
        car3.setTransmission("Automatic");
        assertEquals("Automatic", car3.getTransmission());
    }

    @Test
    void testSetDriveType() {
        car1.setDriveType("Front Wheel Drive");
        assertEquals("Front Wheel Drive", car1.getDriveType());
        car2.setDriveType("RWD");
        assertEquals("RWD", car2.getDriveType());
        car3.setDriveType("All Wheel Drive");
        assertEquals("All Wheel Drive", car3.getDriveType());
    }

    @Test
    void testSetCondition() {
        car1.setCondition("New");
        assertEquals("New", car1.getCondition());
        car2.setCondition("Used");
        assertEquals("Used", car2.getCondition());
        car3.setCondition("Poor");
        assertEquals("Poor", car3.getCondition());
    }

    @Test
    void testSetDescription() {
        car1.setDescription("desc1");
        assertEquals("desc1", car1.getDescription());
        car2.setDescription("desc2");
        assertEquals("desc2", car2.getDescription());
        car3.setDescription("desc3");
        assertEquals("desc3", car3.getDescription());
    }

    @Test
    void testSetYear() {
        car1.setYear(2019);
        assertEquals(2019, car1.getYear());
        car2.setYear(2018);
        assertEquals(2018, car2.getYear());
        car3.setYear(2017);
        assertEquals(2017, car3.getYear());
    }

    @Test
    void testSetPrice() {
        car1.setPrice(10000);
        assertEquals(10000, car1.getPrice());
        car2.setPrice(20000);
        assertEquals(20000, car2.getPrice());
        car3.setPrice(30000);
        assertEquals(30000, car3.getPrice());
    }

    @Test
    void testSetMileage() {
        car1.setMileage(1000);
        assertEquals(1000, car1.getMileage());
        car2.setMileage(2000);
        assertEquals(2000, car2.getMileage());
        car3.setMileage(3000);
        assertEquals(3000, car3.getMileage());
    }

    @Test
    void testSetId() {
        car1.setId(1);
        assertEquals(1, car1.getId());
        car2.setId(2);
        assertEquals(2, car2.getId());
        car3.setId(3);
        assertEquals(3, car3.getId());
    }

    @Test
    void testGetHighestBid() {
        User u1 = new User();
        User u2 = new User();

        assertNull(car1.getHighestBid());

        car1.bid(u1, 1000);
        assertEquals(1000, car1.getHighestBid().getBidAmount());

        car2.bid(u2, 2000);
        car2.bid(u1, 3000);
        assertEquals(3000, car2.getHighestBid().getBidAmount());
    }

    @Test
    void testGetListingCar() {
        assertEquals("[ condition]    , ; with 0km for $0.", car1.getListingCar());

        car2.setMake("Toyota");
        car2.setModel("Corolla");
        car2.setColour("Blue");
        car2.setTransmission("Manual");
        car2.setDriveType("Rear Wheel Drive");
        car2.setCondition("Used");
        car2.setMileage(0);
        car2.setPrice(0);
        assertEquals("[Used condition] Manual Blue Toyota Corolla, Rear Wheel Drive; with 0km for $0.",
                car2.getListingCar());

        car3.setMake("Ford");
        car3.setModel("F-150");
        car3.setColour("Black");
        car3.setTransmission("Automatic");
        car3.setDriveType("All Wheel Drive");
        car3.setCondition("Excellent");
        car3.setMileage(0);
        car3.setPrice(0);
        car3.setTimer(10000);
        assertEquals("[Excellent condition] Automatic Black Ford F-150, All Wheel Drive; with 0km for $0." +
                        "\n\tTime remaining: 10000 seconds.",
                car3.getListingCar());
    }

    @Test
    void testEdit() {
        car1.setMake("Opel");
        car1.setModel("Astra");
        car1.setColour("Red");
        car1.setTransmission("Automatic");
        car1.setDriveType("Front Wheel Drive");
        car1.setCondition("New");
        car1.setDescription("desc");
        car1.setYear(2019);
        car1.setPrice(10000);
        car1.setMileage(1000);
        car1.edit(1, "Toyota");
        assertEquals("Toyota", car1.getMake());
        car1.edit(2, "Corolla");
        assertEquals("Corolla", car1.getModel());
        car1.edit(3, "Blue");
        assertEquals("Blue", car1.getColour());
        car1.edit(4, "Manual");
        assertEquals("Manual", car1.getTransmission());
        car1.edit(5, "Rear Wheel Drive");
        assertEquals("Rear Wheel Drive", car1.getDriveType());
        car1.edit(6, "Used");
        assertEquals("Used", car1.getCondition());
        car1.edit(7, "1997");
        assertEquals(1997, car1.getYear());
        car1.edit(8, "100000");
        assertEquals(100000, car1.getMileage());
        car1.edit(9, "1000");
        assertEquals(1000, car1.getPrice());
        car1.edit(10, "editDesc");
        assertEquals("editDesc", car1.getDescription());

        car2.edit(1, "Nissan");
        assertEquals("Nissan", car2.getMake());
        car2.edit(2, "R34 GTR");
        assertEquals("R34 GTR", car2.getModel());
        car2.edit(3, "Red");
        assertEquals("Red", car2.getColour());
        car2.edit(4, "Automatic");
        assertEquals("Automatic", car2.getTransmission());
        car2.edit(5, "All Wheel Drive");
        assertEquals("All Wheel Drive", car2.getDriveType());
        car2.edit(6, "Used");
        assertEquals("Used", car2.getCondition());
        car2.edit(7, "1996");
        assertEquals(1996, car2.getYear());
        car2.edit(8, "1000");
        assertEquals(1000, car2.getMileage());
        car2.edit(9, "100000");
        assertEquals(100000, car2.getPrice());
        car2.edit(10, "editDesc2");
        assertEquals("editDesc2", car2.getDescription());
    }

    @Test
    void testBid() {
        User u1 = new User();
        User u2 = new User();
        assertEquals(0, car1.getBids().size());

        car2.bid(u1, 2000);
        assertEquals(1, car2.getBids().size());
        assertEquals(u1, car2.getBids().get(0).getUser());

        car3.bid(u1, 4000);
        car3.bid(u2, 1000);
        assertEquals(2, car3.getBids().size());
        assertEquals(u1, car3.getBids().get(0).getUser());
    }

    @Test
    void testSetTimer() {
        car1.setTimer(0);
        assertEquals(0, car1.getTimeLeftInSeconds());

        car2.setTimer(10000);
        assertEquals(10000, car2.getTimeLeftInSeconds());

        car3.setTimer(200);
        assertEquals(200, car3.getTimeLeftInSeconds());
    }

    @Test
    void testMarkExpired() {
        car2.setTimer(10000);
        assertFalse(car2.isExpired());

        car3.setTimer(20000);
        car3.markExpired();
        assertTrue(car3.isExpired());
    }

    @Test
    void testGiveToWinner() {
        User u1 = new User();
        User u2 = new User();
        car1.bid(u1, 1000);
        car1.giveToWinner();
        assertEquals(1, u1.getWonCars().size());
        assertEquals(car1, u1.getWonCars().get(0));
        assertTrue(car1.isExpired());

        car2.bid(u1, 2000);
        car2.bid(u2, 3000);
        car2.giveToWinner();
        assertEquals(1, u2.getWonCars().size());
        assertEquals(car2, u2.getWonCars().get(0));
        assertTrue(car2.isExpired());

        car3.bid(u1, 2000);
        car3.bid(u2, 1000);
        car3.giveToWinner();
        assertEquals(2, u1.getWonCars().size());
        assertEquals(car3, u1.getWonCars().get(1));
        assertTrue(car3.isExpired());

    }

    @Test
    void testGetTimeLeftInSeconds() {
        car1.setTimer(0);
        assertEquals(0, car1.getTimeLeftInSeconds());

        car2.setTimer(10000);
        assertEquals(10000, car2.getTimeLeftInSeconds());

        car3.setTimer(20000);
        assertEquals(20000, car3.getTimeLeftInSeconds());
    }

    @Test
    void testGetBids() {
        User u1 = new User();
        User u2 = new User();

        assertEquals(0, car1.getBids().size());

        car2.bid(u1, 2000);
        assertEquals(1, car2.getBids().size());
        assertEquals(u1, car2.getBids().get(0).getUser());

        car3.bid(u1, 4000);
        car3.bid(u2, 1000);
        assertEquals(2, car3.getBids().size());
        assertEquals(u1, car3.getBids().get(0).getUser());
    }

    @Test
    void testGetMake() {
        assertEquals("", car1.getMake());
        car2.setMake("Nissan");
        assertEquals("Nissan", car2.getMake());
        car3.setMake("Honda");
        assertEquals("Honda", car3.getMake());
    }

    @Test
    void testGetModel() {
        assertEquals("", car1.getModel());
        car2.setModel("R34 GTR");
        assertEquals("R34 GTR", car2.getModel());
        car3.setModel("Civic");
        assertEquals("Civic", car3.getModel());
    }

    @Test
    void testGetColour() {
        assertEquals("", car1.getColour());
        car2.setColour("Red");
        assertEquals("Red", car2.getColour());
        car3.setColour("Blue");
        assertEquals("Blue", car3.getColour());
    }

    @Test
    void testGetTransmission() {
        assertEquals("", car1.getTransmission());
        car2.setTransmission("Automatic");
        assertEquals("Automatic", car2.getTransmission());
        car3.setTransmission("Manual");
        assertEquals("Manual", car3.getTransmission());
    }

    @Test
    void testGetDriveType() {
        assertEquals("", car1.getDriveType());
        car2.setDriveType("All Wheel Drive");
        assertEquals("All Wheel Drive", car2.getDriveType());
        car3.setDriveType("Front Wheel Drive");
        assertEquals("Front Wheel Drive", car3.getDriveType());
    }

    @Test
    void testGetCondition() {
        assertEquals("", car1.getCondition());
        car2.setCondition("Used");
        assertEquals("Used", car2.getCondition());
        car3.setCondition("New");
        assertEquals("New", car3.getCondition());
    }

    @Test
    void testGetYear() {
        assertEquals(0, car1.getYear());
        car2.setYear(1996);
        assertEquals(1996, car2.getYear());
        car3.setYear(2019);
        assertEquals(2019, car3.getYear());
    }

    @Test
    void testGetMileage() {
        assertEquals(0, car1.getMileage());
        car2.setMileage(1000);
        assertEquals(1000, car2.getMileage());
        car3.setMileage(2);
        assertEquals(2, car3.getMileage());
    }

    @Test
    void testGetPrice() {
        assertEquals(0, car1.getPrice());
        car2.setPrice(100000);
        assertEquals(100000, car2.getPrice());
        car3.setPrice(10000000);
        assertEquals(10000000, car3.getPrice());
    }

    @Test
    void testGetDescription() {
        assertEquals("", car1.getDescription());
        car2.setDescription("desc2");
        assertEquals("desc2", car2.getDescription());
        car3.setDescription("desc3");
        assertEquals("desc3", car3.getDescription());
    }
}

