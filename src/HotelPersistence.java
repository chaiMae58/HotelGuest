import java.io.*;

/**
 * Utility class for saving and loading Hotel objects using Java Serialization.
 */


    public class HotelPersistence {
        private static final String FILE_NAME = "hotel_data.ser";

        /**
         * Saves the hotel object to a file.
         */
        public static void saveHotel(Hotel hotel) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
                oos.writeObject(hotel);
            } catch (IOException e) {
                System.err.println("Error saving hotel data: " + e.getMessage());
            }
        }

        /**
         * Loads the hotel object from a file, or returns null if loading fails.
         */
        public static Hotel loadHotel() {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                return (Hotel) ois.readObject();
            } catch (IOException e) {
                System.err.println("Error reading hotel data: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.err.println("Error loading hotel class definition: " + e.getMessage());
            }
            return null;
        }
    }

