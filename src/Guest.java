import java.io.Serializable;

/**
 * Represents a guest in the hotel
 */
public class Guest implements Serializable {
    private final String firstName;
    private final String lastName;

    /**
     * Constructor
     * @param firstName
     * @param lastName
     */
    public Guest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    /**
     *
     * @return the guest's full name
     */
    public String toString() {
        return firstName + " " + lastName;
    }
}
