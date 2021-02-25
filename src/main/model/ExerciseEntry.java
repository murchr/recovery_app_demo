package model;

import java.time.LocalTime;

public class ExerciseEntry extends LogEntry {
    private String exerciseType;
    private int intensity;
    private int duration;

    // REQUIRES:    intensity is [1,10], duration is a positive integer [1, inf]
    // EFFECTS:     Constructor sets time of entry to now and initializes entryId,
    //              exerciseType, intensity, and duration
    public ExerciseEntry(int entryId, String exerciseType, int intensity, int duration) {
        super(entryId);
        this.exerciseType = exerciseType;
        this.intensity = intensity;
        this.duration = duration;
    }

    // REQUIRES:    intensity is [1,10], duration is a positive integer [1, inf]
    // EFFECTS:     Constructor initializes entryId, entryTime, exerciseType, intensity, and duration
    public ExerciseEntry(int entryId, LocalTime entryTime, String exerciseType, int intensity, int duration) {
        super(entryId, entryTime);
        this.exerciseType = exerciseType;
        this.intensity = intensity;
        this.duration = duration;
    }

    // Effects:     gets exerciseType
    public String getExerciseType() {
        return this.exerciseType;
    }

    // Effects:     gets intensity
    public int getIntensity() {
        return this.intensity;
    }

    // EFFECTS: gets duration;
    public int getDuration() {
        return this.duration;
    }

    // Effects:     sets exerciseType
    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    // REQUIRES:    intensity is [1,10]
    // Effects:     sets intensity
    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    // REQUIRES:    duration is a positive, non-zero integer [1, inf]
    // Effects:     sets duration
    public void setDuration(int duration) {
        this.duration = duration;
    }

}
