package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CopyCommand;

public class CopyCommandParserTest {

    private final CopyCommandParser parser = new CopyCommandParser();

    @Test
    void parse_validIndex_success() {
        assertParseSuccess(parser, "1", new CopyCommand(INDEX_FIRST_PERSON));
        assertParseSuccess(parser, "2", new CopyCommand(INDEX_SECOND_PERSON));
    }

    @Test
    void parse_invalidIndex_failure() {
        // letter
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE));

        // exclamation mark
        assertParseFailure(parser, "!",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE));

        // non positive index
        assertParseFailure(parser, "0",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_extraWhitespace_success() {
        assertParseSuccess(parser, "   1   ", new CopyCommand(INDEX_FIRST_PERSON));
    }
}
