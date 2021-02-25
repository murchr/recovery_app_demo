package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.concurrent.ConcurrentSkipListMap;

import static org.junit.jupiter.api.Assertions.*;

public class DailyLogMapTest {

    DailyLogMap DLM0;
    DailyLogMap DLM;

    LocalTime[] tSet = {LocalTime.parse("00:00"),LocalTime.parse("01:00"),LocalTime.parse("03:00"),
            LocalTime.parse("04:00"),LocalTime.parse("05:00"), LocalTime.parse("06:00")};
    String[] workoutTypes = {"WO1","WO2","WO3"};

    int[] dayIntensity = {1,5,10};
    int[] dayDuration = {1,10,30,60};
    int workoutsPerDay = 3;

    int numOfDays = 10; // must be greater than 7 for test file
    LocalDate startDate = LocalDate.of(2021,1,1);
    Period daysBetweenEntries = Period.of(0,0,1);
    ConcurrentSkipListMap<LocalDate, LogVector> storedLV;



    @BeforeEach
    public void runBefore() {
        LogVector lv;
        DailyLog dl;
        storedLV = new ConcurrentSkipListMap<>();
        DLM0 = new DailyLogMap();
        DLM = new DailyLogMap();

        LocalDate day = LocalDate.from(startDate);
        // generates new map of dailyLogs
        for(int i = 0; i < numOfDays; i++, day.plus(daysBetweenEntries)) {
            lv = new LogVector();
            dl = new DailyLog(day);
            // builds daily exerciseLog
            for(int j = 0; j < workoutsPerDay; j++) {
                lv.add(new ExerciseEntry(j+1,tSet[j% tSet.length], workoutTypes[j%workoutTypes.length],
                        dayIntensity[i%dayIntensity.length],dayDuration[i%dayDuration.length]));
            }
            dl.logNew(lv);
            storedLV.put(dl.getLogDate(),lv);

            // assigns dailyLog instance to DLM
            DLM.put(dl.getLogDate(),dl);
        }
    }

    @Test
    public void testGetExercisesBetween() {
        ConcurrentSkipListMap<LocalDate, LogVector> split1;
        ConcurrentSkipListMap<LocalDate, LogVector> split3;
        ConcurrentSkipListMap<LocalDate, LogVector> split5;
        LocalDate day0;
        LocalDate day;

        // parse from pre first day, multiple lengths
        day0 = LocalDate.from(startDate).minus(daysBetweenEntries);
        day = LocalDate.from(day0);

        day.plus(daysBetweenEntries); // 1
        split1 = DLM.getExercisesBetween(day0,day);
        assertEquals(storedLV.subMap(day0, day), split1);

        day.plus(daysBetweenEntries);
        day.plus(daysBetweenEntries); // 3
        split3 = DLM.getExercisesBetween(day0,day);
        assertEquals(storedLV.subMap(day0, day), split3);

        day.plus(daysBetweenEntries);
        day.plus(daysBetweenEntries); // 5
        split5 = DLM.getExercisesBetween(day0,day);
        assertEquals(storedLV.subMap(day0, day), split5);


        // parse from first day, multiple lengths
        day0 = LocalDate.from(startDate);
        day = LocalDate.from(day0);

        day.plus(daysBetweenEntries); // 1
        split1 = DLM.getExercisesBetween(day0,day);
        assertEquals(storedLV.subMap(day0, day), split1);

        day.plus(daysBetweenEntries);
        day.plus(daysBetweenEntries); // 3
        split3 = DLM.getExercisesBetween(day0,day);
        assertEquals(storedLV.subMap(day0, day), split3);

        day.plus(daysBetweenEntries);
        day.plus(daysBetweenEntries); // 5
        split5 = DLM.getExercisesBetween(day0,day);
        assertEquals(storedLV.subMap(day0, day), split5);



        // parse from middle day, multiple lengths
        day0 = LocalDate.from(startDate).plus(daysBetweenEntries);
        day = LocalDate.from(day0);

        day.plus(daysBetweenEntries); // 1
        split1 = DLM.getExercisesBetween(day0,day);
        assertEquals(storedLV.subMap(day0, day), split1);

        day.plus(daysBetweenEntries);
        day.plus(daysBetweenEntries); // 3
        split3 = DLM.getExercisesBetween(day0,day);
        assertEquals(storedLV.subMap(day0, day), split3);

        day.plus(daysBetweenEntries);
        day.plus(daysBetweenEntries); // 5
        split5 = DLM.getExercisesBetween(day0,day);
        assertEquals(storedLV.subMap(day0, day), split5);


        // parse from end day, multiple lengths
        day0 = LocalDate.from(DLM.lastKey());
        day = LocalDate.from(day0);

        day.minus(daysBetweenEntries); // 1
        split1 = DLM.getExercisesBetween(day,day0);
        assertEquals(storedLV.subMap(day, day0), split1);

        day.minus(daysBetweenEntries);
        day.minus(daysBetweenEntries); // 3
        split3 = DLM.getExercisesBetween(day,day0);
        assertEquals(storedLV.subMap(day, day0), split3);

        day.minus(daysBetweenEntries);
        day.minus(daysBetweenEntries); // 5
        split5 = DLM.getExercisesBetween(day,day0);
        assertEquals(storedLV.subMap(day, day0), split5);


        // parse from past end day, multiple lengths
        day0 = LocalDate.from(DLM.lastKey()).plus(daysBetweenEntries);
        day = LocalDate.from(day0);

        day.minus(daysBetweenEntries); // 1
        split1 = DLM.getExercisesBetween(day,day0);
        assertEquals(storedLV.subMap(day, day0), split1);

        day.minus(daysBetweenEntries);
        day.minus(daysBetweenEntries); // 3
        split3 = DLM.getExercisesBetween(day,day0);
        assertEquals(storedLV.subMap(day, day0), split3);

        day.minus(daysBetweenEntries);
        day.minus(daysBetweenEntries); // 5
        split5 = DLM.getExercisesBetween(day,day0);
        assertEquals(storedLV.subMap(day, day0), split5);
    }


}
