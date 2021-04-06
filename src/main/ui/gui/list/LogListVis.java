package ui.gui.list;

import ui.gui.PanelSizes;
import ui.gui.entries.EntryVis;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class LogListVis extends JPanel {
    protected final int width = PanelSizes.LOG_LIST.getWidth();
    protected int height = PanelSizes.LOG_LIST.getHeight();
    protected int entries = 0;
    protected ArrayList<EntryVis> visualizationEntries;

    public LogListVis() {
        this.setLayout(new FlowLayout());
        this.setVisible(true);
        this.setPreferredSize(new Dimension(width, height));
        //this.setMinimumSize(new Dimension(width, height));
        visualizationEntries = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds all items to panel, title, and buttons
    public void rebuild() {
        this.removeAll();
        height = PanelSizes.LOG_LIST.getHeight();

        for (EntryVis ev : visualizationEntries) {
            this.add(ev);
            height += ev.getPreferredSize().getHeight();
        }

        this.setPreferredSize(new Dimension(width, height));
        //this.setMinimumSize(new Dimension(width, height));

        //this.setLayout(new GridBagLayout());
        if (entries > 0) {
            this.setLayout(new GridLayout(entries, 0));
        } else {
            this.setLayout(new GridLayout(1, 0));
        }
    }

    // MODIFIES: this
    // EFFECTS: adds new item to list of entries
    public abstract void addEntry(EntryVis ev);

    // MODIFIES: this
    // EFFECTS: removes item from list of entries
    public abstract void removeEntry(EntryVis ev);

    // MODIFIES: this
    // EFFECTS: adds new item to list of entries
    public abstract void modifyEntry(EntryVis ev);
}