package persistence;

import org.json.JSONObject;

public class JsonAddressWriter extends JsonWriter {
    public JsonAddressWriter(String source) {
        super(source);
    }

    // MODIFIES:    this
    // EFFECTS:     writes JSON representation of JsonAddressReader to file
    public void write(Addresses addresses) {
        JSONObject jsonAddress = addresses.toJson();
        saveToFile(jsonAddress.toString(TAB));
    }
}
