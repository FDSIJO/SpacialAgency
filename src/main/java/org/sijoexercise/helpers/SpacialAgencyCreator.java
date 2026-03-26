package org.sijoexercise.helpers;

import org.sijoexercise.enums.Direction;
import org.sijoexercise.enums.DroneCommand;
import org.sijoexercise.exceptions.BadInputsException;
import org.sijoexercise.models.Coordinates;
import org.sijoexercise.models.Drone;
import org.sijoexercise.models.Grid;
import org.sijoexercise.models.SpacialAgency;

import java.io.*;
import java.util.*;

public class SpacialAgencyCreator {

    public static SpacialAgency getSpacialAgency() throws BadInputsException {

        // Lecture du fichier 'inputs'
        var inputs = SpacialAgencyCreator.readInputs();

        try {
            // Ligne 0 : dimensions du strGrid
            var strGrid = inputs.get(0).split("");
            int maxX = Integer.parseInt(strGrid[0]);
            int maxY = Integer.parseInt(strGrid[1]);

            // Traitement des drones (2 lignes par drone)
            var droneCommands = new TreeMap<Drone, List<DroneCommand>>(Comparator.comparingInt(Drone::getId));
            for (int i = 1; i < inputs.size(); i += 2) {
                var strCoordinates = inputs.get(i).split("");
                var coordinates = new Coordinates(Integer.parseInt(strCoordinates[0]), Integer.parseInt(strCoordinates[1]), Direction.valueOf(strCoordinates[2]));
                var drone = new Drone(i, coordinates);
                droneCommands.put(drone, Arrays.stream(inputs.get(i + 1).split("")).map(DroneCommand::valueOf).toList());
            }

            // Création du plateau
            var grid = new Grid(maxX, maxY);

            // Création de la station spaciale
            return new SpacialAgency(grid, droneCommands);
        }
        catch (Exception e) {
            throw new BadInputsException(e);
        }
    }

    private static List<String> readInputs() {
        List<String> lines = new ArrayList<>();

        try (InputStream is = SpacialAgencyCreator.class.getClassLoader().getResourceAsStream("inputs");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = reader.readLine()) != null) lines.add(line.replaceAll("\\s+", ""));

        } catch (IOException e) {
            throw new RuntimeException("Failed to read the 'inputs' resource file. Please ensure the file exists in the classpath.", e);
        }

        return lines;
    }
}
