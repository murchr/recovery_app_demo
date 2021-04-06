package ui.gui.newentry;

import exceptions.OutOfRange;
import model.entries.ExerciseEntry;
import model.entries.LogEntry;
import model.lists.LogList;

import javax.swing.*;
import java.awt.*;

public class NewExerciseFrame extends NewEntryFrame {
   // ExerciseEntry exerciseEntry;
    JTextField exerciseTypeField;
    JSlider intensitySlider;
    JTextField durationHours;
    JTextField durationMinutes;

    public NewExerciseFrame(int id, LogList logList) {
        super("Exercise Entry", id, logList);
        this.id = id;

        initializeItems();
        defineItemBounds();
        addItemsToFrame();

        displayFrame();
    }

    public NewExerciseFrame(ExerciseEntry exerciseEntry, LogList logList) {
        super("Exercise Entry", exerciseEntry.getEntryId(), logList);
        this.id = exerciseEntry.getEntryId();

        initializeItems(exerciseEntry);
        defineItemBounds();
        addItemsToFrame();

        this.setPreferredSize(new Dimension(entryWidth, bottomItemBase));
    }

    @Override
    protected void storeEntry() {
        try {
            logList.add(new ExerciseEntry(id,
                    exerciseTypeField.getText(),
                    intensitySlider.getValue(),
                    (Integer.valueOf(durationHours.getText()) * 60
                            + Integer.valueOf(durationMinutes.getText()))));
        } catch (OutOfRange e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid Entry",
                    "Error: Invalid Entry",
                    JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace();
            this.dispose();
        }
    }

    @Override
    protected void initializeItems() {
        try {
            initializeItems(new ExerciseEntry(id, "", 5, 5));
        } catch (OutOfRange e) {
            System.out.println("Error initializing item");
            e.printStackTrace();
        }
    }

    @Override
    protected void initializeItems(LogEntry logEntry) {
        ExerciseEntry exerciseEntry = (ExerciseEntry)logEntry;

        addItem(initializeExerciseType(exerciseEntry.getExerciseType()));
        addItem(initializeExerciseIntensity(exerciseEntry.getIntensity()));
        addItem(initializeExerciseDuration(exerciseEntry.getDuration()));
    }

    private JPanel initializeExerciseType(String exerciseType) {
        JPanel exerciseTypePanel = new JPanel();
        exerciseTypePanel.setLayout(new GridLayout(0,2));
        JLabel exercisePrompt = new JLabel("Exercise type");
        exercisePrompt.setFont(font);
        exerciseTypeField = new JTextField();
        exerciseTypeField.setFont(font);
        exerciseTypeField.setText(exerciseType);
        exerciseTypePanel.add(exercisePrompt);
        exerciseTypePanel.add(exerciseTypeField);
        return exerciseTypePanel;
    }

    private JPanel initializeExerciseIntensity(int intensity) {
        JPanel intensityPanel = new JPanel();
        intensityPanel.setLayout(new GridLayout(0,2));
        JLabel intensityPrompt = new JLabel("Intensity");
        intensityPrompt.setFont(font);
        intensitySlider = new JSlider(1,10,intensity);
        intensitySlider.setPaintTicks(true);
        intensitySlider.setMinorTickSpacing(1);
        intensitySlider.setMajorTickSpacing(2);
        intensitySlider.setPaintLabels(true);
        intensitySlider.setFont(font);
        intensityPanel.add(intensityPrompt);
        intensityPanel.add(intensitySlider);
        return intensityPanel;
    }

    private JPanel initializeExerciseDuration(int minutes) {
        JPanel durationPanel = new JPanel();
        durationPanel.setLayout(new GridLayout(0,2));
        JLabel durationPrompt = new JLabel("Duration");
        durationPrompt.setFont(font);

        JPanel durationEntry = new JPanel();
        durationEntry.setLayout(new GridLayout(0,3));
        durationHours = new JTextField();
        durationHours.setFont(font);
        durationHours.setText(Integer.toString(minutes / 60));
        durationMinutes = new JTextField();
        durationMinutes.setFont(font);
        durationMinutes.setText(Integer.toString(minutes % 60));
        JLabel colon = new JLabel("   :   ");
        durationPrompt.setFont(font);
        durationEntry.add(durationHours);
        durationEntry.add(colon);
        durationEntry.add(durationMinutes);
        durationPanel.add(durationPrompt);
        durationPanel.add(durationEntry);
        return durationPanel;
    }
}