package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Role(null));
    }

    @Test
    public void isValidRole_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Role.isValidRole(null));
    }

    @Test
    public void isValidRole_validRoles_returnsTrue() {
        assertTrue(Role.isValidRole("Admin"));
        assertTrue(Role.isValidRole("Manager, Admin"));
        assertTrue(Role.isValidRole("Team Lead"));
        assertTrue(Role.isValidRole("A"));
        assertTrue(Role.isValidRole("Vice President (Internal)"));
    }

    @Test
    public void isValidRole_invalidRoles_returnsFalse() {
        assertFalse(Role.isValidRole(""));
        assertFalse(Role.isValidRole(" "));
    }

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

    @Test
    public void toString_returnsValue() {
        Role role = new Role("Manager");
        assertEquals("Manager", role.toString());
    }
}
