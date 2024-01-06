package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Patients tests.
public class PatientTest {
    Patient p1;
    Patient p2;

    @BeforeEach
    void runBefore(){
        p1 = new Patient("Malkeet");
        p2 = new Patient("Jasmeen");
    }

    @Test
    void testPatientConstructor() {
        assertEquals("Malkeet", p1.getPatientName());
        assertEquals("Jasmeen", p2.getPatientName());
        assertEquals(0, p1.getPatientBookingTime());
    }

    @Test void testSetPatientBookingTime() {
        p1.setPatientBookingTime(12);
        assertEquals(12, p1.getPatientBookingTime());
    }

    @Test
    void testSetPatientName() {
        p1.setPatientName("Rafael");
        assertEquals("Rafael", p1.getPatientName());
    }

    @Test void testGetPatientName() {
        p1.setPatientName("Rafael");
        assertEquals("Rafael", p1.getPatientName());}

    @Test void testGetBookingTime() {
        p1.setPatientBookingTime(12);
        assertEquals(12, p1.getPatientBookingTime());
    }
}
