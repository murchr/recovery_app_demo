package ui.gui.list;

import model.entries.ExerciseEntry;
import model.entries.LogEntry;
import model.lists.ExerciseList;
import ui.gui.entries.EntryVis;
import ui.gui.entries.ExerciseVis;

import java.awt.*;

public class ExerciseListVis extends LogListVis {

    public ExerciseListVis(ExerciseList exerciseList) {
        super();

        for (LogEntry exerciseEntry : exerciseList) {
            ExerciseVis ev = new ExerciseVis((ExerciseEntry) exerciseEntry);
            visualizationEntries.add(ev);
            height += ev.getHeight();
            entries++;
        }

        this.setBackground(Color.pink);
        rebuild();
    }

    @Override
    public void addEntry(EntryVis ev) {
        // stub
        entries++;
    }

    @Override
    public void removeEntry(EntryVis ev) {
        // stub
        entries--;
    }

    @Override
    public void modifyEntry(EntryVis ev) {
        // stub
    }
}
