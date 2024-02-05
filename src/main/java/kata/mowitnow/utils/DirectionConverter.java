package kata.mowitnow.utils;

import kata.mowitnow.exception.InvalidDirectionException;
import kata.mowitnow.model.Direction;

public class DirectionConverter {

    public static Direction convert(String value) {
        Direction direction;
        try {
            direction = Direction.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new InvalidDirectionException("Invalid Direction");
        }
        return direction;
    }
}
