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
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

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
            System.out.println(user.getWonCars().get(0).getId());
            assertTrue(user.getWonCars().get(0).isExpired());
            assertEquals(1, user.getWonCars().size());
            assertEquals("honda", user.getWonCars().get(0).getMake());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testMapUserWonEmpty() {
        jsonReader = new JsonReader("./data/testReaderGeneralData.json");
        try {
            Users users = jsonReader.readUsers();
            User user = users.getUser(0);
            System.out.println(user.getWonCars().get(0).getId());
            assertTrue(user.getWonCars().get(0).isExpired());
            assertEquals(1, user.getWonCars().size());
            assertEquals("honda", user.getWonCars().get(0).getMake());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testMapUserBids() {
        jsonReader = new JsonReader("./data/testReaderGeneralData.json");
        try {
            User user = jsonReader.readUsers().getUser(0);
            assertEquals(1, user.getBids().size());
            assertEquals(10000, user.getBids().get(0).getBidAmount());
            assertEquals(2, user.getBids().get(0).getCar().getId());
            assertEquals("honda", user.getBids().get(0).getCar().getMake());
            assertEquals(0, user.getBids().get(0).getCar().getTimer());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testGetUserMap() {
        jsonReader = new JsonReader("./data/testReaderGeneralData.json");
        try {
            Users users = jsonReader.readUsers();
            HashMap<String, String> userMap = jsonReader.getUserMap();
            assertEquals("1234", userMap.get("John"));
            assertEquals("5678", userMap.get("Adrian"));
            assertEquals(2, userMap.size());
        } catch(IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testMapUserCars() {
        jsonReader = new JsonReader("./data/testReaderGeneralData.json");
        try {
            Users users = jsonReader.readUsers();
            User user = users.getUser(0);
            assertEquals("gmc", user.getCars().get(2).getMake());
            assertEquals(4, user.getCars().get(2).getId());
            assertEquals(0, user.getCars().get(2).getTimer());
            assertEquals(false, user.getCars().get(2).isExpired());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}


