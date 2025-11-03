package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Event;
import seedu.address.model.person.FilterPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonFilter;
import seedu.address.model.person.Role;
import seedu.address.model.person.Skill;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    public static final String PHONE_MESSAGE_CONSTRAINTS = "Phone numbers should only contain numbers when filtering";
    public static final String TELEGRAM_MESSAGE_CONSTRAINTS = "Telegram handles should only be made up of English "
            + "letters (a-z, A-Z), digits (0-9), and underscores (_). "
            + "When filtering, it should be less than 32 characters long.";

    private static final String PHONE_VALIDATION_REGEX = "\\d+";
    private static final String TELEGRAM_VALIDATION_REGEX = "^@?[a-zA-Z0-9_]{0,32}$";


    /**
     * Checks if given {@code String} to test matches the validationRegex
     */
    private static boolean isValid(String test, String validationRegex) {
        return test.matches(validationRegex);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_EVENT, PREFIX_TELEGRAM,
                        PREFIX_ROLE, PREFIX_SKILL);

        if (!anyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TELEGRAM, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ROLE,
                PREFIX_EVENT, PREFIX_SKILL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TELEGRAM, PREFIX_ROLE,
                PREFIX_EVENT, PREFIX_SKILL);

        String namesInput = argMultimap.getValue(PREFIX_NAME).orElse("");
        List<Name> names = getNames(namesInput);

        String phonesInput = argMultimap.getValue(PREFIX_PHONE).orElse("");
        List<String> phones = getPhones(phonesInput);

        List<String> emails = argMultimap.getValue(PREFIX_EMAIL)
                .map(words -> Arrays.stream(words.split("\\s+"))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .filter(Objects::nonNull)
                        .toList())
                .orElse(Collections.emptyList());

        String telegramsInput = argMultimap.getValue(PREFIX_TELEGRAM).orElse("");
        List<String> telegrams = getTelegrams(telegramsInput);

        String eventsInput = argMultimap.getValue(PREFIX_EVENT).orElse("");
        List<Event> events = getEvents(eventsInput);

        String rolesInput = argMultimap.getValue(PREFIX_ROLE).orElse("");
        List<Role> roles = getRoles(rolesInput);

        String skillsInput = argMultimap.getValue(PREFIX_SKILL).orElse("");
        List<Skill> skills = getSkills(skillsInput);

        if (names.isEmpty() && phones.isEmpty() && emails.isEmpty() && telegrams.isEmpty() && events.isEmpty()
            && roles.isEmpty() && skills.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        PersonFilter filterParams = new PersonFilter(names, phones, emails, telegrams, roles, events, skills);

        FilterPredicate filterPredicate = new FilterPredicate(filterParams);

        return new FilterCommand(filterPredicate);
    }

    /**
     * Parse name inputs for filtering
     */
    private static List<Name> getNames(String namesInput) throws ParseException {
        List<Name> names = new ArrayList<>();

        if (!namesInput.isEmpty()) {
            String[] split = namesInput.split("\\s+");
            int namesArguments = split.length;

            for (int i = 0; i < namesArguments; i++) {
                Name temp = ParserUtil.parseName(split[i].trim());
                names.add(temp);
            }
        }

        return names;
    }

    /**
     * Parses phone inputs for filtering
     */
    private static List<String> getPhones(String phonesInput) throws ParseException {
        List<String> phones = new ArrayList<>();

        if (!phonesInput.isEmpty()) {
            String[] split = phonesInput.split("\\s+");
            int phonesArguments = split.length;

            for (int i = 0; i < phonesArguments; i++) {
                String temp = split[i].trim();
                boolean isValid = isValid(temp, PHONE_VALIDATION_REGEX);
                if (!isValid) {
                    throw new ParseException(PHONE_MESSAGE_CONSTRAINTS);
                }
                phones.add(temp);
            }
        }
        return phones;
    }

    /**
     * Parses telegram inputs for filtering
     */
    private static List<String> getTelegrams(String telegramsInput) throws ParseException {
        List<String> telegrams = new ArrayList<>();

        if (!telegramsInput.isEmpty()) {
            String[] split = telegramsInput.split("\\s+");
            int telegramsArguments = split.length;

            for (int i = 0; i < telegramsArguments; i++) {
                String temp = split[i].trim();
                boolean isValid = isValid(temp, TELEGRAM_VALIDATION_REGEX);
                if (temp.startsWith("@")) {
                    temp = temp.substring(1);
                }
                if (!isValid) {
                    throw new ParseException(TELEGRAM_MESSAGE_CONSTRAINTS);
                }
                telegrams.add(temp);
            }
        }
        return telegrams;
    }

    /**
     * Parse event inputs for filtering
     */
    private static List<Event> getEvents(String eventsInput) throws ParseException {
        List<Event> events = new ArrayList<>();

        if (!eventsInput.isEmpty()) {
            String[] split = eventsInput.split("\\s+");
            int eventsArguments = split.length;

            for (int i = 0; i < eventsArguments; i++) {
                Event temp = ParserUtil.parseEvent(split[i].trim());
                events.add(temp);
            }
        }

        return events;
    }

    /**
     * Parse role inputs for filtering
     */
    private static List<Role> getRoles(String rolesInput) throws ParseException {
        List<Role> roles = new ArrayList<>();

        if (!rolesInput.isEmpty()) {
            String[] split = rolesInput.split("\\s+");
            int rolesArgument = split.length;

            for (int i = 0; i < rolesArgument; i++) {
                Role temp = ParserUtil.parseRole(split[i].trim());
                roles.add(temp);
            }
        }

        return roles;
    }

    /**
     * Parse skill inputs for filtering
     */
    private static List<Skill> getSkills(String skillsInput) throws ParseException {
        List<Skill> skills = new ArrayList<>();

        if (!skillsInput.isEmpty()) {
            String[] split = skillsInput.split("\\s+");
            int skillsArgument = split.length;

            for (int i = 0; i < skillsArgument; i++) {
                Skill temp = ParserUtil.parseSkill(split[i].trim());
                skills.add(temp);
            }
        }

        return skills;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
