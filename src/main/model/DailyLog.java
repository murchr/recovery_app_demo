package model;

import model.entries.ExerciseEntry;
import model.entries.LogEntry;
import model.entries.WeightEntry;
import model.vectors.ExerciseList;
import model.vectors.LogList;
import model.vectors.WeightList;
import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;

public class DailyLog implements Writable {

    private final LocalDate logDate;
    private final ExerciseList exerciseLog;
    private final WeightList weightLog;

    // EFFECT:  logDat is set to the initialized logDate
    public DailyLog(LocalDate logDate) {
        this.logDate = logDate;
        this.exerciseLog = new ExerciseList();
        this.weightLog = new WeightList();
    }

    ///////////////////////
    /*      GETTERS      */
    ///////////////////////

    public LocalDate getLogDate() {
        return this.logDate;
    }

    // EFFECT:  returns new instance of exerciseLog from DailyLog
    public ExerciseList getExerciseLog() {
        return this.exerciseLog.clone();
    }

    // EFFECT:  returns new instance of exerciseLog from DailyLog
    public WeightList getWeightLog() {
        return this.weightLog.clone();
    }

    ///////////////////////
    /*      SETTERS      */
    ///////////////////////

    public void setLogDate(LocalDate logDate) {
        this.logDate.adjustInto(logDate);
    }

    ///////////////////////
    /*   FUNCTIONALITY   */
    ///////////////////////

    // EXERCISE FUNCTIONS

    // MODIFIES:    this
    // EFFECT:      adds new ExerciseEntry to exercisesLog
    public void logNewExercise(ExerciseEntry newExercise) {
        this.exerciseLog.add(newExercise);
    }

    // MODIFIES: this
    // EFFECT: adds all ExerciseEntries from newEntries vector to exercisesLog
    public void logNewExercise(LogList newEntries) {
        exerciseLog.addAll(newEntries);
    }

    // MODIFIES: this
    // EFFECT: removes instance of Exercise with exerciseId, if it is not contained nothing is changed
    public void removeExercise(int exerciseId) {
        exerciseLog.remove(exerciseLog.getFromId(exerciseId));
    }

    // EFFECT: returns how many minutes of exercise contained in dailyLog
    public int dailyExerciseTotal() {
        return dailyExerciseScore(0);
    }

    // EFFECT: returns a daily score for exercise quantity and quality
    public int dailyExerciseScore() {
        return dailyExerciseScore(1);
    }

    // EFFECT: returns a daily exercise score based on duration and intensity, based on equation selected
    //                    ScaleType: 0 - duration
    //                               1 - duration * intensity * scalar
    //                               2 - duration * scalar * intensity ^ scalar
    //                            else - duration
    public int dailyExerciseScore(int scaleType) {
        return exerciseLog.summary(scaleType);
    }

    // WEIGHT FUNCTIONS

    // MODIFIES:    this
    // EFFECTS:     adds new WeightEntry to weightLog
    public void logNewWeight(WeightEntry weightEntry) {
        this.weightLog.add(weightEntry);
    }

    // MODIFIES:    this
    // EFFECTS:     adds all WeightEntries from newEntries vector to weightLog
    public void logNewWeight(LogList newEntries) {
        for (LogEntry we : newEntries) {
            logNewWeight((WeightEntry) we);
        }
    }

    // EFFECTS:     returns average of all WeightEntries rounded to NUM_DECIMAL decimal points
    public double dailyWeightAverage() {
        return weightLog.summary();
    }

    ///////////////////////
    /*        JSON       */
    ///////////////////////

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", logDateToJson());
        json.put("exerciseLog", exerciseLog.toJson().getJSONArray("LogList"));
        json.put("weightLog", weightLog.toJson().getJSONArray("LogList"));
        return json;
    }

    // EFFECTS:     returns the date of DailyLog to JSON file
    private JSONObject logDateToJson() {
        JSONObject json = new JSONObject();
        json.put("year", logDate.getYear());
        json.put("month", logDate.getMonthValue());
        json.put("day", logDate.getDayOfMonth());
        return json;
    }
}