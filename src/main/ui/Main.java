package ui;

import java.io.*;


public class Main {
    public static void main(String[] args) {
        new RecoveryTraceApp();
    }
}





// potential alternate file storing implementation

/*
    private static final String fileLocation = "./data/savedAppData";
    //private static File newFile = new File(fileLocation);

    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileLocation));
    //find how to run data
    RecoveryTraceApp userApplet = ois.readObject();
    userApplet.runRecoveryTrace();

    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileLocation));
    oos.writeObject(userApplet);
    oos.close();


    // REQUIRES: n/a
    // MODIFIES:
    // EFFECT:

 */