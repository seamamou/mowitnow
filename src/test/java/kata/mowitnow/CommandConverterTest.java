package kata.mowitnow;

import kata.mowitnow.exception.InvalidCommandException;
import kata.mowitnow.model.Command;
import kata.mowitnow.utils.CommandConverter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static kata.mowitnow.model.Command.A;
import static kata.mowitnow.model.Command.G;

class CommandConverterTest {

    @Test
    void should_convert_GAGAGAGAA_from_string_to_commands() {
        //Given
        String line = "GAGAGAGAA";

        //When
        List<Command> commands = CommandConverter.convert(line);

        //Then
        Assertions.assertEquals(G, commands.get(0));
        Assertions.assertEquals(A, commands.get(1));
        Assertions.assertEquals(G, commands.get(4));
        Assertions.assertEquals(A, commands.get(7));
    }

    @Test
    void should_throw_exception_invalid_command() {

        //Given
        String line = "erdrr74";

        //When
        InvalidCommandException thrown = Assertions.assertThrows(InvalidCommandException.class, () -> CommandConverter.convert(line));

        //Then
        Assertions.assertEquals("Invalid Command", thrown.getMessage());

    }
}
