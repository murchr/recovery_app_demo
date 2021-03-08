package persistence;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// modeled after JsonSerializationDemo
public class JsonAddressWriterTest {

    @Test
    public void testAddressWriterGeneral() {
        Addresses addresses = new Addresses("./data/generalRecoveryTrace.json",
                "./data/recoveryTracePreferences.json");
        try {
            JsonAddressWriter writer = new JsonAddressWriter("./data/testWriterAddressEmpty.Json");
            writer.open();
            writer.write(addresses);
            writer.close();

            try {
                JsonAddressReader reader = new JsonAddressReader("./data/testWriterAddressEmpty.Json");
                addresses = reader.read();
                assertEquals("./data/generalRecoveryTrace.json", addresses.getRecoveryAddress());
                assertEquals("./data/recoveryTracePreferences.json", addresses.getPreferencesAddress());
                // pass
            } catch (IOException e) {
                fail("reader failed to open valid file");
            }
        } catch (IOException e) {
            fail("writer failed to open valid file");
        }
    }
}
