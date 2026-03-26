package org.sijoexercise.models;

import lombok.RequiredArgsConstructor;
import org.sijoexercise.enums.Direction;
import org.sijoexercise.enums.DroneCommand;

@RequiredArgsConstructor
public class Grid {

    private final int rows;
    private final int cols;

    public void move(Drone drone, DroneCommand droneCommand) {
        switch (droneCommand) {
            case L -> moveLeft(drone);
            case R -> moveRight(drone);
            case F -> moveForward(drone);
        }
    }

    private void moveLeft(Drone drone) {
        var droneCoordinates = drone.getCoordinates();
        var newDirection = Direction.rotateLeft(droneCoordinates.getDirection());
        droneCoordinates.setDirection(newDirection);
    }

    private void moveRight(Drone drone) {
        var droneCoordinates = drone.getCoordinates();
        var newDirection = Direction.rotateRight(droneCoordinates.getDirection());
        droneCoordinates.setDirection(newDirection);
    }

    private void moveForward(Drone drone) {
        var droneCoordinates = drone.getCoordinates();
        switch (droneCoordinates.getDirection()) {
                case N -> {
                    if (droneCoordinates.getY() < rows) {
                        droneCoordinates.setY(droneCoordinates.getY() + 1);
                    }
                }
                case E -> {
                    if (droneCoordinates.getX() < cols) {
                        droneCoordinates.setX(droneCoordinates.getX() + 1);
                    }
                }
                case S -> {
                    if (droneCoordinates.getY() > 0) {
                        droneCoordinates.setY(droneCoordinates.getY() - 1);
                    }
                }
                case W -> {
                    if (droneCoordinates.getX() > 0) {
                        droneCoordinates.setX(droneCoordinates.getX() - 1);
                    }
                }
            }
    }
}
