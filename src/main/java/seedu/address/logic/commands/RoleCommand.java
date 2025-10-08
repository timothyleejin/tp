package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Role;

/**
 * Changes the remark of an existing person in the address book.
 */
public class RoleCommand extends Command {

    public static final String COMMAND_WORD = "role";
    public static final String MESSAGE_ADD_ROLE_SUCCESS = "Added role to Person: %1$s";
    public static final String MESSAGE_DELETE_ROLE_SUCCESS = "Removed role from Person: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the role of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing role will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_ROLE + "[REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ROLE + "Organiser";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Role: %2$s";

    private final Index index;
    private final Role role;

    /**
     * @param index of the person in the filtered person list to edit the role
     * @param role of the person to be updated to
     */
    public RoleCommand(Index index, Role role) {
        requireAllNonNull(index, role);

        this.index = index;
        this.role = role;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), role, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the role is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !role.value.isEmpty() ? MESSAGE_ADD_ROLE_SUCCESS : MESSAGE_DELETE_ROLE_SUCCESS;
        return String.format(message, Messages.format(personToEdit));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RoleCommand)) {
            return false;
        }

        // state check
        RoleCommand e = (RoleCommand) other;
        return index.equals(e.index)
                && role.equals(e.role);
    }
}
