package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Skill;

/**
 * Deletes a skill of a person identified using it's displayed index from the address book.
 */
public class DeleteSkillCommand extends Command {

    public static final String COMMAND_WORD = "dtag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a skill from the person identified "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "SKILL\n"
            + "Example: " + COMMAND_WORD + " 1 Java";

    public static final String MESSAGE_DELETE_SKILL_SUCCESS = "Deleted skill %1$s from %2$s";
    public static final String MESSAGE_SKILL_NOT_FOUND = "Person does not have the specified skill.";

    private final Index index;
    private final Skill skillToDelete;

    /**
     * Creates a {@code DeleteSkillCommand} to remove the specified {@code Skill}
     * from the person at the given {@code Index}.
     *
     * @param index Index of the target person in the filtered person list.
     * @param skillToDelete Skill to be removed from the person.
     */
    public DeleteSkillCommand(Index index, Skill skillToDelete) {
        requireNonNull(index);
        requireNonNull(skillToDelete);

        this.index = index;
        this.skillToDelete = skillToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (!personToEdit.getSkills().contains(skillToDelete)) {
            throw new CommandException(MESSAGE_SKILL_NOT_FOUND);
        }

        Set<Skill> updatedSkills = new HashSet<>(personToEdit.getSkills());
        updatedSkills.remove(skillToDelete);

        Person editedPerson = new Person(
                personToEdit.getName(),
                personToEdit.getPhone(),
                personToEdit.getEmail(),
                personToEdit.getTelegram(),
                personToEdit.getRole(),
                personToEdit.getEvent(),
                updatedSkills,
                personToEdit.isFavourite()
        );

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_DELETE_SKILL_SUCCESS, skillToDelete, editedPerson.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteSkillCommand)) {
            return false;
        }

        DeleteSkillCommand otherDeleteSkillCommand = (DeleteSkillCommand) other;
        return index.equals(otherDeleteSkillCommand.index)
                && skillToDelete.equals(otherDeleteSkillCommand.skillToDelete);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", index)
                .add("deletedSkill", skillToDelete)
                .toString();
    }
}
