package model;


import java.time.LocalDate;
import java.util.*;

public class DailyLogList extends ArrayList<DailyLog> {

    public DailyLogList() {
        super();
    }

    // EFFECT: find dailyLog in DailyLogList by date, if not found return null
    public DailyLog findByDate(LocalDate d) {
        int i = findIndex(d);
        if (i >= 0) {
            return get(i);
        } else {
            return null;
        }
    }

    // EFFECT: find index of dailyLog in DailyLogList if it exists, else return -1
    public int findIndex(LocalDate d) {
        DailyLog dl;
        for (int i = 0; i < size(); i++) {
            dl = get(i);
            if (dl.getLogDate().compareTo(d) == 0) {
                return i;
            }
        }
        return -1;
    }
}
