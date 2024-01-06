package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//Doctor tests.
class DoctorTest {
    Doctor doc1;
    Patient mike;
    Patient yasmeen;
    Patient steve;

    @BeforeEach
    void runBefore() {
        doc1 = new Doctor("Roselyn");
        mike = new Patient("Mike");
        yasmeen = new Patient("Yasmeen");
        steve = new Patient("Steve");
    }

    @Test
    void testConstructor() {
        assertEquals("Roselyn", doc1.doctorName);
        assertEquals(22, doc1.patientBookingsList.size());
    }

    @Test
    void testGetDoctorName() {
        assertEquals("Roselyn", doc1.getDoctorName());
    }

    @Test
    void testMakeDoctorBooking() {
        assertEquals("We close at 21 hr, so can't book at " + 22,
                doc1.makeDoctorBooking(mike, 22));
        assertEquals("We open at 9 hr, so can't book at " + 8,
                doc1.makeDoctorBooking(mike, 8));
        assertEquals("Mike is booked at 21 hr successfully!",
                doc1.makeDoctorBooking(mike, 21));
        assertEquals("Yasmeen is booked at 9 hr successfully!",
                doc1.makeDoctorBooking(yasmeen, 9));
    }

    @Test
        //check for >1 booking for same person
    void testMakeDuplicateDoctorBooking() {
        assertEquals("Mike is booked at 9 hr successfully!",
                doc1.makeDoctorBooking(mike, 9));
        assertEquals("Mike can't be booked at 11 as the person with same name is already booked at 9",
                doc1.makeDoctorBooking(mike, 11));
        assertEquals("Yasmeen is booked at 11 hr successfully!",
                doc1.makeDoctorBooking(yasmeen, 11));
        assertEquals("Sorry! Mike, this time slot is already taken by someone else.",
                doc1.makeDoctorBooking(mike, 11));
    }

    @Test
    void testChangeDoctorBooking() {
        assertEquals("Mike, you don't have a booking, please book it.",
                doc1.changeDoctorBooking(mike, 14));
        doc1.makeDoctorBooking(mike, 10);//mike 1t 10
        doc1.makeDoctorBooking(yasmeen, 18);//yasmeen at 18

        assertEquals("Mike's booking is successfully changed from 10 to 12 hr",
                doc1.changeDoctorBooking(mike, 12));//mike 10 -> 12
        assertEquals("This time slot is already taken by someone else, please select different time.",
                doc1.changeDoctorBooking(mike, 18));
        assertEquals("Mike's booking is successfully changed from 12 to 21 hr",
                doc1.changeDoctorBooking(mike, 21));
    }

    @Test
    void testCheckIfPatientBooked() {
        assertFalse(doc1.checkIfPatientBooked(mike));//nothing booked
        doc1.makeDoctorBooking(mike, 12);
        assertTrue(doc1.checkIfPatientBooked(mike));
        assertFalse(doc1.checkIfPatientBooked(yasmeen));
        doc1.makeDoctorBooking(yasmeen, 13);
        assertTrue(doc1.checkIfPatientBooked(yasmeen));
    }

    @Test
    void testReturnBookedPatientObject() {
        assertNull(doc1.returnBookedPatientObject(mike));
        doc1.makeDoctorBooking(mike, 12);
        doc1.makeDoctorBooking(yasmeen, 14);
        assertEquals(mike, doc1.returnBookedPatientObject(mike));
        assertEquals(yasmeen, doc1.returnBookedPatientObject(yasmeen));
    }

    @Test
    void testNoOfPatBooked() {
        assertEquals(0, doc1.noOfBookedPat());
        doc1.makeDoctorBooking(mike, 12);
        assertEquals(1, doc1.noOfBookedPat());
        doc1.makeDoctorBooking(yasmeen, 18);
        assertEquals(2, doc1.noOfBookedPat());
    }


    @Test
    void testRemovePatient() {
        doc1.makeDoctorBooking(yasmeen, 10);
        doc1.makeDoctorBooking(steve, 11);
        doc1.makeDoctorBooking(mike, 12);

        assertEquals(3, doc1.noOfBookedPat());
        doc1.removePatient(yasmeen);
        assertEquals(2, doc1.noOfBookedPat());
        doc1.removePatient(mike);
        assertEquals(1, doc1.noOfBookedPat());
        doc1.removePatient(steve);
        assertEquals(0, doc1.noOfBookedPat());
    }

    @Test
    void testShowOnlyBooked(){
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("9 hr: Yasmeen");
        doc1.makeDoctorBooking(yasmeen, 9);
        assertEquals(list1, doc1.returnOnlyBooked());

        list1.add("21 hr: Mike");
        doc1.makeDoctorBooking(mike, 21);
        assertEquals(list1, doc1.returnOnlyBooked());
    }
}

