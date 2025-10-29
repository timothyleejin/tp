package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s fields matches all the given keywords
 */
public class FilterPredicate implements Predicate<Person> {

    private final PersonFilter filterParams;

    public FilterPredicate(PersonFilter filterParams) {
        this.filterParams = filterParams;
    }

    private boolean checkEventAndRole(Event e, Role r, Person person) {
        return person.getEvents().contains(e) && person.getRole(e).equals(r);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterPredicate)) {
            return false;
        }

        FilterPredicate otherFilterPredicate = (FilterPredicate) other;
        return filterParams.equals(otherFilterPredicate.filterParams);
    }

    @Override
    public boolean test(Person person) {

        if (filterParams.equals(PersonFilter.getEmptyFilter())) {
            return false;
        }


        boolean checkName = filterParams.getNames().isEmpty()
                || filterParams.getNames().stream().anyMatch(
                        keyword -> person.getName().fullName.toLowerCase().contains(keyword.fullName.toLowerCase()));

        boolean checkPhone = filterParams.getPhones().isEmpty()
                || filterParams.getPhones().stream().anyMatch(
                        phone -> person.getPhone().value.toLowerCase().contains(phone.toLowerCase()));

        boolean checkEmail = filterParams.getEmails().isEmpty()
                || filterParams.getEmails().stream().anyMatch(
                        email -> person.getEmail().value.toLowerCase().contains(email.toLowerCase()));

        boolean checkTelegram = filterParams.getTelegrams().isEmpty()
                || filterParams.getTelegrams().stream().anyMatch(
                        telegram -> person.getTelegram().value.toLowerCase().contains(telegram.toLowerCase()));

        int eventsSize = filterParams.getEvents().size();
        boolean checkEvents = eventsSize == 0;

        for (int i = 0; i < eventsSize; i++) {
            Event e = filterParams.getEvents().get(i);
            boolean temp = person.getEvents().stream().anyMatch(
                    event -> event.value.toLowerCase().contains(e.value.toLowerCase()));

            if (temp) {
                checkEvents = true;
                break;
            }
        }

        int rolesSize = filterParams.getRoles().size();
        boolean checkRoles = rolesSize == 0;

        for (int i = 0; i < rolesSize; i++) {
            Role r = filterParams.getRoles().get(i);
            boolean temp = person.getRoles().stream().anyMatch(
                    role -> role.value.toLowerCase().contains(r.value.toLowerCase()));

            if (temp) {
                checkRoles = true;
                break;
            }
        }

        boolean checkSkill = filterParams.getSkills().isEmpty()
                || filterParams.getSkills().stream().anyMatch(
                        skill -> person.getSkills().stream().anyMatch(
                                personSkill -> personSkill.skillName.toLowerCase()
                                        .contains(skill.skillName.toLowerCase())));

        boolean matchFilters = checkName && checkPhone && checkEmail && checkTelegram
                && checkEvents && checkRoles && checkSkill;

        return matchFilters;
    }
}
