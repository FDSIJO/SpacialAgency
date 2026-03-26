package org.sijoexercise.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.sijoexercise.enums.Direction;

@Getter
@Setter
@AllArgsConstructor
public class Coordinates {
    private int x;
    private int y;
    private Direction direction;

    @Override
    public String toString() {
        return x + " " + y + " " + direction;
    }
}
