package org.sijoexercise.helpers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sijoexercise.enums.Direction;
import org.sijoexercise.enums.DroneCommand;
import org.sijoexercise.models.Coordinates;
import org.sijoexercise.models.Drone;
import org.sijoexercise.models.Grid;

import static org.junit.jupiter.api.Assertions.*;


public class SpacialAgencyTest {

    private Grid grid;

    @BeforeEach
    void setUp() {
        grid = new Grid(6, 6);
    }

    // -------------------------------------------------------------------------
    // Rotation gauche (L)
    // -------------------------------------------------------------------------
    @Nested
    class RotateLeft {

        @Test
        void fromNorth_shouldFaceWest() {
            Drone drone = new Drone(1, new Coordinates(2, 2, Direction.N));
            grid.move(drone, DroneCommand.L);
            assertEquals(Direction.W, drone.getCoordinates().getDirection());
        }

        @Test
        void fromWest_shouldFaceSouth() {
            Drone drone = new Drone(1, new Coordinates(2, 2, Direction.W));
            grid.move(drone, DroneCommand.L);
            assertEquals(Direction.S, drone.getCoordinates().getDirection());
        }

        @Test
        void fromSouth_shouldFaceEast() {
            Drone drone = new Drone(1, new Coordinates(2, 2, Direction.S));
            grid.move(drone, DroneCommand.L);
            assertEquals(Direction.E, drone.getCoordinates().getDirection());
        }

        @Test
        void fromEast_shouldFaceNorth() {
            Drone drone = new Drone(1, new Coordinates(2, 2, Direction.E));
            grid.move(drone, DroneCommand.L);
            assertEquals(Direction.N, drone.getCoordinates().getDirection());
        }
    }

    // -------------------------------------------------------------------------
    // Rotation droite (R)
    // -------------------------------------------------------------------------
    @Nested
    class RotateRight {

        @Test
        void fromNorth_shouldFaceEast() {
            Drone drone = new Drone(1, new Coordinates(2, 2, Direction.N));
            grid.move(drone, DroneCommand.R);
            assertEquals(Direction.E, drone.getCoordinates().getDirection());
        }

        @Test
        void fromEast_shouldFaceSouth() {
            Drone drone = new Drone(1, new Coordinates(2, 2, Direction.E));
            grid.move(drone, DroneCommand.R);
            assertEquals(Direction.S, drone.getCoordinates().getDirection());
        }

        @Test
        void fromSouth_shouldFaceWest() {
            Drone drone = new Drone(1, new Coordinates(2, 2, Direction.S));
            grid.move(drone, DroneCommand.R);
            assertEquals(Direction.W, drone.getCoordinates().getDirection());
        }

        @Test
        void fromWest_shouldFaceNorth() {
            Drone drone = new Drone(1, new Coordinates(2, 2, Direction.W));
            grid.move(drone, DroneCommand.R);
            assertEquals(Direction.N, drone.getCoordinates().getDirection());
        }
    }

    // -------------------------------------------------------------------------
    // Avancer (F) — déplacement normal
    // -------------------------------------------------------------------------
    @Nested
    class MoveForward {

        @Test
        void facingNorth_shouldIncrementY() {
            Drone drone = new Drone(1, new Coordinates(2, 2, Direction.N));
            grid.move(drone, DroneCommand.F);
            assertEquals(3, drone.getCoordinates().getY());
            assertEquals(2, drone.getCoordinates().getX());
        }

        @Test
        void facingSouth_shouldDecrementY() {
            Drone drone = new Drone(1, new Coordinates(2, 2, Direction.S));
            grid.move(drone, DroneCommand.F);
            assertEquals(1, drone.getCoordinates().getY());
            assertEquals(2, drone.getCoordinates().getX());
        }

        @Test
        void facingEast_shouldIncrementX() {
            Drone drone = new Drone(1, new Coordinates(2, 2, Direction.E));
            grid.move(drone, DroneCommand.F);
            assertEquals(3, drone.getCoordinates().getX());
            assertEquals(2, drone.getCoordinates().getY());
        }

        @Test
        void facingWest_shouldDecrementX() {
            Drone drone = new Drone(1, new Coordinates(2, 2, Direction.W));
            grid.move(drone, DroneCommand.F);
            assertEquals(1, drone.getCoordinates().getX());
            assertEquals(2, drone.getCoordinates().getY());
        }
    }

    // -------------------------------------------------------------------------
    // Avancer (F) — blocage aux bords de la grille
    // -------------------------------------------------------------------------
    @Nested
    class MoveForwardBoundary {

        @Test
        void facingNorth_atTopEdge_shouldNotMove() {
            Drone drone = new Drone(1, new Coordinates(2, 6, Direction.N));
            grid.move(drone, DroneCommand.F);
            assertEquals(6, drone.getCoordinates().getY());
        }

        @Test
        void facingSouth_atBottomEdge_shouldNotMove() {
            Drone drone = new Drone(1, new Coordinates(2, 0, Direction.S));
            grid.move(drone, DroneCommand.F);
            assertEquals(0, drone.getCoordinates().getY());
        }

        @Test
        void facingEast_atRightEdge_shouldNotMove() {
            Drone drone = new Drone(1, new Coordinates(6, 2, Direction.E));
            grid.move(drone, DroneCommand.F);
            assertEquals(6, drone.getCoordinates().getX());
        }

        @Test
        void facingWest_atLeftEdge_shouldNotMove() {
            Drone drone = new Drone(1, new Coordinates(0, 2, Direction.W));
            grid.move(drone, DroneCommand.F);
            assertEquals(0, drone.getCoordinates().getX());
        }
    }

    // -------------------------------------------------------------------------
    // Séquence de commandes
    // -------------------------------------------------------------------------
    @Nested
    class CommandSequence {

        @Test
        void lflf_fromNorthCenter_shouldFaceSouth() {
            Drone drone = new Drone(1, new Coordinates(2, 1, Direction.E));
            grid.move(drone, DroneCommand.F);
            grid.move(drone, DroneCommand.F);
            grid.move(drone, DroneCommand.L);
            grid.move(drone, DroneCommand.F);
            grid.move(drone, DroneCommand.F);
            grid.move(drone, DroneCommand.R);
            grid.move(drone, DroneCommand.F);
            grid.move(drone, DroneCommand.F);
            assertEquals(6, drone.getCoordinates().getX());
            assertEquals(3, drone.getCoordinates().getY());
            assertEquals(Direction.E, drone.getCoordinates().getDirection());
        }

        @Test
        void rotationComplete_fourRights_shouldReturnToOriginalDirection() {
            Drone drone = new Drone(1, new Coordinates(0, 3, Direction.N));
            grid.move(drone, DroneCommand.F);
            grid.move(drone, DroneCommand.F);
            grid.move(drone, DroneCommand.R);
            grid.move(drone, DroneCommand.F);
            grid.move(drone, DroneCommand.F);
            grid.move(drone, DroneCommand.L);
            grid.move(drone, DroneCommand.F);
            grid.move(drone, DroneCommand.F);
            grid.move(drone, DroneCommand.F);
            assertEquals(2, drone.getCoordinates().getX());
            assertEquals(6, drone.getCoordinates().getY());
            assertEquals(Direction.N, drone.getCoordinates().getDirection());
        }
    }
}
