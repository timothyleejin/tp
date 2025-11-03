package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SkillTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Skill(null));
    }

    @Test
    public void constructor_invalidSkillName_throwsIllegalArgumentException() {
        String invalidSkillName = "";
        assertThrows(IllegalArgumentException.class, () -> new Skill(invalidSkillName));
    }

    @Test
    public void isValidSkillName_null_throwsNullPointerException() {
        // null skill name
        assertThrows(NullPointerException.class, () -> Skill.isValidSkillName(null));
    }

    @Test
    public void isValidSkillName() {
        // invalid skill names
        assertFalse(Skill.isValidSkillName("")); // empty string
        assertFalse(Skill.isValidSkillName(" ")); // spaces only
        assertFalse(Skill.isValidSkillName("^")); // non-alphanumeric
        assertFalse(Skill.isValidSkillName("python*")); // contains non-alphanumeric character

        // valid skill names
        assertTrue(Skill.isValidSkillName("Python"));
        assertTrue(Skill.isValidSkillName("java"));
        assertTrue(Skill.isValidSkillName("C++"));
        assertTrue(Skill.isValidSkillName("AI123"));
        assertTrue(Skill.isValidSkillName("Business-Management"));
    }

    @Test
    public void equals() {
        Skill python = new Skill("Python");
        Skill pythonCopy = new Skill("Python");
        Skill java = new Skill("Java");

        // same object -> returns true
        assertTrue(python.equals(python));

        // same values -> returns true
        assertTrue(python.equals(pythonCopy));

        // different types -> returns false
        assertFalse(python.equals(1));

        // null -> returns false
        assertFalse(python.equals(null));

        // different skill -> returns false
        assertFalse(python.equals(java));
    }

    @Test
    public void hashCode_sameSkillName_sameHashCode() {
        Skill skill1 = new Skill("Python");
        Skill skill2 = new Skill("Python");
        assertEquals(skill1.hashCode(), skill2.hashCode());
    }

    @Test
    public void toString_validSkill_correctFormat() {
        Skill skill = new Skill("Python");
        assertEquals("[Python]", skill.toString());
    }

}
