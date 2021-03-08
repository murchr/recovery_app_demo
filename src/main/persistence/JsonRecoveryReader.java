package persistence;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import exceptions.OutOfRange;
import model.DailyLog;
import model.DailyLogMap;
import model.lists.ExerciseList;
import model.lists.LogList;
import model.entries.ExerciseEntry;
import model.entries.WeightEntry;
import model.lists.WeightList;
import org.json.*;

// modeled after JsonSerializationDemo
public class JsonRecoveryReader extends JsonReader {

    // EFFECTS: constructs reader to read from source file
    public JsonRecoveryReader(String source) throws IOException {
        super(source);
    }


    // EFFECTS:     reads DailyLogMap from file and returns it;
    //              throws IOException if an error occurs reading data from file
    public DailyLogMap readDailyLogMap() {
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDailyLogMap(jsonObject);
    }

    // EFFECTS:     reads activeDate from file and returns it;
    //              throws IOException if an error occurs reading data from file
    public LocalDate readActiveDay() {
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLocalDate(jsonObject.getJSONObject("activeDate"));
    }

    // EFFECTS:     reads user from file and returns it;
    //              throws IOException if an error occurs reading data from file
    public String readUser() {
        JSONObject jsonObject = new JSONObject(jsonData);
        return jsonObject.getString("user");
    }

    //EFFECTS: parses dailyLogMap from jsonObject and returns it
    private DailyLogMap parseDailyLogMap(JSONObject jsonObject) {

        DailyLogMap dailyLogMap = new DailyLogMap();
        JSONArray jsonArray = jsonObject.getJSONArray("DailyLogMap");
        for (Object json : jsonArray) {
            JSONObject dailyLog = (JSONObject) json;
            dailyLogMap.put(parseDailyLog(dailyLog));
        }
        return dailyLogMap;
    }

    //EFFECTS: parses dailyLog from jsonObject and returns it
    private DailyLog parseDailyLog(JSONObject jsonObject) {
        DailyLog dailyLog = new DailyLog(parseLocalDate(jsonObject.getJSONObject("date")));
        dailyLog.logNewExercise(parseExerciseLog(jsonObject.getJSONArray("exerciseLog")));
        dailyLog.logNewWeight(parseWeightLog(jsonObject.getJSONArray("weightLog")));

        return dailyLog;
    }


    //EFFECTS: parses ExerciseList from jsonObject and returns it
    private ExerciseList parseExerciseLog(JSONArray jsonArray) {
        ExerciseList exerciseList = new ExerciseList();
        for (Object json : jsonArray) {
            JSONObject logEntry = (JSONObject) json;
            exerciseList.add(parseExerciseEntry(logEntry));
        }
        return exerciseList;
    }

    //EFFECTS: parses WeightList from jsonObject and returns it
    private LogList parseWeightLog(JSONArray jsonArray) {
        WeightList weightList = new WeightList();
        for (Object json : jsonArray) {
            JSONObject logEntry = (JSONObject) json;
            weightList.add(parseWeightEntry(logEntry));
        }
        return weightList;
    }

    //EFFECTS: parses dailyLog from jsonObject and returns it
    private ExerciseEntry parseExerciseEntry(JSONObject jsonObject) {
        ExerciseEntry exerciseEntry;
        int entryId = jsonObject.getInt("entryId");
        LocalTime entryTime = parseLocalTime(jsonObject.getJSONObject("entryTime"));
        String exerciseType = jsonObject.getString("exerciseType");
        int intensity = jsonObject.getInt("intensity");
        int duration = jsonObject.getInt("duration");

        try {
            exerciseEntry = new ExerciseEntry(entryId, entryTime, exerciseType, intensity, duration);
            return exerciseEntry;
        } catch (OutOfRange e) {
            System.out.println("Error in writing ExerciseEntry");
        }
        return null;
    }

    //EFFECTS: parses dailyLog from jsonObject and returns it
    private WeightEntry parseWeightEntry(JSONObject jsonObject) {
        WeightEntry weightEntry;
        int entryId = jsonObject.getInt("entryId");
        LocalTime entryTime = parseLocalTime(jsonObject.getJSONObject("entryTime"));
        double weight = jsonObject.getDouble("weight");

        try {
            weightEntry = new WeightEntry(entryId, entryTime, weight);
            return weightEntry;
        } catch (OutOfRange e) {
            System.out.println("Error in writing ExerciseEntry");
        }

        return null;
    }

    private LocalDate parseLocalDate(JSONObject jsonObject) {
        LocalDate localDate;
        int year = jsonObject.getInt("year");
        int month = jsonObject.getInt("month");
        int day = jsonObject.getInt("day");
        localDate = LocalDate.of(year, month, day);

        return localDate;
    }

    private LocalTime parseLocalTime(JSONObject jsonObject) {
        LocalTime localTime;
        int hour = jsonObject.getInt("hour");
        int minute = jsonObject.getInt("minute");
        localTime = LocalTime.of(hour, minute);

        return localTime;
    }
}
