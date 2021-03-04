package persistence;

import model.DailyLogMap;

import org.json.JSONObject;

// modeled after JsonSerializationDemo
public class JsonRecoveryWriter  extends JsonWriter {

    // EFFECTS:     constructs writer to write to destination file
    public JsonRecoveryWriter(String destination) {
        super(destination);
    }

    // MODIFIES:    this
    // EFFECTS:     writes JSON representation of DailyLogMap to file
    public void write(DailyLogMap dlm) {
        JSONObject jsonDLM = dlm.toJson();
        saveToFile(jsonDLM.toString(TAB));
    }
}
