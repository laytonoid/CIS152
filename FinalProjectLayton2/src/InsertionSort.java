import java.util.List;

public class InsertionSort {
	/**
	 * Sorts list of main person objects (users) based on skill chosen.
	 * @param people = list of people objects for sorting.
	 */
    public static void sortPeople(List<Person> people) {
        int n = people.size();
        for (int i = 1; i < n; ++i) {
            Person mainPerson = people.get(i);
            int mainService = mainPerson.getSkillOffered();
            int j = i - 1;
            while (j >= 0 && people.get(j).getSkillOffered() > mainService) {
            people.set(j + 1, people.get(j));
            j = j - 1;
        }
        people.set(j + 1, mainPerson);
        }
    }

    /**
     * Prints lit of people
     * @param people = list of people objects for printing
     */
    public static void printPeople(List<Person> people) {
        for (Person person : people) {
            System.out.println(person.getName() + ": " + ServiceType.fromInt(person.getSkillOffered()));
        }
    }
}
