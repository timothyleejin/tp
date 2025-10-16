package seedu.address.model.person;

import java.util.Optional;

import seedu.address.model.skill.Skill;

/**
 * A filter with the parameters to check each {@code Person} for
 */
public class PersonFilter {

    private static final PersonFilter EMPTY_FILTER = new PersonFilter(Optional.empty(), Optional.empty(),
            Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
            Optional.empty());

    // Identity fields
    private final Optional<Name> name;
    private final Optional<Phone> phone;
    private final Optional<Email> email;

    // Data fields
    private final Optional<Address> address;
    private final Optional<Role> role;
    private final Optional<Skill> skill;
    private final Optional<Event> event;

    /**
     * Creates a new PersonFilter object
     * @param name Name to be filtered in Optional, Empty Optional if not filtering by name
     * @param phone Phone to be filtered in Optional, Empty Optional if not filtering by phone
     * @param email Email to be filtered in Optional, Empty Optional if not filtering by email
     * @param address Address to be filtered in Optional, Empty Optional if not filtering by address
     * @param role Role to be filtered in Optional, Empty Optional if not filtering by role
     * @param event Event to be filtered in Optional, Empty Optional if not filtering by event
     * @param skill Skill to be filtered in Optional, Empty Optional if not filtering by skill
     */
    public PersonFilter(Optional<Name> name, Optional<Phone> phone, Optional<Email> email, Optional<Address> address,
                        Optional<Role> role, Optional<Event> event, Optional<Skill> skill) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.role = role;
        this.event = event;
        this.skill = skill;
    }

    /**
     * Alternate constructor taking in raw values instead of optionals
     */
    public PersonFilter(Name name, Phone phone, Email email, Address address, Role role, Event event, Skill skill) {
        this.name = Optional.ofNullable(name);
        this.phone = Optional.ofNullable(phone);
        this.email = Optional.ofNullable(email);
        this.address = Optional.ofNullable(address);
        this.role = Optional.ofNullable(role);
        this.event = Optional.ofNullable(event);
        this.skill = Optional.ofNullable(skill);
    }

    public Optional<Name> getName() {
        return name;
    }

    public Optional<Phone> getPhone() {
        return phone;
    }

    public Optional<Email> getEmail() {
        return email;
    }

    public Optional<Address> getAddress() {
        return address;
    }

    public Optional<Role> getRole() {
        return role;
    }

    public Optional<Event> getEvent() {
        return event;
    }

    public Optional<Skill> getSkill() {
        return skill;
    }

    public static PersonFilter getEmptyFilter() {
        return EMPTY_FILTER;
    }

    /**
     * Returns true if both PersonFilters have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonFilter)) {
            return false;
        }

        PersonFilter otherPersonFilter = (PersonFilter) other;
        return name.equals(otherPersonFilter.name)
                && phone.equals(otherPersonFilter.phone)
                && email.equals(otherPersonFilter.email)
                && address.equals(otherPersonFilter.address)
                && skill.equals(otherPersonFilter.skill)
                && role.equals(otherPersonFilter.role)
                && event.equals(otherPersonFilter.event);

    }
}
