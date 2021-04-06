package model;

import exceptions.OutOfRange;
import model.entries.ExerciseEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class ExerciseEntryTest {

    private ExerciseEntry e1;
    private ExerciseEntry e2;
    private ExerciseEntry e3;
    private LocalTime t1 = LocalTime.parse("00:00");
    private LocalTime t2 = LocalTime.parse("12:00");
    private LocalTime t3 = LocalTime.parse("13:30");

    @BeforeEach
    public void runBefore() {
        try {
            e1 = new ExerciseEntry(1, t1, "exercise1", 1, 1);
            e2 = new ExerciseEntry(2, t2, "exercise2", 5, 30);
            e3 = new ExerciseEntry(3, t3, "exercise3", 10, 60);
        } catch (OutOfRange e) {
            // all inputs valid
        }
    }

    @Test
    public void testConstructorTooLowIntensity() {
        try {
            new ExerciseEntry(1,t1,"exercise1",0,20);
            fail();
        } catch (OutOfRange e) {
            // expected out of range
        }

        try {
            new ExerciseEntry(1,t1,"exercise1",-3,20);
            fail();
        } catch (OutOfRange e) {
            // expected out of range
        }
    }

    @Test
    public void testConstructorTooHighIntensity() {
        try {
            new ExerciseEntry(1,t1,"exercise1",11,20);
            fail();
        } catch (OutOfRange e) {
            // expected out of range
        }

        try {
            new ExerciseEntry(1,t1,"exercise1",15,20);
            fail();
        } catch (OutOfRange e) {
            // expected out of range
        }
    }

    @Test
    public void testConstructorLowDuration() {
        try {
            new ExerciseEntry(1,t1,"exercise1",5,0);
            fail();
        } catch (OutOfRange e) {
            // expected out of range
        }

        try {
            new ExerciseEntry(1,t1,"exercise1",5,-15);
            fail();
        } catch (OutOfRange e) {
            // expected out of range
        }
    }

    @Test
    public void testConstructorEdgeCases() {
        try {
            new ExerciseEntry(1,t1,"exercise1",5,1);
            new ExerciseEntry(1,t1,"exercise1",1,50);
            new ExerciseEntry(1,t1,"exercise1",10,50);
            new ExerciseEntry(1,t1,"exercise1",1,1);
            new ExerciseEntry(1,t1,"exercise1",10,1);
        } catch (OutOfRange e) {
            fail();
        }
    }

    @Test
    public void testSetTime() {
        // set to same time
        e3.setTime(t3);
        assertEquals(t3, e3.getTime());

        // set to different time
        e1.setTime(t2);
        assertEquals(t2, e1.getTime());
    }

    @Test
    public void testSetExerciseType() {
        // set to current value
        e1.setExerciseType("exercise1");
        assertEquals("exercise1", e1.getExerciseType());

        // set to new value
        e1.setExerciseType("run");
        assertEquals("run", e1.getExerciseType());
    }

    @Test
    public void testSetIntensity() {
        // set to current value
        e1.setIntensity(1);
        assertEquals(1, e1.getIntensity());

        // set to new value
        e1.setIntensity(9);
        assertEquals(9, e1.getIntensity());
    }

    @Test
    public void testSetDuration() {
        // set to current value
        e1.setDuration(1);
        assertEquals(1, e1.getDuration());

        // set to new value
        e1.setDuration(120);
        assertEquals(120, e1.getDuration());
    }
}
