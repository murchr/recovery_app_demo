package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class DailyLogMap extends ConcurrentSkipListMap<LocalDate, DailyLog> implements Writable {

    private String user;
    private LocalDate activeDate;

    public DailyLogMap(String user, LocalDate activeDate) {
        super();
        this.user = user;
        this.activeDate = activeDate;
    }

    ///////////////////////
    /*      GETTERS      */
    ///////////////////////

    public String getUser() {
        return user;
    }

    public LocalDate getActiveDate() {
        return activeDate;
    }

    ///////////////////////
    /*      SETTERS      */
    ///////////////////////

    public void setUser(String user) {
        this.user = user;
    }

    public void setActiveDate(LocalDate activeDate) {
        this.activeDate = activeDate;
    }

    ///////////////////////
    /*   FUNCTIONALITY   */
    ///////////////////////

    //EFFECT:       returns all nonempty ExercisesLogs as ConcurrentSkipListMap<LocalDate, LogVector>
    //              between fromKey inclusive to toKey exclusive
    //REQUIRES:     fromKey > toKey, fromKey & toKey are not null, fromKey and toKey must bound some DailyLogs
    public ConcurrentSkipListMap<LocalDate, LogVector> getExercisesBetween(LocalDate fromKey, LocalDate toKey) {
        ConcurrentNavigableMap<LocalDate, DailyLog> subMap = subMap(fromKey, toKey);
        ConcurrentSkipListMap<LocalDate, LogVector> exercisesMap = new ConcurrentSkipListMap<>();
        for (LocalDate ld : subMap.keySet()) {
            DailyLog dailyLog = subMap.get(ld);
            LogVector exercises = dailyLog.getExerciseLog();
            if (!exercises.isEmpty()) {
                exercisesMap.put(ld, exercises);
            }
        }
        return exercisesMap;
    }

    // EFFECTS:     puts dailyLog entry keyed to dailyLog's date
    public void put(DailyLog dailyLog) {
        put(dailyLog.getLogDate(),dailyLog);
    }

    ///////////////////////
    /*        JSON       */
    ///////////////////////

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        json.put("user", user);
        json.put("activeDate", logDateToJson());
        for (LocalDate localDate : keySet()) {
            jsonArray.put(get(localDate).toJson());
        }
        json.put("DailyLogMap", jsonArray);
        return json;
    }

    // EFFECTS:     returns the date of DailyLog to JSON file
    private JSONObject logDateToJson() {
        JSONObject json = new JSONObject();

        json.put("year", activeDate.getYear());
        json.put("month", activeDate.getMonthValue());
        json.put("day", activeDate.getDayOfMonth());
        return json;
    }
}
