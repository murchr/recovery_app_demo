package ui.console;

// REFERENCE: code structured around TellerApp example, some code copied verbatim
public class DisplayOptions {

    // EFFECTS: prints loading options to console
    protected void displayLoadOptions() {
        System.out.println("\tl\t-> Load from file");
        System.out.println("\tn\t-> create new session (WARNING: overwrites default save)");
        System.out.println("\telse\t-> Load previous session");
    }

    // EFFECTS: prints options for user to interact with RecoveryTraceApp
    protected void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\td -> check dailyLog date");
        System.out.println("\ta -> add new entry");
        System.out.println("\tv -> get visualization of log entries");
        System.out.println("\tr -> remove Exercise entry by entryId in current dailyLog");
        System.out.println("\tx -> switch active dailyLog with existing dailyLog in dailyLogMap");
        System.out.println("\tm -> memory handling for dailyLogMap (save and load)");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: prints options for user to determine type of visualization for RecoveryTraceApp
    protected void displayVisualizeMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tt -> get total exercise time today");
        System.out.println("\tp -> print exercise entries of current dailyLog");
    }

    // EFFECTS: prints options for user to handle memory with RecoveryTraceApp
    protected void displayMemoryMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ts -> saves current dailyLog and dailyLogMap to file");
        System.out.println("\tl -> loads dailyLog and dailyLogMap from file");
    }

    // EFFECTS: prints options for user to add new entries with RecoveryTraceApp
    protected void displayEntryMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\te -> add new ExerciseEntry");
        System.out.println("\tw -> add new WeightEntry");
    }
}
