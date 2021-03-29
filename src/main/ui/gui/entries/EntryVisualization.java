package ui.gui.entries;

import model.entries.LogEntry;
import ui.gui.PanelSizes;

import javax.swing.*;
import java.awt.*;

public abstract class EntryVisualization extends JPanel {
    protected Font font = new Font("SansSerif", Font.PLAIN, 18);
    protected JLabel time;
    protected int heightPerEntry = 22;
    protected final int width = PanelSizes.ENTRY.getWidth();
    protected int height = heightPerEntry;

    public EntryVisualization(LogEntry logEntry) {
        this.time = new JLabel();
        time.setText(String.format("Time: \t %02d:%02d", logEntry.getTime().getHour(), logEntry.getTime().getMinute()));
        time.setFont(font);

        this.setVisible(true);
        this.setLayout(new GridLayout(0,1));
        this.setPreferredSize(new Dimension(width,height));
        this.add(time);
    }

    protected void processLabels(JLabel[] labels) {
        for (JLabel label : labels) {
            label.setFont(font);
            this.add(label);
            height += heightPerEntry;
        }
        this.setPreferredSize(new Dimension(width, height));
    }

    // MODIFIES: this
    // EFFECTS: adds all items to panel, title, and buttons
    public void rebuild() {
        this.removeAll();
        height = heightPerEntry;

        this.add(time);
        for (JLabel label : getEntries()) {
            this.add(label);
            height += label.getHeight();
        }

        this.setPreferredSize(new Dimension(width, height));
    }

    protected abstract JLabel[] getEntries();
}
