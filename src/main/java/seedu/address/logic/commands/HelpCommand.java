package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_SHORTCUT = "h";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Commands that you can use:\n"
            + "list: list all contacts\n"
            + "add: add a contact\n"
            + "delete: delete contacts\n"
            + "clear: deletes all contacts\n"
            + "find: finds a contact using name\n"
            + "filter: filters contacts by events/role/telegram/name\n"
            + "fav: add a contact to favourites\n"
            + "unfav: remove a contact from favourites\n"
            + "lfav: list all favourite contacts\n"
            + "edit: edit a contact's information\n"
            + "addskill: add skill to a contact\n"
            + "dtag: deletes skill from a contact\n"
            + "copy: copies a contact add command to clipboard\n"
            + "exit: exit the address book\n"
            + "For more information, visit\n"
            + "https://ay2526s1-cs2103t-f13-2.github.io/tp/UserGuide.html";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
