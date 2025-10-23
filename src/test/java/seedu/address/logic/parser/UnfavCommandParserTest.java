package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnfavCommand;
import seedu.address.logic.parser.exceptions.ParseException;

class UnfavCommandParserTest {

    private final UnfavCommandParser parser = new UnfavCommandParser();

    @Test
    void parse_validIndex_returnsUnfavCommand() throws Exception {
        UnfavCommand command = parser.parse("1");
        assertEquals(0, command.getIndex());
    }

    @Test
    void parse_invalidIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("abc"));
        assertThrows(ParseException.class, () -> parser.parse("-1"));
        assertThrows(ParseException.class, () -> parser.parse("0"));
        assertThrows(ParseException.class, () -> parser.parse(""));
        assertThrows(ParseException.class, () -> parser.parse("   "));
    }
}
