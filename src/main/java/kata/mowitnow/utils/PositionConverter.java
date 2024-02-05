package kata.mowitnow.utils;

import kata.mowitnow.exception.InvalidPositionException;
import kata.mowitnow.exception.PositionDataNotFoundException;
import kata.mowitnow.model.Position;

public class PositionConverter {
    public static Position convert(String[] values) {
        Position position;
        if (values == null || values.length != 3) {
            throw new PositionDataNotFoundException("Position data is empty or incomplete");
        }
        try {
            position = new Position(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
        } catch (NumberFormatException e) {
            throw new InvalidPositionException("Invalid Position Data");
        }
        return position;
    }
}
