import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class ServiceExchangeApp {

    private static List<Person> registeredUsers = new ArrayList<>();
    private static QueueManager queueManager = new QueueManager();

    public static void main(String[] args) {
        // Create the main window
        JFrame frame = new JFrame("Services Exchange Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        // Logo Panel
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

        // Main Panel for user interaction
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Enter Name:");
        JTextField nameField = new JTextField(20);
        JLabel phoneLabel = new JLabel("Enter Phone Number (10 digits):");
        JTextField phoneField = new JTextField(20);
        JLabel offeredLabel = new JLabel("Service Offered:");
        JComboBox<String> offeredBox = new JComboBox<>(getServiceTypeOptions(false));
        JLabel wantedLabel = new JLabel("Service Wanted:");
        JComboBox<String> wantedBox = new JComboBox<>(getServiceTypeOptions(true));
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

        // Add logo and main panel to frame
        frame.add(logoPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        // Register button action
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phoneNumber = phoneField.getText();
                int offeredService = offeredBox.getSelectedIndex() + 1;
                int wantedService = wantedBox.getSelectedIndex();

                if (validPhone(phoneNumber)) {
                    Person person = new Person(name, phoneNumber, offeredService, wantedService);
                    registeredUsers.add(person);
                    queueManager.addToWaitingList(person);
                    JOptionPane.showMessageDialog(frame, "Registered Successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Phone Number.");
                }
            }
        });

        frame.setVisible(true);
    }

    private static boolean validPhone(String phoneNumber) {
        return phoneNumber.length() == 10 && phoneNumber.chars().allMatch(Character::isDigit);
    }

    private static String[] getServiceTypeOptions(boolean none) {
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