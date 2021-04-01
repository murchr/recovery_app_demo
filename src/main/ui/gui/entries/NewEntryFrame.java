package ui.gui.entries;

import model.entries.LogEntry;
import model.lists.LogList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class NewEntryFrame extends JFrame implements ActionListener {
    private ArrayList<JPanel> dataFields;
    private String title;
    private JButton enterButton;
    protected Font font = new Font("SansSerif", Font.PLAIN, 18);
    private int entryHeight = 40;
    protected int entryWidth = 400;
    protected int bottomItemBase = 0;
    protected int id;
    protected LogList logList;
    //private Font fontAlt = new Font("SansSerif", Font.PLAIN, 14);

    public NewEntryFrame(String title, int id, LogList logList) {
        this.logList = logList;
        this.title = String.format(title + ":" + id);
        this.getRootPane().setBorder(BorderFactory.createTitledBorder(title));
        dataFields = new ArrayList<>();

        enterButton = new JButton();
        enterButton.setText("Enter");
        enterButton.setFont(font);
        enterButton.addActionListener(this);
    }

    protected void addItem(JPanel item) {
        dataFields.add(item);
    }

    protected void defineItemBounds() {
        for (JPanel item : dataFields) {
            item.setBounds(0, bottomItemBase,
                    entryWidth, entryHeight);
            bottomItemBase += entryHeight;
        }
        enterButton.setBounds(0, bottomItemBase,
                entryWidth, entryHeight);
        bottomItemBase += entryHeight;
    }

    protected void addItemsToFrame() {
        for (JPanel item : dataFields) {
            this.add(item);
        }
        this.add(enterButton);
    }

    protected void displayFrame() {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(entryWidth, bottomItemBase));
        //this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterButton) {
            storeEntry();
            this.dispose();
        }
    }

    protected abstract void storeEntry();

    protected abstract void initializeItems();

    protected abstract void initializeItems(LogEntry logEntry);
}