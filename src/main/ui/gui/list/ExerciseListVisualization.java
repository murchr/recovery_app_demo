package ui.gui.list;

import model.entries.ExerciseEntry;
import model.entries.LogEntry;
import model.lists.ExerciseList;
import ui.gui.entries.EntryVisualization;
import ui.gui.entries.ExerciseVisualization;

import javax.swing.*;
import java.awt.*;

public class ExerciseListVisualization extends LogListVisualization {

    public ExerciseListVisualization(ExerciseList exerciseList) {
        super();
        name = new JLabel();
        name.setText("Exercise Log:");
        name.setFont(new Font("SansSerif", Font.BOLD, 22));
        name.setLocation(0,0);

        for (LogEntry exerciseEntry : exerciseList) {
            ExerciseVisualization ev = new ExerciseVisualization((ExerciseEntry)exerciseEntry);
            visualizationEntries.add(ev);
            height += ev.getHeight();
        }

        this.setBackground(Color.pink);
        rebuild();
    }

    @Override
    public void addEntry(EntryVisualization ev) {
        // stub
    }

    @Override
    public void removeEntry(EntryVisualization ev) {
        // stub
    }

    @Override
    public void modifyEntry(EntryVisualization ev) {
        // stub
    }
}
