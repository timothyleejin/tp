package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Telegram(null));
    }

    @Test
    public void constructor_validTelegram_success() {
        Telegram validTelegram = new Telegram("Valid_Name123");
        assertEquals("Valid_Name123", validTelegram.value);
    }

    @Test
    public void constructor_invalidTelegram_throwsIllegalArgumentException() {
        String invalidTelegram = "";
        assertThrows(IllegalArgumentException.class, () -> new Telegram(invalidTelegram));
        assertThrows(NullPointerException.class, () -> new Telegram(null));
    }

    @Test
    public void isValidTelegram_validHandles_returnsTrue() {
        assertTrue(Telegram.isValidTelegram("lazyisaac")); // all lowercase letters
        assertTrue(Telegram.isValidTelegram("tele_tubby")); // underscore
        assertTrue(Telegram.isValidTelegram("tele_tubbs123")); // understore and numbers
        assertTrue(Telegram.isValidTelegram("LAZYISAAC")); // all uppercase letters
        assertTrue(Telegram.isValidTelegram("A1_b2C3")); // mixed case, digits and underscore
        assertTrue(Telegram.isValidTelegram("A2345")); // 5 chars min
        assertTrue(Telegram.isValidTelegram("A2345678901234567890123456789012")); // 32 chars max
    }

    @Test
    public void isValidTelegram_invalidHandles_returnsFalse() {
        // empty or null telegram
        assertThrows(NullPointerException.class, () -> Telegram.isValidTelegram(null));
        assertFalse(Telegram.isValidTelegram("")); // empty string
        assertFalse(Telegram.isValidTelegram(" ")); // spaces only

        // invalid input lengths
        // too short (4 chars), less than 5 characters
        assertFalse(Telegram.isValidTelegram("lazy"));
        // too long (34 chars), more than 32 char
        assertFalse(Telegram.isValidTelegram("lazyisaac1234567891011121314151617"));

        // invalid characters
        assertFalse(Telegram.isValidTelegram("lazy isaac")); // contains space
        assertFalse(Telegram.isValidTelegram("lazyisaac!")); // contains special character "!"
        assertFalse(Telegram.isValidTelegram("lazy.isaac")); // contains special character "."
        assertFalse(Telegram.isValidTelegram("lazy-isaac")); // contains special character "-"
    }

    @Test
    public void equals() {
        Telegram telegram = new Telegram("Valid_tele");

        // same values -> returns true
        assertTrue(telegram.equals(new Telegram("Valid_tele")));

        // same object -> returns true
        assertTrue(telegram.equals(telegram));

        // null -> returns false
        assertFalse(telegram.equals(null));

        // different types -> returns false
        assertFalse(telegram.equals(5.0f));

        // different values -> returns false
        assertFalse(telegram.equals(new Telegram("Other_tele")));
    }
}

