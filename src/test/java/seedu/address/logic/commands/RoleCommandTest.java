package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.RoleCommand.MESSAGE_ARGUMENTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Role;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RoleCommand.
 */
public class RoleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        final Role role = new Role("Some role");
        /*
        assertCommandFailure(new RoleCommand(INDEX_FIRST_PERSON, role), model,
                String.format(MESSAGE_ARGUMENTS, INDEX_FIRST_PERSON.getOneBased(), role)); */
    }

    @Test
    public void equals() {
        final RoleCommand standardCommand = new RoleCommand(INDEX_FIRST_PERSON,
                new Role(VALID_ROLE_AMY));

        // same values -> returns true
        RoleCommand commandWithSameValues = new RoleCommand(INDEX_FIRST_PERSON,
                new Role(VALID_ROLE_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RoleCommand(INDEX_SECOND_PERSON, new Role(VALID_ROLE_AMY))));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new RoleCommand(INDEX_FIRST_PERSON, new Role(VALID_ROLE_BOB))));
    }
}
