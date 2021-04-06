package ui.gui.entries;

import model.entries.WeightEntry;

import javax.swing.*;
import java.awt.*;

public class WeightVis extends EntryVis {
    private JLabel weight;

    public WeightVis(WeightEntry weightEntry) {
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
