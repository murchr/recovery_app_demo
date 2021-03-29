package ui.gui;

import model.DailyLog;
import model.DailyLogMap;
import ui.MemoryHandling;
import ui.RecoveryApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private DailyLogVisualization dailyLogVisualization;

    private int height = PanelSizes.RECOVERY_APP.getHeight();
    private int width = PanelSizes.RECOVERY_APP.getWidth();
    private Font font = new Font("SansSerif", Font.PLAIN, 18);

    private RecoveryApp recoveryApp;
    private MemoryHandling memoryHandling;

    public RecoveryTraceApp(RecoveryApp recoveryApp, MemoryHandling memoryHandling) {
        this.recoveryApp = recoveryApp;
        this.memoryHandling = memoryHandling;

        menuSetup();
        optionsSetup();
        dailyLogSetup();
        titleSetup();

        System.out.println(dailyLogVisualization.getWidth());
        System.out.println(title.getWidth());
        title.setLocation(0,0);
        dailyLogVisualization.setBounds(0,22,
                dailyLogVisualization.getWidth(), dailyLogVisualization.getHeight());
        recoveryAppOptions.setBounds(this.getWidth() - recoveryAppOptions.getWidth(), 0,
                recoveryAppOptions.getWidth(), this.getHeight());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(4,4));
        this.setPreferredSize(new Dimension(600,800));
        this.setVisible(true);

        this.add(title);
        this.add(dailyLogVisualization);
        this.add(recoveryAppOptions);
        this.pack();
        this.setBackground(Color.lightGray);
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

        changeDate.setText("Change Date");
        historyVisualize.setText("Historical Visualization");

        changeDate.setFont(font);
        historyVisualize.setFont(font);

        changeDate.addActionListener(this);
        historyVisualize.addActionListener(this);

        recoveryAppOptions.setLayout(new GridLayout(5, 0));
        recoveryAppOptions.add(changeDate);
        recoveryAppOptions.add(historyVisualize);
    }

    private void dailyLogSetup() {
        dailyLogVisualization = new DailyLogVisualization(recoveryApp.getDailyLog());
    }

    private void titleSetup() {
        title = new JLabel();
        title.setText("Pain Recovery App: " + recoveryApp.getUser());
        title.setFont(font);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadRecoveryApp) {
            // stub
        } else if (e.getSource() == saveRecoveryApp) {
            //stub
        } else if (e.getSource() == saveAsRecoveryApp) {
            //stub
        } else if (e.getSource() == changeDate) {
            recoveryApp.print();
        } else if (e.getSource() == historyVisualize) {
            //stub
        } else {
            System.out.println("unhandled event");
        }
    }
}
