import java.time.LocalDateTime;

public class Person {
    private String name;
    private String phoneNumber;
    private int skillOffered;
    private int skillWanted;
    private LocalDateTime submissionTime;

    public Person(String name, String phoneNumber, int skillOffered, int skillWanted) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.skillOffered = skillOffered;
        this.skillWanted = skillWanted;
        this.submissionTime = LocalDateTime.now();
    }

    // Getters and Setters
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    
    public int getSkillOffered() { 
        return skillOffered;
    }
    
    public int getSkillWanted() { 
        return skillWanted;
    }

    public String getSkillOfferedString() {
        return ServiceType.fromInt(skillOffered);
    }

    public String getSkillWantedString() {
        return ServiceType.fromInt(skillWanted);
    }
    
    public LocalDateTime getSubmissionTime() { return submissionTime; }
}