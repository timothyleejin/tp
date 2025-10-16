package seedu.address.testutil;

import java.util.Optional;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Event;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonFilter;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.skill.Skill;

/**
 * A utility class to help with building PersonFilter objects
 */
public class PersonFilterBuilder {

    public static final String DEFAULT_NAME = "Amy";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "Jurong";
    public static final String DEFAULT_ROLE = "Volunteer";
    public static final String DEFAULT_EVENT = "Angbao";
    public static final String DEFAULT_SKILL = "Java";

    private Optional<Name> name;
    private Optional<Phone> phone;
    private Optional<Email> email;
    private Optional<Address> address;
    private Optional<Role> role;
    private Optional<Event> event;
    private Optional<Skill> skill;


    /**
     * Creates a {@code PersonFilterBuilder} with the default details.
     */
    public PersonFilterBuilder() {
        name = Optional.of(new Name(DEFAULT_NAME));
        phone = Optional.of(new Phone(DEFAULT_PHONE));
        email = Optional.of(new Email(DEFAULT_EMAIL));
        address = Optional.of(new Address(DEFAULT_ADDRESS));
        role = Optional.of(new Role(DEFAULT_ROLE));
        event = Optional.of(new Event(DEFAULT_EVENT));
        skill = Optional.of(new Skill(DEFAULT_SKILL));
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personFilterToCopy}.
     */
    public PersonFilterBuilder(PersonFilter personFilterToCopy) {
        name = personFilterToCopy.getName();
        phone = personFilterToCopy.getPhone();
        email = personFilterToCopy.getEmail();
        address = personFilterToCopy.getAddress();
        role = personFilterToCopy.getRole();
        event = personFilterToCopy.getEvent();
        skill = personFilterToCopy.getSkill();
    }

    /**
     * Sets the {@code Name} of the {@code PersonFilter} that we are building.
     */
    public PersonFilterBuilder withName(String name) {
        this.name = Optional.of(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code PersonFilter} that we are building.
     */
    public PersonFilterBuilder withRole(String role) {
        this.role = Optional.of(new Role(role));
        return this;
    }

    /**
     * Sets the {@code Skill} of the {@code PersonFilter} that we are building.
     */
    public PersonFilterBuilder withSkill(String skill) {
        this.skill = Optional.of(new Skill(skill));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code PersonFilter} that we are building.
     */
    public PersonFilterBuilder withAddress(String address) {
        this.address = Optional.of(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code PersonFilter} that we are building.
     */
    public PersonFilterBuilder withPhone(String phone) {
        this.phone = Optional.of(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code PersonFilter} that we are building.
     */
    public PersonFilterBuilder withEmail(String email) {
        this.email = Optional.of(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Event} of the {@code PersonFilter} that we are building.
     */
    public PersonFilterBuilder withEvent(String event) {
        this.event = Optional.of(new Event(event));
        return this;
    }

    public PersonFilter build() {
        return new PersonFilter(name, phone, email, address, role, event, tag);
    }
}
