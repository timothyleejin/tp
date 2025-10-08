package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.RoleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Role;


/**
 * Parses input arguments and creates a new {@code RoleCommand} object
 */
public class RoleCommandParser implements Parser<RoleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RoleCommand}
     * and returns a {@code RoleCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RoleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_ROLE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RoleCommand.MESSAGE_USAGE), ive);
        }

        Role remark = new Role(argMultimap.getValue(PREFIX_ROLE).orElse(""));

        return new RoleCommand(index, remark);
    }

}