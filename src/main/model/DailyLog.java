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
    // EFFECT:      adds new ExerciseEntry to exercisesLog
    public void logNew(ExerciseEntry newExercise) {
        this.exercisesLog.add(newExercise);
    }

    // MODIFIES: this
    // EFFECT: adds all ExerciseEntries from newEntries vector to exercisesLog
    public void logNew(LogVector newEntries) {
        for (LogEntry ee : newEntries) {
            logNew((ExerciseEntry) ee);
        }
    }

    // MODIFIES: this
    // EFFECT: removes instance of Exercise with exerciseId, if it is not contained nothing is changed
    public void removeExercise(int exerciseId) {
        exercisesLog.remove(exercisesLog.getFromId(exerciseId));
    }

    // EFFECT:  returns exercise log from DailyLog
    // NOTE - passed by refrence. should create new instance of LogVector?
    public LogVector getExerciseLog() {
        return this.exercisesLog;
    }

    // EFFECT: returns how many minutes of exercise contained in dailyLog
    public int dailyExerciseTotal() {
        return weightedDailyExerciseScore(0,1);
    }
    
    // EFFECT: returns a daily score for exercise quantity and quality
    public int dailyExerciseScore() {
        return weightedDailyExerciseScore(1,1);
    }
    
    // EFFECT: returns a weighted exercise score based on scaling equation
    private int weightedDailyExerciseScore(int scaleType, int scalor) {
        int totalScore = 0;
        for (LogEntry logEntry : exercisesLog) {
            totalScore += scalingEquation((ExerciseEntry)logEntry, scaleType, scalor);
        }
        return totalScore;
    }
    
    // REQUIRES: scaleType must be {0,1,2}
    // EFFECT: returns an exercise score based on duration and intensity, based on equation selected
    //                    ScaleType: 0 - duration
    //                               1 - durtaion*intensity*scalor
    //                               2 - duration*scalor*intensity^scalor
    private int scalingEquation(ExerciseLog exerciseLog, int scaleType, int scalor) {
        int duration = exerciseLog.getDuration();
        int intensity = exerciseLog.getIntensity();
        switch(scaleType) {
            case 0:
                return duration;
                break;
            case 1:
                return duration * intensity * scalor;
                break;
            case 2:
                return duration * scalor * Math.pow(intensity, scalor);
                break;                
        }
    }
}

// REQUIRES: n/a
// MODIFIES:
// EFFECT:
