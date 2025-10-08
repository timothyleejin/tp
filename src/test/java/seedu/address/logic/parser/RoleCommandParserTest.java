package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RoleCommand;
import seedu.address.model.person.Role;

public class RoleCommandParserTest {
    private RoleCommandParser parser = new RoleCommandParser();
    private final String nonEmptyRole = "Some role.";

    @Test
    public void parse_indexSpecified_success() {
        // have role
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_ROLE + nonEmptyRole;
        RoleCommand expectedCommand = new RoleCommand(INDEX_FIRST_PERSON, new Role(nonEmptyRole));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no role
        userInput = targetIndex.getOneBased() + " " + PREFIX_ROLE;
        expectedCommand = new RoleCommand(INDEX_FIRST_PERSON, new Role(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RoleCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, RoleCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, RoleCommand.COMMAND_WORD + " " + nonEmptyRole, expectedMessage);
    }
}
