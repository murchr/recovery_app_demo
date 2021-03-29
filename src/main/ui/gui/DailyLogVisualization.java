package ui.gui;

import model.DailyLog;
import ui.gui.list.ExerciseListVisualization;
import ui.gui.list.LogListPanel;
import ui.gui.list.WeightListVisualization;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class DailyLogVisualization extends JPanel {
    private DailyLog dailyLog;
    private LocalDate localDate;
    private Font font = new Font("SansSerif", Font.PLAIN, 18);
    private LogListPanel exerciseLog;
    private LogListPanel weightLog;
    private JLabel time;
    private int width = PanelSizes.DAILY_LOG.getWidth();
    private int height = PanelSizes.DAILY_LOG.getHeight();


    public DailyLogVisualization(DailyLog dailyLog) {
        this.dailyLog = dailyLog;
        this.localDate = dailyLog.getLogDate();

        time = new JLabel();
        time.setText(String.format("Day: \t %4d-%02d-%02d",
                localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth()));
        time.setFont(font);

        exerciseLog = new LogListPanel(new ExerciseListVisualization(dailyLog.getExerciseLog()));
        weightLog = new LogListPanel(new WeightListVisualization(dailyLog.getWeightLog()));

        this.setPreferredSize(new Dimension(width,height));
        this.setLayout(null);
        this.setBackground(Color.orange);
        this.add(time);
        this.add(exerciseLog);
        this.add(weightLog);
        this.setVisible(true);
    }
}
