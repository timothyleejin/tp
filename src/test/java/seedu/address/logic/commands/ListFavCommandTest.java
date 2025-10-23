package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

class ListFavCommandTest {

    private Model model;
    private Person person1;
    private Person person2;

    @BeforeEach
    void setUp() {
        model = new ModelManager();

        person1 = new Person(new Name("Alice"), new Phone("123"), new Email("a@b.com"),
                new Telegram("@alice"), new Role("Dev"), new Event("Event1"), Collections.emptySet());
        person2 = new Person(new Name("Bob"), new Phone("456"), new Email("b@b.com"),
                new Telegram("@bob"), new Role("Tester"), new Event("Event2"), Collections.emptySet());

        person1.setFavourite(true);

        model.addPerson(person1);
        model.addPerson(person2);
    }

    @Test
    void execute_withFavourites_listsOnlyFavourites() throws Exception {
        ListFavCommand command = new ListFavCommand();
        CommandResult result = command.execute(model);
        String expected = "Favourites:\n1. Alice\n";
        assertEquals(result.getFeedbackToUser(), expected);
    }

    @Test
    void execute_noFavourites_returnsMessage() throws Exception {
        person1.setFavourite(false);
        ListFavCommand command = new ListFavCommand();
        CommandResult result = command.execute(model);
        assertEquals(result.getFeedbackToUser(), "No favourites found.");
    }
}
