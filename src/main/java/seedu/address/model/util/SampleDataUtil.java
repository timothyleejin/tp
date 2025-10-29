package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Email;
import seedu.address.model.person.Event;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.person.Skill;
import seedu.address.model.person.Telegram;



/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */

public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Telegram("yoitsalex"), new Event("Orientation Camp"), new Role("Organiser"),
                     getSkillSet("JavaScript")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Telegram("FishyBernice"), new Role("Finance Secretary"),
                    new Event("Charity Gala"), getSkillSet("JavaScript", "Piano")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Telegram("CharlotteO"), new Role("Chairman"),
                    new Event("Charity Gala"), getSkillSet("JavaScript")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Telegram("livelyDavid"), new Event("Night Cycling"), new Role("Organiser"),
                     getSkillSet("Drumming")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Telegram("RealIrfanI"), new Role("Programmes Head"),
                    new Event("NUS Marathon"), getSkillSet("JavaScript")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Telegram("Balakrishnan_Roy"), new Role("Project Director"), new Event("Exam Welfare Pack"),
                    getSkillSet("JavaScript"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a skill set containing the list of strings given.
     */
    public static Set<Skill> getSkillSet(String... strings) {
        return Arrays.stream(strings)
                .map(Skill::new)
                .collect(Collectors.toSet());
    }

}
