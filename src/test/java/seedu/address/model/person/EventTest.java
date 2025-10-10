package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
   public void equals() {
        Event event = new Event("BBC");

        assertTrue(event.equals(event));

        // same values -> returns true
        Event remarkCopy = new Event(event.value);
        assertTrue(event.equals(remarkCopy));

        // different types -> returns false
        assertFalse(event.equals(1));

        // null -> returns false
        assertFalse(event.equals(null));

        // different remark -> returns false
        Event differentRemark = new Event("Boats");
        assertFalse(event.equals(differentRemark));
    }
}

