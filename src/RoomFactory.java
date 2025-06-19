

/**
 * Factpry class for creating Room instances based on type (Standard/Business)
 *
 * @param number     Room number
 * @param type       Room type ("standard", "business")
 * @param basePrice  Base price used to compute the actual room price
 *
 * @throws IllegalArgumentException if the room type is unknown
 */

public class RoomFactory {
    public static Room createRoom(int number, String type, double basePrice) {
        return switch (type.toLowerCase()) {
            case "standard" -> new StandardRoom(number,basePrice);
            case "business" -> new BusinessRoom(number, basePrice);
            default -> throw new IllegalArgumentException("Unknown room type");
        };
    }
}
