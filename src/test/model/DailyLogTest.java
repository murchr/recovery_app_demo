package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DailyLogTest {
    private DailyLog testDailyLog;
    private DailyLog testDailyLogFull;
    private LogVector lv;
    private final ExerciseEntry e1 = new ExerciseEntry(1, LocalTime.of(12,30), "exercise 1", 5,30);
    private final ExerciseEntry e2 = new ExerciseEntry(2, LocalTime.of(12,30), "exercise 2", 10,15);
    private final ExerciseEntry e3 = new ExerciseEntry(3, LocalTime.of(14,0), "exercise 3", 1,60);
    private final ExerciseEntry e4 = new ExerciseEntry(4, LocalTime.of(17,10), "exercise 4", 5,15);
    private final ExerciseEntry e5 = new ExerciseEntry(5, LocalTime.of(7,30), "exercise 5", 5,90);


    @BeforeEach
    void runBefore() {
        testDailyLog = new DailyLog(LocalDate.parse("2021-02-22"));
        testDailyLogFull = new DailyLog(LocalDate.parse("2021-02-23"));
        testDailyLogFull.logNew(e1);
        testDailyLogFull.logNew(e2);
        testDailyLogFull.logNew(e3);
        testDailyLogFull.logNew(e4);
        testDailyLogFull.logNew(e5);
        lv = new LogVector();
    }

    @Test
    void testConstructor() {
        assertEquals(LocalDate.parse("2021-02-22"),testDailyLog.getLogDate());
        assertEquals(new LogVector(),testDailyLog.getExerciseLog());
    }

    @Test
    void testSetDate() {
        testDailyLog.setLogDate(LocalDate.parse("2021-03-22"));
        assertEquals(LocalDate.parse("2021-03-22"), testDailyLog.getLogDate());
    }

    @Test
    void testLogNew() {
        testDailyLog.logNew(e1);
        lv.add(e1);
        assertEquals(lv, testDailyLog.getExerciseLog());
    }

    @Test
    void testMultipleLogNew() {
        ExerciseEntry ee;
        for(int i = 0; i < 5; i++) {
            ee = new ExerciseEntry(i+1,LocalTime.of(12,0),"e",5,30);
            testDailyLog.logNew(ee);
            lv.add(ee);
        }
        assertEquals(lv,testDailyLog.getExerciseLog());
    }

    @Test
    void testVectorLogNew() {
        ExerciseEntry ee;
        for(int i = 0; i < 5; i++) {
            ee = new ExerciseEntry(i+1,LocalTime.of(12,0),"e",5,30);
            lv.add(ee);
        }
        testDailyLog.logNew(lv);
        assertEquals(lv,testDailyLog.getExerciseLog());
    }

    @Test
    void testEmptyVectorLogNew() {
        testDailyLog.logNew(lv);
        assertEquals(lv,testDailyLog.getExerciseLog());
    }



    @Test
    void testDailyExerciseTotal() {
        assertEquals(0, testDailyLog.dailyExerciseTotal());
        assertEquals(e1.getDuration()+e2.getDuration()+e3.getDuration()+e4.getDuration()+e5.getDuration(),
                testDailyLogFull.dailyExerciseTotal());
    }

    @Test
    void testRemoveExercise() {
        testDailyLog.removeExercise(2);
        assertEquals(new LogVector(), testDailyLog.getExerciseLog());

        testDailyLogFull.removeExercise(2);
        testDailyLogFull.removeExercise(3);
        testDailyLogFull.removeExercise(5);
        lv.add(e1);
        lv.add(e2);
        lv.add(e3);
        lv.add(e4);
        lv.add(e5);
        lv.remove(e2);
        lv.remove(e3);
        lv.remove(e5);
        assertEquals(lv, testDailyLogFull.getExerciseLog());
    }
}