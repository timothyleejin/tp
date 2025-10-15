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
            + "delete: delete a contact\n"
            + "clear: deletes all contacts\n"
            + "find: finds a contact using name\n"
            + "filter: filters contacts by events/role\n"
            + "edit: edit a contact's information\n"
            + "exit: exit the address book\n"
            + "For more information, visit\n"
            + "https://ay2526s1-cs2103t-f13-2.github.io/tp/UserGuide.html";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
