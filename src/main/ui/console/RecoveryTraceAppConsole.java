package ui.console;

import exceptions.OutOfRange;
import model.entries.ExerciseEntry;
import model.entries.WeightEntry;
import ui.MemoryHandling;
import ui.RecoveryApp;
import ui.gui.DailyLogVisualization;
import ui.gui.PanelSizes;
import ui.gui.entries.ExerciseVisualization;
import ui.gui.entries.WeightVisualization;
import ui.gui.list.ExerciseListVisualization;
import ui.gui.list.LogListPanel;
import ui.gui.list.WeightListVisualization;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class RecoveryTraceAppConsole extends JFrame {
    private Scanner input;

    private DisplayOptions displayOptions;

    private RecoveryApp recoveryApp;
    private MemoryHandling memoryHandling;

    private JFrame testFrame;

    // EFFECTS: runs the RecoveryTraceApp
    public RecoveryTraceAppConsole() {
        String startCommand;
        displayOptions = new DisplayOptions();
        recoveryApp = new RecoveryApp();
        memoryHandling = new MemoryHandling(recoveryApp);
        testFrame = new JFrame();

        input = new Scanner(System.in);
        memoryHandling.initializeAddress();
        displayOptions.displayLoadOptions();

        startCommand = input.next();
        startCommand = startCommand.toLowerCase();

        // test addresses
        System.out.println("last file:" + memoryHandling.getRecoveryAddress());
        System.out.println("preferences:" + memoryHandling.getPreferencesAddress());

        try {
            processLoadOptions(startCommand);
            runRecoveryTrace();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + memoryHandling.getRecoveryAddress());
        }
    }

    ///////////////////////
    /*  STARTUP HELPERS  */
    ///////////////////////

    // MODIFIES: this
    // EFFECTS: processes user startCommand
    private void processLoadOptions(String startCommand) throws IOException {
        switch (startCommand) {
            case "l":
                memoryHandling.loadRuntimeFrom(getSourceFile());
                break;
            case "n":
                memoryHandling.loadRuntimeDefault(setUser());
                break;
            default:
                memoryHandling.loadRuntimeLast();
                break;
        }
    }

    // MODIFIES:    this
    // EFFECTS:     loads historicalLog, activeDate, and user from user determined file
    private File getSourceFile() {
        String source;
        System.out.println("Enter the FileName as seen in: \"./data/FileName.json\":");
        System.out.println("e.g. FileName (do not include path or .json");
        source = input.next();
        return new File("./data/" + source + ".json");
    }

    // EFFECTS: sets user to entered name
    private String setUser() {
        Scanner input = new Scanner(System.in);
        input.useDelimiter("\n");
        System.out.println("Enter your username");
        return input.next();
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
            displayOptions.displayMenu();
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
        switch (command) {
            case "d":
                checkDate();
                break;
            case "a":
                addEntry();
                break;
            case "v":
                visualizeLog();
                break;
            case "r":
                removeExercise();
                break;
            case "x":
                changeDate();
                break;
            case "m":
                memoryHandling();
                break;
            case "c":
                clearFrame();
                break;
            default:
                System.out.println("Selection not valid");
                break;
        }

    }

    private void visualizeLog() {
        displayOptions.displayVisualizeMenu();
        String command = input.next();
        command = command.toLowerCase();
        processVisualize(command);
    }


    private void processVisualize(String command) {
        switch (command) {
            case "t":
                totalExerciseOn();
                break;
            case "e":
                recoveryApp.printExercise();
                //launchFrame(new ExerciseListVisualization(recoveryApp.getDailyLog().getExerciseLog()));
                launchFrame(new
                        LogListPanel("Exercise Log",
                        new ExerciseListVisualization(recoveryApp.getDailyLog().getExerciseLog())));
                break;
            case "a":
                averageWeight();
                break;
            case "w":
                recoveryApp.printWeight();
                //launchFrame(new WeightListVisualization(recoveryApp.getDailyLog().getWeightLog()));
                launchFrame(new
                        LogListPanel("Weight Log",
                        new WeightListVisualization(recoveryApp.getDailyLog().getWeightLog())));
                break;
            case "p":
                recoveryApp.printMap();
                break;
            case "d":
                launchFrame(new DailyLogVisualization(recoveryApp.getDailyLog()));
                break;
            default:
                System.out.println("Selection not valid");
                break;
        }
        System.out.println("\n");
    }

    private void memoryHandling() {
        displayOptions.displayMemoryMenu();
        String command = input.next();
        command = command.toLowerCase();
        processMemory(command);
    }

    private void processMemory(String command) {
        switch (command) {
            case "s":
                memoryHandling.saveRuntime();
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
        displayOptions.displayEntryMenu();
        String command = input.next();
        command = command.toLowerCase();
        processEntry(command);
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

            recoveryApp.getDailyLog().logNewExercise(new ExerciseEntry(id, type, intensity, duration));
            launchFrame(new ExerciseVisualization(new ExerciseEntry(id, type, intensity, duration)));
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

            recoveryApp.getDailyLog().logNewWeight(new WeightEntry(id, weight));
            launchFrame(new WeightVisualization(new WeightEntry(id, weight)));
        } catch (NumberFormatException e) {
            System.out.println("Input could not be parsed into correct data type");
        } catch (OutOfRange e) {
            System.out.println("Input was not a valid weight");
        }
    }

    // EFFECTS: prints the date of the current dailyLog being accessed with formatting
    private void checkDate() {
        System.out.print("Date currently being used is:");
        recoveryApp.printDate();
    }

    // EFFECTS: returns total minutes of exercise on current date
    private void totalExerciseOn() {
        System.out.printf("\nTotal Exercise in current dailyLog is: %d",
                recoveryApp.getDailyLog().dailyExerciseTotal());
    }

    // EFFECTS: returns average weight of current entry
    private void averageWeight() {
        System.out.println("Average weight today:");
        recoveryApp.getDailyLog().getWeightLog().summary();
    }

    // MODIFIES: this
    // EFFECTS: removes exercise of exerciseId from dailyLogs exercisesLog
    private void removeExercise() {
        int exerciseId;

        System.out.println("Enter entryId of exercise you wish to remove:");
        exerciseId = Integer.parseInt(input.next());
        recoveryApp.getDailyLog().removeExercise(exerciseId);
    }

    // MODIFIES: this
    // EFFECTS: stores current dailyEntry in historicalLog then replaces it with user entered LocalDate,
    //          if no entry exists of LocalDate creates new DailyLog of user entered date
    private void changeDate() {
        String command;
        LocalDate d;

        System.out.print("\n Enter date with following format (YYYY-MM-DD):");
        recoveryApp.printDate();
        command = input.next();
        try {
            command = formatDate(command);
            d = LocalDate.parse(command);

            recoveryApp.changeDate(d);

            System.out.print("Day successfully changed to ");
            recoveryApp.printDate();
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
            throw new DateTimeParseException("Invalid dashes -", "", 205);
        }
        year = Integer.parseInt(command.substring(0, firstDash));
        month = Integer.parseInt(command.substring(firstDash + 1, secondDash));
        day = Integer.parseInt(command.substring(secondDash + 1));

        return String.format("%4d-%02d-%02d", year, month, day);
    }

    private void handleLoadRuntimeFrom() {
        try {
            processLoadOptions("l");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + memoryHandling.getRecoveryAddress());
        }
    }

    private void clearFrame() {
        testFrame.dispose();
        testFrame.removeAll();
        testFrame.pack();
        testFrame.setVisible(true);
        //launchFrame(null);
    }

    private void launchFrame(JPanel panel) {
        testFrame.dispose();
        int frameEdge = 50;

        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setLayout(null);
        testFrame.getContentPane().setPreferredSize(
                new Dimension((int) panel.getPreferredSize().getWidth() + 2 * frameEdge,
                        (int) panel.getPreferredSize().getHeight() + 2 * frameEdge));
        panel.setBounds(frameEdge, frameEdge,
                (int) panel.getPreferredSize().getWidth(),
                (int) panel.getPreferredSize().getHeight());
        testFrame.getContentPane().setBackground(Color.black);
        testFrame.add(panel);
        testFrame.pack();
        testFrame.setVisible(true);

        System.out.println("testFrame Preferred Size");
        System.out.println(testFrame.getPreferredSize().getWidth());
        System.out.println(testFrame.getPreferredSize().getHeight());
        testFrame.setResizable(false);
    }
}