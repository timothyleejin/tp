package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

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
        Set<Skill> skillsToDelete = new HashSet<>(Set.of(new Skill("Rust")));

        DeleteSkillCommand command = new DeleteSkillCommand(Index.fromOneBased(1), skillsToDelete);
        String expectedMessage = "Deleted skills [Rust] from Alice Pauline";

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person editedPerson = new PersonBuilder(targetPerson).withoutSkill(skillsToDelete).build();
        expectedModel.setPerson(targetPerson, editedPerson);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteMultipleSkills_success() throws Exception {
        Person targetPerson = model.getFilteredPersonList().get(1);
        Set<Skill> skillsToDelete = new HashSet<>(Set.of(new Skill("Speaking"), new Skill("Coding")));
        DeleteSkillCommand command = new DeleteSkillCommand(Index.fromOneBased(2), skillsToDelete);
        String expectedMessage = "Deleted skills [Coding], [Speaking] from Benson Meier";

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person editedPerson = new PersonBuilder(targetPerson).withoutSkill(skillsToDelete).build();
        expectedModel.setPerson(targetPerson, editedPerson);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_skillNotFound_throwsCommandException() {
        Person targetPerson = model.getFilteredPersonList().get(0);
        Set<Skill> skillsToDelete = new HashSet<>(Set.of(new Skill("NonexistentSkill")));

        DeleteSkillCommand command = new DeleteSkillCommand(Index.fromOneBased(1), skillsToDelete);
        assertCommandFailure(command, model, String.format(DeleteSkillCommand.MESSAGE_SKILL_NOT_FOUND,
                targetPerson.getName(), "NonexistentSkill"));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Set<Skill> skillsToDelete = new HashSet<>(Set.of(new Skill("Java")));
        DeleteSkillCommand deleteSkillCommand = new DeleteSkillCommand(outOfBoundIndex, skillsToDelete);

        assertCommandFailure(deleteSkillCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Set<Skill> skillsToDelete1 = new HashSet<>(Set.of(new Skill("Java")));
        Set<Skill> skillsToDelete2 = new HashSet<>(Set.of(new Skill("Python")));

        DeleteSkillCommand command1 = new DeleteSkillCommand(
                Index.fromOneBased(1), skillsToDelete1);
        DeleteSkillCommand command2 = new DeleteSkillCommand(
                Index.fromOneBased(1), skillsToDelete2);
        DeleteSkillCommand command3 = new DeleteSkillCommand(
                Index.fromOneBased(2), skillsToDelete1);

        // same object -> returns true
        assertEquals(command1, command1);

        // same values -> returns true
        DeleteSkillCommand command1Copy = new DeleteSkillCommand(
                Index.fromOneBased(1), new HashSet<>(Set.of(new Skill("Java"))));
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
        Set<Skill> targetSkill = new HashSet<>(Set.of(new Skill("Rust")));
        DeleteSkillCommand deleteSkillCommand = new DeleteSkillCommand(targetIndex, targetSkill);
        String expected = DeleteSkillCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex
                + ", deletedSkills=" + targetSkill + "}";
        assertEquals(expected, deleteSkillCommand.toString());
    }
}
