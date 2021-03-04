package model.entries;

import exceptions.OutOfRange;
import org.json.JSONObject;

import java.time.LocalTime;

public class ExerciseEntry extends LogEntry {
    private String exerciseType;
    private int intensity;
    private int duration;

    // EFFECTS:     Constructor sets time to now and initializes entryId, type, intensity, and duration;
    //                  Throws OutOfRange exception if intensity is not on [1,10] or duration is not a
    //                  positive integer [1, inf)
    public ExerciseEntry(int entryId, String exerciseType, int intensity, int duration) throws OutOfRange {
        super(entryId);
        if (intensity < 1 || intensity > 10 || duration <= 0) {
            throw new OutOfRange();
        }
        this.exerciseType = exerciseType;
        this.intensity = intensity;
        this.duration = duration;
    }

    // EFFECTS:     Constructor initializes entryId, time, type, intensity, and duration;
    //                  Throws OutOfRange exception if intensity is not on [1,10] or duration is not a
    //                  positive integer [1, inf)
    public ExerciseEntry(int entryId, LocalTime time, String type, int intensity, int duration) throws OutOfRange {
        super(entryId, time);
        if (intensity < 1 || intensity > 10 || duration <= 0) {
            throw new OutOfRange();
        }
        this.exerciseType = type;
        this.intensity = intensity;
        this.duration = duration;
    }

    ///////////////////////
    /*      GETTERS      */
    ///////////////////////

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

    ///////////////////////
    /*      SETTERS      */
    ///////////////////////

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

    ///////////////////////
    /*        JSON       */
    ///////////////////////

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("exerciseType", exerciseType);
        json.put("intensity", intensity);
        json.put("duration", duration);
        return json;
    }
}
