package ui.gui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;

public class SwitchDateFrame extends JFrame implements ActionListener {
    private LocalDate selectedDate;
    private final RecoveryTraceApp recoveryTraceApp;
    private final Collection<LocalDate> existingDates;
    private final Collection<JLabel> dates;
    private final JPanel existingDatesPanel;
    private final JLabel prompt;
    private final JTextField textField;
    private final JButton confirm;
    private final Font font = new Font("SansSerif", Font.PLAIN, 18);

    public SwitchDateFrame(RecoveryTraceApp recoveryTraceApp) {
        this.recoveryTraceApp = recoveryTraceApp;
        existingDates = recoveryTraceApp.getRecoveryApp().getDailyLogMap().keySet();
        dates = new ArrayList<>();

        existingDatesPanel = new JPanel();
        existingDatesPanel.setLayout(new FlowLayout());

        prompt = new JLabel("<html>Please enter the date you would like<br> to access in the form YYYY-MM-DD:</html>");
        prompt.setFont(font);
        textField = new JTextField();
        textField.setText(recoveryTraceApp.getRecoveryApp().getActiveDate().toString());

        confirm = new JButton("Confirm");
        confirm.addActionListener(this);

        generateExistingDates();
        setItemBounds();
        addItemsToFrame();

        this.setLayout(null);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    // MODIFIES:    this
    // EFFECTS:     creates and stores an instance of label for each LocalDate in existingDates
    private void generateExistingDates() {
        for (LocalDate localDate : existingDates) {
            JLabel date = new JLabel(localDate.toString());
            date.setFont(font);
            date.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
            dates.add(date);
        }
    }

    // MODIFIES:    this
    // EFFECTS:     sets bounds for all items and frame
    private void setItemBounds() {
        int promptHeight = (int) prompt.getPreferredSize().getHeight();
        int promptWidth = (int) prompt.getPreferredSize().getWidth();
        int textFieldHeight = promptHeight;
        int textFieldWidth = 100;
        int buttonHeight = promptHeight;
        int buttonWidth = 100;
        int existingDatesPanelWidth = promptWidth + textFieldWidth + buttonWidth;
        int existingDatesPanelHeight = 3 * promptHeight;

        prompt.setBounds(0,0,promptWidth,promptHeight);
        textField.setBounds(promptWidth,0,textFieldWidth, textFieldHeight);
        confirm.setBounds(promptWidth + textFieldWidth, 0, buttonWidth, buttonHeight);
        existingDatesPanel.setBounds(0, promptHeight, existingDatesPanelWidth, existingDatesPanelHeight);

        this.setPreferredSize(new Dimension(existingDatesPanelWidth,existingDatesPanelHeight + promptHeight));
    }

    // MODIFIES:    this
    // EFFECTS:     adds prompt, text field, confirmation button and existing dates to frame
    private void addItemsToFrame() {
        this.add(prompt);
        this.add(textField);
        this.add(confirm);
        for (JLabel label : dates) {
            existingDatesPanel.add(label);
        }
        this.add(existingDatesPanel);
    }

    // MODIFIES:    this
    // EFFECTS:     stores input string as a LocalDate if it is valid date,
    //              --throws DateTimeParseException if it is not a valid date
    private void verifyDate() throws DateTimeParseException {
        selectedDate = LocalDate.parse(formatDate(textField.getText()));
    }

    // EFFECTS:     formats command string to YYYY-MM-DD format if separated by '-',
    //              --throws DateTimeParseException if more or less than 2 '-' exist in command
    private String formatDate(String command) throws DateTimeParseException {
        int year;
        int month;
        int day;
        int firstDash = command.indexOf('-');
        int secondDash = command.indexOf('-', firstDash + 1);
        int thirdDash = command.indexOf('-', secondDash + 1);

        if (firstDash == -1 || secondDash == -1 || thirdDash != -1) {
            throw new DateTimeParseException("Invalid dashes -", "", 205);
        }
        year = Integer.parseInt(command.substring(0, firstDash));
        month = Integer.parseInt(command.substring(firstDash + 1, secondDash));
        day = Integer.parseInt(command.substring(secondDash + 1));

        return String.format("%4d-%02d-%02d", year, month, day);
    }

    // EFFECTS:     launches warning prompt that input date is invalid and for user to enter valid date
    private void invalidDatePrompt() {
        JOptionPane.showMessageDialog(null,
                "please enter valid calender date in format YYYY-MM-DD",
                "Error: Invalid Entry",
                JOptionPane.ERROR_MESSAGE);
    }

    // MODIFIES:    this, recoveryTraceApp
    // EFFECTS:     if valid date has been entered: closes frame and changes recoveryTraceApp to new date,
    //              else it prompts user to enter valid date
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirm) {
            try {
                verifyDate();
                recoveryTraceApp.switchDate(selectedDate);
                this.dispose();
            } catch (DateTimeParseException exc) {
                invalidDatePrompt();
            }
        } else {
            System.out.println("unhandled Action");
        }
    }
}