package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RoleTest {
    @Test
    public void equals() {
        Role role = new Role("Hello");

        // same object -> returns true
        assertTrue(role.equals(role));

        // same values -> returns true
        Role remarkCopy = new Role(role.value);
        assertTrue(role.equals(remarkCopy));

        // different types -> returns false
        assertFalse(role.equals(1));

        // null -> returns false
        assertFalse(role.equals(null));

        // different role -> returns false
        Role differentRole = new Role("Bye");
        assertFalse(role.equals(differentRole));
    }
}
