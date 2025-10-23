package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Unmarks a person as favourite by index.
 */
public class UnfavCommand extends Command {

    public static final String COMMAND_WORD = "unfav";

    public static final String MESSAGE_SUCCESS = "Unmarked %1$s as favourite.";
    public static final String MESSAGE_ALREADY_UNFAV = "%1$s is not a favourite.";
    public static final String MESSAGE_INVALID_INDEX = "Invalid index.";

    private final int index;

    public UnfavCommand(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index < 0 || index >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_INDEX);
        }

        Person personToUnfav = lastShownList.get(index);
        if (!personToUnfav.isFavourite()) {
            throw new CommandException(String.format(MESSAGE_ALREADY_UNFAV, personToUnfav.getName()));
        }

        Person unfavouritedPerson = new Person(
                personToUnfav.getName(),
                personToUnfav.getPhone(),
                personToUnfav.getEmail(),
                personToUnfav.getTelegram(),
                personToUnfav.getRole(),
                personToUnfav.getEvent(),
                personToUnfav.getSkills(),
                false
        );

        model.setPerson(personToUnfav, unfavouritedPerson);

        return new CommandResult(String.format(MESSAGE_SUCCESS, personToUnfav.getName()));
    }
}
