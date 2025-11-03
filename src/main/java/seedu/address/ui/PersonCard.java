package seedu.address.ui;


import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label telegram;
    @FXML
    private Label email;
    @FXML
    private FlowPane skills;
    @FXML
    private VBox eventsRolesContainer;
    @FXML
    private ImageView favouriteIcon;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        telegram.setText("@" + person.getTelegram().value);
        phone.setText(person.getPhone().value);
        email.setText(person.getEmail().value);

        person.getEvents().stream()
                .sorted(Comparator.comparing(event -> event.value))
                .forEach(event -> {
                    // Create HBox for each event-role pair
                    HBox eventRolePair = new HBox(5); // 5px spacing between event and role
                    eventRolePair.getStyleClass().add("event-role-pair");

                    // Event label
                    Label eventLabel = new Label(event.value);
                    eventLabel.getStyleClass().add("cell_small_label");

                    // Role label - will appear immediately to the right of the event
                    Label roleLabel = new Label(person.getRole(event).value);
                    roleLabel.getStyleClass().add("bubble");
                    eventLabel.setWrapText(true);
                    roleLabel.setWrapText(true);
                    roleLabel.setMinWidth(25);
                    eventRolePair.getChildren().addAll(eventLabel, roleLabel);
                    eventsRolesContainer.getChildren().add(eventRolePair);
                });

        person.getSkills().stream()
                .sorted(Comparator.comparing(skill -> skill.skillName))
                .forEach(skill -> skills.getChildren().add(new Label(skill.skillName)));
        favouriteIcon.setImage(person.isFavourite()
                ? new Image(this.getClass().getResourceAsStream("/images/fav.png"))
                : null);
        favouriteIcon.setVisible(person.isFavourite());
    }

}
