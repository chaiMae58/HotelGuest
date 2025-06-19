
/**
 * concrete class for Business room
 */
public class BusinessRoom extends Room  {
private static final double BUSINESS_RATE = 1.5;

    /**
     *
     * @param number of room
     * @param basePrice of standard room
     *                  We apply a 50% higher room price
     */

    public BusinessRoom(int number, double basePrice) {
        super(number, basePrice*BUSINESS_RATE);
    }

    /**
     *
     * @return room type "Business"
     */

    public String getType() {
        return "Business";
    }

}
