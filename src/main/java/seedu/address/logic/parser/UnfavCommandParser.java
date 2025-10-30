package seedu.address.logic.parser;

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
            throw new ParseException("Invalid command format! Index is required.\n" +
                    "unfav: Removes a person identified by the index number used in the displayed person list from favourites.\n" +
                    "Parameters: INDEX (must be a positive integer)\n" +
                    "Example: unfav 1");
        }

        try {
            int index = Integer.parseInt(trimmedArgs);
            if (index <= 0) {
                throw new ParseException("Invalid command format! Index must be a positive integer.\n" +
                        "unfav: Removes a person identified by the index number used in the displayed person list from favourites.\n" +
                        "Parameters: INDEX (must be a positive integer)\n" +
                        "Example: unfav 1");
            }
            return new UnfavCommand(index - 1);
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid command format! Index must be an integer.\n" +
                    "unfav: Removes a person identified by the index number used in the displayed person list from favourites.\n" +
                    "Parameters: INDEX (must be a positive integer)\n" +
                    "Example: unfav 1", e);
        }
    }
}
