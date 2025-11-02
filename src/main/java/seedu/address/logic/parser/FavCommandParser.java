package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FavCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@link FavCommand} object.
 *
 * <p>This parser is responsible for interpreting the user input for the "fav" command.
 * It expects a single positive integer representing the 1-based index of the contact
 * to mark as a favourite. The parser converts it internally to a zero-based index
 * for command execution.
 *
 * <p>Example usage:
 * <pre>{@code
 * FavCommandParser parser = new FavCommandParser();
 * FavCommand command = parser.parse("1"); // marks the first contact as favourite
 * }</pre>
 */
public class FavCommandParser implements Parser<FavCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FavCommand
     * and returns an FavCommand object for execution.
     *
     * @throws ParseException if the user input does not contain a valid index
     */
    @Override
    public FavCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FavCommand.MESSAGE_USAGE));
        }

        try {
            int index = Integer.parseInt(trimmedArgs);
            if (index <= 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FavCommand.MESSAGE_USAGE));
            }
            return new FavCommand(index - 1);
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FavCommand.MESSAGE_USAGE), e);
        }
    }
}
