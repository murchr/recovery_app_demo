package model;

import exceptions.OutOfRange;
import model.entries.ExerciseEntry;
import model.statistics.ExerciseStat;
import model.lists.ExerciseList;
import model.lists.LogList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DailyLogTest {
    private DailyLog testDailyLog;
    private DailyLog testDailyLogFull;
    private LogList lv;
    private ExerciseEntry e1;
    private ExerciseEntry e2;
    private ExerciseEntry e3;
    private ExerciseEntry e4;
    private ExerciseEntry e5;


    @BeforeEach
    void runBefore() {
        try {
            e1 = new ExerciseEntry(1, LocalTime.of(12,30), "exercise 1", 5,30);
            e2 = new ExerciseEntry(2, LocalTime.of(12,30), "exercise 2", 10,15);
            e3 = new ExerciseEntry(3, LocalTime.of(14,0), "exercise 3", 1,60);
            e4 = new ExerciseEntry(4, LocalTime.of(17,10), "exercise 4", 5,15);
            e5 = new ExerciseEntry(5, LocalTime.of(7,30), "exercise 5", 5,90);
        } catch (OutOfRange e) {
            // all inputs valid
        }


        testDailyLog = new DailyLog(LocalDate.parse("2021-02-22"));
        testDailyLogFull = new DailyLog(LocalDate.parse("2021-02-23"));
        testDailyLogFull.logNewExercise(e1);
        testDailyLogFull.logNewExercise(e2);
        testDailyLogFull.logNewExercise(e3);
        testDailyLogFull.logNewExercise(e4);
        testDailyLogFull.logNewExercise(e5);
        lv = new ExerciseList();
    }

    @Test
    void testConstructor() {
        assertEquals(LocalDate.parse("2021-02-22"),testDailyLog.getLogDate());
        assertEquals(new ExerciseList(),testDailyLog.getExerciseLog());
    }

    @Test
    void testSetDate() {
        testDailyLog.setLogDate(LocalDate.parse("2021-05-22"));
        assertEquals(LocalDate.parse("2021-05-22"), testDailyLog.getLogDate());
    }

    @Test
    void testLogNew() {
        testDailyLog.logNewExercise(e1);
        lv.add(e1);
        assertEquals(lv, testDailyLog.getExerciseLog());
    }

    @Test
    void testMultipleLogNew() {
        ExerciseEntry ee;
        try {
            for (int i = 0; i < 5; i++) {
                ee = new ExerciseEntry(i + 1, LocalTime.of(12, 0), "e", 5, 30);
                testDailyLog.logNewExercise(ee);
                lv.add(ee);
            }
            assertEquals(lv, testDailyLog.getExerciseLog());
        } catch (OutOfRange e) {
            // all inputs valid
        }
    }

    @Test
    void testVectorLogNew() {
        ExerciseEntry ee;
        try {
            for (int i = 0; i < 5; i++) {
                ee = new ExerciseEntry(i + 1, LocalTime.of(12, 0), "e", 5, 30);
                lv.add(ee);
            }
            testDailyLog.logNewExercise(lv);
            assertEquals(lv, testDailyLog.getExerciseLog());
        } catch (OutOfRange e) {
            // all inputs valid
        }
    }

    @Test
    void testEmptyVectorLogNew() {
        testDailyLog.logNewExercise(lv);
        assertEquals(lv,testDailyLog.getExerciseLog());
    }

    @Test
    void testDailyExerciseTotal() {
        assertEquals(0, testDailyLog.dailyExerciseTotal());
        assertEquals(e1.getDuration()+e2.getDuration()+e3.getDuration()+e4.getDuration()+e5.getDuration(),
                testDailyLogFull.dailyExerciseTotal());
    }

    @Test
    void testDailyExerciseScore() {
        ExerciseStat exerciseStat = new ExerciseStat();
        double scalar = exerciseStat.SCORE_SCALAR;
        assertEquals(0, testDailyLog.dailyExerciseScore());
        assertEquals((e1.getDuration() * e1.getIntensity() + e2.getDuration() *  e2.getIntensity() +
                        e3.getDuration() * e3.getIntensity() + e4.getDuration() * e4.getIntensity() +
                        e5.getDuration() * e5.getIntensity()) * scalar, testDailyLogFull.dailyExerciseScore());
        testDailyLog.logNewExercise(e1);
        assertEquals((int)(scalar * e1.getDuration() * Math.pow(e1.getIntensity(), scalar)), testDailyLog.dailyExerciseScore(2));
    }

    @Test
    void testRemoveExercise() {
        testDailyLog.removeExercise(2);
        assertEquals(new ExerciseList(), testDailyLog.getExerciseLog());

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