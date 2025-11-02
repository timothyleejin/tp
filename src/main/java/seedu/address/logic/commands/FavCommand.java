package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Marks a person as favourite by index.
 */
public class FavCommand extends Command {

    public static final String COMMAND_WORD = "fav";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the person identified by the index number in the displayed person list to favourites.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "Marked %1$s as favourite.";
    public static final String MESSAGE_ALREADY_FAV = "%1$s is already a favourite.";
    public static final String MESSAGE_INVALID_INDEX = "The person index provided is invalid.";

    private final int index;

    public FavCommand(int index) {
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

        Person personToFav = lastShownList.get(index);
        if (personToFav.isFavourite()) {
            throw new CommandException(String.format(MESSAGE_ALREADY_FAV, personToFav.getName()));
        }

        Person favouritedPerson = new Person(
                personToFav.getName(),
                personToFav.getPhone(),
                personToFav.getEmail(),
                personToFav.getTelegram(),
                personToFav.getEventsWithRoles(),
                personToFav.getSkills(),
                true
        );

        model.setPerson(personToFav, favouritedPerson);

        return new CommandResult(String.format(MESSAGE_SUCCESS, personToFav.getName()));
    }
}
