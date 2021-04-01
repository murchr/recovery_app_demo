package ui.gui;

import ui.MemoryHandling;
import ui.RecoveryApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class RecoveryTraceApp extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem loadRecoveryApp;
    private JMenuItem saveRecoveryApp;
    private JMenuItem saveAsRecoveryApp;
    private JLabel title;
    private JPanel recoveryAppOptions;
    private JButton changeDate;
    private JButton historyVisualize;
    private JButton refreshButton;
    private DailyLogVisualization dailyLogVisualization;

    private final Font font = new Font("SansSerif", Font.BOLD, 22);

    private int titleHeight;
    private int dailyLogWidth;
    private int dailyLogHeight;
    private int optionsWidth;

    private final RecoveryApp recoveryApp;
    private final MemoryHandling memoryHandling;

    public RecoveryTraceApp(RecoveryApp recoveryApp, MemoryHandling memoryHandling) {
        this.recoveryApp = recoveryApp;
        this.memoryHandling = memoryHandling;

        menuSetup();
        optionsSetup();
        dailyLogSetup();
        titleSetup();
        placeItems();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.getContentPane().setPreferredSize(
                new Dimension(dailyLogWidth + optionsWidth, titleHeight + dailyLogHeight));
        this.setVisible(true);

        this.add(title);
        this.add(dailyLogVisualization);
        this.add(recoveryAppOptions);
        this.pack();
        this.setBackground(Color.lightGray);
    }

    protected RecoveryApp getRecoveryApp() {
        return recoveryApp;
    }

    protected MemoryHandling getMemoryHandling() {
        return memoryHandling;
    }

    private void placeItems() {
        titleHeight = (int) title.getPreferredSize().getHeight();
        dailyLogWidth = (int) dailyLogVisualization.getPreferredSize().getHeight();
        dailyLogHeight = (int) dailyLogVisualization.getPreferredSize().getHeight();
        optionsWidth = (int) recoveryAppOptions.getPreferredSize().getWidth();

        title.setBounds(0, 0,
                dailyLogWidth,
                titleHeight);
        dailyLogVisualization.setBounds(0, 22, dailyLogWidth, dailyLogHeight);
        recoveryAppOptions.setBounds(dailyLogWidth, 0,
                (int) recoveryAppOptions.getPreferredSize().getWidth(),
                dailyLogHeight + titleHeight);
    }

    public void menuSetup() {
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");

        loadRecoveryApp = new JMenuItem("Load");
        saveRecoveryApp = new JMenuItem("Save");
        saveAsRecoveryApp = new JMenuItem("SaveAs");

        loadRecoveryApp.addActionListener(this);
        saveRecoveryApp.addActionListener(this);
        saveAsRecoveryApp.addActionListener(this);

        fileMenu.add(loadRecoveryApp);
        fileMenu.add(saveRecoveryApp);
        fileMenu.add(saveAsRecoveryApp);

        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
    }

    private void optionsSetup() {
        recoveryAppOptions = new JPanel();
        changeDate = new JButton();
        historyVisualize = new JButton();
        refreshButton = new JButton();

        changeDate.setText("Change Date");
        historyVisualize.setText("Historical Visualization");
        refreshButton.setText("Refresh RecoveryApp");

        changeDate.setFont(font);
        historyVisualize.setFont(font);
        refreshButton.setFont(font);

        changeDate.addActionListener(this);
        historyVisualize.addActionListener(this);
        refreshButton.addActionListener(this);

        recoveryAppOptions.setLayout(new GridLayout(5, 0));
        recoveryAppOptions.add(changeDate);
        recoveryAppOptions.add(historyVisualize);
        recoveryAppOptions.add(refreshButton);
    }

    private void dailyLogSetup() {
        dailyLogVisualization = new DailyLogVisualization(recoveryApp.getDailyLog());
    }

    private void titleSetup() {
        title = new JLabel();
        title.setText("Pain Recovery App: " + recoveryApp.getUser());
        title.setFont(font);
    }

    private void loadRecoveryApp() {
        int fileChosen; // 0 = yes, 1 = cancel
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));

        fileChosen = fileChooser.showOpenDialog(null);
        try {
            if (fileChosen == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getPath());
                memoryHandling.loadRuntimeFrom(file);
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + memoryHandling.getRecoveryAddress());
        }
    }

    // EFFECTS:     Launches new window allow user to select data,
    //              if canceled without selection does nothing
    //              else changes date to selected one
    private void launchSwitchDateFrame() {
        new SwitchDateFrame(this);
    }

    // MODIFIES:    this
    // EFFECTS:     changes active date to selectedDate,
    //              rebuilds DailyLogVisualization to new active date
    protected void switchDate(LocalDate selectedDate) {
        recoveryApp.setActiveDate(selectedDate);
        rebuild();
    }

    // MODIFIES:    this
    // EFFECTS:     rebuilds RecoveryTraceApp updating output to that of active DailyLog
    private void rebuild() {
        //dailyLogSetup();
        //titleSetup();
        //placeItems();
        //this.getContentPane().setPreferredSize(
        //        new Dimension(dailyLogWidth + optionsWidth, titleHeight + dailyLogHeight));
        //this.setVisible(true);

        //this.add(title);
        //this.add(dailyLogVisualization);
        //this.add(recoveryAppOptions);
        //
        //this.setBackground(Color.lightGray);
        new RecoveryTraceApp(recoveryApp,memoryHandling);
        this.dispose();
    }

    private void clearFrame() {
        this.removeAll();
    }


    // EFFECTS:     Launches new window with tabs for various visualizations of data
    private void launchVisualizationWindow() {
        JOptionPane.showMessageDialog(null,
                "This feature is under development",
                "Not Yet Implemented",
                JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadRecoveryApp) {
            loadRecoveryApp();
        } else if (e.getSource() == saveRecoveryApp) {
            memoryHandling.saveRuntime();
        } else if (e.getSource() == saveAsRecoveryApp) {
            JOptionPane.showMessageDialog(null,
                    "This feature is under development",
                    "Not Yet Implemented",
                    JOptionPane.WARNING_MESSAGE);
        } else if (e.getSource() == changeDate) {
            launchSwitchDateFrame();
        } else if (e.getSource() == historyVisualize) {
            launchVisualizationWindow();
            recoveryApp.printMap();
        } else if (e.getSource() == refreshButton) {
            rebuild();
        } else {
            System.out.println("unhandled event");
        }
    }
}