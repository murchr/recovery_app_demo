package ui.gui;

import ui.MemoryHandling;
import ui.RecoveryApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LaunchOptions extends JFrame implements ActionListener {
    private JButton loadPrevious;
    private JButton loadFromFile;
    private JButton newUser;

    protected Font font = new Font("SansSerif", Font.PLAIN, 18);

    private RecoveryApp recoveryApp;
    private MemoryHandling memoryHandling;

    public LaunchOptions() {
        recoveryApp = new RecoveryApp();
        memoryHandling = new MemoryHandling(recoveryApp);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(300,400));
        this.setLayout(new GridLayout(3,0));

        buttonSetup();

        this.add(loadPrevious);
        this.add(loadFromFile);
        this.add(newUser);
        this.pack();
        this.setVisible(true);
    }

    // MODIFIES:    this
    // EFFECT:      setup for buttons
    private void buttonSetup() {
        loadPrevious = new JButton();
        loadFromFile = new JButton();
        newUser = new JButton();

        loadPrevious.setText("Load Previous File");
        loadFromFile.setText("Load From File");
        newUser.setText("Create New User");

        loadPrevious.setFont(font);
        loadFromFile.setFont(font);
        newUser.setFont(font);

        loadPrevious.addActionListener(this);
        loadFromFile.addActionListener(this);
        newUser.addActionListener(this);
    }

    // MODIFIES:    this
    // EFFECTS:     loads relevant data from last session and passes to Recovery TraceApp
    //              Throws IOException if unable to read
    private void loadPrevious() throws IOException {
        memoryHandling.loadRuntimeLast();
    }

    // MODIFIES:    this
    // EFFECTS:     launches file explorer to select file to open
    //              Throws IOException if unable to read
    private void loadFromFile() throws IOException {
        int fileChosen; // 0 = yes, 1 = cancel
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));

        fileChosen = fileChooser.showOpenDialog(null);

        if (fileChosen == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getPath());
            memoryHandling.loadRuntimeFrom(file);
        }
    }

    // MODIFIES:    this
    // EFFECTS:     creates new instance of app and passes to Recovery TraceApp
    //              Throws IOException if unable to read
    private void newUser() throws IOException {
        // implement user name option
        memoryHandling.loadRuntimeDefault("Ryan-Default");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == loadPrevious) {
                loadPrevious();
            } else if (e.getSource() == loadFromFile) {
                loadFromFile();
            } else if (e.getSource() == newUser) {
                newUser();
            } else {
                System.out.println("unhandled event");
            }

            new RecoveryTraceApp(recoveryApp, memoryHandling);

        } catch (IOException exc) {
            System.out.println("Unable to read from file: "
                    + memoryHandling.getRecoveryAddresses().getRecoveryAddress());
        } finally {
            this.dispose();
        }
    }
}