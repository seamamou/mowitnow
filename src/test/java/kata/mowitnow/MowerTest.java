package kata.mowitnow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.mowitnow.model.Command;
import kata.mowitnow.model.Direction;
import kata.mowitnow.model.Field;
import kata.mowitnow.model.Mower;
import kata.mowitnow.model.Position;

import java.util.ArrayList;
import java.util.List;

import static kata.mowitnow.model.Direction.E;
import static kata.mowitnow.model.Direction.N;

class MowerTest {

    @Test
    void should_initiate_field_with_h5_and_l5() {
        //Given
        Mower mower;
        Field field = new Field(6, 6);


        //When
        mower = new Mower(field, null, null, null);

        //Then
        Assertions.assertEquals(6, mower.getField().getLength());
        Assertions.assertEquals(6, mower.getField().getHeight());

    }

    @Test
    void should_initiate_position_1_2_N_given_field_5_5() {
        //Given
        Position position = new Position(1, 2);
        Direction direction = N;
        Mower mower;

        //When
        mower = new Mower(null, position, direction, null);

        //Then
        Assertions.assertEquals(1, mower.getPosition().getX());
        Assertions.assertEquals(2, mower.getPosition().getY());
        Assertions.assertEquals(N, mower.getDirection());

    }

    @Test
    void should_move_mower_following_GAGAGAGAA() {
        //Given
        Mower mower;
        Field field = new Field(6, 6);
        Position position = new Position(1, 2);
        Direction direction = N;
        
        List<Command> commands = new ArrayList<>();
        commands.add(Command.G);
        commands.add(Command.A);
        commands.add(Command.G);
        commands.add(Command.A);
        commands.add(Command.G);
        commands.add(Command.A);
        commands.add(Command.G);
        commands.add(Command.A);
        commands.add(Command.A);

        mower = new Mower(field, position, direction, commands);
        //When
        mower.start();

        //Then
        Assertions.assertEquals(2, mower.getPosition().getX());
        Assertions.assertEquals(3, mower.getPosition().getY());
        Assertions.assertEquals(N, mower.getDirection());

    }

    @Test
    void should_initiate_position_3_3_E_given_field_5_5() {
        //Given
        Position position = new Position(3, 3);
        Direction direction = E;
        Mower mower;

        //When
        mower = new Mower(null, position, direction, null);

        //Then
        Assertions.assertEquals(3, mower.getPosition().getX());
        Assertions.assertEquals(3, mower.getPosition().getY());
        Assertions.assertEquals(E, mower.getDirection());

    }


    @Test
    void should_move_mower_following_AADAADADDA() {
        //Given
        Mower mower;
        Field field = new Field(6, 6);
        Position position = new Position(3, 3);
        Direction direction = E;
        
        List<Command> commands = new ArrayList<>();
        commands.add(Command.A);
        commands.add(Command.A);
        commands.add(Command.D);
        commands.add(Command.A);
        commands.add(Command.A);
        commands.add(Command.D);
        commands.add(Command.A);
        commands.add(Command.D);
        commands.add(Command.D);
        commands.add(Command.A);

        mower = new Mower(field, position, direction, commands);
        //When
        mower.start();

        //Then
        Assertions.assertEquals(5, mower.getPosition().getX());
        Assertions.assertEquals(1, mower.getPosition().getY());
        Assertions.assertEquals(E, mower.getDirection());


    }
}
