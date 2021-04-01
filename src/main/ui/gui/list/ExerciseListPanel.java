package ui.gui.list;

import model.lists.LogList;
import ui.gui.entries.NewExerciseFrame;

import javax.swing.*;

public class ExerciseListPanel extends LogListPanel {


    public ExerciseListPanel(String listType, LogListVisualization logListVisualization, LogList logList) {
        super(listType, logListVisualization, logList);
    }

    @Override
    protected void addEntry() {
        new NewExerciseFrame(currID,logList);
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
