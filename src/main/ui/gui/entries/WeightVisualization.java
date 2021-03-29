package ui.gui.entries;

import model.entries.WeightEntry;

import javax.swing.*;
import java.awt.*;

public class WeightVisualization extends EntryVisualization {
    private JLabel weight;

    public WeightVisualization(WeightEntry weightEntry) {
        super(weightEntry);
        weight = new JLabel();
        JLabel[] labels = {weight};
        this.setBackground(Color.blue);

        weight.setText(String.format("weight: \t %s", weightEntry.getWeight()));
        processLabels(labels);
    }

    @Override
    protected JLabel[] getEntries() {
        JLabel[] labels = {weight};
        return labels;
    }
}
