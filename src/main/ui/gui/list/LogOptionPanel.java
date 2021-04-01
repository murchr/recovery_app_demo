package ui.gui.list;

import ui.gui.PanelSizes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

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
    protected JButton summaryStat;

    public LogOptionPanel(JButton addEntry, JButton removeEntry, JButton modifyEntry, JButton summaryStat) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ArrayList<JButton> buttons = new ArrayList<>();

        this.addEntry = addEntry;
        this.removeEntry = removeEntry;
        this.modifyEntry = modifyEntry;
        this.summaryStat = summaryStat;

        this.addEntry.setText("Add");
        this.removeEntry.setText("Remove");
        this.modifyEntry.setText("Modify");
        this.summaryStat.setText("Summary");

        buttons.add(addEntry);
        buttons.add(removeEntry);
        buttons.add(modifyEntry);
        buttons.add(summaryStat);

        initializeButtons(buttons);

        this.setBackground(Color.darkGray);
        this.add(addEntry);
        this.add(removeEntry);
        this.add(modifyEntry);
        this.add(summaryStat);
        this.setPreferredSize(new Dimension(width, height));
    }

    private void initializeButtons(Collection<JButton> buttons) {
        for (JButton button : buttons) {
            button.setPreferredSize(buttonDimension);
            button.setFont(font);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
    }
}