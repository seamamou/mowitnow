package kata.mowitnow.utils;

import kata.mowitnow.exception.InvalidCommandException;
import kata.mowitnow.model.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandConverter {

    public static List<Command> convert(String line) {
        String[] values = line.split("");
        List<Command> commands = new ArrayList<>();
        for (String a : values) {
            try {
                commands.add(Command.valueOf(a));
            } catch (IllegalArgumentException e) {
                throw new InvalidCommandException("Invalid Command");
            }
        }
        return commands;
    }
}
