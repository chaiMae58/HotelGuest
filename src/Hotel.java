import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.Serializable;

/**
 * Hotel class that manages rooms and guests
 * Stores rooms mapped by they respective room number
 */

public class Hotel implements Serializable{



    private Map<Integer, Room> rooms;
    private final int totalRooms;
    private final double basePrice;

    /**
     *
     * @return basePrice
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     *
     * @return totalRooms
     */

    public int getTotalRooms() {
        return totalRooms;
    }

    /**
     *
     * @param totalRooms
     * @param basePrice
     */
    public Hotel(int totalRooms, double basePrice) {
        if (totalRooms <= 0 || totalRooms % 2 != 0) {
            throw new IllegalArgumentException("Total number of rooms must be a positive even number ! ");
        }
        this.totalRooms = totalRooms;
        this.basePrice = basePrice;

        initRooms(); //initialize rooms on creation
    }

    //
    /**
     * Initialize rooms :  half standard and half business type
     */
    private void initRooms() {
        rooms = new HashMap<>();
        int half = totalRooms / 2;
        for (int i = 1; i <= totalRooms; i++) {

            //first half number of rooms is Standard, second half is Business
            String type = (i <= half) ? "Standard" : "Business";
            rooms.put(i, RoomFactory.createRoom(i, type, basePrice));

        }
    }




    /**
     * Registers a guest to the first available room
     * @param firstName
     * @param lastName
     * @return info about guest registration
     *
     */

    public String registerGuest(String firstName, String lastName) {
        Guest guest = new Guest(firstName, lastName);
        for (Room room : rooms.values()) {
            if (!room.isOccupied()) {
                room.assignGuest(guest);
                return "Guest " + guest + " registered in " + room.getType() +
                        " Room number: " + room.getRoomNumber();
            }
        }
        return "Registration failed: no vacant rooms available";
    }

    /**
     * Deregisters a guest from a specific room number
     * @param roomNumber
     * @return info about guest registration
     */

    public String deregisterGuest(int roomNumber) {
        Room room = rooms.get(roomNumber);
        if (room != null && room.isOccupied()) {
            Guest guest = room.getCurrentGuest();
            room.removeGuest();
            return "Guest " + guest + " deregistered from " + room.getType() + " Room number: " + roomNumber;
        }
        return "Deregistration failed: room " + roomNumber + " is vacant or does not exist";
    }



    /**
     * Displays all currently occupied rooms with guest names
     */
    public void showOccupiedRooms() {
        System.out.println("Occupied rooms and guests:");
        boolean any = false;
        for (Room room : rooms.values()) {
            if (room.isOccupied()) {
                System.out.println("Room " + room.getRoomNumber() + ": " + room.getCurrentGuest());
                any = true;
            }
        }
        if (!any) {
            System.out.println("All hotel rooms are empty ! ");
        }
    }


    /**
     * Display full history of all guests per room and current status
     */
    public void showRoomHistory() {
        for (Room room : rooms.values()) {
            System.out.println("Room " + room.getRoomNumber() + " (" + room.getStatus() + ")");
            List<Guest> history = room.getGuestHistory();
            if (history.isEmpty()) {
                System.out.println("  This room has no previous guests.");
            } else {
                for (Guest g : history) {
                    System.out.println("  - " + g);
                }
            }
        }
    }




    /**
     * Displays occupancy stats sorted by number of stays (from Max to Min)
     */
    public void showOccupancyReport() {
        List<Room> sorted = new ArrayList<>(rooms.values());
        sorted.sort(Comparator.comparingInt(Room::getOccupancyCount).reversed());

        System.out.println("Room Occupancy Report:");
        for (Room room : sorted) {
            System.out.printf(
                    "Room number %d: %d stays, %.2f EUR total profit%n",
                    room.getRoomNumber(),
                    room.getOccupancyCount(),
                    room.getTotalProfit()
            );
        }
    }
}
