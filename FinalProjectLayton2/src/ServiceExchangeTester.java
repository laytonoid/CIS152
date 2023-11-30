public class ServiceExchangeTester {
	/**
	 * ServiceExchangeTester class has a main method to test the functionality of S.E.A.
	 */
    public static void main(String[] args) {
        QueueManager queueManager = new QueueManager();

        // Creates instances of Person with int ServiceType values
        Person Han = new Person("Han", "123-456-7890", 8, 6); // Cooking, DIY
        Person Leia = new Person("Leia", "987-654-3210", 12, 5); // Childcare, art/Creativity
        Person Luke = new Person("Luke", "555-555-5555", 1, 11); // Education, Personal Care
        Person Chewbacca = new Person("Chewbacca", "444-444-4444", 11, 1); // Personal Care, Education
        Person Vader = new Person("Vader", "666-666-6666", 5, 0); // Art/Creativity, None
        Person Threepio = new Person("Threepio", "264-765-3567", 11, 1); // personal care, education
        Person Artoo = new Person("Artoo", "336-455-4424", 6, 8); // DIY, Cooking
        Person Kenobi = new Person("Kenobi", "665-463-5654", 9, 5); // Financial, Art/Creativity

        // Adds people to the queue and tries to match them
        addPersonToQueue(queueManager, Han);
        addPersonToQueue(queueManager, Leia);
        addPersonToQueue(queueManager, Luke);
        addPersonToQueue(queueManager, Chewbacca);
        addPersonToQueue(queueManager, Vader);
        addPersonToQueue(queueManager, Threepio);
        addPersonToQueue(queueManager, Artoo);
        addPersonToQueue(queueManager, Kenobi);
    }

    private static void addPersonToQueue(QueueManager queueManager, Person person) {
        System.out.println(person.getName() + " was added to the queue");
        queueManager.addToWaitingList(person);
        System.out.println("Current queue size: " + queueManager.getWaitingList().size());
    }
}