package model.statistics;

import model.entries.ExerciseEntry;
import model.entries.LogEntry;

public class ExerciseStat implements SummaryStat {
    public static final double SCORE_SCALAR = 1.2;
    private double score;


    public ExerciseStat() {
        score = 0;
    }

    @Override
    public void store(LogEntry logEntry) {
        score += scalingEquation((ExerciseEntry)logEntry, 0);
    }

    @Override
    public void store(LogEntry logEntry, int storeType) {
        score += scalingEquation((ExerciseEntry)logEntry, storeType);
    }

    // EFFECTS: returns score of statistic
    public double getScore() {
        return score;
    }

    // EFFECTS: returns an exercise score based on duration and intensity, based on equation selected
    //                    ScaleType: 0 - duration
    //                               1 - duration * intensity * scalar
    //                               2 - duration * scalar * intensity ^ scalar
    //                            else - duration
    public double scalingEquation(ExerciseEntry exerciseEntry, int scaleType) {
        double duration = exerciseEntry.getDuration();
        double intensity = exerciseEntry.getIntensity();
        switch (scaleType) {
            case 1:
                return duration * intensity * SCORE_SCALAR;
            case 2:
                return duration * SCORE_SCALAR * Math.pow(intensity, SCORE_SCALAR);
            default:
                return duration;
        }
    }
}
