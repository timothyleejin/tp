package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteSkillCommand;
import seedu.address.model.person.Skill;

class DeleteSkillCommandParserTest {

    private DeleteSkillCommandParser parser = new DeleteSkillCommandParser();

    @Test
    void parse_validInput_returnsDeleteSkillCommand() throws Exception {
        DeleteSkillCommand command = parser.parse("1 s/Java");
        assertParseSuccess(parser, "1 s/Java",
                new DeleteSkillCommand(Index.fromOneBased(1),
                        new HashSet<>(Set.of(new Skill("Java")))));
    }

    @Test
    void parse_invalidIndex_throwsParseException() throws Exception {
        assertParseFailure(parser, "a s/Java",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSkillCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_negativeIndex_throwsParseException() throws Exception {
        assertParseFailure(parser, "-2 s/Java",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSkillCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_missingPrefix_throwsParseException() throws Exception {
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSkillCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_missingIndex_throwsParseException() throws Exception {
        assertParseFailure(parser, "s/Java",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSkillCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_missingSkill_throwsParseException() throws Exception {
        assertParseFailure(parser, "1 s/",
                String.format(Skill.MESSAGE_CONSTRAINTS));
    }

    @Test
    void parse_invalidSkill_throwsParseException() throws Exception {
        assertParseFailure(parser, "1 s/^&h",
                String.format(Skill.MESSAGE_CONSTRAINTS));
    }

    @Test
    void parse_emptyInput_throwsParseException() throws Exception {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSkillCommand.MESSAGE_USAGE));
    }
}
