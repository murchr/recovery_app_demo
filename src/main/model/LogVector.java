package model;

import model.entries.LogEntry;
import model.statistics.SummaryStat;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Vector;

public class LogVector extends Vector<LogEntry> implements Writable {

    public LogVector() {
        super();
    }

    // EFFECTS: returns true if entryId is contained in Vector, else false
    public boolean contains(int entryId) {
        return indexFromId(entryId) >= 0;
    }

    // EFFECTS: returns index of first logEntry with matching entryId
    //          if no matching entryId found returns -1
    public int indexFromId(int entryId) {
        for (int i = 0; i < size(); i++) {
            if (entryId == get(i).getEntryId()) {
                return i;
            }
        }
        return -1;
    }

    // EFFECTS: returns logEntry if one matching entryId is found else returns null
    public LogEntry getFromId(int entryId) {
        int i = indexFromId(entryId);
        if (i >= 0) {
            return get(i);
        } else {
            return null;
        }
    }

    // MODIFIES: summaryStat
    // EFFECTS: applies process method to each element in vector with summaryStat
    public void summary(SummaryStat summaryStat) {
        forEach(logEntry -> summaryStat.store(logEntry));
    }

    // MODIFIES: summaryStat
    // EFFECTS: applies process method to each element in vector with summaryStat
    public void summary(SummaryStat summaryStat, int storeType) {
        forEach(logEntry -> summaryStat.store(logEntry, storeType));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        forEach(logEntry -> jsonArray.put(logEntry.toJson()));
        json.put("LogVector", jsonArray);
        return json;
    }
}
