package persistence;

import org.json.JSONObject;

// modeled after JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
