package ui.gui.list;

import model.entries.LogEntry;
import model.entries.WeightEntry;
import model.lists.WeightList;
import ui.gui.entries.EntryVisualization;
import ui.gui.entries.WeightVisualization;

import javax.swing.*;
import java.awt.*;

public class WeightListVisualization extends LogListVisualization {

    public WeightListVisualization(WeightList weightList) {
        super();
        name = new JLabel();
        name.setText("Weight Log:");
        name.setFont(new Font("SansSerif", Font.BOLD, 22));
        name.setLocation(0,0);

        for (LogEntry weightEntry : weightList) {
            WeightVisualization wv = new WeightVisualization((WeightEntry)weightEntry);
            visualizationEntries.add(wv);
            height += wv.getHeight();
        }

        this.setBackground(Color.cyan);
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
