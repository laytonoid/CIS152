import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QueueManager {
    private Queue<Person> waitingList;
    private List<Person> registeredUsers;
    private int addUserCount;

    public QueueManager() {
        waitingList = new LinkedList<>();
        registeredUsers = new ArrayList<>();
        addUserCount = 0;
    }

    public void addToWaitingList(Person person) {
        waitingList.offer(person);
        registeredUsers.add(person);
        addUserCount++;
        if (addUserCount >= 2) {
            matchUsers();
            addUserCount = 0;
        }
    }

    private void matchUsers() {
        InsertionSort.sortPeople(registeredUsers);

        List<Person> peopleToMatch = new ArrayList<>(registeredUsers);
        List<Person> matched = new ArrayList<>();

        for (Person offerer : new ArrayList<>(peopleToMatch)) {
            if (offerer.getSkillWanted() == 0) {
                continue;
            }

            for (Person requester : new ArrayList<>(peopleToMatch)) {
                if (offerer.getSkillWanted() == requester.getSkillOffered() &&
                    requester.getSkillWanted() == offerer.getSkillOffered()) {
                    System.out.println("Matched " + offerer.getName() + " with " + requester.getName());
                    matched.add(offerer);
                    matched.add(requester);
                    peopleToMatch.remove(requester);
                    peopleToMatch.remove(offerer);
                    break;
                }
            }
        }

        registeredUsers.removeAll(matched);
        waitingList = new LinkedList<>(registeredUsers);
    }

    public Person removeFromWaitingList() {
        Person person = waitingList.poll();
        if (person != null) {
            registeredUsers.remove(person);
        }
        return person;
    }

    public Queue<Person> getWaitingList() {
        return waitingList;
    }
}