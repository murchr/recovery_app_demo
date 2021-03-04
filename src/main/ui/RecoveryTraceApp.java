package ui;

import exceptions.OutOfRange;
import model.DailyLog;
import model.DailyLogMap;
import model.entries.ExerciseEntry;
import model.LogVector;
import model.entries.LogEntry;
import model.entries.WeightEntry;
import persistence.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class RecoveryTraceApp implements Serializable {
    private Scanner input;
    private JsonRecoveryWriter jsonRecoveryWriter;
    private JsonRecoveryReader jsonRecoveryReader;
    private JsonAddressWriter jsonAddressWriter;
    private JsonAddressReader jsonAddressReader;
    private static final String LAST_JSON = "./data/lastRecoveryTraceAddress.json";
    private static final String DEFAULT_JSON = "./data/defaultRecoveryTrace.json";

    private DailyLog dailyLog;
    private DailyLogMap historicalLog;
    private Addresses addresses;

    // EFFECTS: runs the RecoveryTraceApp
    public RecoveryTraceApp() {
        String startCommand;

        input = new Scanner(System.in);
        initializeAddress();
        displayLoadOptions();

        startCommand = input.next();
        startCommand = startCommand.toLowerCase();

        try {
            processLoadOptions(startCommand);
            runRecoveryTrace();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + addresses.getRecoveryAddress());
        }
    }

    ///////////////////////
    /*  STARTUP HELPERS  */
    ///////////////////////

    // MODIFIES: this
    // EFFECTS: initializes RecoveryTraceApp location for storing previous save Address location
    private void initializeAddress() {
        try {
            jsonAddressWriter = new JsonAddressWriter(LAST_JSON);
            jsonAddressReader = new JsonAddressReader(LAST_JSON);
            addresses = jsonAddressReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + LAST_JSON);
        }
    }

    // EFFECTS: prints loading options to console
    private void displayLoadOptions() {
        System.out.println("\tl\t-> Load from file");
        System.out.println("\tn\t-> create new session (WARNING: overwrites default save)");
        System.out.println("\telse\t-> Load previous session");
    }

    // MODIFIES: this
    // EFFECTS: processes user startCommand
    private void processLoadOptions(String startCommand) throws IOException {
        switch (startCommand) {
            case "l":
                loadRuntimeFrom();
                break;
            case "n":
                loadRuntimeDefault();
                break;
            default:
                loadRuntimeLast();
                break;
        }
    }

    // MODIFIES:    this
    // EFFECTS:     loads historicalLog from user determined file
    private void loadRuntimeFrom() throws IOException {
        String source;
        System.out.println("Enter the file address as \"./data/FileName.json\":");
        source = input.next();

        addresses.setRecoveryAddress(source);

        loadRuntime(source);
    }

    // MODIFIES:    this
    // EFFECTS:     loads historicalLog from default file
    private void loadRuntimeDefault() throws IOException {
        addresses.setRecoveryAddress(DEFAULT_JSON);
        LocalDate currentDate = LocalDate.now();

        initialize(DEFAULT_JSON);

        historicalLog = new DailyLogMap("Ryan",currentDate);
        dailyLog = new DailyLog(currentDate);
        storeDay();
    }

    // MODIFIES:    this
    // EFFECTS:     loads historicalLog from last accessed file
    private void loadRuntimeLast() throws IOException {
        loadRuntime(addresses.getRecoveryAddress());
    }

    // MODIFIES:    this
    // EFFECTS:     initialize jsonRecovery read and write, loads historicalLog from source file
    private void loadRuntime(String source) throws IOException {
        initialize(source);

        historicalLog = jsonRecoveryReader.read();
        dailyLog = historicalLog.get(historicalLog.getActiveDate());
        System.out.println("Loaded " + historicalLog.getUser() + " from " + addresses.getRecoveryAddress());
    }

    // MODIFIES: this
    // EFFECTS: initializes RecoveryTraceApp for reading and writing to file from source
    private void initialize(String source) {
        jsonRecoveryWriter = new JsonRecoveryWriter(source);
        jsonRecoveryReader = new JsonRecoveryReader(source);
    }

    ///////////////////////
    /*   FUNCTIONALITY   */
    ///////////////////////

    // MODIFIES: this
    // EFFECT: processes user input
    // REFERENCE: code structured around TellerApp example, some code copied verbatim
    private void runRecoveryTrace() {
        boolean keepGoing = true;
        String command;

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

    // EFFECTS: prints options for user to interact with RecoveryTraceApp
    // REFERENCE: code structured around TellerApp example, some code copied verbatim
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\td -> check dailyLog date");
        System.out.println("\ta -> add new entry");
        System.out.println("\tt -> get total exercise time today");
        System.out.println("\tp -> print exercise entries of current dailyLog");
        System.out.println("\tr -> remove entry by entryId in current dailyLog");
        System.out.println("\tx -> switch active dailyLog with existing dailyLog in dailyLogMap");
        System.out.println("\ts -> saves current dailyLog and dailyLogMap to file");
        System.out.println("\tl -> loads dailyLog and dailyLogMap from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    // REFERENCE: code structured around TellerApp example, some code copied verbatim
    private void processCommand(String command) {
        switch (command) {
            case "d":
                checkDate();
                break;
            case "a":
                addEntry();
                break;
            case "t":
                totalExerciseOn();
                break;
            case "p":
                displayExercises();
                break;
            case "r":
                removeExercise();
                break;
            case "x":
                changeDate();
                break;
            case "s":
                saveRuntime();
                break;
            case "l":
                handleLoadRuntimeFrom();
                break;
            default:
                System.out.println("Selection not valid");
                break;
        }

    }

    private void addEntry() {
        displayEntryMenu();
        String command = input.next();
        command = command.toLowerCase();
        processEntry(command);
    }

    private void displayEntryMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\te -> add new ExerciseEntry");
        System.out.println("\tw -> add new WeightEntry");
    }

    private void processEntry(String command) {
        switch (command) {
            case "e":
                addExerciseEntry();
                break;
            case "w":
                addWeightEntry();
                break;
            default:
                System.out.println("Selection not valid");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: adds new exercise entry to DailyLog
    private void addExerciseEntry() {
        int id;
        int intensity;
        int duration;
        String type;

        try {
            System.out.print("\nEnter unique entryId:\t");
            id = Integer.parseInt(input.next());
            System.out.print("\nEnter exercise type:\t");
            type = input.next();
            System.out.print("\nEnter relative intensity on (1-10):\t");
            intensity = Integer.parseInt(input.next());
            System.out.print("\nEnter duration of workout in minutes:\t");
            duration = Integer.parseInt(input.next());

            dailyLog.logNewExercise(new ExerciseEntry(id, type, intensity, duration));
        } catch (NumberFormatException e) {
            System.out.println("Input could not be parsed into correct data type");
        } catch (OutOfRange e) {
            System.out.println("Input was outside of valid range");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds new exercise entry to DailyLog
    private void addWeightEntry() {
        int id;
        double weight;

        try {
            System.out.print("\nEnter unique entryId:\t");
            id = Integer.parseInt(input.next());
            System.out.println("\nEnter weight in lbs:\t");
            weight = Double.parseDouble(input.next());
            dailyLog.logNewWeight(new WeightEntry(id, weight));
        } catch (NumberFormatException e) {
            System.out.println("Input could not be parsed into correct data type");
        } catch (OutOfRange e) {
            System.out.println("Input was not a valid weight");
        }
    }

    // EFFECTS: prints the date of the current dailyLog being accessed with formatting
    private void checkDate() {
        System.out.print("Date currently being used is:");
        printCurrentDate();
    }

    // MODIFIES: this
    // EFFECTS: returns total minutes of exercise on current date
    private void totalExerciseOn() {
        System.out.printf("\nTotal Exercise in current dailyLog is: %d", dailyLog.dailyExerciseTotal());
    }

    // MODIFIES: this
    // EFFECTS: displays exercises of current entry
    private void displayExercises() {
        ExerciseEntry exerciseEntry;
        LogVector exercisesLog = dailyLog.getExerciseLog();

        for (LogEntry logEntry : exercisesLog) {
            exerciseEntry = (ExerciseEntry) logEntry;
            System.out.printf("\nentryId %d, Exercise Type: %s, Exercise Intensity: %d, Exercise duration: %d",
                    exerciseEntry.getEntryId(), exerciseEntry.getExerciseType(), exerciseEntry.getIntensity(),
                    exerciseEntry.getDuration());
        }
        System.out.print("\n");
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
    // EFFECTS: stores current dailyEntry in historicalLog then replaces it with user entered LocalDate,
    //          if no entry exists of LocalDate creates new DailyLog of user entered date
    private void changeDate() {
        String command;
        LocalDate d;

        System.out.print("\n Enter date with following format (YYYY-MM-DD):");
        printCurrentDate();
        command = input.next();
        try {
            command = formatDate(command);
            d = LocalDate.parse(command);
            storeDay();
            dailyLog = historicalLog.get(d);
            if (dailyLog == null) {
                dailyLog = new DailyLog(d);
                historicalLog.put(dailyLog);
            }
            historicalLog.setActiveDate(dailyLog.getLogDate());

            System.out.print("Day successfully changed to ");
            printCurrentDate();
        } catch (DateTimeParseException e) {
            System.out.println("Input is not a valid day");
        }
    }

    // EFFECTS:     formats command string to YYYY-MM-DD format if separated by '-',
    //              throws DateTimeParseException if more or less than 2 '-' exist in command
    private String formatDate(String command) throws DateTimeParseException {
        int year;
        int month;
        int day;
        int firstDash = command.indexOf('-');
        int secondDash = command.indexOf('-', firstDash + 1);
        int thirdDash = command.indexOf('-', secondDash + 1);

        if (firstDash == -1 || secondDash == -1 || thirdDash != -1) {
            throw new DateTimeParseException("Invalid dashes -","",205);
        }
        year = Integer.parseInt(command.substring(0,firstDash));
        month = Integer.parseInt(command.substring(firstDash + 1, secondDash));
        day = Integer.parseInt(command.substring(secondDash + 1));

        return String.format("%4d-%02d-%02d", year, month, day);
    }

    // EFFECTS:     saves active DailyLog and DailyLogMap to file
    // modeled after JsonSerializationDemo
    private void saveRuntime() {
        storeDay();

        try {
            jsonRecoveryWriter.open();
            jsonRecoveryWriter.write(historicalLog);
            jsonRecoveryWriter.close();
            System.out.println("Saved DailyLog and DailyLogMap to" + addresses.getRecoveryAddress());
            saveAddress();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + addresses.getRecoveryAddress());
        }
    }

    // EFFECTS:     saves active address to lastRecoveryTraceAddress
    private void saveAddress() {
        try {
            jsonAddressWriter.open();
            jsonAddressWriter.write(addresses);
            jsonAddressWriter.close();
            System.out.println("Saved recovery address to" + LAST_JSON);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + LAST_JSON);
        }

    }

    private void handleLoadRuntimeFrom() {
        try {
            loadRuntimeFrom();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + addresses.getRecoveryAddress());
        }
    }



    // EFFECTS: prints the date of the current dailyLog being accessed
    private void printCurrentDate() {
        printDate(dailyLog.getLogDate());
    }

    // EFFECTS: prints the date of the current dailyLog being accessed
    private void printDate(LocalDate localDate) {
        System.out.printf("\t %4d-%02d-%02d\n",
                localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
    }


    // MODIFIES:    this
    // EFFECT:      if no entry exists in historicalLog for day of someDailyLog, adds dailyLog to historicalLog indexed
    //              by date, otherwise overwrites existing entry for day of someDailyLog
    private void storeDay() {
        historicalLog.put(dailyLog);
    }
}
