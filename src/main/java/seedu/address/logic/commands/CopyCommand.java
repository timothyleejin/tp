package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

import static java.util.Objects.requireNonNull;

/**
 * Copies add command string of an existing contact to the clipboard for users to add a contact with similar fields.
 * Works even in headless environments by printing the command instead.
 */
public class CopyCommand extends Command {
    public static final String COMMAND_WORD = "copy";
    public static final String COMMAND_SHORTCUT = "c";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Copies the add command string of the person identified by the index number to the clipboard. "
            + "If clipboard does not work, manually copy command string from the display box.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Copied add command for %s to clipboard!\n"
            + "If paste (ctrl + v) does not work, "
            + "here is the add command string for you to add manually:\n%s";
    public static final String MESSAGE_INVALID_INDEX = "The person index provided is invalid.";

    private final Index targetIndex;

    public CopyCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex.getZeroBased() >= model.getFilteredPersonList().size()) {
            throw new CommandException(MESSAGE_INVALID_INDEX);
        }

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        String addCommand = getAddCommand(person);

        // Copy to clipboard
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(new StringSelection(addCommand), null);

        return new CommandResult(String.format(MESSAGE_SUCCESS, person.getName(), addCommand));
    }

    private String getAddCommand(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append("add ");
        sb.append("n/").append(person.getName().fullName).append(" ");
        sb.append("p/").append(person.getPhone().value).append(" ");
        sb.append("e/").append(person.getEmail().value).append(" ");
        sb.append("t/").append(person.getTelegram().value).append(" ");
        sb.append("ev/").append(person.getEvent().value).append(" ");
        sb.append("r/").append(person.getRole().value).append(" ");
        person.getSkills().forEach(skill -> sb.append("s/").append(skill.skillName).append(" "));
        return sb.toString().trim();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CopyCommand)) {
            return false;
        }

        CopyCommand otherCopyCommand = (CopyCommand) other;

        return this.targetIndex.equals(otherCopyCommand.targetIndex);
    }
}
