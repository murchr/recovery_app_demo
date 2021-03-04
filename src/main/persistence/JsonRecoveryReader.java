package persistence;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import exceptions.OutOfRange;
import model.DailyLog;
import model.DailyLogMap;
import model.LogVector;
import model.entries.ExerciseEntry;
import model.entries.LogEntry;
import model.entries.WeightEntry;
import org.json.*;

// modeled after JsonSerializationDemo
public class JsonRecoveryReader extends JsonReader {

    // EFFECTS: constructs reader to read from source file
    public JsonRecoveryReader(String source) {
        super(source);
    }

    // MODIFIES:    dailyLog, historicalLog
    // EFFECTS:     reads DailyLog from file and returns it;
    //              throws IOException if an error occurs reading data from file
    public DailyLogMap read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDailyLogMap(jsonObject);
    }

    //EFFECTS: parses dailyLog from jsonObject and returns it
    private DailyLogMap parseDailyLogMap(JSONObject jsonObject) {
        String user = jsonObject.getString("user");
        LocalDate activeDate = parseLocalDate(jsonObject.getJSONObject("activeDate"));
        DailyLogMap dailyLogMap = new DailyLogMap(user, activeDate);
        JSONArray jsonArray = jsonObject.getJSONArray("DailyLogMap");
        for (Object json : jsonArray) {
            JSONObject dailyLog = (JSONObject) json;
            dailyLogMap.put(parseDailyLog(dailyLog));
        }
        return dailyLogMap;
    }

    //EFFECTS: parses dailyLog from jsonObject and returns it
    private DailyLog parseDailyLog(JSONObject jsonObject) {
        ExerciseEntry emptyExerciseEntry;
        WeightEntry emptyWeightEntry;

        try {
            emptyExerciseEntry = new ExerciseEntry(1, "", 1, 1);
            emptyWeightEntry = new WeightEntry(1, 1.0);

            DailyLog dailyLog = new DailyLog(parseLocalDate(jsonObject.getJSONObject("date")));
            dailyLog.logNewExercise(parseLogVector(jsonObject.getJSONObject("exerciseLog"), emptyExerciseEntry));
            dailyLog.logNewWeight(parseLogVector(jsonObject.getJSONObject("weightLog"), emptyWeightEntry));

            return dailyLog;
        } catch (OutOfRange e) {
            System.out.println("Error in valid constructor of LogEntry");
        }
        return null;
    }


    //EFFECTS: parses LogVector from jsonObject and returns it, parses actual type of LogEntry for each element
    private LogVector parseLogVector(JSONObject jsonObject, LogEntry type) {
        LogVector logVector = new LogVector();
        JSONArray jsonArray = jsonObject.getJSONArray("LogVector");
        for (Object json : jsonArray) {
            JSONObject logEntry = (JSONObject) json;
            if (type instanceof ExerciseEntry) {
                logVector.add(parseExerciseEntry(logEntry));
            } else if (type instanceof WeightEntry) {
                logVector.add(parseWeightEntry(logEntry));
            } else {
                System.out.println("Error in identifying type of LogEntry");
            }
        }
        return logVector;
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
