package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Lists all favourite persons.
 */
public class ListFavCommand extends Command {

    public static final String COMMAND_WORD = "lfav";

    public static final String MESSAGE_NO_FAVOURITES = "No favourites found.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        List<Person> favourites = lastShownList.stream()
                .filter(Person::isFavourite)
                .collect(Collectors.toList());

        if (favourites.isEmpty()) {
            return new CommandResult(MESSAGE_NO_FAVOURITES);
        }

        StringBuilder sb = new StringBuilder("Favourites:\n");
        for (int i = 0; i < favourites.size(); i++) {
            sb.append(i + 1).append(". ").append(favourites.get(i).getName()).append("\n");
        }

        return new CommandResult(sb.toString());
    }
}
