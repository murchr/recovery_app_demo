package model.entries;

import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalTime;
import java.util.Comparator;

public abstract class LogEntry implements Comparator<LogEntry>, Writable {
    private LocalTime entryTime;
    private final int entryId;

    // EFFECTS:     Constructor sets time of entry to current hour and minute
    public LogEntry(int entryId) {
        this(entryId, LocalTime.now().withNano(0).withSecond(0));
    }

    // EFFECTS:     Constructor sets time of entry to entryTime
    public LogEntry(int entryId, LocalTime entryTime) {
        this.entryId = entryId;
        this.entryTime = entryTime;
    }

    ///////////////////////
    /*      GETTERS      */
    ///////////////////////

    public LocalTime getTime() {
        return entryTime;
    }

    public int getEntryId() {
        return this.entryId;
    }

    ///////////////////////
    /*      SETTERS      */
    ///////////////////////

    public void setTime(LocalTime t) {
        this.entryTime = t;
    }

    ///////////////////////
    /*   FUNCTIONALITY   */
    ///////////////////////

    // EFFECTS: Allows for class to be compared based on represented time
    @Override
    public int compare(LogEntry o1, LogEntry o2) {
        return o1.getTime().compareTo(o2.getTime());
    }

    ///////////////////////
    /*        JSON       */
    ///////////////////////

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("entryId", entryId);
        json.put("entryTime", logTimeToJson());
        return json;
    }

    // EFFECTS:     returns the date of DailyLog to JSON file
    private JSONObject logTimeToJson() {
        JSONObject json = new JSONObject();
        json.put("hour", entryTime.getHour());
        json.put("minute", entryTime.getMinute());
        return json;
    }
}
