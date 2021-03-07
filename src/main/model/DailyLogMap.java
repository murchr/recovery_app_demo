package model;


import model.vectors.LogList;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class DailyLogMap extends ConcurrentSkipListMap<LocalDate, DailyLog> implements Writable {
    public DailyLogMap() {
        super();
    }

    ///////////////////////
    /*   FUNCTIONALITY   */
    ///////////////////////

    // EFFECTS:     puts dailyLog entry keyed to dailyLog's date
    public void put(DailyLog dailyLog) {
        put(dailyLog.getLogDate(),dailyLog);
    }

    //EFFECT:       returns all nonempty ExercisesLogs as ConcurrentSkipListMap<LocalDate, LogVector>
    //              between fromKey inclusive to toKey exclusive
    //REQUIRES:     fromKey > toKey, fromKey & toKey are not null, fromKey and toKey must bound some DailyLogs
    public ConcurrentSkipListMap<LocalDate, LogList> getExercisesBetween(LocalDate fromKey, LocalDate toKey) {
        ConcurrentNavigableMap<LocalDate, DailyLog> subMap = subMap(fromKey, toKey);
        ConcurrentSkipListMap<LocalDate, LogList> exercisesMap = new ConcurrentSkipListMap<>();
        for (LocalDate ld : subMap.keySet()) {
            DailyLog dailyLog = subMap.get(ld);
            LogList exercises = dailyLog.getExerciseLog();
            if (!exercises.isEmpty()) {
                exercisesMap.put(ld, exercises);
            }
        }
        return exercisesMap;
    }

    ///////////////////////
    /*        JSON       */
    ///////////////////////

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (LocalDate localDate : keySet()) {
            jsonArray.put(get(localDate).toJson());
        }
        json.put("DailyLogMap", jsonArray);
        return json;
    }

}
