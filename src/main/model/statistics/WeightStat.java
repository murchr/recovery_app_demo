package model.statistics;

import model.entries.LogEntry;
import model.entries.WeightEntry;

public class WeightStat implements SummaryStat {
    private double weight;
    private int numEntries;
    private static final int NUM_DECIMAL = 1;

    @Override
    public void store(LogEntry logEntry) {
        numEntries++;
        weight += ((WeightEntry)logEntry).getWeight();
    }

    @Override
    public void store(LogEntry logEntry, int storeType) {
        store(logEntry);
    }

    // EFFECTS: returns average weight rounded to the xxx.x decimal place
    public double getWeight() {
        return round(weight / numEntries, NUM_DECIMAL);
    }

    // EFFECTS: returns double rounded to i decimal places
    private double round(double d, int i) {
        return Math.round((d * Math.pow(10,i))) / Math.pow(10,i);
    }
}