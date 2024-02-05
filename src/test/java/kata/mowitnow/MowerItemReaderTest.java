package kata.mowitnow;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import kata.mowitnow.batch.MowerItemReader;
import kata.mowitnow.exception.InvalidPositionException;

import java.io.File;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static kata.mowitnow.model.Direction.E;
import static kata.mowitnow.model.Direction.N;

@SpringBootTest
class MowerItemReaderTest {

    @Autowired
    MowerItemReader mowerItemReader;

    @Test
    void should_read_file_and_execute_one_cycle_of_commands() throws URISyntaxException, IOException {

        //Given
        URL resource = getClass().getResource("resources/test1.txt");
        File file = new File(resource.toURI());

        //When
        mowerItemReader.Scanning(file);

        //Then
        Assertions.assertEquals(2, mowerItemReader.getMower().getPosition().getX());
        Assertions.assertEquals(3, mowerItemReader.getMower().getPosition().getY());
        Assertions.assertEquals(N, mowerItemReader.getMower().getDirection());

    }

    @Test
    void should_read_file_and_execute_two_cycle_of_commands() throws URISyntaxException, IOException {

        //Given
        URL resource = getClass().getResource("resources/test2.txt");
        File file = new File(resource.toURI());

        //When
        mowerItemReader.Scanning(file);

        //Then
        Assertions.assertEquals(5, mowerItemReader.getMower().getPosition().getX());
        Assertions.assertEquals(1, mowerItemReader.getMower().getPosition().getY());
        Assertions.assertEquals(E, mowerItemReader.getMower().getDirection());

    }


    @Test
    void should_read_file_with_bad_field_input() throws URISyntaxException {

        //Given
        URL resource = getClass().getResource("resources/test3.txt");
        File file = new File(resource.toURI());

        //When
        InvalidPositionException thrown = Assertions.assertThrows(InvalidPositionException.class, () -> mowerItemReader.Scanning(file));

        //Then
        Assertions.assertEquals("Invalid Position Data", thrown.getMessage());

    }

}
