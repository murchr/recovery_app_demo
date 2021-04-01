package ui.gui.list;

import model.lists.LogList;
import ui.gui.entries.NewWeightFrame;

import javax.swing.*;

public class WeightListPanel extends LogListPanel {


    public WeightListPanel(String listType, LogListVisualization logListVisualization, LogList logList) {
        super(listType, logListVisualization, logList);
    }

    @Override
    protected void addEntry() {
        new NewWeightFrame(currID,logList);
        currID++;
    }

    @Override
    protected void removeEntry() {
        JOptionPane.showMessageDialog(null,
                "This feature is under development",
                "Not Yet Implemented",
                JOptionPane.WARNING_MESSAGE);
    }

    @Override
    protected void modifyEntry() {
        JOptionPane.showMessageDialog(null,
                "This feature is under development",
                "Not Yet Implemented",
                JOptionPane.WARNING_MESSAGE);
    }
}
