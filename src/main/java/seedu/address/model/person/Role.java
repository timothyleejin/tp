package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's role in the address book.
 * Guarantees: immutable; is always valid
 */
public class Role {
    public final String value;
    public static final String MESSAGE_CONSTRAINTS = "Role should only contain alphanumeric characters "
            + "separated by commas and spaces, and it should not be blank";

    /*
     * The role can only be made up of letters, and separated by commas and whitespaces
     */
    public static final String VALIDATION_REGEX = "^(?=.*[a-zA-Z])[a-zA-Z ,]+$";

    /**
     * Constructs a {@code Role}.
     *
     * @param role A valid role string.
     */
    public Role(String role) {
        requireNonNull(role);
        value = role;
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidRole(String test) {
        return test.matches(VALIDATION_REGEX);
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
