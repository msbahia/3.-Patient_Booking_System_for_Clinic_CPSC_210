package persistence;

import model.Doctor;
import model.Patient;
import org.junit.jupiter.api.Test;

import javax.print.Doc;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest  {

    @Test
    void testWriterInvalidFile() {
        try {
            //AllDoctors allDoc = new AllDoctors();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Doctor doc = new Doctor("Rose");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAllDocs.json");
            writer.open();
            writer.write(doc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAllDocs.json");
            doc = reader.read();
            //assertEquals("My work room", wr.getName());
            //assertEquals(22, doc.numOfPat());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Doctor doc = new Doctor("Jose");
//            DoctorDoc.addDoctorToAllDocs(new Doctor("Jose"));
//            allDocs.addDoctorToAllDocs(new Doctor("Rose"));
            Patient malk = new Patient("Malkeet");
            Patient jas = new Patient("Jasmeen");
            Patient sahil = new Patient("Sahil");
            Patient nan = new Patient("Nancy");
            doc.makeDoctorBooking(malk, 10);
            doc.makeDoctorBooking(jas, 11);
            doc.makeDoctorBooking(sahil, 12);
            doc.makeDoctorBooking(nan, 13);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAllDocs.json");
            writer.open();
            writer.write(doc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralAllDocs.json");
            doc = reader.read();//all doctors list here

            assertEquals(4, doc.noOfBookedPat());

            assertTrue(doc.checkIfPatientBooked(malk));
            assertTrue(doc.checkIfPatientBooked(jas));
            assertTrue(doc.checkIfPatientBooked(sahil));
            assertTrue(doc.checkIfPatientBooked(nan));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}