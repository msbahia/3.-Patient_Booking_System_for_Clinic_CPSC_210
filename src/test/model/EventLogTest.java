package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventLogTest {

    Patient mike;
    EventLog el;
    Event makeBooking;
    Event changeBooking;
    Event cancelBooking;

    @BeforeEach
    void runBefore() {
        Doctor doctor = new Doctor("Sam");
        mike = new Patient("Mike");
        makeBooking = new Event(doctor.makeDoctorBooking(mike, 9));
        changeBooking = new Event(doctor.changeDoctorBooking(mike, 11));
        cancelBooking = new Event(doctor.removePatient(mike));
        el = EventLog.getInstance();
    }

    @Test
    void testLogEvent() {
        el.clear();
        el.logEvent(makeBooking);

        List<Event> eventList = new ArrayList<>();
        for (Event e : el) {
            eventList.add(e);
        }
        assertEquals(2, eventList.size());
        assertTrue(eventList.contains(makeBooking));
        assertFalse(eventList.contains(changeBooking));
        assertFalse(eventList.contains(cancelBooking));

        el.logEvent(changeBooking);
        el.logEvent(cancelBooking);

        List<Event> eventList1 = new ArrayList<>();

        for (Event e : el) {
            eventList1.add(e);
        }
        assertEquals(4, eventList1.size());
        assertTrue(eventList1.contains(makeBooking));
        assertTrue(eventList1.contains(changeBooking));
        assertTrue(eventList1.contains(cancelBooking));
    }

    @Test
    void testClear() {
        el.clear();
        Iterator<Event> iterator = el.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertFalse(iterator.hasNext());
    }
}
