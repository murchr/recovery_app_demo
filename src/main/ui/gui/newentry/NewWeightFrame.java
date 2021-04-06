package ui.gui.newentry;

import exceptions.OutOfRange;
import model.entries.LogEntry;
import model.entries.WeightEntry;
import model.lists.LogList;

import javax.swing.*;
import java.awt.*;

public class NewWeightFrame extends NewEntryFrame {
    JTextField textField;

    public NewWeightFrame(int id, LogList logList) {
        super("Weight Entry", id, logList);
        this.id = id;

        initializeItems();
        defineItemBounds();
        addItemsToFrame();

        displayFrame();
    }

    public NewWeightFrame(WeightEntry weightEntry, LogList logList) {
        super("Weight Entry", weightEntry.getEntryId(), logList);
        this.id = weightEntry.getEntryId();

        initializeItems(weightEntry);
        defineItemBounds();
        addItemsToFrame();

        this.setPreferredSize(new Dimension(entryWidth, bottomItemBase));
    }

    @Override
    protected void storeEntry() {
        try {
            logList.add(new WeightEntry(id,
                    Integer.valueOf(textField.getText())));
        } catch (OutOfRange e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid Entry",
                    "Error: Invalid Entry",
                    JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace();
            this.dispose();
        }
    }

    @Override
    protected void initializeItems() {
        try {
            initializeItems(new WeightEntry(id, 100));
        } catch (OutOfRange e) {
            System.out.println("Error initializing item");
            e.printStackTrace();
        }
    }

    @Override
    protected void initializeItems(LogEntry logEntry) {
        WeightEntry weightEntry = (WeightEntry)logEntry;

        addItem(initializeWeight(weightEntry.getWeight()));
    }

    private JPanel initializeWeight(double weight) {
        JPanel weightPanel = new JPanel();
        weightPanel.setLayout(new GridLayout(0,2));
        JLabel weightPrompt = new JLabel("Weight (lbs)");
        textField = new JTextField();
        textField.setFont(font);
        weightPanel.add(weightPrompt);
        weightPanel.add(textField);
        return weightPanel;
    }
}