package kata.mowitnow.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Mower {
    private static final Logger LOGGER = LoggerFactory.getLogger(Mower.class);
    private final Field field;
    private Position position;
    private Direction direction;
    private List<Command> commands;

    public Mower(Field field, Position position, Direction direction, List<Command> commands) {
        this.field = field;
        this.position = position;
        this.direction = direction;
        this.commands = commands;
    }

    public Field getField() {
        return field;
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public List<Command> getCommands() {
        return commands;
    }    

    public void start() {
        for (Command command : commands) {
            switch (command) {
                case G -> this.direction = this.direction.getLeft();
                case D -> this.direction = this.direction.getRight();
                case A -> move();
            }
        }
    }

    private void move() {
        switch (direction) {
            case E -> {
                if (this.position.getX() < field.getLength() - 1) {
                    this.position = new Position(position.getX() + 1, position.getY());
                }
            }
            case W -> {
                if (this.position.getX() > 1) {
                    this.position = new Position(position.getX() - 1, position.getY());
                }
            }
            case N -> {
                if (this.position.getY() < field.getLength() - 1) {
                    this.position = new Position(position.getX(), position.getY() + 1);
                }
            }
            case S -> {
                if (this.position.getY() > 1) {
                    this.position = new Position(position.getX(), position.getY() - 1);
                }
            }
        }
        LOGGER.info("moving to position: {} {}",position.getX(),position.getY());
    }
}
