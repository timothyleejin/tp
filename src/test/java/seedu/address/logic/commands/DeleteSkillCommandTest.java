package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Skill;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteSkillCommand}.
 */
public class DeleteSkillCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validArgs_success() throws Exception {
        Person targetPerson = model.getFilteredPersonList().get(0);
        Skill skillToDelete = new Skill("Rust");

        DeleteSkillCommand command = new DeleteSkillCommand(Index.fromOneBased(1), skillToDelete);
        String expectedMessage = String.format(DeleteSkillCommand.MESSAGE_DELETE_SKILL_SUCCESS,
                skillToDelete, targetPerson.getName());

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person editedPerson = new PersonBuilder(targetPerson).withoutSkill(skillToDelete).build();
        expectedModel.setPerson(targetPerson, editedPerson);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_skillNotFound_throwsCommandException() {
        Person targetPerson = model.getFilteredPersonList().get(0);
        Skill skillToDelete = new Skill("NonexistentSkill");

        DeleteSkillCommand command = new DeleteSkillCommand(Index.fromOneBased(1), skillToDelete);
        assertCommandFailure(command, model, DeleteSkillCommand.MESSAGE_SKILL_NOT_FOUND);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteSkillCommand deleteSkillCommand = new DeleteSkillCommand(outOfBoundIndex, new Skill("Java"));

        assertCommandFailure(deleteSkillCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteSkillCommand command1 = new DeleteSkillCommand(
                Index.fromOneBased(1), new Skill("Java"));
        DeleteSkillCommand command2 = new DeleteSkillCommand(
                Index.fromOneBased(1), new Skill("Python"));
        DeleteSkillCommand command3 = new DeleteSkillCommand(
                Index.fromOneBased(2), new Skill("Java"));

        // same object -> returns true
        assertEquals(command1, command1);

        // same values -> returns true
        DeleteSkillCommand command1Copy = new DeleteSkillCommand(
                Index.fromOneBased(1), new Skill("Java"));
        assertEquals(command1, command1Copy);

        // different skill -> returns false
        assertFalse(command1.equals(command2));

        // different index -> returns false
        assertFalse(command1.equals(command3));

        // different type -> returns false
        assertFalse(command1.equals("some string"));

        // null -> returns false
        assertFalse(command1.equals(null));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        Skill targetSkill = new Skill("Rust");
        DeleteSkillCommand deleteSkillCommand = new DeleteSkillCommand(targetIndex, targetSkill);
        String expected = DeleteSkillCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex
                + ", deletedSkill=" + targetSkill + "}";
        assertEquals(expected, deleteSkillCommand.toString());
    }
}
