package persistence;

import model.Doctor;
import model.Patient;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/fileDoesNotExist.json");
        try {
            Doctor doc = reader.read();
            fail("IOException expected");// if such file exists, then reader will fail.
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testEmptyReaderAllDocs.json");

        try {
            Doctor doc = reader.read();
            assertEquals(0, doc.noOfBookedPat());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralAllDocs.json");
        try {
            Doctor doc = reader.read();

            assertEquals(2, doc.noOfBookedPat());

            assertEquals("Ralf", doc.getDoctorName());

            Patient raghav = new Patient("Raghav");
            raghav.setPatientBookingTime(9);
            assertTrue(doc.checkIfPatientBooked(raghav));

            Patient shalini = new Patient("Shalini");
            shalini.setPatientBookingTime(21);
            assertTrue(doc.checkIfPatientBooked(shalini));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}