package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Event;
import seedu.address.model.person.FilterPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonFilter;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.skill.Skill;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_EVENT, PREFIX_ADDRESS,
                        PREFIX_ROLE, PREFIX_SKILL);

        if (!anyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ROLE,
                PREFIX_EVENT, PREFIX_SKILL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }


        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_ROLE,
                PREFIX_EVENT, PREFIX_SKILL);


        Optional<Name> name = argMultimap.getValue(PREFIX_NAME).flatMap(
                nameInput -> {
                    try {
                        return Optional.of(ParserUtil.parseName(nameInput));
                    } catch (ParseException e) {
                        return Optional.empty();
                    }
                });

        Optional<Phone> phone = argMultimap.getValue(PREFIX_PHONE).flatMap(
                phoneInput -> {
                    try {
                        return Optional.of(ParserUtil.parsePhone(phoneInput));
                    } catch (ParseException e) {
                        return Optional.empty();
                    }
                });

        Optional<Email> email = argMultimap.getValue(PREFIX_EMAIL).flatMap(
                emailInput -> {
                    try {
                        return Optional.of(ParserUtil.parseEmail(emailInput));
                    } catch (ParseException e) {
                        return Optional.empty();
                    }
                });

        Optional<Address> address = argMultimap.getValue(PREFIX_ADDRESS).flatMap(
                addressInput -> {
                    try {
                        return Optional.of(ParserUtil.parseAddress(addressInput));
                    } catch (ParseException e) {
                        return Optional.empty();
                    }
                });

        Optional<Role> role = argMultimap.getValue(PREFIX_ROLE).flatMap(
                roleInput -> {
                    try {
                        return Optional.of(ParserUtil.parseRole(roleInput));
                    } catch (ParseException e) {
                        return Optional.empty();
                    }
                });

        Optional<Event> event = argMultimap.getValue(PREFIX_EVENT).flatMap(
                eventInput -> {
                    try {
                        return Optional.of(ParserUtil.parseEvent(eventInput));
                    } catch (ParseException e) {
                        return Optional.empty();
                    }
                });

        Optional<Skill> skill = argMultimap.getValue(PREFIX_SKILL).flatMap(
                skillInput -> {
                    try {
                        return Optional.of(ParserUtil.parseSkill(skillInput));
                    } catch (ParseException e) {
                        return Optional.empty();
                    }
                });

        PersonFilter filterParams = new PersonFilter(name, phone, email, address, role, event, skill);

        FilterPredicate filterPredicate = new FilterPredicate(filterParams);

        return new FilterCommand(filterPredicate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
