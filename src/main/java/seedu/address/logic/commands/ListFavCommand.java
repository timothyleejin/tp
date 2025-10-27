package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.model.Model;

/**
 * Lists all favourite persons.
 */
public class ListFavCommand extends Command {

    public static final String COMMAND_WORD = "lfav";

    public static final String MESSAGE_NO_FAVOURITES = "No favourites found.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredPersonList(person -> person.isFavourite());
        int favcount = model.getFilteredPersonList().size();

        if (favcount < 1) {
            return new CommandResult(MESSAGE_NO_FAVOURITES);
        }
        return new CommandResult(String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, favcount));
    }
}
