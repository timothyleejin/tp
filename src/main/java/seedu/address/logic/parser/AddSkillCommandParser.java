package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddSkillCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Skill;

/**
 * Parses input arguments and creates a new {@code AddSkillCommand} object.
 */
public class AddSkillCommandParser implements Parser<AddSkillCommand> {

    /**
     * Parses the given  String of arguments in the context of the AddSkillCommand
     * and returns an AddSkillCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    @Override
    public AddSkillCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SKILL);

        if (!arePrefixesPresent(argMultimap, PREFIX_SKILL) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSkillCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSkillCommand.MESSAGE_USAGE), pe);
        }

        List<Skill> skillsToAdd = new ArrayList<>();
        for (String skillName : argMultimap.getAllValues(PREFIX_SKILL)) {
            try {
                skillsToAdd.add(new Skill(skillName.trim()));
            } catch (IllegalArgumentException e) {
                throw new ParseException(Skill.MESSAGE_CONSTRAINTS);
            }
        }

        return new AddSkillCommand(index, skillsToAdd);
    }

    /**
     * Returns true if all the specified prefixes are present in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
