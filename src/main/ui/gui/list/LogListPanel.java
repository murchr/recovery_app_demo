package ui.gui.list;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogListPanel extends JPanel implements ActionListener {
    private LogListVisualization logListPanel;
    private LogOptionPanel buttonPanel;
    protected JButton addEntry;
    protected JButton removeEntry;
    protected JButton modifyEntry;

    public LogListPanel(LogListVisualization logListPanel) {
        this.logListPanel = logListPanel;

        addEntry = new JButton();
        removeEntry = new JButton();
        modifyEntry = new JButton();

        this.add(addEntry);
        this.add(removeEntry);
        this.add(modifyEntry);

        addEntry.addActionListener(this);
        removeEntry.addActionListener(this);
        modifyEntry.addActionListener(this);

        buttonPanel = new LogOptionPanel(addEntry, removeEntry, modifyEntry);
        buttonPanel.setSize(new Dimension(buttonPanel.getWidth(), logListPanel.getHeight()));
        this.setSize(new Dimension(this.logListPanel.getWidth() + buttonPanel.getWidth(),
                logListPanel.getHeight()));

        logListPanel.setLocation(0, 0);
        buttonPanel.setLocation(logListPanel.getWidth(), 0);

        this.add(this.logListPanel);
        this.add(buttonPanel);
    }

    // MODIFIES: this
    // EFFECTS: launches new window for user to create new log of desired type
    private void addEntry() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: Waits for user to select entry to remove
    private void removeEntry() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: Waits for user to select the entry to modify and then launches
    //          new window for user to create new log of desired type with with data filled in
    private void modifyEntry() {
        // stub
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addEntry) {
            addEntry();
        } else if (e.getSource() == removeEntry) {
            removeEntry();
        } else if (e.getSource() == modifyEntry) {
            modifyEntry();
        }
    }
}
