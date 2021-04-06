package ui.gui.entries;

import model.entries.LogEntry;
import ui.gui.PanelSizes;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public abstract class EntryVis extends JPanel {
    protected Font font = new Font("SansSerif", Font.PLAIN, 18);
    protected JLabel time;
    protected int heightPerEntry = 20;
    protected final int width = PanelSizes.ENTRY.getWidth();
    protected int height = heightPerEntry;
    protected int entries = 1;

    public EntryVis(LogEntry logEntry) {
        this.time = new JLabel();
        time.setText(String.format("Time: \t %02d:%02d", logEntry.getTime().getHour(), logEntry.getTime().getMinute()));
        time.setFont(font);

        this.setLayout(new GridLayout(entries, 0));
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.setPreferredSize(new Dimension(width, height));
        this.add(time);
    }

    protected void processLabels(JLabel[] labels) {
        for (JLabel label : labels) {
            label.setFont(font);
            this.add(label);
            height += heightPerEntry;
            entries++;
        }
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new GridLayout(entries, 0));
    }

    // MODIFIES: this
    // EFFECTS: adds all items to panel, title, and buttons
    public void rebuild() {
        this.removeAll();
        entries = 1;
        height = heightPerEntry;

        this.add(time);
        for (JLabel label : getEntries()) {
            this.add(label);
            height += label.getHeight();
            entries++;
        }

        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new GridLayout(entries, 0));
    }

    protected abstract JLabel[] getEntries();
}
