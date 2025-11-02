package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.StringUtil;

/**
 * Represents a Person's role in the address book.
 * Guarantees: immutable; is always valid
 */
public class Role {
    public static final String MESSAGE_CONSTRAINTS = "Roles can take up any values and should not be blank.";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code Role}.
     *
     * @param role A valid role string.
     */
    public Role(String role) {
        requireNonNull(role);
        String normalized = StringUtil.normalizeWhitespace(role);
        value = normalized.trim();
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidRole(String description) {
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
        return other == this // short circuit if same object
                || (other instanceof Role // instanceof handles nulls
                && value.equals(((Role) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
