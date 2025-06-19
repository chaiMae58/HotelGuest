import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


/**
 * Main GUI class for the Hotel Management System.
 * Handles all the graphical user interface interactions.
 */

public class HotelAppUI {
    private final Hotel hotel;


    /**
     * This constructor: Loads saved hotel state or creates a new one with default settings.
     * We can change the default room count and default prince there
     */

    public HotelAppUI() {

        final int DEFAULT_ROOM_COUNT = 6;
        final double DEFAULT_PRICE = 20.0;

        Hotel loadedHotel = HotelPersistence.loadHotel();

        if (loadedHotel != null &&
                loadedHotel.getTotalRooms() == DEFAULT_ROOM_COUNT &&
                loadedHotel.getBasePrice() == DEFAULT_PRICE) {

            hotel = loadedHotel; // Accept loaded hotel
        } else {

            hotel = new Hotel(DEFAULT_ROOM_COUNT, DEFAULT_PRICE);
        }
    }

    /**
     * Creates and displays the GUI window.
     * Saves hotel when we close the window
     */
    public void createAndShowGUI() {

       // main frame setup
        JFrame frame = new JFrame("Hotel Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 450);
        frame.setLocationRelativeTo(null); // center on screen
        frame.setLayout(new BorderLayout(10, 10));

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                HotelPersistence.saveHotel(hotel);
            }
        });

        // Hotel Name Display

        String hotelName = "My Hotel";
        JLabel titleLabel = new JLabel(" " + hotelName, SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0, 102, 204)); // Blue
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Base prince and room count
        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        JLabel priceLabel = new JLabel(" Base Price: €" + hotel.getBasePrice());
        priceLabel.setForeground(new Color(0, 153, 153)); // Green
        priceLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JLabel roomCountLabel = new JLabel("Total Rooms: " + hotel.getTotalRooms());
        roomCountLabel.setForeground(new Color(255, 0, 68)); // Orange
        roomCountLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        infoPanel.add(priceLabel);
        infoPanel.add(roomCountLabel);
        frame.add(infoPanel, BorderLayout.SOUTH);

        // Buttons panel with vertical layout

        JPanel buttonPanel = getJPanel();
// centring the button panel inside the frame
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(buttonPanel);
        frame.add(centerPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JPanel getJPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 0, 10));
        buttonPanel.setPreferredSize(new Dimension(200, 250));

        JButton registerButton = new JButton("Register Guest");
        JButton deregisterButton = new JButton("Deregister Guest");
        JButton occupancyButton = new JButton("View Occupancy");
        JButton historyButton = new JButton("Room History & Status");
        JButton reportButton = new JButton("Occupancy Report");
        // Button actions
        registerButton.addActionListener(e -> showRegisterDialog());
        deregisterButton.addActionListener(e -> showDeregisterDialog());
        occupancyButton.addActionListener(e -> showOutput(hotel::showOccupiedRooms));
        historyButton.addActionListener(e -> showOutput(hotel::showRoomHistory));
        reportButton.addActionListener(e -> showOutput(hotel::showOccupancyReport));
// Adding buttons to panel
        buttonPanel.add(registerButton);
        buttonPanel.add(deregisterButton);
        buttonPanel.add(occupancyButton);
        buttonPanel.add(historyButton);
        buttonPanel.add(reportButton);
        return buttonPanel;
    }

    /**
     * Displays dialog to register a guest.
     * Validates input and registers via hotel instance.
     */
    private void showRegisterDialog() {
        JTextField firstName = new JTextField();
        JTextField lastName = new JTextField();
        Object[] fields = {
                "First Name:", firstName,
                "Last Name:", lastName
        };

        int result = JOptionPane.showConfirmDialog(null, fields, "Register Guest", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String fname = firstName.getText().trim();
            String lname = lastName.getText().trim();

            // Name validation
            String nameRegex = "^[A-Za-z]+([ '-]?[A-Za-z]+)*$";

            if (fname.isEmpty() || lname.isEmpty()) {
                JOptionPane.showMessageDialog(null, "First and last name cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!fname.matches(nameRegex) || !lname.matches(nameRegex)) {
                JOptionPane.showMessageDialog(null, "Names must contain only letters, optionally separated by spaces or hyphens.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Call hotel.registerGuest, get message directly
            String message = hotel.registerGuest(fname, lname);

            // Show the returned message in the dialog
            JOptionPane.showMessageDialog(null, message, "Registration Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    /**
     * Displays a dialog to deregister a guest from a room by number.
     */

    private void showDeregisterDialog() {
        String input = JOptionPane.showInputDialog(null, "Enter Room Number to Checkout:", "Deregister Guest", JOptionPane.QUESTION_MESSAGE);
        if (input != null) {
            try {
                int roomNumber = Integer.parseInt(input.trim());
                String message = hotel.deregisterGuest(roomNumber);
                JOptionPane.showMessageDialog(null, message, "Deregistration Result", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Redirects output of a runnable into a GUI text area.
     * Used to display hotel reports.
     */
    private void showOutput(Runnable action) {
        JTextArea textArea = new JTextArea(20, 40);
        // capture printed output
        PrintStream oldOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
// run hotel action that prints the utput
        action.run();
// flush and setOut to restore normal output
        System.out.flush();
        System.setOut(oldOut);
// display captured output
        textArea.setText(baos.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scrollPane, "Output", JOptionPane.INFORMATION_MESSAGE);
    }
}
