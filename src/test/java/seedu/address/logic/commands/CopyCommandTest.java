package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CopyCommand.MESSAGE_CLIPBOARD_FAIL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.awt.GraphicsEnvironment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for CopyCommand.
 */
public class CopyCommandTest {

    private Model model;

    @BeforeEach
    void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    void execute_validIndex_success() throws CommandException {
        Person personToCopy = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        CopyCommand command = new CopyCommand(INDEX_FIRST_PERSON);

        StringBuilder addCommand = new StringBuilder();
        addCommand.append("add ");
        addCommand.append("n/").append(personToCopy.getName().fullName).append(" ");
        addCommand.append("p/").append(personToCopy.getPhone().value).append(" ");
        addCommand.append("e/").append(personToCopy.getEmail().value).append(" ");
        addCommand.append("t/").append(personToCopy.getTelegram().value).append(" ");
        personToCopy.getEvents().forEach(event -> addCommand.append("ev/").append(event.value).append(" "));
        personToCopy.getRoles().forEach(role -> addCommand.append("r/").append(role.value).append(" "));
        personToCopy.getSkills().forEach(skill -> addCommand.append("s/").append(skill.skillName).append(" "));
        String addCommandOutput = addCommand.toString().trim();

        CommandResult result = command.execute(model);

        if (GraphicsEnvironment.isHeadless()) {
            assertEquals(String.format(MESSAGE_CLIPBOARD_FAIL, personToCopy.getName(), addCommandOutput),
                    result.getFeedbackToUser());
        } else {
            assertEquals(String.format(CopyCommand.MESSAGE_SUCCESS, personToCopy.getName(), addCommandOutput),
                    result.getFeedbackToUser());
        }
    }

    @Test
    void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        CopyCommand command = new CopyCommand(outOfBoundIndex);
        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    void equals() {
        CopyCommand commandA = new CopyCommand(INDEX_FIRST_PERSON);
        CopyCommand commandB = new CopyCommand(INDEX_FIRST_PERSON);

        // same object
        assertEquals(commandA, commandA);

        // same values
        assertEquals(commandA, commandB);

        // different type, not instance of CopyCommand
        assert !commandA.equals("string");

        // null
        assert !commandA.equals(null);
    }
}
