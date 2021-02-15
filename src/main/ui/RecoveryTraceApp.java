package ui;

import model.DailyLog;
import model.DailyLogList;
import model.ExerciseEntry;
import model.LogVector;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

public class RecoveryTraceApp implements Serializable {
    private DailyLog dailyLog;
    private DailyLogList historicalLog;
    private Scanner input;


    // EFFECTS: runs the RecoveryTraceApp
    public RecoveryTraceApp() {
        runRecoveryTrace();
    }

    // MODIFIES: this
    // EFFECT: processes user input
    // REFERENCE: code structured around TellerApp example, some code copied verbatim
    private void runRecoveryTrace() {
        boolean keepGoing = true;
        String command = null;

        initialize();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }

        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    // REFERENCE: code structured around TellerApp example, some code copied verbatim
    private void processCommand(String command) {
        if (command.equals("d")) {
            checkDate();
        } else if (command.equals("a")) {
            addExerciseEntry();
        } else if (command.equals("t")) {
            totalExerciseOn();
        } else if (command.equals("p")) {
            displayExercises();
        } else if (command.equals("r")) {
            removeExercise();
        } else {
            System.out.println("Selection not valid");
        }

    }

    // MODIFIES: this
    // EFFECTS: removes exercise of exerciseId from dailyLogs exercisesLog
    private void removeExercise() {
        int exerciseId;

        System.out.println("Enter entryId of exercise you wish to remove:");
        exerciseId = Integer.parseInt(input.next());
        dailyLog.removeExercise(exerciseId);
    }

    // MODIFIES: this
    // EFFECTS: displays exercises of current entry
    private void displayExercises() {
        ExerciseEntry exerciseEntry;
        LogVector exercisesLog = dailyLog.getExerciseLog();

        for (int i = 0; i < exercisesLog.size(); i++) {
            exerciseEntry = (ExerciseEntry) exercisesLog.get(i);
            System.out.printf("\nentryId %d, Exercise Type: %s, Exercise Intensity: %d, Exercise duration: %d",
                    exerciseEntry.getEntryId(),exerciseEntry.getExerciseType(), exerciseEntry.getIntensity(),
                    exerciseEntry.getDuration());
        }
        System.out.println("");
    }

    // MODIFIES: this
    // EFFECTS: returns total minutes of exercise on current date
    private void totalExerciseOn() {
        System.out.printf("\nTotal Exercise in current dailyLog is: %d", dailyLog.dailyExerciseTotal());
    }

    // MODIFIES: this
    // EFFECTS: adds new exercise entry to DailyLog
    private void addExerciseEntry() {
        int id;
        int intensity;
        int duration;
        String type;

        System.out.print("\nEnter unique entryId:\t");
        id = Integer.parseInt(input.next());
        System.out.print("\nEnter exercise type:\t");
        type = input.next();
        System.out.print("\nEnter relative intensity on (1-10):\t");
        intensity = Integer.parseInt(input.next());
        System.out.print("\nEnter duration of workout in minutes:\t");
        duration = Integer.parseInt(input.next());

        dailyLog.logNew(new ExerciseEntry(id, type, intensity, duration));
    }

    //ExerciseEntry(int entryId, String exerciseType, int intensity, int duration)

    // EFFECTS: prints options for user to interact with RecoveryTraceApp
    // REFERENCE: code structured around TellerApp example, some code copied verbatim
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\td -> check dailyLog date");
        System.out.println("\ta -> add new entry to current dailyLogs exerciseLog");
        System.out.println("\tt -> get total exercise time today");
        System.out.println("\tp -> print exercise entries of current dailyLog");
        System.out.println("\tr -> remove entry by entryId in current dailyLog");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: prints the date of the current dailyLog being accessed with formatting
    private void checkDate() {
        System.out.print("Date currently being used is:");
        printDate();
    }


    // EFFECTS: prints the date of the current dailyLog being accessed
    private void printDate() {
        LocalDate d = this.dailyLog.getLogDate();
        System.out.printf("\t %d-%d-%d\n",
                d.getYear(), d.getMonthValue(), d.getDayOfMonth());
    }

    // MODIFIES: this
    // EFFECTS: initializes dailyLog by creating new ones if it is a new day
    private void initialize() {
        dailyLog = new DailyLog(LocalDate.now());
        historicalLog = new DailyLogList();
        input = new Scanner(System.in);
    }

    /*
    Date changing implementation for future builds

    // MODIFIES: this
    // EFFECTS: stores current dailyEntry in historicalLog then replaces it with user entered LocalDate,
    //          if no entry exists of LocalDate creates new DailyLog of user entered date
    private void changeDate() {
        String command = null;
        LocalDate d;

        System.out.print("\n Enter date with following format (YYYY-MM-DD):");
        printDate();
        command = input.next();
        d = LocalDate.parse(command);
        DailyLog dl = historicalLog.findByDate(d);
        if (dl.equals(null)) {
            dailyLog = new DailyLog(d);
        } else {
            storeDay(dailyLog);
            dailyLog = dl;
        }
    }


    // MODIFIES:    this
    // EFFECT:      if no entry exists in historicalLog for day of someDailyLog, adds dailyLog to historicalLog indexed
    //              by date, otherwise overwrites existing entry for day of someDailyLog
    private void storeDay(DailyLog dl) {
        LocalDate d = dl.getLogDate();
        int i = historicalLog.findIndex(d);
        if (i >= 0) {
            historicalLog.remove(i);
            historicalLog.add(i, dl);
        } else {
            historicalLog.add(dl);
        }
    }

     */

    /*
    // New implementation for when application saves data

    // EFFECT:  returns true if dailyLog is not LocalDate date
    private boolean isNewDay() {
        return dailyLog.getLogDate().compareTo(LocalDate.now()) == 0;
    }

     */
}
