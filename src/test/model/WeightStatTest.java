package model;

import model.entries.LogEntry;
import model.statistics.WeightStat;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalTime;

public class WeightStatTest {
    private WeightStat weightStatFull;
    private WeightStat weightStatEmpty;
    private LogEntry w1;
    private LogEntry w2;
    private LogEntry w3;
    private LocalTime t1 = LocalTime.parse("00:00");
    private LocalTime t2 = LocalTime.parse("12:00");
    private LocalTime t3 = LocalTime.parse("13:30");

    @BeforeEach
    public void setup() {
        weightStatFull = new WeightStat();
        weightStatEmpty = new WeightStat();


    }


}
