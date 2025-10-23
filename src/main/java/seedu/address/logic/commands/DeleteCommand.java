package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
            + ": Deletes the person(s) identified by the index number(s) used in the displayed person list.\n"
            + "Parameters: INDEX... (one or more positive integers, separated by spaces)\n"
            + "Example: " + COMMAND_WORD + " 1 3 or " + COMMAND_WORD + " 6 4 2";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: \n%1$s";

    private final List<Index> targetIndices;

    public DeleteCommand(List<Index> targetIndices) {
        // Remove duplicate indices
        List<Index> copyInput = new ArrayList<>(targetIndices);
        this.targetIndices = copyInput.stream()
                .map(Index::getZeroBased)
                .distinct()
                .map(Index::fromZeroBased)
                .collect(Collectors.toList());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        // Sort list of indices in descending order so deletion of earlier index
        // would not affect subsequent index number
        this.targetIndices.sort((a, b) -> b.getZeroBased() - a.getZeroBased());

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

        // Sort a copy of both lists in descending order to compare
        List<Index> thisCopy = new ArrayList<>(this.targetIndices);
        List<Index> otherCopy = new ArrayList<>(otherDeleteCommand.targetIndices);

        thisCopy.sort((a, b) -> b.getZeroBased() - a.getZeroBased());
        otherCopy.sort((a, b) -> b.getZeroBased() - a.getZeroBased());

        return thisCopy.equals(otherCopy);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndices)
                .toString();
    }
}
