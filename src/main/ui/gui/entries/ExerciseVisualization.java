package ui.gui.entries;

import model.entries.ExerciseEntry;

import javax.swing.*;
import java.awt.*;

public class ExerciseVisualization extends EntryVisualization {
    private JLabel type;
    private JLabel duration;
    private JLabel intensity;

    public ExerciseVisualization(ExerciseEntry exerciseEntry) {
        super(exerciseEntry);
        type = new JLabel();
        duration = new JLabel();
        intensity = new JLabel();
        JLabel[] labels = {type, duration, intensity};
        this.setBackground(Color.red);

        type.setText(String.format("Type: \t %s", exerciseEntry.getExerciseType()));
        duration.setText(String.format("Duration: \t %d", exerciseEntry.getDuration()));
        intensity.setText(String.format("Intensity: \t %d", exerciseEntry.getIntensity()));
        processLabels(labels);
    }

    @Override
    protected JLabel[] getEntries() {
        JLabel[] labels = {type, duration, intensity};
        return labels;
    }
}
