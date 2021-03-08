package model;

import exceptions.OutOfRange;
import model.entries.LogEntry;
import model.entries.WeightEntry;
import model.statistics.WeightStat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeightStatTest {
    private WeightStat weightStat1;
    private WeightStat weightStatRoundDown;
    private WeightStat weightStatNoRound;
    private WeightStat weightStatRoundUp;
    private WeightEntry w1;
    private WeightEntry w2;
    private WeightEntry w3;
    private WeightEntry w4;
    private WeightEntry w5;
    private WeightEntry w6;
    private double w24Round = 160.8;
    private double w25Round = 160.5;
    private double w26Round = 160.2;
    private final LocalTime t1 = LocalTime.parse("00:00");
    private final LocalTime t2 = LocalTime.parse("12:00");
    private final LocalTime t3 = LocalTime.parse("13:30");

    @BeforeEach
    public void setup() {
        weightStat1 = new WeightStat();
        weightStatRoundDown = new WeightStat();
        weightStatNoRound = new WeightStat();
        weightStatRoundUp = new WeightStat();

        try {
            w1 = new WeightEntry(1, t1, 120);
            w2 = new WeightEntry(2, t2, 160);
            w3 = new WeightEntry(3, t3, 200);
            w4 = new WeightEntry(4,161.5);
            w5 = new WeightEntry(5, 161);
            w6 = new WeightEntry(6, 160.45);
        } catch (OutOfRange e) {
            // all inputs valid
        }
    }

    @Test
    public void testConstructor() {
        assertEquals(0, weightStatNoRound.getWeight());
        assertEquals(0, weightStat1.getWeight());
    }

    @Test
    public void testStore() {
        weightStat1.store(w1);
        assertEquals(w1.getWeight(), weightStat1.getWeight());
        weightStat1.store(w2);
        assertEquals((w1.getWeight() + w2.getWeight()) / 2, weightStat1.getWeight());
        weightStat1.store(w3);
        assertEquals((w1.getWeight() + w2.getWeight() + w3.getWeight()) / 3, weightStat1.getWeight());

        weightStatNoRound.store(w2);
        weightStatNoRound.store(w5);
        assertEquals(w25Round, weightStatNoRound.getWeight());

        weightStatRoundUp.store(w2);
        weightStatRoundUp.store(w4);
        assertEquals(w24Round, weightStatRoundUp.getWeight());

        weightStatRoundDown.store(w2);
        weightStatRoundDown.store(w6);
        assertEquals(w26Round, weightStatRoundDown.getWeight());
    }

    @Test
    public void testStoreAlternate() {
        weightStat1.store(w1,1);
        assertEquals(w1.getWeight(), weightStat1.getWeight());
        weightStat1.store(w2, 2);
        assertEquals((w1.getWeight() + w2.getWeight()) / 2, weightStat1.getWeight());
        weightStat1.store(w3, 3);
        assertEquals((w1.getWeight() + w2.getWeight() + w3.getWeight()) / 3, weightStat1.getWeight());

        weightStatNoRound.store(w2, 0);
        weightStatNoRound.store(w5, 5);
        assertEquals(w25Round, weightStatNoRound.getWeight());

        weightStatRoundUp.store(w2, 1);
        weightStatRoundUp.store(w4, 11);
        assertEquals(w24Round, weightStatRoundUp.getWeight());

        weightStatRoundDown.store(w2, 2);
        weightStatRoundDown.store(w6, 7);
        assertEquals(w26Round, weightStatRoundDown.getWeight());
    }
}
