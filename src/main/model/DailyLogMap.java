package model;


import java.time.LocalDate;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class DailyLogMap extends ConcurrentSkipListMap<LocalDate, DailyLog> {

    public DailyLogMap() {
        super();
    }

    //EFFECT: returns all nonempty ExercisesLogs as ConcurrentSkipListMap<LocalDate, LogVector>
    //        between fromKey inclusive to toKey exclusive
    //REQUIRES: fromKey > toKey, fromKey & toKey are not null, fromKey and toKey must bound some DailyLogs
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


}
