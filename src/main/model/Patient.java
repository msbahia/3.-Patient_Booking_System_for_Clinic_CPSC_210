package model;

import org.json.JSONObject;
import persistence.Writable;

//Patient with name and booking time.
public class Patient implements Writable {
    String patientName;
    int bookingTime;

    //Constructor: Creates a Patient with name and booking time = 0.
    //MODIFIES: this (patient name)
    //EFFECTS: set patient name and booking time to 0.
    public Patient(String patientName) {
        this.patientName = patientName;
        bookingTime = 0;
    }

    //EFFECTS: returns patient name
    public String getPatientName() {
        return patientName;
    }

    //REQUIRES: patient name (string)
    //MODIFIES: this (patient name)
    //EFFECTS: sets the given PatientName to patientName.
    public void setPatientName(String patName) {
        this.patientName = patName;
    }

    //EFFECTS: return bookingTime.
    public int getPatientBookingTime() {
        return bookingTime;
    }

    //REQUIRES: booking time (integer)
    //MODIFIES: this (booking time)
    //EFFECTS: sets given time to booking time.
    public void setPatientBookingTime(int bookTime) {
        this.bookingTime = bookTime;
    }

    //EFFECTS: convert the patient object into json object.
    //create new json object and put the fields of patients objects in it.
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("patientName", patientName);
        jsonObject.put("bookingTime", bookingTime);
        return jsonObject;
    }
}
