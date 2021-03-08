package persistence;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

// modeled after JsonSerializationDemo
public class JsonAddressReaderTest {

    @Test
    public void testAddressReaderNonExistentFile() {
        try {
            JsonAddressReader reader = new JsonAddressReader("./data/noSuchFile.json");
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testAddressReaderEmpty() {
        Addresses addresses;
        try {
            JsonAddressReader reader = new JsonAddressReader("./data/testReaderAddressEmpty.Json");
            addresses = reader.read();

            try {
                JsonRecoveryReader jsonRecoveryReader = new JsonRecoveryReader(addresses.getRecoveryAddress());
                fail("IOException expected, stored address invalid");
            } catch (IOException e) {
                // pass
            }
        } catch (IOException e) {
            fail("failed to open valid file");
        }
    }

    @Test
    public void testAddressReaderExpected() {
        Addresses addresses;
        try {
            JsonAddressReader reader = new JsonAddressReader("./data/testAddressExpected.json");
            addresses = reader.read();

            try {
                JsonRecoveryReader jsonRecoveryReader = new JsonRecoveryReader(addresses.getRecoveryAddress());
                // pass
            } catch (IOException e) {
                fail("couldn't access valid address");
            }
        } catch (IOException e) {
            fail("failed to open valid file");
        }
    }
}
