package persistence;

import model.Doctor;
import model.Patient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }


    // EFFECTS: reads AlDoc from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Doctor read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAllDocs(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }
        return contentBuilder.toString();
    }

    //MODIFIES: this
    // EFFECTS: parse Doctor from jsonObject

    private Doctor parseAllDocs(JSONObject jsonObject) {
        String drName = jsonObject.getString("doctorName");
        Doctor roselyn = new Doctor(drName);
        addPatientsList(roselyn, jsonObject);
        return roselyn;
    }

    //MODIFIES: this
    // EFFECTS: parse Patients from jsonArray.
    private void addPatientsList(Doctor roselyn, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("patientBookings");
        for (Object json : jsonArray) {
            JSONObject nextPat = (JSONObject) json;//casting
            addPatient(roselyn, nextPat);
        }
    }

    //MODIFIES: this
    // EFFECTS: add parsed Patient back to the Doctor.
    private void addPatient(Doctor roselyn, JSONObject jsonObject) {
        String patName = jsonObject.getString("patientName");
        int bookTime = jsonObject.getInt("bookingTime");
        Patient patient = new Patient(patName);
        patient.setPatientBookingTime(bookTime);
        roselyn.makeDoctorBooking(patient, bookTime);

    }
}