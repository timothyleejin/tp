package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteSkillCommand;
import seedu.address.model.person.Skill;

class DeleteSkillCommandParserTest {

    private DeleteSkillCommandParser parser = new DeleteSkillCommandParser();

    @Test
    void parse_validInput_returnsDeleteSkillCommand() throws Exception {
        DeleteSkillCommand command = parser.parse("1 Java");
        assertParseSuccess(parser, "1 Java",
                new DeleteSkillCommand(Index.fromOneBased(1), new Skill("Java")));
    }

    @Test
    void parse_invalidIndex_throwsParseException() throws Exception {
        assertParseFailure(parser, "a Java",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSkillCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_negativeIndex_throwsParseException() throws Exception {
        assertParseFailure(parser, "-2 Java",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSkillCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_oneArgument_throwsParseException() throws Exception {
        assertParseFailure(parser, "Java",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSkillCommand.MESSAGE_USAGE));
    }
}
