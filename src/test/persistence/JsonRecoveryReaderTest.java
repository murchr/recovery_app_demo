package persistence;

import model.DailyLog;
import model.DailyLogMap;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// modeled after JsonSerializationDemo
public class JsonRecoveryReaderTest {

    @Test
    public void testRecoveryReaderNonExistentFile() {
        try {
            JsonRecoveryReader reader = new JsonRecoveryReader("./data/noSuchFile.json");
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testRecoveryReaderEmpty() {
        DailyLogMap dailyLogMap;
        String user;
        LocalDate activeDate;
        LocalDate expectedDate = LocalDate.of(2021,3,7);
        try {
            JsonRecoveryReader reader = new JsonRecoveryReader("./data/emptyGenerated_2021_3_7.Json");

            dailyLogMap = reader.readDailyLogMap();
            user = reader.readUser();
            activeDate = reader.readActiveDay();

            assertEquals("Default Empty", user);
            assertEquals(expectedDate, activeDate);
            assertEquals(1, dailyLogMap.size());
            // pass
        } catch (IOException e) {
            fail("failed to open valid file");
        }
    }

    @Test
    public void testRecoveryReaderExpected() {
        DailyLogMap dailyLogMap;
        String user;
        LocalDate activeDate;
        try {
            JsonRecoveryReader reader = new JsonRecoveryReader("./data/testReaderRecoveryExpected.json");
            dailyLogMap = reader.readDailyLogMap();
            user = reader.readUser();
            activeDate = reader.readActiveDay();

            assertEquals("Ryan Murch", user);
            assertEquals(4, dailyLogMap.size());
            assertEquals(LocalDate.of(2021,3,4),activeDate);

        } catch (IOException e) {
            fail("failed to open valid file");
        }
    }
}
