package ui.gui.list;

import model.lists.ExerciseList;
import model.lists.LogList;
import ui.gui.entries.NewExerciseFrame;

import javax.swing.*;

public class ExerciseListPanel extends LogListPanel {
    JLabel summaryStat;

    public ExerciseListPanel(String listType, LogListVisualization logListVisualization, LogList logList) {
        super(listType, logListVisualization, logList);
    }

    @Override
    protected void addEntry() {
        new NewExerciseFrame(currID,logList);
        currID++;
    }

    @Override
    protected void removeEntry() {
        JOptionPane.showMessageDialog(null,
                "This feature is under development",
                "Not Yet Implemented",
                JOptionPane.WARNING_MESSAGE);
    }

    @Override
    protected void modifyEntry() {
        JOptionPane.showMessageDialog(null,
                "This feature is under development",
                "Not Yet Implemented",
                JOptionPane.WARNING_MESSAGE);
    }

    @Override
    protected void launchSummary() {
        JFrame launchWindow = new JFrame();
        ImageIcon imageIcon = new ImageIcon("./data/biceps_skelle.png");
        summaryStat = new JLabel("total minutes active:  "
                + Integer.toString(((ExerciseList)logList).summary(0)));
        summaryStat.setFont(font);
        summaryStat.setIcon(imageIcon);
        summaryStat.setHorizontalAlignment(JLabel.CENTER);
        summaryStat.setVerticalAlignment(JLabel.TOP);
        summaryStat.setOpaque(true);

        launchWindow.setSize(300,300);
        launchWindow.setVisible(true);
        launchWindow.add(summaryStat);
    }
}
