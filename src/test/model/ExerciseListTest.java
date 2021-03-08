package model;

import exceptions.OutOfRange;
import model.entries.ExerciseEntry;
import model.lists.ExerciseList;
import model.lists.WeightList;
import model.statistics.ExerciseStat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class ExerciseListTest {
    // since LogEntry is abstract ExerciseLogs are used as entries with apparent type LogEntry
    private LocalTime t1 = LocalTime.parse("00:00");
    private LocalTime t2 = LocalTime.parse("12:00");
    private LocalTime t3 = LocalTime.parse("13:30");
    private ExerciseEntry e1;
    private ExerciseEntry e2;
    private ExerciseEntry e3;
    private ExerciseEntry e4;
    private ExerciseList l1;
    private ExerciseList l2;
    private ExerciseList l3;

    @BeforeEach
    public void runBefore() {
        try {
            e1 = new ExerciseEntry(1, t1, "exercise1", 1, 1);
            e2 = new ExerciseEntry(2, t2, "exercise2", 5, 30);
            e3 = new ExerciseEntry(3, t3, "exercise3", 10, 60);
            e4 = new ExerciseEntry(4, t1, "exercise2", 7, 13);

            l1 = new ExerciseList();
            l1.add(e1);
            l1.add(e2);
            l1.add(e3);
            l1.add(e4);

            l2 = new ExerciseList();
            l2.add(e1);

            l3 = new ExerciseList();
        } catch (OutOfRange e) {
            // all inputs valid
        }
    }

    @Test
    public void testContains() {
        // all entries contained
        assertTrue(l1.contains(1));
        assertTrue(l1.contains(2));
        assertTrue(l1.contains(3));
        assertTrue(l1.contains(4));

        // one element vector
        assertTrue(l2.contains(1));
        assertFalse(l2.contains(2));
        assertFalse(l2.contains(3));
        assertFalse(l2.contains(4));

        // empty vector
        assertFalse(l3.contains(1));
        assertFalse(l3.contains(2));
        assertFalse(l3.contains(3));
        assertFalse(l3.contains(4));
    }

    @Test
    public void testIndexOf() {
        // index found first entry
        assertEquals(0, l1.indexFromId(1));

        // index found last entry
        assertEquals(3, l1.indexFromId(4));

        // index found middle entry
        assertEquals(1, l1.indexFromId(2));

        // index found only entry
        assertEquals(0, l2.indexFromId(1));

        // index not found many entries
        assertEquals(-1, l1.indexFromId(32));

        // index not found one entry
        assertEquals(-1, l2.indexFromId(2));
        assertEquals(-1, l2.indexFromId(3));

        // index not found empty vector
        assertEquals(-1, l3.indexFromId(1));
        assertEquals(-1, l3.indexFromId(3));
    }

    @Test
    public void testGetFromId() {
        // element found first entry
        assertEquals(e1, l1.getFromId(1));

        // element found last entry
        assertEquals(e4, l1.getFromId(4));

        // element found middle entry
        assertEquals(e2, l1.getFromId(2));

        // element found only entry
        assertEquals(e1, l2.getFromId(1));

        // element not found many entries
        assertNull(l1.getFromId(32));

        // element not found one entry
        assertNull(l2.getFromId(2));
        assertNull(l2.getFromId(3));

        // element not found empty vector
        assertNull(l3.getFromId(1));
        assertNull(l3.getFromId(3));

    }

    @Test
    public void testClone() {
        ExerciseList l2Clone = l2.clone();
        l2Clone.add(e3);
        assertNotEquals(l2, l2Clone);
    }

    @Test
    public void testSummaryDefault() {
        // 0
        assertEquals(e1.getDuration() + e2.getDuration() + e3.getDuration() + e4.getDuration(),
                l1.summary(0));
        assertEquals(e1.getDuration(), l2.summary(0));
        assertEquals(0, l3.summary(0));
    }

    @Test
    public void testSummaryAlt1() {
        double scoreScalar = ExerciseStat.SCORE_SCALAR;
        // 1
        assertEquals(((int) (e1.getDuration() * e1.getIntensity() * scoreScalar +
                e2.getDuration() * e2.getIntensity() * scoreScalar +
                e3.getDuration() * e3.getIntensity() * scoreScalar +
                e4.getDuration() * e4.getIntensity() * scoreScalar)), l1.summary(1));
        assertEquals((int) (e1.getDuration() * e1.getIntensity() * scoreScalar), l2.summary(1));
        assertEquals(0, l3.summary(1));
    }

    @Test
    public void testSummaryAlt2() {
        double scoreScalar = ExerciseStat.SCORE_SCALAR;
        // 2
        assertEquals((int) (e1.getDuration() * Math.pow(e1.getIntensity(), scoreScalar) * scoreScalar +
                        e2.getDuration() * Math.pow(e2.getIntensity(), scoreScalar) * scoreScalar +
                        e3.getDuration() * Math.pow(e3.getIntensity(), scoreScalar) * scoreScalar +
                        e4.getDuration() * Math.pow(e4.getIntensity(), scoreScalar) * scoreScalar),
                l1.summary(2));
        assertEquals((int) (e1.getDuration() * Math.pow(e1.getIntensity(), scoreScalar) * scoreScalar),
                l2.summary(2));
        assertEquals(0, l3.summary(2));
    }
}
