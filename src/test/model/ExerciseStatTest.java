package model;

import exceptions.OutOfRange;
import model.entries.ExerciseEntry;
import model.statistics.ExerciseStat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;

public class ExerciseStatTest {
    private ExerciseStat exerciseStat1;
    private ExerciseStat exerciseStat2;

    private ExerciseEntry e1;
    private ExerciseEntry e2;
    private ExerciseEntry e3;
    private final LocalTime t1 = LocalTime.parse("00:00");
    private final LocalTime t2 = LocalTime.parse("12:00");
    private final LocalTime t3 = LocalTime.parse("13:30");

    @BeforeEach
    public void runBefore() {
        exerciseStat1 = new ExerciseStat();
        exerciseStat2 = new ExerciseStat();

        try {
            e1 = new ExerciseEntry(1, t1, "exercise1", 1, 1);
            e2 = new ExerciseEntry(2, t2, "exercise2", 5, 30);
            e3 = new ExerciseEntry(3, t3, "exercise3", 10, 60);
        } catch (OutOfRange e) {
            // all inputs valid
        }
    }

    @Test
    public void testConstructor() {
        assertEquals(0, exerciseStat2.getScore());
        assertEquals(0, exerciseStat1.getScore());
    }

    @Test
    public void testStore() {
        exerciseStat1.store(e1);
        assertEquals(e1.getDuration(), exerciseStat1.getScore());
        exerciseStat1.store(e2);
        assertEquals(e1.getDuration() + e2.getDuration(), exerciseStat1.getScore());
        exerciseStat1.store(e3);
        assertEquals(e1.getDuration() + e2.getDuration() + e3.getDuration(), exerciseStat1.getScore());

        exerciseStat2.store(e1);
        assertEquals(e1.getDuration(), exerciseStat2.getScore());
        exerciseStat2.store(e1);
        assertEquals(e1.getDuration() * 2, exerciseStat2.getScore());
    }

    @Test
    public void testStoreAlternate() {
        double scoreScalar = ExerciseStat.SCORE_SCALAR;
        double result123 = (e1.getDuration() * e1.getIntensity() * scoreScalar +
                e2.getDuration() * e2.getIntensity() * scoreScalar + e3.getDuration() * e3.getIntensity() * scoreScalar);

        // 1
        exerciseStat1.store(e1, 1);
        assertEquals(e1.getDuration() * e1.getIntensity() * scoreScalar, exerciseStat1.getScore());
        exerciseStat1.store(e2, 1);
        assertEquals((e1.getDuration() * e1.getIntensity() +
                e2.getDuration() * e2.getIntensity()) * scoreScalar, exerciseStat1.getScore());
        exerciseStat1.store(e3, 1);
        assertEquals(result123, exerciseStat1.getScore());

        // 2
        exerciseStat2.store(e1, 2);
        assertEquals(e1.getDuration() * Math.pow(e1.getIntensity(), scoreScalar) * scoreScalar, exerciseStat2.getScore());
        exerciseStat2.store(e2, 2);
        assertEquals((e1.getDuration() * Math.pow(e1.getIntensity(), scoreScalar) +
                e2.getDuration() * Math.pow(e2.getIntensity(), scoreScalar)) * scoreScalar, exerciseStat2.getScore());
    }
}
