package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents an event in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEvent(String)}
 */
public class Event {

    public static final String MESSAGE_CONSTRAINTS = "Events should not be blank and can take up any values,";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param event A valid event string.
     */
    public Event(String event) {
        requireNonNull(event);
        this.value = event;
    }

    /**
     * Returns true if event has valid description
     */
    public static boolean isValidEvent(String description) {
        if (description.trim().isEmpty()) {
            return false;
        }
        return description.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof Event)) {
            return false;
        }
        Event otherEvent = (Event) other;
        return value.equals(otherEvent.value);
    }

    public int hashCode() {
        return value.hashCode();
    }

}
