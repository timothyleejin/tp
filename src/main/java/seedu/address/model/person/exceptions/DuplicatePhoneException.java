package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate emails as each person should have a unique email address.
 */
public class DuplicatePhoneException extends RuntimeException {
    public DuplicatePhoneException() {
        super("Operation would result in duplicate phone numbers");
    }
}
