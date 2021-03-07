package model;

import exceptions.OutOfRange;
import model.entries.ExerciseEntry;
import model.entries.LogEntry;
import model.vectors.ExerciseList;
import model.vectors.LogList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class LogListTest {
    // since LogEntry is abstract ExerciseLogs are used as entries with apparent type LogEntry
    LocalTime t1 = LocalTime.parse("00:00");
    LocalTime t2 = LocalTime.parse("12:00");
    LocalTime t3 = LocalTime.parse("13:30");
    LogEntry e1;
    LogEntry e2;
    LogEntry e3;
    LogEntry e4;
    LogList v1;
    LogList v2;
    LogList v3;

    @BeforeEach
    public void runBefore() {
        try {
            e1 = new ExerciseEntry(1, t1,"exercise1",1,1);
            e2 = new ExerciseEntry(2, t2,"exercise2",5,30);
            e3 = new ExerciseEntry(3, t3,"exercise3",10,60);
            e4 = new ExerciseEntry(4, t1,"exercise2",7,13);

            v1 = new ExerciseList();
            v1.add(e1);
            v1.add(e2);
            v1.add(e3);
            v1.add(e4);

            v2 = new ExerciseList();
            v2.add(e1);

            v3 = new ExerciseList();
        } catch (OutOfRange e) {
            // all inputs valid
        }
    }

    @Test
    public void testContains() {
        // all entries contained
        assertTrue(v1.contains(1));
        assertTrue(v1.contains(2));
        assertTrue(v1.contains(3));
        assertTrue(v1.contains(4));

        // one element vector
        assertTrue(v2.contains(1));
        assertFalse(v2.contains(2));
        assertFalse(v2.contains(3));
        assertFalse(v2.contains(4));

        // empty vector
        assertFalse(v3.contains(1));
        assertFalse(v3.contains(2));
        assertFalse(v3.contains(3));
        assertFalse(v3.contains(4));
    }

    @Test
    public void testIndexOf() {
        // index found first entry
        assertEquals(0, v1.indexFromId(1));

        // index found last entry
        assertEquals(3, v1.indexFromId(4));

        // index found middle entry
        assertEquals(1, v1.indexFromId(2));

        // index found only entry
        assertEquals(0, v2.indexFromId(1));

        // index not found many entries
        assertEquals(-1, v1.indexFromId(32));

        // index not found one entry
        assertEquals(-1, v2.indexFromId(2));
        assertEquals(-1, v2.indexFromId(3));

        // index not found empty vector
        assertEquals(-1, v3.indexFromId(1));
        assertEquals(-1, v3.indexFromId(3));
    }

    @Test
    public void testGetFromId() {
        // element found first entry
        assertEquals(e1, v1.getFromId(1));

        // element found last entry
        assertEquals(e4, v1.getFromId(4));

        // element found middle entry
        assertEquals(e2, v1.getFromId(2));

        // element found only entry
        assertEquals(e1, v2.getFromId(1));

        // element not found many entries
        assertNull(v1.getFromId(32));

        // element not found one entry
        assertNull(v2.getFromId(2));
        assertNull(v2.getFromId(3));

        // element not found empty vector
        assertNull(v3.getFromId(1));
        assertNull(v3.getFromId(3));

    }
}
