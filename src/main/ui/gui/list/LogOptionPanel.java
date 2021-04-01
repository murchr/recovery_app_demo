package ui.gui.list;

import ui.gui.PanelSizes;

import javax.swing.*;
import java.awt.*;

public class LogOptionPanel extends JPanel {
    private int width = PanelSizes.LOG_LIST_BUTTON.getWidth();
    private int height = PanelSizes.LOG_LIST_BUTTON.getHeight();
    private int buttonWidth = width - 10;
    private int buttonHeight = width / 4;
    private Dimension buttonDimension = new Dimension(buttonWidth, buttonHeight);
    protected Font font = new Font("SansSerif", Font.PLAIN, 18);
    protected JButton addEntry;
    protected JButton removeEntry;
    protected JButton modifyEntry;

    public LogOptionPanel(JButton addEntry, JButton removeEntry, JButton modifyEntry) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.addEntry = addEntry;
        this.removeEntry = removeEntry;
        this.modifyEntry = modifyEntry;

        this.addEntry.setPreferredSize(buttonDimension);
        this.modifyEntry.setPreferredSize(buttonDimension);
        this.removeEntry.setPreferredSize(buttonDimension);

        this.addEntry.setText("Add");
        this.removeEntry.setText("Remove");
        this.modifyEntry.setText("Modify");

        this.addEntry.setFont(font);
        this.modifyEntry.setFont(font);
        this.removeEntry.setFont(font);

        addEntry.setAlignmentX(Component.CENTER_ALIGNMENT);
        modifyEntry.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeEntry.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setBackground(Color.darkGray);
        this.add(addEntry);
        this.add(removeEntry);
        this.add(modifyEntry);
        //this.setSize(new Dimension(width, height));
        this.setPreferredSize(new Dimension(width, height));
    }
}