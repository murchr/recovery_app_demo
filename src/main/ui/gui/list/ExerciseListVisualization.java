package ui.gui.list;

import model.entries.ExerciseEntry;
import model.entries.LogEntry;
import model.lists.ExerciseList;
import ui.gui.entries.EntryVisualization;
import ui.gui.entries.ExerciseVisualization;

import java.awt.*;

public class ExerciseListVisualization extends LogListVisualization {

    public ExerciseListVisualization(ExerciseList exerciseList) {
        super();

        for (LogEntry exerciseEntry : exerciseList) {
            ExerciseVisualization ev = new ExerciseVisualization((ExerciseEntry) exerciseEntry);
            visualizationEntries.add(ev);
            height += ev.getHeight();
            entries++;
        }

        this.setBackground(Color.pink);
        rebuild();
    }

    @Override
    public void addEntry(EntryVisualization ev) {
        // stub
        entries++;
    }

    @Override
    public void removeEntry(EntryVisualization ev) {
        // stub
        entries--;
    }

    @Override
    public void modifyEntry(EntryVisualization ev) {
        // stub
    }
}
