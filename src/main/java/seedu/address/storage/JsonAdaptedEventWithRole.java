package seedu.address.storage;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Event;
import seedu.address.model.person.Role;

/**
 * Jackson-friendly version of Event and Role
 */
public class JsonAdaptedEventWithRole {

    private final String event;
    private final String role;

    /**
     * Constructs a {@code JsonAdaptedEventWithRole} with the given {@code eventName} and {@code roleName}.
     */
    @JsonCreator
    public JsonAdaptedEventWithRole(@JsonProperty("event") String event,
                                    @JsonProperty("role") String role) {
        this.event = event;
        this.role = role;
    }

    /**
     * Converts a given {@code Event} and {@code Role} into this class for Jackson use.
     */
    public JsonAdaptedEventWithRole(Event event, Role role) {
        this.event = event.value;
        this.role = role.value;
    }

    public String getEventName() {
        return event;
    }

    public String getRoleName() {
        return role;
    }

    /**
     * Converts this Jackson-friendly adapted event and role object into the model's {@code EventsAndRoles} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted skill.
     */
    public HashMap<Event, Role> toModelType() throws IllegalValueException {
        if (!Event.isValidEvent(event)) {
            throw new IllegalValueException(Event.MESSAGE_CONSTRAINTS);
        }

        if (!Role.isValidRole(role)) {
            throw new IllegalValueException(Role.MESSAGE_CONSTRAINTS);
        }

        HashMap<Event, Role> map = new HashMap<>();
        map.put(new Event(event), new Role(role));

        return map;
    }
}
