package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(null));
    }

    @Test
    public void isValidEvent_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Event.isValidEvent(null));
    }

    @Test
    public void isValidEvent_invalidEvents_returnsFalse() {
        assertFalse(Event.isValidEvent(""));
        assertFalse(Event.isValidEvent(" "));
        assertFalse(Event.isValidEvent("\tabc"));
    }

    @Test
    public void isValidEvent_validEvents_returnsTrue() {
        assertTrue(Event.isValidEvent("Meeting"));
        assertTrue(Event.isValidEvent("123"));
        assertTrue(Event.isValidEvent("Company Dinner"));
        assertTrue(Event.isValidEvent("Zoom@8pm"));
        assertTrue(Event.isValidEvent("AI Workshop 2025"));
    }

    @Test
    public void equals() {
        Event event = new Event("BBC");
        Event sameEvent = new Event("BBC");
        Event differentEvent = new Event("Boats");

        // same object
        assertTrue(event.equals(event));

        // same value
        assertTrue(event.equals(sameEvent));

        // different type
        assertFalse(event.equals(1));

        // null
        assertFalse(event.equals(null));

        // different event
        assertFalse(event.equals(differentEvent));
    }

    @Test
    public void toString_returnsValue() {
        Event event = new Event("BBC");
        assertEquals("BBC", event.toString());
    }

}

