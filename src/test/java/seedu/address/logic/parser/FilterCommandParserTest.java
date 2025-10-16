package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.FILTER_EVENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FILTER_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FILTER_NAME_AMY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.FILTER_SUBSTRING_EMAIL_ALICE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.person.Event;
import seedu.address.model.person.FilterPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonFilter;

import java.util.Collections;
import java.util.List;

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

    @Test
    public void parse_emailSubstring_returnsFilterCommand() {
        PersonFilter personFilter = new PersonFilter(Collections.emptyList(), Collections.emptyList(),
                List.of("alice"), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),
                        Collections.emptyList());
        FilterCommand expectedFilterCommand = new FilterCommand(new FilterPredicate(personFilter));
        assertParseSuccess(parser, FILTER_SUBSTRING_EMAIL_ALICE, expectedFilterCommand);
    }

    @Test
    public void parse_multipleName_returnsFilterCommand() {
        PersonFilter personFilter = new PersonFilter(List.of(new Name("Amy"), new Name("Bob")), Collections.emptyList(),
                Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),
                        Collections.emptyList());
        FilterCommand expectedFilterCommand = new FilterCommand(new FilterPredicate(personFilter));
        assertParseSuccess(parser, FILTER_NAME_AMY_BOB, expectedFilterCommand);
    }
}
