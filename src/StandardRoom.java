// concrete class for Standard room
public class StandardRoom extends Room {

    // Constructor
    public StandardRoom(int number, double basePrice) {
        super(number, basePrice);
    }
    // returns room type (String)
    public String getType() {
        return "standard";
    }


}
