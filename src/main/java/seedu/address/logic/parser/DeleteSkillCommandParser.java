package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteSkillCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Skill;

/**
 * Parses input arguments and creates a new DeleteSkillCommand object
 */
public class DeleteSkillCommandParser implements Parser<DeleteSkillCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteSkillCommand
     * and returns a DeleteSkillCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteSkillCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSkillCommand.MESSAGE_USAGE));
        }

        String[] parts = trimmedArgs.split("\\s+", 2);
        if (parts.length < 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSkillCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(parts[0]);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSkillCommand.MESSAGE_USAGE), pe);
        }
        String skillName = parts[1].trim();
        if (skillName.isEmpty()) {
            throw new ParseException("Skill cannot be empty");
        }

        Skill skillToDelete = new Skill(skillName);
        return new DeleteSkillCommand(index, skillToDelete);
    }
}
