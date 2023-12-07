import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
/**
 * ServiceExchangeApp class code for GUI for users to register
 */
public class ServiceExchangeApp {

    private static List<Person> registeredUsers = new ArrayList<>();
    private static QueueManager queueManager = new QueueManager();

    public static void main(String[] args) {
        /**
         *  Creates window
         */
        JFrame frame = new JFrame("Services Exchange App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(320, 400);
        frame.setLayout(new BorderLayout());

        /**
         *  Logo Panel
         */
        JPanel logoPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 32));
            g.drawString("S.E.A.", 10, 50);
            }
        };
        logoPanel.setPreferredSize(new Dimension(600, 60));

        /**
         *  Main Panel for user interaction
         */
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Enter Name:");
        JTextField nameField = new JTextField(20);
        JLabel phoneLabel = new JLabel("Enter Phone Number (10 digits):");
        JTextField phoneField = new JTextField(20);
        JLabel offeredLabel = new JLabel("Service Offered:");
        JComboBox<String> offeredBox = new JComboBox<>(getChoice(false));
        JLabel wantedLabel = new JLabel("Service Wanted:");
        JComboBox<String> wantedBox = new JComboBox<>(getChoice(true));
        JButton registerButton = new JButton("Register");

        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(phoneLabel);
        mainPanel.add(phoneField);
        mainPanel.add(offeredLabel);
        mainPanel.add(offeredBox);
        mainPanel.add(wantedLabel);
        mainPanel.add(wantedBox);
        mainPanel.add(registerButton);

        /**
         *  Adds logo and main panel to frame
         */
        frame.add(logoPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        /**
         *  Register button
         */
        registerButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        	    String name = nameField.getText();
        	    String phoneNumber = phoneField.getText();
        	    int offeredService = offeredBox.getSelectedIndex() + 1;
        	    int wantedService = wantedBox.getSelectedIndex();
        	    if (!validName(name)) {
        	        JOptionPane.showMessageDialog(frame, "Invalid Name");
        	        return;
        	    }
        	    if (!validPhone(phoneNumber)) {
        	        JOptionPane.showMessageDialog(frame, "Invalid Phone Number");
        	        return;
        	    }
        	    Person person = new Person(name, phoneNumber, offeredService, wantedService);
        	    registeredUsers.add(person);
        	    queueManager.addToWaitingList(person);
        	    JOptionPane.showMessageDialog(frame, "Registered Successfully! Once you are"
        	    + " matched, you will receive a text message to the number provided. Thank you!");
        	}
        });

        frame.setVisible(true);
    }
    /**
     * checks if phone number has 10 digits
     */
    private static boolean validPhone(String phoneNumber) {
        return phoneNumber.length() == 10 && phoneNumber.chars().allMatch(Character::isDigit);
    }
    /**
     * checks for valid name (no digits)
     */
    private static boolean validName(String name) {
        return name.matches("[a-zA-Z\\s]+");
    }
    /**
     * allows "none" service option only for wanted services drop down
     */
    private static String[] getChoice(boolean none) {
        List<String> options = new ArrayList<>();
        if (none) {
            options.add("None");
        }
        for (Entry<Integer, String> entry : ServiceType.getServiceTypes().entrySet()) {
            if (entry.getKey() != 0) {
                options.add(entry.getValue());
            }
        }
        return options.toArray(new String[0]);
    }
}