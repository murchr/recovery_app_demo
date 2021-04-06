package ui.gui.list;

import model.entries.LogEntry;
import model.entries.WeightEntry;
import model.lists.WeightList;
import ui.gui.entries.EntryVis;
import ui.gui.entries.WeightVis;

import java.awt.*;

public class WeightListVis extends LogListVis {

    public WeightListVis(WeightList weightList) {
        super();

        for (LogEntry weightEntry : weightList) {
            WeightVis wv = new WeightVis((WeightEntry) weightEntry);
            visualizationEntries.add(wv);
            height += wv.getHeight();
            entries++;
        }

        this.setBackground(Color.cyan);
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