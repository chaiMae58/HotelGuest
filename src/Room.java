import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.Serializable;

/**
 * Abstract base class for the hotel rooms
 */
public abstract class Room implements Serializable {
    private final int roomNumber;
    private final double price;
    private Guest currentGuest;
    private final List<Guest> guestHistory = new ArrayList<>();
    private int occupancyCount = 0;
    private double totalProfit = 0.0;


    public Room(int number, double price) {
        this.roomNumber = number;
        this.price = price;
    }

    /**
     *
     * @return room number
     */
    public int getRoomNumber() {
        return roomNumber;
    }



    /**
     *
     * @return current guest if a room is occupied by a guest
     */
    public boolean isOccupied() {
        return currentGuest != null;
    }



    /**
     *
     * @return room status
     */
    public String getStatus() {
        return isOccupied() ? "Occupied" : "Vacant";
    }


    /**
     * Assign a guest to a room
     * @param guest
     */
    public void assignGuest(Guest guest) {
        currentGuest = guest;
        guestHistory.add(guest);
        occupancyCount++;
        totalProfit += getPrice();
    }

    //

    /**
     *
     * Implemented by subclasses "standard" and "business"
     * @return type of room
     */
    public abstract String getType();

    //

    /**
     * Removes the current guest from the room if present
     */
    public void removeGuest() {
        currentGuest = null;
    }


    /**
     *
     * @return current guest
     */
    public Guest getCurrentGuest() {
        return currentGuest;
    }

    /**
     *
     * @return a non-modifiable list of all past guests
     */
    public List<Guest> getGuestHistory() {
        return Collections.unmodifiableList(guestHistory);
    }

    /**
     *
     * @return the count of occupancy of each room
     */
    public int getOccupancyCount() {
        return occupancyCount;
    }

    /**
     *
     * @return the total profit per room
     */

    public double getTotalProfit() {
        return totalProfit;
    }

    /**
     *
     * @return the room price
     */

    public double getPrice(){
        return price;
    }
}
