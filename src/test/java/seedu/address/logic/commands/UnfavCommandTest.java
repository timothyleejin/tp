package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Email;
import seedu.address.model.person.Event;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.person.Telegram;

class UnfavCommandTest {

    private Model model;
    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person(new Name("Shananth"), new Phone("123"), new Email("b@b.com"),
                new Telegram("naffyShann"), new Event("EWP"), new Role("Tester"), Collections.emptySet());
        person.setFavourite(true);
        model = new ModelManager();
        model.addPerson(person);
    }

    @Test
    void execute_validIndex_unmarksFavourite() throws Exception {
        UnfavCommand command = new UnfavCommand(0);
        CommandResult result = command.execute(model);
        Person updatedPerson = model.getFilteredPersonList().get(0);
        assertEquals(updatedPerson.isFavourite(), false);
        assertEquals(result.getFeedbackToUser(), "Unmarked Shananth from favourites.");
    }

    @Test
    void execute_alreadyUnfavourited_throwsCommandException() {
        person.setFavourite(false);
        UnfavCommand command = new UnfavCommand(0);
        assertThrows(seedu.address.logic.commands.exceptions.CommandException.class, () -> command.execute(model));
    }

    @Test
    void execute_invalidIndex_throwsCommandException() {
        UnfavCommand command = new UnfavCommand(5);
        assertThrows(seedu.address.logic.commands.exceptions.CommandException.class, () -> command.execute(model));
    }
}
