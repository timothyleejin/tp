package seedu.address.logic.parser;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SKILL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.SKILL_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.SKILL_DESC_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddSkillCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Skill;



public class AddSkillCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSkillCommand.MESSAGE_USAGE);

    private AddSkillCommandParser parser = new AddSkillCommandParser();

    @Test
    public void parse_invalidInput_failure() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_SKILL_DESC;
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        String inp1 = "-1 sk/friend";
        assertThrows(ParseException.class, () -> parser.parse(inp1));
    }

    @Test
    public void parse_invalidSkill_throwsParseException() {
        String input1 = "-1 sk/frien$d";
        String input2 = "-1 sk/ friend";
        assertThrows(ParseException.class, () -> parser.parse(input1));
        assertThrows(ParseException.class, () -> parser.parse(input2));
    }

    @Test
    public void parse_validSingleSkill_success() throws Exception {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + SKILL_DESC_FRIEND; // e.g., "1 sk/friend"

        AddSkillCommand expectedCommand = new AddSkillCommand(targetIndex,
                List.of(new Skill("JavaScript")));

        AddSkillCommand actualCommand = parser.parse(userInput);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parse_multipleSkills_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + SKILL_DESC_FRIEND + SKILL_DESC_HUSBAND;
        AddSkillCommand expectedCommand = new AddSkillCommand(targetIndex,
                List.of(new Skill("JavaScript"), new Skill("Java")));
        assertParseSuccess(parser, userInput, expectedCommand);
    }



}
