package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    Patient mike;
    Event makeBooking;
    Event changeBooking;
    Event cancelBooking;
    Date date;

    @BeforeEach
    void RunBefore() {
        Doctor doctor = new Doctor("Sam");
        mike = new Patient("Mike");
        makeBooking = new Event(doctor.makeDoctorBooking(mike, 9));
        changeBooking = new Event(doctor.changeDoctorBooking(mike, 11));
        cancelBooking = new Event(doctor.removePatient(mike));
        date = Calendar.getInstance().getTime();
    }

    @Test
    void testEvent() {
        assertEquals("Mike is booked at 9 hr successfully!", makeBooking.getDescription());
        assertEquals("Mike's booking is successfully changed from 9 to 11 hr",
                changeBooking.getDescription());
        assertEquals("Mike's booking at 11 is cancelled successfully!",
                cancelBooking.getDescription());
    }

    @Test
    void testToString() {
        assertEquals(date.toString() +
                "\n" + "Mike is booked at 9 hr successfully!",  makeBooking.toString());
    }
}

