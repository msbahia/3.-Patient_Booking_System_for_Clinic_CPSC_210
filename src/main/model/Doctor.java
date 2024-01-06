package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Doctor class has a list of patient bookings and name of the doctor
// booking times are restricts between 9:00 and 21:00
public class Doctor implements Writable {
    static final int OPENING_TIME = 9;
    static final int CLOSING_TIME = 21;
    String doctorName;
    ArrayList<Patient> patientBookingsList;

    //CONSTRUCTOR: construct Doctor (object) with "name" and ArrayList of Patient objects (length 22),
    // where index refers to patient booking time.
    public Doctor(String doctorName) {
        this.doctorName = doctorName;

        patientBookingsList = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            patientBookingsList.add(i, null);
        }
    }


    //EFFECTS: return name of the doctor
    public String getDoctorName() {
        return doctorName;
    }

    // REQUIRE: Patient and non-zero/non negative time (integer).
    //MODIFIES: This (patientBookings)
    //EFFECTS: adds patient(Object) and booking time to patientsBookings list; booking time: index of ArrayList
    // set booking_time to patient as well.
    // ONLY between OPENING TIME AND CLOSING TIME.
    // NOT allowed, if slot already booked.
    //NOT allowed to do >1 bookings.
    //return string responses for all above listed tasks.
    //and records the event to event log.

    public String makeDoctorBooking(Patient p, int bookingTime) {
        if (bookingTime > CLOSING_TIME) {
            return "We close at 21 hr, so can't book at " + bookingTime;
        } else if (bookingTime < OPENING_TIME) {
            return "We open at 9 hr, so can't book at " + bookingTime;
        } else if (!(patientBookingsList.get(bookingTime) == null)) {
            return "Sorry! " + p.getPatientName() + ", this time slot is already taken by someone else.";
        } else if (checkIfPatientBooked(p)) {
            int alreadyBookedTime = returnBookedPatientObject(p).bookingTime;
            return p.getPatientName() + " can't be booked at " + bookingTime
                    + " as the person with same name is already booked at " + alreadyBookedTime;
        } else {
            patientBookingsList.set(bookingTime, p);// don't use .add(.. , ..).
            p.setPatientBookingTime(bookingTime);
            EventLog.getInstance().logEvent(
                    new Event(p.getPatientName() + " is booked at " + bookingTime + " hr successfully!"));
            return p.getPatientName() + " is booked at " + bookingTime + " hr successfully!";

        }
    }

    //REQUIRE: Patient and new time (changing to)
    //MODIFIES: this; (patientsBooking)
    //EFFECTS: check if 'patientsBooking' at 'new time' index is null;
    // Y: set given Patient to 'new time' index; N: print slot already booked.
    // for Y: change patient at old time to null (make slot free, p = null);
    // don't forget to add 'new time' to Patient object.
    //return String response for all listed tasks.
    //and records the event to event log.
    public String changeDoctorBooking(Patient p, int newBookingTime) {
        Patient patient = patientBookingsList.get(newBookingTime);
        int oldTime = p.getPatientBookingTime();
        if (patient != null) {
            getDoctorBookingList();
            return "This time slot is already taken by someone else, please select different time.";
        } else if (!checkIfPatientBooked(p)) {
            return p.getPatientName() + ", you don't have a booking, please book it.";
        } else {
            patientBookingsList.set(newBookingTime, p);
            patientBookingsList.set(oldTime, null);
            p.setPatientBookingTime(newBookingTime);
            EventLog.getInstance().logEvent(
                    new Event(p.getPatientName() + "'s booking is successfully changed from " + oldTime
                            + " to " + newBookingTime + " hr"));
            return p.getPatientName() + "'s booking is successfully changed from " + oldTime
                    + " to " + newBookingTime + " hr";
        }
    }

    //REQUIRES: patient object => patient name
    //EFFECTS: true<if the given patient name is in list>
    public boolean checkIfPatientBooked(Patient pt) {
        String givenPatientName = pt.getPatientName();
        for (Patient patientsBooking : patientBookingsList) {
            if (!(patientsBooking == null)
                    && givenPatientName.equals(patientsBooking.getPatientName())) {
                return true;
            }
        }
        return false;
    }

    //REQUIRES: Patient object
    //EFFECTS: returns booked patient
    public Patient returnBookedPatientObject(Patient pt) {
        String givenPatientName = pt.getPatientName();
        for (Patient patientsBooking : patientBookingsList) {
            if (!(patientsBooking == null)
                    && givenPatientName.equals(patientsBooking.getPatientName())) {
                return patientsBooking;
            }
        }
        return null;
    }


    //EFFECTS: returns total number of booked patients
    public int noOfBookedPat() {
        int size = 0;
        for (Patient p : patientBookingsList) {
            if (p != null) {
                size += 1;
            }
        }
        return size;
    }

    //REQUIRES: Patient object
    //MODIFIES: this
    //EFFECTS:  remove the given patient from the list of bookings, and records the event to event log.
    public String removePatient(Patient p) {
        Patient patient = returnBookedPatientObject(p);
        patientBookingsList.set(patient.bookingTime, null);
        EventLog.getInstance().logEvent(
                new Event(patient.getPatientName() + "'s booking at "
                        + patient.getPatientBookingTime() + " is cancelled successfully!."));
        return patient.getPatientName() + "'s booking at "
                + patient.getPatientBookingTime() + " is cancelled successfully!";
    }


    // EFFECTS: returns patient booking list for doctor (opening time to closing time)
    // shows time, booked patient name, and available time slots.
    // returns list of strings in "8 hr: Available/name" format.
    public ArrayList<String> getDoctorBookingList() {
        ArrayList<String> hello = new ArrayList<>();

        for (int i = OPENING_TIME; i <= CLOSING_TIME; i++) {
            Patient c = patientBookingsList.get(i);
            if (c != null) {
                hello.add(i + " hr: " + c.getPatientName());
            } else {
                hello.add(i + " hr: " + ".............");
            }
        }
        return hello;
    }


    //EFFECTS: returns the list <String> of only booked patients.
    public ArrayList<String> returnOnlyBooked() {
        ArrayList<String> hello1 = new ArrayList<>();
        for (int i = OPENING_TIME; i <= CLOSING_TIME; i++) {
            Patient pat = patientBookingsList.get(i);
            if (pat != null) {
                hello1.add(i + " hr: " + (pat.getPatientName()));
            }
        }
        System.out.println(hello1);
        return hello1;
    }

    //EFFECTS: convert the doctor object into json object
    // first make new json object => set doctor field
    //for arrayL, convert it to json Array, and add to json object.

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("doctorName", doctorName);
        json.put("patientBookings", patBookingsToJson());
        return json;
    }

    //EFFECTS: convert ArrayList to JSON ARRAY, and records the event to event log.
    private JSONArray patBookingsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Patient p : patientBookingsList) {
            if (p != null) {
                jsonArray.put(p.toJson());
            }
        }
        EventLog.getInstance().logEvent(
                new Event("The booking list has been saved successfully!"));
        return jsonArray;
    }
}

