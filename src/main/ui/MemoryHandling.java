package ui;

import model.DailyLog;
import model.DailyLogMap;
import persistence.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public class MemoryHandling {
    private JsonRecoveryWriter jsonRecoveryWriter;
    private JsonRecoveryReader jsonRecoveryReader;
    private JsonAddressWriter jsonAddressWriter;
    private JsonAddressReader jsonAddressReader;
    private static final String LAST_JSON = "./data/lastRecoveryTraceAddress.json";
    private static final String DEFAULT_JSON = "./data/defaultRecoveryTrace.json";
    private RecoveryApp recoveryApp;

    private Addresses addresses;

    public MemoryHandling(RecoveryApp recoveryApp) {
        this.recoveryApp = recoveryApp;
        initializeAddress();
    }

    // MODIFIES: this
    // EFFECTS: initializes RecoveryTraceApp location for storing previous save Address location
    public void initializeAddress() {
        try {
            jsonAddressWriter = new JsonAddressWriter(LAST_JSON);
            jsonAddressReader = new JsonAddressReader(LAST_JSON);
            addresses = jsonAddressReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + LAST_JSON);
        }
    }

    // MODIFIES:    this
    // EFFECTS:     loads historicalLog, activeDate, and user from source file
    public void loadRuntimeFrom(File source) throws IOException {
        addresses.setRecoveryAddress(source.toString());
        loadRuntime(addresses.getRecoveryAddress());
    }

    // MODIFIES:    this
    // EFFECTS:     creates new historicalLog, sets activeDate to current, retrieves new user,
    //              and changes last accessed file to default
    public void loadRuntimeDefault(String user) throws IOException {
        addresses.setRecoveryAddress(DEFAULT_JSON);
        recoveryApp.setActiveDate(LocalDate.now());

        initialize(DEFAULT_JSON);

        recoveryApp.setUser(user);
        recoveryApp.setDailyLogMap(new DailyLogMap());
        recoveryApp.setDailyLog(new DailyLog(recoveryApp.getActiveDate()));
        recoveryApp.storeDay();
    }

    // MODIFIES:    this
    // EFFECTS:     loads historicalLog, activeDate, and user from last accessed file
    public void loadRuntimeLast() throws IOException {
        loadRuntime(addresses.getRecoveryAddress());
    }

    // MODIFIES:    this
    // EFFECTS:     initialize jsonRecovery read and write, loads historicalLog from source file
    private void loadRuntime(String source) throws IOException {
        initialize(source);

        recoveryApp.setDailyLogMap(jsonRecoveryReader.readDailyLogMap());
        recoveryApp.setUser(jsonRecoveryReader.readUser());
        recoveryApp.setActiveDate(jsonRecoveryReader.readActiveDay()); // tied to active dailyLog

        System.out.println("Loaded " + recoveryApp.getUser() + " from " + addresses.getRecoveryAddress());
    }


    // MODIFIES: this
    // EFFECTS: initializes RecoveryTraceApp for reading and writing to file from source
    public void initialize(String source) throws IOException {
        jsonRecoveryWriter = new JsonRecoveryWriter(source);
        jsonRecoveryReader = new JsonRecoveryReader(source);
    }

    // EFFECTS:     saves active DailyLog and DailyLogMap to file
    // modeled after JsonSerializationDemo
    public void saveRuntime() {
        recoveryApp.storeDay();

        try {
            jsonRecoveryWriter.open();
            jsonRecoveryWriter.write(recoveryApp.getUser(), recoveryApp.getActiveDate(), recoveryApp.getDailyLogMap());
            jsonRecoveryWriter.close();
            System.out.println("Saved DailyLog and DailyLogMap to" + addresses.getRecoveryAddress());
            saveAddress();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + addresses.getRecoveryAddress());
        }
    }

    // EFFECTS:     saves active address to lastRecoveryTraceAddress
    public void saveAddress() {
        try {
            jsonAddressWriter.open();
            jsonAddressWriter.write(addresses);
            jsonAddressWriter.close();
            System.out.println("Saved recovery address to" + LAST_JSON);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + LAST_JSON);
        }
    }

    public Addresses getRecoveryAddresses() {
        return addresses;
    }
}
