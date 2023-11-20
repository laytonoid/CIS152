import java.util.HashMap;
import java.util.Map;

public class ServiceType {
    private static final Map<Integer, String> serviceTypes = new HashMap<>();

    static {
        serviceTypes.put(0, "None");
        serviceTypes.put(1, "Education");
        serviceTypes.put(2, "Tech/IT");
        serviceTypes.put(3, "Home/Garden");
        serviceTypes.put(4, "Wellness");
        serviceTypes.put(5, "Art/Creativity");
        serviceTypes.put(6, "DIY");
        serviceTypes.put(7, "Transportation");
        serviceTypes.put(8, "Cooking");
        serviceTypes.put(9, "Financial");
        serviceTypes.put(10, "Entertainment");
        serviceTypes.put(11, "Personal Care");
        serviceTypes.put(12, "Childcare");
        serviceTypes.put(13, "Pets");
    }

    /**
     * Retrieves service type associated with index
     */
    public static String fromInt(int index) {
        return serviceTypes.getOrDefault(index, "Unknown");
    }

    /**
     * Retrieves the entire map of service types.
     */
    public static Map<Integer, String> getServiceTypes() {
        return new HashMap<>(serviceTypes);
    }
}