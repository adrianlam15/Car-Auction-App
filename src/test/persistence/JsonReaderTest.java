package persistence;

import model.Car;
import model.Cars;
import model.User;
import model.Users;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {
    JsonReader jsonReader;

    @Test
    void testReaderNonExistentFile() {
        jsonReader = new JsonReader("./data/noSuchFile.json");
        try {
            Users users = jsonReader.readUsers();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNoSettings() {
        jsonReader = new JsonReader("./data/testReaderEmptyData.json");
        try {
            Users users = jsonReader.readUsers();
            assertEquals(0, users.getUsers().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralSettings() {
        jsonReader = new JsonReader("./data/testReaderGeneralData.json");
        try {
            Users users = jsonReader.readUsers();
            assertEquals(2, users.getUsers().size());
            assertEquals("John", users.getUser(0).getUsername());
            assertEquals("Adrian", users.getUser(1).getUsername());
            assertEquals("1234", users.getUser(0).getPassword());
            assertEquals("5678", users.getUser(1).getPassword());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReadDate() {
        jsonReader = new JsonReader("./data/testReaderGeneralData.json");
        try {
            String date = jsonReader.readDate();
            assertEquals("07-03-2023 17:18:30", date);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testMapUserWon() {
        jsonReader = new JsonReader("./data/testReaderGeneralData.json");
        try {
            Users users = jsonReader.readUsers();
            User user = users.getUser(0);
            ArrayList<Car> listedCars = user.getCars();
            Cars cars = new Cars();
            for (Car car : listedCars) {
                cars.addCar(car);
            }
            assertEquals(1, user.getWonCars().size());
            assertEquals("Honda", user.getWonCars().get(0).getMake());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    /*
    @Test
    void testMapUserWonEmpty() {
        jsonReader = new JsonReader("./data/testReaderGeneralData.json");
        try {
            Users users = jsonReader.readUsers();
            User user = users.getUser(1);
            ArrayList<Car> listedCars = user.getCars();
            Cars cars = new Cars();
            for (Car car : listedCars) {
                cars.addCar(car);
            }
            mapUserWon(users.getUser(1), user, cars);
            assertEquals(0, user.getWonCars().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testMapUserBids() {
        jsonReader = new JsonReader("./data/testReaderGeneralData.json");
        try {
            Users users = jsonReader.readUsers();
            User user = users.getUser(0);
            ArrayList<Car> listedCars = user.getCars();
            Cars cars = new Cars();
            for (Car car : listedCars) {
                cars.addCar(car);
            }
            mapUserBids(users.getUser(0), user, cars);
            assertEquals(1, user.getBids().size());
            assertEquals("Honda", user.getBids().get(0).getCar().getMake());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }*/
}

