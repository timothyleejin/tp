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
            + "list or l: list all contacts\n"
            + "add or a: add a contact\n"
            + "delete or d: delete contacts\n"
            + "clear: deletes all contacts\n"
            + "find or f: finds a contact using name\n"
            + "filter or fil: filters contacts by events/role/telegram/name\n"
            + "favourite or fav: add a contact to favourites\n"
            + "unfavourite or unfav: remove a contact from favourites\n"
            + "lfav: list all favourite contacts\n"
            + "edit or e: edit a contact's information\n"
            + "addskill or as: add skills to a contact\n"
            + "dskill or ds: delete skills from a contact\n"
            + "copy or c: copies a contact add command to clipboard\n"
            + "exit or ex: exit the address book\n"
            + "For more information, visit\n"
            + "https://ay2526s1-cs2103t-f13-2.github.io/tp/UserGuide.html";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
