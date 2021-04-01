package ui.gui;

import model.DailyLog;
import ui.gui.list.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DailyLogVisualization extends JPanel {
    private DailyLog dailyLog;
    private LocalDate localDate;
    private Font font = new Font("SansSerif", Font.PLAIN, 18);
    private LogListPanel exerciseLog;
    private LogListPanel weightLog;
    private JLabel time;
    private ArrayList<Container> items = new ArrayList<>();

    private int width = PanelSizes.DAILY_LOG.getWidth();
    private int height = PanelSizes.DAILY_LOG.getHeight();
    private int bottomItemBase = 0;


    public DailyLogVisualization(DailyLog dailyLog) {
        this.dailyLog = dailyLog;
        this.localDate = dailyLog.getLogDate();

        initializeItems();
        items.add(time);
        items.add(exerciseLog);
        items.add(weightLog);
        defineItemsBounds();

        Dimension preferredSize = new Dimension((int) exerciseLog.getPreferredSize().getWidth(), bottomItemBase);

        this.setPreferredSize(preferredSize);
        this.setLayout(null);
        this.setBackground(Color.orange);
        addItems();
        this.setVisible(true);
    }

    private void initializeItems() {
        time = new JLabel();
        time.setText(String.format("Day: \t %4d-%02d-%02d",
                localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth()));
        time.setFont(font);

        exerciseLog = new ExerciseListPanel("Exercise Log",
                new ExerciseListVisualization(dailyLog.getExerciseLog()), dailyLog.getExerciseLog());
        weightLog = new WeightListPanel("Weight Log",
                new WeightListVisualization(dailyLog.getWeightLog()), dailyLog.getWeightLog());
    }

    private void defineItemsBounds() {
        for (Container item : items) {
            item.setBounds(0, bottomItemBase,
                    (int) item.getPreferredSize().getWidth(), (int) item.getPreferredSize().getHeight());
            bottomItemBase += item.getPreferredSize().getHeight();
        }
    }

    private void addItems() {
        for (Container item : items) {
            this.add(item);
        }
    }
}