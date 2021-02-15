package model;

import java.time.LocalTime;
import java.util.Comparator;

public class LogEntry implements Comparator<LocalTime> {
    private LocalTime entryTime;
    private int entryId;

    // EFFECTS:     Constructor sets time of entry to now
    public LogEntry(int entryId) {
        this(entryId, LocalTime.now());
    }

    // EFFECTS:     Constructor sets time of entry to entryTime
    public LogEntry(int entryId, LocalTime entryTime) {
        this.entryId = entryId;
        this.entryTime = entryTime;
    }

    // EFFECTS:     returns time of logEntry
    public LocalTime getTime() {
        return entryTime;
    }

    // MODIFIES:    this
    // EFFECTS:     sets entry time to t
    public void setTime(LocalTime t) {
        this.entryTime = t;
    }

    // EFFECTS:     returns name of logEntry
    public int getEntryId() {
        return this.entryId;
    }


    // EFFECTS: Allows for class to be compared based on represented time
    @Override
    public int compare(LocalTime o1, LocalTime o2) {
        return o1.compareTo(o2);
    }
}
