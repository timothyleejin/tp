package seedu.address.model.person;

import java.util.Optional;

import seedu.address.model.tag.Tag;

public class PersonFilter {

    // Identity fields
    private final Optional<Name> name;
    private final Optional<Phone> phone;
    private final Optional<Email> email;

    // Data fields
    private final Optional<Address> address;
    private final Optional<Role> role;
    private final Optional<Tag> tag;
    private final Optional<Event> event;

    public PersonFilter(Optional<Name> name, Optional<Phone> phone, Optional<Email> email, Optional<Address> address,
                        Optional<Role> role, Optional<Event> event, Optional<Tag> tag) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.role = role;
        this.event = event;
        this.tag = tag;
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

    public Optional<Tag> getTag() {
        return tag;
    }
}
