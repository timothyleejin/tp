package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.FILTER_EVENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FILTER_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.person.Event;
import seedu.address.model.person.FilterPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonFilter;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        // One argument
        PersonFilter personFilter = new PersonFilter(new Name("Amy"), null, null, null, null,
                null, null);
        FilterCommand expectedFilterCommand =
                new FilterCommand(new FilterPredicate(personFilter));
        assertParseSuccess(parser, FILTER_NAME_AMY, expectedFilterCommand);

        // Multiple arguments
        PersonFilter secondPersonFilter = new PersonFilter(new Name("Amy"), null, null, null, null,
                new Event("Angbao"), null);
        FilterCommand secondExpectedFilterCommand =
                new FilterCommand(new FilterPredicate(secondPersonFilter));
        assertParseSuccess(parser, FILTER_NAME_AMY + FILTER_EVENT_AMY, secondExpectedFilterCommand);
    }
}
