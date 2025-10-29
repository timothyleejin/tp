package seedu.address.logic.commands;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddSkillCommandParser;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Skill;


public class AddSkillCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private final AddSkillCommandParser addSkillCommandParser = new AddSkillCommandParser();

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddSkillCommand(null, List.of(new Skill("Fight"))));
    }

    @Test
    public void constructor_nullSkill_throwsException() {
        assertThrows(NullPointerException.class, () -> new AddSkillCommand(Index.fromOneBased(3),
                null));
    }

    @Test
    public void constructor_inValidIndex_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> new AddSkillCommand(Index.fromOneBased(-2),
                List.of(new Skill("Swimming"), new Skill("Jogging"))));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        List<Skill> skillToAdd = List.of(new Skill("CProg"), new Skill("Python"));
        AddSkillCommand addSkillCommand = new AddSkillCommand(outOfBoundIndex, skillToAdd);

        assertCommandFailure(addSkillCommand, model, "Invalid index for person");
    }

    @Test
    public void parse_validArgs_returnsAddSkillCommand() {
        assertParseSuccess(addSkillCommandParser, "1 s/dance s/football s/gaming",
                new AddSkillCommand(Index.fromOneBased(1), List.of(new Skill("dance"),
                        new Skill("football"), new Skill("gaming"))));
        assertParseSuccess(addSkillCommandParser, "2 s/Python",
                new AddSkillCommand(Index.fromOneBased(2), List.of(new Skill("Python"))));
    }

    @Test
    public void equals_sameVal_returnsTrue() {
        List<Skill> kungFuSkill = List.of(new Skill("KungFu"));
        AddSkillCommand command1 = new AddSkillCommand(INDEX_FIRST_PERSON, kungFuSkill);
        AddSkillCommand command2 = new AddSkillCommand(INDEX_FIRST_PERSON, List.of(new Skill("KungFu")));
        assertEquals(command1, command2);
    }

    @Test
    public void execute_duplicateSkill_throwsCommandException() {
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Skill existingSkill = personToEdit.getSkills().isEmpty()
                ? new Skill("Karate")
                : personToEdit.getSkills().iterator().next();

        Set<Skill> updatedSkills = new HashSet<>(personToEdit.getSkills());
        updatedSkills.add(existingSkill);
        Person editedPerson = personToEdit.withUpdatedSkills(updatedSkills);
        model.setPerson(personToEdit, editedPerson);

        AddSkillCommand addSkillCommand = new AddSkillCommand(INDEX_FIRST_PERSON, List.of(existingSkill));

        String expectedMessage = String.format(AddSkillCommand.MESSAGE_DUPLICATE_SKILL,
                editedPerson.getName(), existingSkill);

        assertCommandFailure(addSkillCommand, model, expectedMessage);
    }
    @Test
    void execute_addMultipleSkills_success() throws CommandException {
        Skill skill1 = new Skill("JavaScript");
        Skill skill2 = new Skill("Python");
        List<Skill> skillsToAdd = List.of(skill2, skill1);

        AddSkillCommand command = new AddSkillCommand(Index.fromOneBased(3), skillsToAdd);

        CommandResult result = command.execute(model);

        String expectedMessage = String.format(AddSkillCommand.MESSAGE_ADD_SKILL_SUCCESS,
                "[JavaScript], [Python]", "Carl Kurz");
        assertEquals(expectedMessage, result.getFeedbackToUser());

        Person editedPerson = model.getFilteredPersonList().get(2);
        assertEquals(new HashSet<>(skillsToAdd), editedPerson.getSkills());
    }

}
