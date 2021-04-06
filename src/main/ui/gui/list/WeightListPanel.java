package ui.gui.list;

import model.lists.LogList;
import model.lists.WeightList;
import ui.gui.newentry.NewWeightFrame;

import javax.swing.*;

public class WeightListPanel extends LogListPanel {
    JLabel summaryStat;

    public WeightListPanel(String listType, LogListVis logListVis, LogList logList) {
        super(listType, logListVis, logList);
    }

    @Override
    protected void addEntry() {
        new NewWeightFrame(currID,logList);
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
        ImageIcon imageIcon = new ImageIcon("./data/genaric_scale.png");
        summaryStat = new JLabel("Average Weight:  "
                + String.format("%.2f",((WeightList)logList).summary()));
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
