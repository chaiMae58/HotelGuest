
import javax.swing.*;

/** Main class to launch the hotel application
* This class uses SwingUtilities.invokeLater to ensure GUI creation runs on the Event Dispatch Thread
 * which is the correct and safe way to initialize Swing GUIs.
* */

public class HotelApp {
    public static void main(String[] args) {
        System.out.println("Hotel App launching...");
        SwingUtilities.invokeLater(() -> new HotelAppUI().createAndShowGUI());
    }
}









