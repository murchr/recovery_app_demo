package model;

import exceptions.OutOfRange;
import model.entries.WeightEntry;
import model.lists.WeightList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class WeightListTest {
    // since LogEntry is abstract ExerciseLogs are used as entries with apparent type LogEntry
    private LocalTime t1 = LocalTime.parse("00:00");
    private LocalTime t2 = LocalTime.parse("12:00");
    private LocalTime t3 = LocalTime.parse("13:30");
    private WeightEntry w1;
    private WeightEntry w2;
    private WeightEntry w3;
    private WeightEntry w4;
    private WeightList l1;
    private WeightList l2;
    private WeightList l3;

    @BeforeEach
    public void runBefore() {
        try {
            w1 = new WeightEntry(1, t1, 120);
            w2 = new WeightEntry(2, t2, 160);
            w3 = new WeightEntry(3, t3, 200);
            w4 = new WeightEntry(4, t1,120);

            l1 = new WeightList();
            l1.add(w1);
            l1.add(w2);
            l1.add(w3);
            l1.add(w4);

            l2 = new WeightList();
            l2.add(w1);

            l3 = new WeightList();
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
        assertEquals(w1, l1.getFromId(1));

        // element found last entry
        assertEquals(w4, l1.getFromId(4));

        // element found middle entry
        assertEquals(w2, l1.getFromId(2));

        // element found only entry
        assertEquals(w1, l2.getFromId(1));

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
    public void testSummary() {

    }
}
