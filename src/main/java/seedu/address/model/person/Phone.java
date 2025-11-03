package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.util.StringUtil;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {


    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers can include digits, pluses, dashes, commas, brackets, "
                    + "may start with 0, and must contain between 3 and 18 digits.";
    public static final String VALIDATION_REGEX = "^(?:0)?[\\d\\s,+().-]*\\d[\\d\\s,+().-]*$";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        String normalized = StringUtil.normalizeWhitespace(phone);
        checkArgument(isValidPhone(normalized), MESSAGE_CONSTRAINTS);
        value = normalized;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        String digits = test.replaceAll("\\D", "");
        return digits.length() >= 3 && digits.length() <= 18;
    }

    private static String normalize(String phone) {
        return phone.replaceAll("[+\\-,()\\s]", "");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Phone)) {
            return false;
        }

        Phone otherPhone = (Phone) other;
        return normalize(value).equals(normalize(otherPhone.value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
