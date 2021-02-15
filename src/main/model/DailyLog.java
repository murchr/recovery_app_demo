package model;

import java.time.LocalDate;

public class DailyLog {
    private LocalDate logDate;
    private LogVector exercisesLog;

    // EFFECT:  logDat is set to the initialized logDate
    public DailyLog(LocalDate logDate) {
        this.logDate = logDate;
        this.exercisesLog = new LogVector();
    }

    // EFFECTS: gets the local logDate
    public LocalDate getLogDate() {
        return this.logDate;
    }

    // MODIFIES:    this
    // EFFECT:      changes this.logDate to logDate
    public void setLogDate(LocalDate logDate) {
        this.logDate = logDate;
    }


    // MODIFIES:    this
    // EFFECT:      adds new ExerciseEntry to logExercises
    public void logNew(ExerciseEntry newExercise) {
        this.exercisesLog.add(newExercise);
    }

    // MODIFIES: this
    // EFFECT: removes instance of Exercise with exerciseId, if it is not contained nothing is changed
    public void removeExercise(int exerciseId) {
        exercisesLog.remove(exercisesLog.getFromId(exerciseId));
    }

    // EFFECT:  returns exercise log from DailyLog
    public LogVector getExerciseLog() {
        return this.exercisesLog;
    }

    // EFFECT: returns how many minutes of exercise contained in dailyLog
    public int dailyExerciseTotal() {
        ExerciseEntry exerciseEntry;
        int totalExercise = 0;
        for (int i = 0; i < exercisesLog.size(); i++) {
            exerciseEntry = (ExerciseEntry) exercisesLog.get(i);
            totalExercise += exerciseEntry.getDuration();
        }
        return totalExercise;
    }


}

// REQUIRES: n/a
// MODIFIES:
// EFFECT:
