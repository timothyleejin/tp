package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.ImageView;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


public class PersonCardTest {

    @BeforeAll
    static void intiToolkit() {
        new JFXPanel();
    }

    @Test
    void favouriteIcon_visibleWhenPersonIsFavourite() throws Exception {
        Person favouritePerson = new PersonBuilder().withName("Alice").build();
        favouritePerson.setFavourite(true);

        PersonCard card = new PersonCard(favouritePerson, 1);

        Platform.runLater(() -> {
            ImageView favIcon = card.getFavouriteIcon();
            assertTrue(favIcon.isVisible());
            assertTrue(favIcon.getImage() != null);
        });

        Thread.sleep(100);
    }

    @Test
    void favouriteIcon_hiddenWhenPersonIsNotFavourite() throws Exception {
        Person nonFavouritePerson = new PersonBuilder().withName("Bob").build();
        nonFavouritePerson.setFavourite(false);

        PersonCard card = new PersonCard(nonFavouritePerson, 1);

        Platform.runLater(() -> {
            ImageView favIcon = card.getFavouriteIcon();
            assertFalse(favIcon.isVisible());
        });

        Thread.sleep(100);
    }
}
