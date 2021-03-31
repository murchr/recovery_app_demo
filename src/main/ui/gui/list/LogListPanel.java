package ui.gui.list;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogListPanel extends JPanel implements ActionListener {
    protected JLabel name;
    private LogListVisualization logListPanel;
    private LogOptionPanel buttonPanel;
    protected JButton addEntry;
    protected JButton removeEntry;
    protected JButton modifyEntry;

    public LogListPanel(String listType, LogListVisualization logListPanel) {
        this.logListPanel = logListPanel;

        initializeButtons();

        name = new JLabel();
        name.setText(listType + ":");
        name.setFont(new Font("SansSerif", Font.BOLD, 22));

        defineItemBounds();

        this.setPreferredSize(new Dimension((int) this.logListPanel.getPreferredSize().getWidth()
                + (int) buttonPanel.getPreferredSize().getWidth(),
                (int) (this.logListPanel.getPreferredSize().getHeight() + name.getPreferredSize().getHeight())));

        this.setBackground(Color.white);
        this.setLayout(null);
        this.add(name);
        this.add(this.logListPanel);
        this.add(buttonPanel);
    }

    private void defineItemBounds() {
        int logListPanelHeight = (int) logListPanel.getPreferredSize().getHeight();
        int buttonPanelHeight = (int) buttonPanel.getPreferredSize().getHeight();
        int nameHeight = (int) name.getPreferredSize().getHeight();
        name.setBounds(0, 0,
                (int) logListPanel.getPreferredSize().getWidth(),
                nameHeight);
        if ((logListPanelHeight + nameHeight) >= buttonPanelHeight) {
            logListPanel.setBounds(0, (int) name.getPreferredSize().getHeight(),
                    (int) logListPanel.getPreferredSize().getWidth(),
                    logListPanelHeight);
            buttonPanel.setBounds((int) logListPanel.getPreferredSize().getWidth(), 0,
                    (int) buttonPanel.getPreferredSize().getWidth(),
                    (int) (logListPanelHeight + name.getPreferredSize().getHeight()));
        } else {
            logListPanel.setBounds(0, (int) name.getPreferredSize().getHeight(),
                    (int) logListPanel.getPreferredSize().getWidth(),
                    buttonPanelHeight);
            buttonPanel.setBounds((int) logListPanel.getPreferredSize().getWidth(), 0,
                    (int) buttonPanel.getPreferredSize().getWidth(),
                    buttonPanelHeight + nameHeight);
        }
    }

    private void initializeButtons() {
        addEntry = new JButton();
        removeEntry = new JButton();
        modifyEntry = new JButton();

        addEntry.addActionListener(this);
        removeEntry.addActionListener(this);
        modifyEntry.addActionListener(this);

        buttonPanel = new LogOptionPanel(addEntry, removeEntry, modifyEntry);
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
