package ui;

import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// runs GUI for teh clinic booking app, takes name and time to do booking, change booking
// cancel booking, and also allows to save and load the current form. Also allows to exit.
public class BookingAppUi
        extends JFrame
        implements ActionListener {

    private static final int WIDTH = 430;
    private static final int HEIGHT = 480;
    private static final BookingApp bookingApp = new BookingApp();
    private static JPanel panel;
    private static JTextField patientName;
    private static JTextField bookingTime;
    private static JButton addPatientButton;
    private static JButton cancelPatientBookingButton;
    private static JButton saveBookingButton;
    private static JButton loadBookingsButton;
    private static JButton changeBookingButton;
    private static JButton exitProgramButton;
    private static JButton loadOnlyBookedButton;
    private static JTextArea errorDisplay;

    //MODIFIES: this (JFrame, panel, buttons)
    //EFFECTS: initialise, JFrame and set its parameters.
    public static void main(String[] args) {

        JFrame frame = initialiseJFrame();
        initialiseLabelFields();
        initialiseTextFields();
        initialiseAllButtons();
        addListenerAndPanelToButton();
        frame.setVisible(true);
    }


    //MODIFIES: this
    //EFFECTS: initialise JFrame
    private static JFrame initialiseJFrame() {
        JFrame frame = new JFrame("WELCOME TO WELLNESS CLINIC");
        panel = new ImagePanel();
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ensure proper closing
        frame.add(panel);//adds panel to frame
        panel.setLayout(null);
        return frame;
    }


    //MODIFIES: this (JLabels)
    //EFFECTS: initialise all JLabels of the program.
    public static void initialiseLabelFields() {
        JLabel patientNameLabel = new JLabel("Patient Name");
        patientNameLabel.setBounds(10, 20, 100, 25);
        panel.add(patientNameLabel);

        JLabel bookingTimeLabel = new JLabel("Booking Time");
        bookingTimeLabel.setBounds(10, 50, 100, 25);
        panel.add(bookingTimeLabel);

        JLabel errorDisplayLabel = new JLabel("Remarks");
        errorDisplayLabel.setBounds(10, 310, 100, 25);
        panel.add(errorDisplayLabel);


        JLabel bookingDisplayLabel = new JLabel("Booking List");
        bookingDisplayLabel.setBounds(290, 100, 100, 25);
        panel.add(bookingDisplayLabel);
    }


    //MODIFIES: this (JTexts)
    //EFFECTS: initialise all JTexts of the program.
    public static void initialiseTextFields() {
        patientName = new JTextField();
        patientName.setBounds(150, 20, 165, 25);
        panel.add(patientName);

        bookingTime = new JTextField();
        bookingTime.setBounds(150, 50, 165, 25);
        panel.add(bookingTime);

        errorDisplay = new JTextArea();
        errorDisplay.setBounds(10, 340, 200, 70);
        errorDisplay.setLineWrap(true);
        panel.add(errorDisplay);

        JTextArea bookingDisplayText = new JTextArea();
        bookingDisplayText.setBounds(250, 130, 160, 250);
        bookingDisplayText.setOpaque(false);
        panel.add(bookingDisplayText);
    }


    //MODIFIES: this (JButton, panel)
    //EFFECTS: initialise all JButtons of program.
    public static void initialiseAllButtons() {
        addPatientButton = new JButton("Add Patient");
        addPatientButton.setBounds(10, 100, 200, 25);

        changeBookingButton = new JButton("Change Booking");
        changeBookingButton.setBounds(10, 130, 200, 25);

        cancelPatientBookingButton = new JButton("Cancel Booking");
        cancelPatientBookingButton.setBounds(10, 160, 200, 25);

        saveBookingButton = new JButton("Save Bookings");
        saveBookingButton.setBounds(10, 190, 200, 25);

        loadBookingsButton = new JButton("Load Bookings");
        loadBookingsButton.setBounds(10, 220, 200, 25);

        loadOnlyBookedButton = new JButton("Show Bookings");
        loadOnlyBookedButton.setBounds(10, 250, 200, 25);

        exitProgramButton = new JButton("Exit");
        exitProgramButton.setBounds(10, 280, 200, 25);

//        addListenerAndPanelToButton();
    }

    //MODIFIES: this (panel, button)
    //EFFECTS: Add action listener to all buttons, and then add this to panel.
    public static void addListenerAndPanelToButton() {
        addPatientButton.addActionListener(new BookingAppUi());
        panel.add(addPatientButton);

        changeBookingButton.addActionListener(new BookingAppUi());
        panel.add(changeBookingButton);

        cancelPatientBookingButton.addActionListener(new BookingAppUi());
        panel.add(cancelPatientBookingButton);

        saveBookingButton.addActionListener(new BookingAppUi());
        panel.add(saveBookingButton);

        loadBookingsButton.addActionListener(new BookingAppUi());
        panel.add(loadBookingsButton);

        loadOnlyBookedButton.addActionListener(new BookingAppUi());
        panel.add(loadOnlyBookedButton);

        exitProgramButton.addActionListener(new BookingAppUi());
        panel.add(exitProgramButton);
    }

    //MODIFIES: this (panel)
    //EFFECTS: create a new JTextArea with changing list data, and replace the other textArea with new JTextArea.
    public static void displayBookingList() {
        JTextArea bookingDisplayText1 = new JTextArea();
        bookingDisplayText1.setBounds(250, 120, 160, 250);
        panel.add(bookingDisplayText1);

        ArrayList<String> listOfPatients1 = bookingApp.printRoselynList();
        String newline = "\n";

        for (String str : listOfPatients1) {
            bookingDisplayText1.append(str + newline);
        }

        String myString = bookingDisplayText1.getText();
        bookingDisplayText1.setText(myString);

    }

    //MODIFIES: this (panel)
    //EFFECTS: Displays the booking list in the J-TEXTAREA on the right side
    public static void displayBookingList2() {
        JTextArea bookingDisplayText2 = new JTextArea();
        bookingDisplayText2.setBounds(250, 120, 160, 250);
        panel.add(bookingDisplayText2);

        ArrayList<String> listOfPatients1 = bookingApp.printAllBookingsOfClinic();
        String newline = "\n";

        for (String str : listOfPatients1) {
            bookingDisplayText2.append(str + newline);
        }

        String myString = bookingDisplayText2.getText();
        bookingDisplayText2.setText(myString);

    }


    //REQUIRES: Action event, user input through button pressed (string name and integer time)
    //MODIFIES: this
    //EFFECTS: take data from JText fields (patient name, booking time), and apply relevant methods on them
    //to perform specific tasks.
    // in remarks textarea - print info related to the performed action (like successful or not etc.)
    //finally, update the booking-list TEXTAREA after the action performed (e.g. add patient or remove patient after
    // the add patient or cancel patient method implementation).
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Add Patient":
                String patientNameText = patientName.getText();
                int bookingTimeText = Integer.parseInt(bookingTime.getText());
                errorDisplay.setText(bookingApp.bookDoctorRoselyn(patientNameText, bookingTimeText));
                bookingApp.bookDoctorRoselyn(patientNameText, bookingTimeText);
                displayBookingList();
                break;
            case "Cancel Booking":
                String patientNameText1 = patientName.getText();
                int bookingTimeText1 = Integer.parseInt(bookingTime.getText());
                errorDisplay.setText(bookingApp.cancelMyBooking(patientNameText1, bookingTimeText1));
                bookingApp.cancelMyBooking(patientNameText1, bookingTimeText1);
                displayBookingList();
                break;
            case "Change Booking":
                String patientNameText2 = patientName.getText();
                int bookingTimeText2 = Integer.parseInt(bookingTime.getText());
                errorDisplay.setText(bookingApp.changeBooking(patientNameText2, bookingTimeText2));
                bookingApp.changeBooking(patientNameText2, bookingTimeText2);
                displayBookingList();
                break;
            case "Save Bookings":
                bookingApp.saveAllDocBookingList();
                errorDisplay.setText(bookingApp.saveAllDocBookingList());
                displayBookingList();
                break;
            case "Load Bookings":
                bookingApp.loadAllDocBookingList();
                errorDisplay.setText(bookingApp.loadAllDocBookingList());
                displayBookingList();
                break;
            case "Show Bookings":
                displayBookingList2();
                errorDisplay.setText("Confirmed bookings are displayed successfully!");
                break;
            case "Exit":
                printLog(EventLog.getInstance());
                System.out.println("GUI closed successfully!");
                System.exit(0);
                break;
        }
    }


    //EFFECTS: print out individual Event from the collection of Events in EventLog.
    void printLog(EventLog el) {
        for (Event e : el) {
            System.out.println(e.toString());
        }
    }
}




