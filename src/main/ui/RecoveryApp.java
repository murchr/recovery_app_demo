package ui;

import model.DailyLog;
import model.DailyLogMap;
import model.entries.ExerciseEntry;
import model.entries.LogEntry;
import model.entries.WeightEntry;
import model.lists.WeightList;

import java.time.LocalDate;

public class RecoveryApp {
    private DailyLog dailyLog;
    private DailyLogMap historicalLog;
    private String user;
    private LocalDate activeDate;

    public RecoveryApp() {
        user = "userDefault";
        activeDate = LocalDate.now();
        dailyLog = new DailyLog(activeDate);
        historicalLog = new DailyLogMap();
    }

    /*
    public RecoveryApp(String user, LocalDate activeDate, DailyLogMap historicalLog) {
        this.user = user;
        this.activeDate = activeDate;
        this.historicalLog = historicalLog;

        if (historicalLog.containsKey(activeDate)) {
            this.dailyLog = historicalLog.get(activeDate);
        } else {
            this.dailyLog = new DailyLog(activeDate);
            storeDay();
        }
    }
    */


    ///////////////////////
    /*      GETTERS      */
    ///////////////////////

    public LocalDate getActiveDate() {
        return activeDate;
    }

    public String getUser() {
        return user;
    }

    public DailyLog getDailyLog() {
        return dailyLog;
    }

    public DailyLogMap getDailyLogMap() {
        return historicalLog;
    }

    ///////////////////////
    /*      SETTERS      */
    ///////////////////////

    public void setActiveDate(LocalDate date) {
        this.activeDate = date;
        if (historicalLog.containsKey(date)) {
            this.dailyLog = historicalLog.get(date);
        } else {
            this.dailyLog = new DailyLog(date);
            storeDay();
        }
    }

    public void setDailyLogMap(DailyLogMap dailyLogMap) {
        this.historicalLog = dailyLogMap;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setDailyLog(DailyLog dailyLog) {
        this.dailyLog = dailyLog;
        this.activeDate = dailyLog.getLogDate();
        storeDay();
    }


    // MODIFIES:    this
    // EFFECT:      if no entry exists in historicalLog for day of someDailyLog, adds dailyLog to historicalLog indexed
    //              by date, otherwise overwrites existing entry for day of someDailyLog
    protected void storeDay() {
        historicalLog.put(dailyLog);
    }

    // MODIFIES: this
    // EFFECTS: stores current dailyEntry in historicalLog then replaces it with user entered LocalDate,
    //          if no entry exists of LocalDate creates new DailyLog of user entered date
    private void changeDate(LocalDate newDate) {
        storeDay();
        dailyLog = historicalLog.get(newDate);
        activeDate = newDate;
        if (dailyLog == null) {
            dailyLog = new DailyLog(newDate);
            storeDay();
        }
    }

    public void print() {
        System.out.println(user);
        System.out.println(activeDate.toString());
        for (LogEntry ee : dailyLog.getExerciseLog()) {
            ExerciseEntry exerciseEntry = (ExerciseEntry) ee;
            System.out.printf("\nentryId %d, Exercise Type: %s, Exercise Intensity: %d, Exercise duration: %d",
                    exerciseEntry.getEntryId(), exerciseEntry.getExerciseType(), exerciseEntry.getIntensity(),
                    exerciseEntry.getDuration());
        }
        System.out.println("end exercises");
        for (LogEntry we : dailyLog.getWeightLog()) {
            WeightEntry weightEntry = (WeightEntry) we;
            System.out.printf("\nentryId %d, weight: %s",
                    weightEntry.getEntryId(), weightEntry.getWeight());
        }
        System.out.println("end weights");
    }
}
