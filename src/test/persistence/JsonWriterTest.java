package persistence;

import model.User;
import model.Users;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest {
    private LocalDateTime now;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private ZoneId zoneId = ZoneId.of("America/Los_Angeles");
    private ZonedDateTime zonedTime;
    private Users users = new Users();

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyData() {
        try {
            now = LocalDateTime.now();
            zonedTime = ZonedDateTime.of(now, zoneId);
            String formattedNow = zonedTime.format(formatter);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyData.json");
            writer.open();
            writer.write(formattedNow, users);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyData.json");
            Users users = reader.readUsers();
            assertEquals(0, users.getUsers().size());
            assertEquals(formattedNow, reader.readDate());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralData() {
        try {
            User user = new User();
            user.createUser("user1", "pass1", "pass1");
            users.add(user);
            now = LocalDateTime.now();
            zonedTime = ZonedDateTime.of(now, zoneId);
            String formattedNow = zonedTime.format(formatter);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralData.json");
            writer.open();
            writer.write(formattedNow, users);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralData.json");
            users = reader.readUsers();
            assertEquals(1, users.getUsers().size());
            assertEquals(formattedNow, reader.readDate());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}