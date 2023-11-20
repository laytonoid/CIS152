import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

public class ServiceExchangeApp {

    private static List<Person> registeredUsers = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static QueueManager queueManager = new QueueManager();

    public static void main(String[] args) {
        System.out.println("Welcome to the Community Services Exchange!");
        while (true) {
        System.out.println("Would you like to register? (yes/no)");
        String input = scanner.nextLine();
            if ("no".equalsIgnoreCase(input)) {
                System.out.println("Thank you. Goodbye!");
                break;
            }
            registerUser();
        }
    }

    private static void registerUser() {
        System.out.println("Enter name:");
        String name = scanner.nextLine();

        String phoneNumber;
        while (true) {
            System.out.println("Enter phone number (10 digits):");
            phoneNumber = scanner.nextLine();

            if (isValidPhoneNumber(phoneNumber)) {
                break;
            } else {
                System.out.println("Invalid phone number. Please enter a 10-digit number.");
            }
        }

        int offered = getServiceType("offered");
        int wanted = getServiceType("wanted");

        Person person = new Person(name, phoneNumber, offered, wanted);
        registeredUsers.add(person);
        queueManager.addToWaitingList(person);

        int positionInQueue = queueManager.getWaitingList().size();
        System.out.println("Thank you for registering!");
        System.out.println("You are position " + positionInQueue + " in the queue.");
    }

    /**
     * Checks if provided phone number is 10 digits.
     *
     * @param phoneNumber The phone number for validating
     * @return true if phoneNumber is valid
     */
    private static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 10) {
            return false;
        }
        for (char c : phoneNumber.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Prompts user for selection of service type for offered and wanted.
     * offered services allows entry of 1-13 and wanted allows 0-13
     */
    private static int getServiceType(String type) {
        int serviceType = -1;
        while (serviceType < 1 || serviceType > 13) {
            try {
                // Displays available service types
                System.out.println("Available Services:");
                for (Entry<Integer, String> entry : ServiceType.getServiceTypes().entrySet()) {
                    if (!"offered".equalsIgnoreCase(type) || entry.getKey() != 0) {
                        System.out.println(entry.getKey() + " - " + entry.getValue());
                    }
                }

                // Prompt based on the type of service
                if ("offered".equalsIgnoreCase(type)) {
                    System.out.println("What service do you have to offer? (Select 1-13):");
                } else if ("wanted".equalsIgnoreCase(type)) {
                    System.out.println("What service are you wanting in exchange? (Select 1-13, 0 if none):");
                    serviceType = -1; // Allows 0 for wanted
                }

                serviceType = Integer.parseInt(scanner.nextLine());

                // Checks for service type and makes sure its valid otherwise outputs invalid
                if (serviceType >= 0 && serviceType <= 13 &&
                    (!ServiceType.fromInt(serviceType).equals("Unknown")) &&
                    (!"offered".equalsIgnoreCase(type) || serviceType != 0)) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1-13.");
                    serviceType = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                if ("wanted".equalsIgnoreCase(type)) {
                    serviceType = -1; 
                }
            }
        }
        return serviceType;
    }
}