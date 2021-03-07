package persistence;

import model.DailyLogMap;

import org.json.JSONObject;

import java.time.LocalDate;

// modeled after JsonSerializationDemo
public class JsonRecoveryWriter  extends JsonWriter {

    // EFFECTS:     constructs writer to write to destination file
    public JsonRecoveryWriter(String destination) {
        super(destination);
    }

    // MODIFIES:    this
    // EFFECTS:     writes JSON representation of DailyLogMap to file
    public void write(String user, LocalDate activeDate, DailyLogMap dlm) {
        JSONObject jsonObject = dlm.toJson();
        jsonObject.put("activeDate", localDateToJson(activeDate));
        jsonObject.put("user", user);
        saveToFile(jsonObject.toString(TAB));
    }


    // EFFECTS:     returns the date of DailyLog to JSON file
    private JSONObject localDateToJson(LocalDate activeDate) {
        JSONObject json = new JSONObject();

        json.put("year", activeDate.getYear());
        json.put("month", activeDate.getMonthValue());
        json.put("day", activeDate.getDayOfMonth());
        return json;
    }
}
