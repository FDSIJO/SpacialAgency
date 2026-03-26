package org.sijoexercise.models;

import lombok.AllArgsConstructor;
import org.sijoexercise.enums.DroneCommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class SpacialAgency {

    private Grid grid;
    private Map<Drone, List<DroneCommand>> droneCommands;

    public void moveAllDrones() {
        droneCommands.forEach((drone, commands) -> {
            commands.forEach(command -> grid.move(drone, command));
            System.out.println(drone.getCoordinates());
        });
    }
}
