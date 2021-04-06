package ui.gui.list;

import model.entries.LogEntry;
import model.lists.LogList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class LogListPanel extends JPanel implements ActionListener {
    protected JLabel name;
    private LogListVis logListVis;
    private LogOptionPanel buttonPanel;
    protected JButton addEntry;
    protected JButton removeEntry;
    protected JButton modifyEntry;
    protected JButton summaryStat;
    protected LogList logList;
    protected Font font = new Font("SansSerif", Font.PLAIN, 18);
    protected int currID = 1;

    public LogListPanel(String listType, LogListVis logListVis, LogList logList) {
        this.logListVis = logListVis;
        this.logList = logList;
        // sets new minimum entry id
        for (LogEntry logEntry : logList) {
            if (logEntry.getEntryId() >= currID) {
                currID = logEntry.getEntryId() + 1;
            }
        }

        initializeButtons();

        name = new JLabel();
        name.setText(listType + ":");
        name.setFont(new Font("SansSerif", Font.BOLD, 22));

        defineItemBounds();

        this.setPreferredSize(new Dimension((int) this.logListVis.getPreferredSize().getWidth()
                + (int) buttonPanel.getPreferredSize().getWidth(),
                (int) (this.logListVis.getPreferredSize().getHeight()
                        + name.getPreferredSize().getHeight())));

        this.setBackground(Color.white);
        this.setLayout(null);
        this.add(name);
        this.add(this.logListVis);
        this.add(buttonPanel);
    }

    private void defineItemBounds() {
        int logListPanelHeight = (int) logListVis.getPreferredSize().getHeight();
        int buttonPanelHeight = (int) buttonPanel.getPreferredSize().getHeight();
        int nameHeight = (int) name.getPreferredSize().getHeight();
        name.setBounds(0, 0,
                (int) logListVis.getPreferredSize().getWidth(),
                nameHeight);
        if ((logListPanelHeight + nameHeight) >= buttonPanelHeight) {
            logListVis.setBounds(0, (int) name.getPreferredSize().getHeight(),
                    (int) logListVis.getPreferredSize().getWidth(),
                    logListPanelHeight);
            buttonPanel.setBounds((int) logListVis.getPreferredSize().getWidth(), 0,
                    (int) buttonPanel.getPreferredSize().getWidth(),
                    (int) (logListPanelHeight + name.getPreferredSize().getHeight()));
        } else {
            logListVis.setBounds(0, (int) name.getPreferredSize().getHeight(),
                    (int) logListVis.getPreferredSize().getWidth(),
                    buttonPanelHeight);
            buttonPanel.setBounds((int) logListVis.getPreferredSize().getWidth(), 0,
                    (int) buttonPanel.getPreferredSize().getWidth(),
                    buttonPanelHeight + nameHeight);
        }
    }

    private void initializeButtons() {
        addEntry = new JButton();
        removeEntry = new JButton();
        modifyEntry = new JButton();
        summaryStat = new JButton();

        addEntry.addActionListener(this);
        removeEntry.addActionListener(this);
        modifyEntry.addActionListener(this);
        summaryStat.addActionListener(this);

        buttonPanel = new LogOptionPanel(addEntry, removeEntry, modifyEntry, summaryStat);
    }

    // MODIFIES: this
    // EFFECTS: launches new window for user to create new log of desired type
    protected abstract void addEntry();

    // MODIFIES: this
    // EFFECTS: Waits for user to select entry to remove
    protected abstract void removeEntry();

    // MODIFIES: this
    // EFFECTS: Waits for user to select the entry to modify and then launches
    //          new window for user to create new log of desired type with with data filled in
    protected abstract void modifyEntry();

    // EFFECTS:     displays summary statistic with image
    protected abstract void launchSummary();

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addEntry) {
            addEntry();
        } else if (e.getSource() == removeEntry) {
            removeEntry();
        } else if (e.getSource() == modifyEntry) {
            modifyEntry();
        } else if (e.getSource() == summaryStat) {
            launchSummary();
        }
    }
}
