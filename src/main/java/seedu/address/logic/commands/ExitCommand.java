package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String COMMAND_SHORTCUT = "ex";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";
    public static final String MESSAGE_EXIT_FAILURE = "To exit, type exit without additional arguments!";

    private final boolean noExtraArguments;

    public ExitCommand(boolean noExtraArguments) {
        this.noExtraArguments = noExtraArguments;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (noExtraArguments) {
            return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        } else {
            throw new CommandException(MESSAGE_EXIT_FAILURE);
        }
    }

}
