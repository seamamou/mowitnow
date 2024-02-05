package kata.mowitnow.batch;

import kata.mowitnow.exception.FieldLineNotFoundException;
import kata.mowitnow.exception.InvalidPositionException;
import kata.mowitnow.model.Command;
import kata.mowitnow.model.Direction;
import kata.mowitnow.model.Field;
import kata.mowitnow.model.Mower;
import kata.mowitnow.model.Position;
import kata.mowitnow.utils.CommandConverter;
import kata.mowitnow.utils.DirectionConverter;
import kata.mowitnow.utils.FieldConverter;
import kata.mowitnow.utils.PositionConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MowerItemReader implements ItemReader<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MowerItemReader.class);

    @Value("${file.input}")
    private String fileName;

    private Mower mower;


    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return Scanning(new ClassPathResource(fileName).getFile());
    }     

    public String Scanning(File file) throws IOException {

        try (Scanner scanner = new Scanner(file)) {
            Position position = null;
            Direction direction = null;
            List<Command> commands = null;

            if (!scanner.hasNextLine()) {
                throw new FieldLineNotFoundException("Field line is empty");
            }
            Field field = FieldConverter.convert(scanner.nextLine());
            LOGGER.info("=============================");
            LOGGER.info("Field size is: {}, {}",field.getLength()-1,field.getHeight()-1);

            int lineNumber = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                LOGGER.info("reading line: {}", line);
                if (lineNumber % 2 == 1) {
                    String[] values = line.split(" ");
                    position = PositionConverter.convert(values);
                    if (!position.isValidPosition(field)) {
                        throw new InvalidPositionException("Invalid Position Data");
                    }
                    direction = DirectionConverter.convert(values[2]);
                } else {
                    commands = CommandConverter.convert(line);
                    mower = new Mower(field, position, direction, commands);
                    mower.start();
                    LOGGER.info("Final Position {} {} {}", mower.getPosition().getX(), mower.getPosition().getY(), mower.getDirection());
                    LOGGER.info("=============================");
                }
                lineNumber++;
            }

            LOGGER.info("no more lines to read");
            return null;
        }
    }

    public Mower getMower() {
        return mower;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
   
}
