package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.UnfavCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@link UnfavCommand} object.
 *
 * <p>This parser is responsible for interpreting the user input for the "unfav" command.
 * It expects a single positive integer representing the 1-based index of the contact
 * to unmark as a favourite. The parser converts it internally to a zero-based index
 * for command execution.
 *
 * <p>Example usage:
 * <pre>{@code
 * UnfavCommandParser parser = new UnfavCommandParser();
 * UnfavCommand command = parser.parse("1"); // unmarks the first contact as favourite
 * }</pre>
 */
public class UnfavCommandParser implements Parser<UnfavCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnfavCommand
     * and returns an UnfavCommand object for execution.
     *
     * @throws ParseException if the user input does not contain a valid index
     */
    public UnfavCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnfavCommand.MESSAGE_USAGE));
        }

        try {
            int index = Integer.parseInt(trimmedArgs);
            if (index <= 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnfavCommand.MESSAGE_USAGE));
            }
            return new UnfavCommand(index - 1);
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnfavCommand.MESSAGE_USAGE), e);
        }
    }
}
