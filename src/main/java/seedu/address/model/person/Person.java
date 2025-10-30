package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Telegram telegram;
    //HashMap to store roles with event as key and role as value
    private final HashMap<Event, Role> roles = new HashMap<Event, Role>();
    private final Set<Skill> skills = new HashSet<>();
    private boolean isFavourite;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Telegram telegram, HashMap<Event, Role> roles,
                  Set<Skill> skills, boolean isFavourite) {
        requireAllNonNull(name, phone, email, telegram);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.telegram = telegram;
        this.roles.putAll(roles);
        this.skills.addAll(skills);
        this.isFavourite = isFavourite;
    }

    /**
     * Constructor for one event and one role
     */
    public Person(Name name, Phone phone, Email email, Telegram telegram, Event event, Role role, Set<Skill> skills) {
        this(name, phone, email, telegram, new HashMap<Event, Role>(), skills, false);
        this.roles.put(event, role);
    }

    /**
     * Constructor for one event and one role with favourite
     */
    public Person(Name name, Phone phone, Email email, Telegram telegram, Event event, Role role, Set<Skill> skills,
                  boolean isFavourite) {
        this(name, phone, email, telegram, new HashMap<Event, Role>(), skills, isFavourite);
        this.roles.put(event, role);
    }

    public Person(Name name, Phone phone, Email email, Telegram telegram,
                  HashMap<Event, Role> roles, Set<Skill> skills) {
        this(name, phone, email, telegram, roles, skills, false);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Telegram getTelegram() {
        return telegram;
    }

    public HashMap<Event, Role> getEventsWithRoles() {
        return roles;
    }

    public Role getRole(Event e) {
        return roles.get(e);
    }

    public Collection<Role> getRoles() {
        return Collections.unmodifiableCollection(roles.values());
    }

    public Set<Event> getEvents() {
        return Collections.unmodifiableSet(roles.keySet());
    }

    public void addEventWithRole(Event e, Role r) {
        roles.put(e, r);
    }

    /**
     * Returns true if this person is marked as favourite.
     */
    public boolean isFavourite() {
        return isFavourite;
    }

    /**
     * Sets the favourite status of this person.
     */
    public void setFavourite(boolean favourite) {
        this.isFavourite = favourite;
    }

    /**
     * Returns an immutable skill set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Skill> getSkills() {
        return Collections.unmodifiableSet(skills);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameName(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same phone number.
     */
    public boolean isSamePhone(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getPhone().equals(getPhone());
    }

    /**
     * Returns true if both persons have the same email.
     */
    public boolean isSameEmail(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getEmail().equals(getEmail());
    }

    /**
     * Returns true if both persons have the same telegram.
     */
    public boolean isSameTelegram(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getTelegram().equals(getTelegram());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && telegram.equals(otherPerson.telegram)
                && skills.equals(otherPerson.skills)
                && roles.equals(otherPerson.roles);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, telegram, skills, roles);
    }

    public Person withUpdatedSkills(Set<Skill> newSkills) {
        return new Person(name, phone, email, telegram, roles, newSkills, isFavourite);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("telegram", telegram)
                .add("events", this.getEvents())
                .add("roles", this.getRoles())
                .add("skills", skills)
                .toString();
    }

}
