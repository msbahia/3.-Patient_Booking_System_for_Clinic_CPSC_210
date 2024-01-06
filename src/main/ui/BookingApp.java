package ui;

import model.Doctor;
import model.Event;
import model.EventLog;
import model.Patient;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// BookingApp has a doctor's booking list that can take bookings
public class BookingApp {
    private static final String JSON_STORE = "./data/booking_list.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private Doctor doctorRoselyn;


    // EFFECTS: runs the booking app;
    // initialise scanner input, json writer and reader
    public BookingApp() {
        doctorRoselyn = new Doctor("Roselyn");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //EFFECTS: returns whole list of Dr. Roselyn
    public ArrayList<String> printRoselynList() {
        return doctorRoselyn.getDoctorBookingList();
    }

    //REQUIRES: name and time (string and int)
    //MODIFIES: This (Doctor doctorRoselyn)
    //EFFECTS: takes name of patient and cancels the booking,
    // cross-checks with the user if he/she really wants to cancel tha booking.
    public String cancelMyBooking(String name, int time) {
        Patient userToCancel = new Patient(name);
        if (doctorRoselyn.checkIfPatientBooked(userToCancel)) {
            return doctorRoselyn.removePatient(userToCancel);
        } else {
            return userToCancel.getPatientName() + " you don't have any bookings to cancel.";
        }
    }


    //REQUIRES: name and time (string and int)
    //MODIFIES: this (Doctor doctorRoselyn)
    //EFFECTS: book the patient with given name at the given time slot
    public String bookDoctorRoselyn(String name, int time) {
        Patient p = new Patient(name);
        return doctorRoselyn.makeDoctorBooking(p, time);
    }

    //REQUIRES: name and time (string and int)
    //MODIFIES: this (Doctor doctorRoselyn)
    //EFFECTS: take user command (name of patient), returns patient object for th given name
    // check if the patient (O) booked, if booked returns p-object from booking list.
    // if not then?
    public String changeBooking(String name, int time) {
        Patient pat = new Patient(name);
        if (doctorRoselyn.checkIfPatientBooked(pat)) {
            pat = doctorRoselyn.returnBookedPatientObject(pat);
            return doctorRoselyn.changeDoctorBooking(pat, time);
        } else {
//            pat = null;
//            return doctorRoselyn.changeDoctorBooking(pat, time);
            return "You don't have a booking, please book it.";
        }
    }



    //EFFECT: prints all bookings done in the clinic.
    public ArrayList<String> printAllBookingsOfClinic() {
        return doctorRoselyn.returnOnlyBooked();
    }


    //EFFECTS: save Doctor to a json file.
    public String saveAllDocBookingList() {
        try {
            jsonWriter.open();
            jsonWriter.write(doctorRoselyn);
            jsonWriter.close();
            return "Booking list saved successfully!";
        } catch (FileNotFoundException e) {
            return "unable to save booking list!";
        }
    }

    //EFFECTS: load Doctor from saved json file.
    public String loadAllDocBookingList() {
        try {
            EventLog.getInstance().logEvent(
                    new Event("The booking list has been loaded successfully from " + JSON_STORE));

            doctorRoselyn = jsonReader.read();

            return "Booking list loaded successfully from: " + JSON_STORE;
        } catch (IOException e) {
            return "Unable to load booking list: " + JSON_STORE;
        }
    }
}
