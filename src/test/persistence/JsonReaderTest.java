package persistence;

import model.User;
import model.Users;
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
            assertEquals("John", users.get(0).getUsername());
            assertEquals("Adrian", users.get(1).getUsername());
            assertEquals("1234", users.get(0).getPassword());
            assertEquals("5678", users.get(1).getPassword());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
