package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s fields matches all the given keywords
 */
public class FilterPredicate implements Predicate<Person> {

    private final PersonFilter filterParams;

    public FilterPredicate(PersonFilter filterParams) {
        this.filterParams = filterParams;
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


        boolean checkName = filterParams.getName().map(
                keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword.fullName))
                .orElse(true);

        boolean checkPhone = filterParams.getPhone().map(
                phone -> StringUtil.containsWordIgnoreCase(person.getPhone().toString(), phone.toString()))
                .orElse(true);

        boolean checkEmail = filterParams.getEmail().map(
                email -> StringUtil.containsWordIgnoreCase(person.getEmail().toString(), email.toString()))
                .orElse(true);

        boolean checkAddress = filterParams.getAddress().map(
                address ->
                        StringUtil.containsWordIgnoreCase(person.getAddress().toString(), address.toString()))
                .orElse(true);

        boolean checkRole = filterParams.getRole().map(
                role ->
                        StringUtil.containsWordIgnoreCase(person.getRole().toString(), role.toString()))
                .orElse(true);

        boolean checkEvent = filterParams.getEvent().map(
                event -> StringUtil.containsWordIgnoreCase(person.getEvent().toString(), event.toString()))
                .orElse(true);

        boolean checkSkill = filterParams.getSkill().map(
                skill -> person.getSkills().stream().anyMatch(personSkill ->
                        personSkill.skillName.equalsIgnoreCase(skill.skillName)))
                .orElse(true);

        boolean matchFilters = checkName && checkPhone && checkEmail && checkAddress
                && checkRole && checkEvent && checkSkill;

        return matchFilters;
    }
}
