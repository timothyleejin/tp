package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Skill;


/**
 * Adds skill to a person based on the index in the displayed person list
 */
public class AddSkillCommand extends Command {

    public static final String COMMAND_WORD = "addskill";
    public static final String COMMAND_SHORTCUT = "as";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds skills to the person based on "
            + "the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_SKILL + "SKILL\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_SKILL + "Java";

    public static final String MESSAGE_ADD_SKILL_SUCCESS = "Added skill %1$s to %2$s";
    public static final String MESSAGE_DUPLICATE_SKILL = "%1$s already has the skill %2$s";

    private final Index index;
    private final List<Skill> skillToAdd;

    /**
     * Constructor for the AddSkillCommand which takes in an index and a skill
     * @param index
     * @param skills
     */

    public AddSkillCommand(Index index, List<Skill> skills) {
        requireNonNull(skills);
        assert index.getOneBased() > 0 : "Index must be a positive integer";
        this.index = index;
        this.skillToAdd = new ArrayList<>(skills);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException("Invalid index for person");
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        Set<Skill> updatedSkills = new HashSet<>(personToEdit.getSkills());
        List<Skill> duplicateSkills = new ArrayList<>();
        for (Skill skill : skillToAdd) {
            if (updatedSkills.contains(skill)) {
                duplicateSkills.add(skill);
            } else {
                updatedSkills.add(skill);
            }
        }

        if (!duplicateSkills.isEmpty()) {
            String duplicateSkillNames = duplicateSkills.stream()
                    .map(Skill::toString)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("");

            throw new CommandException(String.format(MESSAGE_DUPLICATE_SKILL,
                    personToEdit.getName(), duplicateSkillNames));
        }

        Person editedPerson = personToEdit.withUpdatedSkills(updatedSkills);

        String skillNames = skillToAdd.stream()
                .map(Skill::toString)
                .sorted()
                .reduce((a, b) -> a + ", " + b)
                .orElse("");

        model.setPerson(personToEdit, editedPerson);
        return new CommandResult(String.format(MESSAGE_ADD_SKILL_SUCCESS, skillNames, editedPerson.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddSkillCommand
                && index.equals(((AddSkillCommand) other).index)
                && skillToAdd.equals(((AddSkillCommand) other).skillToAdd));
    }

}
