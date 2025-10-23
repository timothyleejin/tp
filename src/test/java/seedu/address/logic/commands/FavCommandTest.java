package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

class FavCommandTest {

    private Model model;
    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person(new Name("Isaac"), new Phone("123"), new Email("a@b.com"),
                new Telegram("@dingdong"), new Role("Dev"), new Event("Event1"), Collections.emptySet());
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        model = new ModelManager();
        model.addPerson(person);
    }

    @Test
    void execute_validIndex_marksFavourite() throws Exception {
        FavCommand command = new FavCommand(0);
        CommandResult result = command.execute(model);
        Person updatedPerson = model.getFilteredPersonList().get(0);
        assertEquals(updatedPerson.isFavourite(), true);
        assertEquals(result.getFeedbackToUser(), "Marked Isaac as favourite.");
    }

    @Test
    void execute_alreadyFavourite_throwsCommandException() throws Exception {
        person.setFavourite(true);
        FavCommand command = new FavCommand(0);
        assertThrows(seedu.address.logic.commands.exceptions.CommandException.class, () -> command.execute(model));
    }

    @Test
    void execute_invalidIndex_throwsCommandException() {
        FavCommand command = new FavCommand(5);
        assertThrows(seedu.address.logic.commands.exceptions.CommandException.class, () -> command.execute(model));
    }
}
