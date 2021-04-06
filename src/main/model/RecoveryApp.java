package model;

import model.entries.ExerciseEntry;
import model.entries.LogEntry;
import model.entries.WeightEntry;

import java.time.LocalDate;
import java.util.Collection;

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

    public void initialize(String user, LocalDate activeDate, DailyLogMap historicalLog) {
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

    // EFFECTS: sets active date to date, stores active DailyLog and replaces it with one representing date
    public void setActiveDate(LocalDate date) {
        this.activeDate = date;
        changeDate(date);
    }

    public void setDailyLogMap(DailyLogMap dailyLogMap) {
        this.historicalLog = dailyLogMap;
    }

    public void setUser(String user) {
        this.user = user;
    }

    // EFFECTS: sets active DailyLog to dailyLog, then sets activeDate to dailyLog's stored date
    public void setDailyLog(DailyLog dailyLog) {
        this.dailyLog = dailyLog;
        this.activeDate = dailyLog.getLogDate();
        storeDay();
    }


    // MODIFIES:    this
    // EFFECT:      if no entry exists in historicalLog for day of someDailyLog, adds dailyLog to historicalLog indexed
    //              by date, otherwise overwrites existing entry for day of someDailyLog
    public void storeDay() {
        historicalLog.put(dailyLog);
    }

    // MODIFIES: this
    // EFFECTS: stores current dailyEntry in historicalLog then replaces it with user entered LocalDate,
    //          if no entry exists of LocalDate creates new DailyLog of user entered date
    public void changeDate(LocalDate newDate) {
        storeDay();
        dailyLog = historicalLog.get(newDate);
        activeDate = newDate;
        if (dailyLog == null) {
            dailyLog = new DailyLog(newDate);
            storeDay();
        }
    }

    public void print() {
        printUser();
        printDate();
        printExercise();
        printWeight();
    }

    public void printMap() {
        Collection<DailyLog> entries = historicalLog.values();
        printUser();
        for (DailyLog dailyLog : entries) {
            printDate(dailyLog);
            printExercise(dailyLog);
            printWeight(dailyLog);
            System.out.println("");
        }
    }

    public void printUser() {
        System.out.println(user);
    }

    public void printDate() {
        printDate(dailyLog);
    }

    private void printDate(DailyLog dailyLog) {
        System.out.println(dailyLog.getLogDate().toString());
    }

    public void printExercise() {
        printExercise(dailyLog);
    }

    private void printExercise(DailyLog dailyLog) {
        System.out.println("\tExercise List:");
        for (LogEntry ee : dailyLog.getExerciseLog()) {
            ExerciseEntry exerciseEntry = (ExerciseEntry) ee;
            System.out.printf("\t\tentryId %d, Exercise Type: %s, Exercise Intensity: %d, Exercise duration: %d\n",
                    exerciseEntry.getEntryId(), exerciseEntry.getExerciseType(), exerciseEntry.getIntensity(),
                    exerciseEntry.getDuration());
        }
    }

    public void printWeight() {
        printWeight(dailyLog);
    }

    private void printWeight(DailyLog dailyLog) {
        System.out.println("\tWeight List:");
        for (LogEntry we : dailyLog.getWeightLog()) {
            WeightEntry weightEntry = (WeightEntry) we;
            System.out.printf("\t\tentryId %d, weight: %s\n",
                    weightEntry.getEntryId(), weightEntry.getWeight());
        }
    }
}
