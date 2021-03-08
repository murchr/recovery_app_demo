package model;

import exceptions.OutOfRange;
import model.entries.WeightEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeightEntryTest {

    private WeightEntry w1;
    private WeightEntry w2;
    private WeightEntry w3;
    private LocalTime t1 = LocalTime.parse("00:00");
    private LocalTime t2 = LocalTime.parse("12:00");
    private LocalTime t3 = LocalTime.parse("13:30");
    @BeforeEach
    public void runBefore() {
        try {
            w1 = new WeightEntry(1, t1, 120);
            w2 = new WeightEntry(2, t2, 160);
            w3 = new WeightEntry(3, t3, 200);
        } catch (OutOfRange e) {
            // all inputs valid
        }
    }

    @Test
    public void testSetTime() {
        // set to same time
        w3.setTime(t3);
        assertEquals(t3, w3.getTime());

        // set to different time
        w1.setTime(t2);
        assertEquals(t2, w1.getTime());
    }

    @Test
    public void testSetWeight() {
        // set to current value
        w1.setWeight(1);
        assertEquals(1, w1.getWeight());

        // set to new value
        w1.setWeight(9);
        assertEquals(9, w1.getWeight());
    }
}
