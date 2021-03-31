package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;

public class SwitchDateFrame extends JFrame implements ActionListener {
    private LocalDate selectedDate;
    private RecoveryTraceApp recoveryTraceApp;
    private Collection<LocalDate> existingDates;
    private Collection<JLabel> dates;
    private JLabel prompt;
    private JTextField textField;
    private JButton confirm;
    private Font font = new Font("SansSerif", Font.PLAIN, 18);

    public SwitchDateFrame(RecoveryTraceApp recoveryTraceApp) {
        this.recoveryTraceApp = recoveryTraceApp;
        this.existingDates = recoveryTraceApp.getRecoveryApp().getDailyLogMap().keySet();
        this.dates = new ArrayList<>();
        prompt = new JLabel("Please enter the date you would like to access in the form YYYY-MM-DD:");
        prompt.setFont(font);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 30));

        confirm = new JButton("Confirm");
        confirm.addActionListener(this);

        generateExistingDates();
        addItemsToFrame();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
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
            dates.add(date);
        }
    }

    // MODIFIES:    this
    // EFFECTS:     adds prompt, text field, confirmation button and existing dates to frame
    private void addItemsToFrame() {
        this.add(prompt);
        this.add(textField);
        this.add(confirm);
        for (JLabel label : dates) {
            this.add(label);
        }
    }

    public LocalDate getLocalDate() throws NullPointerException {
        if (selectedDate != null) {
            return selectedDate;
        }
        throw new NullPointerException();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirm) {
            try {
                verifyDate();
            } catch (DateTimeParseException exc) {
                invalidDateRecovery();
            }
        } else {
            System.out.println("unhandled Action");
        }
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
    private void invalidDateRecovery() {
    }
}
