package model.entries;

import exceptions.OutOfRange;
import org.json.JSONObject;

import java.time.LocalTime;

public class WeightEntry extends LogEntry {
    private double weight;

    public WeightEntry(int entryId, double weight) throws OutOfRange {
        super(entryId);
        if (weight <= 0) {
            throw new OutOfRange();
        }
        this.weight = weight;
    }

    public WeightEntry(int entryId, LocalTime entryTime, double weight) throws OutOfRange {
        super(entryId, entryTime);
        if (weight <= 0) {
            throw new OutOfRange();
        }
        this.weight = weight;
    }

    ///////////////////////
    /*      GETTERS      */
    ///////////////////////

    public double getWeight() {
        return weight;
    }

    ///////////////////////
    /*      SETTERS      */
    ///////////////////////

    public void setWeight(double weight) {
        this.weight = weight;
    }

    ///////////////////////
    /*        JSON       */
    ///////////////////////

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("weight", weight);
        return json;
    }
}
