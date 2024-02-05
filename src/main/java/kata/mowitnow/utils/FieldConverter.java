package kata.mowitnow.utils;

import kata.mowitnow.exception.FieldLineNotFoundException;
import kata.mowitnow.exception.InvalidFieldLineException;
import kata.mowitnow.model.Field;

public class FieldConverter {

    public static Field convert(String line) {
        if (line == null || line.isEmpty()) {
            throw new FieldLineNotFoundException("Field line is empty");
        }
        String[] values = line.split(" ");
        try {
            return new Field(Integer.parseInt(values[0]) + 1, Integer.parseInt(values[1]) + 1);
        } catch (NumberFormatException e) {
            throw new InvalidFieldLineException("Invalid Filed Line");
        }
    }
}
