package model.statistics;

import model.entries.LogEntry;

public interface SummaryStat {
    // MODIFIES: this
    // EFFECTS: stores relevant information for logEntry
    void store(LogEntry logEntry);

    // MODIFIES: this
    // EFFECTS: stores relevant information for logEntry, non-default option
    void store(LogEntry logEntry, int storeType);

}
