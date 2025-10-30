package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_SHORTCUT = "d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person(s) identified by the index number(s) used in the displayed person list. "
            + "You can repeat indices but only the single corresponding contact will be deleted.\n"
            + "Parameters: INDEX... (one or more positive integers, separated by spaces)\n"
            + "Example: " + COMMAND_WORD + " 1 3 (same as " + COMMAND_WORD + " 3 1 3) or " + COMMAND_WORD + " 6 4 2";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: \n%1$s";

    private final List<Index> targetIndices;

    /**
     * Constructs DeleteCommand using a list of distinct indices which are sorted in descending order.
     */
    public DeleteCommand(List<Index> targetIndices) {
        // Remove duplicate indices
        List<Index> copyInput = new ArrayList<>(targetIndices);
        this.targetIndices = copyInput.stream()
                .map(Index::getZeroBased)
                .distinct() // Remove duplicate indices
                .map(Index::fromZeroBased)
                .sorted((a, b) -> b.getZeroBased() - a.getZeroBased()) // Sort in descending order
                .collect(Collectors.toList());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        List<Person> deletedPersonList = new ArrayList<>();

        for (int i = 0; i < this.targetIndices.size(); i++) {
            Index currentIndex = this.targetIndices.get(i);
            if (currentIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person personToDelete = lastShownList.get(currentIndex.getZeroBased());
            model.deletePerson(personToDelete);
            deletedPersonList.add(personToDelete);
        }

        String message = deletedPersonList.stream()
                .map(Messages::format)
                .collect(Collectors.joining(", \n"));

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, message));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;

        // Both targetIndices are already distinct and sorted in descending order, so can just directly compare
        return this.targetIndices.equals(otherDeleteCommand.targetIndices);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndices)
                .toString();
    }
}
