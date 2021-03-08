package persistence;

import org.json.JSONObject;

import java.io.IOException;

public class JsonAddressReader extends JsonReader {
    // EFFECTS: constructs reader to read from source file
    public JsonAddressReader(String source) throws IOException {
        super(source);
    }

    public Addresses read() {
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAddresses(jsonObject);
    }

    private Addresses parseAddresses(JSONObject jsonObject) {
        String preferencesAddress = jsonObject.getString("lastPreferences");
        String recoveryAddress = jsonObject.getString("lastAddress");
        return new Addresses(recoveryAddress, preferencesAddress);
    }
}
