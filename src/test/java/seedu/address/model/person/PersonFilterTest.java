package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonFilterBuilder;

public class PersonFilterTest {

    @Test
    public void equals() {
        // same values -> returns true
        PersonFilter amy = new PersonFilterBuilder().build();
        PersonFilter amyCopy = new PersonFilterBuilder().build();
        assertTrue(amy.equals(amyCopy));

        // same object -> returns true
        assertTrue(amy.equals(amy));

        // null -> returns false
        assertFalse(amy.equals(null));

        // different type -> returns false
        assertFalse(amy.equals(5));

        // different name -> returns false
        PersonFilter dimothy = new PersonFilterBuilder().withName("Dimothy").build();
        assertFalse(amy.equals(dimothy));

        // different phone -> returns false
        PersonFilter editedAmy = new PersonFilterBuilder().withPhone(VALID_PHONE_BOB).build();
        assertFalse(amy.equals(editedAmy));

        // different email -> returns false
        editedAmy = new PersonFilterBuilder().withEmail(VALID_EMAIL_BOB).build();
        assertFalse(amy.equals(editedAmy));

        // different event -> returns false
        editedAmy = new PersonFilterBuilder().withEvent("Tennis").build();
        assertFalse(amy.equals(editedAmy));

        // different role -> returns false
        editedAmy = new PersonFilterBuilder().withRole("Coach").build();
        assertFalse(amy.equals(editedAmy));

        // different address -> returns false
        editedAmy = new PersonFilterBuilder().withAddress("Bishan").build();
        assertFalse(amy.equals(editedAmy));

        // different skills -> returns false
        editedAmy = new PersonFilterBuilder().withSkill("hunting").build();
        assertFalse(amy.equals(editedAmy));
    }
}
