package persistence;

import model.User;
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
            ArrayList<User> settings = jsonReader.readUsers();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNoSettings() {
        jsonReader = new JsonReader("./data/testReaderEmptyData.json");
        try {
            ArrayList<User> settings = jsonReader.readUsers();
            assertEquals(0, settings.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralSettings() {
        jsonReader = new JsonReader("./data/testReaderGeneralData.json");
        try {
            ArrayList<User> settings = jsonReader.readUsers();
            assertEquals(2, settings.size());
            assertEquals("user1", settings.get(0).getUsername());
            assertEquals("user2", settings.get(1).getUsername());
            assertEquals("1234", settings.get(0).getPassword());
            assertEquals("5678", settings.get(1).getPassword());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

