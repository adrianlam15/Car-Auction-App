package test;

import model.Car;
import model.Cars;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarsTest {
    Car c1;
    Car c2;
    Car c3;
    Cars cs1;
    Cars cs2;
    Cars cs3;

    @BeforeEach
    void setup() {
        c1 = new Car();
        c2 = new Car();
        c3 = new Car();
        cs1 = new Cars();
        cs2 = new Cars();
        cs3 = new Cars();
    }

    @Test
    void testConstructor() {
        assertEquals(0, cs1.getCars().size());
        assertEquals(0, cs2.getCars().size());
        assertEquals(0, cs3.getCars().size());
    }

    @Test
    void testAddCar() {
        cs2.addCar(c2);
        cs3.addCar(c1);
        cs3.addCar(c2);
        cs3.addCar(c3);
        assertEquals(0, cs1.getCars().size());
        assertEquals(1, cs2.getCars().size());
        assertEquals(3, cs3.getCars().size());
    }

    @Test
    void testRemoveCar() {
        cs1.addCar(c1);
        cs2.addCar(c1);
        cs2.addCar(c2);
        cs3.addCar(c1);
        cs3.addCar(c2);
        cs3.addCar(c3);
        cs1.removeCar(c1);
        cs2.removeCar(c2);
        cs3.removeCar(c3);
        assertEquals(0, cs1.getCars().size());
        assertEquals(1, cs2.getCars().size());
        assertEquals(2, cs3.getCars().size());
    }

    @Test
    void testGetCars() {
        cs1.addCar(c1);
        cs2.addCar(c1);
        cs2.addCar(c2);
        cs3.addCar(c1);
        cs3.addCar(c2);
        cs3.addCar(c3);
        assertEquals(1, cs1.getCars().size());
        assertEquals(2, cs2.getCars().size());
        assertEquals(3, cs3.getCars().size());
    }
}
